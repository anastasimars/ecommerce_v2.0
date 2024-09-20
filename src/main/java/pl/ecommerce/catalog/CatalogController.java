package pl.ecommerce.catalog;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.api.CatalogApi;
import pl.ecommerce.catalog.logic.service.CatalogService;
import pl.ecommerce.model.GetAvailableProducts200Response;

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

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("it works");
    }
}
