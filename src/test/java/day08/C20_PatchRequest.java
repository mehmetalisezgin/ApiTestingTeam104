package day08;

import org.junit.Test;

import static utilities.ApiCalls.patchRequestJsonPlaceHolder;

public class C20_PatchRequest {

    /*
    Send a following body request  to the https://jsonplaceholder.typicode.com/todos/2
    {
    "title" : "Team104"
    }
    The response should be like the following body
{
    "userId": 1,
    "id": 2,
    "title": "Team104",
    "completed": false
}
     */

    @Test
    public void patchJsonPlaceHolder() {

        patchRequestJsonPlaceHolder(2,200,1,"Team104",false);
    }

}
