package day04;

import utilities.BaseUrlSpec;
import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C06_Getrequest extends BaseUrlSpec {

    /*
    {
        "data": {
            "id": 2,
            "email": "janet.weaver@reqres.in",
            "first_name": "Janet",
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
    public void userId2ReqresIn() {
        //reqresIn bring us this url "https://reqres.in/" from the BaseUrlSpec class
        reqresIn.pathParams("parameter1", "api", "parameter2", "users", "parameter3", 15);
        Response response = given()
                .spec(reqresIn)
                .when()
                .get("{parameter1}/{parameter2}/{parameter3}");
        response.prettyPeek();

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
     */
    @Test
    public void userId3ReqresIn() {
        //reqresIn bring us this url "https://reqres.in/" from the BaseUrlSpec class
        reqresIn.pathParams("1st", "api", "2nd", "users", "3rd", 3);
        Response response = given()
                .spec(reqresIn)
                .when()
                .get("{1st}/{2nd}/{3rd}");
       // response.prettyPeek();
        // let test with Matcher class
        response.then()
                .assertThat()
                .contentType("application/json; charset=utf-8")
                .statusCode(200)
                .body("data.email", equalTo("emma.wong@reqres.in"),
                        "data.first_name", equalTo("Emma"),
                        "data.last_name", equalTo("Wong"));
    }


}
