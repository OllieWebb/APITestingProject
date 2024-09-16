package Pets;

import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;

public class UploadImageByID {

    private static Response response;
    protected static final String BASE_URI = AppConfig.getBaseUri();
    private static final String PATH = "/pet/{petId}/uploadImage";
    private static final int PET_ID = 10;

    File validImage = new File("src/test/resources/DogImage.jpeg");
    //Happy: Valid image is uploaded
        @Test
        @DisplayName("Upload image to an existing pet")
        public void addAnImageToAnExistingPet() {
            // Perform the file upload as multipart
            response = RestAssured
                    .given()
                    .baseUri(BASE_URI)
                    .basePath(PATH)
                    .pathParam("petId", PET_ID)  // Replace with your actual pet ID
                    .multiPart("file", validImage)  // Name of the part might need to be "file"
                    .when()
                    .post()
                    .thenReturn();

            // Print response for debugging
            response.prettyPrint();

            // Assert successful upload (status code 200)
            MatcherAssert.assertThat(response.getStatusCode(), org.hamcrest.Matchers.is(200));
        }
}
