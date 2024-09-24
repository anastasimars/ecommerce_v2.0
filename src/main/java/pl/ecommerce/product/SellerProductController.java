package pl.ecommerce.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.product.logic.service.SellerProductService;
import pl.ecommerce.product.model.dto.NewProductRequest;
import pl.ecommerce.product.model.dto.UpdateProductDetailsRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
class SellerProductController {
    private final SellerProductService sellerProductService;

    @PostMapping("/seller/products")
    public ResponseEntity<Void> addNewProduct(@RequestBody NewProductRequest request) {
        sellerProductService.addNewProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/seller/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        sellerProductService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/seller/products/{productId}")
    public ResponseEntity<Void> updateProductDetails(@PathVariable UUID productId,
                                                     @RequestBody UpdateProductDetailsRequest request) {
        if (request.price() != null && request.quantity() != null) {
            sellerProductService.updateProductPrice(productId, request.price());
            sellerProductService.updateProductQuantity(productId, request.quantity());
        } else if (request.price() != null) {
            sellerProductService.updateProductPrice(productId,request.price());
        } else if (request.quantity() != null) {
            sellerProductService.updateProductQuantity(productId, request.quantity());
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
