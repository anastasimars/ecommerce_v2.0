package pl.ecommerce.cart.logic.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ecommerce.cart.logic.repository.DataCartService;
import pl.ecommerce.cart.model.dto.CartProductResponse;
import pl.ecommerce.cart.model.entity.CartEntity;
import pl.ecommerce.cart.model.entity.CartProductEntity;
import pl.ecommerce.exception.OutOfStockException;
import pl.ecommerce.product.logic.repository.DataProductService;
import pl.ecommerce.product.model.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final DataCartService dataCartService;
    private final DataProductService dataProductService;

    @Override
    @Transactional
    public void addProductToCart(UUID cartId, UUID productId, Integer quantity) {
        //adding products to cart don't change the product stock state
        ProductEntity product = dataProductService
                .getProductByTechnicalId(productId);
        CartEntity cart = dataCartService
                .getCartByTechnicalId(cartId);
        Optional<CartProductEntity> existProductInTheCart = findProductInCart(cart, product);
        Integer productStockState = product.getStockState();
        if (existProductInTheCart.isPresent()) {
            CartProductEntity cartProductEntity = existProductInTheCart.get();
            increaseTheQuantity(cartProductEntity, quantity, productStockState);
        } else {
            addNewProductToTheCart(cart, product, quantity);
        }
        dataCartService.saveCart(cart);
    }


    private Optional<CartProductEntity> findProductInCart(CartEntity cart, ProductEntity product) {
        if (cart.getCartProducts().isEmpty()) {
            return Optional.empty();
        } else {
            return cart.getCartProducts()
                    .stream()
                    .filter(cartProductEntity -> cartProductEntity
                            .getProduct()
                            .equals(product)).findAny();
        }
    }

    private void increaseTheQuantity(CartProductEntity cartProduct,
                                     Integer quantity,
                                     Integer productStockState) {
        Integer newQuantity = cartProduct.getQuantity() + quantity;
        if (productStockState >= newQuantity) {
            cartProduct.updateQuantity(newQuantity);
        } else {
            throw new OutOfStockException("Insufficient stock for item: "
                    + cartProduct.getProduct().getName());
        }
    }

    private void addNewProductToTheCart(CartEntity cart,
                                        ProductEntity product,
                                        Integer quantity) {
        if (product.getStockState() >= quantity) {
            CartProductEntity newProductForAdding = new CartProductEntity(cart, product, quantity);
            cart.getCartProducts().add(newProductForAdding);
        } else {
            throw new OutOfStockException("Insufficient stock for item: "
                    + product.getName());
        }

    }

    @Override
    @Transactional
    public void removeProductFromCart(UUID cartId, UUID productId) {
        CartEntity cart = dataCartService.getCartByTechnicalId(cartId);
        ProductEntity product = dataProductService
                .getProductByTechnicalId(productId);
        cart.getCartProducts().removeIf(cartProductEntity -> cartProductEntity.getProduct().equals(product));
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
