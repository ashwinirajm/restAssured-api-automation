package base;

import config.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;

public class ReqResBaseTest extends BaseTest{

    @BeforeClass
    public void setupReqRes() {
        RestAssured.baseURI = ConfigManager.get("reqres.base.url");

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", ConfigManager.get("reqres.api.key"))
                .addFilter(new AllureRestAssured())
                .build();
    }
}
