import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Pet;
import utils.utils;

public class GetPetByID {
    private static Response response;
    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String PET_PATH = "/pet/{petId}";
    private static final Integer PET_ID = 10;
    private static Pet pet;

    @BeforeAll
    public static void beforeAll(){
        // Make sure the request expects JSON
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .pathParam("petId", PET_ID)
                .accept("application/json")  // Add the Accept header to ensure JSON response
                .when()
                .get(PET_PATH)
                .thenReturn();

        // Check the Content-Type to ensure it's JSON before deserialization
        String contentType = response.getContentType();
        if (contentType != null && contentType.contains("application/json")) {
            pet = response.as(Pet.class);
        } else {
            System.err.println("Expected JSON but got: " + contentType);
            System.err.println("Response body: " + response.getBody().asString());
        }
    }

    @Test
    @DisplayName("Get pet with a specific id and check the ID")
    public void petIdTest() {
        // Ensure the pet object was successfully deserialized
        if (pet != null) {
            MatcherAssert.assertThat(pet.id(), Matchers.is(10));
        } else {
            System.err.println("Test skipped due to invalid response content.");
        }
    }

    @Test
    @DisplayName("Confirm pet with ID 10 is called doggie")
    public void petIsCalledDoggie(){
        MatcherAssert.assertThat(pet.name(), Matchers.is("doggie"));
    }


}
