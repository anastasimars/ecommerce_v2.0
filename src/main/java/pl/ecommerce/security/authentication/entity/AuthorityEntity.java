package pl.ecommerce.security.authentication.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "authorities")
public class AuthorityEntity implements GrantedAuthority {
    public AuthorityEntity(String authority) {
        this.authority = authority;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}

