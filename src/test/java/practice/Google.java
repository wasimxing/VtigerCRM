package practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Google {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://fortification-uat-ui.s3-website.ap-south-1.amazonaws.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@id='details-button']")).click();
		driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
		driver.findElement(By.id("username")).sendKeys("demo_premixuser");
		driver.findElement(By.id("password")).sendKeys("demo_premixuser");
		driver.findElement(By.name("login")).click();
		driver.findElement(By.xpath("//div[text()='BATCH']")).click();
		driver.findElement(By.xpath("//*[name()='svg' and @data-testid='AddIcon']")).click();
		
		driver.findElement(By.xpath("//input[@aria-label='Choose date']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[name()='svg' and @data-testid='ArrowRightIcon']")).click();
		System.out.println("Pass");
	}

}
