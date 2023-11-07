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

public class CreateAndDuplicateLeadTest {
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

		String headerXpath= "//a[@class='hdrLink']";
		WebElement homepageheader = driver.findElement(By.xpath(headerXpath));
		if(homepageheader.getText().trim().equals("Home"))
			System.out.println("home page is displayed");
		else
			System.out.println("home page not found");
		
		driver.findElement(By.xpath("//td[4]/a[text()='Leads']")).click();
		WebElement leadsPageHeader = driver.findElement(By.xpath(headerXpath));
		if(leadsPageHeader.getText().trim().equals("Leads"))
			System.out.println("Leads Page Displayed");
		else
			System.out.println("Leads page not found");
		
		driver.findElement(By.xpath("//td[1]/a[contains(@href,'ndex.php?module=Leads&action=EditView&return')]")).click();
		
		String subPageHeader = "//span[@class='lvtHeaderText']";
		WebElement createLeadHeader = driver.findElement(By.xpath(subPageHeader));
		if(createLeadHeader.getText().trim().equals("Creating New Lead"))
			System.out.println("create New Lead displayed");
		else
			System.out.println("create new Lead not found");

		WebElement prefix = driver.findElement(By.name("salutationtype"));

		Select s = new Select(prefix);
		s.selectByValue("Mr.");

		driver.findElement(By.name("firstname")).sendKeys("sher");
		driver.findElement(By.name("lastname")).sendKeys("khan");
		driver.findElement(By.name("company")).sendKeys("xyz pvt ltd");
		driver.findElement(By.name("button")).click();
		
		WebElement newLeadInfoHeader = driver .findElement(By.xpath("//span[contains(text(),'Lead Information')]"));
		if(newLeadInfoHeader.getText().contains("khan"))
			System.out.println("Lead created");
		else
			System.out.println("lead not found");
		
		
		driver.findElement(By.name("Duplicate")).click();
		
		WebElement duplicatePageHeader = driver.findElement(By.xpath("//span[contains(text(),'Duplicating')]"));
		if(duplicatePageHeader.getText().contains("Duplicating"))
			System.out.println("Duplicating page displayed");
		else
			System.out.println("Dupilicating page not found");
		
		WebElement lastNameTF = driver.findElement(By.name("lastname"));
		lastNameTF.clear();
		lastNameTF.sendKeys("Abdul");
		driver.findElement(By.name("button")).click();
		
		WebElement duplicateLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(duplicateLeadInfoHeader.getText().contains("Abdul"))
			System.out.println("lead duplicated");
		else
			System.out.println("lead not duplicated");
		
		driver.findElement(By.xpath(headerXpath)).click();
		
		WebElement newLead = driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]"));
		if(newLead.getText().equals("Abdul"))
			System.out.println("lead added to database");
		else
			System.out.println("lead not added to database");
		
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		Thread.sleep(2000);
		
		driver.quit();
		
	}

}
