package pl.ecommerce.cart.logic.service;

import pl.ecommerce.model.CartProductResponse;

import java.util.List;
import java.util.UUID;

public interface CartService {
    void addProductToCart(UUID cartId, UUID productId, Integer quantity);

    void removeProductFromCart(UUID cartId, UUID productId);

    void updateCartProductQuantity(UUID cartId, UUID productId, Integer quantity);

    List<CartProductResponse> showCart(UUID cartId);

}
