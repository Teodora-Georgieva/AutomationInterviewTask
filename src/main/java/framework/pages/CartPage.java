package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(css = "[data-test='title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
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

    public boolean isProductInCart(String productName) {
        By cartItemXpath = findXpathByCartItemName(productName);
        return !driver.findElements(cartItemXpath).isEmpty(); //better than driver.findElement(findXpathByCartItemName(productName)).isDisplayed(),
                                                              //because findElement() throws NoSuchElementException if the product isn't there
    }

    public String getCartItemPrice(String productName) {
        By xpath = findXpathByCartItemName(productName);
        return driver.findElement(xpath)
                .findElement(By.cssSelector("[data-test='inventory-item-price']"))
                .getText();
    }

    public void clickCheckoutButton() {
        waitForElementClickable(checkoutButton);
        checkoutButton.click();
    }
}