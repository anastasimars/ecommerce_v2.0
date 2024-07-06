package pl.akademiaspecjalistowit.ecommerce.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.akademiaspecjalistowit.api.CartsApi;
import pl.akademiaspecjalistowit.ecommerce.domain.service.cart.CartService;
import pl.akademiaspecjalistowit.model.CartProductResponse;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
class CartController implements CartsApi {
    private final CartService cartService;

    @Override
    public ResponseEntity<Void> addToCart(UUID cartId, UUID productId, Integer quantity) {
        cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> removeFromCart(UUID cartId, UUID productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateCartItemQuantity(UUID cartId, UUID productId, Integer quantity) {
        cartService.updateCartProductQuantity(cartId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CartProductResponse>> showCart(UUID cartId) {
        List<CartProductResponse> cartProductResponses = cartService.showCart(cartId);
        return ResponseEntity.ok(cartProductResponses);
    }
}
