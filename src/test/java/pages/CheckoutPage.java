package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    @FindBy(css = "input[name='firstname']")
    WebElement fname;

    @FindBy(css = "input[name='lastname']")
    WebElement lname;

    @FindBy(css = "input[name='address_1']")
    WebElement address;

    @FindBy(css = "input[name='city']")
    WebElement city;

    @FindBy(css = "input[name='postcode']")
    WebElement postcode;

    @FindBy(css = "select[name='country_id']")
    WebElement country;

    @FindBy(css = "#button-guest")
    WebElement continueBtn;

    @FindBy(css = "#collapse-checkout-confirm .btn-primary")
    WebElement confirmBtn;

    @FindBy(css = "#content h1")
    WebElement successMsg;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get(utils.ConfigReader.get("url") + "/index.php?route=checkout/checkout");
    }

    public String getSuccessMsg() {
        return getText(successMsg);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}