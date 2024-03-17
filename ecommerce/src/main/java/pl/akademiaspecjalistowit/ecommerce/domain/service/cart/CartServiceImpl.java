package pl.akademiaspecjalistowit.ecommerce.domain.service.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.domain.repository.cart.DataCartService;
import pl.akademiaspecjalistowit.model.CartProductResponse;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    private final DataCartService dataCartService;
    @Override
    public void addItemToCart(UUID cartId, UUID itemId, Integer quantity) {

    }

    @Override
    public void removeItemFromCart(UUID cartId, UUID itemId) {

    }

    @Override
    public void updateCartItemQuantity(UUID cartId, UUID itemId, Integer quantity) {

    }

    @Override
    public List<CartProductResponse> showCart(UUID cartId) {
        return null;
    }
}
