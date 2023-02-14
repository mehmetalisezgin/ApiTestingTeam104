package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import static day03.BaseUrl.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateToken;


public class ApiCalls {


    // This method will return response and we will use MatcherClass
    // https://reqres.in/api/users/
    public static boolean checkUserExistWithId(int id, int statuscode, String email,
                                               String first_name, String last_name) {

        Response response = given().when().get(userIDHerokuapp(id));
        response
                .then()
                .statusCode(statuscode)
                .contentType("application/json")
                .body("data.email", equalTo(email), "data.first_name", equalTo(first_name),
                        "data.last_name", equalTo(last_name));
        return Boolean.parseBoolean(String.valueOf(response));
    }

    public static Response checkUsersExistWithId(int id, int statuscode, String email,
                                                 String first_name, String last_name) {

        Response response = given().when().get(userIDHerokuapp(id));
        response
                .then()
                .statusCode(statuscode)
                .contentType("application/json")
                .body("data.email", equalTo(email), "data.first_name", equalTo(first_name),
                        "data.last_name", equalTo(last_name));
        return response;
    }

    public static Response checkUsersExistsJsonPath(int id, int statuscode, String email,
                                                    String first_name, String last_name) {
        Response response = given().when().get(userIDHerokuapp(id));
        response.then().statusCode(statuscode);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(email, jsonPath.getString("data.email"));
        Assert.assertEquals(first_name, jsonPath.getString("data.first_name"));
        Assert.assertEquals(last_name, jsonPath.getString("data.last_name"));
        return response;
    }


    //********************************************************************************************
    // booking ==>> https://restful-booker.herokuapp.com/booking/
    public static Response checkUsersBookingExistWithId(int id, int statuscode, String firstname,
                                                        String lastname, int totalprice, boolean depositpaid
            , String checkin, String checkout) {

        Response response = given().when().get(bookingUserID(id));// https://restful-booker.herokuapp.com/booking/
        response
                .then()
                .statusCode(statuscode)
                .contentType("application/json")
                .body("firstname", equalTo(firstname), "lastname", equalTo(lastname),
                        "totalprice", equalTo(totalprice), "depositpaid", equalTo(depositpaid),
                        "bookingdates.checkin", equalTo(checkin), "bookingdates.checkout", equalTo(checkout));
        return response;
    }


//****** De-Serialization Dynamic Method *************************

    public static Response deSerializationMethod(int id, int statuscode, String firstname,
                                                 String lastname, double totalprice, boolean depositpaid
            , String checkin, String checkout) {

        HashMap<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);

        // Request and Response
        Response response = given().when().get(bookingUserID(id));
        response.then().statusCode(statuscode);
        //Convert data from Json to Java  ===>> De-Serialization
        // Convert data from Java to Json ===>> Serialization
        // here Data will be converted to the Map by De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);// we did de-serialization

        // Verify
        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("checkin"), actualData.get("checkin"));
        Assert.assertEquals(expectedData.get("checkout"), actualData.get("checkout"));
        return response;
    }


//**************************************** JsonPlaceHolder *********************************



/*
{
    "userId": 1,
    "id": 2,
    "title": "quis ut nam facilis et officia qui",
    "completed": false
}
 */

    //Lets create a dynamic method with Matchers class
    public static Response jsonPlaceHolderMatcherClass(int id, int statuscode, int userId, String title, boolean completed) {
        Response response = given().when().get(jsonPlaceHolder(id));
        response.then().assertThat().statusCode(statuscode);
        response.then().body("userId", Matchers.equalTo(userId),
                "title", equalTo(title), "completed", equalTo(completed));
        return response;
    }

    // Lets create a dynamic method with JsonPath
    public static Response jsonPlaceHolderJsonPath(int id, int statuscode, int userId, String title, boolean completed) {
        Response response = given().when().get(jsonPlaceHolder(id));
        response.then().assertThat().statusCode(statuscode);
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(userId, jsonPath.getInt("userId"));
        Assert.assertEquals(title, jsonPath.getString("title"));
        Assert.assertEquals(completed, jsonPath.getBoolean("completed"));
        return response;
    }


    //Map De-Serialization dynamic method
    public static Response jsonPlaceHolderDeSerialization(int id, int statuscode, double userId, String title, boolean completed) {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);

        Response response = given().when().get(jsonPlaceHolder(id));
        response.then().assertThat().statusCode(statuscode);

        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization
        // the data converted from json to java
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));

        return response;
    }

    //****************** JSONObject *******************
    public static Response jsonPlaceHolderJSONObject(int id, int statuscode, double userId, String title, boolean completed) {
        //Test Data Created from JSONObject
        JSONObject expectedData = new JSONObject();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);

        Response response = given().when().get(jsonPlaceHolder(id));
        response.then().assertThat().statusCode(statuscode);
        JsonPath actualData = response.jsonPath();

        Assert.assertEquals(expectedData.get("userId"), actualData.getDouble("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        return response;
    }

//******************************** Post Methods *******************

    public static Response createBookingData(int statuscode, String firstname, String lastname,
                                             int totalprice, boolean depositpaid, String checkin, String checkout) {
        // Test data
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);


        //Request and Response
        Response response = given().contentType("application/json")
                .auth()
                .basic("admin", "password123")
                .body(expectedData.toString()) // if you are using JSONObject we should add .toString()
                .when()
                .post(createBooking());
        response.prettyPrint();
        // Verify ( did we create data or not)
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(expectedData.getString("firstname"), actualData.getString("booking.firstname"));
        Assert.assertEquals(expectedData.getString("lastname"), actualData.getString("booking.lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"), actualData.getInt("booking.totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"), actualData.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkin"), actualData.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkout"), actualData.getString("booking.bookingdates.checkout"));
        return response;
    }

    // we used authentication
    public static Response createBookingDataUsingAuth(int statuscode, String firstname, String lastname,
                                                      int totalprice, boolean depositpaid, String checkin, String checkout) {
        // Test data
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);


        //Request and Response
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + generateToken())
                .body(expectedData.toString()) // if you are using JSONObject we should add .toString()
                .when()
                .post(createBooking());
        response.prettyPrint();
        // Verify ( did we create data or not)
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(expectedData.getString("firstname"), actualData.getString("booking.firstname"));
        Assert.assertEquals(expectedData.getString("lastname"), actualData.getString("booking.lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"), actualData.getInt("booking.totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"), actualData.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkin"), actualData.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkout"), actualData.getString("booking.bookingdates.checkout"));
        return response;
    }

}
