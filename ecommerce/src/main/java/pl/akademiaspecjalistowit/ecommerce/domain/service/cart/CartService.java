package pl.akademiaspecjalistowit.ecommerce.domain.service.cart;

import pl.akademiaspecjalistowit.model.CartProductResponse;

import java.util.List;
import java.util.UUID;

public interface CartService {
    void addItemToCart(UUID cartId, UUID itemId, Integer quantity);
    void removeItemFromCart(UUID cartId, UUID itemId);
    void updateCartItemQuantity(UUID cartId, UUID itemId, Integer quantity);
    List<CartProductResponse> showCart(UUID cartId);

}
