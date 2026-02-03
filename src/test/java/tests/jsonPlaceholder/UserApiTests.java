package tests.jsonPlaceholder;

import base.PostsBaseTest;
import io.qameta.allure.*;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.posts.response.UserResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Phase 2 - JSONPlaceholder")
@Feature("Users APIs")
public class UserApiTests extends PostsBaseTest {

    @Test
    @Story("Get All Users")
    @Severity(SeverityLevel.NORMAL)
    public void getAllUsersTest() {

        Response response =
                given()
                        .when()
                        .get("/users");

        Assert.assertEquals(response.statusCode(), 200);

        List<UserResponse> users =
                response.as(new TypeRef<List<UserResponse>>() {});

        Assert.assertFalse(users.isEmpty(), "Users list should not be empty");
    }

    @Test
    @Story("Get Single User")
    @Severity(SeverityLevel.NORMAL)
    public void getSingleUserTest() {

        UserResponse user =
                given()
                        .when()
                        .get("/users/1")
                        .as(UserResponse.class);

        Assert.assertEquals(user.getId(), 1);
        Assert.assertNotNull(user.getEmail());
        Assert.assertNotNull(user.getCompany());
    }
}

