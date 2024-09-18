package User;

import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pojo.User;
import utils.RequestUtils;

import java.util.Map;

public class DeleteUserByUsername {
    private static Response response;

    private static User user;
    private static User returnedUser;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String POST_PATH = "/user";
    protected static final String PATH = "/user/{username}";

    @BeforeAll
    public static void setup() {

    }
    @Test
    public void ifdf(){
        // Create user to delete
        user = new User("Blah", "lastName", "password", 1, "phone", 345435345, "email@example.com", "blahBlah");

        // Send POST request to create user
        response = RestAssured.given(RequestUtils.userRequestSpec(BASE_URI, POST_PATH, user)).post().thenReturn();
        response.prettyPrint();

        // Check if the user creation was successful
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));

        returnedUser = response.as(User.class);
        System.out.println(returnedUser.getUsername() + " " + returnedUser.getPassword());
    }

    @Test
    @Order(1)
    @DisplayName("Delete user and return 200 code")
    public void deleteUser(){
        response = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, PATH, Map.of("username", "blahBlah")))
                .delete()
                .thenReturn();

        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }
}
