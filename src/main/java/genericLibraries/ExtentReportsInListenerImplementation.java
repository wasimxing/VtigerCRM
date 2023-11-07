package genericLibraries;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsInListenerImplementation implements ITestListener {
	private ExtentReports report;
	private ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {

		Capabilities cap = ((RemoteWebDriver) BaseClass.sdriver).getCapabilities();
		report.setSystemInfo("Browser", cap.getBrowserName() + " " + cap.getBrowserVersion());

		test = report.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName() + "Pass");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		test.fail(result.getMethod().getMethodName() + "Fail");
		WebdriverUtility web = new WebdriverUtility();
		String path = web.getScreenshot(BaseClass.sjavaUtil, result.getMethod().getMethodName(), BaseClass.sdriver);
		test.addScreenCaptureFromPath(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName() + "Skipped");
		test.skip(result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		File file = new File("./VtigerExtentReport/createAndDuplicate.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		spark.config().setReportName("VtigerCRM");
		spark.config().setDocumentTitle("VtigerExtentReport");
		spark.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Author", "Wasim");
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("java", System.getProperty("java.version"));
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();

	}

}
