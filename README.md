## Pet Store v3 API Testing Project

This is an individual project I embarked on in order to practice my recent Java automation testing upskilling course. The aim of this project was to test the functionality of the Petstore v3 API using RestAssured to perform comprehensive coverage of the available endpoints of the API and identify defects, which have been highlighted in the project Kanban board.

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
build the requests that are sent to the API, and finally `Pets', `Store` & `User`packages contain the test classes separated by their functioality (eg. GET, POST, PUT & DELETE)
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


