package hardcodeTests;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Contacts1 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wasim\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//td[8]/a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		WebElement prefix = driver.findElement(By.name("salutationtype"));
		
		Select s = new Select(prefix);
		s.selectByValue("Mr.");
		
		driver.findElement(By.name("firstname")).sendKeys("salman");
		driver.findElement(By.name("lastname")).sendKeys("khan");
		
		driver.findElement(By.xpath("//tr/td[2]/img[@alt='Select']")).click();
		
		String parentid = driver.getWindowHandle();
		Set<String> org= driver.getWindowHandles();
		
		for(String id:org)
		{
			driver.switchTo().window(id);
		}
		
		driver.findElement(By.id("3")).click();
		
		driver.switchTo().window(parentid);
		
		driver.findElement(By.name("button")).click();
		
		driver.quit();
	}
	

}
