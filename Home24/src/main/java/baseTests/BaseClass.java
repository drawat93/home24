package baseTests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import pageObjects.LoginPageObject;
import utilities.Home24Utilities;
import utilities.ReadProperties;


public class BaseClass
{
	public WebDriver driver;	
	
	// Declaring Extent Reports variables
	public static ExtentReports reports;
	public static ExtentTest testInfo;
	public static ExtentHtmlReporter htmlReporter;
	
	public Home24Utilities util;
	public ReadProperties read=new ReadProperties();
	public LoginPageObject loginObject;
	public static int taskId=0;
	public Logger logger;

	
	@BeforeSuite
	public void setupExtentReports()
	{
		htmlReporter = new ExtentHtmlReporter(new File(read.getExtentReportPath()));
		htmlReporter.loadConfig(read.getExtentConfigPath());
		htmlReporter.config().setDocumentTitle("Home24 Test Report");
		htmlReporter.config().setReportName("Home24 Test Automation Report");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("OS ", System.getProperty("os.name"));
		reports.setSystemInfo("Browser ", read.getBrowser());
	}
	
	
	@BeforeTest
	public void setupBrowser() 
	{
		String browser=read.getBrowser();
		if(browser.toLowerCase().equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", read.getChromeDriverPath());
			driver = new ChromeDriver();
		}
		else if(browser.toLowerCase().equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", read.getFirefoxDriverPath());
			driver = new FirefoxDriver();	
		}
		driver.manage().timeouts().implicitlyWait(read.getImplicitTimeOut(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(read.getPageLoadTimeOut(), TimeUnit.SECONDS);
		
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure(read.getlog4jPath()); // Configuring log patterns from log4j properties

		util = new Home24Utilities();
		loginObject=new LoginPageObject(driver);
		logger.info("###########Opening Browser##########");
		driver.get(read.getURL());
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		loginObject.clickOnCountryPopUp();
	}

	@BeforeMethod
	public void createReportLogger(Method method) {
		String testname = method.getName();
		testInfo = reports.createTest(testname);
	}

	@AfterMethod
	public void captureStatus(ITestResult result) throws IOException // Capture status of each test method
	{
		if (result.getStatus() == ITestResult.SUCCESS) 
		{
			testInfo.log(Status.PASS, "Test method " + result.getName() + " is passed");
			logger.info("Test method " + result.getName() + " is passed");
		} 
		
		else if (result.getStatus() == ITestResult.FAILURE) 
		{
			String imagePath = util.screenshot(driver, result.getName());
			testInfo.log(Status.FAIL, "Test method " + result.getName() + " is failed");
			testInfo.log(Status.FAIL, "Test Failure is" + result.getThrowable());
			
			testInfo.addScreenCaptureFromPath(imagePath); // adding screenshot of failed case on report
			logger.error("Test method " + result.getName() + " is failed");
			logger.debug("Test Failure is" + result.getThrowable());
		} 
		
		else if (result.getStatus() == ITestResult.SKIP) {
			testInfo.log(Status.PASS, "Test method " + result.getName() + " is skipped");
			logger.info("Test method " + result.getName() + " is skipped");
		}
	}

	@AfterTest
	public void tearDown() {
		reports.flush();
		logger.info("###########Closing Browser##########");
		driver.quit();
	}
	
	

}
