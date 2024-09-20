## Pet Store v3 API Testing Project

This is an individual project I embarked on in order to practice my recent Java automation testing upskilling course. The aim of this project was to test the functionality of the [Petstore v3 API](https://petstore3.swagger.io/) using RestAssured to perform comprehensive coverage of the available endpoints of the API and identify defects, which have been highlighted in the project Kanban board.

### Project Setup

1. **Clone this repository onto your local system.**

2. **Set up `config.properties`**
   This project comes without a `config.properties` file to avoid sharing sensitive data. Set this up manually by following these steps:
   
   i. Create a `resources` folder within the `test` directory.
   
   ii. Create a `config.properties` file containing the following:
   
   ```properties
   base.uri=https://petstore.swagger.io/v2

## Framework
The framework is first made up of a config class which allows the program to access the protected data from the `config.properties` file such as the uri as well as
a POJO packagage which defines all the different objects the program needs to interact with in order to test the API. 

The `RequestUtils` file houses abstracted methods that
build the requests that are sent to the API, and finally `Pets`, `Store` & `User`packages contain the test classes separated by their functioality (eg. GET, POST, PUT & DELETE)
```
src
├── main
│ └── java
│   ├── config              # Configuration classes and settings
│   ├── Pets                # Pet-related functionalities and business logic
│   ├── pojo                # Plain Old Java Objects (data models) 
│   ├── Store               # Store-related functionalities and business logic
│   ├── User                # User-related functionalities and business logic
│   └── utils              
|     └── RequestsUtils     # Utility class
└── resources
  ├── config.properties     # Application configuration settings
  └── DogImage.jpeg
```
### Testing Walkthrough
Each testing class consists of a setUp() method, which calls `RestAssured` methods and saves them in the appropriate response to outline the potential happy 
and sad paths.
```
@BeforeAll
    public static void beforeAll(){
        //Happy
        successResponse = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH,Map.of("petId", PET_ID)))
                .when().get().thenReturn();
        foundPet = successResponse.as(Pet.class);
        //Sad
        notFoundResponse = RestAssured.given(RequestUtils.getRequestSpec(BASE_URI, PATH,Map.of("petId", "999999")))
                .when().get().thenReturn();
    }
```
After this the various tests are carried out, where the status codes and contents of the response body is returned as expected, in order to identify defects.
```
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
```

