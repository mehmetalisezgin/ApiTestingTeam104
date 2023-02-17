package day09;

import org.junit.Test;

import static utilities.ApiCalls.createBookingDataUsingAuth;
import static utilities.ApiCalls.createBookingWithPojo;

public class C23_PostRequestPojo {


    @Test
    public void createBooking(){
        createBookingWithPojo(200,"MehmetErva","Sezgin",320,true
        ,"2023-03-02","2023-03-05","Breakfast and more tea");
    }

    @Test
    public void createBookingTest05(){
        createBookingDataUsingAuth(200,"ErvaNaz","Sezgin",500,
                true,"2023-03-02","2023-03-05");
    }


}
