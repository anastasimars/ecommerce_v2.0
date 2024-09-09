package pl.akademiaspecjalistowit.ecommerce.product.logic.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.product.model.ProductEntity;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DataProductService {
    private final ProductRepository productRepository;
    public Optional<ProductEntity> getProductByTechnicalId(UUID technicalId) {
        return productRepository
                .findByTechnicalId(technicalId);
    }
}
