package practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLink1 {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for(WebElement link:links)
		{
			String url =link.getAttribute("href");
			
			if(url==null||url.isBlank()||!(url.contains("http")))
			{
				System.err.println("Broken link ->"+url);
				continue;
			}
			
			URL url1= new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
			httpURLConnection.connect();
			httpURLConnection.setConnectTimeout(5000);
			
			if(httpURLConnection.getResponseCode()!=200)
			{
				System.err.println("broken link found"+url+"->"+httpURLConnection.getResponseCode());
				
			}
			
			httpURLConnection.disconnect();	
			
		}
		
		
		driver.quit();
				
	}
	

}
