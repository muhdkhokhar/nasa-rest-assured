# nasa-rest-assured
This is the project that contains the Test cases for NASA Open API for Asteroids - NeoWs

All the API are documents here https://api.nasa.gov/

## Neo - Feed

Retrieve a list of Asteroids based on their closest approach date to Earth. 

GET https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY 

## Neo - Lookup

Lookup a specific Asteroid based on its NASA JPL small body (SPK-ID) ID GET https://api.nasa.gov/neo/rest/v1/neo/ 

## Neo - Browse

Browse the overall Asteroid data-set GET https://api.nasa.gov/neo/rest/v1/neo/browse/ 

### Frameworks for testing

RestAssured

All the test cases are available in the package : com.nasa.rest.tests

## Tech Stack

* Java 8
* Maven
* Junit
* Rest Assured
