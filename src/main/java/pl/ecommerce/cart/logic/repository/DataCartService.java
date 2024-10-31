package pl.ecommerce.cart.logic.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ecommerce.cart.model.entity.CartEntity;
import pl.ecommerce.exception.CartNotFoundException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DataCartService {
    private final CartRepository cartRepository;

    public CartEntity getCartByTechnicalId(UUID technicalId) {
        return cartRepository.findByTechnicalId(technicalId)
                .orElseThrow(() -> new CartNotFoundException(String.format("No cart with cartId: %s",
                        technicalId)));
    }

    public CartEntity saveCart(CartEntity cart) {
        return cartRepository.save(cart);
    }
}
