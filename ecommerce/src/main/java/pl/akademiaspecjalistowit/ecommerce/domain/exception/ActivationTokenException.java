package pl.akademiaspecjalistowit.ecommerce.domain.exception;

public class ActivationTokenException extends IllegalArgumentException{
    public ActivationTokenException(String message) {
        super(message);
    }
}
