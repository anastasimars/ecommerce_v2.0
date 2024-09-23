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
    BigDecimal PREPARED_PRICE = new BigDecimal("19.99");
    Currency PREPARED_CURRENCY = Currency.PLN;
    Integer PREPARED_QUANTITY = 1;
    Integer PREPARED_STOCK_STATE = 10;
}
