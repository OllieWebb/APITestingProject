package User;

import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.User;
import utils.RequestUtils;

import java.util.Map;

public class Login {
    private static Response response;
    private static Response noUsernameResponse;
    private static Response noPasswordResponse;
    private static Response noDetailsResponse;

    private static User user;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String PATH = "/user/login";
    private static final String USERNAME = "testUsername";
    private static final String PASSWORD = "testPassword";

    @BeforeAll
    public static void setup() {
        response = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, PATH)).queryParam("username", USERNAME).queryParam("password", PASSWORD)
                .get()
                .thenReturn();
        noUsernameResponse = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, PATH)).queryParam("username", USERNAME)
                .get()
                .thenReturn();
        noPasswordResponse = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, PATH)).queryParam("password", PASSWORD)
                .get()
                .thenReturn();
        noDetailsResponse = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, PATH)).queryParam("password", PASSWORD)
                .get()
                .thenReturn();
    }
    @Test
    @DisplayName("Username and Password supplied so 200 code returned")
    public void validLogin(){
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    //SAD - Incorrect detials are entered
    @Test
    @DisplayName("No password - return 400 bad request")
    public void invalidUsername(){
        MatcherAssert.assertThat(noUsernameResponse.getStatusCode(), Matchers.is(400));
    }
    @Test
    @DisplayName("No password - return 400 bad request")
    public void invalidPassword(){
        MatcherAssert.assertThat(noPasswordResponse.getStatusCode(), Matchers.is(400));
    }
    @Test
    @DisplayName("No details - return 400 bad request")
    public void noUsernameOrPassword(){
        MatcherAssert.assertThat(noDetailsResponse.getStatusCode(), Matchers.is(400));
    }
}
