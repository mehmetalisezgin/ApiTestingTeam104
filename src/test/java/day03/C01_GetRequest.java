package day03;

import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class C01_GetRequest {

    private String usersReqresIn = "https://reqres.in/api/users/";


   @Test
    public void getUsersReqresIn(){
       // We are using Response interface
       Response response = given().when().get(usersReqresIn);

       // response.prettyPrint() ; // it brings us the body of the data (url)
       response.prettyPeek() ; // it brings us all data about the url
      // response.then().log().all();

       // given() : it refers to the inital values given to us when starting our test
       // when()  : it refers operations
       // then()  : it refers to the action taken to evaluate the response values.
       // and()   : Represents interconnected opearations

   }




}
