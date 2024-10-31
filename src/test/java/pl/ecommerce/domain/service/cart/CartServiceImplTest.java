package pl.ecommerce.domain.service.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ecommerce.cart.logic.repository.DataCartService;
import pl.ecommerce.cart.logic.service.CartServiceImpl;
import pl.ecommerce.cart.model.entity.CartEntity;
import pl.ecommerce.cart.model.entity.CartProductEntity;
import pl.ecommerce.product.logic.repository.DataProductService;
import pl.ecommerce.product.model.entity.ProductEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static pl.ecommerce.PreparedTestConstants.*;
import static pl.ecommerce.TestData.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
    @Mock
    private DataCartService dataCartServiceMock;
    @Mock
    private DataProductService dataProductServiceMock;
    @InjectMocks
    private CartServiceImpl cartSuT;


    @Test
    @DisplayName("should update quantity for existing product in the cart")
    void addItemToCart_whenProductExists_shouldUpdateCartProductQuantity() {
        //given data
        CartEntity givenCartEntity = preparedTestCartWithProduct(PREPARED_USER_UUID);
        ProductEntity givenProductEntity = preparedTestProductEntity_1();

        //prepare mock response from repository
        when(dataCartServiceMock.getCartByTechnicalId(PREPARED_CART_UUID))
                .thenReturn(givenCartEntity);
        when(dataProductServiceMock.getProductByTechnicalId(PREPARED_PRODUCT1_UUID))
                .thenReturn(givenProductEntity);

        //when
        cartSuT.addProductToCart(PREPARED_CART_UUID,
                PREPARED_PRODUCT1_UUID,
                PREPARED_QUANTITY_TO_ADD);

        //then
        verify(dataCartServiceMock).saveCart(givenCartEntity);
        CartProductEntity cartProduct = getCartProductEntity(givenCartEntity,
                givenProductEntity).get();
        assertEquals(EXPECTED_QUANTITY_FOR_CART_PRODUCT_ENTITY, cartProduct.getQuantity());

    }

    @Test
    @DisplayName("should add a new cart product")
    void addItemToCart_whenProductDoesNotExist_shouldAddNewProduct() {
        //given data
        CartEntity givenCartEntity = preparedTestCartWithProduct(PREPARED_CART_UUID);
        ProductEntity givenNewProductEntity = preparedTestProductEntity_2();

        //prepare mock response from repository
        when(dataCartServiceMock.getCartByTechnicalId(PREPARED_CART_UUID))
                .thenReturn(givenCartEntity);
        when(dataProductServiceMock.getProductByTechnicalId(PREPARED_PRODUCT2_UUID))
                .thenReturn(givenNewProductEntity);

        //when
        cartSuT.addProductToCart(PREPARED_CART_UUID,
                PREPARED_PRODUCT2_UUID,
                PREPARED_QUANTITY_TO_ADD);

        //then
        verify(dataCartServiceMock).saveCart(givenCartEntity);
        CartProductEntity newCartProductEntity = getCartProductEntity(givenCartEntity,
                givenNewProductEntity).get();
        assertEquals(PREPARED_QUANTITY, newCartProductEntity.getQuantity());
        assertEquals(EXPECTED_QUANTITY_OF_ALL_TYPES_PRODUCTS, givenCartEntity.getCartProducts().size());
    }

    @Test
    @DisplayName("should add a new cart product to empty cart")
    void addItemToCart_whenCartIsEmpty_shouldAddNewProduct() {
        //given data
        CartEntity givenCartEntity = preparedTestEmptyCart(PREPARED_CART_UUID);
        ProductEntity givenProductEntity = preparedTestProductEntity_2();

        //prepare mock response from repository
        when(dataCartServiceMock.getCartByTechnicalId(PREPARED_CART_UUID))
                .thenReturn(givenCartEntity);
        when(dataProductServiceMock.getProductByTechnicalId(PREPARED_PRODUCT2_UUID))
                .thenReturn(givenProductEntity);

        //when
        cartSuT.addProductToCart(PREPARED_CART_UUID,
                PREPARED_PRODUCT2_UUID,
                PREPARED_QUANTITY_TO_ADD);

        //then
        assertEquals(EXPECTED_QUANTITY_FOR_ADD_ONE_PRODUCT_TO_EMPTY_CART,
                givenCartEntity.getCartProducts().size());
    }

    @Test
    @DisplayName("should delete a product from cart")
    void removeItemFromCart_shouldRemoveProductFromCart() {
        //given data
        CartEntity givenCartEntity = preparedTestCartWithProduct(PREPARED_USER_UUID);
        CartProductEntity givenCartProductEntity = preparedTestCartProduct_1(givenCartEntity);
        UUID givenProductEntityId = givenCartProductEntity.getProduct().getTechnicalId();

        //prepare mock response from repository
        when(dataCartServiceMock.getCartByTechnicalId(PREPARED_CART_UUID))
                .thenReturn(givenCartEntity);
        //when
        cartSuT.removeProductFromCart(PREPARED_CART_UUID, givenProductEntityId);

        //then
        verify(dataCartServiceMock).saveCart(givenCartEntity);
        verify(dataCartServiceMock, times(1)).saveCart(givenCartEntity);
        assertFalse(givenCartEntity.getCartProducts().contains(givenCartProductEntity));
    }

    @Test
    void updateCartItemQuantity() {
    }

    @Test
    void showCart() {
    }

    private static Optional<CartProductEntity> getCartProductEntity(CartEntity givenCartEntity, ProductEntity givenProductEntity) {
        return givenCartEntity.getCartProducts()
                .stream().filter(cartProductEntity ->
                        cartProductEntity.getProduct().equals(givenProductEntity)).findFirst();
    }
}