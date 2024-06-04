package pl.akademiaspecjalistowit.ecommerce.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.util.values.UserRole;
import pl.akademiaspecjalistowit.model.Currency;
import pl.akademiaspecjalistowit.model.Address;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "active_users")
public class ActiveUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technical_id", unique = true, nullable = false)
    private UUID technicalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "address_house_number")),
            @AttributeOverride(name = "apartmentNumber", column = @Column(name = "address_apartment_number"))
    })
    private Address address;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_currency", nullable = false)
    private Currency accountCurrency;

    @Column(name = "account_balance", nullable = false)
    private BigDecimal accountBalance;

    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Setter
    private UserEntity userEntity;

    public ActiveUserEntity(UUID technicalId, String name, String surname,
                            CartEntity cart, String email, String password, Address address,
                            Currency accountCurrency, BigDecimal accountBalance,
                            UserRole userRole, UserEntity userEntity) {
        this.technicalId = technicalId;
        this.name = name;
        this.surname = surname;
        this.cart = cart;
        this.email = email;
        this.password = password;
        this.address = address;
        this.accountCurrency = accountCurrency;
        this.accountBalance = accountBalance;
        this.userRole = userRole;
        this.userEntity = userEntity;
    }

    public void assignCartToUser(CartEntity cart) {
        this.cart = cart;
        if (cart != null && cart.getClient() != this) {
            cart.assignUserToCart(this);
        }
    }
}
