package pl.ecommerce.product.model.dto;

import pl.ecommerce.util.values.Currency;

import java.math.BigDecimal;

public record ProductResponse(String name,
                              String description,
                              String category,
                              BigDecimal price,
                              Currency currency) {
}
