package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RequestSpecBuilderUtil {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = RequestSpecBuilderUtil.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private RequestSpecBuilderUtil() {
        // prevent object creation
    }

    public static RequestSpecification getRequestSpec(String baseUrl) {
        return buildSpec(baseUrl, null);
    }

    public static RequestSpecification getRequestSpec(String baseUrl,String apiKey) {
        return buildSpec(baseUrl, apiKey);
    }

    private static RequestSpecification buildSpec(String baseUrl, String apiKey) {

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("User-Agent", "RestAssured-API-Tests")
                .addFilter(new AllureRestAssured())
                .log(io.restassured.filter.log.LogDetail.ALL);

        if (apiKey != null && !apiKey.trim().isEmpty()) {
            builder.addHeader("X-API-Key", apiKey);
        }

        return builder.build();
    }
}
