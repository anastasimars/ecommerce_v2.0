package pl.akademiaspecjalistowit.ecommerce.catalog.logic.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.product.logic.repository.DataProductService;
import pl.akademiaspecjalistowit.model.GetAvailableProducts200Response;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
class CatalogServiceImpl implements CatalogService {
    private final DataProductService dataProductService;

    @Override
    public GetAvailableProducts200Response getAvailableItems(String category,
                                                             BigDecimal minPrice,
                                                             BigDecimal maxPrice,
                                                             Integer page,
                                                             Integer size) {
        //todo: implement logic here
        return null;
    }
}
