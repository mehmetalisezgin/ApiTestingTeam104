package utilities;

public interface BaseUrlInterface {

    // We created this interface for the useful methods. Actually this is not different than
    // BaseUrl class.... Just for fun :)

     static String createBookingFromInterface(){
        return "https://restful-booker.herokuapp.com/booking" ;
    }

    static String createJsonPlaceHolder(){
         return "https://jsonplaceholder.typicode.com/todos";
    }

    static String deleteEmployeeById(int id){
         return "https://dummy.restapiexample.com/api/v1/delete/"+id+"";
    }

}
