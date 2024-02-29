package pl.akademiaspecjalistowit.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.domain.model.ActiveUserEntity;
@Repository
public interface ActiveUserRepository extends JpaRepository<ActiveUserEntity, Long> {
}
