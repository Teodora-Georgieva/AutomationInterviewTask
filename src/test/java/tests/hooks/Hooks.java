package tests.hooks;

import framework.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before("@ui")
    public void setUp() {
        DriverManager.initializeDriver();
    }

    @After("@ui")
    public void tearDown() {
        DriverManager.quitDriver();
    }
}