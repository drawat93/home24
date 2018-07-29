package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Home24Utilities {
	Logger logger = Logger.getLogger(this.getClass()); 
	ReadProperties read=new ReadProperties();
	//Read excel data
		public String[][] readExcelData(String fileName) throws InvalidFormatException, IOException
		{
			logger.info("Reading Excel file "+fileName);
			File file=new File(read.getTestDataPath()+"/" + fileName+".xlsx");
			Workbook workbook=new XSSFWorkbook(file);
			XSSFSheet sheet= (XSSFSheet) workbook.getSheetAt(0);
			int numberOfRows=sheet.getLastRowNum();
			int numberOfColumns=sheet.getRow(0).getPhysicalNumberOfCells();
			String [][] data = new String[numberOfRows][numberOfColumns];
			int j=0;   //index of array data
			for (int i=1; i<=numberOfRows;i++)
			{
				XSSFRow row=sheet.getRow(i);
				for (int col=0;col<numberOfColumns;col++)
				{
					data[j][col]=row.getCell(col).toString();
				}
				j++;
			}
			logger.info("Reading of excel file "+fileName+" is completed");
			return data;
		}

	public String screenshot(WebDriver driver,String name) 
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String fileName= read.getScreenShotPath()+"/"+name+"-"+timeStamp+".png";
		
		// Driver will capture screenshot of current page and store it in File variable scrFile
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		try
		{
			//Copying image from scrFile to other file in a specified path
			FileUtils.copyFile(scrFile, new File(fileName)); 
			logger.error("Screenshot is captured and saved in src/customTestOutput/FailedCase_ScreenShots");
		} 
		
		catch (Exception e)
		{
			logger.error("Unable to take screenshot");
			e.printStackTrace();
		}
		return fileName;
	}
	
}
