package tests.jsonPlaceholder;

import base.PostsBaseTest;
import io.qameta.allure.*;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.posts.response.CommentResponse;
import pojo.posts.response.PostResponse;
import pojo.posts.response.UserResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Phase 2 - JSONPlaceholder")
@Feature("User → Post → Comment Relationship")
public class RelationshipApiTests extends PostsBaseTest {

    @Test
    @Story("User → Post → Comment relationship")
    @Severity(SeverityLevel.CRITICAL)
    public void userPostCommentRelationshipTest() {

        // 1️⃣ Get User
        UserResponse user =
                given()
                        .when()
                        .get("/users/1")
                        .as(UserResponse.class);

        Assert.assertEquals(user.getId(), 1);

        // 2️⃣ Get Posts for User
        List<PostResponse> posts =
                given()
                        .queryParam("userId", user.getId())
                        .when()
                        .get("/posts")
                        .as(new TypeRef<List<PostResponse>>() {});

        Assert.assertFalse(posts.isEmpty(), "User should have posts");

        PostResponse post = posts.get(0);

        // 3️⃣ Validate Post belongs to User
        Assert.assertEquals(post.getUserId(), user.getId());

        // 4️⃣ Get Comments for Post
        List<CommentResponse> comments =
                given()
                        .queryParam("postId", post.getId())
                        .when()
                        .get("/comments")
                        .as(new TypeRef<List<CommentResponse>>() {});

        Assert.assertFalse(comments.isEmpty(), "Post should have comments");

        // 5️⃣ Validate all comments belong to the post
        comments.forEach(comment ->
                Assert.assertEquals(comment.getPostId(), post.getId())
        );
    }

}
