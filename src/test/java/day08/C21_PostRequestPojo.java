package day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolder.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static utilities.ApiCalls.pojoPostJasonPlaceHolder;
import static utilities.BaseUrlInterface.createJsonPlaceHolder;

public class C21_PostRequestPojo {


    // POJO : Plain Old Java Object
    // POJOs are used for increasing the readability and re-usability of a program

    /*
    send a request body to the  https://jsonplaceholder.typicode.com/todos
    requestbody

    {
    "userId": 25,
    "id": 120,
    "title": "Slovenia",
    "completed": true
}
     */
    @Test
    public void pojoFirstTest(){

        //expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo();
        expectedData.setId(201);
        expectedData.setUserId(584);
        expectedData.setTitle("Germany");
        expectedData.setCompleted(true);

        // Request and Response
        Response response = given().contentType(ContentType.JSON)
                .body(expectedData)
                .when()
                .post(createJsonPlaceHolder());
        response.then().assertThat().statusCode(201);
        response.prettyPrint();

        //Verify with De-Serialization
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        Assert.assertEquals(expectedData.getId(),actualData.getId());
        Assert.assertEquals(expectedData.getUserId(),actualData.getUserId());
        Assert.assertEquals(expectedData.getTitle(),actualData.getTitle());
        Assert.assertEquals(expectedData.isCompleted(),actualData.isCompleted());

    }


    @Test
    public void pojoPostTest(){
        pojoPostJasonPlaceHolder(201,201,580,"Turkiye",true);
    }

}
