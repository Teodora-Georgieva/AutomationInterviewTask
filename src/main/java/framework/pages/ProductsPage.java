package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;
import java.util.List;

public class ProductsPage extends BasePage {
    @FindBy(css = "[data-test='title']")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    private WebElement cartBadge;

    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement sortDropdown;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        waitForElementVisible(pageTitle);
        return pageTitle.getText();
    }

    private By findXpathByProductName(String productName) {
        return By.xpath(
                "//div[@data-test='inventory-item']" +
                        "[.//div[@data-test='inventory-item-name' and text()='" + productName + "']]"
        );
    }

    public String getProductPrice(String productName) {
        By xpath = findXpathByProductName(productName);
        return driver.findElement(xpath)
                .findElement(By.cssSelector("[data-test='inventory-item-price']"))
                .getText();
    }

    public void addProductToCart(String productName) {
        By xpath = findXpathByProductName(productName);

        driver.findElement(xpath)
                .findElement(By.cssSelector("button[data-test^='add-to-cart-']"))
                .click();
    }

    public int getCartItemsCount() {
        return Integer.parseInt(cartBadge.getText());
    }

    public void openCart() {
        cartIcon.click();
    }

    public List<BigDecimal> getProductPrices() {
        By byProductPrice = By.cssSelector("[data-test='inventory-item-price']");

        return driver.findElements(byProductPrice)
                .stream()
                .map(element -> element.getText().replace("$", "").trim())
                .map(BigDecimal::new)
                .toList();
    }

    public void sortProductsBy(String sortCriterion) {
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText(sortCriterion);
    }
}