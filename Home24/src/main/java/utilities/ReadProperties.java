package utilities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;


public class ReadProperties 
{
	private Properties properties;
	private final String propertyFilePath= System.getProperty("user.dir")+"/src/main/java/resources/configFiles/Configuration.properties";
 
	public ReadProperties()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			properties.load(reader);
			reader.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String getURL()
	{
		return properties.getProperty("url");
	}
	
	public String getBrowser()
	{
		return properties.getProperty("browser");
	}
	
	public String getEmail()
	{
		return properties.getProperty("email");
	}
	
	public String getPassword()
	{
		return properties.getProperty("password");
	}
	
	
	public String getChromeDriverPath()
	{
		return System.getProperty("user.dir")+properties.getProperty("chromeDriverPath");
	}
	
	public String getFirefoxDriverPath()
	{
		return System.getProperty("user.dir")+properties.getProperty("firefoxDriverPath");
	}
	
	public String getExtentReportPath()
	{
		return System.getProperty("user.dir")+properties.getProperty("extentReportPath");
	}
	
	public String getExtentConfigPath()
	{
		return System.getProperty("user.dir")+properties.getProperty("extentConfigPath");
	}
	
	public int getImplicitTimeOut()
	{	
		return Integer.parseInt(properties.getProperty("implicitWait"));
	}
	public int getPageLoadTimeOut()
	{
		return Integer.parseInt(properties.getProperty("pageLoadtimeOut"));
	}
	
	
	public String getTestDataPath()
	{
		
		return System.getProperty("user.dir")+properties.getProperty("testDataPath");	
	}
	
	public String getScreenShotPath()
	{
		return System.getProperty("user.dir")+properties.getProperty("screenShotPath");
	}
	
	public String getlog4jPath()
	{
		return System.getProperty("user.dir")+properties.getProperty("log4jPath");
	}
	
}
