package day04;

import utilities.BaseUrlSpec;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C07_GetRequest extends BaseUrlSpec {

/*
{
    "firstname": "Josh",
    "lastname": "Allen",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "super bowls"
}
 */

    @Test
    public void bookingId741() {
        //1st step
        bookingId.pathParams("1st", "booking", "2nd", 5641);
        //2nd step
        Response response = given().spec(bookingId).when().get("{1st}/{2nd}");
        //response.prettyPeek();
        //Verify with Matchers class
        response.then()
                .assertThat()
                .contentType("text/plain; charset=utf-8")
                .statusCode(200)
                .body("firstname", equalTo("Josh"),
                        "lastname", equalTo("Allen"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true));

    }


}
