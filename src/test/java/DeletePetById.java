import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.Pet;
import pojo.TagsItem;
import utils.RequestUtils;

import java.util.List;

public class DeletePetById {
    private static Response response;
    private static Pet newPet;
    private static TagsItem tagsItem;
    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String UPLOAD_PATH = "/pet";
    private static final String DELETE_PATH = "/pet/{petId}";

    @BeforeAll
    public static void setUp() {
        tagsItem = new TagsItem(0, "String");
        newPet = new Pet(
                234,
                "petToDelete",
                new Category(2, "Dog"),
                List.of("string"),
                List.of(tagsItem),
                "available"
        );
        RestAssured
                .given(RequestUtils.petRequestSpec(BASE_URI, UPLOAD_PATH, newPet))
                .when()
                .post()
                .thenReturn();
    }
//Happy: Pet is deleted using valid ID
    @Test
    @Order(1)
    @DisplayName("Delete pet by ID gives 200 response")
    public void deletePetByIdGives200Response() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath(DELETE_PATH)
                .pathParam("petId", 234)
                .header("api_key", "")
                .when()
                .delete()
                .thenReturn();

        MatcherAssert.assertThat(response.getStatusCode(), org.hamcrest.Matchers.is(200));
    }
//Sad: Invalid ID is entered
@Test
@Order(2)
@DisplayName("Delete the same pet ")
public void petNotFound404() {
    response = RestAssured
            .given()
            .baseUri(BASE_URI)
            .basePath(DELETE_PATH)
            .pathParam("petId", 234)
            .header("api_key", "")
            .when()
            .delete()
            .thenReturn();

    MatcherAssert.assertThat(response.getStatusCode(), org.hamcrest.Matchers.is(404));
}
}
