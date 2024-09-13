import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Pet;
import utils.RequestUtils;

import java.util.Arrays;

public class GetPetByTag {
    private static Response responseAvailable;
    private static Response invalidTagResponse;
    private static Response tagNotFoundResponse;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String PATH = "/pet/findByTags";
    private static final String TAG = "string";

    private static Pet[] availablePets;

    @BeforeAll
    public static void beforeAll(){
        responseAvailable = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH))
                .queryParam("tags", TAG).when().get().thenReturn();
        availablePets = responseAvailable.as(Pet[].class);

        invalidTagResponse = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH))
                .queryParam("tags", 21).when().get().thenReturn();

        tagNotFoundResponse = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH))
                .queryParam("tags", "tagDoesntExist").when().get().thenReturn();
    }
//Happy: when a correct query is made - 200 code is returned
    @Test
    @DisplayName("Checking the correct status code")
    public void testCorrectStatusCode() {
         MatcherAssert.assertThat(responseAvailable.statusCode(),Matchers.is(200 ));  // Example test
    }
//Happy: When a successful tag is queried, all results have said tag
    @Test
    @DisplayName("Check all pets have the correct tag")
    public void iShouldReceiveAnArrayOfPetsWithTheTag() {
               // Check if all pets have the correct tag
        boolean tagPresentInEveryPet = Arrays.stream(availablePets)
                .allMatch(
                        p -> p.tags().stream().anyMatch(t -> t.name().equals(TAG))
                );
        Assertions.assertTrue(tagPresentInEveryPet);
    }
    //Sad: when a tag that is an invalid type returns 400 bad request - returns 200 -DEFECT
    @Test
    void testEmptyResponseForInvalidTag() {
        MatcherAssert.assertThat(tagNotFoundResponse.statusCode(), Matchers.is(400));
        Pet[] petsWithInvalidTag = invalidTagResponse.as(Pet[].class);
        MatcherAssert.assertThat(petsWithInvalidTag.length, Matchers.is(0));
    }
//Sad: when a tag that doesn't exist - returns 200 -DEFECT
    @Test
    void testResponseForTagNotFound() {
        MatcherAssert.assertThat(tagNotFoundResponse.statusCode(), Matchers.is(404));
        Pet[] petsWithTagNotFound = tagNotFoundResponse.as(Pet[].class);
        MatcherAssert.assertThat(petsWithTagNotFound.length, Matchers.is(0));
    }

}
