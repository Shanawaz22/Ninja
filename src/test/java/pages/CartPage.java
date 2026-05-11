package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    @FindBy(css = "#cart-total")
    WebElement cartBtn;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get(utils.ConfigReader.get("url") + "/index.php?route=checkout/cart");
    }

    public void updateQty(String qty) {
        WebElement q = driver.findElement(By.cssSelector("input.form-control"));
        type(q, qty);
        WebElement u = driver.findElement(By.cssSelector(".input-group-btn .btn-primary"));
        click(u);
    }

    public void removeProduct() {
        WebElement r = driver.findElement(By.xpath("//button[contains(@onclick,'cart.remove')]"));
        click(r);
    }

    public String getEmptyMsg() {
        WebElement e = driver.findElement(By.cssSelector("#content p"));
        return getText(e);
    }

    public String getProductName() {
        WebElement e = driver.findElement(By.cssSelector(".table-responsive td:nth-child(2) a"));
        return getText(e);
    }

    public String getCartTotal() {
        return getText(cartBtn);
    }

}