package pl.akademiaspecjalistowit.ecommerce.catalog;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.api.CatalogApi;
import pl.akademiaspecjalistowit.ecommerce.catalog.logic.service.CatalogService;
import pl.akademiaspecjalistowit.model.GetAvailableProducts200Response;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
class CatalogController implements CatalogApi {
    private final CatalogService catalogService;

    @Override
    public ResponseEntity<GetAvailableProducts200Response> getAvailableProducts(String category,
                                                                                BigDecimal minPrice,
                                                                                BigDecimal maxPrice,
                                                                                Integer page, Integer size) {
        return CatalogApi.super.getAvailableProducts(category, minPrice, maxPrice, page, size);
    }
}
