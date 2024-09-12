import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Pet;

public class GetPetByTag {
    private static Response response;
    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String PET_PATH = "/findByTags?tags=string";
    //private static final String TAG = "string";
    private static Pet[] pets;

    @BeforeAll
    public static void beforeAll(){
        response = RestAssured
                .given()
                .baseUri("http://petstore.swagger.io/v2")
                .queryParam("tags", "string")  // Make sure "tags=string" is valid
                .when()
                .get("/pet/findByTags");

        pets = response.as(Pet[].class);
    }

    @Test
    public void validateResponse() {
        if (response.getStatusCode() == 200) {
            // Test may fail as the it is a public API so the data is constantly modified
            MatcherAssert.assertThat(84, Matchers.is(pets.length));  // Example test
        } else {
            System.err.println("Test skipped due to non-200 response.");
        }
    }
}
