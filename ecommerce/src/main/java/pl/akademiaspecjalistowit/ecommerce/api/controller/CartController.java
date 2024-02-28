package pl.akademiaspecjalistowit.ecommerce.api.controller;

import org.springframework.http.ResponseEntity;
import pl.akademiaspecjalistowit.api.CartsApi;
import pl.akademiaspecjalistowit.model.ClientProductResponse;

import java.util.List;
import java.util.UUID;

public class CartController implements CartsApi {
    @Override
    public ResponseEntity<Void> addToCart(UUID cartId, UUID itemId, Integer quantity) {
        return CartsApi.super.addToCart(cartId, itemId, quantity);
    }

    @Override
    public ResponseEntity<Void> removeFromCart(UUID cartId, UUID itemId) {
        return CartsApi.super.removeFromCart(cartId, itemId);
    }

    @Override
    public ResponseEntity<Void> updateCartItemQuantity(UUID cartId, UUID itemId, Integer quantity) {
        return CartsApi.super.updateCartItemQuantity(cartId, itemId, quantity);
    }
    @Override
    public ResponseEntity<List<ClientProductResponse>> showCart(UUID cartId) {
        return CartsApi.super.showCart(cartId);
    }
}
