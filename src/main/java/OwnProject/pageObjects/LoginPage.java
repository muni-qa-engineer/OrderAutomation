package OwnProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	String userMail = "muni@gmail.com";
	String userPassword = "Muni@123";
	
//	Login to the page with credentials
	
	@FindBy(id="userEmail")
    WebElement Email;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
    WebElement loginBtn;
	
	public ProductCataloguePage login(String userMail, String userPassword) {
        Email.sendKeys(userMail);
        Password.sendKeys(userPassword);
        loginBtn.click();
        return new ProductCataloguePage(driver);
    }
}
