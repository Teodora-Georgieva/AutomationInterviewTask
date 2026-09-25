package framework.models;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BookingRequest {
    private String firstname;
    private String lastname;
    private BigDecimal totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}