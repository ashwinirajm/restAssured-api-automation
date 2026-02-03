package base;

import config.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;

public class BookingBaseTest extends BaseTest{

    @BeforeClass
    public void setupBooking() {
        RestAssured.baseURI = ConfigManager.get("booking.base.url");

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-type", "application/json")
                .setAuth(RestAssured
                        .preemptive()
                        .basic(ConfigManager.get("booking.username"), ConfigManager.get("booking.password")))
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();

    }
}
