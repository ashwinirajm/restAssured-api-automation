package tests.booking;

import base.BookingBaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

@Epic("Phase 3 - Restful Booker")
@Feature("Booking API Schema Validation")
public class BookingSchemaTests extends BookingBaseTest {

    @Test
    @Story("Create Booking Schema Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void createBookingSchemaTest() {
        Response response =
                given()
                        .body("{ \"firstname\": \"Ashwini\", \"lastname\": \"Raj\", \"totalprice\": 100, \"depositpaid\": true, \"bookingdates\": { \"checkin\": \"2026-02-01\", \"checkout\": \"2026-02-05\" }, \"additionalneeds\": \"Breakfast\" }")
                        .when()
                        .post("/booking");

        assertEquals(response.getStatusCode(), 200);

        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/booking/createBooking.json"));
    }

    @Test
    @Story("Get Booking Schema Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void getBookingSchemaTest() {
        int bookingId = 1; // use an existing booking id

        Response response =
                given()
                        .when()
                        .get("/booking/" + bookingId);

        assertEquals(response.getStatusCode(), 200);

        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/booking/getBooking.json"));
    }
}
