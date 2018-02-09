package com.msg.qa.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class HelperScriptFetchURLs {
	public HashMap<String, String> ParseAPIResponse(String EnvFilter) {
		HashMap<String, String> myMap = new HashMap<String, String>();
		try {
			BufferedReader myBufferedReader = null;
			// 3. Read from shows Dump File if Event Engine Api and TM Api both
			// are down.
			File myFile = new File("./config/URLPageMapping.json");
			if (myFile.exists()) {
				myBufferedReader = new BufferedReader(new FileReader(myFile));
			}
			JSONParser myJSONParser = new JSONParser();
			JSONObject myJSONObject = (JSONObject) myJSONParser
					.parse(myBufferedReader);
			String myJSONLine = myJSONObject.toJSONString();
			JsonElement myJSONElement = new JsonParser().parse(myJSONLine);
			JsonObject myJSONHeadNodeObject = myJSONElement.getAsJsonObject();
			if (EnvFilter.equals("rockettes-prod")) {
				myJSONHeadNodeObject = myJSONHeadNodeObject
						.getAsJsonObject("rockettes-prod");
			}

			if (EnvFilter.equals("rockettes-staging")) {
				myJSONHeadNodeObject = myJSONHeadNodeObject
						.getAsJsonObject("rockettes-staging");
			}

			if (EnvFilter.equals("rockettes-dev")) {
				myJSONHeadNodeObject = myJSONHeadNodeObject
						.getAsJsonObject("rockettes-dev");
			}

			if (EnvFilter == null) {
				myJSONHeadNodeObject = myJSONHeadNodeObject
						.getAsJsonObject("rockettes-prod");
			}

			for (Entry<String, JsonElement> myelem : myJSONHeadNodeObject
					.entrySet()) {
				myMap.put(myelem.getKey().toString(),
						myelem.getValue().toString().substring(1,
								myelem.getValue().toString().length() - 1));
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return myMap;
	}
}
