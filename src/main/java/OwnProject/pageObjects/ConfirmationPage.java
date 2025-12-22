package OwnProject.pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ConfirmationPage {

    WebDriver driver;
    WebDriverWait wait;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".hero-primary")
    WebElement orderConfirmationByImg;

    public String getConfirmationMessage() throws IOException {
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		 File src1 = orderConfirmationByImg.getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(src1, new File(System.getProperty("user.dir")+"/screenshots/OrderConfirmation.png"));
		 return orderConfirmationByImg.getText();
    }
}