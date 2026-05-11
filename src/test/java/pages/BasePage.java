package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        int t = Integer.parseInt(ConfigReader.get("timeout"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(t));
    }

    public void waitFor(WebElement e) {
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(WebElement e) {
        waitFor(e);
        e.click();
    }

    public void type(WebElement e, String txt) {
        waitFor(e);
        e.clear();
        e.sendKeys(txt);
    }

    public String getText(WebElement e) {
        waitFor(e);
        return e.getText();
    }

}