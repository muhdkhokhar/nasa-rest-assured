package com.nasa.rest.client.service.impl;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class NasaNeoFeedRestServiceTest {
	private static final String FEED_BASE_URL_HTTPS = "https://api.nasa.gov/neo/rest/v1/feed";
	private static final String API_KEY = "uPHcU8J6Rzt3uF8mqv5y4oMvhGusOhp4kmtTLkRC";
	public static final String START_DATE = "2020-01-01";
	public static final String END_DATE = "2020-01-02";

	//This test case check if we don't provide the start date
	//result: The api will fail and return BAD Request response
	@Test
	public void test_without_startDate_bad_request() {
		RestAssured.given()
			.param("end_date", END_DATE)
			.param("api_key", API_KEY)
			.when()
			.get(FEED_BASE_URL_HTTPS)
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_BAD_REQUEST);

	}

	//if we don't provide  start and end date
	//it will give you result with status ok
	@Test
	public void test_without_start_end_date_valid_data() {
		given()

			.param("api_key", API_KEY)
			.when()
			.get(FEED_BASE_URL_HTTPS)
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK);

	}

	//if I provide wrong api KEY
	  //it will not allow me to access the api with respone FORBIDDEN
	 //the body will have error with code api key invalid
	@Test
	public void test_invalid_api_key() {
		Response response = given()

			.param("api_key", "gibberKEY")
			.when()
			.get(FEED_BASE_URL_HTTPS);
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
			.get(FEED_BASE_URL_HTTPS);
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
	public void testNeoFeed_valid_dates() {

		//validate the element count
		//validate near earth objects


		given().param("start_date", START_DATE)
			.param("end_date", END_DATE)
			.param("api_key", API_KEY)
			.when()
			.get(FEED_BASE_URL_HTTPS)
			.then()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
			.body("element_count", equalTo(31))
			.body("near_earth_objects.size()", Matchers.is(2))
			.body("near_earth_objects.get('2020-01-01').size()", Matchers.is(12))
			.body("near_earth_objects.get('2020-01-02').size()", Matchers.is(19))
			//check the data
			.body("near_earth_objects.get('2020-01-02').get(0).neo_reference_id", Matchers.equalTo("3971141"))
			.body("near_earth_objects.get('2020-01-02').get(0).name", Matchers.equalTo("(2019 YO6)"))
			.body("near_earth_objects.get('2020-01-02').get(0).nasa_jpl_url", Matchers.equalTo("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3971141"))

			.body("near_earth_objects.get('2020-01-02').get(0).estimated_diameter.kilometers.estimated_diameter_min", Matchers.equalTo(0.09650615f))


		;
	}

}
