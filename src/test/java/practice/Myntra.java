package practice;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {
	@Test
	public void myntra() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement men = driver.findElement(By.xpath("//a[text()='Men']/ancestor::div[@class=\"desktop-navContent\"]"));

		Actions action = new Actions(driver);
		action.moveToElement(men).perform();

		driver.findElement(By.xpath("//a[text()='Suits']")).click();
		WebElement louis = driver.findElement(By.xpath("//img[contains(@alt,\"Louis Philippe Men Navy Blue \")]"));
		louis.click();

		String parentID = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		for (String id : windows) {
			driver.switchTo().window(id);

		}

		driver.findElement(By.xpath("//p[text()='44']")).click();
		driver.findElement(By.xpath("//div[contains(text(),'ADD TO BAG')]")).click();

		driver.findElement(By.xpath("//span[text()='Bag']")).click();
		driver.findElement(By.cssSelector("div.itemComponents-base-invisibleBackDrop")).click();

		WebElement remove = driver.findElement(By.xpath("//button[text()='REMOVE']"));
		remove.click();

		driver.findElement(By.xpath("//button[text()='REMOVE']/ancestor::div[@class=\"inlinebuttonV2-base-actions \"]"))
				.click();

		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(parentID);
		driver.quit();
	}

}
