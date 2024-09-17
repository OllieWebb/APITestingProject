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

public class DeleteOrderById {
    protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String POST_PATH = "/store/order";
    protected static final String DELETE_PATH = "store/order/{orderId}";
    private static Response response;
    private static Order order;

    @BeforeAll
    public static void beforeAll() {
        //Create Order to delete
            RestAssured
                .given(RequestUtils.postRequestSpec(BASE_URI, POST_PATH, Map.of("Accept", "application/json", "Content-Type", "application/json"),
                        Map.of(),
                        Map.of("id", 888, "petId", 55, "quantity", 7, "shipDate", "2024-09-10T14:49:33.773Z", "status", "approved", "complete", "true"))
                )
                .when()
                .post()
                .thenReturn();
    }
//HAPPY: ID found and successfully deleted
    @Test
    @org.junit.jupiter.api.Order(1)
    @DisplayName("Delete order which returns a 200 code")
    public void deleteOrderById() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath(DELETE_PATH)
                .pathParam("orderId", 888)
                .header("api_key", "")
                .when()
                .delete()
                .thenReturn();
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }
//SAD: ID not found as it was just deleted in test 1 - return 404 Not Found
    @Test
    @org.junit.jupiter.api.Order(2)
    @DisplayName("Delete order which has already been deleted returns a 404 code")
    public void deleteOrderWithInvalidId() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath(DELETE_PATH)
                .pathParam("orderId", 888)
                .header("api_key", "")
                .when()
                .delete()
                .thenReturn();
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(404));
    }
}
