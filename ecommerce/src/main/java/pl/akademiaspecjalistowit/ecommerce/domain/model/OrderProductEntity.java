package pl.akademiaspecjalistowit.ecommerce.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_products")
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id", unique = true, nullable = false)
    private UUID technicalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ProductEntity item;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public OrderProductEntity(UUID technicalId,
                              OrderEntity order,
                              ProductEntity item,
                              Integer quantity) {
        this.technicalId = technicalId;
        this.order = order;
        this.item = item;
        this.quantity = quantity;
    }
}
