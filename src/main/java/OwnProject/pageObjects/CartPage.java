package OwnProject.pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.ReusableUtilities;

public class CartPage extends ReusableUtilities{

	public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    // Locators
    
    @FindBy(css=".btn-primary")
    WebElement addCartButton ;
    
    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement CartIcon ;
    
    @FindBy(css=".items h3")
    List<WebElement> productInCart ;
    

    public CheckoutPage addProductToCart(String requiredProductName) throws InterruptedException, IOException {
    	
    	Thread.sleep(3000);
    	WebElement image = driver.findElement(By.xpath("//img[@class='img-fluid']"));
		File src = image.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "/screenshots/productImg.png"));
		addCartButton.click();
		CartIcon.click();
		WebElement buyProduct = productInCart.stream()
		        .filter(c -> c.getText().equalsIgnoreCase(requiredProductName)).findFirst().orElse(null);
		if (buyProduct != null) {
		    buyProduct
		        .findElement(By.xpath("//button[.='Buy Now']")).click();
		} else {
		    System.out.println("Product not found in cart");
		}
		
		return new CheckoutPage(driver);
    	
    }

}