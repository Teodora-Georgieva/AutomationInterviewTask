package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BasePage {
    @FindBy(css = "[data-test='title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-test='complete-header']")
    private WebElement pageHeader;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        waitForElementVisible(pageTitle);
        return pageTitle.getText();
    }

    public String getPageHeader() {
        waitForElementVisible(pageHeader);
        return pageHeader.getText();
    }
}