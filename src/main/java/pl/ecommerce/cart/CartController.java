package pl.ecommerce.cart;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ecommerce.cart.logic.service.CartService;
import pl.ecommerce.cart.model.dto.AddToCartRequest;
import pl.ecommerce.cart.model.dto.CartProductResponse;
import pl.ecommerce.cart.model.dto.UpdateQuantityRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
class CartController {
    private final CartService cartService;

    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<String> addToCart(
            @PathVariable UUID cartId,
            @PathVariable UUID productId,
            @RequestBody AddToCartRequest request) {
        if (request.quantity() <= 0) {
            return ResponseEntity.badRequest().body("Quantity must be greater than 0");
        }
        cartService.addProductToCart(cartId, productId, request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully added to cart");
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @PathVariable UUID cartId,
            @PathVariable UUID productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Void> updateCartItemQuantity(
            @PathVariable UUID cartId,
            @PathVariable UUID productId,
            @RequestBody UpdateQuantityRequest request) {
        cartService.updateCartProductQuantity(cartId, productId, request.quantity());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartProductResponse>> showCart(@PathVariable UUID cartId) {
        List<CartProductResponse> cartProductResponses = cartService.showCart(cartId);
        return ResponseEntity.ok(cartProductResponses);
    }
}
