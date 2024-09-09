package pl.akademiaspecjalistowit.ecommerce.cart.logic.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.exception.CartNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.exception.ItemNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartEntity;
import pl.akademiaspecjalistowit.ecommerce.product.model.ProductEntity;
import pl.akademiaspecjalistowit.ecommerce.cart.logic.repository.DataCartService;
import pl.akademiaspecjalistowit.ecommerce.product.logic.repository.DataProductService;
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
    @Transactional
    public void removeProductFromCart(UUID cartId, UUID productId) {
       CartEntity cart = dataCartService.getCartByTechnicalId(cartId)
               .orElseThrow(()-> new CartNotFoundException(String
                       .format("No cart with cartId: %s", cartId.toString())));
        cart.removeProduct(productId);
        dataCartService.saveCart(cart);
    }

    @Override
    public void updateCartProductQuantity(UUID cartId, UUID productId, Integer quantity) {

    }

    @Override
    public List<CartProductResponse> showCart(UUID cartId) {
        return null;
    }
}
