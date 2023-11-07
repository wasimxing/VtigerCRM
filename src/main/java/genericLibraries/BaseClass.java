package genericLibraries;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pompages.CreateNewLeadPage;
import pompages.DuplicatingLeadPage;
import pompages.HomePage;
import pompages.LeadPage;
import pompages.LoginPage;
import pompages.NewLeadInfoPage;

public class BaseClass {
	protected PropertyUtilityFile property;
	protected ExcelUtility excel;
	protected JavaUtility javaUtil;
	protected WebdriverUtility web;
	protected WebDriver driver;
	protected long time;
	protected LoginPage login;
	protected HomePage home;
	protected LeadPage leads;
	protected CreateNewLeadPage createLead;
	protected NewLeadInfoPage leadInfo;
	protected DuplicatingLeadPage duplicateLead;
	public static JavaUtility sjavaUtil;
	public static WebDriver sdriver;
	

	// @BeforeSuite
	// @BeforeTest

	@BeforeClass
	public void classSetup() {
		property = new PropertyUtilityFile();
		excel = new ExcelUtility();
		javaUtil = new JavaUtility();
		web = new WebdriverUtility();
		sjavaUtil = javaUtil;
		

		property.propertiesInitialization(IConstantPath.PROPERTIES_FILE_PATH);
		String url = property.getDataFromProperties("url");
		String browser = property.getDataFromProperties("browser");
		time = Long.parseLong(property.getDataFromProperties("time"));

		driver = web.openApplication(browser, url, time);
		sdriver=driver;
		
	}

	@BeforeMethod
	public void methodSetup() {
		login = new LoginPage(driver);
		Assert.assertTrue(login.getLoginButton().isDisplayed());
		home = new HomePage(driver);
		leads = new LeadPage(driver);
		createLead = new CreateNewLeadPage(driver);
		leadInfo = new NewLeadInfoPage(driver);
		duplicateLead = new DuplicatingLeadPage(driver);

		excel.excelInitialization(IConstantPath.EXCEL_PATH);

		login.loginToVtiger(property.getDataFromProperties("username"), property.getDataFromProperties("password"));
		Assert.assertEquals(home.getPageHeaderText(), "Home");
	}

	@AfterMethod
	public void methodTeardown() {
		home.signOutOfVtiger(web);
		excel.closeWorkbook();
	}

	@AfterClass
	public void classTeardown() {
		web.closeCurrentWindow();
	}

	// @AfterTest
	// @AfterSuite
}
