package genericLibrariesImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyUtilityFile;
import genericLibraries.WebdriverUtility;

public class CreateAndDuplicateLeadTest {

	public static void main(String[] args) {
		PropertyUtilityFile property = new PropertyUtilityFile();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility java = new JavaUtility();
		WebdriverUtility web = new WebdriverUtility();

		property.propertiesInitialization(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInitialization(IConstantPath.EXCEL_PATH);

		String url = property.getDataFromProperties("url");
		String browser = property.getDataFromProperties("browser");
		long time = Long.parseLong(property.getDataFromProperties("time"));

		WebDriver driver = web.openApplication(browser, url, time);

		WebElement loginButton = driver.findElement(By.id("SubmitButton"));
		if (loginButton.isDisplayed())
			System.out.println("login page displayed");
		else
			System.out.println("login page not found");
		driver.findElement(By.name("user_name")).sendKeys(property.getDataFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(property.getDataFromProperties("password"));
		loginButton.click();

		String headerXpath = "//a[@class='hdrLink']";
		WebElement homePageHeader = driver.findElement(By.xpath(headerXpath));

		if (homePageHeader.getText().trim().equals("Home"))
			System.out.println("Home Page Is Displayed");
		else
			System.out.println("Home page is not displayed");
		driver.findElement(By.xpath("//td[4]/a[.='Leads']")).click();

		WebElement leadPageHeader = driver.findElement(By.xpath(headerXpath));
		if (leadPageHeader.getText().trim().equals("Leads"))
			System.out.println("Leads Page Displayed");
		else
			System.out.println("Leads Page Not Displayed");
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();

		String subPageHeader = "//span[@class='lvtHeaderText']";
		WebElement createLeadHeader = driver.findElement(By.xpath(subPageHeader));

		if (createLeadHeader.getText().contains("Creating New Lead"))
			System.out.println("Creating Lead is displayed");
		else
			System.out.println("Creating Lead is not displayed");

		Map<String, String> map = excel.readDataFromExcel("TestData", "Create Lead");
		String lastName = map.get("Last Name") + java.generateRandomNumber(100);
		String company = map.get("Company") + java.generateRandomNumber(100);

		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("company")).sendKeys(company);

		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();

		WebElement newLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (newLeadInfoHeader.getText().contains(lastName))
			System.out.println("Lead Created");
		else
			System.out.println("Lead Not Found");

		driver.findElement(By.name("Duplicate")).click();
		WebElement duplicatingHeader = driver.findElement(By.xpath(subPageHeader));
		if (duplicatingHeader.getText().contains("Duplicating"))
			System.out.println("Duplicating page Displayed");
		else
			System.out.println("Duplicating page not displayed");

		String newLastName = map.get("New Last Name") + java.generateRandomNumber(100);
		WebElement lastNameTF = driver.findElement(By.name("lastname"));
		lastNameTF.clear();
		lastNameTF.sendKeys(newLastName);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();

		WebElement duplicateLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (duplicateLeadInfoHeader.getText().contains(newLastName))
			System.out.println("Lead Duplicated");
		else
			System.out.println("Lead Not Duplicated");
		driver.findElement(By.xpath(headerXpath)).click();

		WebElement newLead = driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]"));
		
		if (newLead.getText().equals(newLastName)) {
			System.out.println("Lead Added to Database");
			excel.writeToExcel("TestData", "Pass", "Create Lead", IConstantPath.EXCEL_PATH);
		} 
		else {
			System.out.println("Lead not added to database");
			excel.writeToExcel("TestData", "Fail", "Create Lead", IConstantPath.EXCEL_PATH);
		}
		
		WebElement adminIcon=driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		web.mouseHover(adminIcon);
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		web.closeWindows();
		excel.closeWorkbook();
		

	}

}
