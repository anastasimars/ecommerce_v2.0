package pl.akademiaspecjalistowit.ecommerce.domain.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
