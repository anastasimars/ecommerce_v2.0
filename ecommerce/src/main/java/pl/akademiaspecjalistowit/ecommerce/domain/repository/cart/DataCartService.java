package pl.akademiaspecjalistowit.ecommerce.domain.repository.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.domain.model.CartEntity;

import java.util.Optional;
import java.util.UUID;
@Service
@AllArgsConstructor
public class DataCartService {
    private final CartRepository cartRepository;
    public Optional<CartEntity> getCartByTechnicalId(UUID technicalId) {
        return cartRepository.findByTechnicalId(technicalId);
    }

    public CartEntity saveCart(CartEntity cart) {
        return cartRepository.save(cart);
    }
}
