package Common;

import ai.planz.dev.Browsers.BrowserManager;
import ai.planz.dev.Logs.WriteLogs;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;


public class Actions {

    private static WebDriver driver;
    private static int TIMEOUT = 10;
    private static double STEP_TIME = 0.5;
    private static int PAGE_LOAD_TIMEOUT = 20;


    //Actions with elements
    public static void clickElement(By by) {
        waitForPageLoaded();
        scrollToElement(by);
        waitForElementVisible(by);
        waitForElementToBeClickable(by);
        BrowserManager.getDriver().findElement(by).click();
        WriteLogs.info("Click on element " + by);
        waitForPageLoaded();
    }

    // clickElement that accepts WebElement
    public static void clickElement(WebElement element) {
        waitForPageLoaded();
        scrollToElement(element);
        waitForElementVisible(element);
        waitForElementToBeClickable(element);
        element.click();
        WriteLogs.info("Click on element " + element);
        waitForPageLoaded();
    }

    public static void setText(By by, String text) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(5));
        scrollToElement(by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        BrowserManager.getDriver().findElement(by).sendKeys(text);
        WriteLogs.info("Set text " + text + " on element " + by);
        waitForPageLoaded();
    }

    public static void clearText(By by) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(5));
        scrollToElement(by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        BrowserManager.getDriver().findElement(by).clear();
        WriteLogs.info("Clear text on element " + by);
        waitForPageLoaded();
    }

    public static void setTextAndKey(By by, String value, Keys key) {
        waitForPageLoaded();
        scrollToElement(by);
        getWebElement(by).sendKeys(value, key);
        WriteLogs.info("Set text: " + value + " on element " + by);
        waitForPageLoaded();
    }

    //Wait for Element to
    public static void waitForElementVisible(By by) {
        waitForPageLoaded();
        try {
            WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            WriteLogs.error("Timeout waiting for the element to be visible. " + by.toString());
            // Mark the current testcase as failed
            Assert.fail("Timeout waiting for the element to be visible. " + by.toString());
        }
    }

    //waitForElementVisible that accepts WebElement
    public static void waitForElementVisible(WebElement element) {
        waitForPageLoaded();
        try {
            WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOf(element));

        } catch (Throwable error) {
            WriteLogs.error("Timeout waiting for the element to be visible. " + element.toString());
            // Mark the current testcase as failed
            Assert.fail("Timeout waiting for the element to be visible. " + element.toString());
        }
    }

    public static void waitForElementVisible(By by, int second) {
        waitForPageLoaded();
        try {
            WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(second));

            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            WriteLogs.error("Timeout waiting for the element to be visible. " + by.toString());
            // Mark the current testcase as failed
            Assert.fail("Timeout waiting for the element to be visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int second) {
        waitForPageLoaded();
        try {
            WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(second));

            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            WriteLogs.error("Element does not exist. " + by.toString());
            Assert.fail("Element does not exist. " + by.toString());
        }
    }

    public static void waitForElementToBeClickable(By by) {
        waitForPageLoaded();
        try {
            WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(5));


            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            WriteLogs.error("Timeout waiting for the element to be ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element to be ready to click. " + by.toString());
        }
    }

    //waitForElementToBeClickable that accepts WebElement
    public static void waitForElementToBeClickable(WebElement element) {
        waitForPageLoaded();
        try {
            WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(5));


            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Throwable error) {
            WriteLogs.error("Timeout waiting for the element to be ready to click. " + element.toString());
            Assert.fail("Timeout waiting for the element to be ready to click. " + element.toString());
        }
    }

    public static void waitForElementToBeClickable(By by, int second) {
        waitForPageLoaded();
        try {
            WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(second));

            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            WriteLogs.error("Timeout waiting for the element to be ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element to be ready to click. " + by.toString());
        }
    }

    public static void waitForOverlaysToDisappear(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(BrowserManager.getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
// Extra check for Firefox: Remove overlay if it still exists in the DOM
            if (driver instanceof FirefoxDriver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.querySelectorAll(arguments[0]).forEach(el => el.remove());", by);
                WriteLogs.info("✅ Overlay removed successfully in Firefox.");
            }

        } catch (Throwable error) {
            WriteLogs.error("Timeout waiting for overlays to disappear. " + by.toString());
            Assert.fail("Timeout waiting for overlays to disappear. " + by.toString());
        }
    }

    //Wait for page to load
    public static void waitForPageLoaded() {
        WebDriver driver = BrowserManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait until document is fully loaded
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                boolean isPageLoaded = js.executeScript("return document.readyState").equals("complete");
                boolean isJQueryInactive = (Boolean) js.executeScript("return window.jQuery == undefined || jQuery.active == 0");
                boolean isNetworkIdle = (Boolean) js.executeScript("return window.performance.getEntriesByType('resource').every(r => r.responseEnd > 0)");

                return isPageLoaded && isJQueryInactive && isNetworkIdle;
            }
        };

        try {
            wait.until(pageLoadCondition);
//            WriteLogs.info("✅ Page fully loaded.");
        } catch (TimeoutException e) {
            WriteLogs.error("⏳ Timeout: Page did not fully load in time.");
        }

        // Extra wait for overlays (if any)
