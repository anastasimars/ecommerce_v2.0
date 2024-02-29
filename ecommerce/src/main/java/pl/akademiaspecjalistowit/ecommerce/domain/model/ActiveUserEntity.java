package pl.akademiaspecjalistowit.ecommerce.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.util.values.UserRole;
import pl.akademiaspecjalistowit.model.Currency;

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
                            CartEntity cart, String email, String password,
                            Currency accountCurrency, BigDecimal accountBalance,
                            UserRole userRole, UserEntity userEntity) {
        this.technicalId = technicalId;
        this.name = name;
        this.surname = surname;
        this.cart = cart;
        this.email = email;
        this.password = password;
        this.accountCurrency = accountCurrency;
        this.accountBalance = accountBalance;
        this.userRole = userRole;
        this.userEntity = userEntity;
    }
}
