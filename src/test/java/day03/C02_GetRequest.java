package day03;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;


import java.util.Scanner;

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
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
        Assert.assertEquals("Cowboy",response.header("Server"));
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());
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
