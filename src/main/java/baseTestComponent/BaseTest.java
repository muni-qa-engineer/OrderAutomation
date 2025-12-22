package baseTestComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	
    protected WebDriver driver;
    
    @BeforeTest
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }
    
   @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}