package practice;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker {
	public static void main(String[] args) {

		WebDriverManager.edgedriver().setup();
		

		Random random = new Random();
		int randomNum = random.nextInt(100);

		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if (driver.getTitle().contains("vtiger"))
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not found");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Home"))
			System.out.println("Home page is displayed");
		else
			System.out.println("Home page not found");
			
		
		WebElement quickCreate = driver.findElement(By.id("qccombo"));
		Select s = new Select(quickCreate);
		s.selectByValue("Events");

		
		String header = driver.findElement(By.xpath("//td[@class='mailSubHeader']")).getText();
		if (header.contains("Create To Do"))
			System.out.println("Create To Do is displayed");
		else
			System.out.println("Create To Do not found");

		String subject = "Event" + randomNum;
		driver.findElement(By.name("subject")).sendKeys(subject);
		driver.findElement(By.id("jscal_trigger_date_start")).click();

		String currentMonthYear = driver
				.findElement(By
						.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
				.getText();

		String[] str = currentMonthYear.split(",");

		int currentMonthInNum = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0])
				.get(ChronoField.MONTH_OF_YEAR);
		int currentYearInNum = Integer.parseInt(str[1].trim());
		int requiredYear = 2025;
		int requiredMonth = 10;
		int requiredDate = 18;

		while (currentYearInNum < requiredYear) {
			driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='»']"))
					.click();
			currentMonthYear = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str = currentMonthYear.split(",");
			currentMonthInNum = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0])
					.get(ChronoField.MONTH_OF_YEAR);
			currentYearInNum = Integer.parseInt(str[1].trim());

			if (currentYearInNum == requiredYear) {
				while (currentMonthInNum < requiredMonth) {
					driver.findElement(
							By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='›']"))
							.click();
					currentMonthYear = driver.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
							.getText();
					str = currentMonthYear.split(",");
					currentMonthInNum = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0])
							.get(ChronoField.MONTH_OF_YEAR);
				}
				while (currentMonthInNum > requiredMonth) {
					driver.findElement(
							By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='‹']"))
							.click();
					currentMonthYear = driver.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
							.getText();
					str = currentMonthYear.split(",");
					currentMonthInNum = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0])
							.get(ChronoField.MONTH_OF_YEAR);
				}
			}
		}

		driver.findElement(By.xpath(
				"//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='" + requiredDate + "']"))
				.click();

		driver.findElement(By.id("jscal_trigger_due_date")).click();
		String currentMonthYearDue = driver
				.findElement(By
						.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
				.getText();
		String[] str1 = currentMonthYear.split(",");

		int currentMonthInNumDue = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str1[0])
				.get(ChronoField.MONTH_OF_YEAR);
		int currentYearInNumDue = Integer.parseInt(str1[1].trim());
		int requiredYearDue = 2025;
		int requiredMonthDue = 11;
		int requiredDateDue = 20;
		while (currentYearInNumDue < requiredYearDue) {
			driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='»']"))
					.click();
			currentMonthYearDue = driver
					.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
					.getText();
			str1 = currentMonthYearDue.split(",");
			currentMonthInNumDue = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str1[0])
					.get(ChronoField.MONTH_OF_YEAR);
			currentYearInNumDue = Integer.parseInt(str1[1].trim());

			if (currentYearInNumDue == requiredYearDue) {
				while (currentMonthInNumDue < requiredMonthDue) {
					driver.findElement(
							By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='›']"))
							.click();
					currentMonthYearDue = driver.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
							.getText();
					str1 = currentMonthYearDue.split(",");
					currentMonthInNumDue = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str1[0])
							.get(ChronoField.MONTH_OF_YEAR);
				}
				while (currentMonthInNumDue > requiredMonthDue) {
					driver.findElement(
							By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='‹']"))
							.click();
					currentMonthYearDue = driver.findElement(By.xpath(
							"//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']"))
							.getText();
					str1 = currentMonthYearDue.split(",");
					currentMonthInNumDue = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str1[0])
							.get(ChronoField.MONTH_OF_YEAR);
				}
			}
		}

		driver.findElement(By.xpath(
				"//div[@class='calendar' and contains(@style,'block')]/descendant::td[.='" + requiredDateDue + "']"))
				.click();

	}

	public static void datepicker() {

	}

	// driver.get("http://localhost/avactis.4.6.Next/");

}
