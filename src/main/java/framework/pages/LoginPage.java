package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "[data-test='username']")
    private WebElement usernameField;

    @FindBy(css = "[data-test='password']")
    private WebElement passwordField;

    @FindBy(css = "[data-test='login-button']")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        waitForElementVisible(usernameField);
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForElementVisible(passwordField);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        waitForElementClickable(loginButton);
        loginButton.click();
    }

    public String getErrorMessage() {
        waitForElementVisible(errorMessage);
        return errorMessage.getText();
    }
}