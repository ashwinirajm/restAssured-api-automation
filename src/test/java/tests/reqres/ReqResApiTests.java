package tests.reqres;

import base.ReqResBaseTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.reqres.request.*;

import pojo.reqres.response.*;
import utils.ResponseUtil;

import static io.restassured.RestAssured.given;


import io.qameta.allure.*;

@Epic("Phase 1 - ReqRes API")
@Feature("Authentication APIs")
public class ReqResApiTests extends ReqResBaseTest {

    // 1️⃣ List Users
    @Test
    @Story("List Users")
    @Severity(SeverityLevel.NORMAL)
    public void listUsersTest() {
        Response response =
                given()
                        .when()
                        .get("/api/users?page=2");

        Assert.assertEquals(ResponseUtil.getStatusCode(response), 500);
        Assert.assertNotNull(ResponseUtil.getValueFromJson(response, "data"));
    }

    // 2️⃣ Single User
    @Test
    @Story("List Single User")
    @Severity(SeverityLevel.NORMAL)
    public void singleUserTest() {
        Response response =
                given()
                        .when()
                        .get("/api/users/2");

        SingleUserResponse user = response.as(SingleUserResponse.class);

        Assert.assertEquals(ResponseUtil.getStatusCode(response), 200);
        Assert.assertEquals(user.getData().getId(), 2);
    }

    // 3️⃣ Create User
    @Test
    @Story("Create User")
    @Severity(SeverityLevel.CRITICAL)
    public void createUserTest() {
        CreateUserRequest request =
                new CreateUserRequest("Ashwini", "Software QA Engineer");

        Response response =
                given()
                        .body(request)
                        .when()
                        .post("/api/users");

        CreateUserResponse createResponse = response.as(CreateUserResponse.class);

        Assert.assertEquals(ResponseUtil.getStatusCode(response), 201);
        Assert.assertEquals(createResponse.getName(),"Ashwini");
        Assert.assertNotNull(createResponse.getId());
    }

    // 4️⃣ Update User
    @Test
    @Story("Update User")
    @Severity(SeverityLevel.CRITICAL)
    public void updateUserTest() {
        UpdateUserRequest request =
                new UpdateUserRequest("Ashwini", "Senior s/w QA");

        Response response =
                given()
                        .body(request)
                        .when()
                        .put("/api/users/2");

        UpdateUserResponse updateResponse = response.as(UpdateUserResponse.class);

        Assert.assertEquals(ResponseUtil.getStatusCode(response), 200);
        Assert.assertEquals(updateResponse.getJob(), "Senior s/w QA");
        Assert.assertNotNull(updateResponse.getUpdatedAt());
    }

    // 5️⃣ Delete User
    @Test
    @Story("Delete User")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteUserTest() {
        Response response =
                given()
                        .when()
                        .delete("/api/users/2");

        Assert.assertEquals(ResponseUtil.getStatusCode(response), 204);
    }

    // 6️⃣ Register Successful
    @Test
    @Story("Register User")
    @Severity(SeverityLevel.TRIVIAL)
    public void registerUserTest() {
        RegisterRequest request =
                new RegisterRequest("eve.holt@reqres.in", "pistol");

        Response response =
                given()
                        .body(request)
                        .when()
                        .post("/api/register");

        AuthSuccessResponse registerResponse = response.as(AuthSuccessResponse.class);
        Assert.assertEquals(ResponseUtil.getStatusCode(response), 200);
        Assert.assertNotNull(registerResponse.getToken());
    }

    // 7️⃣ Login Successful
    @Test
    @Story("Login Successful")
    @Severity(SeverityLevel.BLOCKER)
    public void loginSuccessUserTest() {
        LoginRequest request =
                new LoginRequest("eve.holt@reqres.in", "cityslicka");

        Response response =
                given()
                        .body(request)
                        .when()
                        .post("/api/login");

        AuthSuccessResponse loginResponse = response.as(AuthSuccessResponse.class);

        Assert.assertEquals(ResponseUtil.getStatusCode(response), 200);
        Assert.assertNotNull(loginResponse.getToken());
    }

    // 8 Login Failure
    @Test
    @Story("Login Failure")
    @Severity(SeverityLevel.TRIVIAL)
    public void loginFailureUserTest() {
        LoginFailureRequest request =
                new LoginFailureRequest("eve.holt@reqres.in");

        Response response =
                given()
                        .body(request)
                        .when()
                        .post("/api/login");

        AuthErrorResponse errorResponse = response.as(AuthErrorResponse.class);

        Assert.assertEquals(ResponseUtil.getStatusCode(response), 400);
        Assert.assertEquals(errorResponse.getError(),"Missing password");
    }
}
