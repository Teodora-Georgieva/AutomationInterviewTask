package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (inputStream == null) {
                throw new RuntimeException("Could not find config.properties");
            }

            properties.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("Could not load configuration", e);
        }
    }

    private ConfigManager() {}

    public static String getUiBaseUrl() {
        return properties.getProperty("ui.base.url");
    }

    public static String getApiBaseUrl() {
        return properties.getProperty("api.base.url");
    }

    public static String getProductsUrl() {
        return properties.getProperty("products.url");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }

    public static String getUiUsername() {
        return properties.getProperty("ui.username");
    }

    public static String getUiPassword() {
        return properties.getProperty("ui.password");
    }

    public static String getApiUsername() { return properties.getProperty("api.username"); }

    public static String getApiPassword() { return properties.getProperty("api.password"); }
}