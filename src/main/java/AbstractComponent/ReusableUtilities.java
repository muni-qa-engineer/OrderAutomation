package AbstractComponent;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableUtilities {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor receives driver
    public ReusableUtilities(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Wait for element to be visible
    public void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    // Wait for element to be visibleby
    public void waitForElementByToAppear(By locator) {
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    // Wait for element to be clickable
    public void waitForElementToBeClickable(WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for element to disappear
    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Screenshot utility
    public String takeElementScreenshot(WebElement element, String fileName) throws IOException {
        File src = element.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + fileName + ".png";
        FileUtils.copyFile(src, new File(path));
        return path;
    }

    // Scroll to element
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}