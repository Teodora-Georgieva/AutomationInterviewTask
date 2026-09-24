package tests.hooks;

import framework.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.log4j.Log4j2;
import tests.context.TestContext;

@Log4j2
public class Hooks {
    private TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before("@ui")
    public void setUpUi() {
        DriverManager.initializeDriver();
        context.initializeUi();
        log.info("UI test setup completed");
    }

    @After("@ui")
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Before("@api")
    public void setUpApi() {
        context.initializeApi();
        log.info("API test setup completed");
    }
}