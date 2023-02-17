package pojos.JsonPlaceHolder;

public class JsonPlaceHolderPojo {

    /*
    {
    "userId": 1,
    "id": 2,
    "title": "Team104",
    "completed": false
}
     */
   // How to Create a POJO class
   // 1st Create private variables from the body
   private int userId ;
   private int id ;
   private String title ;
   private boolean completed ;

    // 2nd Create a without parameter constructor

    public JsonPlaceHolderPojo() {

    }

    // 3rd Create constructor with all parameter

    public JsonPlaceHolderPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // 4th Create getter and setter for all

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // 5th Create toString method for all
    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
