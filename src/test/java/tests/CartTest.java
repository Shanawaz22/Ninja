package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.SearchPage;
import utils.ConfigReader;
import utils.DriverSetup;
import utils.ExtentManager;

public class CartTest {

    WebDriver driver;
    CartPage cp;
    SearchPage sp;
    LoginPage lp;

    @BeforeMethod
    public void setup() {
        ConfigReader.load();
        driver = DriverSetup.getDriver();
        cp = new CartPage(driver);
        sp = new SearchPage(driver);
        lp = new LoginPage(driver);
        lp.goTo();
        lp.login(ConfigReader.get("email"), ConfigReader.get("password"));
    }

    @AfterMethod
    public void teardown() {
        DriverSetup.quitDriver();
    }

    @Test
    public void addToCart() {
        ExtentManager.createTest("addToCart");
        driver.get(ConfigReader.get("url"));
        sp.search("MacBook");
        sp.openFirstProduct();
        driver.findElement(org.openqa.selenium.By.id("button-cart")).click();
        cp.goTo();
        Assert.assertTrue(cp.getProductName().contains("MacBook"));
        ExtentManager.getTest().pass("Product added to cart");
    }

    @Test
    public void removeFromCart() {
        ExtentManager.createTest("removeFromCart");
        cp.goTo();
        cp.removeProduct();
        Assert.assertTrue(cp.getEmptyMsg().contains("empty"));
        ExtentManager.getTest().pass("Cart is empty after removal");
    }

}