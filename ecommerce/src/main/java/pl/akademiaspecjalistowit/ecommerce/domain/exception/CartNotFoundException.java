package pl.akademiaspecjalistowit.ecommerce.domain.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message) {
        super(message);
    }
}
