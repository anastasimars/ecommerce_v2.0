package pl.ecommerce.product.model.dto;

import java.math.BigDecimal;

public record UpdateProductDetailsRequest(BigDecimal price, Integer quantity) {
}
