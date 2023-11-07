 package extentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportTest {
	public static void main(String[] args) throws IOException {
		ExtentReports report = new ExtentReports();
		
		File file = new File("report.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		spark.config().setReportName("Sample Test");
		spark.config().setDocumentTitle("Extent Reporting practice");
		spark.config().setTheme(Theme.STANDARD);
		
		report.attachReporter(spark);
		report.setSystemInfo("Author", "Wasim");
		report.setSystemInfo("OS", "windows");
		
		ExtentTest test = report.createTest("Test 1");
		test.log(Status.INFO, "this is sample test");
		report.createTest("Test2")
		.pass("This test is passed")
		.info("This is just an info");
		report.createTest("Test3")
		.fail("this is failed").info("this is just info");
		report.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());

		
		
	}

}
