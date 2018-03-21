import com.msg.suites.pom.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MISCExtentTest {

	public static ExtentReports extent = ExtentManager.getInstance();
	public static ExtentTest test;
	//public static ExtentTest ChildTest;

	
	
	public static void main(String[] args) 
	{

		
		test = extent.startTest("Sample Test" + " - "+"This is a Test Description");
		test.log(LogStatus.INFO, "<mark>" +  "Sample Steps " + "</mark>" );

		
		ExtentTest ChildTest1;
		ChildTest1 = extent.startTest("Child 1" + " - "+"Child Test goes here ");
		ChildTest1.log(LogStatus.PASS, "PASS Steps 1");
		ChildTest1.log(LogStatus.PASS, "PASS Steps 2");

		
		ExtentTest ChildTest2;
		ChildTest2 = extent.startTest("Child 2" + " - "+"Child Test goes here ");
		ChildTest2.log(LogStatus.PASS, "PASS Steps 1");
		ChildTest2.log(LogStatus.FAIL, "Failed Steps 2");
		
		
		
		test.appendChild(ChildTest1);
		test.appendChild(ChildTest2);

		
		
		extent.endTest(test);
		extent.flush();

		
	}

}
