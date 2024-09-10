package pl.ecommerce.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.ecommerce.model.Currency;
import pl.ecommerce.util.values.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id", unique = true, nullable = false)
    private UUID technicalId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductEntity> items = new ArrayList<>();

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "order_date_time", nullable = false)
    private LocalDateTime orderDateTime;

    @Column(name = "delivery_date_time", nullable = false)
    private LocalDateTime deliveryDateTime;

    @Column(name = "shipping_method", nullable = false)
    private String shippingMethod;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus status;

    public OrderEntity(UUID technicalId, List<OrderProductEntity> items,
                       BigDecimal totalPrice, Currency currency,
                       LocalDateTime orderDateTime, LocalDateTime deliveryDateTime,
                       String shippingMethod, OrderStatus status) {
        this.technicalId = technicalId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.currency = currency;
        this.orderDateTime = orderDateTime;
        this.deliveryDateTime = deliveryDateTime;
        this.shippingMethod = shippingMethod;
        this.status = status;
    }
}
