package xmlParametrization;



import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XmlParametrization {
	@Test
	public void loginTest(XmlTest xml)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wasim\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(xml.getParameter("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String username =xml.getParameter("user");
		String password = xml.getParameter("password");
		
		driver.findElement(By.id("username")).sendKeys(username+Keys.TAB+password+Keys.ENTER);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean status =wait.until(ExpectedConditions.titleContains("Enter Time-Track"));
		
		Assert.assertTrue(status);
		
		driver.quit();
		
		
		
	}

}
