package cz.projekt.application.builder;

import cz.projekt.domain.dto.ProductDetail;
import cz.projekt.domain.entity.database.Product;
import cz.projekt.domain.exception.ItemBuilderException;
import cz.projekt.domain.repository.database.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductDetailBuilder {

    private final ProductRepository productRepository;

    public ProductDetail build(Long productId){

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ItemBuilderException("Item not found!"));

        return ProductDetail.builder()
                .productId(product.getId())
                .name(product.getName())
                .valueTypeId(product.getValueType().getId())
                .valueTypeName(product.getValueType().getValue())
                .build();

    }

}
