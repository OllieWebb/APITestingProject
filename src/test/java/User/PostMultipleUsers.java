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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostMultipleUsers {
    private static Response response;
    private static Response getResponse;
    
    private static List<User> userList = new ArrayList<>();
    private static User firstUser;
    private static User secondUser;
    private static User thirdUser;
    private static User returnedNewUser;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String POST_PATH = "/user/createWithList";

    @BeforeAll
    public static void setup() {
        firstUser = new User( "firstName1", "lastName1", "password1", 1, "phone1",
                854,
                "email@example.com1",
                "Username1");
        userList.add(firstUser);
        secondUser = new User( "firstName2", "lastName2", "password2", 1, "phone2",
                855,
                "email@example.com2",
                "Username2");
        userList.add(secondUser);
        thirdUser = new User( "firstName3", "lastName3", "password3", 1, "phone3",
                855,
                "email@example.com3",
                "Username3");
        userList.add(thirdUser);

    }
    //Happy: successfully created new user
    @Test
    @Order(1)
    @DisplayName("users are posted and 200 is returned")
    public void postUser(){
        //Post Response
        response = RestAssured
                .given(RequestUtils.postRequestSpecList(BASE_URI, POST_PATH, userList))
                .post()
                .thenReturn();
        response.prettyPrint();
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));

        //Get reponse to test update
        getResponse = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, "/user/{username}", Map.of("username", "Username3")))
                .get()
                .thenReturn();
        returnedNewUser = getResponse.as(User.class);
        MatcherAssert.assertThat(returnedNewUser.getFirstName(), Matchers.is("firstName3"));
        MatcherAssert.assertThat(returnedNewUser.getUsername(), Matchers.is("Username3"));
    }
}
