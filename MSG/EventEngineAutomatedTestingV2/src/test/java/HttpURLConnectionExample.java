import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.msg.suites.pom.util.FBConstants;

public class HttpURLConnectionExample 
{

	private final String USER_AGENT = "Mozilla/5.0";
	public static WebDriver driver;


	public static void main(String[] args) throws Exception 
	{

		//user_login();
		
		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}

	// HTTP GET request
	private void sendGet() throws Exception {

		//String url = "http://www.google.com/search?q=mkyong";
		String url = "https://qa1.mymsgsuites.com/";

		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	
	
	
	
	
	
	public static void user_login()
	{
    	System.setProperty("webdriver.chrome.driver", FBConstants.CHROME_DRIVER_EXE);
		driver= new ChromeDriver();

		
	/*	String Url = "https://qa1.mymsgsuites.com/";
		String Username = "shadmin@mailinator.com";
		String Password = "Suites123";
		driver.get(Url); //Loading the Direct URL From EXCEL FIle 
		WebElement email = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginUserID_Field));
		WebElement password = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginPassword_Field));
		WebElement loginButton = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginButton));
		email.sendKeys(Username);
		password.sendKeys(Password);
		//password.sendKeys(Keys.ENTER);  // This is can be used only 
		loginButton.click();
		fwait();
		//super.hardWait();
		*/
	}

	public static void fwait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
}