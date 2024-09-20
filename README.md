##Pet store v3 API Testing Project
This is an individual project I embarked on inorder to practice my recent java automation testing upskilling course. The aim of this project was to test the functionality of the Petstore3 API, 
using RestAssured to perform comprehensive coverage of the available endpoints of the API and identify defects which have been highlighted in the project kanban board.

#Project Setup
Project Setup
1. Clone this repository onto your local system

2.Set up config.properties
This project comes without a config.properties to avoid sharing sensitive data. This must be set up manually by following the follwoing steps:
i. Create a resources folder within the test directory

ii. Create a config.properties file containing the following:
base.uri=https://petstore.swagger.io/v2

iii. Save an image of a dog in the same resources folder and save it as "DogImage.jpeg" - to test the image upload functionality on the API

##Using the Framework
src ├── main │ ├── java │ │ ├── config # Configuration classes and settings │ │ ├── Pets # Pet-related functionalities and business logic │ │ ├── pojo # Plain Old Java Objects (data models) │ │ ├── Store # Store operations like inventory and orders │ │ ├── User # User management and profiles │ │ └── utils # Utility classes and helper functions │ └── resources │ ├── config.properties # Application configuration settings │ └── DogImage.jpeg # Sample image for testing/demo ├── test # Test classes for the application's functionalities └── target # Build output (generated, not version-controlled)


