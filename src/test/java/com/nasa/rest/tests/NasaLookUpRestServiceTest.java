package com.nasa.rest.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class NasaLookUpRestServiceTest extends NasaAbstractTest {


	@Test
	public void test_without_steriodId() {
		RestAssured.given()
			.pathParam("asteriodId", "")
			.param("api_key", API_KEY)
			.when()
			.get(NEO_LOOKUP_URL)
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);

	}


	@Test
	public void test_invalid_api_key() {
		Response response = given()
			.pathParam("asteriodId", "1234")
			.param("api_key", "gibberKEY")
			.when()
			.get(NEO_LOOKUP_URL);
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
			.pathParam("asteriodId", "1234")
			.when()
			.get(NEO_LOOKUP_URL);
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

		//validate the element count
		//validate near earth objects


		given()
			.pathParam("asteriodId", "3542519")
			.param("api_key", API_KEY)
			.when()
			.get(NEO_LOOKUP_URL)
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
			.body("name", equalTo("(2010 PK9)"))
			.body("orbital_data.orbit_id", Matchers.equalTo("23"))


		;
	}

}
