package testngImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
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

@Listeners(genericLibraries.ExtentReportsInListenerImplementation.class)
public class CreateAndDuplicateLead extends BaseClass {
	@Test
	public void createAndDuplicateTest() {
		SoftAssert soft = new SoftAssert();
		home.clickLeadsTab();

		soft.assertTrue(leads.getPageHeaderText().contains("Leads"));

		leads.clickPlusButton();

		soft.assertTrue(createLead.getPageHeaderText().contains("Creating New Lead"));

		Map<String, String> map = excel.readDataFromExcel("TestData", "Create Lead");
		String lastName = map.get("Last Name") + javaUtil.generateRandomNumber(100);
		String company = map.get("Company") + javaUtil.generateRandomNumber(100);

		createLead.setLastName(lastName);
		createLead.setCompanyName(company);
		createLead.clickSaveButton();

		soft.assertTrue(leadInfo.getPageHeaderText().contains(lastName));

		leadInfo.clickDuplicateButton();
		soft.assertTrue(duplicateLead.getPageHeaderText().contains("Duplicating"));

		String newLastName = map.get("New Last Name") + javaUtil.generateRandomNumber(100);
		duplicateLead.setLastName(newLastName);
		duplicateLead.clickSaveButton();

		soft.assertTrue(leadInfo.getPageHeaderText().contains(newLastName));
		duplicateLead.clickLeads();
		
		soft.assertTrue(leads.getNewLeadName().equals(newLastName));

		if (leads.getNewLeadName().equals(newLastName)) {
			System.out.println("Lead Added to Database");
			excel.writeToExcel("TestData", "Pass", "Create Lead", IConstantPath.EXCEL_PATH);
		} else {
			System.out.println("Lead Not Added to DataBase");
			excel.writeToExcel("TestData", "Fail", "Create Lead", IConstantPath.EXCEL_PATH);
		}

		soft.assertAll();
		
	}

}
