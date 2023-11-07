package xmlParametrization;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * actitime
 * 
 * @author wasim
 *
 */


public class Step1_WriteTestScriptXML {
	@Test
	public void loginTest(XmlTest xml) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wasim\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(xml.getParameter("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("username")).sendKeys(xml.getParameter("user"));
		driver.findElement(By.name("pwd")).sendKeys(xml.getParameter("password"));

		driver.findElement(By.id("loginButton")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		boolean status = wait.until(ExpectedConditions.titleContains("Enter Time-Track"));

		if (status)
			System.out.println("pass");
		else
			System.out.println("fail");

		/**
		 * step 1 : write script 
		 * step 2 : convert test class to xml right click --->
		 * testNG ----> convert to testNG ---> change xml file name ---> finish
		 * 
		 * step 3: <parameter name ="url" value="https://demo.actitime.com/login.do"></parameter>
		 * <parameter name="user" value="admin" ></parameter>
		 * <parameter name="password" value="manager" ></parameter>
		 * 
		 * 
		 */

		driver.quit();

	}

}
