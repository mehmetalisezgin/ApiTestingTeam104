package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static day03.BaseUrl.bookingUserID;
import static day03.BaseUrl.userIDHerokuapp;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiCalls {



    // This method will return response and we will use MatcherClass
    // https://reqres.in/api/users/
    public static boolean checkUserExistWithId(int id, int statuscode, String email,
                                                String first_name, String last_name ){

        Response response = given().when().get(userIDHerokuapp(id));
        response
                .then()
                .statusCode(statuscode)
                .contentType("application/json")
                .body("data.email", equalTo(email),"data.first_name",equalTo(first_name),
                        "data.last_name",equalTo(last_name)) ;
        return Boolean.parseBoolean(String.valueOf(response));
    }

    public static Response checkUsersExistWithId(int id, int statuscode, String email,
                                               String first_name, String last_name ) {

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
                                                    String first_name, String last_name){
        Response response = given().when().get(userIDHerokuapp(id));
        response.then().statusCode(statuscode);

        JsonPath jsonPath = response.jsonPath() ;
        Assert.assertEquals(email,jsonPath.getString("data.email"));
        Assert.assertEquals(first_name,jsonPath.getString("data.first_name"));
        Assert.assertEquals(last_name,jsonPath.getString("data.last_name"));
        return response ;
    }





//********************************************************************************************
    // booking ==>> https://restful-booker.herokuapp.com/booking/
    public static Response checkUsersBookingExistWithId(int id, int statuscode, String firstname,
                                                 String lastname, int totalprice,boolean depositpaid
                                                  ,String checkin,String checkout ) {

        Response response = given().when().get(bookingUserID(id));// https://restful-booker.herokuapp.com/booking/
        response
                .then()
                .statusCode(statuscode)
                .contentType("application/json")
                .body("firstname", equalTo(firstname), "lastname", equalTo(lastname),
                        "totalprice", equalTo(totalprice),"depositpaid",equalTo(depositpaid),
                        "bookingdates.checkin",equalTo(checkin),"bookingdates.checkout",equalTo(checkout));
        return response;
    }






}
