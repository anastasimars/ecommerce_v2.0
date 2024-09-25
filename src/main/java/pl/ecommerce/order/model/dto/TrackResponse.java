package pl.ecommerce.order.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrackResponse(UUID orderId,
                            String status,
                            LocalDateTime deliveryDateTime) {
}
