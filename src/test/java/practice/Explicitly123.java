package practice;




import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Explicitly123 {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wasim\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.actitime.com/login.do");
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//to wait until title appears
		wait.until(ExpectedConditions.titleContains("actiTIME"));
		
		WebElement username_TF = driver.findElement(By.id("username"));
		
		//to wait until username text field appears
		
		wait.until(ExpectedConditions.visibilityOf(username_TF)).sendKeys("admin");
	
		WebElement passowrd_TF =driver.findElement(By.name("pwd"));
		//to  wait until password text field appears
		
		wait.until(ExpectedConditions.visibilityOf(passowrd_TF)).sendKeys("manager");
		
		WebElement loginButton =driver.findElement(By.id("loginButton"));
		//to wait until login button appears
		
		wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
		
		
		//to wait until home page appears
		wait.until(ExpectedConditions.titleContains("Enter Time-Track"));
		driver.quit();
	}

}
