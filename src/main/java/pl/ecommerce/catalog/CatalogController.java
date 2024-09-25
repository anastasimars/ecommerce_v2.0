package pl.ecommerce.catalog;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.catalog.logic.service.CatalogService;
import pl.ecommerce.catalog.model.ProductSearchResultResponse;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
class CatalogController {
    private final CatalogService catalogService;

    @GetMapping("/catalog")
    public ResponseEntity<ProductSearchResultResponse> getAvailableProducts(
            @RequestParam(required = false)  String category,
            @RequestParam(required = false)  BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam (defaultValue = "10") Integer size) {
        ProductSearchResultResponse products = catalogService.getProducts(category, minPrice, maxPrice, page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("it works");
    }
}
