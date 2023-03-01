package day05;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static utilities.BaseUrl.employees;
import static io.restassured.RestAssured.given;

public class C09_GetRequest {

   /*
    http://dummy.restapiexample.com/api/v1/employees url
   */

    static Response response = given().when().get(employees());
    static JsonPath jsonPath = response.jsonPath();

    @Test
    public void printAllNames() {
        //   1) Let's print the names of all employees on the console
        response.then().statusCode(200);
        System.out.println("All Employees :" + jsonPath.getList("data.employee_name"));
        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Tatyana Fitzpatrick"));


    }

    @Test
    public void specificNamePrint() {
        // 2) Let's print the name of the 3rd employee on the console
        response.then().statusCode(200);
        System.out.println("3rd Employee is :" + jsonPath.getString("data[2].employee_name")); // 0,1,2,3
        System.out.println("3rd Employee is :" + jsonPath.getString("data.employee_name[2]"));
        // 3) Let's print the names of the first 5 employees on the console
        System.out.println("first 5 Employees are :" + jsonPath.getString("data.employee_name[0,1,2,3,4]"));
        //  4) Let's print the last employee's name on the console
        System.out.println("last employee is :" + jsonPath.getString("data[-1].employee_name"));

    }

    @Test
    public void test() {
        response.prettyPrint();
    }

}
