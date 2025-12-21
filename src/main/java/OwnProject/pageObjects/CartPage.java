package OwnProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Locators
    By productCards = By.cssSelector(".card-body");
    By productName = By.cssSelector("h5");
    By addToCartBtn = By.cssSelector("button");
    By cartBtn = By.xpath("//button[@routerlink='/dashboard/cart']");
    By cartProducts = By.cssSelector(".items h3");

    public void addProductToCart(String requiredProductName) {
        List<WebElement> products = driver.findElements(productCards);
        WebElement requiredProduct = products.stream().filter(p -> p.findElement(productName).getText()
                        .equalsIgnoreCase(requiredProductName)).findFirst().orElse(null);
        if (requiredProduct == null) {
            throw new RuntimeException("Product not found: " + requiredProductName);
        }
        requiredProduct.findElement(addToCartBtn).click();
        System.out.println("\n Muni printing the End of addProductToCart");
    }

    public CheckoutPage buyNow(String requiredProductName) {
        driver.findElement(cartBtn).click();
        List<WebElement> products = driver.findElements(cartProducts);
        WebElement product = products.stream().filter(p -> p.getText().equalsIgnoreCase(requiredProductName))
                .findFirst().orElse(null);
        System.out.println(product.getText());
        if (product != null) {
            product.findElement(By.xpath(".//button[.='Buy Now']")).click();
            System.out.println("\n Muni printing the End of buynowclick");
        }
        System.out.println("\n Muni printing the End of buyNow");
        return new CheckoutPage(driver);
    }
}