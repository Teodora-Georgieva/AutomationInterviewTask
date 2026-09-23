package tests.context;

import framework.driver.DriverManager;
import framework.pages.*;
import org.openqa.selenium.WebDriver;

public class TestContext {
    private final WebDriver driver;

    private final LoginPage loginPage;
    private final ProductsPage productsPage;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;
    private final CheckoutOverviewPage checkoutOverviewPage;
    private final OrderConfirmationPage confirmationPage;

    public TestContext() {
        this.driver = DriverManager.getDriver();

        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
        this.checkoutOverviewPage = new CheckoutOverviewPage(driver);
        this.confirmationPage = new OrderConfirmationPage(driver);
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public ProductsPage getProductsPage() {
        return productsPage;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public CheckoutPage getCheckoutPage() { return checkoutPage; }

    public CheckoutOverviewPage getCheckoutOverviewPage() { return checkoutOverviewPage; }

    public OrderConfirmationPage getConfirmationPage() { return confirmationPage; }
}