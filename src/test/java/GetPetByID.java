import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Pet;
import utils.RequestUtils;

import java.util.Map;

public class GetPetByID {
    private static Response successResponse;
    private static Response notFoundResponse;
    private static Response badRequestResponse;
    private static Response emptyIdResponse;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String PATH = "/pet/{petId}";
    private static final String PET_ID = "10";

    private static Pet foundPet;

    @BeforeAll
    public static void beforeAll(){
        //Happy
        successResponse = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH,Map.of("petId", PET_ID)))
                .when().get().thenReturn();
        foundPet = successResponse.as(Pet.class);
        //Sad
        notFoundResponse = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH,Map.of("petId", "999999")))
                .when().get().thenReturn();
        //Sad
        badRequestResponse = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH,Map.of("petId", "invalidFormat")))
                .when().get().thenReturn();
        //Sad
        emptyIdResponse= RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH,Map.of("petId", "")))
                .when().get().thenReturn();

    }
    //Happy: Query GET pet with id: 10L
    @Test
    @DisplayName("Test the pet has the correct ID and returns 200 status code")
    public void petHasCorrectId(){
        MatcherAssert.assertThat(successResponse.statusCode(), Matchers.is(200));
        MatcherAssert.assertThat(foundPet.getId(), Matchers.is(10L));
    }
    //Sad: Query GET id of a pet that doesn't exist
    @Test
    void testPetNotFound() {
        //MatcherAssert.assertThat(notFoundResponse.statusCode(), Matchers.is(404));
        MatcherAssert.assertThat(notFoundResponse.jsonPath().getString("message"), Matchers.is("Pet not found"));
    }
    //Sad: Query GET id that contains characters
    @Test
    void testInvalidPetIdReturns400() {
        MatcherAssert.assertThat(badRequestResponse.statusCode(), Matchers.is(404));
    }
    //Sad: Query GET id that is empty
    @Test
    void testEmptyPetIdReturns405() {
        MatcherAssert.assertThat(emptyIdResponse.statusCode(), Matchers.is(405));
    }
}
