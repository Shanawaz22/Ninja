package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegisterPage;
import utils.ConfigReader;
import utils.DriverSetup;
import utils.ExtentManager;

public class RegisterTest {

    WebDriver driver;
    RegisterPage rp;

    @BeforeMethod
    public void setup() {
        ConfigReader.load();
        driver = DriverSetup.getDriver();
        rp = new RegisterPage(driver);
    }

    @AfterMethod
    public void teardown() {
        DriverSetup.quitDriver();
    }

    @Test
    public void validRegister() {
        ExtentManager.createTest("validRegister");
        rp.goTo();
        String em = "user" + System.currentTimeMillis() + "@gmail.com";
        rp.register("Test", "User", em, "9876543210", "Test@1234");
        Assert.assertTrue(driver.getCurrentUrl().contains("account"));
        ExtentManager.getTest().pass("Registration successful");
    }

    @Test
    public void emptyRegister() {
        ExtentManager.createTest("emptyRegister");
        rp.goTo();
        rp.submitEmpty();
        Assert.assertTrue(driver.getCurrentUrl().contains("register"));
        ExtentManager.getTest().pass("Stayed on register page with errors");
    }

}