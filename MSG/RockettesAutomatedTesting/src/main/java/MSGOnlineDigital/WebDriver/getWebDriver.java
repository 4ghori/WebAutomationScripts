package MSGOnlineDigital.WebDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import MSGOnlineDigital.AutomationUtilities.GlobalParameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;
 
public class getWebDriver extends GlobalParameters{
 
                /**
                * @author Rachit Kumar Rastogi
                * @return
                 * @throws MalformedURLException
                * @detail Method to fetch the single webdriver by reading the desired capabilities from Jenkins
                *                                RemoteInstance Method assumes that build is triggered from Jenkins else it will return an empty driver.
                */
                @Test(description="Get Single Remote Webdriver Instance")
                public static WebDriver JenkinsSauceConnectRemoteInstance() throws MalformedURLException {
                                WebDriver driver = null;
 
                                // Handling the case for Single browser Selection in Jenkins
                                if(System.getenv("SELENIUM_BROWSER").toString().equals("firefox"))
                                {
                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.firefox();         
                                                myBrowserCapability.setBrowserName(System.getenv("SELENIUM_BROWSER"));
                                                myBrowserCapability.setVersion(System.getenv("SELENIUM_VERSION"));
                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
                                                String URL = URLwithEnvVariables;
                                                driver = new RemoteWebDriver(new URL(URL),myBrowserCapability);
                                }
                               
                                if(System.getenv("SELENIUM_BROWSER").toString().equals("chrome"))
                                {
                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.chrome();       
                                                myBrowserCapability.setBrowserName(System.getenv("SELENIUM_BROWSER"));
                                                myBrowserCapability.setVersion(System.getenv("SELENIUM_VERSION"));
                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
                                                String URL = URLwithEnvVariables;
                                                driver = new RemoteWebDriver(new URL(URL),myBrowserCapability);
                                }
                                if(System.getenv("SELENIUM_BROWSER").toString().equals("safari"))
                                {
                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.safari();           
                                                myBrowserCapability.setBrowserName(System.getenv("SELENIUM_BROWSER"));
                                                myBrowserCapability.setVersion(System.getenv("SELENIUM_VERSION"));
                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
                                                String URL = URLwithEnvVariables;
                                                driver = new RemoteWebDriver(new URL(URL),myBrowserCapability);
                                }
                                if(System.getenv("SELENIUM_BROWSER").toString().equals("edge"))
                                {
                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.edge();            
                                                myBrowserCapability.setBrowserName(System.getenv("SELENIUM_BROWSER"));
                                                myBrowserCapability.setVersion(System.getenv("SELENIUM_VERSION"));
                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
                                                String URL = URLwithEnvVariables;
                                                driver = new RemoteWebDriver(new URL(URL),myBrowserCapability);
                                }
                                if(System.getenv("SELENIUM_BROWSER").toString().equals("ie"))
                                {
                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.internetExplorer();      
                                                myBrowserCapability.setBrowserName(System.getenv("SELENIUM_BROWSER"));
                                                myBrowserCapability.setVersion(System.getenv("SELENIUM_VERSION"));
                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
                                                String URL = URLwithEnvVariables;
                                                driver = new RemoteWebDriver(new URL(URL),myBrowserCapability);
                                }
                                return driver;
                }
 
/**
* @author Rachit Kumar Rastogi
* @param myDrivers
* @return
* @throws MalformedURLException
* @throws ParseException
* @details Get all webdrivers specified in Jenkins
*/
                @Test(description="Get multiple Remote Webdriver Instances")
                public static List<WebDriver> JenkinsSauceConnectRemoteInstances(List<WebDriver> myDrivers) throws MalformedURLException, ParseException
                {
                                JsonElement myJSONElement = new JsonParser().parse(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
                    JsonArray   myJSONPlatforms = (JsonArray) myJSONElement.getAsJsonArray();
                    System.out.println(myJSONPlatforms.toString());
   
                    for (int j=0;j<myJSONPlatforms.size();j++)
                {
                                String  myPlatform = null, myOS = null, myBrowser = null, myBrowserVersion = null, mySLURL = null;
                               
                                for(Entry<String, JsonElement> myelem : myJSONPlatforms.get(j).getAsJsonObject().entrySet())
                                {              
                                                // 1. Parse the JSON of All SauceLabs Environment variables
                                                if (myelem.getKey().toString().equals("platform"))
                                                {
                                                                myPlatform = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
                                                }
                                                if (myelem.getKey().toString().equals("os"))
                                                {
                                                                myOS = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
                                                }
                                                if (myelem.getKey().toString().equals("browser"))
                                                {
                                                                myBrowser = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
                                                }
                                                if (myelem.getKey().toString().equals("browser-version"))
                                                {
                                                                myBrowserVersion = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
                                                }
                                                if (myelem.getKey().toString().equals("url"))
                                                {
                                                                mySLURL = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
                                                }
               
                                                if(myPlatform != null && myOS != null && myBrowser != null && myBrowserVersion != null && mySLURL != null)
                                                {
                                               
                                                // 2. Once we get all variables then we can figure out the respective driver to be triggered.
                                                if(myBrowser.equals("firefox"))
                                                {
                                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.firefox();         
                                                                myBrowserCapability.setBrowserName(myBrowser);
                                                                myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
                                                                myBrowserCapability.setCapability("os", myOS);
                                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
                                                                String URL = URLwithEnvVariables;
                                                                myDrivers.add(new RemoteWebDriver(new URL(URL),myBrowserCapability));
                                                }
                                               
                                                if(myBrowser.equals("chrome"))
                                                {
                                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.chrome();       
                                                                myBrowserCapability.setBrowserName(myBrowser);
                                                                myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
                                                                myBrowserCapability.setCapability("os", myOS);                                                     
                                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
                                                                String URL = URLwithEnvVariables;
                                                                myDrivers.add(new RemoteWebDriver(new URL(URL),myBrowserCapability));
                                                }
                                                if(myBrowser.equals("safari"))
                                                {
                                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.safari();           
                                                                myBrowserCapability.setBrowserName(myBrowser);
                                                                myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
                                                                myBrowserCapability.setCapability("os", myOS);                                                     
                                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
                                                                String URL = URLwithEnvVariables;
                                                                myDrivers.add(new RemoteWebDriver(new URL(URL),myBrowserCapability));
                                                }
                                                if(myBrowser.equals("edge"))
                                                {
                                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.edge();            
                                                                myBrowserCapability.setBrowserName(myBrowser);
                                                                myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
                                                                myBrowserCapability.setCapability("os", myOS);                                                     
                                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
                                                                String URL = URLwithEnvVariables;
                                                                myDrivers.add(new RemoteWebDriver(new URL(URL),myBrowserCapability));
                                                }
                                                if(myBrowser.equals("ie"))
                                                {
                                                                DesiredCapabilities myBrowserCapability = DesiredCapabilities.internetExplorer();      
                                                                myBrowserCapability.setBrowserName(myBrowser);
                                                                myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
                                                                myBrowserCapability.setCapability("os", myOS);                                                     
                                                                myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
                                                                String URL = URLwithEnvVariables;
                                                                myDrivers.add(new RemoteWebDriver(new URL(URL),myBrowserCapability));
                                                }                              
                                                }                                              
                                }
                                System.out.println("Our selected paramters are: Platform = "+myPlatform+" OS = "+myOS+" Browser = "+myBrowser+" BrowserVersion = "+myBrowserVersion);
                }
                   
                    System.out.println("Total Drivers Selected by end users are: "+myDrivers.size());
                                return myDrivers;
                }
               
                @Test(description="Get Local Webdriver Instance")
                public static WebDriver LocalChromeInstance(String URL) {
                                System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
                                WebDriver driver = new ChromeDriver();
                                driver.get(URL);
                                return driver;
                }
               
}
 