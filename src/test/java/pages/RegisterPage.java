package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    @FindBy(id = "input-firstname")
    WebElement fname;

    @FindBy(id = "input-lastname")
    WebElement lname;

    @FindBy(id = "input-email")
    WebElement email;

    @FindBy(id = "input-telephone")
    WebElement phone;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(id = "input-confirm")
    WebElement confirmPass;

    @FindBy(name = "agree")
    WebElement agreeCheck;

    @FindBy(css = "input[value='Continue']")
    WebElement continueBtn;

    @FindBy(css = ".alert-danger")
    WebElement errorMsg;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get(utils.ConfigReader.get("url") + "/index.php?route=account/register");
    }

    public void register(String fn, String ln, String em, String ph, String pass) {
        type(fname, fn);
        type(lname, ln);
        type(email, em);
        type(phone, ph);
        type(password, pass);
        type(confirmPass, pass);
        click(agreeCheck);
        click(continueBtn);
    }

    public String getError() {
        return getText(errorMsg);
    }

}