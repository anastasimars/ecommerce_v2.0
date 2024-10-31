package pl.ecommerce;


import pl.ecommerce.cart.model.entity.CartEntity;
import pl.ecommerce.cart.model.entity.CartProductEntity;
import pl.ecommerce.product.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.UUID;

import static pl.ecommerce.PreparedTestConstants.*;

public class TestData {

    public static CartEntity preparedTestEmptyCart(UUID userId) {
        CartEntity cartEntity = new CartEntity(PREPARED_CART_UUID,
                new ArrayList<>(),
                null);
        cartEntity.assignUserToCart(userId);
        return cartEntity;
    }

    public static CartEntity preparedTestCartWithProduct(UUID userId) {
        CartEntity cartEntity = new CartEntity(PREPARED_CART_UUID,
                new ArrayList<>(),
                null);
        CartProductEntity cartProductEntity = preparedTestCartProduct_1(cartEntity);
        cartEntity.getCartProducts().add(cartProductEntity);
        cartEntity.assignUserToCart(userId);
        return cartEntity;
    }

    public static CartProductEntity preparedTestCartProduct_1(CartEntity cart) {
        return new CartProductEntity(cart,
                preparedTestProductEntity_1(), PREPARED_QUANTITY);
    }

    public static CartProductEntity preparedTestCartProduct_2(CartEntity cart) {
        return new CartProductEntity(cart,
                preparedTestProductEntity_2(), PREPARED_QUANTITY);
    }

    public static ProductEntity preparedTestProductEntity_1() {
        return new ProductEntity(PREPARED_PRODUCT1_UUID,
                PREPARED_PRODUCT_NAME,
                PREPARED_PRODUCT_DESCRIPTION,
                PREPARED_PRODUCT_CATEGORY,
                PREPARED_PRICE,
                PREPARED_CURRENCY,
                PREPARED_STOCK_STATE);
    }

    public static ProductEntity preparedTestProductEntity_2() {
        return new ProductEntity(PREPARED_PRODUCT2_UUID,
                PREPARED_PRODUCT_NAME_2,
                PREPARED_PRODUCT_DESCRIPTION,
                PREPARED_PRODUCT_CATEGORY_2,
                PREPARED_PRICE,
                PREPARED_CURRENCY,
                PREPARED_STOCK_STATE);
    }


}
