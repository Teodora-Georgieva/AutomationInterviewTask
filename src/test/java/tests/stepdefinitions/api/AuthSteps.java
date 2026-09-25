package tests.stepdefinitions.api;

import framework.config.ConfigManager;
import framework.models.AuthRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.context.TestContext;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AuthSteps {
    private final TestContext context;

    public AuthSteps(TestContext context) {
        this.context = context;
    }

    @When("I authenticate with valid credentials")
    public void authenticateWithValidCredentials() {
        AuthRequest request = new AuthRequest(
                ConfigManager.getApiUsername(),
                ConfigManager.getApiPassword()
        );

        Response response = context.getAuthApi().createToken(request);
        context.setResponse(response);
        context.setToken(response.getBody().jsonPath().getString("token"));
    }

    @Then("the authentication should be successful")
    public void verifySuccessfulAuth() {
        assertEquals(context.getResponse().getStatusCode(), 200);
    }

    @Then("an authentication token should be returned")
    public void verifyAuthTokenPresence() {
        String token = context.getResponse()
                .jsonPath()
                .getString("token");

        assertNotNull(token);
        context.setToken(token);
    }
}