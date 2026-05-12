package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage extends BasePage {
    @FindBy(id = "input-email")
    WebElement emailField;
    @FindBy(id = "input-password")
    WebElement passwordField;
    @FindBy(css = "input[value='Login']")
    WebElement loginBtn;
    @FindBy(css = ".alert-danger")
    WebElement errorMsg;
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void goTo() {
        driver.get(utils.ConfigReader.get("url") + "/index.php?route=account/login");
    }
    public void login(String email, String pass) {
        type(emailField, email);
        type(passwordField, pass);
        click(loginBtn);
    }
    public String getError() {
        return getText(errorMsg);
    }

}