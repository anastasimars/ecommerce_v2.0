package pl.ecommerce.cart.model.dto;

import pl.ecommerce.product.model.dto.ProductResponse;

public record CartProductResponse(ProductResponse item, Integer quantity) {
}
