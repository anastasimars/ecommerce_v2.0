package pl.akademiaspecjalistowit.ecommerce.domain.service.cart;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.domain.exception.CartNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.domain.exception.ItemNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.domain.model.CartEntity;
import pl.akademiaspecjalistowit.ecommerce.domain.model.ProductEntity;
import pl.akademiaspecjalistowit.ecommerce.domain.repository.cart.DataCartService;
import pl.akademiaspecjalistowit.ecommerce.domain.repository.product.DataProductService;
import pl.akademiaspecjalistowit.model.CartProductResponse;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final DataCartService dataCartService;
    private final DataProductService dataProductService;

    @Override
    @Transactional
    public void addProductToCart(UUID cartId, UUID productId, Integer quantity) {
        ProductEntity product = dataProductService.getProductByTechnicalId(productId).orElseThrow(() ->
                new ItemNotFoundException(String.format("Item with id %s not found", productId.toString())));
        CartEntity cart = dataCartService.getCartByTechnicalId(cartId).orElseThrow(() ->
                new CartNotFoundException(String.format("No cart with cartId: %s", cartId.toString())));
        cart.addProduct(product, quantity);
        dataCartService.saveCart(cart);
    }

    @Override
    public void removeProductFromCart(UUID cartId, UUID productId) {

    }

    @Override
    public void updateCartProductQuantity(UUID cartId, UUID productId, Integer quantity) {

    }

    @Override
    public List<CartProductResponse> showCart(UUID cartId) {
        return null;
    }
}
