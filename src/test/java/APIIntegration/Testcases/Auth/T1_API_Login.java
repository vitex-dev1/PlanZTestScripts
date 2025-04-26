package APIIntegration.Testcases.Auth;

import APIIntegration.Functions.Auth.API_Login;
import ai.planz.dev.helpers.ExcelHelper;
import ai.planz.dev.helpers.PropertiesHelper;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T1_API_Login {

    API_Login API_Login = new API_Login();
    private ExcelHelper excelHelper;

    @BeforeClass
    public void setup() {
        // Set the endpoint URL
        RestAssured.baseURI = PropertiesHelper.getValue("ENDPOINT_AUTH_LOGIN"); // Load it dynamically based on the environment

        // Initialize the Excel helper once before running tests
        excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/TestData/LoginPage.xlsx", "NormalLogin");
    }

    @Test(priority = 1)
    public void authLoginSuccess() {
        API_Login.authLoginSuccess(
                excelHelper.getCellData("Email", 1),
                excelHelper.getCellData("Password", 1));
    }

    @Test(priority = 2)
    public void authLoginFailDueToBlankEmail() {
        API_Login.authLoginFailDueToBlankEmail(
                excelHelper.getCellData("Email", 4),
                excelHelper.getCellData("Password", 4));
    }

    @Test(priority = 3)
    public void authLoginFailDueToMissingEmail() {
        API_Login.authLoginFailDueToMissingEmail(excelHelper.getCellData("Password", 3));
    }

    @Test(priority = 4)
    public void authLoginFailDueToBlankPassword() {
        API_Login.authLoginFailDueToBlankPassword(
                excelHelper.getCellData("Email", 5),
                excelHelper.getCellData("Password", 5));
    }

    @Test(priority = 5)
    public void authLoginFailDueToMissingPassword() {
        API_Login.authLoginFailDueToMissingPassword(excelHelper.getCellData("Email", 1));
    }

    @Test(priority = 6)
    public void authLoginFailDueToWrongEmail() {
        API_Login.authLoginFailDueToWrongEmail(
                excelHelper.getCellData("Email", 2),
                excelHelper.getCellData("Password", 2));
    }

    @Test(priority = 7)
    public void authLoginFailDueToWrongPassword() {
        API_Login.authLoginFailDueToWrongPassword(
                excelHelper.getCellData("Email", 3),
                excelHelper.getCellData("Password", 3));
    }
}
