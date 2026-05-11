package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SearchPage;
import utils.ConfigReader;
import utils.DriverSetup;
import utils.ExtentManager;

public class SearchTest {

    WebDriver driver;
    SearchPage sp;

    @BeforeMethod
    public void setup() {
        ConfigReader.load();
        driver = DriverSetup.getDriver();
        sp = new SearchPage(driver);
        driver.get(ConfigReader.get("url"));
    }

    @AfterMethod
    public void teardown() {
        DriverSetup.quitDriver();
    }

    @Test
    public void searchValid() {
        ExtentManager.createTest("searchValid");
        sp.search("MacBook");
        Assert.assertTrue(driver.getCurrentUrl().contains("search"));
        ExtentManager.getTest().pass("Search results shown");
    }

    @Test
    public void searchInvalid() {
        ExtentManager.createTest("searchInvalid");
        sp.search("xyzxyzxyz123");
        String content = sp.getNoResultMsg();
        Assert.assertFalse(content.isEmpty());
        ExtentManager.getTest().pass("No results page loaded");
    }

    @Test
    public void openProduct() {
        ExtentManager.createTest("openProduct");
        sp.search("MacBook");
        sp.openFirstProduct();
        Assert.assertTrue(driver.getCurrentUrl().contains("product"));
        ExtentManager.getTest().pass("Product page opened");
    }

}