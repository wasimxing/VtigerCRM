package practice;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XML_First {

		
		@Test
		public void demo(XmlTest xml) throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(xml.getParameter("url"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElement(By.name("user_name")).sendKeys(xml.getParameter("username"));
			driver.findElement(By.name("user_password")).sendKeys(xml.getParameter("password"));
			driver.findElement(By.id("submitButton")).click();
			Thread.sleep(5000);
			driver.quit();
		}

	}


