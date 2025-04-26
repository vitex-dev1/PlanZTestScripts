package Login.Testcases;

import Login.Pages.Login;
import ai.planz.dev.Browsers.TestSetup;
import Common.Actions;
import ai.planz.dev.Browsers.BrowserManager;
import ai.planz.dev.helpers.ExcelHelper;
import ai.planz.dev.helpers.PropertiesHelper;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class T1_NormalLogin extends TestSetup {
    static Login Login = new Login();
    private ExcelHelper excelHelper;

    @BeforeClass
    public void setup() {
        // Initialize the Excel helper once before running tests
        excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/TestData/LoginPage.xlsx", "NormalLogin");
    }

    @Test(priority = 1)
    public void checkCorrectPage() {
        BrowserManager.getDriver().get(PropertiesHelper.getValue("ADMIN_LOGIN_URL"));
        Actions.assertContains(Actions.getCurrentUrl(), "/admin/login", "FAIL. Not at Login");
    }

    @Test(priority = 2)
    public void loginSuccess() {

        Actions.openURL(PropertiesHelper.getValue("ADMIN_LOGIN_URL"));
        Login.adminLogin("clickdoc.dev@gmail.com", "nnN3ZYjw@");
        Login.verifyLoginSuccess();
    }

    @Test(priority = 3)
    public void blankEmail() {
        //         Click "Log Out"
        BrowserManager.getDriver().findElement(By.xpath("//a[@class='user-profile dropdown-toggle']")).click();
        BrowserManager.getDriver().findElement(By.xpath("//*[normalize-space()='Log Out']")).click();
        //Click login
        BrowserManager.getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        Login.verifyLoginFailDueToBlankEmail();
    }

    @Test(priority = 4)

    public void blankPassword() {
        Actions.openURL(PropertiesHelper.getValue("ADMIN_LOGIN_URL"));

        //Click login
        BrowserManager.getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        Login.verifyLoginFailDueToBlankPassword();
    }

    @Test(priority = 5)
    public void wrongEmail() {
        Login.adminLogin(
                excelHelper.getCellData("Email", 2),
                excelHelper.getCellData("Password", 2)
        );
        Login.verifyLoginFailDueToWrongEmail();
    }

    @Test(priority = 6)
    public void wrongPassword() {
        Login.adminLogin(
                excelHelper.getCellData(1, 1),
                excelHelper.getCellData(2, 2)
        );
        Login.verifyLoginFailDueToWrongPassword();
    }
}

