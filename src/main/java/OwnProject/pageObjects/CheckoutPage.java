package OwnProject.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }
    
    By countryInput = By.xpath("//input[@placeholder='Select Country']");
    By countrySuggestions = By.cssSelector(".ta-item");
    
    @FindBy(css=".action__submit")
    WebElement placeOrderBtn;

    public CheckoutPage selectCountry(String countryName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(countryInput));
        driver.findElement(countryInput).sendKeys(countryName);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(countrySuggestions));
        List<WebElement> countries = driver.findElements(countrySuggestions);
        WebElement selectedCountry = countries.stream().filter(c -> c.getText().equalsIgnoreCase(countryName))
                .findFirst().orElse(null);
        if (selectedCountry == null) {
            throw new RuntimeException("Country not found: " + countryName);
        }
        selectedCountry.click();
        
        return this ;
    }

    public ConfirmationPage placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
        placeOrderBtn.click();
        
        return new ConfirmationPage(driver);
    }
}