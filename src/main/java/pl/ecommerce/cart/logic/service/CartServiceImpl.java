package pl.ecommerce.cart.logic.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ecommerce.cart.logic.repository.DataCartService;
import pl.ecommerce.cart.model.dto.CartProductResponse;
import pl.ecommerce.cart.model.entity.CartEntity;
import pl.ecommerce.exception.CartNotFoundException;
import pl.ecommerce.exception.ItemNotFoundException;
import pl.ecommerce.product.logic.repository.DataProductService;
import pl.ecommerce.product.model.entity.ProductEntity;

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
                .orElseThrow(() -> new CartNotFoundException(String
                        .format("No cart with cartId: %s", cartId.toString())));
        cart.removeProduct(productId);
        dataCartService.saveCart(cart);
    }

    @Override
    public void updateCartProductQuantity(UUID cartId, UUID productId, Integer quantity) {
        //todo: implement logic here
    }

    @Override
    public List<CartProductResponse> showCart(UUID cartId) {
        //todo: implement logic here
        return null;
    }
}
