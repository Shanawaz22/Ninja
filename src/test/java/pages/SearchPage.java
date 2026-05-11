package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

    @FindBy(name = "search")
    WebElement searchBox;

    @FindBy(css = "button.btn-default")
    WebElement searchBtn;

    @FindBy(css = "h2")
    WebElement resultHeader;

    @FindBy(css = ".product-thumb h4 a")
    WebElement firstProduct;

    @FindBy(css = "p.text-danger")
    WebElement noResultMsg;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void search(String keyword) {
        type(searchBox, keyword);
        click(searchBtn);
    }

    public String getResultHeader() {
        return getText(resultHeader);
    }

    public String getFirstProductName() {
        return getText(firstProduct);
    }

    public void openFirstProduct() {
        click(firstProduct);
    }

    public String getNoResultMsg() {
        return getText(noResultMsg);
    }

}