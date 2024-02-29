package pl.akademiaspecjalistowit.ecommerce.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.akademiaspecjalistowit.model.Currency;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id", unique = true, nullable = false)
    private UUID technicalId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "stock_state")
    private Integer stockState;

    public ProductEntity(UUID technicalId, String name, String description,
                         String category, BigDecimal price,
                         Currency currency, Integer stockState) {
        this.technicalId = technicalId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.currency = currency;
        this.stockState = stockState;
    }
}
