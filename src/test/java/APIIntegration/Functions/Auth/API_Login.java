package APIIntegration.Functions.Auth;

import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.PropertiesHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class API_Login {

//    @BeforeClass
//    public void setup() {
//        // Set the endpoint URL
//        RestAssured.baseURI = PropertiesHelper.getValue("ENDPOINT_AUTH_LOGIN"); // Load it dynamically based on the environment
//    }

    public Response authLogin(Map<String, String> payload) { //This method will dynamically handle payloads (even with missing parameters).
        // Ensure the payload is serialized and sent as JSON
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload) // RestAssured automatically serializes the map to JSON
                .post(PropertiesHelper.getValue("ENDPOINT_AUTH_LOGIN"));
    }


    public void authLoginSuccess(String email, String password) {
        // Define the request payload
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        // Call the login method and get the response
        Response response = authLogin(payload);

        // Assert the response status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch!");
        WriteLogs.info("Response  status code is 200");

        // Assert the response contains a token
        String token = response.jsonPath().getString("data.token");
        Assert.assertNotNull(token, "Token is not present in the response!");
        WriteLogs.info("Token is present in the response");

        // Validate that the response's "email" field matches the request email
        String responseEmail = response.jsonPath().getString("data.user.email");
        Assert.assertEquals(responseEmail, email, "Email in the response does not match the email in the request!");
        WriteLogs.info("Email in the response matches the email in the request");
        // Log specific details only
        WriteLogs.info("Login successful. Status code: " + response.getStatusCode() + ", Token: " + token + ", Email: " + responseEmail);
    }

    // ✅ Function to log in and return a token (to use in other tests)
    public Response authenticateUser(String loginEndpoint, String email, String password) {
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);  // Fetch email from Excel
        payload.put("password", password); // Fetch password from Excel

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .post(loginEndpoint);

        // ✅ Validate login success
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Login failed! Status: " + response.getStatusCode());
        }

        // Return Response
        return response;
    }

    // Determine whether the account is an admin or a user
    public String verifyAccountType(Response response) {
        // Extract the platform value to determine account type
        Integer platform = response.jsonPath().getInt("data.user.platform");

        // Check if platform is 0 (admin) or 1 (user)
        if (platform == 0) {
            return "admin";  // If platform is 0, it's an admin
        } else if (platform == 1) {
            return "user";  // If platform is 1, it's a regular user
        } else {
            throw new RuntimeException("Unexpected platform value: " + platform);
        }
    }

    //Assert User is OwnerRole
    public void assertUserIsAdmin(Response response) {
        // Extract the platform value from the response
        Integer platform = response.jsonPath().getInt("data.user.platform");

        // Assert that the platform is 0, which indicates an admin
        Assert.assertEquals(platform, Integer.valueOf(0), "User is not an admin! Platform value is: " + platform);
        WriteLogs.info("✅ User is an admin.");
    }

    // Assert User is Regular User
    public void assertUserIsRegularUser(Response response) {
        // Extract the platform value from the response
        Integer platform = response.jsonPath().getInt("data.user.platform");

        // Assert that the platform is 1, which indicates a regular user
        Assert.assertEquals(platform, Integer.valueOf(1), "User is not a regular user! Platform value is: " + platform);
        WriteLogs.info("✅ User is a regular user.");
    }


    public void authLoginFailDueToBlankEmail(String email, String password) {
        // Define the request payload
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        // Call the login method and get the response
        Response response = authLogin(payload);

        // Assert the response status code is 404
        Assert.assertEquals(response.getStatusCode(), 404, "Status code mismatch!");
        WriteLogs.info("Response  status code is 404");

        // Assert the response contains an error message
        String errorMessage = response.jsonPath().getString("message.email");
        Assert.assertNotNull(errorMessage, "Error message is not present in the response!");
        Assert.assertTrue(errorMessage.contains("email field is required"), "Error message does not contain the expected text: 'email field is required'");
        WriteLogs.info("Error message is correct in the response");

        // Log specific details only
        WriteLogs.info("Login failed. Status code: " + response.getStatusCode() + ", Error message: " + errorMessage);
    }

    //To test scenarios where you do not send a parameter at all (e.g., email or password)
    public void authLoginFailDueToMissingEmail(String password) {
        // Define the payload without the "email" field
        Map<String, String> payload = new HashMap<>();
        payload.put("password", password);

        // Call the login method and get the response
        Response response = authLogin(payload);

        // Assert the response status code
        Assert.assertEquals(response.getStatusCode(), 404, "Status code mismatch!");
        WriteLogs.info("Response  status code is 404");

        // Assert the response contains an error message for "email"
        String errorMessage = response.jsonPath().getString("message.email");
        Assert.assertNotNull(errorMessage, "Error message for 'email' is not present in the response!");
        Assert.assertTrue(errorMessage.contains("email field is required"), "Error message does not contain the expected text: 'email field is required'");
        WriteLogs.info("Error message for 'email' is correct in the response");

        // Log specific details
        WriteLogs.info("Login failed due to missing email. Status code: " + response.getStatusCode() + ", Error message: " + errorMessage);
    }

    public void authLoginFailDueToBlankPassword(String email, String password) {
        // Define the request payload
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        // Call the login method and get the response
        Response response = authLogin(payload);

        // Assert the response status code is 404
        Assert.assertEquals(response.getStatusCode(), 404, "Status code mismatch!");
        WriteLogs.info("Response  status code is 404");

        // Assert the response contains an error message
        String errorMessage = response.jsonPath().getString("message.password");
        Assert.assertNotNull(errorMessage, "Error message is not present in the response!");
        Assert.assertTrue(errorMessage.contains("password field is required"), "Error message does not contain the expected text: 'password field is required'");
        WriteLogs.info("Error message is correct in the response");

        // Log specific details only
        WriteLogs.info("Login failed. Status code: " + response.getStatusCode() + ", Error message: " + errorMessage);
    }

    //To test scenarios where you do not send a parameter at all (e.g., email or password)
    public void authLoginFailDueToMissingPassword(String email) {
        // Define the payload without the "password" field
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);

        // Call the login method and get the response
        Response response = authLogin(payload);

        // Assert the response status code
        Assert.assertEquals(response.getStatusCode(), 404, "Status code mismatch!");
        WriteLogs.info("Response  status code is 404");

        // Assert the response contains an error message for "password"
        String errorMessage = response.jsonPath().getString("message.password");
        Assert.assertNotNull(errorMessage, "Error message for 'password' is not present in the response!");
        Assert.assertTrue(errorMessage.contains("password field is required"), "Error message does not contain the expected text: 'password field is required'");
        WriteLogs.info("Error message for 'password' is correct in the response");

        // Log specific details
        WriteLogs.info("Login failed due to missing password. Status code: " + response.getStatusCode() + ", Error message: " + errorMessage);
    }

    public void authLoginFailDueToWrongEmail(String email, String password) {
        // Define the request payload
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        // Call the login method and get the response
        Response response = authLogin(payload);

        // Assert the response status code is 422
        Assert.assertEquals(response.getStatusCode(), 422, "Status code mismatch!");
        WriteLogs.info("Response  status code is 422");

        // Assert the response contains an error message
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertNotNull(errorMessage, "Error message is not present in the response!");
        Assert.assertTrue(errorMessage.contains("email or password you have entered is not correct"), "Error message does not contain the expected text: 'email address or password you have entered was not correct'");
        WriteLogs.info("Error message is correct in the response");

        // Log specific details only
        WriteLogs.info("Login failed. Status code: " + response.getStatusCode() + ", Error message: " + errorMessage);
    }

    public void authLoginFailDueToWrongPassword(String email, String password) {
        // Define the request payload
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        // Call the login method and get the response
        Response response = authLogin(payload);

        // Assert the response status code is 422
        Assert.assertEquals(response.getStatusCode(), 422, "Status code mismatch!");
        WriteLogs.info("Response  status code is 422");

        // Assert the response contains an error message
        String errorMessage = response.jsonPath().getString("message");
        Assert.assertNotNull(errorMessage, "Error message is not present in the response!");
        Assert.assertTrue(errorMessage.contains("email or password you have entered is not correct"), "Error message does not contain the expected text: 'email address or password you have entered was not correct'");
        WriteLogs.info("Error message is correct in the response");

        // Log specific details only
        WriteLogs.info("Login failed. Status code: " + response.getStatusCode() + ", Error message: " + errorMessage);
    }
}
