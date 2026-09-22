package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProductsPage extends BasePage {
    @FindBy(css = "[data-test='title']")
    private WebElement secondaryHeader;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

//    public boolean isDisplayed() {
//        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
//    }

    public String getPageHeader() {
        return secondaryHeader.getText();
    }
}