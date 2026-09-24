package tests.stepdefinitions.ui;

import framework.config.ConfigManager;
import framework.driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import tests.context.TestContext;

@Log4j2
public class LoginSteps {
    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the SauceDemo login page")
    public void openLoginPage() {
        DriverManager.getDriver().get(ConfigManager.getUiBaseUrl());
    }

    @When("I login with username {string} and password {string}")
    public void login(String username, String password) {
        log.info("Logging in as user: {}", username);
        context.getLoginPage().enterUsername(username);
        context.getLoginPage().enterPassword(password);
        context.getLoginPage().clickLoginButton();
    }

    @Then("I should be redirected to the products page")
    public void verifySuccessfulLogin() {
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), ConfigManager.getProductsUrl());
        Assert.assertEquals(context.getProductsPage().getPageTitle(), "Products");
    }

    @Then("I should see the login error message {string}")
    public void verifyLoginError(String errorMessage) {
        Assert.assertEquals(context.getLoginPage().getErrorMessage(), errorMessage);
    }

    @Given("I'm logged in")
    public void loginAsDefaultUser() {
        openLoginPage();
        login(ConfigManager.getUiUsername(), ConfigManager.getUiPassword());
    }
}