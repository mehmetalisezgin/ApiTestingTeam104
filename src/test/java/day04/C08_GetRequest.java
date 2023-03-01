package day04;

import utilities.BaseUrlSpec;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C08_GetRequest extends BaseUrlSpec {

    @Test
    public void userReqresIn() {
        // 1st step
        reqresIn.pathParams("1st","api","2nd","users");
        // 2nd step
        Response  response = given()
                .spec(reqresIn)
                .when()
                .get("{1st}/{2nd}");
        // 3rd header test
        //response.prettyPrint();
        response.then()
                .assertThat()
                .contentType("application/json; charset=utf-8")
                .statusCode(200);
        // 4th step
        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getList("data.email"));
        System.out.println(jsonPath.getList("data.first_name"));
        System.out.println(jsonPath.getList("data.last_name"));

        Assert.assertEquals("George",jsonPath.getString("data[0].first_name"));
        Assert.assertEquals("Bluth",jsonPath.getString("data[0].last_name"));
        Assert.assertEquals("Weaver",jsonPath.getString("data[1].last_name"));
        Assert.assertEquals("Wong",jsonPath.getString("data[2].last_name"));
        Assert.assertEquals("Holt",jsonPath.getString("data[3].last_name"));
        Assert.assertEquals("Morris",jsonPath.getString("data[4].last_name"));
        Assert.assertEquals("Ramos",jsonPath.getString("data[5].last_name"));
    }


   /*
{
    "data": {
        "id": 5,
        "email": "charles.morris@reqres.in",
        "first_name": "Charles",
        "last_name": "Morris",
        "avatar": "https://reqres.in/img/faces/5-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
 */

   @Test
   public void userId5(){
       // 1st step
       reqresIn.pathParams("1st","api","2nd","users","3rd",5);
       // 2nd step
       Response response = given()
               .spec(reqresIn)
               .when()
               .get("{1st}/{2nd}/{3rd}");
       // 3rd step Verify the Header
       response.then()
               .statusCode(200)
               .contentType("application/json");

       // Verify with JsonPath// Difference between json and jsonPath
       JsonPath jsonPath = response.jsonPath();
       Assert.assertEquals("charles.morris@reqres.in",jsonPath.getString("data.email"));
       Assert.assertEquals("Charles",jsonPath.getString("data.first_name"));
       Assert.assertEquals("Morris",jsonPath.getString("data.last_name"));
       Assert.assertEquals("https://reqres.in/img/faces/5-image.jpg",jsonPath.getString("data.avatar"));

   }



}
