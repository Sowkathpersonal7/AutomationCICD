package Sowkath.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportNG {
	
	ExtentReports extent;
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+ "//reports//index.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("Sowkath Testing");
		spark.config().setReportName("Web Automation Testing");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Sowkath");
		extent.setSystemInfo("Environment", "QA");
		return extent;
		
		
	}

}
