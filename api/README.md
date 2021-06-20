## Prerequisites
This project is powered by Spring Boot 2.5.1, Gradle as dependency manager and H2 as in-memory database. In order to work with the project we need Java 8 JDK

## Architecture
The architecture is composed of the following layers:
* Controller
* Service
* Repository

The repository layer works directly with database entities. Services read/write database entities using the repository layer but the input and output is done using the structure required by the controller (through Payload objects).
The controller will expose the endpoints and contact services.

## Endpoints
This API provides endpoints for:
* Create, read and update bugs
* Read statuses


## Error handling
I avoided using exceptions whenever possible using for it Java 8 Optional class, but to prevent 

## Build
When using IntelliJ IDEA opening the project will usually detect that this project uses Spring Boot as well as Gradle creating the necessary configuration to run the project from the IDE.

In case this is not possible we can always do it manually using the following commands inside the api folder

### `gradlew build`

Will build the application and pass the tests

### `gradlew bootRun`

Will build and initialize the project in the port 8080 allowing us to connect from the client project

## Tests
In order to execute the tests we need to use the following command inside the api folder

### `gradlew test`