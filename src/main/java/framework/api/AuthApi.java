package framework.api;

import io.restassured.response.Response;
import framework.models.AuthRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuthApi {
    private final ApiClient apiClient;

    public AuthApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Response createToken(AuthRequest request) {
        log.info("Creating authentication token");
        return apiClient.post("/auth", request);
    }
}