package OwnProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import AbstractComponent.ReusableUtilities;

public class ProductCataloguePage extends ReusableUtilities{
	
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
	}
	
    public CartPage getRequiredProductName(String requiredProductName) throws InterruptedException {
    	List<WebElement> FindProductName = driver.findElements(By.cssSelector(".card-body h5"));
    	WebElement requiredProduct = FindProductName.stream().filter(r->r.getText()
    			.equalsIgnoreCase(requiredProductName)).findFirst().orElse(null);
		if (requiredProduct != null) {
		    System.out.println("Your selected Product is " + requiredProduct.getText());
		    requiredProduct.findElement(By.xpath("./ancestor::div[@class='card-body']//button")).click();
		}
		return new CartPage(driver);
		
    }
}
