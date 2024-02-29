package pl.akademiaspecjalistowit.ecommerce.domain.exception;

public class OutOfStockException extends IllegalArgumentException {
    public OutOfStockException(String s) {
        super(s);
    }
}
