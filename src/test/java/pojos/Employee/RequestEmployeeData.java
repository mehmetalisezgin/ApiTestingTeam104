package pojos.Employee;

public class RequestEmployeeData {

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

    // Create private values
    private int id ;
    private String employee_name;
    private int employee_salary ;
    private int employee_age ;
    private String profile_image ;

    // Constructor without parameter
    public RequestEmployeeData() {
    }

    // constructor with parameter
    public RequestEmployeeData(int id, String employee_name, int employee_salary, int employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    // Getter and Setter for all

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    // create toString for reading data

    @Override
    public String toString() {
        return "EmployeeData{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}
