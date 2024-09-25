package pl.ecommerce.order.model.dto;

import pl.ecommerce.product.model.dto.ProductResponse;

public record OrderProductResponse(ProductResponse productResponse, Integer quantity) {
}
