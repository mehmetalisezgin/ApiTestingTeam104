package pojos.Booking;

public class BookingDates {

    /*
     "bookingdates" : {
        "checkin" : "2020-02-03",
        "checkout" : "2020-02-10"
    },
     */

    // Create data from the body
    private String checkin ;
    private String checkout ;

    // Create without parameter constructor


    public BookingDates() {
    }

    // Create copnstructor with parameter


    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // Create Getter and Setter

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // Create toString

    @Override
    public String toString() {
        return "BookingDates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
