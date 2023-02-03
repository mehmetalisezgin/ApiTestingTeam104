package day03;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C02_GetRequest {

    /*
    send a Get request to the https://restful-booker.herokuapp.com/booking/10
    verify:
    the status code is 200,
    the content type is application/json; charset=utf-8,
    the value of the Header named Server is Cowboy
    status line is HTTP/1.1 200 OK
     */


    private String restfulBookerUrl = "https://restful-booker.herokuapp.com/booking/10";
    private Response response = given().when().get(restfulBookerUrl);


    @Test
    public void printInfoRestfulBooker(){

        System.out.println("Status Code is :"+response.getStatusCode());
        System.out.println("The Content Type is :"+response.contentType());
        System.out.println("The Value of the Header :"+response.getHeader("Server"));
        System.out.println("Status Line :"+response.statusLine());
        System.out.println("The time of the response :"+response.getTime());
    }


    @Test
    public void bookingTestWithAssertion(){
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server"),"Cowboy");
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");
    }


    // We have used response.then.assertThat it is similar to Assertion
    @Test
    public void bookingHeaderTest(){
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK") ;
    }



}
