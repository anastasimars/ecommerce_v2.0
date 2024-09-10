package pl.ecommerce.order;

import org.springframework.http.ResponseEntity;
import pl.ecommerce.api.OrdersApi;
import pl.ecommerce.model.OrderResponse;
import pl.ecommerce.model.PlaceOrderRequest;
import pl.ecommerce.model.TrackResponse;
import pl.ecommerce.model.UpdateOrderStatusRequest;

import java.util.List;
import java.util.UUID;

class OrderController implements OrdersApi {
    @Override
    public ResponseEntity<List<OrderResponse>> viewAllOrders(UUID clientId) {
        return OrdersApi.super.viewAllOrders(clientId);
    }

    @Override
    public ResponseEntity<Void> placeOrder(  PlaceOrderRequest placeOrderRequest) {
        return OrdersApi.super.placeOrder(placeOrderRequest);
    }

    @Override
    public ResponseEntity<Void> cancelOrder(UUID orderId) {
        return OrdersApi.super.cancelOrder(orderId);
    }

    @Override
    public ResponseEntity<TrackResponse> trackOrder(UUID orderId) {
        return OrdersApi.super.trackOrder(orderId);
    }

    @Override
    public ResponseEntity<Void> updateOrderStatus(UUID orderId, UpdateOrderStatusRequest updateOrderStatusRequest) {
        return OrdersApi.super.updateOrderStatus(orderId, updateOrderStatusRequest);
    }
}
