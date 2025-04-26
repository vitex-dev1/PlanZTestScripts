package ai.planz.dev.Browsers;

import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Point;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.time.Duration;


public class TestSetup {
    private WebDriver driver;

    @BeforeSuite
    public void setupEnvironment() {
        PropertiesHelper.loadAllFiles();
    }

    @BeforeClass
    @Parameters({"browser"})
    public void setupDriver(@Optional("chrome") String browserName) {
        driver = createDriver(browserName);
        if (driver != null) {
            BrowserManager.setDriver(driver);
        } else {
            throw new IllegalStateException("Failed to initialize WebDriver for browser: " + browserName);
        }
    }

    private WebDriver createDriver(String browserName) {
        WebDriver driver;
        PropertiesHelper.loadAllFiles();
        String browserProperty = PropertiesHelper.getValue("BROWSER");
        if (browserProperty == null || browserProperty.isEmpty() || browserProperty.isBlank()) {
            browserName = browserName;
        } else {
            browserName = browserProperty;
        }
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initializeDriver(new ChromeDriver());
                break;
            case "firefox":
                driver = initializeDriver(new FirefoxDriver());
                break;
            case "edge":
                driver = initializeDriver(new EdgeDriver());
                break;
            default:
                WriteLogs.warn("Invalid browser specified: " + browserName + ". Defaulting to Chrome.");
                driver = initializeDriver(new ChromeDriver());
        }
        return driver;
    }


    private WebDriver initializeDriver(WebDriver driverInstance) {
        WriteLogs.info("Launching browser...");
        driver = driverInstance;

         //Position the browser window on the second monitor if available
        GraphicsDevice[] screens = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        if (screens.length > 1) {
            Rectangle secondScreenBounds = screens[1].getDefaultConfiguration().getBounds();
            driver.manage().window().setPosition(new Point(secondScreenBounds.x + 100, secondScreenBounds.y + 100));
        } else {
            WriteLogs.info("Only one monitor detected. Opening browser on the primary screen.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        return driver;
    }

    @AfterClass
    public void closeDriver() {
        if (BrowserManager.getDriver() != null) {
            BrowserManager.quit();
        }
    }
}
