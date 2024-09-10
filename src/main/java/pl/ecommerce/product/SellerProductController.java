package pl.ecommerce.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.api.SellerApi;
import pl.ecommerce.model.NewProductRequest;
import pl.ecommerce.model.UpdateProductDetailsRequest;
import pl.ecommerce.product.logic.service.SellerProductService;

import java.math.BigDecimal;
import java.util.UUID;
@RestController
@AllArgsConstructor
class SellerProductController implements SellerApi {
    private final SellerProductService sellerProductService;

    @Override
    public ResponseEntity<Void> addNewProduct(NewProductRequest newProductRequest) {
        sellerProductService.addNewProduct(newProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> deleteProduct(UUID productId) {
        sellerProductService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateProductDetails(UUID productId,
                                                     UpdateProductDetailsRequest updateProductDetailsRequest) {
        if (updateProductDetailsRequest.getPrice() != null) {
            BigDecimal price = updateProductDetailsRequest.getPrice();
            sellerProductService.updateProductPrice(productId, price);
        } else if (updateProductDetailsRequest.getQuantity() != null) {
            Integer quantity = updateProductDetailsRequest.getQuantity();
            sellerProductService.updateProductQuantity(productId, quantity);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
