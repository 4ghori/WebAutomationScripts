package com.msg.qa.common;

import Utility.SauceLabs.MSGSalesCenterSauceJobAccessController;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class SauceLabsGlobalFunctions {

	public static void main(String args[]) throws ClientProtocolException,
			ParseException, IOException, org.json.simple.parser.ParseException {
		SauceLabsGlobalFunctions mySauce = new SauceLabsGlobalFunctions();
		System.out.println(mySauce.getSauceLabsJobStatus(10, "jenkinscimsg",
				"76d8f272-7211-4054-8be1-bb972043d4eb"));
	}

	public ArrayList<HashMap<String, JsonElement>> getSauceLabsJobStatus(
			int jobLimit, String USERNAME, String ACCESS_KEY)
			throws ParseException, ClientProtocolException, IOException,
			org.json.simple.parser.ParseException {
		ArrayList<HashMap<String, JsonElement>> myRunInformation = new ArrayList<HashMap<String, JsonElement>>();
		JSONParser myJSONParser = new JSONParser();
		if (USERNAME == null && ACCESS_KEY == null) {
			MSGSalesCenterSauceJobAccessController mySauceRestObject = new MSGSalesCenterSauceJobAccessController(
					USERNAME, ACCESS_KEY);
			mySauceRestObject.getFullJobs(jobLimit);
			org.json.simple.JSONArray myJobs = (org.json.simple.JSONArray) myJSONParser
					.parse(mySauceRestObject.getFullJobs(jobLimit));
			for (int i = 0; i < myJobs.size(); i++) {
				org.json.simple.JSONObject myJSONObject = (org.json.simple.JSONObject) myJobs
						.get(i);
				String myJSONLine = myJSONObject.toJSONString();
				JsonElement myJSONElement = new JsonParser().parse(myJSONLine);
				JsonObject myJSONHeadObject = myJSONElement.getAsJsonObject();
				HashMap<String, JsonElement> myRunInfo = new HashMap<String, JsonElement>();
				for (Entry<String, JsonElement> myUniquePromoCode : myJSONHeadObject
						.entrySet()) {
					myRunInfo.put(myUniquePromoCode.getKey(),
							myUniquePromoCode.getValue());
				}
				myRunInformation.add(myRunInfo);
			}
		} else {
			USERNAME = "jenkinscimsg";
			ACCESS_KEY = "76d8f272-7211-4054-8be1-bb972043d4eb";
			MSGSalesCenterSauceJobAccessController mySauceRestObject = new MSGSalesCenterSauceJobAccessController(
					USERNAME, ACCESS_KEY);
			mySauceRestObject.getFullJobs(jobLimit);
			org.json.simple.JSONArray myJobs = (org.json.simple.JSONArray) myJSONParser
					.parse(mySauceRestObject.getFullJobs(jobLimit));
			for (int i = 0; i < myJobs.size(); i++) {
				org.json.simple.JSONObject myJSONObject = (org.json.simple.JSONObject) myJobs
						.get(i);
				String myJSONLine = myJSONObject.toJSONString();
				JsonElement myJSONElement = new JsonParser().parse(myJSONLine);
				JsonObject myJSONHeadObject = myJSONElement.getAsJsonObject();
				HashMap<String, JsonElement> myRunInfo = new HashMap<String, JsonElement>();
				for (Entry<String, JsonElement> myUniquePromoCode : myJSONHeadObject
						.entrySet()) {
					myRunInfo.put(myUniquePromoCode.getKey(),
							myUniquePromoCode.getValue());
				}
				myRunInformation.add(myRunInfo);
			}
		}
		return myRunInformation;
	}

}
