package Store;

import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Order;
import utils.RequestUtils;

import java.util.Map;

public class PostOrder {
    protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String PATH = "/store/order";
    private static Response response;
    private static Order order;

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(RequestUtils.postRequestSpec(BASE_URI, PATH,
                        Map.of(
                                "Accept", "application/json",
                                "Content-Type", "application/json"
                        ),
                        Map.of(),
                        Map.of("id", 10, "petId", 198772, "quantity", 7, "shipDate", "2024-09-10T14:49:33.773Z", "status", "approved", "complete", "true"))
                )
                .when()
                .post()
                .thenReturn();
        order = response.as(Order.class);
    }

    @Test
    @DisplayName("Check if status code is 200")
    public void checkStatusCode() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Check server header is petstoreapi")
    public void checkServerHeader() {
        MatcherAssert.assertThat(response.headers().get("Server").getValue(), Matchers.containsString("Jetty"));
    }
}
