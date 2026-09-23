package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {
    @FindBy(css = "[data-test='title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-test='finish']")
    private WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        waitForElementVisible(pageTitle);
        return pageTitle.getText();
    }

    private By findXpathByCartItemName(String cartItemName) {
        return By.xpath(
                "//div[@data-test='inventory-item']" +
                        "[.//div[@data-test='inventory-item-name' and text()='" + cartItemName + "']]"
        );
    }

    public boolean isProductPresent(String productName) {
        By cartItemXpath = findXpathByCartItemName(productName);
        return !driver.findElements(cartItemXpath).isEmpty();
    }

    public void clickOnFinishButton() {
        waitForElementClickable(finishButton);
        finishButton.click();
    }
}