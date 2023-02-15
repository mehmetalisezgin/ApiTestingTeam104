package day07;
import org.junit.Test;

import static utilities.ApiCalls.*;

public class C18_PostRequest {
    /*
    send a request body on the https://jsonplaceholder.typicode.com/todos
    {
    "userId": 12,
    "title" : "Mehmet",
    "completed" : true
    }
     */

   @Test
    public void createJsonPlaceHolder(){
       jsonPlaceHolderPostMethodMacthersClass(201,106,"Mathematics",true);
   }


    @Test
    public void createJsonPlaceHolderJsonPath(){
        jsonPlaceHolderPostMethodJsonPath(201,106,"History",true);
    }


    @Test
    public void createJsonPlaceHolderDeSerialization(){
        jsonPlaceHolderPostMethodDeSerialization(201,106,"History",true);
    }



}
