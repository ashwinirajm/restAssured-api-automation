package tests.booking;

import base.BookingBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AuthSchemaTests extends BookingBaseTest {

        @Test
        public void validateCreateTokenSuccessSchema() {

            Response response =
                    given()
                            .body("{\n" +
                                    "  \"username\": \"admin\",\n" +
                                    "  \"password\": \"password123\"\n" +
                                    "}")
                            .when()
                            .post("/auth");

            response.then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath(
                            "schemas/auth/createTokenSuccess.json"));
        }

        @Test
        public void validateCreateTokenErrorSchema_MissingPassword() {

            Response response =
                    given()
                            .body("{\n" +
                                    "  \"username\": \"admin\"\n" +
                                    "}")
                            .when()
                            .post("/auth");

            response.then()
                    .statusCode(200)   // booking API returns 200 for auth errors
                    .body(matchesJsonSchemaInClasspath(
                            "schemas/auth/createTokenError.json"));
        }

        @Test
        public void validateCreateTokenErrorSchema_MissingUsername() {

            Response response =
                    given()
                            .body("{\n" +
                                    "  \"password\": \"password123\"\n" +
                                    "}")
                            .when()
                            .post("/auth");

            response.then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath(
                            "schemas/auth/createTokenError.json"));
        }

        @Test
        public void validateCreateTokenErrorSchema_EmptyBody() {

            Response response =
                    given()
                            .when()
                            .post("/auth");

            response.then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath(
                            "schemas/auth/createTokenError.json"));
        }
    }


