package cz.projekt.infrastructure;


import cz.projekt.application.builder.ProductDetailBuilder;
import cz.projekt.application.handler.product.ProductCacheHandler;
import cz.projekt.application.helper.FlashMessageHelper;
import cz.projekt.domain.dto.ProductDetail;
import cz.projekt.domain.dto.ProductSuggest;
import cz.projekt.domain.exception.MotherException;
import cz.projekt.domain.message.Response;
import cz.projekt.domain.repository.database.ProductNativeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("product/")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductResource {

    private final ProductNativeRepository productNativeRepository;
    private final ProductDetailBuilder productDetailBuilder;
    private final ProductCacheHandler productCacheHandler;

    @GetMapping("suggest")
    public Response<List<ProductSuggest>> suggestProduct(@RequestParam("value") String value) {
        try {
            return new Response<>(true, productNativeRepository.suggest(value),
                    FlashMessageHelper.getMessages());
        } catch (MotherException e) {
            FlashMessageHelper.addMessage(FlashMessageHelper.fromException(e));
            return new Response<>(false, null, FlashMessageHelper.getMessages());
        }
    }


    @GetMapping("{productId}")
    public Response<ProductDetail> getProductDetail(@PathVariable Long productId) {
        try {
            return new Response<>(true, productDetailBuilder.build(productId),
                    FlashMessageHelper.getMessages());
        } catch (MotherException e) {
            FlashMessageHelper.addMessage(FlashMessageHelper.fromException(e));
            return new Response<>(false, null, FlashMessageHelper.getMessages());
        }
    }

    @GetMapping("sync")
    public Response<Boolean> syncProduct(@RequestParam(required = false) Long produktId) {
        try {
            productCacheHandler.sync(produktId);
            return new Response<>(true, true,
                    FlashMessageHelper.getMessages());
        } catch (MotherException e) {
            FlashMessageHelper.addMessage(FlashMessageHelper.fromException(e));
            return new Response<>(false, null, FlashMessageHelper.getMessages());
        }
    }

}
