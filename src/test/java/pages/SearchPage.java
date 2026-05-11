package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

    @FindBy(name = "search")
    WebElement searchBox;

    @FindBy(css = "button.btn-default")
    WebElement searchBtn;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void search(String keyword) {
        type(searchBox, keyword);
        click(searchBtn);
    }

    public String getResultHeader() {
        WebElement e = driver.findElement(By.cssSelector("#content h1"));
        return getText(e);
    }

    public String getFirstProductName() {
        WebElement e = driver.findElement(By.cssSelector(".product-thumb h4 a"));
        return getText(e);
    }

    public void openFirstProduct() {
        WebElement e = driver.findElement(By.cssSelector(".product-thumb h4 a"));
        click(e);
    }

    public String getNoResultMsg() {
        WebElement e = driver.findElement(By.cssSelector("#content"));
        return getText(e);
    }

}