package day06;

import org.junit.Test;

import static utilities.ApiCalls.createBookingDataUsingAuth;

public class C16_PostRequest {

    @Test
    public void createBookingTest05(){
        createBookingDataUsingAuth(200,"ErvaNaz","Sezgin",500,
                true,"2023-03-02","2023-03-05");
    }





}
