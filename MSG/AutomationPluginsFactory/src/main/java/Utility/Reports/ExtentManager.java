package Utility.Reports;

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html

/**
 * Utility class for Extent Report For Reporting
 *
 * @author MSG QA Automation Team
 * @version 1.0
 * @category MSG Automation Framework Reports Manager 
 * @since 02/08/2018, 10:00AM EST
 */

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.util.Date;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_")
					+ ".html";
			String reportPath = Utility.Parsers.Constants.REPORTS_PATH
					+ fileName;

			extent = new ExtentReports(reportPath, true,
					DisplayOrder.NEWEST_FIRST);

			extent.loadConfig(new File(
					System.getProperty("user.dir") + "//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "3.0.1")
					.addSystemInfo("Environment", "QA");
		}
		return extent;
	}
}
