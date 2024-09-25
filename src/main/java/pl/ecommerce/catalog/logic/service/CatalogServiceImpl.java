package pl.ecommerce.catalog.logic.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ecommerce.catalog.model.ProductSearchResultResponse;
import pl.ecommerce.product.logic.repository.DataProductService;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
class CatalogServiceImpl implements CatalogService {
    private final DataProductService dataProductService;

    @Override
    public ProductSearchResultResponse getProducts(String category,
                                                   BigDecimal minPrice,
                                                   BigDecimal maxPrice,
                                                   Integer page,
                                                   Integer size) {
        //todo: implement logic here
        return null;
    }
}
