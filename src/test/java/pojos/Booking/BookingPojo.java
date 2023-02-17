package pojos.Booking;

public class BookingPojo {

    /*
       {
    "firstname" : "Omer",
    "lastname" : "Can",
    "totalprice" : 254,
    "depositpaid" : false,
}

     */

    private String firstname ;
    private String lastname ;
    private int totalprice ;
    private boolean depositpaid ;

    public BookingPojo() {
    }

    public BookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                '}';
    }







}
