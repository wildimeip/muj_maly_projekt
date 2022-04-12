package cz.projekt.application.handler.item;

import cz.projekt.application.handler.product.ProductCacheHandler;
import cz.projekt.domain.dto.ItemNew;
import cz.projekt.domain.entity.database.Item;
import cz.projekt.domain.entity.database.Product;
import cz.projekt.domain.entity.redis.ProductRedis;
import cz.projekt.domain.enums.StatusEnum;
import cz.projekt.domain.exception.NewItemExeption;
import cz.projekt.domain.repository.database.ItemRepository;
import cz.projekt.domain.repository.database.ProductRepository;
import cz.projekt.domain.repository.database.ShoppingListRepository;
import cz.projekt.domain.repository.database.ValueTypeRepository;
import cz.projekt.domain.repository.redis.ProductRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewItemHandler {

    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ValueTypeRepository valueTypeRepository;
    private final ProductRedisRepository productRedisRepository;
    private final ProductCacheHandler productCacheHandler;

    @Transactional
    public void handle(ItemNew itemNew){

        Product product = getProduct(itemNew);

        checkIfListContainsProduct(itemNew,product);

        Item item = new Item();
        item.setProduct(product);
        item.setCreatedBy("NOT_DONE"); // TODO with security
        item.setAmount(itemNew.getAmount());
        item.setShoppingList(shoppingListRepository.getOne(itemNew.getListId()));
        item.setStatus(StatusEnum.PENDING);

        itemRepository.save(item);
    }

    private void checkIfListContainsProduct(ItemNew itemNew, Product product) {
        if(product.getId() == null){
            return;
        }

        if(itemRepository.existsByShoppingListIdAndProduct(itemNew.getListId(),product)){
            throw new NewItemExeption("Item is already on the list!");
        }
    }

    private Product getProduct(ItemNew itemNew) {

        if(itemNew.getProductId() != null){
            return productRepository.getOne(itemNew.getProductId());
        }

        Optional<ProductRedis> productRedis = productRedisRepository.findById(itemNew.getName().toLowerCase());

        if(productRedis.isPresent()){
            return productRepository.getOne(productRedis.get().getProductId());
        }

        Optional<Product> name = productRepository.findByName(itemNew.getName());

        if(name.isPresent()){
            productCacheHandler.sync(name.get());
            return name.get();
        }

        Product product = new Product();
        product.setName(itemNew.getName());
        product.setValueType(valueTypeRepository.findFirstByOrderById());
        return product;
    }


}
