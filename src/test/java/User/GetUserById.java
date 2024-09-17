package User;

import config.AppConfig;
import pojo.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.RequestUtils;

import java.util.Map;

public class GetUserById {
    private static Response response;
    private static Response invalidResponse;
    private static User user;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String PATH = "/user/{username}";
    private static final String USERNAME = "test";
    private static final String INVALID_USERNAME = "fakeUser";

    @BeforeAll
    public static void setup() {
        response = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, PATH, Map.of("username", USERNAME)))
                .get()
                .thenReturn();
        user = response.as(User.class);

        invalidResponse = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, PATH, Map.of("username", INVALID_USERNAME)))
                .get()
                .thenReturn();
    }
//Happy - valid usernmame entered
    @Test
    public void validateStatusCode() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    public void validateContentTypeHeader() {
        MatcherAssert.assertThat(response.getHeader("Content-Type"), Matchers.is("application/json"));
    }
    //SAD - invalid usernmame entered
    @Test
    public void invalidateStatusCode() {
        MatcherAssert.assertThat(invalidResponse.getStatusCode(), Matchers.is(404));
    }
}
