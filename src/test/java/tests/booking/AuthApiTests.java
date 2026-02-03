package tests.booking;

import base.BookingBaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.auth.request.AuthRequest;
import pojo.auth.response.AuthResponse;
import pojo.reqres.response.AuthErrorResponse;

import static io.restassured.RestAssured.given;

@Epic("Phase 3 - Restful Booker")
@Feature("Authentication")
public class AuthApiTests extends BookingBaseTest {

    @Test
    @Story("Create Token - valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void createTokenSuccessTest() {
        AuthRequest request = new AuthRequest("admin", "password123");

        Response response = given()
                .body(request)
                .when()
                .post("/auth");

        Assert.assertEquals(response.statusCode(), 200);

        AuthResponse authResponse = (response.as(AuthResponse.class));

        String token = authResponse.getToken();

        Assert.assertNotNull(token);
        System.out.println(token);
    }

    @Test
    @Story("Create token - missing credentials")
    @Severity(SeverityLevel.NORMAL)
    public void createTokenMissingPasswordTest() {

        AuthRequest request = new AuthRequest("admin", null);

        Response response = given()
                .body(request)
                .when()
                .post("/auth");

        Assert.assertEquals(response.statusCode(),200);

        AuthErrorResponse errorResponse = response.as(AuthErrorResponse.class);

        Assert.assertEquals(errorResponse.getReason(),"Bad credentials");
    }


    @Test
    @Story("Update booking with basic auth")
    @Severity(SeverityLevel.BLOCKER)
    public void updateBookingWithBasicAuth() {

        Response response = given()
                .body("{\n" +
                        "    \"firstname\": \"Ash\",\n" +
                        "    \"lastname\": \"Raj\",\n" +
                        "    \"totalprice\": 100,\n" +
                        "    \"depositpaid\": true,\n" +
                        "    \"bookingdates\": {\n" +
                        "        \"checkin\": \"2024-01-01\",\n" +
                        "        \"checkout\": \"2024-01-05\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\": \"Breakfast\"\n" +
                        "}")
                .when()
                .put("/booking/1");

        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    @Story("Update booking with token auth")
    @Severity(SeverityLevel.BLOCKER)
    public void updateBookingWithTokenTest() {

        String token =
                given()
                        .body(new AuthRequest("admin", "password123"))
                        .when()
                        .post("/auth")
                        .then()
                        .extract()
                        .path("token");

        Response response =
                given()
                        .cookie("token", token)
                        .body("{\n" +
                                "    \"firstname\": \"Ash\",\n" +
                                "    \"lastname\": \"Raj\",\n" +
                                "    \"totalprice\": 100,\n" +
                                "    \"depositpaid\": true,\n" +
                                "    \"bookingdates\": {\n" +
                                "        \"checkin\": \"2024-01-01\",\n" +
                                "        \"checkout\": \"2024-01-05\"\n" +
                                "    },\n" +
                                "    \"additionalneeds\": \"Breakfast\"\n" +
                                "}")
                        .when()
                        .put("/booking/1");

        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test
    @Story("Update booking without auth")
    @Severity(SeverityLevel.CRITICAL)
    public void updateBookingWithoutAuthTest() {

        Response response =
                given()
                        .auth().none()
                        .body("{\"firstname\":\"Ash\",\"lastname\":\"Raj\"}")
                        .when()
                        .put("/booking/1");

        Assert.assertEquals(response.statusCode(), 403);
    }

    @Test
    @Story("Update booking with invalid token")
    @Severity(SeverityLevel.CRITICAL)
    public void updateBookingWithInvalidTokenTest() {

        Response response =
                given()
                        .auth().none()
                        .cookie("token", "invalid_token")
                        .body("{\n" +
                                "    \"firstname\": \"Ash\",\n" +
                                "    \"lastname\": \"Raj\",\n" +
                                "    \"totalprice\": 100,\n" +
                                "    \"depositpaid\": true,\n" +
                                "    \"bookingdates\": {\n" +
                                "        \"checkin\": \"2024-01-01\",\n" +
                                "        \"checkout\": \"2024-01-05\"\n" +
                                "    },\n" +
                                "    \"additionalneeds\": \"Breakfast\"\n" +
                                "}")
                        .when()
                        .put("/booking/1");

        Assert.assertEquals(response.statusCode(), 403);
    }
}
