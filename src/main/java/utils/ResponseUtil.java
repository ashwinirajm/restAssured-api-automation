package utils;

import io.restassured.response.Response;

public class ResponseUtil {

    private ResponseUtil() {
        // prevent instantiation
    }

    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    public static String getResponseAsString(Response response) {
        return response.asString();
    }

    public static <T> T getResponseAs(Response response, Class<T> clazz) {
        return response.as(clazz);
    }

    public static String getValueFromJson(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }
}
