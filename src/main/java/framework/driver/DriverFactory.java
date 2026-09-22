package framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private DriverFactory() {}

    public static WebDriver createDriver(String browser, boolean headless) {

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }

                return new FirefoxDriver(firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();

                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }

                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
        }
    }
}