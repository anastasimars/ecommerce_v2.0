package pl.akademiaspecjalistowit.ecommerce.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.ecommerce.domain.exception.OutOfStockException;

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

    public void addItem(ProductEntity product, Integer quantity) {
        Optional<CartProductEntity> existProduct = findProductInCart(product);
        Integer productStockAmount = product.getStockState();

        if(existProduct.isPresent()){
            CartProductEntity existCartProduct = existProduct.get();
            updateCartItemQuantity(existCartProduct, quantity, productStockAmount);
        } else{
            addNewProductToCart(product, quantity, productStockAmount);
        }
    }

    private void updateCartItemQuantity(CartProductEntity cartProduct,
                                               Integer quantity,
                                               Integer productStockAmount) {
        Integer newQuantity = cartProduct.getQuantity() + quantity;
        if (productStockAmount >= newQuantity){
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
            CartProductEntity cartItem =
                    new CartProductEntity(this, product, quantity);
            products.add(cartItem);
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

    public void removeItem(UUID technicalId) {
        products.removeIf(e -> e.getProduct().getTechnicalId().equals(technicalId));
    }

}