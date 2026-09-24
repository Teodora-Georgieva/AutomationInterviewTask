package tests.context;

import framework.api.ApiClient;
import framework.api.AuthApi;

import framework.api.BookingApi;
import framework.config.ConfigManager;
import framework.driver.DriverManager;
import framework.models.BookingRequest;
import framework.pages.*;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import io.restassured.response.Response;

@Data
public class TestContext {
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private OrderConfirmationPage confirmationPage;

    private ApiClient apiClient;
    private AuthApi authApi;
    private BookingApi bookingApi;
    private BookingRequest bookingRequest;
    private int bookingId;

    private Response response;
    private String token;

    public void initializeUi() {
        WebDriver driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
        this.checkoutOverviewPage = new CheckoutOverviewPage(driver);
        this.confirmationPage = new OrderConfirmationPage(driver);
    }

    public void initializeApi() {
        this.apiClient = new ApiClient(ConfigManager.getApiBaseUrl());
        this.authApi = new AuthApi(apiClient);
        this.bookingApi = new BookingApi(apiClient);
    }
}