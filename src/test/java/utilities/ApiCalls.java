package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Booking.BookingDates;
import pojos.Booking.BookingPojo;
import pojos.Booking.BookingResponse;
import pojos.JsonPlaceHolder.JsonPlaceHolderPojo;
import pojos.Employee.RequestEmployeeData;
import pojos.Employee.ResponseEmployeeData;

import java.util.HashMap;
import java.util.Map;

import static utilities.BaseUrl.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateToken;
import static utilities.BaseUrlInterface.*;


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
    public static Response jsonPlaceHolderDeSerialization(int id, int statuscode, double userId,
                                                          String title, boolean completed) {

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
    public static Response jsonPlaceHolderJSONObject(int id, int statuscode,
                                                     double userId, String title, boolean completed) {
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


    public static Response createBookingDataHashMap(int statuscode, String firstname, String lastname,
                                                    int totalprice, boolean depositpaid, String checkin, String checkout) {
        // Test data
        HashMap<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);


        //Request and Response
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + generateToken())
                .body(expectedData) // if you are using HashMap we do not need to add .toString()
                .when()
                .post(createBookingFromInterface());
        response.prettyPrint();
        // Verify ( did we create data or not)
        JsonPath actualData = response.jsonPath();//De-Serialization

        Assert.assertEquals(firstname, actualData.getString("booking.firstname"));
        Assert.assertEquals(lastname, actualData.getString("booking.lastname"));
        Assert.assertEquals(totalprice, actualData.getInt("booking.totalprice"));
        Assert.assertEquals(depositpaid, actualData.getBoolean("booking.depositpaid"));
        Assert.assertEquals(checkin, actualData.get("booking.bookingdates.checkin"));
        Assert.assertEquals(checkout, actualData.get("booking.bookingdates.checkout"));
        return response;
    }

    public static Response jsonPlaceHolderPostMethodMacthersClass(int statuscode, int userId, String title, boolean completed) {
        // Request and Response data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);

        //Request and Response
        Response response = given().contentType("application/json")
                .body(expectedData)
                .when()
                .post(createJsonPlaceHolder());
        response.prettyPrint();

        // Verify
        // 1st MacthersClass

        response.then().statusCode(statuscode)
                .body("userId", equalTo(userId),
                        "title", equalTo(title),
                        "completed", equalTo(completed));
        return response;
    }

    public static Response jsonPlaceHolderPostMethodJsonPath(int statuscode, int userId, String title, boolean completed) {
        // Request and Response data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);

        //Request and Response
        Response response = given().contentType("application/json")
                .body(expectedData)
                .when()
                .post(createJsonPlaceHolder());
        response.prettyPrint();
        response.then().statusCode(statuscode);
        // 2nd JsonPath
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(userId, actualData.getInt("userId"));
        Assert.assertEquals(title, actualData.getString("title"));
        Assert.assertEquals(completed, actualData.getBoolean("completed"));
        return response;
    }

    public static Response jsonPlaceHolderPostMethodDeSerialization(int statuscode, double userId, String title, boolean completed) {
        // Request and Response data
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);
        //Request and Response
        Response response = given().contentType("application/json")
                .body(expectedData)
                .when()
                .post(createJsonPlaceHolder());
        response.prettyPrint();
        response.then().statusCode(statuscode);

        // 3rd De-Serialization ==> from json to java
        HashMap<String, Object> actualDataHashMap = response.as(HashMap.class);
        Assert.assertEquals(expectedData.get("userId"), actualDataHashMap.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualDataHashMap.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualDataHashMap.get("completed"));

        return response;
    }

    public static Response deleteEmployee(int id, int statuscode, String status, String data, String message) {
        // expected data when we delete this data should return
        JSONObject expectedData = new JSONObject();
        expectedData.put("status", status);
        expectedData.put("data", data);
        expectedData.put("message", message);

        // Request and Response
        Response response = given()
                .when().delete(deleteEmployeeById(id));

        // Verify with Matchers Class
        response.then().assertThat().statusCode(statuscode)
                .body("status", equalTo(status), "data", equalTo(data),
                        "message", equalTo(message));

        return response;
    }

    /*
    {
        "userId": 1,
        "id": 2,
        "title": "Team104",
        "completed": false
    }
     */
    public static Response patchRequestJsonPlaceHolder(int id, int statuscode, int userId, String title, boolean completed) {
        // we will send this data for the patchrequest
        JSONObject requestData = new JSONObject();
        requestData.put("title", title);

        // create Request and Response
        Response response = given().contentType(ContentType.JSON)
                .body(requestData.toString())// if our data is a JSONObject we sould add .toString method
                .when()
                .patch(jsonPlaceHolderUserId(id));
        response.prettyPrint();
        // Verify with Matchers class

        response.then().assertThat().statusCode(statuscode)
                .body("userId", equalTo(userId), "title", equalTo(title), "completed", equalTo(completed));
        return response;
    }

    public static Response patchRequestJsonPlaceHolderJsonPath(int id, int statuscode, int userId, String title, boolean completed) {
        // we will send this data for the patchrequest
        JSONObject requestData = new JSONObject();
        requestData.put("title", title);
        // we will Assert these data
        JSONObject expectedData = new JSONObject();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);
        // create Request and Response
        Response response = given().contentType(ContentType.JSON)
                .body(requestData.toString())// if our data is a JSONObject we sould add .toString method
                .when()
                .patch(jsonPlaceHolderUserId(id));
        response.prettyPrint();
        // Verify with Matchers class
        response.then().assertThat().statusCode(statuscode);

        //Verify with JsonPath  student will create

        return response;
    }


    public static Response patchRequestJsonPlaceHolderDeSerialization(int id, int statuscode, int userId, String title, boolean completed) {
        // we will send this data for the patchrequest
        JSONObject requestData = new JSONObject();
        requestData.put("title", title);
        // we will Assert these data
        JSONObject expectedData = new JSONObject();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);
        // create Request and Response
        Response response = given().contentType(ContentType.JSON)
                .body(requestData.toString())// if our data is a JSONObject we sould add .toString method
                .when()
                .patch(jsonPlaceHolderUserId(id));
        response.prettyPrint();
        // Verify with Matchers class
        response.then().assertThat().statusCode(statuscode);

        //Verify with DeSerialization

        return response;
    }


    public static Response pojoPostJasonPlaceHolder(int id, int statuscode, int userId, String title
            , boolean completed) {
        //expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo();
        expectedData.setId(id);
        expectedData.setUserId(userId);
        expectedData.setTitle(title);
        expectedData.setCompleted(completed);

        // Request and Response
        Response response = given().contentType(ContentType.JSON)
                .body(expectedData)
                .when()
                .post(createJsonPlaceHolder());
        response.then().assertThat().statusCode(statuscode);
        response.prettyPrint();

        //Verify with De-Serialization
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        Assert.assertEquals(expectedData.getId(), actualData.getId());
        Assert.assertEquals(expectedData.getUserId(), actualData.getUserId());
        Assert.assertEquals(expectedData.getTitle(), actualData.getTitle());
        Assert.assertEquals(expectedData.isCompleted(), actualData.isCompleted());
        return response;
    }


    public static Response getEmployeeInnerPojoClass(int id, int statusCode,String employeeName
    ,int salary,int age,String profile,String status,String message){
        //Expected Data
        RequestEmployeeData expectedData = new RequestEmployeeData();
        expectedData.setId(id);
        expectedData.setEmployee_name(employeeName);
        expectedData.setEmployee_salary(salary);
        expectedData.setEmployee_age(age);
        expectedData.setProfile_image(profile);

        ResponseEmployeeData expectedData2 = new ResponseEmployeeData();
        expectedData2.setStatus(status);
        expectedData2.setData(expectedData);
        expectedData2.setMessage(message);
        //Request And Response
        Response response = given().contentType(ContentType.JSON)
                .when()
                .get(getEmployee(id));
        response.then().assertThat().statusCode(statusCode) ;
        response.prettyPrint();

        ResponseEmployeeData actualData = response.as(ResponseEmployeeData.class);
        Assert.assertEquals(expectedData2.getData().getId(),actualData.getData().getId());
        Assert.assertEquals(expectedData2.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        Assert.assertEquals(expectedData2.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        Assert.assertEquals(expectedData2.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        Assert.assertEquals(expectedData2.getData().getProfile_image(),actualData.getData().getProfile_image());
        Assert.assertEquals(expectedData2.getStatus(),actualData.getStatus());
        Assert.assertEquals(expectedData2.getMessage(),actualData.getMessage());
        return response ;
    }

    /*
    {
    "firstname" : "Omer",
    "lastname" : "Can",
    "totalprice" : 254,
    "depositpaid" : false,
    "bookingdates" : {
        "checkin" : "2020-02-03",
        "checkout" : "2020-02-10"
    },
    "additionalneeds" : "Breakfast"
}
     */

    public static Response createBookingWithPojo(int statuscode,String firstname, String lastname
    ,int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds){

      // to use pojo class we need to create object from thw pojo classes
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);

        BookingPojo bookingPojo = new BookingPojo() ;
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);

        BookingResponse booking = new BookingResponse();
        booking.setBookingDates(bookingDates);
        booking.setBookingPojo(bookingPojo);
        booking.setAdditionalneeds(additionalneeds);

        Response response = given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + generateToken())
                .body(booking)
                .when().post(createBookingFromInterface());
        response.prettyPrint();
        response.then().assertThat().statusCode(statuscode) ;
        System.out.println(response.statusCode());
        BookingResponse actualData = response.as(BookingResponse.class) ;
        Assert.assertEquals(booking.getBookingPojo().getFirstname(),actualData.getBookingPojo().getFirstname());
        Assert.assertEquals(booking.getBookingPojo().getLastname(),actualData.getBookingPojo().getLastname());
        Assert.assertEquals(booking.getBookingPojo().getTotalprice(),actualData.getBookingPojo().getTotalprice());
        Assert.assertEquals(booking.getBookingPojo().isDepositpaid(),actualData.getBookingPojo().isDepositpaid());
        Assert.assertEquals(booking.getBookingDates().getCheckin(),actualData.getBookingDates().getCheckin());
        Assert.assertEquals(booking.getBookingDates().getCheckout(),actualData.getBookingDates().getCheckout());
        Assert.assertEquals(booking.getAdditionalneeds(),actualData.getAdditionalneeds());
        return response;
    }

}
