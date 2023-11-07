package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * this is login pom page which using commonly
 * 
 * @author wasim
 *
 */
public class LoginPage {

	// Declaration
	@FindBy(id = "submitButton")
	private WebElement loginButton;
	@FindBy(name = "user_name")
	private WebElement usernameTF;
	@FindBy(name = "user_password")
	private WebElement passwordTF;

	// Initialization
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getLoginButton() {
		return loginButton;
	}

	public void loginToVtiger(String username, String password) {
		usernameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		loginButton.click();
	}

}
