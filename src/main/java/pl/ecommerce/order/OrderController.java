package pl.ecommerce.order;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.order.model.dto.OrderResponse;
import pl.ecommerce.order.model.dto.PlaceOrderRequest;
import pl.ecommerce.order.model.dto.TrackResponse;
import pl.ecommerce.order.model.dto.UpdateOrderStatusRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
class OrderController {
    @GetMapping("/customer/orders")
    public ResponseEntity<List<OrderResponse>> viewAllOrders(UUID clientId) {
        //will be implemented in a separate issue
        return null;
    }

    @PostMapping("/customer/orders/place")
    public ResponseEntity<Void> placeOrder(@RequestBody PlaceOrderRequest request) {
        //will be implemented in a separate issue
        return null;
    }

    @DeleteMapping("/customer/orders/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId) {
        //will be implemented in a separate issue
        return null;
    }

    @GetMapping("/customer/orders/track/{orderId}")
    public ResponseEntity<TrackResponse> trackOrder(@PathVariable UUID orderId) {
        //will be implemented in a separate issue
        return null;
    }

    @PutMapping("/seller/orders/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable UUID orderId,
                                                  @RequestBody UpdateOrderStatusRequest request) {
        //will be implemented in a separate issue
        return null;
    }
}
