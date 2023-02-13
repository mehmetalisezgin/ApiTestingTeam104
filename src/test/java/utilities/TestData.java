package utilities;

import java.util.HashMap;

public class TestData {
    /*
{
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 745,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2017-04-07",
        "checkout": "2019-02-18"
    },
    "additionalneeds": "Breakfast"
}
 */
    public static HashMap<String,Object> getBookingData(){

     HashMap<String , Object> bookingdates = new HashMap<>();
     bookingdates.put("checkin","2017-04-07");
     bookingdates.put("checkout","2019-02-18");

     HashMap<String , Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Sally");
        expectedData.put("lastname", "Jackson");
        expectedData.put("totalprice", 923.0);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates",bookingdates );
        return expectedData;
    }






}
