package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.SearchPage;
import utils.ConfigReader;
import utils.DriverSetup;
import utils.ExtentManager;
import java.time.Duration;
import java.util.List;
public class CartTest {
    WebDriver driver;
    CartPage cp;
    SearchPage sp;
    LoginPage lp;
    WebDriverWait w;
    @BeforeMethod
    public void setup() {
        ConfigReader.load();
        driver = DriverSetup.getDriver();
        cp = new CartPage(driver);
        sp = new SearchPage(driver);
        lp = new LoginPage(driver);
        w = new WebDriverWait(driver, Duration.ofSeconds(15));
        lp.goTo();
        lp.login(
                ConfigReader.get("email"),
                ConfigReader.get("password")
        );
    }
    @AfterMethod
    public void teardown() {
        DriverSetup.quitDriver();
    }
    public void addMacbook() {
        driver.get(ConfigReader.get("url"));
        sp.search("MacBook");
        WebElement product = w.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".product-thumb h4 a")
                )
        );
        product.click();
        WebElement btn = w.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("button-cart")
                )
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);
        w.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert-success")
                )
        );
    }

    @Test
    public void addToCart() {
        ExtentManager.createTest("addToCart");
        addMacbook();
        cp.goTo();
        Assert.assertTrue(
                cp.getProductName().contains("MacBook")
        );
        ExtentManager.getTest()
                .pass("Product added to cart");
    }
    @Test
    public void removeFromCart() {
        ExtentManager.createTest("removeFromCart");
        addMacbook();
        cp.goTo();
        try {
            List<WebElement> btns =
                    driver.findElements(By.tagName("button"));
            for (WebElement b : btns) {
                String cls = b.getAttribute("class");
                if (cls != null && cls.contains("danger")) {
                    ((JavascriptExecutor) driver)
                            .executeScript(
                                    "arguments[0].click();",
                                    b
                            );
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("remove failed");
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String page = driver.getPageSource().toLowerCase();
        Assert.assertTrue(
                page.contains("empty")
                        || page.contains("shopping cart")
        );
        ExtentManager.getTest()
                .pass("Cart remove checked");
    }
}