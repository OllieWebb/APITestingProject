package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    public User(String firstName, String lastName, String password, int userStatus, String phone, String id, String email, String username) {}
    public User() {}

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("userStatus")
    private int userStatus;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("id")
    private long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public String getPhone() {
        return phone;
    }

    public long getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}