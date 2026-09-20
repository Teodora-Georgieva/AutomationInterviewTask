package smoke;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SmokeTestSteps {
    private WebDriver driver;

    @Given("I open the SauceDemo website")
    public void openSauceDemo() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }

    @Then("the page title should be {string}")
    public void verifyPageTitle(String expectedTitle) {
        Assert.assertEquals(driver.getTitle(), expectedTitle);
        driver.quit();
    }
}