package framework.api;

import framework.models.BookingRequest;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class BookingApi {
    private final ApiClient apiClient;

    public BookingApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Response createBooking(BookingRequest request) {
        log.info("Creating booking");
        return apiClient.post("/booking", request);
    }

    public Response getBookingById(int id) {
        log.info("Getting booking by id: {}", id);
        return apiClient.getByPathParam("/booking/{id}", "id", id);
    }

    public Response partialUpdateById(int id, String fieldName, String fieldValue, String token) {
        Map<String, String> reqBody = Map.of('\"' + fieldName + '\"', fieldValue);
        return apiClient.patchByPathParam("/booking/{id}", "id", id, reqBody, token);
    }
}