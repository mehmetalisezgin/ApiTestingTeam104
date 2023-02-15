package day07;

import org.junit.Test;

import static utilities.ApiCalls.deleteEmployee;

public class C19_DeleteRequest {


/*
Send a request to the 	https://dummy.restapiexample.com/api/v1/delete/2
verfy the status code is 200
and the body is like following
{
    "status": "success",
    "data"  : "2"
    "message": "successfully! deleted Records"
}
 */
    // When data deleted sometimes it should return a message, we can test this message
    // AND The good testers are delete their test data after the post request
    @Test
    public void deleteEmployeeId5() {
        deleteEmployee(5, 200, "success","5","Successfully! Record has been deleted");
    }


}
