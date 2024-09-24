package pl.ecommerce.order.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public record OrderResponse(UUID technicalId,
                            List<OrderProductResponse> items,
                            BigDecimal totalAmount,
                            Currency currency,
                            LocalDateTime orderDateTime,
                            LocalDateTime deliveryDateTime,
                            String shippingMethod) {
}
