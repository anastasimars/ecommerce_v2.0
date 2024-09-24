package pl.ecommerce.product.model.dto;


import pl.ecommerce.util.values.Currency;

import java.math.BigDecimal;

public record NewProductRequest(String name,
                                String description,
                                BigDecimal price,
                                Currency currency,
                                String category,
                                Integer quantity) {
}
