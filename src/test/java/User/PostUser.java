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

public class PostUser {
    private static Response response;

    private static User newUser;
    private static User updatedUser;
    private static User returnedUser;
    private static User invalidUser;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String POST_PATH = "/user";
    protected static final String PUT_PATH = "/user/{username};";

    @BeforeAll
    public static void setup() {
        newUser = new User( "firstName",
                 "lastName",
                "password",
                1,
                "phone",
                "69",
                "email@example.com",
                "username");
        updatedUser = new User( "UPDATEDfirstName",
                "UPDATEDlastName",
                "UPDATEDpassword",
                122,
                "UPDATEDphone",
                "69999",
                "UPDATED.email@example.com",
                "username");    //keep username the same
        invalidUser = new User( "firstName", "lastName", "password", 1, "phone", "69", "email@example.com",
                "fdfsfdfsf");   //invalid username

    }
    //Happy: successfully created new user
    @Test
    @Order(1)
    @DisplayName("user is posted and 200 is returned")
    public void postUser(){
        response = RestAssured
                .given(RequestUtils.userRequestSpec(BASE_URI, POST_PATH, newUser))
                .post()
                .thenReturn();
        response.prettyPrint();
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }
    //Happy successfully updated user
    @Test
    @Order(2)
    @DisplayName("user is updated and 200 is returned and the name has changed")
    public void updateUser(){
        response = RestAssured
                .given(RequestUtils.userRequestSpec(BASE_URI, PUT_PATH,Map.of("username", "username"), updatedUser))
                .put()
                .thenReturn();
        response.prettyPrint();
        returnedUser = response.as(User.class);
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
        MatcherAssert.assertThat(returnedUser.getFirstName(), Matchers.is(updatedUser.getFirstName()));
    }
    //SAD - invalid username is entered - DEFECT
    @Test
    @DisplayName("non-existant username searched - should return 404")
    public void invalidUpdateUser(){
        response = RestAssured
                .given(RequestUtils.userRequestSpec(BASE_URI, PUT_PATH,Map.of("username", "ssdsss"), updatedUser))
                .put()
                .thenReturn();
        response.prettyPrint();
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(404));
    }
}
