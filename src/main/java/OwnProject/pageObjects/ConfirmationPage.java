package OwnProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationPage {

    WebDriver driver;
    WebDriverWait wait;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        return confirmationMessage.getText();
    }
}