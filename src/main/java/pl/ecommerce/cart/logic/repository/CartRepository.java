package pl.ecommerce.cart.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ecommerce.cart.model.entity.CartEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    @Query("SELECT c FROM CartEntity c WHERE c.technicalId=:technicalId")
    Optional<CartEntity> findByTechnicalId(UUID technicalId);
}
