package practice;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static utilities.Authentication.generateToken;
import static utilities.Authentication.generateTokenTrendLife;

public class GetRequest01 {


    public static String trendLifeAllUsers(){
        return "https://trendlifebuy.com/api/get-users";
    }
    public static String trendLifeUser(int id){
        return "https://trendlifebuy.com/api/get-user?id="+id+"";
    }


    @Test
    public void trendLifeAllUsersHeaderTest(){
    // generateTokenTrendLife()

     Response response = given()
             .header("Authorization", "Bearer " + generateTokenTrendLife())
             .when()
             .get(trendLifeAllUsers());
     response.prettyPeek() ;
     response.then().assertThat()
             .statusCode(200)
             .contentType("application/json")
             .header("Server","Apache");
    }


    public static Response trendLifeHeaderTest( int statuscode, String contentType){
        Response response = given()
                .header("Authorization", "Bearer " + generateTokenTrendLife())
                .when()
                .get(trendLifeAllUsers());
        response.then().assertThat()
                .statusCode(statuscode)
                .contentType(contentType)
                .header("Server","Apache");

        return response;
    }

    @Test
    public void headerTestTrendLife(){
        trendLifeHeaderTest(200,"application/json") ;
    }



    @Test
    public void getUserVerifyId3(){
        JSONObject id = new JSONObject();
        id.put("id",3);
        Response response = given()
                .header("Authorization", "Bearer " + generateTokenTrendLife())
                .when()
                .get(trendLifeUser(3));
        response.prettyPrint() ;
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .header("Server","Apache");

        JsonPath actualData = response.jsonPath();
        Assert.assertEquals("Staff",actualData.getString("user.first_name"));
        Assert.assertEquals(null,actualData.getString("user.last_name"));
        Assert.assertEquals("0183",actualData.getInt("user.username"));
        Assert.assertEquals("null",actualData.getInt("user.photo"));


    }

/*
"user": {
        "id": 3,
        "first_name": "Staff",
        "last_name": null,
        "username": "0183",
        "photo": null,
        "role_id": 3,
        "mobile_verified_at": null,
        "email": "staff@gmail.com",
        "is_verified": 1,
        "verify_code": null,
        "email_verified_at": null,
        "notification_preference": "mail",
        "is_active": 1,
        "avatar": null,
        "phone": null,
        "date_of_birth": null,
        "description": null,
        "secret_login": 0,
        "created_at": "2021-09-25T21:32:55.000000Z",
        "updated_at": "2021-09-25T21:32:55.000000Z",
        "name": "Staff "
    },
    "message": "success"
}
 */



}
