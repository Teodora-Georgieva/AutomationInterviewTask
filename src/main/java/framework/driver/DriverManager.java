package framework.driver;

import framework.config.ConfigManager;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {}

    public static void initializeDriver() {
        if (driver.get() == null) {
            WebDriver webDriver = DriverFactory.createDriver(
                    ConfigManager.getBrowser(),
                    ConfigManager.isHeadless()
            );

            driver.set(webDriver);
        }
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized. Call DriverManager.initializeDriver() first."
            );
        }

        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}