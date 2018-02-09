package com.msg.qa.MSGOnlineDigital.SalesCenter;

import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MSGSalesCenterURLs {
	
	private static final Logger LOGGER = Logger
			.getLogger(MSGSalesCenterURLs.class);
	
	private static final Map<String, String> ENVIRONMENT_URLS;
	
	static {
		String baseURLTemplate = "https://%s.msg.com";
		Map <String, String> environmentURLs = new HashMap<>();
		
		environmentURLs.put("staging", String.format(baseURLTemplate, "staging"));
		environmentURLs.put("devcomrelease", String.format(baseURLTemplate, "devcomrelease"));
		environmentURLs.put("qacomrelease", String.format(baseURLTemplate, "qacomrelease"));
		environmentURLs.put("uatcomrelease", String.format(baseURLTemplate, "uatcomrelease"));
		environmentURLs.put("softlaunch", String.format(baseURLTemplate, "softlaunch"));
		environmentURLs.put("green", String.format(baseURLTemplate, "uat"));
		environmentURLs.put("blue", String.format(baseURLTemplate, "www"));
		environmentURLs.put("production", String.format(baseURLTemplate, "www"));
		
		ENVIRONMENT_URLS = Collections.unmodifiableMap(environmentURLs);
	}
	
	public static Map<String, String> getEnvironmentBaseURLs() {
		return ENVIRONMENT_URLS;
	}
	
	public static String getEnvironmentBaseURL(String environment) {
		return getEnvironmentBaseURLs().get(environment);
	}
	
	public static String getTestEnvironment() {
		String testEnvironment = System.getProperty("testEnvironment");
		return testEnvironment != null ? testEnvironment : "production";
	}

	public static HashMap<String, String> getMSGURLs(String testEnvironment) {
		HashMap<String, String> myURLs = new HashMap<String, String>();
		String host;
		switch (testEnvironment) {
			case "staging" :
				host = "staging";
				break;
			case "devcomrelease" :
				host = "devcomrelease";
				break;
			case "qacomrelease" :
				host = "qacomrelease";
				break;
			case "uatcomrelease" :
				host = "uatcomrelease";
				break;
			case "softlaunch" :
				host = "softlaunch";
				break;				
			case "green" :
				host = "uat";
				break;				
			case "blue" :
				host = "www";
				break;
			default :
				host = "www";
				break;
		}

		myURLs.put("MSGTC000LandingPage",
				String.format("https://%s.msg.com", host));
		myURLs.put("MSGTC002VenueLandingPage", String
				.format("https://%s.msg.com/madison-square-garden", host));

		myURLs.put("MSGSalesCenterTC001VenueRentalsPage",
				String.format("https://%s.msg.com/venue-rentals", host));
		myURLs.put("MSGSalesCenterTC003CorporateEntertainmentPage", String
				.format("https://%s.msg.com/corporate-entertainment", host));
		myURLs.put("MSGSalesCenterTC004PremiumHospitalityPage",
				String.format("https://%s.msg.com/premium-hospitality", host));
		myURLs.put("MSGSalesCenterTC005GroupOutingsPage",
				String.format("https://%s.msg.com/group-outings", host));

		/**
		 * New URLs added for handling L3 pages and Knicks/Rangers Tickets Added
		 * by: Rachit kumar Rastogi, date: 09/15/17
		 */
		myURLs.put("MSGTC006CalendarPage",
				String.format("https://%s.msg.com/calendar", host));
		myURLs.put("MSGTC007EventDetailsPage", String.format(
				"https://%s.msg.com/venue-rentals/madison-square-garden/",
				host));
		myURLs.put("MSGSalesCenterTC008VenueRentalsMSGRadioCityPage",
				String.format(
						"https://%s.msg.com/venue-rentals/radio-city-music-hall",
						host));
		myURLs.put("MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage",
				String.format(
						"https://%s.msg.com/premium-hospitality/msg-event-level-suites",
						host));
		myURLs.put("MSGSalesCenterTC010VenueToursPage",
				String.format("https://%s.msg.com/venue-tours/", host));
		myURLs.put("MSGSalesCenterTC011PremHospMSGKnicksPage", String.format(
				"https://%s.msg.com/premium-hospitality/msg-knicks-locker-room",
				host));

		myURLs.put("MSGTC012KnicksTicketsPage",
				String.format("https://%s.msg.com/knicks-tickets", host));
		myURLs.put("MSGTC013RangersTicketsPage",
				String.format("https://%s.msg.com/rangers-tickets", host));
		myURLs.put("MSGTC014MSGVenueGuidePage",
				String.format("https://%s.msg.com/madison-square-garden/venue-guide/", host));
		myURLs.put("MSGTC015SearchPage",
				String.format("https://%s.msg.com/search?q=", host));
		return myURLs;
	}
}
