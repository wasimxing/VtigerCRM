package practice;



import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import genericLibraries.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SystemProperties {
	public static void main(String[] args) {
		/*
		 * use system class to get all information about System 
		 */
		//System.getProperties().list(System.out);
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless");
		
		WebDriver driver = new ChromeDriver(option);
		
		Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
		System.out.println(cap.getBrowserName()+" "+cap.getBrowserName()+" "+cap.getBrowserName());
		
		
		driver.quit();
	}

}
