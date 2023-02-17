package day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Employee.RequestEmployeeData;
import pojos.Employee.ResponseEmployeeData;

import static io.restassured.RestAssured.given;
import static utilities.ApiCalls.getEmployeeInnerPojoClass;
import static utilities.BaseUrlInterface.getEmployee;

public class C22_GetRequestPojo {
       /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                          Status code is 200
{
"status": "success",
"message": "Successfully! Record has been fetched."
"data": {
  "id": 1,
  "employee_name": "Tiger Nixon",
  "employee_salary": 320800,
  "employee_age": 61,
  "profile_image": ""
  },

}

*/

     @Test
    public void employeeIdTest(){
         //Expected Data
         RequestEmployeeData expectedData = new RequestEmployeeData();
         expectedData.setId(1);
         expectedData.setEmployee_name("Tiger Nixon");
         expectedData.setEmployee_salary(320800);
         expectedData.setEmployee_age(61);
         expectedData.setProfile_image("");

         ResponseEmployeeData expectedData2 = new ResponseEmployeeData();
         expectedData2.setStatus("success");
         expectedData2.setData(expectedData);
         expectedData2.setMessage("Successfully! Record has been fetched.");
         //Request And Response
         Response response = given().contentType(ContentType.JSON)
                 .when()
                 .get(getEmployee(1));
         response.then().assertThat().statusCode(200) ;
         response.prettyPrint();

         ResponseEmployeeData actualData = response.as(ResponseEmployeeData.class);
         Assert.assertEquals(expectedData2.getData().getId(),actualData.getData().getId());
         Assert.assertEquals(expectedData2.getData().getEmployee_name(),actualData.getData().getEmployee_name());
         Assert.assertEquals(expectedData2.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
         Assert.assertEquals(expectedData2.getData().getEmployee_age(),actualData.getData().getEmployee_age());
         Assert.assertEquals(expectedData2.getData().getProfile_image(),actualData.getData().getProfile_image());
         Assert.assertEquals(expectedData2.getStatus(),actualData.getStatus());
         Assert.assertEquals(expectedData2.getMessage(),actualData.getMessage());
     }


     @Test
     public void getEmployeeTest01(){
          getEmployeeInnerPojoClass(1, 200,"Tiger Nixon"
    ,320800,61,"","success","Successfully! Record has been fetched.");
     }



     /*
     "status": "success",
    "data": [
        {
            "id": 1,
            "employee_name": "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
            "profile_image": ""
        },
      */
}
