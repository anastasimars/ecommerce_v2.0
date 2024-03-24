package pl.akademiaspecjalistowit.ecommerce.domain.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message) {
        super(message);
    }
}
