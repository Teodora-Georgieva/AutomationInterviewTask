package tests.context;

import framework.driver.DriverManager;
import framework.pages.LoginPage;
import framework.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

public class TestContext {
    private final WebDriver driver;

    private final LoginPage loginPage;
    private final ProductsPage productsPage;

    public TestContext() {
        this.driver = DriverManager.getDriver();

        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public ProductsPage getProductsPage() {
        return productsPage;
    }
}