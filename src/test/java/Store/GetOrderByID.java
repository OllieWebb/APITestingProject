package Store;

import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Order;
import utils.RequestUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.Map;

public class GetOrderByID {
        protected static final String BASE_URI = AppConfig.getBaseUri();
    protected static final String PATH = "store/order/{orderId}";
        private static Response validResponse;
        private static Response invalidResponse;
        private static Response notFoundResponse;
        private static Order validOrder;

        private static final int ORDER_ID = 2;


        @BeforeAll
        public static void beforeAll() {
            validResponse = RestAssured
                    .given(RequestUtils.getRequestSpec(BASE_URI, PATH, Map.of("orderId", String.valueOf(ORDER_ID))
                    )).when().get().thenReturn();
            validOrder = validResponse.as(Order.class);
            invalidResponse = RestAssured
                    .given(RequestUtils.getRequestSpec(BASE_URI, PATH, Map.of("orderId", String.valueOf("abc"))
                    )).when().get().thenReturn();

            notFoundResponse = RestAssured
                    .given(RequestUtils.getRequestSpec(BASE_URI, PATH, Map.of("orderId", String.valueOf("4664278"))
                    )).when().get().thenReturn();

        }
//Happy: valid ID is entered and valid JSON File is returned
        @Test
        @DisplayName("Check if status code is 200")
        public void checkStatusCode() {
            MatcherAssert.assertThat(validResponse.getStatusCode(), Matchers.is(200));
        }

        @Test
        @DisplayName("Check content type header is application/json")
        public void checkContentTypeHeader() {
            MatcherAssert.assertThat(validResponse.headers().get("Content-Type").getValue(), Matchers.is("application/json"));
        }
//Sad:
    @Test
    @DisplayName("Check 404 is returned when an id is entered that doesn't exist")
    public void validateNonExistentOrderIdReturns404() {
        MatcherAssert.assertThat(notFoundResponse.getStatusCode(), Matchers.is(404));
        MatcherAssert.assertThat(notFoundResponse.getBody().asString(), Matchers.containsString("Order not found"));
    }
//Sad: --Defect
    @Test
    @DisplayName("Check 400 is returned when an id of the wrong type is entered")
    public void validateInvalidOrderIdReturns400() {
        MatcherAssert.assertThat(invalidResponse.getStatusCode(), Matchers.is(400));
        MatcherAssert.assertThat(invalidResponse.jsonPath().getString("message"), Matchers.containsString("Input error: couldn't convert `" + "abc" + "` to type `class java.lang.Long`"));
    }
}
