package Pets;

import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import pojo.Category;
import pojo.Pet;
import pojo.TagsItem;
import utils.RequestUtils;

import java.util.List;

public class PostPetById {
    private static Response response;
    private static Pet newPet;
    private static Pet updatePet;
    private static TagsItem tagsItem;
    private static Pet returnedPet;
    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String PATH = "/pet";

    @BeforeAll
    public static void setUp() {
        tagsItem = new TagsItem(0, "String");
        newPet = new Pet(
                81,
                "Greg",
                new Category(2, "Horse"),
                List.of("string"),
                List.of(tagsItem),
                "available"
        );
        updatePet = new Pet(
                81,
                "Oscar",
                new Category(2, "Dog"),
                List.of("string"),
                List.of(tagsItem),
                "pending"
        );

    }

    @Test
    @Order(1)
    @DisplayName("Add new pet to the store")
    public void addNewPet() {
        response = RestAssured
                .given(RequestUtils.petRequestSpec(BASE_URI, PATH, newPet))
                .when()
                .post()
                .thenReturn();
        returnedPet = response.as(Pet.class);
        MatcherAssert.assertThat(response.getStatusCode(), org.hamcrest.Matchers.is(200));
        MatcherAssert.assertThat(returnedPet.getName(), org.hamcrest.Matchers.is("Greg"));

    }

    @Test
    @Order(2)
    @DisplayName("Update pet in the store")
    public void updatePet() {
        response = RestAssured
                .given(RequestUtils.petRequestSpec(BASE_URI, PATH, updatePet))
                .when()
                .put()
                .thenReturn();
        returnedPet = response.as(Pet.class);
        MatcherAssert.assertThat(response.getStatusCode(), org.hamcrest.Matchers.is(200));
        MatcherAssert.assertThat(returnedPet.getName(), org.hamcrest.Matchers.is("Oscar"));
    }
}