//        waitForOverlaysToDisappear(By.xpath("//div[@class='spinner']"));
//        waitForOverlaysToDisappear(By.id("DataTables_Table_0_processing"));
        //Wait for another 2 seconds to be extra sure
        //sleep(2);
    }


    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebElement getWebElement(By by) {
        return BrowserManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return BrowserManager.getDriver().findElements(by);
    }

    //Mouse operations
    public static void scrollToElement(By by) {
        waitForPageLoaded();
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        //false means the element will appear at the bottom of the viewport (scroll up or down as needed).
    }

    public static void scrollToElement(WebElement element) {
        waitForPageLoaded();
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public static void scrollToElementAtTop(By by) {
        waitForPageLoaded();
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }

    public static void scrollToElementAtBottom(By by) {
        waitForPageLoaded();
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
    }

    public static void scrollToElementAtTop(WebElement element) {
        waitForPageLoaded();
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        //true means the element will appear at the top of the viewport (scroll up or down as needed).
    }

    public static void scrollToElementAtBottom(WebElement element) {
        waitForPageLoaded();
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        waitForPageLoaded();
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static void scrollAndClickAtCoordinates(int x, int y) {
        try {
            // Scroll the viewport to the desired position
            scrollToPosition(x, y);
            WriteLogs.info("Scrolled to coordinates (" + x + ", " + y + ")");

            // Click at the coordinates using Actions
            org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(BrowserManager.getDriver());
            action.moveByOffset(x, y).click().perform();
            WriteLogs.info("Clicked at coordinates (" + x + ", " + y + ")");
        } catch (Exception e) {
            WriteLogs.error("Failed to scroll and click at coordinates (" + x + ", " + y + "): " + e.getMessage());
            throw e;
        }
    }

    public static boolean mouseHover(By by) {
        waitForPageLoaded();
        try {
            org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(BrowserManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        waitForPageLoaded();
        try {
            org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(BrowserManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            WriteLogs.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        waitForPageLoaded();
        try {
            org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(BrowserManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            WriteLogs.error(e.getMessage());
            return false;
        }
    }

    //Keyboard operations
    public static boolean pressENTER() {
        waitForPageLoaded();
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressF5() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F5);
            robot.keyRelease(KeyEvent.VK_F5);
            waitForPageLoaded();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Equality and Containment Functions
    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        WriteLogs.info("Assert equals: " + actual + "- and - " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        WriteLogs.info("Assert contains: Does '" + actual + "' contain '" + expected + "'?");
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    public static void assertNotContains(String actual, String expected, String message) {
        waitForPageLoaded();
        WriteLogs.info("Assert NOT contains: Does '" + actual + "' not contain '" + expected + "'?");
        boolean check = actual.contains(expected);
        Assert.assertFalse(check, message);
    }

    //Get CSS
    public static String getElementText(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        WriteLogs.info("Get text of element " + by);
        String text = getWebElement(by).getText();
        WriteLogs.info("==> TEXT: " + text);
        return text; //Return String
    }

    public static String getTextToLowerCase(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        WriteLogs.info("Get text of element " + by);
        String text = getWebElement(by).getText().toLowerCase();
        WriteLogs.info("==> TEXT: " + text);
        return text; //Return String
    }

    public static String getTextToLowerCase(WebElement element) {
        waitForPageLoaded();
        WriteLogs.info("Get text of element " + element);
        String text = element.getText().toLowerCase();
        WriteLogs.info("==> TEXT: " + text);
        return text; //Return String
    }

    public static String getElementAttribute(By by, String attributeName) {
        waitForPageLoaded();
        waitForElementVisible(by);
        WriteLogs.info("Get attribute of element " + by);
        String value = getWebElement(by).getAttribute(attributeName);
        WriteLogs.info("==> Attribute value: " + value);
        return value;
    }

    public static String getElementCssValue(By by, String cssPropertyName) {
        waitForPageLoaded();
        waitForElementVisible(by);
        WriteLogs.info("Get CSS value " + cssPropertyName + " of element " + by);
        String value = getWebElement(by).getCssValue(cssPropertyName);
        WriteLogs.info("==> CSS value: " + value);
        return value;
    }

    //Check if an element is visible
    public static boolean isElementDisplayed(By by) {
        waitForPageLoaded();
        try {
            WebElement element = BrowserManager.getDriver().findElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //Checks if an element exists in the DOM, regardless of whether it is visible or interactable.
    public static boolean checkElementExist(By xpath) {
        waitForPageLoaded();
        try {
            BrowserManager.getDriver().findElement(xpath);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //Get attribute value
    public static String getAttribute(By by, String attributeName) {
        waitForPageLoaded();
        waitForElementVisible(by);
        WriteLogs.info("Get attribute " + attributeName + " of element " + by);
        String value = getWebElement(by).getAttribute(attributeName);
        WriteLogs.info("==> Attribute value: " + value);
        return value;
    }

    public static boolean isElementSelected(By by) {
        waitForPageLoaded();
        try {
            WebElement element = BrowserManager.getDriver().findElement(by);
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }

    }

    //@Step("Open URL: {url}")
    @Step("Open URL: {0}")
    public static void openURL(String url) {
        BrowserManager.getDriver().get(url);
        sleep(STEP_TIME);
        WriteLogs.info("Open URL: " + url);
    }

    public static String getCurrentUrl() {
        waitForPageLoaded();
        return BrowserManager.getDriver().getCurrentUrl();
    }

    public static boolean verifyElementVisibility(By xpath) {
        waitForPageLoaded();
        try {
            boolean isElementVisible = Actions.isElementDisplayed(xpath);
            if (isElementVisible) {
                return true; // Element is visible
            } else {
                return false; // Element is not displayed
            }
        } catch (Exception e) {
            WriteLogs.error("Error while verifying Element visibility: " + xpath);
            e.printStackTrace();
            return false; //If there is an error, assume that the Element is not displayed

        }
    }

    public static boolean verifyElementInvisibility(By xpath) {
        waitForPageLoaded();
        try {
            boolean isElementVisible = Actions.isElementDisplayed(xpath);
            if (!isElementVisible) {
                return true; // Element is not displayed
            } else {
                return false; // Element is visible
            }
        } catch (Exception e) {
            WriteLogs.error("Error while verifying Element invisibility: " + xpath);
            e.printStackTrace();
            return false; //If there is an error, assume that the Element is displayed
        }
    }

    //Close the debug bar if it is open
    public static void closeDebugBar() {
        waitForPageLoaded();
        try {
            // Locate the debug bar close button 👇🏾
            WebElement closeButton = BrowserManager.getDriver().findElement(By.xpath("//a[@class='phpdebugbar-close-btn']"));

            // Check if the close button is visible
            if (closeButton.isDisplayed()) {
                closeButton.click(); // Close the debug bar
                System.out.println("Debug bar closed successfully.");
            }
        } catch (NoSuchElementException e) {
            // If the element is not found, proceed with the next steps
            System.out.println("Debug bar close button not found. Proceeding with next steps.");
        } catch (Exception e) {
            // Catch any other exceptions and log them
            System.out.println("An error occurred while handling the debug bar: " + e.getMessage());
        }
    }

    //Zoom in/out the browser
    public static void zoomOutZoomIn(double zoomLevel) //(0.7 = 70% zoom)
    {
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("document.body.style.zoom = '" + zoomLevel + "'");
        WriteLogs.info("Zoomed out/in to " + (zoomLevel * 100) + "%");
    }

    public static void resetZoom() {
        JavascriptExecutor js = (JavascriptExecutor) BrowserManager.getDriver();
        js.executeScript("document.body.style.zoom = '1.0'");
        WriteLogs.info("Reset zoom to 100%");
    }

}