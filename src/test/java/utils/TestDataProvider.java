package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "orderData")
    public Object[][] getOrderData() {
        return new Object[][] {
            {"muni@gmail.com", "Muni@123", "ADIDAS ORIGINAL", "india"},
        };
    }
}