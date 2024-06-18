package pl.akademiaspecjalistowit.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.domain.model.ActiveUserEntity;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;

import java.util.Optional;

@Repository
public interface ActiveUserRepository extends JpaRepository<ActiveUserEntity, Long> {
    Optional<ActiveUserEntity> findByUserEntity(UserEntity user);
}
