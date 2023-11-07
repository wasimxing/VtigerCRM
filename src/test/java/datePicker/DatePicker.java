package datePicker;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker {
	
	public static void main(String[] args) {
		WebDriverManager.edgedriver().setup();
		
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("datepicker")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ui-datepicker-div")));
		
	String actualMonth=	driver.findElement(By.className("ui-datepicker-month")).getText();
	String actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
	
	while(!(actualMonth.equals("August")&& actualYear.equals("2024")))
	{
		driver.findElement(By.xpath("//a[@data-handler='next']")).click();
		actualMonth=	driver.findElement(By.className("ui-datepicker-month")).getText();
		actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		
		
	}
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[text()='12']")).click();
		
		
	}

}
