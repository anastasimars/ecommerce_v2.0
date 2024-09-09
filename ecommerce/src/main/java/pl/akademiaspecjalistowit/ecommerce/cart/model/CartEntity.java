package pl.akademiaspecjalistowit.ecommerce.cart.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.exception.OutOfStockException;
import pl.akademiaspecjalistowit.ecommerce.activeuser.model.ActiveUserEntity;
import pl.akademiaspecjalistowit.ecommerce.product.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id", unique = true, nullable = false)
    private UUID technicalId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProductEntity> products = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    private ActiveUserEntity client;

    public CartEntity(UUID technicalId, List<CartProductEntity> products,
                      ActiveUserEntity client) {
        this.technicalId = technicalId;
        this.products = products;
        this.client = client;
    }

    public void addProduct(ProductEntity product, Integer quantity) {
        Optional<CartProductEntity> existProduct = findProductInCart(product);
        Integer productStockAmount = product.getStockState();

        if (existProduct.isPresent()) {
            CartProductEntity existCartProduct = existProduct.get();
            updateCartItemQuantity(existCartProduct, quantity, productStockAmount);
        } else {
            addNewProductToCart(product, quantity, productStockAmount);
        }
    }

    public void removeProduct(UUID technicalId) {
        products.removeIf(cartProductEntity -> cartProductEntity.getProduct()
                .getTechnicalId().equals(technicalId));
    }

    private void updateCartItemQuantity(CartProductEntity cartProduct,
                                        Integer quantity,
                                        Integer productStockAmount) {
        Integer newQuantity = cartProduct.getQuantity() + quantity;
        if (productStockAmount >= newQuantity) {
            cartProduct.updateQuantity(newQuantity);
        } else {
            throw new OutOfStockException("Insufficient stock for item: "
                    + cartProduct.getProduct().getName());
        }
    }

    private void addNewProductToCart(ProductEntity product,
                                     Integer quantity,
                                     Integer productStockAmount) {
        if (productStockAmount >= quantity) {
            CartProductEntity cartProduct =
                    new CartProductEntity(this, product, quantity);
            products.add(cartProduct);
        } else {
            throw new OutOfStockException("Insufficient stock for item: "
                    + product.getName());
        }
    }

    private Optional<CartProductEntity> findProductInCart(ProductEntity product) {
        return products.stream()
                .filter(cartProductEntity ->
                        cartProductEntity.getProduct().equals(product)).findFirst();
    }

    public void assignUserToCart(ActiveUserEntity client) {
        this.client = client;
        if (client != null && client.getCart() != this) {
            client.assignCartToUser(this);
        }
    }
}
