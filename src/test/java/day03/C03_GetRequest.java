package day03;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class C03_GetRequest {

    private String bookingHerokuapp = "https://restful-booker.herokuapp.com/booking/";
    private String bookingID240Herokuapp = "https://restful-booker.herokuapp.com/booking/";
    private String userID2ReqresIn = "https://reqres.in/api/users/2";
    private String UserID3ReqresIn = "https://reqres.in/api/users/3";



    @Test
    public void getBooking() {
        Response response = given().when().get(bookingHerokuapp);
        response.prettyPeek();
    }

    /*
    {
    "firstname": "John",
    "lastname": "Smith",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
}

  TASK:
  Verify:
  The status Code is 200 ,
  Content Type is application/json; charset=utf-8
  firstname is John
  lastname is Smith
  totalprice is 111
  depositpaid true
  bookingdates(checkin) is 2018-01-01
  bookingdates(checkout) is checkout
     */


    // we will verify the data with matcher class
    @Test
    public void bookingIdIsExist() {
        int id = 240;
        Response response = given().when().get(bookingID240Herokuapp+id);
        response
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("firstname", equalTo("John"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"));

    }

    /*
    {
        "data": {
            "id": 2,
            "email": "janet.weaver@reqres.in",
            "first_name": "",
            "last_name": "Weaver",
            "avatar": "https://reqres.in/img/faces/2-image.jpg"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
    }

     TASK :
     verify the
     Status code is 200
     Content Type is application/json; charset=utf-8
     email is janet.weaver@reqres.in
     first_name is Janet
     last_name is Weaver
     url is https://reqres.in/#support-heading
     text is To keep ReqRes free, contributions towards server costs are appreciated!
     */
    @Test
    public void userReqresID2Exist() {
        Response response = given().when().get(userID2ReqresIn);
        response
                .then()
                .contentType("application/json")
                .statusCode(200)
                .body("data.first_name", equalTo("Janettt"),
                        "data.last_name", equalTo("Weaver"),
                        "data.email", equalTo("janet.weaver@reqres.in"),
                        "support.url", equalTo("https://reqres.in/#support-heading"),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    /*
    {
    "data": {
        "id": 3,
        "email": "emma.wong@reqres.in",
        "first_name": "Emma",
        "last_name": "Wong",
        "avatar": "https://reqres.in/img/faces/3-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
TASK :
     verify the
     Status code is 200
     Content Type is application/json; charset=utf-8
     email is emma.wong@reqres.in
     first_name is Emma
     last_name is Wong
     url is https://reqres.in/#support-heading
     text is To keep ReqRes free, contributions towards server costs are appreciated!

     */

    @Test
    public void userReqresID3Exist() {
        Response response = given().when().get(UserID3ReqresIn);
        response.prettyPrint();
        response
                .then()
                .contentType("application/json")
                .statusCode(200)
                .body("data.first_name", equalTo("Emma"),
                        "data.last_name", equalTo("Wong"),
                        "data.email", equalTo("ema.wong@reqres.in"),
                        "support.url", equalTo("https://reqres.in/#support-heading"),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }


}
