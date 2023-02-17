package pojos.Employee;

public class ResponseEmployeeData {

     /*
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

} */

    private String status ;
    private String message ;
    private RequestEmployeeData data;

    public ResponseEmployeeData() {
    }

    public ResponseEmployeeData(String status, String message, RequestEmployeeData data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestEmployeeData getData() {
        return data;
    }

    public void setData(RequestEmployeeData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseEmployeeData{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
