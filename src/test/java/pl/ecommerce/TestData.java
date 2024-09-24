package pl.ecommerce;


import pl.ecommerce.cart.model.entity.CartEntity;
import pl.ecommerce.cart.model.entity.CartProductEntity;
import pl.ecommerce.product.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.UUID;

import static pl.ecommerce.PreparedTestConstants.*;

public class TestData {

    public static CartEntity preparedTestEmptyCart(UUID userId) {
        CartEntity cartEntity = new CartEntity(PREPARED_UUID,
                new ArrayList<>(),
               null);
        cartEntity.assignUserToCart(userId);
        return cartEntity;
    }

    public static CartEntity preparedTestCartWithProduct(UUID userId) {
        CartEntity cartEntity = new CartEntity(PREPARED_UUID,
                new ArrayList<>(),
                null);
        ProductEntity productEntity = preparedTestProductEntity();
        cartEntity.addProduct(productEntity, PREPARED_QUANTITY);
        cartEntity.assignUserToCart(userId);
        return cartEntity;
    }
    public static CartProductEntity preparedTestCartProduct(CartEntity cart) {
        return new CartProductEntity(cart,
                preparedTestProductEntity(), 1);
    }

    public static ProductEntity preparedTestProductEntity() {
        return new ProductEntity(PREPARED_UUID,
                PREPARED_PRODUCT_NAME,
                PREPARED_PRODUCT_DESCRIPTION,
                PREPARED_PRODUCT_CATEGORY,
                PREPARED_PRICE,
                PREPARED_CURRENCY,
                PREPARED_STOCK_STATE);
    }
    public static ProductEntity preparedTestProductEntity_2() {
        return new ProductEntity(PREPARED_UUID,
                PREPARED_PRODUCT_NAME_2,
                PREPARED_PRODUCT_DESCRIPTION,
                PREPARED_PRODUCT_CATEGORY_2,
                PREPARED_PRICE,
                PREPARED_CURRENCY,
                PREPARED_STOCK_STATE);
    }



}
