package pl.akademiaspecjalistowit.ecommerce.api.controller;

import org.springframework.http.ResponseEntity;
import pl.akademiaspecjalistowit.api.ProductsApi;
import pl.akademiaspecjalistowit.model.GetAvailableProducts200Response;
import pl.akademiaspecjalistowit.model.NewProductRequest;
import pl.akademiaspecjalistowit.model.UpdateProductDetailsRequest;

import java.math.BigDecimal;
import java.util.UUID;

class ProductController implements ProductsApi {
    @Override
    public ResponseEntity<GetAvailableProducts200Response> getAvailableProducts(String category, BigDecimal minPrice, BigDecimal maxPrice, Integer page, Integer size) {
        return ProductsApi.super.getAvailableProducts(category, minPrice, maxPrice, page, size);
    }

    @Override
    public ResponseEntity<Void> addNewProduct(NewProductRequest newProductRequest) {
        return ProductsApi.super.addNewProduct(newProductRequest);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(UUID productId) {
        return ProductsApi.super.deleteProduct(productId);
    }

    @Override
    public ResponseEntity<Void> updateProductDetails(UUID productId, UpdateProductDetailsRequest updateProductDetailsRequest) {
        return ProductsApi.super.updateProductDetails(productId, updateProductDetailsRequest);
    }
}
