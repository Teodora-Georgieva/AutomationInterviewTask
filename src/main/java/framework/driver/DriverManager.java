package framework.driver;

import framework.config.ConfigManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
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
            log.info("WebDriver initialized");
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
            log.info("WebDriver closed");
        }
    }
}