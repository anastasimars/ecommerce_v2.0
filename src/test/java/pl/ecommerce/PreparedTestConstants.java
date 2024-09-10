package pl.ecommerce;



import pl.ecommerce.model.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public interface PreparedTestConstants {
    UUID PREPARED_UUID = UUID.randomUUID();
    String PREPARED_PRODUCT_NAME = "Iphone";
    String PREPARED_PRODUCT_NAME_2 = "Apple";
    String PREPARED_PRODUCT_CATEGORY = "Phones";
    String PREPARED_PRODUCT_CATEGORY_2 = "Fruits";
    String PREPARED_PRODUCT_DESCRIPTION = "Test product description";
    String PREPARED_USER_NAME = "John";
    String PREPARED_USER_SURNAME = "Doe";
    String PREPARED_EMAIL = "doe@gmail.com";
    String PREPARED_PASSWORD = "u70604kvl4";
    BigDecimal PREPARED_PRICE = new BigDecimal("19.99");
    BigDecimal PREPARED_BALANCE = new BigDecimal("909.09");
    Currency PREPARED_CURRENCY = Currency.PLN;
    Integer PREPARED_QUANTITY = 1;
    Integer PREPARED_STOCK_STATE = 10;
}
