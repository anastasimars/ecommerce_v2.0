package pl.ecommerce.domain.service.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ecommerce.cart.logic.service.CartServiceImpl;
import pl.ecommerce.cart.model.CartEntity;
import pl.ecommerce.cart.model.CartProductEntity;
import pl.ecommerce.product.model.ProductEntity;
import pl.ecommerce.cart.logic.repository.DataCartService;
import pl.ecommerce.product.logic.repository.DataProductService;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
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
    void addItemToCart_whenProductExists_shouldUpdateQuantity() {
        //given data
        UUID givenCartId = UUID.randomUUID();
        UUID givenProductId = UUID.randomUUID();
        UUID givenUserId = UUID.randomUUID();
        Integer givenQuantityForExistingProduct = 1;
        Integer quantityToAdd = 2;
        CartEntity givenCartEntity = preparedTestEmptyCart(givenUserId);
        ProductEntity givenProductEntity = preparedTestProductEntity();

        // simulate existing product
        givenCartEntity.addProduct(givenProductEntity, givenQuantityForExistingProduct);

        //prepare mock response from repository
        when(dataCartServiceMock.getCartByTechnicalId(givenCartId))
                .thenReturn(Optional.of(givenCartEntity));
        when(dataProductServiceMock.getProductByTechnicalId(givenProductId))
                .thenReturn(Optional.of(givenProductEntity));

        //when
        cartSuT.addProductToCart(givenCartId, givenProductId, quantityToAdd);

        //then
        verify(dataCartServiceMock).saveCart(givenCartEntity);
        Optional<CartProductEntity> cartProduct = getCartProductEntity(givenCartEntity,
                givenProductEntity);
        assertEquals(givenQuantityForExistingProduct + quantityToAdd,
                cartProduct.get().getQuantity());

    }

    @Test
    void addItemToCart_whenProductDoesNotExist_shouldAddNewProduct() {
        //given data
        UUID givenCartId = UUID.randomUUID();
        UUID givenUserId = UUID.randomUUID();
        Integer givenQuantityForNewProduct = 1;
        CartEntity givenCartEntity = preparedTestCartWithProduct(givenUserId);
        ProductEntity givenNewProductEntity = preparedTestProductEntity_2();
        UUID givenNewProductEntityId = givenNewProductEntity.getTechnicalId();

        //prepare mock response from repository
        when(dataCartServiceMock.getCartByTechnicalId(givenCartId))
                .thenReturn(Optional.of(givenCartEntity));
        when(dataProductServiceMock.getProductByTechnicalId(givenNewProductEntityId))
                .thenReturn(Optional.of(givenNewProductEntity));

        //when
        cartSuT.addProductToCart(givenCartId,
                givenNewProductEntityId,
                givenQuantityForNewProduct);

        //then
        verify(dataCartServiceMock).saveCart(givenCartEntity);
        CartProductEntity newCartProductEntity = getCartProductEntity(givenCartEntity,
                givenNewProductEntity).get();
        assertEquals(givenQuantityForNewProduct, newCartProductEntity.getQuantity());
        assertEquals(2, givenCartEntity.getProducts().size());
    }

    @Test
    void removeItemFromCart_shouldRemoveProductFromCart() {
        //given data
        UUID givenUserId = UUID.randomUUID();
        CartEntity givenCartEntity = preparedTestCartWithProduct(givenUserId);
        UUID givenCartId = givenCartEntity.getTechnicalId();
        CartProductEntity givenCartProductEntity = preparedTestCartProduct(givenCartEntity);
        UUID givenProductEntityId = givenCartProductEntity.getProduct().getTechnicalId();

        //prepare mock response from repository
        when(dataCartServiceMock.getCartByTechnicalId(givenCartId))
                .thenReturn(Optional.of(givenCartEntity));
        //when
        cartSuT.removeProductFromCart(givenCartId, givenProductEntityId);

        //then
        verify(dataCartServiceMock).saveCart(givenCartEntity);
        verify(dataCartServiceMock, times(1)).saveCart(givenCartEntity);
        assertFalse(givenCartEntity.getProducts().contains(givenCartProductEntity));
    }

    @Test
    void updateCartItemQuantity() {
    }

    @Test
    void showCart() {
    }

    private static Optional<CartProductEntity> getCartProductEntity(CartEntity givenCartEntity, ProductEntity givenProductEntity) {
        return givenCartEntity.getProducts()
                .stream().filter(cartProductEntity ->
                        cartProductEntity.getProduct().equals(givenProductEntity)).findFirst();
    }
}