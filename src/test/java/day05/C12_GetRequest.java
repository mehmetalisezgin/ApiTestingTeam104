package day05;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static utilities.BaseUrl.bookingUserID;
import static io.restassured.RestAssured.given;
import static utilities.ApiCalls.deSerializationMethod;
import static utilities.TestData.getBookingData;

public class C12_GetRequest {

    @Test
    public void bookingTest() {
        //Test Data from HasMap method
        HashMap<String, Object> expectedDataMap = getBookingData();

        // Request and Response
        Response response = given().when().get(bookingUserID(7));

        //Convert data from Json to Java  ===>> De-Serialization
        // Convert data from Java to Json ===>> Serialization
        // here Data will be converted to the Map by De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class) ;// we did de-serialization

        // Verify
        Assert.assertEquals(expectedDataMap.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(expectedDataMap.get("checkin"),actualData.get("checkin"));
        Assert.assertEquals(expectedDataMap.get("checkout"),actualData.get("checkout"));
    }
/*
{
  {
    "firstname": "Eric",
    "lastname": "Jackson",
    "totalprice": 224,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2021-04-17",
        "checkout": "2022-10-02"
    }
}
 */

    @Test
    public void bookingId10(){
        deSerializationMethod(10,200,"Eric","Jackson",224.0,
                false,"2021-04-17","2022-10-02");
    }





}
