package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverSetup;
import utils.ExtentManager;

public class LoginTest {

    WebDriver driver;
    LoginPage lp;

    @BeforeMethod
    public void setup() {
        ConfigReader.load();
        driver = DriverSetup.getDriver();
        lp = new LoginPage(driver);
    }

    @AfterMethod
    public void teardown() {
        DriverSetup.quitDriver();
    }

    @Test
    public void validLogin() {
        ExtentManager.createTest("validLogin");
        lp.goTo();
        lp.login(ConfigReader.get("email"), ConfigReader.get("password"));
        Assert.assertTrue(driver.getCurrentUrl().contains("account"));
        ExtentManager.getTest().pass("Login successful");
    }

    @Test
    public void invalidLogin() {
        ExtentManager.createTest("invalidLogin");
        lp.goTo();
        lp.login("wrong@gmail.com", "wrongpass");
        Assert.assertTrue(lp.getError().contains("Warning"));
        ExtentManager.getTest().pass("Error message shown");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                { ConfigReader.get("email"), ConfigReader.get("password"), true },
                { "bad@gmail.com", "badpass", false }
        };
    }

    @Test(dataProvider = "loginData")
    public void loginWithData(String em, String pass, boolean expected) {
        ExtentManager.createTest("loginWithData");
        lp.goTo();
        lp.login(em, pass);
        if (expected) {
            Assert.assertTrue(driver.getCurrentUrl().contains("account"));
        } else {
            Assert.assertTrue(lp.getError().contains("Warning"));
        }
    }

}