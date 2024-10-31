package pl.ecommerce.cart.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id", unique = true, nullable = false)
    private UUID technicalId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProductEntity> cartProducts = new ArrayList<>();

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    public CartEntity(UUID technicalId, List<CartProductEntity> cartProducts,
                      UUID userId) {
        this.technicalId = technicalId;
        this.cartProducts = cartProducts;
        this.userId = userId;
    }

    public void removeProduct(UUID technicalId) {
        cartProducts.removeIf(cartProductEntity -> cartProductEntity.getProduct()
                .getTechnicalId().equals(technicalId));
    }


    public void assignUserToCart(UUID userId) {
        this.userId = userId;
    }
}
