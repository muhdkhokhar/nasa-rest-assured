package com.nasa.rest.tests;

/**
 * This is abstract class to keep any common constants or method
 * This follow DRY rule (Don't repeat yourself)
 */
public abstract class NasaAbstractTest {


	protected static final String NEO_FEED_URL = "https://api.nasa.gov/neo/rest/v1/feed";

	protected static final String NEO_LOOKUP_URL = "https://api.nasa.gov/neo/rest/v1/neo/{asteriodId}";

	protected static final String API_KEY = "uPHcU8J6Rzt3uF8mqv5y4oMvhGusOhp4kmtTLkRC";
	protected static final String START_DATE = "2020-01-01";
	protected static final String END_DATE = "2020-01-02";

}
