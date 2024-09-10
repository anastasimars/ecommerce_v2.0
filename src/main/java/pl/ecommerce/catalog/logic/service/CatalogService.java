package pl.ecommerce.catalog.logic.service;


import pl.ecommerce.model.GetAvailableProducts200Response;

import java.math.BigDecimal;

public interface CatalogService {
    GetAvailableProducts200Response getAvailableItems(String category,
                                                      BigDecimal minPrice,
                                                      BigDecimal maxPrice,
                                                      Integer page,
                                                      Integer size);
}
