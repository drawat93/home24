package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

	WebDriver driver;
	Logger logger;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger = Logger.getLogger(this.getClass());
	}

	@FindBy(xpath = "//span[@class='alternative-country-box__country-name']")
	WebElement countryPopUp;

	@FindBy(xpath = "//input[@class='acte-customer-login-email-fld']")
	WebElement email;

	@FindBy(xpath = "//input[@class='acte-customer-login-password-fld']")
	WebElement password;

	@FindBy(xpath = "//button[@class='button button--primary pull-right acte-login-login-btn']")
	WebElement logIn;

	@FindBy(xpath = "//*[@class='messages']")
	WebElement loginErrorMessage;

	@FindBy(xpath = "//*[@id='email-error']")
	WebElement noEmailMessage;

	@FindBy(xpath = "//*[@id='password-error']")
	WebElement noPasswordMessage;

	public void clickOnCountryPopUp() {
		logger.info("User is clicking on Country pop up");
		countryPopUp.click();
	}

	public void clickOnlogIn() {
		logger.info("User is clicking on Sign In");
		logIn.click();
	}

	public void enterEmail(String str) {
		email.clear();
		logger.info("User is entering username: " + str);
		email.sendKeys(str);
	}

	public void enterPassword(String str) {
		password.clear();
		logger.info("User is entering password " + str);
		password.sendKeys(str);
	}

	// Return login error message if there's any otherwise return "No error message"
	// in case of successful login
	public String getLoginErrorMessage() {
		logger.info("Checking if login error message is coming");
		if (loginErrorMessage.isDisplayed()) {
			return loginErrorMessage.getText();
		} else {
			return "no error message";
		}
	}

	//Check if no email entered validation error is coming
	public boolean getNoEmailErrorMessage() {
		return noEmailMessage.isEnabled();
	}

	//Check if no password entered validation error is coming
	public boolean getNoPasswordErrorMessage() {
		return noPasswordMessage.isEnabled();
	}

}
