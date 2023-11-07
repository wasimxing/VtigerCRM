package genericLibraries;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnerImplementation implements ITestListener {
	
	private ExtentReports report;
	private ExtentTest test;
	public static ExtentTest stest;
	
	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter("./VtigerExtentReports/extentreports");
		spark.config().setReportName("vtigerCRM");
		spark.config().setDocumentTitle("VtigerCRM Extent Reports");
		spark.config().setTheme(Theme.STANDARD);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", System.getProperty(""));
		
				
		System.err.println("onStart");
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		System.err.println("onTestStart");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.err.println("onTestSuccess");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		System.err.println("onTestFailure");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		System.err.println("ontestSkipped");
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		System.err.println("onFinish");
	}
	

}
