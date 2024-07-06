package pl.akademiaspecjalistowit.ecommerce.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity implements UserDetails, OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<AuthorityEntity> authorities = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private String username;

    private String password;

    @Transient
    @Setter
    private Map<String, Object> attributes;

 //for creating a user with basic fields
    public UserEntity(Set<AuthorityEntity> authorities,
                      String email, String password, String username) {
        this.authorities = authorities;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    // for OAuth2 authentication
    public UserEntity(Set<AuthorityEntity> authorities, String username, String password, Map<String, Object> attributes) {
        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username != null ? username : email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return username;
    }
}
