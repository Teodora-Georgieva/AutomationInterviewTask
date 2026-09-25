package tests.stepdefinitions.api;

import framework.models.BookingDates;
import framework.models.BookingRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import tests.context.TestContext;
import io.restassured.response.Response;

import java.math.BigDecimal;
import java.util.Map;

@Log4j2
public class BookingSteps {
    private final TestContext context;

    public BookingSteps(TestContext context) {
        this.context = context;
    }

    @Given("I prepare booking details")
    public void prepareRequest(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        BookingDates bookingDates = BookingDates.builder()
                .checkin(data.get("checkin"))
                .checkout(data.get("checkout"))
                .build();

        BookingRequest bookingRequest = BookingRequest.builder()
                .firstname(data.get("firstname"))
                .lastname(data.get("lastname"))
                .totalprice(new BigDecimal(data.get("totalprice")))
                .depositpaid(Boolean.parseBoolean(data.get("depositpaid")))
                .bookingdates(bookingDates)
                .additionalneeds(data.get("additionalneeds"))
                .build();

        log.info("bookingRequest: {}", bookingRequest);
        context.setBookingRequest(bookingRequest);
    }

    @When("I create the booking")
    public void createBooking() {
        Response response = context.getBookingApi().createBooking(context.getBookingRequest());
        context.setResponse(response);
        if (response.getStatusCode() == 200) {
            context.setBookingId(response.jsonPath().getInt("bookingid"));
        }
    }

    @Then("the response status code should be {int}")
    public void verifyCreateBookingResponseStatusCode(int expectedStatusCode) {
        Assert.assertEquals(context.getResponse().getStatusCode(), expectedStatusCode);
    }

    @Then("the response should contain the created booking")
    public void verifyCreatedBookingReturnedInResponse() {
        BookingRequest bookingRequest = context.getBookingRequest();

        String firstname = context.getResponse().jsonPath().getString("booking.firstname");
        String lastname = context.getResponse().jsonPath().getString("booking.lastname");
        String checkinDate = context.getResponse().jsonPath().getString("booking.bookingdates.checkin");

        Assert.assertEquals(firstname, bookingRequest.getFirstname());
        Assert.assertEquals(lastname, bookingRequest.getLastname());
        Assert.assertEquals(checkinDate, bookingRequest.getBookingdates().getCheckin());
    }

    @Given("I prepare an invalid booking payload with missing firstname")
    public void prepareInvalidRequest(DataTable dataTable) {
        prepareRequest(dataTable);
    }

    @When("I get the created booking")
    public void getBookingById() {
        Response response = context.getBookingApi().getBookingById(context.getBookingId());
        context.setResponse(response);
    }

    @Then("the response should contain the retrieved booking")
    public void verifyRetrievedBookingReturnedInResponse() {
        BookingRequest bookingRequest = context.getBookingRequest();

        String firstname = context.getResponse().jsonPath().getString("firstname");
        String lastname = context.getResponse().jsonPath().getString("lastname");
        String checkinDate = context.getResponse().jsonPath().getString("bookingdates.checkin");

        Assert.assertEquals(firstname, bookingRequest.getFirstname());
        Assert.assertEquals(lastname, bookingRequest.getLastname());
        Assert.assertEquals(checkinDate, bookingRequest.getBookingdates().getCheckin());
    }

    @When("I update the booking {string} to {string}")
    public void updateField(String fieldName, String fieldValue) {
        String token = context.getToken();
        Response response = context.getBookingApi().partialUpdateById(context.getBookingId(), fieldName, fieldValue, token);
        context.setResponse(response);
    }

    @Then("the response should contain {string} {string}")
    public void verifyUpdatedEntity(String fieldName, String expectedFieldValue) {
        String actualFieldValue = context.getResponse().getBody().jsonPath().getString(fieldName);
        Assert.assertEquals(actualFieldValue, expectedFieldValue);
    }
}