package tests.stepdefinitions.ui;

import framework.config.ConfigManager;
import framework.driver.DriverManager;
import framework.pages.LoginPage;
import framework.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import tests.context.TestContext;

public class LoginSteps {
    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the SauceDemo login page")
    public void openLoginPage() {
        DriverManager.getDriver().get(ConfigManager.getUiBaseUrl());
    }

//    @When("I enter username {string}")
//    public void enterUsername(String username) {
//        context.getLoginPage().enterUsername(username);
//    }
//
//    @When("I enter password {string}")
//    public void enterPassword(String password) {
//        context.getLoginPage().enterPassword(password);
//    }
//
//    @When("I click the login button")
//    public void clickLoginButton() {
//        context.getLoginPage().clickLoginButton();
//    }

    @When("I login with username {string} and password {string}")
    public void login(String username, String password) {
        context.getLoginPage().enterUsername(username);
        context.getLoginPage().enterPassword(password);
        context.getLoginPage().clickLoginButton();
    }

    @Then("I should be redirected to the products page")
    public void verifySuccessfulLogin() {
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), ConfigManager.getProductsUrl());
        Assert.assertEquals(context.getProductsPage().getPageHeader(), "Products"); //todo remove the constant from here
    }

    @Then("I should see the login error message {string}")
    public void verifyLoginError(String errorMessage) {
        Assert.assertEquals(context.getLoginPage().getErrorMessage(), errorMessage);
    }
}