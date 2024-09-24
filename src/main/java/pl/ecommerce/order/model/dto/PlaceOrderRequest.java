package pl.ecommerce.order.model.dto;

import java.util.UUID;

public record PlaceOrderRequest(UUID cartId) {
}
