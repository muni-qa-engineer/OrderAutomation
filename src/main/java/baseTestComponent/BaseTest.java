package baseTestComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
	
    protected WebDriver driver;
    
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}