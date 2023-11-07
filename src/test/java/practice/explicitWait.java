package practice;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class explicitWait {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		/*driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("https://www.hyrtutorials.com/");
		driver.findElement(By.xpath("//a[text()='Selenium Practice']")).click();
		WebElement wh = driver.findElement(By.xpath("//a[text()='Window Handles']"));
		wh.click();
		String parentwindow =driver.getWindowHandle();
		System.out.println("parent window handled"+parentwindow+driver.getTitle());*/
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://www.hyrtutorials.com/");
		driver.findElement(By.linkText("Selenium Practice")).click();
		driver.findElement(By.linkText("Window Handles")).click();
		String parentwindow=driver.getWindowHandle();
		System.out.println("parant window handel "+parentwindow +driver.getTitle());
		WebElement click =driver.findElement(By.xpath("//button[@id='newTabBtn']"));
		wait.until(ExpectedConditions.elementToBeClickable(click)).click();
		Set<String>chiledwindow=driver.getWindowHandles();
		System.out.println("the windows of 2 are "+ chiledwindow+driver.getTitle());
		
		for ( String windowhandel:chiledwindow) {
			if(!windowhandel.equals(parentwindow))
			{
			driver.switchTo().window(windowhandel);
			driver.manage().window().maximize();
			System.out.println(driver.getTitle());
			Thread.sleep(3000);
			driver.close();
			}
			}
			
			driver.switchTo().window(parentwindow);
			driver.findElement(By.xpath("//input[@id='name']")).sendKeys("ravikumar");
			Thread.sleep(4000);
			driver.quit();
		
	}
		
		
		
	}


