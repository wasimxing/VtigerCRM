package practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinkTest {
	@Test
	public void brokenLink() throws IOException {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\wasim\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		for(WebElement link: links)
		{
			String linkURL = link.getAttribute("href");
			if(linkURL==null|| linkURL.isBlank()||!(linkURL.contains("http"))) {
			
				System.err.println(linkURL+ "-> Broken link");
				continue;
		}
			
			URL url = new URL(linkURL);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//abstract class
			httpURLConnection.connect();
			httpURLConnection.setConnectTimeout(5000);
			
			if(httpURLConnection.getResponseCode() !=200)
			
				System.err.println(linkURL+ "->"+ httpURLConnection.getResponseCode());
			httpURLConnection.disconnect();
			
		}
		driver.quit();
	}
	
	
}
