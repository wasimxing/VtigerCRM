package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;
 

public class Chrome123 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wasim\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[.='✕']")).click();
		
		WebElement searchTF = driver.findElement(By.name("q"));
		searchTF.sendKeys("iphone");
		
		WebElement searchbutton=driver.findElement(By.cssSelector("button.L0Z3Pu"));
		searchbutton.submit();
		
		
	

		//WebElement title =  driver.findElement(By.xpath("//span[contains(.,'Showing 1 – 24 of 428 results for ')]"));
		
		List<WebElement> listProducts = driver.findElements(By.className("_4rR01T"));
		List<WebElement> listPrice = driver.findElements(By.className("_1_WHN1"));
		
		
		
//		String product_name = listProducts.get(0).getText();
//		String product_price = listPrice.get(0).getText();
////		product_price = product_price.replaceAll("[^0-9]", "");
//		System.out.println(product_name+""+product_price);

		
        System.out.println(listProducts.size());
        
        int w=listProducts.size();
    	//print all links names using for each loop
         for(int i=0; i<w; i++)
         { 
        	 
        	 
        	 for(int j=i; j<w; j++)
        	 {
        		 System.out.println(listProducts.get(j).getText());
        		 System.out.println(listPrice.get(j).getText());
        	 }
        	 
         }
        
         
	         
				
				
		
		
		
		
		driver.close();
	
		
		
	}

	}

