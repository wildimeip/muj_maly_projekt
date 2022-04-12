package cz.projekt.application.handler.product;


import cz.projekt.domain.entity.database.Product;
import cz.projekt.domain.entity.redis.ProductRedis;
import cz.projekt.domain.exception.ProductSyncException;
import cz.projekt.domain.repository.database.ProductRepository;
import cz.projekt.domain.repository.redis.ProductRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCacheHandler {

    private final ProductRedisRepository productRedisRepository;
    private final ProductRepository productRepository;


    public void sync(){
        productRepository.findAll().forEach(this::sync);
    }

    public void sync(Long productId){
        if(productId==null){
            this.sync();
            return;
        }
        sync(productRepository.findById(productId)
                .orElseThrow(()-> new ProductSyncException("Product not found and could not be sync!")));
    }

    public void sync(Product product){

        productRedisRepository.save(ProductRedis.builder()
                .productId(product.getId())
                .id(product.getName().toLowerCase())
                .build());
    }

}
