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
		openBrowser();
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
//      Login to the page with credentials
        LoginPage loginPage = new LoginPage(driver);
		loginPage.login("muni@gmail.com", "Muni@123");
//		Find the required productName
		ProductCataloguePage productCatalogePage = new ProductCataloguePage(driver);
		productCatalogePage.isProductDisplayed(requiredProductName);
		productCatalogePage.getRequiredProductName(requiredProductName);
		Thread.sleep(3000); //We used this there is an Animation unable to find the webElement, so we gave break for 3sec.
//		Add to Cart
		CartPage cart = new CartPage(driver);
		cart.addProductToCart(requiredProductName);
		cart.buyNow(requiredProductName);
//		CheckOut
		CheckoutPage checkOutCart = new CheckoutPage(driver) ;
		checkOutCart.selectCountry(countryName);
//		 OrderConfirmation
		ConfirmationPage confirm = new ConfirmationPage(driver);
		confirm.getConfirmationMessage();
//		Close all the tabs and quit 
		closeBrowser();
	}

}
