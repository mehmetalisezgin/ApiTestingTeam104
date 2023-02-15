package day07;

import org.junit.Test;

import static utilities.ApiCalls.createBookingDataHashMap;
import static utilities.ApiCalls.createBookingDataUsingAuth;

public class C17_PostRequest {





    @Test
    public void createBookingTest05(){
        createBookingDataHashMap(200,"Omer","Hasan",500,
                true,"2023-04-02","2023-04-05");
    }



}
