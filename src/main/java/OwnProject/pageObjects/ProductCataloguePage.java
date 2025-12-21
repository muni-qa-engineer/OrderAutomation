package OwnProject.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductCataloguePage {
	
	
	WebDriver driver ;
	public ProductCataloguePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	By productCards = By.cssSelector(".card-body");
	By productNames = By.cssSelector("h5");
	
	public List<WebElement> getProductCards() {
	    return driver.findElements(productCards);
	}

    // Returns all product names
    public List<String> getListOfProductNames() {
        List<String> ProductNames = getProductCards().stream().map(WebElement::getText).collect(Collectors.toList());
        return ProductNames;
    }

    // Returns true/false (NO assertion here)
    public boolean isProductDisplayed(String requiredProductName) {
        return getListOfProductNames().contains(requiredProductName);
    }
    
    public void getRequiredProductName(String requiredProductName) {
    	WebElement requiredProduct = getProductCards().stream()
    			.filter(r->r.getText().equalsIgnoreCase(requiredProductName)).findFirst().orElse(null);
		if (requiredProduct != null) {
		    String foundProduct = requiredProduct.getText();
		    System.out.println(foundProduct);
		    requiredProduct.findElement(By.xpath("./ancestor::div[@class='card-body']//button")).click();
		    System.out.println("\n Muni printing the End of getRequiredProductName");
		}
    }

}
