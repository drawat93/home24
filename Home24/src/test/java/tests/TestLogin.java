package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.Status;

import baseTests.BaseClass;

public class TestLogin extends BaseClass
{
	
	@DataProvider(name="credentials")
    public String[][] getCredentials() throws InvalidFormatException, IOException
	{
		return util.readExcelData("credentials");
    }

	@Test(priority=1,dataProvider="credentials")
	public void verifyInvalidLogin(String username,String password)
	{
		testInfo.log(Status.INFO, "User is entering email: "+username);
		loginObject.enterEmail(username);
		
		testInfo.log(Status.INFO, "User is entering password: "+password);
		loginObject.enterPassword(password);
		
		loginObject.clickOnlogIn();
		testInfo.log(Status.INFO, "User is clicking on login");
		
		AssertJUnit.assertEquals(loginObject.getLoginErrorMessage(),"Das Passwort oder die E-Mail Adresse sind falsch.");
	}
	
	@Test(priority=2,description="test to verify validation checks on login page")
	public void verifyLoginValidationChecks()
	{
		testInfo.log(Status.INFO, "Clicking on login without entering email & password");
		loginObject.clickOnlogIn();
		Assert.assertTrue(loginObject.getNoEmailErrorMessage() && loginObject.getNoPasswordErrorMessage());
		testInfo.log(Status.INFO, "User is getting validation error message that email & password is not entered");
		
	}
}
