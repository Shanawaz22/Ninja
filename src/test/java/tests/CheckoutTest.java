package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CheckoutPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverSetup;
import utils.ExtentManager;

public class CheckoutTest {

    WebDriver driver;
    CheckoutPage chp;
    LoginPage lp;

    @BeforeMethod
    public void setup() {
        ConfigReader.load();
        driver = DriverSetup.getDriver();
        chp = new CheckoutPage(driver);
        lp = new LoginPage(driver);
    }

    @AfterMethod
    public void teardown() {
        DriverSetup.quitDriver();
    }

    @Test
    public void checkoutWithoutLogin() {
        ExtentManager.createTest("checkoutWithoutLogin");
        chp.goTo();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("login") || url.contains("checkout"));
        ExtentManager.getTest().pass("Redirected correctly");
    }

    @Test
    public void checkoutWithLogin() {
        ExtentManager.createTest("checkoutWithLogin");
        lp.goTo();
        lp.login(ConfigReader.get("email"), ConfigReader.get("password"));
        chp.goTo();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
        ExtentManager.getTest().pass("Checkout page loaded");
    }

}