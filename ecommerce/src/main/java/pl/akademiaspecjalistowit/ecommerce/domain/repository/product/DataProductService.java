package pl.akademiaspecjalistowit.ecommerce.domain.repository.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.domain.model.ProductEntity;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DataProductService {
    private final ProductRepository productRepository;
    public Optional<ProductEntity> getItemByTechnicalId(UUID technicalId) {
        return productRepository
                .findByTechnicalId(technicalId);
    }
}
