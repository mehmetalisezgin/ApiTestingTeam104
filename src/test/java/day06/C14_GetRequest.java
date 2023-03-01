package day06;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static utilities.BaseUrl.bookingUserID;
import static io.restassured.RestAssured.given;
import static utilities.ApiCalls.jsonPlaceHolderJSONObject;
import static utilities.TestData.getRequestData;

public class C14_GetRequest {


    @Test
    public void bookingId(){

        // ExpectedData
        JSONObject expectedData = getRequestData();
        // Request and Response
        Response response = given().when().get(bookingUserID(10));

        response.then().assertThat().statusCode(200) ;

        JsonPath actualData = response.jsonPath() ;

        Assert.assertEquals(expectedData.getString("firstname"),actualData.getString("firstname"));
        Assert.assertEquals(expectedData.getString("lastname"),actualData.getString("lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"),actualData.getInt("totalprice"));
    }

    @Test
    public void jsonPaleHolderTest() {
        jsonPlaceHolderJSONObject(5, 200, 1,
                "laboriosam mollitia et enim quasi adipisci quia provident illum"
                , false);
    }



}
