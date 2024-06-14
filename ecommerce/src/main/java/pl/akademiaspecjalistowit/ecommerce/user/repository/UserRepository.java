package pl.akademiaspecjalistowit.ecommerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}
