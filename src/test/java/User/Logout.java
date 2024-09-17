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

public class Logout {
    private static Response response;
    private static User user;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String PATH = "/user/logout";

    @BeforeAll
    public static void setup() {
        response = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, PATH))
                .get()
                .thenReturn();
    }

    @Test
    @DisplayName("200 response returned")
    public void logout() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }
}
