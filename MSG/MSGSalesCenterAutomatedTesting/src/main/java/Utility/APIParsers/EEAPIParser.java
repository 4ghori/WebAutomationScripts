/**
 *
 */
package Utility.APIParsers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author rastogir
 */
public class EEAPIParser {

	public String endPoint = null;

	public EEAPIParser(String endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EEAPIParser myEEAPIParser = new EEAPIParser("http://api.msg.com");

	}

	@SuppressWarnings({"deprecation", "resource"})
	public List<String> getListOfVenueNames(List<String> myPromoCodeList)
			throws IOException, ParseException {
		List<String> myVenueNames = new ArrayList<String>();

		DefaultHttpClient myDefaultHttpClient = new DefaultHttpClient();

		HttpGet myHttpGetRequest = new HttpGet(this.endPoint + "/v2/venues/");
		myHttpGetRequest.addHeader("accept", "application/json");

		HttpResponse myHttpResponse = myDefaultHttpClient
				.execute(myHttpGetRequest);

		if (myHttpResponse.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Http Error Code: "
					+ myHttpResponse.getStatusLine().getStatusCode());
		}

		BufferedReader myBufferedReader = new BufferedReader(
				new InputStreamReader(
						(myHttpResponse.getEntity().getContent())));

		JSONParser myJSONParser = new JSONParser();
		JSONObject myJSONObject = (JSONObject) myJSONParser
				.parse(myBufferedReader);
		String myJSONLine = myJSONObject.toJSONString();
		JsonElement myJSONElement = new JsonParser().parse(myJSONLine);

		JsonObject myJSONHeadObject = myJSONElement.getAsJsonObject();
		myJSONHeadObject = myJSONHeadObject.getAsJsonObject("results");
		JsonObject myJSONPromoCodes = myJSONHeadObject
				.getAsJsonObject("promo_codes");

		for (Entry<String, JsonElement> myUniquePromoCode : myJSONPromoCodes
				.entrySet())
			myPromoCodeList.add(myUniquePromoCode.getKey());

		myDefaultHttpClient.getConnectionManager().shutdown();

		return myVenueNames;
	}

}
