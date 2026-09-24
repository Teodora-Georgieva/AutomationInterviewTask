package framework.api;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class ApiClient {
    private final RequestSpecification requestSpecification;

    public ApiClient(String baseUrl) {
        requestSpecification = given()
                .baseUri(baseUrl)
                .contentType("application/json");
    }

    public Response getByQueryParam(String endpoint, Map<String, ?> queryParams) {
        log.info("GET {} with query parameters: {}", endpoint, queryParams);
        return requestSpecification
                .queryParams(queryParams)
                .when()
                .get(endpoint);
    }

    public Response getByPathParam(String endpoint, String parameterName, Object parameterValue) {
        log.info("GET {} with path parameter: {}={}", endpoint, parameterName, parameterValue);
        return requestSpecification
                .pathParam(parameterName, parameterValue)
                .when()
                .get(endpoint);
    }

    public Response post(String endpoint, Object body) {
        log.info("POST {}", endpoint);
        Response response = requestSpecification
                .body(body)
                .when()
                .post(endpoint);

        log.info("Response status: {}", response.getStatusCode());
        return response;
    }

    public Response put(String endpoint, Object body) {
        log.info("PUT {}", endpoint);
        Response response =  requestSpecification
                .body(body)
                .when()
                .put(endpoint);

        log.info("Response status: {}", response.getStatusCode());
        return response;
    }

    public Response patchByPathParam(
            String endpoint,
            String parameterName,
            Object parameterValue,
            Object body,
            String token) {

        log.info("PATCH {} with path parameter: {}={}", endpoint, parameterName, parameterValue);

        return requestSpecification
                .pathParam(parameterName, parameterValue)
                .cookie("token", token)
                .body(body)
                .when()
                .patch(endpoint);
    }

    public Response delete(String endpoint) {
        log.info("DELETE {}", endpoint);
        Response response =  requestSpecification
                .when()
                .delete(endpoint);

        log.info("Response status: {}", response.getStatusCode());
        return response;
    }
}