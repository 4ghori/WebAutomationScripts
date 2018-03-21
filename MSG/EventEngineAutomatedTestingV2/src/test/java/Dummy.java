import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msg.suites.pom.util.FBConstants;

public class Dummy {
  //public String value1 = "foo";
  //public int value2 = 42;
  //public Integer value3 = new Integer(43);
  //private String value4 = "bar";
  
  public String AdminTabe = "Xpath Of Admin Tab";  
  public String UserTabe = "Xpath Of User Tab";  

	@FindBy(xpath=FBConstants.Suit_QA_LoginButton)
	public WebElement AdimTab;
  
}