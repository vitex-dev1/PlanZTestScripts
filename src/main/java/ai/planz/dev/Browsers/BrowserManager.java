package ai.planz.dev.Browsers;

import org.openqa.selenium.WebDriver;

public class BrowserManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private BrowserManager() {
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        BrowserManager.driver.set(driver);
    }

    public static void quit() {
        BrowserManager.driver.get().quit();
        driver.remove();
    }
}
