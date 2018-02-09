package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.support.FindBy;
import com.msg.qa.MSGOnlineDigital.Rockettes.RockettesGlobalParameters;

@SuppressWarnings("deprecation")
public class HelperScriptRockettesTMAPIParser
{
	static WebDriver driver;
	
	
	

@SuppressWarnings({ "deprecation", "resource" })
public static List<String> getPromoCodelist(List<String> myPromoCodeList) throws IOException, ParseException
{
	RockettesGlobalParameters rGP = new RockettesGlobalParameters(driver);

//	List<String> myPromoCodeList = new ArrayList<String>();
	
	DefaultHttpClient myDefaultHttpClient = new DefaultHttpClient();

	HttpGet myHttpGetRequest = new HttpGet(rGP.myAPIURL);
	myHttpGetRequest.addHeader("accept", "application/json");

	HttpResponse myHttpResponse = myDefaultHttpClient.execute(myHttpGetRequest);

	if (myHttpResponse.getStatusLine().getStatusCode() != 200) 
		{
	    throw new RuntimeException("Http Error Code: "+ myHttpResponse.getStatusLine().getStatusCode());
		}

	BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader((myHttpResponse.getEntity().getContent())));
	
	JSONParser myJSONParser = new JSONParser();
	JSONObject myJSONObject = (JSONObject) myJSONParser.parse(myBufferedReader);	
	String myJSONLine = myJSONObject.toJSONString();
	JsonElement myJSONElement = new JsonParser().parse(myJSONLine);
    
	JsonObject  myJSONHeadObject = myJSONElement.getAsJsonObject();
	myJSONHeadObject = myJSONHeadObject.getAsJsonObject("meta");   
    JsonObject myJSONPromoCodes = myJSONHeadObject.getAsJsonObject("promo_codes");

    for(Entry<String, JsonElement> myUniquePromoCode: myJSONPromoCodes.entrySet())
    		myPromoCodeList.add(myUniquePromoCode.getKey());
    
	myDefaultHttpClient.getConnectionManager().shutdown();
		
	return myPromoCodeList;
}	

}