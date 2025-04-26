package APIIntegration.Testcases.Agenda.Agenda_List;

import APIIntegration.Functions.Agenda.Agenda_List;
import APIIntegration.Functions.Auth.API_Login;
import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.ExcelHelper;
import ai.planz.dev.helpers.PropertiesHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginWithUserAccount {

    API_Login API_Login = new API_Login();
    private ExcelHelper excelHelper;
    Agenda_List Agenda_List = new Agenda_List();
    private String token; // ✅ Store the token globally for later use

    @BeforeClass
    public void setup() {
        // ✅ Load API Endpoints
        String loginEndpoint = PropertiesHelper.getValue("ENDPOINT_AUTH_LOGIN");
        String agendaEndpoint = PropertiesHelper.getValue("ENDPOINT_AGENDA_LIST");

        // ✅ Initialize Excel Helper
        excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/TestData/LoginPage.xlsx", "NormalLogin");

        // ✅ Login and get Response
        Response response = API_Login.authenticateUser(loginEndpoint,
                excelHelper.getCellData("Email", 7),
                excelHelper.getCellData("Password", 7));

        // ✅ Lấy token từ response
        token = response.jsonPath().getString("data.token");

// ✅ Log token để kiểm tra
        WriteLogs.info("🔑 Token received: " + token);

        // ✅ Set the agenda list endpoint as the base URI for further tests
        RestAssured.baseURI = agendaEndpoint;

        //Assert User is Regular User
        API_Login.assertUserIsRegularUser(response);
    }

    //Case 1: Filter by User + Add User Info: Get all the ACTIVE agenda’s of the logged in user (including shared agendas). User id and name included
    @Test(priority = 1)
    public void filterByUser_And_AddUserInfo() {
        Agenda_List.getAgendaListByUserAndAddUserInfo(token);
    }
}
