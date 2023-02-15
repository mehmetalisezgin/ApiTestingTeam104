package day04;

import io.restassured.response.Response;
import org.junit.Test;

import static utilities.BaseUrl.userJsonPlaceHolderId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C05_GetRequest {

    /*
    TASK:
    Verify the user which is id 5:
    the status code is 200
    content type is "application/json"
    the server is "cloudflare" in the header
    userId is 1
    title is "laboriosam mollitia et enim quasi adipisci quia provident illum"
    completed is false
     */

    /*
    {
        "userId": 1,
        "id": 5,
        "title": "laboriosam mollitia et enim quasi adipisci quia provident illum",
        "completed": false
    }
     */
    @Test
    public void userId5JsonPlaceHolder() {
     Response response = given().when().get(userJsonPlaceHolderId(5));
     response.then()
             .assertThat()
             .contentType("application/json")
             .statusCode(200)
             .headers("Server", equalTo("cloudflare"))
             .body("userId",equalTo(1),
                     "title",equalTo("laboriosam mollitia et enim quasi adipisci quia provident illum"),
                     "completed",equalTo(false));
    }


}
