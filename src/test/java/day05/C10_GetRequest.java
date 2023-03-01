package day05;

import org.junit.Test;

import static utilities.ApiCalls.checkUserExistWithId;
import static utilities.ApiCalls.checkUsersExistWithId;

public class C10_GetRequest {

   /*
    {
    "data": {
        "id": 5,
        "email": "charles.morris@reqres.in",
        "first_name": "Charles",
        "last_name": "Morris",
        "avatar": "https://reqres.in/img/faces/5-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
     */

    @Test
    public void checkUserId5Exist() {
        // this is a boolean method
        checkUserExistWithId(5,200,"charles.morris@reqres.in","Charles","Morris");
    }

    @Test
    public void checkUserId14Exist(){
        checkUserExistWithId(5,200,"mali@gmail.com","mehmet","ali");
    }

    @Test
    public void checkUserIdFiveExist() {
        // this is a Response method
        checkUsersExistWithId(5,200,"charles.morris@reqres.in","Charles","Morris");
    }



    @Test
    public void checkUserId6Exist() {
        // this is a Response method
        checkUsersExistWithId(6,200,"tracey.ramos@reqres.in","Tracey","Ramos");
    }





}
