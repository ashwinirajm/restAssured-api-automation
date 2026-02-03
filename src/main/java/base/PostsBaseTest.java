package base;

import config.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;

public class PostsBaseTest extends BaseTest{

    @BeforeClass
    public void setUpPosts() {
        RestAssured.baseURI = ConfigManager.get("posts.base.url");

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }
}
