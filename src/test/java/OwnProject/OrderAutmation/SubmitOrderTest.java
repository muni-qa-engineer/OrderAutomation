package OwnProject.OrderAutmation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import OwnProject.pageObjects.CartPage;
import OwnProject.pageObjects.CheckoutPage;
import OwnProject.pageObjects.LoginPage;
import OwnProject.pageObjects.ConfirmationPage;
import OwnProject.pageObjects.ProductCataloguePage;
import baseTestComponent.BaseTest;
import utils.TestDataProvider;

public class SubmitOrderTest extends BaseTest {
	
	@Test(dataProvider = "orderData", dataProviderClass = TestDataProvider.class)
    public void submitOrderTest(
    		String email,
            String password,
            String requiredProductName,
            String countryName) 
            		throws InterruptedException, IOException {
		
//      Login to the page with credentials
        LoginPage loginPage = new LoginPage(driver);
        
//		Find the required productName
		ProductCataloguePage productCatalogePage = loginPage.login("muni@gmail.com", "Muni@123");
//		Add to Cart
		CartPage cart = productCatalogePage.getRequiredProductName(requiredProductName);
		
//		CheckOut
		CheckoutPage checkOutCart = cart.addProductToCart(requiredProductName);
		
//		 OrderConfirmation
		ConfirmationPage confirm = checkOutCart.selectCountry(countryName)
				.placeOrder();
		
//		Place order
		confirm.getConfirmationMessage();
		
//		 Assert confirmation
        Assert.assertTrue(
        		confirm.getConfirmationMessage()
                        .equalsIgnoreCase("THANKYOU FOR THE ORDER.")
        );

//		Close all the tabs and quit 
		
	}

}
