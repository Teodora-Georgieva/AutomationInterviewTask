package tests.stepdefinitions.ui;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import tests.context.TestContext;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortProductsSteps {
    private final TestContext context;

    public SortProductsSteps(TestContext context) {
        this.context = context;
    }

    @When("I sort the products by {string}")
    public void sortProductsBy(String sortCriterion) {
        context.getProductsPage().sortProductsBy(sortCriterion);
    }

    @Then("the products should be displayed in ascending order by price")
    public void verifyProductsSortedByPriceAsc() {
        List<BigDecimal> actualProductPrices = context.getProductsPage().getProductPrices();
        List<BigDecimal> expectedProductPrices = new ArrayList<>(actualProductPrices);
        Collections.sort(expectedProductPrices);
        Assert.assertEquals(actualProductPrices, expectedProductPrices);
    }
}