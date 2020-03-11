package com.nasa.rest.tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class NasaBrowserRestServiceTest extends NasaAbstractTest {


	@Test
	public void test_invalid_api_key() {
		Response response = given()

			.param("api_key", "gibberKEY")
			.when()
			.get(NEO_BROWSE_URL);
		response.then()
			.assertThat()
			.statusCode(HttpStatus.SC_FORBIDDEN)
			.body("error.code", Matchers.equalTo("API_KEY_INVALID"))

		;


	}

	//if I don't provide any API KEY
	//it will be forbidden
	// error code is api key missing
	@Test
	public void test_without_api_key() {
		Response response = given()
			.when()
			.get(NEO_BROWSE_URL);
		response.then()
			.assertThat()
			.statusCode(HttpStatus.SC_FORBIDDEN)
			.body("error.code", Matchers.equalTo("API_KEY_MISSING"))

		;


	}

	//if I provide valid parameter
	//then it will with come OK response
	// data will be validated.
	@Test
	public void test_valid_lookup_with_data() {


		given()
			.param("api_key", API_KEY)
			.when()
			.get(NEO_BROWSE_URL)
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
			.body("near_earth_objects.size()", Matchers.is(20))
			//check the data
			.body("near_earth_objects.get(0).neo_reference_id", Matchers.equalTo("2021277"))


		;
	}

}
