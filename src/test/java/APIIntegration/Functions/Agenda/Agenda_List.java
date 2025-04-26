package APIIntegration.Functions.Agenda;

import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.PropertiesHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class Agenda_List {
    public Response agendaList(Map<String, String> payload, String token) { //This method will dynamically handle payloads (even with missing parameters).
        // Ensure the payload is serialized and sent as JSON
        return RestAssured.given()
                .header("Authorization", "Bearer " + token) // ✅ Attach token
                .contentType(ContentType.JSON)
                .queryParams(payload) // ✅ Adds parameters in URL (e.g., ?filter=byUser&add_user_info=true)
                .when()
                .get(PropertiesHelper.getValue("ENDPOINT_AGENDA_LIST"));
    }

    public void getAgendaListByUserAndAddUserInfo(String token) {

        // Define the request payload
        Map<String, String> payload = new HashMap<>();
        payload.put("filter", "byUser");
        payload.put("add_user_info", "true");

        // Call the agendaList method and get the response
        Response response = agendaList(payload, token);

        // Assert the response status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch!");
        WriteLogs.info("Response  status code is 200");

        
    }
}
