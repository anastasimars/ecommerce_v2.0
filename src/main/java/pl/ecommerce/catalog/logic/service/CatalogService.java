package pl.ecommerce.catalog.logic.service;


import pl.ecommerce.catalog.model.ProductSearchResultResponse;

import java.math.BigDecimal;

public interface CatalogService {
    ProductSearchResultResponse getProducts(String category,
                                            BigDecimal minPrice,
                                            BigDecimal maxPrice,
                                            Integer page,
                                            Integer size);
}
