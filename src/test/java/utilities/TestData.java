package utilities;

import org.json.JSONObject;

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





    //JSONObject test data
    public static JSONObject getRequestData(){
        JSONObject  bookingdates = new JSONObject() ;
        bookingdates.put("checkin","2017-07-10");
        bookingdates.put("checkout","2022-08-23");

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname","Jim");
        expectedData.put("lastname", "Jones");
        expectedData.put("totalprice", 741);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates",bookingdates );
        return expectedData;
    }


    public static JSONObject setUpBookingData(){
        JSONObject  bookingdates = new JSONObject() ;
        bookingdates.put("checkin","2023-03-10");
        bookingdates.put("checkout","2023-03-23");

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname","Mehmet");
        expectedData.put("lastname", "Sezgin");
        expectedData.put("totalprice", 1250);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates",bookingdates );
        return expectedData;
    }


}
