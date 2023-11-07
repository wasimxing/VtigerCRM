package hardcodeTests;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Contacts {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wasim\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement login = 	driver.findElement(By.id("submitButton"));
		if(login.isDisplayed())
			System.out.println("login page is displayed");
		else
			System.out.println("login page not found");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		login.click();
		
		String pageHeader="//a[@class='hdrLink']";
		
		WebElement homepage = driver.findElement(By.xpath(pageHeader));
		if(homepage.getText().contains("Home"))
			System.out.println("home page displayed");
		else
			System.out.println("homepage not found");
		
		
		driver.findElement(By.xpath("//td[8]/a[text()='Contacts']")).click();
		WebElement contactpage = driver.findElement(By.xpath(pageHeader));
		if(contactpage.getText().contains("Contacts"))
			System.out.println("contacts page displayed");
		else
			System.out.println("contacts page not found");
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		WebElement creatingNewContactPage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(creatingNewContactPage.getText().contains("Creating New Contact"))
			System.out.println("creating new page displayed");
		else
			System.out.println("creating new page not found");
		
		WebElement prefix = driver.findElement(By.name("salutationtype"));
		
		Select s = new Select(prefix);
		s.selectByValue("Mr.");
		
		driver.findElement(By.name("firstname")).sendKeys("saddam");
		driver.findElement(By.name("lastname")).sendKeys("khan");
		driver.findElement(By.name("button")).click();
		
		WebElement contactInformationPage = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
		if(contactInformationPage.getText().contains("Contact Information"))
			System.out.println("contact created page displayed");
		else 
			System.out.println("contact created page not found");
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		Thread.sleep(2000);

		
		driver.quit();
	}

}
