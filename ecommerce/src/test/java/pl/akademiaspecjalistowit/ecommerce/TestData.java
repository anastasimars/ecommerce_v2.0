package pl.akademiaspecjalistowit.ecommerce;

import pl.akademiaspecjalistowit.ecommerce.activeuser.model.ActiveUserEntity;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartEntity;
import pl.akademiaspecjalistowit.ecommerce.cart.model.CartProductEntity;
import pl.akademiaspecjalistowit.ecommerce.product.model.ProductEntity;
import pl.akademiaspecjalistowit.ecommerce.util.values.UserRole;

import java.util.ArrayList;

import static pl.akademiaspecjalistowit.ecommerce.PreparedTestConstants.*;

public class TestData {

    public static CartEntity preparedTestEmptyCart(ActiveUserEntity activeUser) {
        CartEntity cartEntity = new CartEntity(PREPARED_UUID,
                new ArrayList<>(),
               null);
        cartEntity.assignUserToCart(activeUser);
        return cartEntity;
    }

    public static CartEntity preparedTestCartWithProduct(ActiveUserEntity activeUser) {
        CartEntity cartEntity = new CartEntity(PREPARED_UUID,
                new ArrayList<>(),
                null);
        ProductEntity productEntity = preparedTestProductEntity();
        cartEntity.addProduct(productEntity, PREPARED_QUANTITY);
        cartEntity.assignUserToCart(activeUser);
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

    public static ActiveUserEntity preparedTestActiveUser() {
        ActiveUserEntity activeUser = new ActiveUserEntity(PREPARED_UUID,
                PREPARED_USER_NAME,
                PREPARED_USER_SURNAME,
                null,
                PREPARED_EMAIL,
                PREPARED_PASSWORD,
                PREPARED_CURRENCY,
                PREPARED_BALANCE,
                UserRole.CLIENT,
                null);
        CartEntity cartEntity = preparedTestEmptyCart(activeUser);
        activeUser.assignCartToUser(cartEntity);
        return activeUser;
    }

}
