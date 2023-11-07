package pomImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyUtilityFile;
import genericLibraries.WebdriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import pompages.CreateNewLeadPage;
import pompages.DuplicatingLeadPage;
import pompages.HomePage;
import pompages.LeadPage;
import pompages.LoginPage;
import pompages.NewLeadInfoPage;

public class CreateAndDuplicateLead {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ExcelUtility excel = new ExcelUtility();
		PropertyUtilityFile property = new PropertyUtilityFile();
		JavaUtility java = new JavaUtility();
		WebdriverUtility web = new WebdriverUtility();
		property.propertiesInitialization(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInitialization(IConstantPath.EXCEL_PATH);

		String browser = property.getDataFromProperties("browser");
		String url = property.getDataFromProperties("url");
		long time = Long.parseLong(property.getDataFromProperties("time"));

		WebDriver driver = web.openApplication(browser, url, time);

		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadPage leads = new LeadPage(driver);
		CreateNewLeadPage createLead = new CreateNewLeadPage(driver);
		NewLeadInfoPage newleads = new NewLeadInfoPage(driver);
		DuplicatingLeadPage duplicateLeads = new DuplicatingLeadPage(driver);

		if (login.getLoginButton().isDisplayed())
			System.out.println("Login Page Displayed");
		else
			System.out.println("Login Page Not Found");

		property.getDataFromProperties("url");
		login.loginToVtiger(property.getDataFromProperties("username"), property.getDataFromProperties("password"));

		if (home.getPageHeaderText().contains("Home"))
			System.out.println("Home Page displayed");
		else
			System.out.println("Home Page not displayed");

		home.clickLeadsTab();

		if (leads.getPageHeaderText().contains("Leads"))
			System.out.println("Lead Page Displayed");
		else
			System.out.println("Lead Page Not displayed");

		leads.clickPlusButton();

		if (createLead.getPageHeaderText().contains("Creating New Lead"))
			System.out.println("Creating Lead page Displayed");
		else
			System.out.println("Creating Lead page not Displayed ");

		Map<String, String> map = excel.readDataFromExcel("TestData", "Create Lead");
		String lastName = map.get("Last Name") + java.generateRandomNumber(100);
		String company = map.get("Company") + java.generateRandomNumber(100);

		createLead.setLastName(lastName);
		createLead.setCompanyName(company);
		createLead.clickSaveButton();
 
		if (newleads.getPageHeaderText().contains(lastName))
			System.out.println("Lead Created ");
		else
			System.out.println("Lead Not Created");
		newleads.clickDuplicateButton();

		if (duplicateLeads.getPageHeaderText().contains(lastName))
			System.out.println("Duplicating Page Displayed");
		else
			System.out.println("Duplicating Page Not Found");
		
		String newLastName = map.get("New Last Name") + java.generateRandomNumber(100);
		duplicateLeads.setLastName(newLastName);
		duplicateLeads.clickSaveButton();
		
		if(newleads.getPageHeaderText().contains(newLastName))
			System.out.println("Lead Duplicated ");
		else
			System.out.println("Lead Not Duplicated");
		duplicateLeads.clickLeads();
		
		if(leads.getNewLeadName().contains(newLastName)) {
			System.out.println("Lead Added to Database");
			excel.writeToExcel("TestData","Pass", "Create Lead", IConstantPath.EXCEL_PATH);
		}
		else
		{
			System.out.println("Lead Not Added to DataBase");
			excel.writeToExcel("TestData","Fail", "Create Lead", IConstantPath.EXCEL_PATH);
		}
		
		home.signOutOfVtiger(web);
		web.closeWindows();
		excel.closeWorkbook();
			

	}

}
