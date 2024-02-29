package pl.akademiaspecjalistowit.ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.ecommerce.domain.model.OrderEntity;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
