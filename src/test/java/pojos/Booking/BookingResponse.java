package pojos.Booking;

public class BookingResponse {

    /*
    {
    "firstname" : "Omer",
    "lastname" : "Can",
    "totalprice" : 254,
    "depositpaid" : false,
    "bookingdates" : {
        "checkin" : "2020-02-03",
        "checkout" : "2020-02-10"
    },
    "additionalneeds" : "Breakfast"
}
     */

    private BookingPojo bookingPojo ;
    private BookingDates bookingDates ;
    private String additionalneeds ;

    public BookingResponse() {
    }

    public BookingResponse(BookingPojo bookingPojo, BookingDates bookingDates, String additionalneeds) {
        this.bookingPojo = bookingPojo;
        this.bookingDates = bookingDates;
        this.additionalneeds = additionalneeds;
    }

    public BookingPojo getBookingPojo() {
        return bookingPojo;
    }

    public void setBookingPojo(BookingPojo bookingPojo) {
        this.bookingPojo = bookingPojo;
    }

    public BookingDates getBookingDates() {
        return bookingDates;
    }

    public void setBookingDates(BookingDates bookingDates) {
        this.bookingDates = bookingDates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "bookingPojo=" + bookingPojo +
                ", bookingDates=" + bookingDates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}
