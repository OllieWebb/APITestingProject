package Store;

import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.RequestUtils;

import java.util.Map;

public class GetPetInventoryByStatus {/*
    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String INVENTORY_PATH = "store/inventory";
    private static Response response;

    @BeforeAll
    public static void setup() {
        response = RestAssured
                .given(RequestUtils.getRequestSpec(BASE_URI, INVENTORY_PATH))
                .when()
                .get()
                .thenReturn();
    }
//Happy: returns JSON that is expected
    @Test
    public void testStatusCode() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    public void testContentTypeHeader() {
        MatcherAssert.assertThat(response.getHeader("Content-Type"), Matchers.is("application/json"));
    }

    @Test
    public void validateResponseBodyNotEmpty() {
        MatcherAssert.assertThat(response.getBody().asString().length(), Matchers.greaterThan(0));
    }

    @Test
    public void validateInventoryStatusValuesAreNotNegative() {
        Map<String, Integer> inventory = response.as(Map.class);

        inventory.forEach((key, value) -> {
            MatcherAssert.assertThat(value, Matchers.greaterThanOrEqualTo(0));
        });
    }*/
}
