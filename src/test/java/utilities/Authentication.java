package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

  public static String generateToken(){
      String username = "admin" ;
      String password = "password123" ;

      Map<String,Object> map = new HashMap<>();
      map.put("username",username);
      map.put("password",password);
      String url = "https://restful-booker.herokuapp.com/auth" ;
      Response response = given().contentType(ContentType.JSON)
              .body(map).when().post(url) ;
      JsonPath token = response.jsonPath() ;
      return token.getString("token") ;
  }

}
