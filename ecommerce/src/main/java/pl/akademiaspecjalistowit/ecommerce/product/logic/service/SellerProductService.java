package pl.akademiaspecjalistowit.ecommerce.product.logic.service;

import pl.akademiaspecjalistowit.model.NewProductRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface SellerProductService {
    void addNewProduct(NewProductRequest newProductRequest);
    void deleteProduct(UUID productId);
    void updateProductPrice(UUID productId, BigDecimal price);
    void updateProductQuantity(UUID productId, Integer quantity);
}
