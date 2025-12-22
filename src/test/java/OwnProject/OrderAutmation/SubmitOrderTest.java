package OwnProject.OrderAutmation;

import java.io.IOException;
import org.testng.annotations.Test;

import OwnProject.pageObjects.CartPage;
import OwnProject.pageObjects.CheckoutPage;
import OwnProject.pageObjects.LoginPage;
import OwnProject.pageObjects.ConfirmationPage;
import OwnProject.pageObjects.ProductCataloguePage;
import baseTestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String requiredProductName = "ADIDAS ORIGINAL" ; 
	String countryName = "india" ;
	String orderConfirmationExpected = "Thankyou for the order." ;
	@Test
    public void submitOrderTest() throws InterruptedException, IOException {
		
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
		
//		Close all the tabs and quit 
		closeBrowser();
	}

}
