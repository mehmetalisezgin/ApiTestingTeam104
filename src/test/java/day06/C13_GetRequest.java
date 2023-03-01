package day06;

import org.junit.Test;

import static utilities.ApiCalls.*;

public class C13_GetRequest {
    // https://jsonplaceholder.typicode.com/todos/2
    /*
{
    "userId": 1,
    "id": 2,
    "title": "quis ut nam facilis et officia qui",
    "completed": false
}
 */

    @Test
    public void jsonPlaceHolderUsersID2(){
        jsonPlaceHolderMatcherClass(2,200,1,"quis ut nam facilis et officia qui"
        ,false);
    }

   /*
   {
    "userId": 1,
    "id": 3,
    "title": "fugiat veniam minus",
    "completed": false
}
    */


    @Test
    public void jsonPlaceHolderUsersID3(){
        jsonPlaceHolderJsonPath(3,200,1,"fugiat veniam minus"
                ,false);
    }

  /*
  {
    "userId": 1,
    "id": 5,
    "title": "laboriosam mollitia et enim quasi adipisci quia provident illum",
    "completed": false
}
   */
    @Test
    public void jsonPlaceHolderUsersID5(){
        jsonPlaceHolderDeSerialization(5,200,1,
                "laboriosam mollitia et enim quasi adipisci quia provident illum",false);
    }
}
