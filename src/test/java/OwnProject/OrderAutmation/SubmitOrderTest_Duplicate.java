package OwnProject.OrderAutmation;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class SubmitOrderTest_Duplicate {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String userMail = "muni@gmail.com";
		String userPassword = "Muni@123";
		String requiredProductName = "ADIDAS ORIGINAL" ; 
		String countryName = "india" ;
		String orderConfirmationExpected = "Thankyou for the order." ;
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
//		Scroll up and down
		JavascriptExecutor js = (JavascriptExecutor)driver ;
		js.executeScript("window.scrollBy(0,1000)");
		js.executeScript("window.scrollBy(0,0)");
		
//		Login to the page with credentials
		driver.findElement(By.id("userEmail")).sendKeys(userMail);
		driver.findElement(By.id("userPassword")).sendKeys(userPassword);
		driver.findElement(By.id("login")).click();
		
//		Find the required productName
		List<WebElement> FindProductName = driver.findElements(By.cssSelector(".card-body h5"));
		List<String> Productnames = FindProductName.stream().map(p->p.getText()).collect(Collectors.toList());
		if(Productnames.contains(requiredProductName)) {
			System.out.println("Hey, Your Item is there in shop! \n proceed with shopping...");
		}
		else {
			System.out.println("Sorry! Your Item is not there in shop! \n Will notify you soon...");
		}
//		Select product
		SoftAssert softAssert = new SoftAssert();
		
		WebElement requiredProduct = FindProductName.stream().filter(r->r.getText().equalsIgnoreCase(requiredProductName)).findFirst().orElse(null);
		if (requiredProduct != null) {
		    String foundProduct = requiredProduct.getText();
		    softAssert.assertEquals(foundProduct, requiredProductName);
		    System.out.println("Your selected Product is " + foundProduct);
		    requiredProduct.findElement(By.xpath("./ancestor::div[@class='card-body']//button")).click();
		    softAssert.assertAll();
		    
		}
		Thread.sleep(3000); //We used this there is an Animation unable to find the webElement, so we gave break for 3sec.
		
//		Add to cart
		WebElement image = driver.findElement(By.cssSelector(".img-fluid"));
		File src = image.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "/screenshots/productImg.png"));
		driver.findElement(By.cssSelector(".btn-primary")).click();
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
//		Buy the product from cart
		List<WebElement> productInCart = driver.findElements(By.cssSelector(".items h3"));
		WebElement buyProduct = productInCart.stream()
		        .filter(c -> c.getText().equalsIgnoreCase(requiredProductName)).findFirst().orElse(null);
		if (buyProduct != null) {
		    buyProduct
		        .findElement(By.xpath("//button[.='Buy Now']")).click();
		} else {
		    System.out.println("Product not found in cart");
		}
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		 driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(countryName);
//		 .ta-item
		
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		 
		 List<WebElement> selectCountry = driver.findElements(By.cssSelector(".ta-item"));
		 WebElement country = selectCountry.stream().filter(cntry->cntry.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		 country.click();
		 
//		 Place order
		 driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		 WebElement orderConfirmationByImg = driver.findElement(By.cssSelector(".hero-primary"));
		 File src1 = orderConfirmationByImg.getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(src1, new File(System.getProperty("user.dir")+"/screenshots/OrderConfirmation.png"));
		 String orderConfirmationByText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		 softAssert.assertEquals(orderConfirmationByText, orderConfirmationExpected);
		 
//		Close all the tabs and quit 
		driver.quit();
	}

}
