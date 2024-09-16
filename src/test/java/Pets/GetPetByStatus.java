package Pets;

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

public class GetPetByStatus {
    protected static Response response;
    protected Long petId;
    protected static Pet petResponse;
    protected static Pet[] pets;

    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String PATH = "pet/findByStatus";
    private static Response responseAvailable;
    private static Response responsePending;
    private static Response responseSold;
    private static Response responseNotFound;

    private static Pet[] availablePets;
    private static Pet[] pendingPets;
    private static Pet[] soldPets;

    @BeforeAll
    public static void beforeAll() {
        responseAvailable = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH))
                .queryParam("status", "available").when().get().thenReturn();
        availablePets = responseAvailable.as(Pet[].class);

        responsePending = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH))
                .queryParam("status", "pending").when().get().thenReturn();
        pendingPets = responsePending.as(Pet[].class);

        responseSold = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH))
                .queryParam("status", "sold").when().get().thenReturn();
        soldPets = responseSold.as(Pet[].class);

        responseNotFound = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH))
                .queryParam("status", "adiowhdahdkj").when().get().thenReturn();
    }

    //Happy: Query GET available pets
    @Test
    @DisplayName("Check all pets have status available")
    public void GetPetStatus_Available() {
        for (Pet pet : availablePets) {
            MatcherAssert.assertThat(pet.getStatus(), Matchers.is("available"));
        }
    }

    //Happy: Query GET pending pets
    @Test
    @DisplayName("Check all pets have status pending")
    public void GetPetStatus_Pending() {
        for (Pet pet : pendingPets) {
            MatcherAssert.assertThat(pet.getStatus(), Matchers.is("pending"));
        }
    }

    //Happy: Query GET sold pets
    @Test
    @DisplayName("Check all pets have status pending")
    public void GetPetStatus_Sold() {
        for (Pet pet : soldPets) {
            MatcherAssert.assertThat(pet.getStatus(), Matchers.is("sold"));
        }
    }
//NEED TO FIX
    //Sad: GET pets with an invalid status
    @Test
    @DisplayName("Check that response body is empty for invalid status")
    public void GetPetByStatus_EmptyResponseForInvalidStatus() {MatcherAssert.assertThat(responseNotFound.jsonPath().getString("message"), Matchers.containsString("Input error: query parameter `status value `adiowhdahdkj` is not in the allowable values `[available, pending, sold]`"));}

}

