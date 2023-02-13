package day05;

import org.junit.Test;

import static utilities.ApiCalls.checkUsersBookingExistWithId;
import static utilities.ApiCalls.checkUsersExistsJsonPath;

public class C11_GetRequest {


    /*
   {
    "firstname": "Eric",
    "lastname": "Brown",
    "totalprice": 737,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2017-08-09",
        "checkout": "2021-08-17"
    },
    "additionalneeds": "Breakfast"
}
     */


    @Test
    public void bookingId5Exist(){
        checkUsersBookingExistWithId(5,200,"Eric","Brown",
                737,true,"2017-08-09","2021-08-17");
    }

/*
{
    "firstname": "Susan",
    "lastname": "Brown",
    "totalprice": 591,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2020-09-21",
        "checkout": "2021-04-20"
    }
}
 */
    @Test
    public void bookingId6Exist(){
        checkUsersBookingExistWithId(6,200,"Susan","Brown",
                591,false,"2020-09-21","2021-04-20");
    }
/*
{
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 745,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2017-04-07",
        "checkout": "2019-02-18"
    },
    "additionalneeds": "Breakfast"
}
 */
    @Test
    public void bookingId7Exist(){
        checkUsersBookingExistWithId(7,200,"Jim","Brown",
                745,false,"2017-04-07","2019-02-18");
    }

/*
{
    "data": {
        "id": 10,
        "email": "byron.fields@reqres.in",
        "first_name": "Byron",
        "last_name": "Fields",
        "avatar": "https://reqres.in/img/faces/10-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
 */
   @Test
    public void userId10Exist(){
       checkUsersExistsJsonPath(10,200,"byron.fields@reqres.in",
               "Byron", "Fields");
   }



}
