package Login.Pages;

import Common.Actions;
import ai.planz.dev.Browsers.BrowserManager;
import ai.planz.dev.helpers.PropertiesHelper;
import org.openqa.selenium.*;
import org.testng.Assert;

public class Login {
    private final WebDriver driver;

    public Login() {
    this.driver = (BrowserManager.getDriver());}

    private By inputEmail = By.xpath("//input[@type='email']");
    private By inputPassword = By.id("password");
    private By buttonLogin = By.xpath("//button[@type='submit']");

    public void adminLogin(String email, String password) {
        Actions.openURL(PropertiesHelper.getValue("ADMIN_LOGIN_URL"));
        Actions.waitForPageLoaded();
        Actions.setText(inputEmail, email);
        Actions.setText(inputPassword, password);
        Actions.clickElement(buttonLogin);
        Actions.waitForPageLoaded();
    }

    public void verifyLoginSuccess() {
        Actions.assertNotContains(Actions.getCurrentUrl(), "/admin/login", "FAIL. Still at Login");
    }

    public void verifyLoginFailDueToBlankEmail() {
        String actualErrorMessage = "The email field is required.";
        String expectedErrorMessage = BrowserManager.getDriver().findElement(By.xpath("//li[normalize-space()='The email field is required.']")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    public void verifyLoginFailDueToBlankPassword() {
        String actualErrorMessage = "The password field is required.";
        String expectedErrorMessage = BrowserManager.getDriver().findElement(By.xpath("//li[normalize-space()='The password field is required.']")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    public void verifyLoginFailDueToWrongEmail() {
        String actualErrorMessage = "The email address or password you have entered was not correct. Please try again!";
        String expectedErrorMessage = BrowserManager.getDriver().findElement(By.xpath("//li[contains(text(),'The email address or password you have entered was not')]")).getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    public void verifyLoginFailDueToWrongPassword() {
        String actualErrorMessage = "The email address or password you have entered was not correct. Please try again!";
        String expectedErrorMessage = BrowserManager.getDriver().findElement(By.xpath("//li[contains(text(),'The email address or password you have entered was not')]")).getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}
