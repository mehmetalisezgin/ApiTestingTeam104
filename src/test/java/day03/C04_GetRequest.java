package day03;

import io.restassured.response.Response;
import org.junit.Test;

import static utilities.BaseUrl.userIDHerokuapp;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C04_GetRequest {

    /*
    {
    "data": {
        "id": 4,
        "email": "eve.holt@reqres.in",
        "first_name": "Eve",
        "last_name": "Holt",
        "avatar": "https://reqres.in/img/faces/4-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}

TASK
verify the
     Status code is 200
     Content Type is application/json; charset=utf-8
     email is eve.holt@reqres.in
     first_name is Eve
     last_name is Holt
     url is https://reqres.in/#support-heading
     text is To keep ReqRes free, contributions towards server costs are appreciated!
     */


    @Test
    public void userReqresID3Exist() {
        Response response = given().when().get(userIDHerokuapp(4));
        response.prettyPrint();
        response
                .then()
                .contentType("application/json")
                .statusCode(200)
                .body("data.first_name", equalTo("Eve"),
                        "data.last_name", equalTo("Holt"),
                        "data.email", equalTo("eve.holt@reqres.in"),
                        "support.url", equalTo("https://reqres.in/#support-heading"),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }




}
