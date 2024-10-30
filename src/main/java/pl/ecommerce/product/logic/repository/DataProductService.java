package pl.ecommerce.product.logic.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ecommerce.exception.ItemNotFoundException;
import pl.ecommerce.product.model.entity.ProductEntity;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DataProductService {
    private final ProductRepository productRepository;

    public ProductEntity getProductByTechnicalId(UUID technicalId) {
        return productRepository
                .findByTechnicalId(technicalId).orElseThrow(() ->
                        new ItemNotFoundException(String.format("Item with id %s not found",
                                technicalId)));
    }
}
