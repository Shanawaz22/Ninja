package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    @FindBy(css = "#cart-total")
    WebElement cartBtn;

    @FindBy(css = "button[title='Remove']")
    WebElement removeBtn;

    @FindBy(css = "input.form-control")
    WebElement qtyInput;

    @FindBy(css = "button[title='Update']")
    WebElement updateBtn;

    @FindBy(css = ".text-center p")
    WebElement emptyMsg;

    @FindBy(css = ".cart-info table tbody tr td:nth-child(2)")
    WebElement productName;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get(utils.ConfigReader.get("url") + "/index.php?route=checkout/cart");
    }

    public void updateQty(String qty) {
        type(qtyInput, qty);
        click(updateBtn);
    }

    public void removeProduct() {
        click(removeBtn);
    }

    public String getEmptyMsg() {
        return getText(emptyMsg);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getCartTotal() {
        return getText(cartBtn);
    }

}