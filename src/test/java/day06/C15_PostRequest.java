package day06;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static utilities.BaseUrl.createBooking;
import static io.restassured.RestAssured.given;
import static utilities.ApiCalls.createBookingData;
import static utilities.TestData.setUpBookingData;

public class C15_PostRequest {


   @Test
    public void createBookingTest(){
       // Expected Data
       JSONObject expectedRequestData = setUpBookingData() ;
       //Request and Response
       Response response =given().contentType("application/json")
               .auth()
               .basic("admin","password123")
               .body(expectedRequestData.toString()) // if you are using JSONObject we should add .toString()
               .when()
               .post(createBooking());
       response.prettyPrint() ;

       // Verify ( did we create data or not)
       JsonPath actualData = response.jsonPath();
       Assert.assertEquals(expectedRequestData.getString("firstname"),actualData.getString("booking.firstname"));
       Assert.assertEquals(expectedRequestData.getString("lastname"),actualData.getString("booking.lastname"));
       Assert.assertEquals(expectedRequestData.getInt("totalprice"),actualData.getInt("booking.totalprice"));
       Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),actualData.getBoolean("booking.depositpaid"));
       Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),actualData.getString("booking.bookingdates.checkin"));
       Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),actualData.getString("booking.bookingdates.checkout"));

   }


/*
{
   {
    "bookingid": 332,
    "booking": {
        "firstname": "Mehmet",
        "lastname": "Sezgin",
        "totalprice": 1250,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2023-03-10",
            "checkout": "2023-03-23"
        }
    }
}

 */
   @Test
   public void createBookingTest01(){
      createBookingData(200,"Erva","Sezgin",500,
              true,"2023-03-02","2023-03-05") ;
   }

   @Test
   public void createBookingTest02(){
      createBookingData(200,"Mustafa","Tokat",500,
              true,"2023-03-02","2023-03-05") ;
   }

}
