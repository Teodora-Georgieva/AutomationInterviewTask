package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(css = "[data-test='title']")
    private WebElement pageTitle;

    @FindBy(css = "[data-test='firstName']")
    private WebElement firstNameField;

    @FindBy(css = "[data-test='lastName']")
    private WebElement lastNameField;

    @FindBy(css = "[data-test='postalCode']")
    private WebElement postalCodeField;

    @FindBy(css = "[data-test='continue']")
    private WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        waitForElementVisible(pageTitle);
        return pageTitle.getText();
    }

    public void enterOrderDetails(String firstName, String lastName, String postalCode) {
       waitForElementVisible(firstNameField);
       firstNameField.sendKeys(firstName);
       waitForElementVisible(lastNameField);
       lastNameField.sendKeys(lastName);
       waitForElementVisible(postalCodeField);
       postalCodeField.sendKeys(postalCode);
    }

    public void clickContinueButton() {
        waitForElementClickable(continueButton);
        continueButton.click();
    }
}