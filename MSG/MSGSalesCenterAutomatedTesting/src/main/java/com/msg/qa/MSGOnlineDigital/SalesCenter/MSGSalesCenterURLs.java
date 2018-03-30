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

		/**
		 * URL-0: msg.com Landing Page.
		 */
		myURLs.put("MSGTC000LandingPage",String.format("https://%s.msg.com", host));
		
		/**
		 * URL-1: msg.com Venue Rentals Page. 
		 */
		myURLs.put("MSGSalesCenterTC001VenueRentalsPage",String.format("https://%s.msg.com/venue-rentals", host));
		
		/**
		 * URL-2: msg.com Venue Landing Page.
		 */
		myURLs.put("MSGTC002VenueLandingPage", String.format("https://%s.msg.com/madison-square-garden", host));
		
		/**
		 * URL-3: Corporate Entertainment Page.
		 */
		myURLs.put("MSGSalesCenterTC003CorporateEntertainmentPage", String.format("https://%s.msg.com/corporate-entertainment", host));
		
		/**
		 * URL-4: Premium Hospitality Page.
		 */
		myURLs.put("MSGSalesCenterTC004PremiumHospitalityPage",String.format("https://%s.msg.com/premium-hospitality", host));
		
		/**
		 * URL-5: Group Outings Page.
		 */
		myURLs.put("MSGSalesCenterTC005GroupOutingsPage",String.format("https://%s.msg.com/group-outings", host));

		/**
		 * URL-6: Calendar Page.
		 */
		myURLs.put("MSGTC006CalendarPage",String.format("https://%s.msg.com/calendar", host));
		
		/**
		 * URL-7: Event Details Page.
		 */
		myURLs.put("MSGTC007EventDetailsPage", String.format("https://%s.msg.com/calendar/madison-square-garden-august-2017-billy-joel-in-concert",host));
		
		/**
		 * URL-8: Venue Rentals Radio City Page.
		 */
		myURLs.put("MSGSalesCenterTC008VenueRentalsMSGRadioCityPage",String.format("https://%s.msg.com/venue-rentals/radio-city-music-hall",host));
		
		/**
		 * URL-9: Premium Hospitality -> Event Level Suites Page.
		 */
		myURLs.put("MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage",String.format("https://%s.msg.com/premium-hospitality/msg-event-level-suites",host));
		
		/**
		 * URL-10: Venue Tours Page.
		 */
		myURLs.put("MSGSalesCenterTC010VenueToursPage",String.format("https://%s.msg.com/venue-tours/", host));
		
		/**
		 * URL-11: Premium Hospitality -> Knicks Page.
		 */
		myURLs.put("MSGSalesCenterTC011PremHospMSGKnicksPage", String.format("https://%s.msg.com/premium-hospitality/msg-knicks-locker-room",host));
		
		/**
		 * URL-12: Ticket Central -> Knicks Tickets Page.
		 */
		myURLs.put("MSGTC012KnicksTicketsPage",String.format("https://%s.msg.com/knicks-tickets", host));
		
		/**
		 * URL-13: Tickets Central -> Rangers Tickets Page.
		 */
		myURLs.put("MSGTC013RangersTicketsPage",String.format("https://%s.msg.com/rangers-tickets", host));
		
		/**
		 * URL-14: Venue Guides Page.
		 */
		myURLs.put("MSGTC014MSGVenueGuidePage",String.format("https://%s.msg.com/madison-square-garden/venue-guide/", host));
		
		/**
		 * URL-15: Search Page.
		 */
		myURLs.put("MSGTC015SearchPage",String.format("https://%s.msg.com/search?q=", host));
		
		return myURLs;
	}
}
