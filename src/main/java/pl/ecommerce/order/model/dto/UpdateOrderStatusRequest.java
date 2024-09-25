package pl.ecommerce.order.model.dto;

import pl.ecommerce.util.values.OrderStatus;

public record UpdateOrderStatusRequest(OrderStatus status) {
}
