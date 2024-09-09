package pl.akademiaspecjalistowit.ecommerce.activeuser.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.activeuser.model.ActiveUserEntity;
@Repository
public interface ActiveUserRepository extends JpaRepository<ActiveUserEntity, Long> {
}
