package tests.jsonPlaceholder;


import base.PostsBaseTest;
import io.qameta.allure.*;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.posts.request.CreatePostRequest;
import pojo.posts.request.UpdatePostRequest;
import pojo.posts.response.PostResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Phase 2 - JSONPlaceholder")
@Feature("Post API")
public class PostApiTests extends PostsBaseTest {

    @Test
    @Story("Get all posts")
    @Severity(SeverityLevel.NORMAL)
    public void getAllPostsTest() {

        Response response =
                given()
                        .when()
                        .get("/posts");

        response.then().statusCode(200);

        List<PostResponse> posts =
                response.as(new TypeRef<List<PostResponse>>() {});

        Assert.assertFalse(posts.isEmpty(), "Posts list should not be empty");
    }


    @Test
    @Story("Create Post")
    @Severity(SeverityLevel.CRITICAL)
    public void createPostTest() {
        CreatePostRequest request = new CreatePostRequest(1, "Api Automation", "Learning JSONPlaceholder");
        PostResponse response = given()
                .body(request)
                .when()
                .post("/posts")
                .as(PostResponse.class);

        Assert.assertEquals(response.getUserId(),1);
        Assert.assertEquals(response.getTitle(), "Api Automation");
    }


    @Test
    @Story("Update Post")
    @Severity(SeverityLevel.CRITICAL)
    public void updatePostTest() {

        UpdatePostRequest request =
                new UpdatePostRequest(1, "Updated Title", "Updated Body");

        PostResponse response =
                given()
                        .body(request)
                        .when()
                        .put("/posts/1")
                        .as(PostResponse.class);

        Assert.assertEquals(response.getTitle(), "Updated Title");
    }

    @Test
    @Story("Delete Post")
    @Severity(SeverityLevel.NORMAL)
    public void deletePostTest() {

        Response response =
                given()
                        .when()
                        .delete("/posts/1");

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    @Story("Get post by id")
    @Severity(SeverityLevel.NORMAL)
    public void getPostByIdTest() {

        Response response =
                given()
                        .when()
                        .get("/posts/2");

        response.then().statusCode(200);

        PostResponse post = response.as(PostResponse.class);

        Assert.assertEquals(post.getId(), 2);
        Assert.assertEquals(post.getUserId(), 1);
        Assert.assertNotNull(post.getTitle(), "Title should not be null");
        Assert.assertNotNull(post.getBody(), "Body should not be null");
    }

    @Test
    @Story("Get posts by userId")
    @Severity(SeverityLevel.NORMAL)
    public void getPostsByUserIdTest() {

     List <PostResponse> posts =
                given()
                        .queryParam("userId", 3)
                        .when()
                        .get("/posts")
                                .as(new TypeRef<List<PostResponse>>() {});

        Assert.assertFalse(posts.isEmpty(), "Posts list should not be empty");

        posts.forEach(post -> Assert.assertEquals(post.getUserId(),3));
    }

}
