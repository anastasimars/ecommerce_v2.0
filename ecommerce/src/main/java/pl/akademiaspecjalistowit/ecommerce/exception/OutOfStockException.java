package pl.akademiaspecjalistowit.ecommerce.exception;

public class OutOfStockException extends IllegalArgumentException {
    public OutOfStockException(String s) {
        super(s);
    }
}
