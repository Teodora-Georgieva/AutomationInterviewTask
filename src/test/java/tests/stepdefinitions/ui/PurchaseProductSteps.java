package tests.stepdefinitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import tests.context.TestContext;

import java.util.Map;

@Log4j2
public class PurchaseProductSteps {
    private final TestContext context;

    public PurchaseProductSteps(TestContext context) {
        this.context = context;
    }

    @When("I add {string} to the cart")
    public void addProductToCart(String productName) {
        context.getProductsPage().addProductToCart(productName);
        log.info("product {} added to cart", productName);
    }

    @Then("the cart should contain {int} item")
    public void verifyCartItemsCount(int expectedCountOfItems) {
        Assert.assertEquals(expectedCountOfItems, context.getProductsPage().getCartItemsCount());
    }

    @When("I open the cart")
    public void openCart() {
        context.getProductsPage().openCart();
    }

    @Then("I should see the cart page")
    public void verifyCartPageIsDisplayed() {
        Assert.assertEquals(context.getCartPage().getPageTitle(), "Your Cart");
    }

    @Then("I should see {string} in the cart")
    public void verifyProductAddedToCart(String productName) {
        Assert.assertTrue(context.getCartPage().isProductInCart(productName));
    }

    @Then("the price of {string} in the cart should match the price on the products page")
    public void verifyProductPriceMatchesPriceInCart(String productName) {
        Assert.assertEquals(context.getProductsPage().getProductPrice(productName), context.getCartPage().getCartItemPrice(productName));
    }

    @When("I click on checkout button")
    public void clickCheckoutButton() {
        context.getCartPage().clickCheckoutButton();
    }

    @Then("I should see the checkout personal details page")
    public void verifyCheckoutPageIsDisplayed() {
        Assert.assertEquals(context.getCheckoutPage().getPageTitle(), "Checkout: Your Information");
    }

    @When("I enter my order details")
    public void enterOrderDetails(DataTable orderDetails) {
        Map<String, String> details = orderDetails.asMaps().get(0);

        context.getCheckoutPage().enterOrderDetails(
                details.get("firstName"),
                details.get("lastName"),
                details.get("postalCode"));
    }

    @When("I place the order")
    public void placeOrder() {
        context.getCheckoutPage().clickContinueButton();
    }

    @Then("I should see the checkout overview page")
    public void verifyCheckoutOverviewPageIsDisplayed() {
        Assert.assertEquals(context.getCheckoutOverviewPage().getPageTitle(), "Checkout: Overview");
    }

    @Then("I should see {string} on the page")
    public void verifyProductPresentOnOverviewPage(String productName) {
        Assert.assertTrue(context.getCheckoutOverviewPage().isProductPresent(productName));
    }

   @When("I click on finish button")
    public void clickOnFinishButton() {
        context.getCheckoutOverviewPage().clickOnFinishButton();
   }

   @Then("I should see the order confirmation page")
    public void verifyConfirmationPageIsDisplayed() {
        Assert.assertEquals(context.getConfirmationPage().getPageTitle(), "Checkout: Complete!");
        Assert.assertEquals(context.getConfirmationPage().getPageHeader(), "Thank you for your order!");
   }
}