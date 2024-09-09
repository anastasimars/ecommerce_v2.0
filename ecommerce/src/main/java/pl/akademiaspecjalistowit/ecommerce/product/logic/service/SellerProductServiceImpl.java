package pl.akademiaspecjalistowit.ecommerce.product.logic.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.model.NewProductRequest;

import java.math.BigDecimal;
import java.util.UUID;
@Service
@AllArgsConstructor
class SellerProductServiceImpl implements SellerProductService {

    @Override
    public void addNewProduct(NewProductRequest newProductRequest) {
        //todo: implement logic here
    }

    @Override
    public void deleteProduct(UUID productId) {
        //todo: implement logic here
    }

    @Override
    public void updateProductPrice(UUID productId, BigDecimal price) {
        //todo: implement logic here
    }

    @Override
    public void updateProductQuantity(UUID productId, Integer quantity) {
        //todo: implement logic here
    }
}
