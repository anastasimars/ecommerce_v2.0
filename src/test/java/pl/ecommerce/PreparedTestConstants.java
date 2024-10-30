package pl.ecommerce;



import pl.ecommerce.util.values.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public interface PreparedTestConstants {
    UUID PREPARED_CART_UUID = UUID.randomUUID();
    UUID PREPARED_PRODUCT1_UUID = UUID.randomUUID();
    UUID PREPARED_PRODUCT2_UUID = UUID.randomUUID();
    UUID PREPARED_USER_UUID = UUID.randomUUID();
    String PREPARED_PRODUCT_NAME = "Iphone";
    String PREPARED_PRODUCT_NAME_2 = "Apple";
    String PREPARED_PRODUCT_CATEGORY = "Phones";
    String PREPARED_PRODUCT_CATEGORY_2 = "Fruits";
    String PREPARED_PRODUCT_DESCRIPTION = "Test product description";
    BigDecimal PREPARED_PRICE = new BigDecimal("19.99");
    Currency PREPARED_CURRENCY = Currency.PLN;
    Integer PREPARED_QUANTITY = 1;
    Integer PREPARED_QUANTITY_TO_ADD = 1;
    Integer EXPECTED_QUANTITY_OF_ALL_TYPES_PRODUCTS = 2;
    Integer EXPECTED_QUANTITY_FOR_CART_PRODUCT_ENTITY = 2;
    Integer EXPECTED_QUANTITY_FOR_ADD_ONE_PRODUCT_TO_EMPTY_CART = 1;
    Integer PREPARED_STOCK_STATE = 10;
}
