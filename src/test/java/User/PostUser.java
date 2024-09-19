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
    private static Response getResponse;

    private static User newUser;
    private static User returnedNewUser;
    private static User returnedUpdatedUser;
    private static User updatedUser;

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
                6354,
                "email@example.com",
                "ki");
        updatedUser = new User( "UPDATEDfirstName",
                "UPDATEDlastName",
                "UPDATEDpassword",
                122,
                "UPDATEDphone",
                6354,
                "UPDATED.email@example.com",
                "ki");    //keep username the same
    }
    //Happy: successfully created new user
    @Test
    @Order(1)
    @DisplayName("user is posted and 200 is returned")
    public void postUser(){
        //Post Response
        response = RestAssured
                .given(RequestUtils.userRequestSpec(BASE_URI, POST_PATH, newUser))
                .post()
                .thenReturn();
        response.prettyPrint();
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
        //Get reponse to test update
        getResponse = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, "/user/{username}", Map.of("username", "ki")))
                .get()
                .thenReturn();
        returnedNewUser = getResponse.as(User.class);
        MatcherAssert.assertThat(returnedNewUser.getFirstName(), Matchers.is("firstName"));
        MatcherAssert.assertThat(returnedNewUser.getUsername(), Matchers.is("ki"));
    }

    //Happy successfully updated user
    @Test
    @Order(2)
    @DisplayName("user is updated and 200 is returned and the name has changed")
    public void updateUser(){
        //Update respomse
        response = RestAssured
                .given(RequestUtils.userRequestSpec(BASE_URI, PUT_PATH,Map.of("username", "ki"), updatedUser))
                .put()
                .thenReturn();
        response.prettyPrint();
        //Get reponse to test update
        getResponse = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, "/user/{username}", Map.of("username", "ki")))
                .get()
                .thenReturn();
        returnedUpdatedUser = getResponse.as(User.class);
        MatcherAssert.assertThat(returnedUpdatedUser.getFirstName(), Matchers.is("UPDATEDfirstName"));
        MatcherAssert.assertThat(returnedUpdatedUser.getUsername(), Matchers.is("ki"));
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }
}
