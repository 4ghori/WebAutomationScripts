package com.msgqa.selenium.Keyword;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.http.Method;


//==== API Call Using URL Connection
	import java.net.URLConnection;
	import java.util.ArrayList;
	import java.util.Map.Entry;
import java.net.MalformedURLException;
	import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
	import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;



public class Restassured2_Backups {
	
	@Test
	public void GetWeatherDetails()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}

	
	/*@Test
	public void GetWeatherDetails_Malinator_UrlConnection_AndRachitHTTP()
	{   

        try 
        {
			//URLConnection connection = new URL("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").openConnection();
			//URLConnection connection = new URL("http://qa-api.msg.com/v2/events/3C0052D2FE330E9C").openConnection();
			//URLConnection connection = new URL("https://api.msg.com/v2/venues/").openConnection();

			DefaultHttpClient myDefaultHttpClient = new DefaultHttpClient();
			HttpGet myHttpGetRequest = new HttpGet("https://api.msg.com/v2/venues/");
			myHttpGetRequest.addHeader("accept", "application/json");
			HttpResponse myHttpResponse = myDefaultHttpClient.execute(myHttpGetRequest);
			BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader((myHttpResponse.getEntity().getContent())));

			
			
	        //InputStream response = connection.getInputStream();
	        //Reader reader = new InputStreamReader(response);
	        
			JSONParser myJSONParser = new JSONParser();
			JSONObject myJSONObject = null;
			try 
			{
				myJSONObject = (JSONObject) myJSONParser.parse(myBufferedReader);
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
			
			String myJSONLine = myJSONObject.toJSONString();
			JsonElement myJSONElement = new JsonParser().parse(myJSONLine);

			JsonObject myJSONHeadObject = myJSONElement.getAsJsonObject();
			myJSONHeadObject = myJSONHeadObject.getAsJsonObject("results");
			JsonObject myJSONPromoCodes = myJSONHeadObject.getAsJsonObject("promo_codes");

			for (Entry<String, JsonElement> myUniquePromoCode : myJSONPromoCodes.entrySet())	
			{
				System.out.println(myUniquePromoCode.getKey());
			}
				
				//myPromoCodeList.add(myUniquePromoCode.getKey());
				//myDefaultHttpClient.getConnectionManager().shutdown();
				//return myVenueNames;

	        
	        
	        
	        
	        
	        //ArrayList<InboxMessage> messages = new ArrayList<>();
	        JSONObject obj = (JSONObject) JSONValue.parse(reader);
	        JSONArray jsonMessages = (JSONArray) obj.get("results/artists"); //City array    {"Temperature":"15 Degree celsius","WindDirectionDegree":"140 Degree","Humidity":"82 Percent","WeatherDescription":"mist","WindSpeed":"1.5 Km per hour","City":"Hyderabad"}
	  
	        if(jsonMessages != null) 
	        {
	          //Parse the messages, make the POJOs and add them to our custom list.
	          for (Object jsonMsg : jsonMessages) 
	          {
	              //InboxMessage message = createInboxMessageFrom((JSONObject) jsonMsg);
	              //messages.add(message);
	        	  		System.out.println(jsonMsg);
	          }
	  
	          //The messages come in the response in a reversed order, so lets give 
	          //back an ordered list
	          //Collections.reverse(messages);
	        }
	        
		} 
        catch (MalformedURLException e) 
        {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
	
	
	
	
	
	public static void main(String[] args) 
	{
		/*// Specify the base URL to the RESTful web service
		RestAssured myrestAssured = new RestAssured();
		myrestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);*/

	}

}
