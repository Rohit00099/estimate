package test.java.ormbframework.utils;
 

import static org.testng.Assert.assertTrue;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.FileWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import javax.activation.DataHandler;

import javax.activation.FileDataSource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import java.net.InetAddress;
import java.net.UnknownHostException;

import test.java.ormbframework.BaseTest;

import test.java.ormbframework.pageObjects.CommonPageElements;


public class CommonFunctions extends BaseTest
{
	public static String sFinalScreenshotFileName ="";
	
	public static String emailbody = String.join(
			System.getProperty("line.separator"),
			"Team,",
			System.getProperty("line.separator"),
			"Automated Report is generated after execution.",
			"Please find attached automated extent report on build - "+System.getProperty("buildNo")+ " for ORMB Version - "+System.getProperty("releaseNo"),
			System.getProperty("line.separator"),
			System.getProperty("line.separator"),
			"Thank You",
			System.getProperty("line.separator"),
			"Note: ** This is an auto-generated email. Please do not reply to this email.**");
	
	public static String to = "rohit.ramesh.thik@oracle.com,ajinkya.a.joshi@oracle.com,ajeet.phatak@oracle.com,vivek.jangam@oracle.com";
	public static String from = "";
	public static String host = "internal-mail-router.oraclecorp.com";
	
	/*'###############################################################
	'Function Name        : FnGetWebElement
	'Function Description : Gets the WebElement to be fetched
	'Created by           : Ajeet
	'Input Parameters     : sIdentifierType
	'                     : sIdentifierValue
	'Output Parameters    : WebElement
	'################################################################*/
	public WebElement FnGetWebElement(WebDriver driver, String sIdentifierType, String sIdentifierValue)
	{
		switch (sIdentifierType) 
		{
			case "ID":
				return driver.findElement(By.id(sIdentifierValue));
			case "CSS":
				return driver.findElement(By.cssSelector(sIdentifierValue));
			case "TAGNAME":
				return driver.findElement(By.tagName(sIdentifierValue));
			case "XPATH":
				return driver.findElement(By.xpath(sIdentifierValue));
			case "LINKTEXT":
				return driver.findElement(By.linkText(sIdentifierValue));
			default:
				return null;
		}
	}
	
	/*'###############################################################
	'Function Name        : FnGetWebElement
	'Function Description : Gets the WebElement to be fetched
	'Created by           : Ajeet
	'Input Parameters     : sIdentifierType
	'                     : sIdentifierValue
	'Output Parameters    : WebElement list
	'################################################################*/
	public List<WebElement> getListWebElements(WebDriver driver, String sIdentifierType, String sIdentifierValue)
	{
		switch (sIdentifierType) 
		{
			case "ID":
				return driver.findElements(By.id(sIdentifierValue));
			case "CSS":
				return driver.findElements(By.cssSelector(sIdentifierValue));
			case "TAGNAME":
				return driver.findElements(By.tagName(sIdentifierValue));
			case "XPATH":
				return driver.findElements(By.xpath(sIdentifierValue));
			default:
				return null;
		}
	}
	
	/*'###############################################################
	'Function Name        : FnGetUniqueId
	'Function Description : Creates Unique ID
	'Created by           : Sumit
	'Input Parameters     :  
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public static String FnGetUniqueId() 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/*'###############################################################
	'Function Name        : getCurrentDateTime
	'Function Description : Get Current Date and Time
	'Created by           : Sumit
	'Input Parameters     :  
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public String getCurrentDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
		 
		 //get current date time with Date()
		 Date date = new Date();
		 
		 // Now format the date
		 String datenew= dateFormat.format(date);
		 
		 return datenew;
	}
	
	
	 /*'###############################################################
		'Function Name        : FnGetCurrentDateInSpcificFormat
		'Function Description : Get Current Date in MM-dd-yyyy format
		'Created by           : Ajinkya
		'Input Parameters     : None
		'Output Parameters    : Current Date in expected format
		'################################################################*/
		public String FnGetCurrentDateInSpcificFormat(String sFormat) throws Exception
		{
			String sCurrDate;
			Calendar currentDate = Calendar.getInstance();		
			SimpleDateFormat formatter =  new SimpleDateFormat(sFormat);		
			sCurrDate = formatter.format(currentDate.getTime());		
			System.out.println("Current Date is: "+ sCurrDate);
			return (sCurrDate);
		}
		
		/*'###############################################################
		'Function Name        : FnGetPastDateInSpcificFormat
		'Function Description : Get Current Date in MM-dd-yyyy format
		'Created by           : Ajinkya
		'Input Parameters     : None
		'Output Parameters    : Current Date in expected format
		'################################################################*/
		public String FnGetPastDateInSpcificFormat(String sFormat,int sDay) throws Exception
		{
			String sCurrDate;
			Calendar currentDate = Calendar.getInstance();		
			currentDate.add(Calendar.DATE, sDay);		
			SimpleDateFormat formatter =  new SimpleDateFormat(sFormat);		
			sCurrDate = formatter.format(currentDate.getTime());		
			System.out.println("Current Date is: "+ sCurrDate);
			return (sCurrDate);
		}
		
		/*'###############################################################
		'Function Name        : FnGetCurrentDateInSpcificFormat
		'Function Description : Get Current Date in MM-dd-yyyy format
		'Created by           : Ajinkya
		'Input Parameters     : None
		'Output Parameters    : Current Date in expected format
		'################################################################*/
		public String FnGetDateOfMonthInSpcificFormat(String sFormat,int sMonth) throws Exception
		{
			String sCurrDate;
			Calendar currentDate = Calendar.getInstance();		
			currentDate.add(Calendar.MONTH, sMonth);
			SimpleDateFormat formatter =  new SimpleDateFormat(sFormat);		
			sCurrDate = formatter.format(currentDate.getTime());		
			System.out.println("Current Date is: "+ sCurrDate);
			return (sCurrDate);
		}
	public String captureScreenshot(WebDriver driver) throws Exception
	{
		FileInputStream fileInputStream = null;
	    TakesScreenshot screenshot = (TakesScreenshot) driver;
	    File source = screenshot.getScreenshotAs(OutputType.FILE);
		String destination="./reports/Images/Image_"+getCurrentDateTime()+".png";
		File finalDestination = new File(destination);
	    FileUtils.copyFile(source, finalDestination);
		try
		{
			fileInputStream =new FileInputStream(finalDestination);
	        byte[] bytes =new byte[(int)finalDestination.length()];
	        fileInputStream.read(bytes);
		} 
		catch (IOException e) 
		{
			System.out.println("Could not capture screenshot "+e.getMessage());
		}
		return destination;//"data:image/png;base64,"+encodedBase64;
	}

	public static WebElement highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
				element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript(
				"arguments[0].setAttribute('style','border: solid 2px white');",
				element);

		return element;
	}
	
	/*'###############################################################
	'Function Name        : erromsg
	'Function Description : Capture the error message
	'Input Parameters     : a 	-> Exception to be captured 
	'                     : 
	'Output Parameters    :
	'Written By 		  : Ajeet P
	'################################################################*/
	public String erromsg(Exception a) throws Exception
	{
		FnScreenCapture();
		StringWriter sw = new StringWriter();
		a.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		int end = 100; if (exceptionAsString.length() > 1000){end = 1000;} else { end = exceptionAsString.length();}
		return exceptionAsString.substring(0,end).replace(",", "'");
	}
	
	/*'###############################################################
	'Function Name        : FnScreenCapture
	'Function Description : To capture the screenshot
	'Created by           : Sumit
	'Input Parameters     : 
	'Output Parameters    : 
	'################################################################*/
	public static void FnScreenCapture() throws Exception
	{
		System.out.println("*--FnScreenCapture");

		File bufferedImage;

		String sFunctionalModule	= BaseTest.sFunctionalModule;
		String sBuild 				= System.getProperty("buildNo");
		String sReportScreenShot	= BaseTest.sReportScreenShot;
		String sScriptName 			= BaseTest.sScriptName;
		System.out.println("sReportScreenShot->"+sReportScreenShot);
		String sScreenshotFileName = "";

		sScreenshotFileName = sReportScreenShot+sFunctionalModule +"_"+sScriptName+"_"+sBuild+"_"+FnGetUniqueId() + "." + "png";
		System.out.println("sScreenshotFileName->"+sScreenshotFileName);
		sFinalScreenshotFileName = sScreenshotFileName;		
		System.out.println(sFinalScreenshotFileName);
		// Capture the whole screen
		bufferedImage = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);	

		File file = new File(sScreenshotFileName);
		try
		{
			FileUtils.copyFile(bufferedImage, file);		
		} 
		catch(IOException e) 
		{
			System.out.println("Write error for " + file.getPath() + ": " + e.getMessage());
		} 
		
	}
	/*'###############################################################
	'Function Name        : FnWaitForElement
	'Function Description : To wait for element
	'Created by           : Ajinkya J
	'Input Parameters     : Driver,Time in seconds to wait,XPath of element to wait 
	'					  :  
	'Output Parameters    :
	'################################################################*/
	
	public void FnWaitForElement(WebDriver driver, int timeOutInSeconds,String sXPath) 
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
	}

	/*'###############################################################
	'Function Name        : FnImpliciteWaitForElement
	'Function Description : To wait for element using impicite wait
	'Created by           : Ajinkya J
	'Input Parameters     : Driver,Time in seconds to wait,XPath of element to wait 
	'					  :  
	'Output Parameters    :
	'################################################################*/
	
	public void FnImplicitWaitForElement(WebDriver driver, int timeOutInSeconds) 
	{
		
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds,TimeUnit.SECONDS);
	}
	
	/*'###############################################################
	'Function Name        : FnMoveToElementAndClick
	'Function Description : To move to element and click using actions class
	'Created by           : Ajinkya J
	'Input Parameters     : Driver,XPath of element to wait 
	'					  :  
	'Output Parameters    :
	'################################################################*/
	
	public void FnMoveToElementAndClick(WebDriver driver, String sXPath) 
	{
		Actions action = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath(sXPath));
		action.moveToElement(ele).click().build().perform();
	}
	
	
	/*'###############################################################
	'Function Name        : FnClearTextFieldValue
	'Function Description : To clear value from text field for element
	'Created by           : Ajinkya J
	'Input Parameters     : Driver 
	'					  :  
	'Output Parameters    :
	'################################################################*/
	
	public void FnClearTextFieldValue(WebDriver driver,String sXPath) throws InterruptedException 
	{
		WebElement ele = driver.findElement(By.xpath(sXPath));
		ele.click();
		Thread.sleep(3000);
		ele.clear();
	}
	
	/*'###############################################################
	'Function Name        : FnScrollToElement
	'Function Description : To Scroll to element
	'Created by           : Ajinkya J
	'Input Parameters     : Driver,XPath of element to wait 
	'					  :  
	'Output Parameters    :
	'################################################################*/
	
	public void FnScrollToElement(WebDriver driver, WebElement ele) 
	{
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", ele);
	}
	
	/*'###############################################################
	'Function Name        : FnClickOnElementByJavaScriptExecutor
	'Function Description : To Click on element using JavaScriptExecutor
	'Created by           : Ajinkya J
	'Input Parameters     : Driver,XPath of element to click 
	'					  :  
	'Output Parameters    :
	'################################################################*/
	
	public void FnClickOnElementByJavaScriptExecutor(WebDriver driver, WebElement ele) 
	{
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);                
	}
	
	/*'###############################################################
	'Function Name        : FnSetText
	'Function Description : To set data in Text box
	'Created by           : Sumit
	'Input Parameters     : sTextBoxXPath -> XPath of Text box 
	'					  : sSetValue     -> Value to be set in the Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSetText(WebDriver driver, String sXPath, String sSetValue) throws Exception
	{
		System.out.println("*--FnSetText  >> "+sSetValue);	
		try 
		{
			
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSetValue != null && !"".equalsIgnoreCase(sSetValue))
				{
					int iCount = 0;
					boolean flgFound;
					flgFound = false;
					
					// To check the existence of Text Box before setting the value
					FnWaitForElement(driver,360,sXPath);
					flgFound = true;
	
					// Text box not found
					if(flgFound == false)
					{
						System.out.println("TextBox not found ->"+sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound="false";
					}
					// Text Box found so setting up the value
					else
					{
						System.out.println("TextBox found ->="+sXPath);
						if (sSetValue.equalsIgnoreCase("Blank"))
						{
							sSetValue = "";
						}
						WebElement ele = driver.findElement(By.xpath(sXPath));
		                FnScrollToElement(driver,ele);  
		                ele.clear();
						Thread.sleep(1000);
						ele.click();
						Thread.sleep(1000);
						ele.sendKeys(sSetValue);
						Thread.sleep(1000);
						ele.sendKeys(Keys.TAB);
						Thread.sleep(1000);
						String sGetValue = ele.getAttribute("value");
					
						
						if (sSetValue.equalsIgnoreCase("")){
							if (sGetValue == null){
								sGetValue = "";
							}
						}
						Thread.sleep(1000);
						while (sGetValue == null || !sSetValue.equalsIgnoreCase(sGetValue))
						{
							iCount++;
							FnScrollToElement(driver,ele);  
			                ele.clear();
							Thread.sleep(1000);
							ele.click();
							Thread.sleep(1000);
							ele.sendKeys(sSetValue);
							Thread.sleep(1000);
							ele.sendKeys(Keys.TAB);
							Thread.sleep(1000);
							sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
							Thread.sleep(1000);
							if ((sGetValue == null || !sSetValue.equalsIgnoreCase(sGetValue))&& iCount == 3 )
							{
								System.out.println("Value '"+sSetValue+"' is not set to TextBox "+sXPath);
								break;
							}
						}
					}					
				}
				else
				{
					System.out.println("Set value is blank or null");
				}
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			System.out.println("FnSetText not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnElementClick
	'Function Description : To Click Element
	'Created by           : Sumit
	'Input Parameters     : sXPath 			-> XPath of element 
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public void FnElementClick(WebDriver driver, String sXPath) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of element to be clicked
				FnWaitForElement(driver,360,sXPath);
				flgFound = true;
						
				// Element not found
				if (flgFound == false)
				{
					System.out.println("cf =FnElementClick - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Element found so clicking on the element
				else 
				{
					System.out.println("Element found ->="+sXPath);
                    Thread.sleep(5000);
                    WebElement ele = driver.findElement(By.xpath(sXPath));
                    FnScrollToElement(driver,ele);             
                    highLightElement(driver, ele);
                    FnClickOnElementByJavaScriptExecutor(driver,ele);
                    Thread.sleep(5000);
				}
			}
		}
		catch (UnhandledAlertException f) 
		{
			try
			{
				System.out.println("FnElementClick is throwing UnhandledAlertException ->"+f.getMessage());
				FnCheckMSG(driver);
				Thread.sleep(3000);
				FnSetFrame(driver, "main");
            	FnElementClick(driver, CommonPageElements.ORMB_Page_Refresh_Button);
				Thread.sleep(3000);
			}
			catch(Exception e)
			{
				System.out.println("FnElementClick not working ->"+e.getMessage());
				BaseTest.eFlgFound="false";			
			}

		}
	}
	
	/*'###############################################################
	'Function Name        : FnSelectCheckBox
	'Function Description : To Select check box
	'Created by           : Ajinkya
	'Input Parameters     : sXPath 			-> XPath of element 
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public void FnSelectCheckBox(WebDriver driver, String sXPath) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of element to be clicked
				FnWaitForElement(driver,360,sXPath);
				flgFound = true;
						
				// Element not found
				if (flgFound == false)
				{
					System.out.println("cf =FnElementClick - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Element found so clicking on the element
				else 
				{
					System.out.println("Element found ->="+sXPath);
                    Thread.sleep(5000);
                    WebElement ele = driver.findElement(By.xpath(sXPath));
                    FnScrollToElement(driver,ele); 
                    if(!ele.isSelected())
                    {
                    	highLightElement(driver, ele);
                        FnClickOnElementByJavaScriptExecutor(driver,ele);
                        Thread.sleep(5000);      	
                    }	
                    assertTrue(ele.isSelected(),"Check box is Selected now");
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnElementClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}	
	
	/*'###############################################################
	'Function Name        : FnDeSelectCheckBox
	'Function Description : To DeSelect/Uncheck check box
	'Created by           : Ajinkya
	'Input Parameters     : sXPath 			-> XPath of element 
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public void FnDeSelectCheckBox(WebDriver driver, String sXPath) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of element to be clicked
				FnWaitForElement(driver,360,sXPath);
				flgFound = true;
						
				// Element not found
				if (flgFound == false)
				{
					System.out.println("cf =FnElementClick - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Element found so clicking on the element
				else 
				{
					System.out.println("Element found ->="+sXPath);
                    Thread.sleep(5000);
                    WebElement ele = driver.findElement(By.xpath(sXPath));
                    FnScrollToElement(driver,ele); 
                    if(ele.isSelected())
                    {
                    	highLightElement(driver, ele);
                        FnClickOnElementByJavaScriptExecutor(driver,ele);
                        Thread.sleep(5000);      	
                    }	
                    assertTrue(!ele.isSelected(),"Check box is NOT Selected now");
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnElementClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnGetLabel
	'Function Description : To get label value
	'Input Parameters     : sLabelXPath -> XPath of Label
	'Output Parameters    : Returns the value of Label 
	'Written By			  : Sumit
	'################################################################*/
	public String FnGetLabel(WebDriver driver, String sXPath) throws Exception
	{
		String sGetValue;  // Used to return the value from Label
		sGetValue = "NoValue";
		WebElement slabel;
		try
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of Label before getting the value
				FnWaitForElement(driver,360,sXPath);
				flgFound = true;
			
				// Label not found
				if(flgFound == false)
				{
					System.out.println("cf =FnGetLabel - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
				}
			
				// Label found so getting the value
				else
				{
					System.out.println("Label found ->"+sXPath);
					slabel = driver.findElement(By.xpath(sXPath));
					sGetValue=slabel.getText().trim();
					System.out.println("Label Value = "+sGetValue);
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			System.out.println("FnGetLabel not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return sGetValue;
	}
	
	
	/*'###############################################################
	'Function Name        : FnSelectValue
	'Function Description : To select the option from Select box (Dropdown)
	'Created by           : Sumit
	'Input Parameters     : sXPath 			-> XPath of Text box 
	'                     : sSelectValue	-> Value to be set in the Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSelectValue(WebDriver driver, String sXPath, String sSelectValue) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSelectValue != null && !"".equalsIgnoreCase(sSelectValue))
				{
					boolean flgFound;
					flgFound = false;

					// To check the existence of Dropdown
					try 
					{ 
						WebDriverWait wait = new WebDriverWait(driver,360);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
						flgFound = true;
					} 
					catch (Exception e)
					{ 
						System.out.println("waitFor - Fail ->"+e.getMessage());
					}
					
					// Dropdown not found
					if (flgFound == false)
					{
						System.out.println("cf =FnSelectValue - Fail - Object not found ->"+sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound="false";
					}

					// Checking Select option available 
					else 
					{					    
						WebElement ele = driver.findElement(By.xpath(sXPath));
	                    FnScrollToElement(driver,ele);  
						Select option = new Select(ele);
						Boolean found = false;
						List<WebElement> allOptions = option.getOptions();
						
						for(int i=0; i<allOptions.size(); i++) 
						{
							
							if(allOptions.get(i).getText().equalsIgnoreCase(sSelectValue)) 
							{
								found=true;
								break;
							}
						}
						
						if(found) 
						{
							System.out.println("Dropdown Value exists-->"+sSelectValue);
						}
						else
						{
							System.out.println("Dropdown value not exists-->"+sSelectValue);
							FnScreenCapture();
						}
						Thread.sleep(2000);
						// Dropdown found so setting up the value
						option.selectByVisibleText(sSelectValue);
					}
				}
				else
				{
					System.out.println("Select value is blank or null");
				}
			}
		}
		catch (Exception e) 
        {
               System.out.println("FnSelectValue not working ->");
               BaseTest.eFlgFound="false";
               e.printStackTrace();
        }

	}
	
	/*'###############################################################
	'Function Name        : FnObjectExists
	'Function Description : To check object
	'Created by           : Sumit
	'Input Parameters     : sTextBoxXPath -> XPath of Text box 
	'Output Parameters    :
	'################################################################*/
	public String FnObjectExists(WebDriver driver, String sXPath) throws Exception
	{
		String flgFound = "false";
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Object
				try 
				{
					WebDriverWait wait = new WebDriverWait(driver,360);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
					flgFound = "true";
				}
				catch (Exception e) 
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				// Object not found
				if(flgFound.equalsIgnoreCase("false"))
				{
					System.out.println("cf =FnCheckObjectExists - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("FnCheckObjectExists not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return(flgFound);
	}
	
	/*'###############################################################
	'Function Name        : FnGetTextFromElement
	'Function Description : To get attribute value from element
	'Created By           : Sanjeev
	'Input Parameters     : sXPath -> XPath of element
	'					  : sAttribute -> Attribute to get	
	'Output Parameters    : Returns the value of Attribute
	'################################################################*/
	public String FnGetTextFromElement(WebDriver driver, String sXPath, String sAttribute) throws Exception
	{
		System.out.println("*--FnGetTextFromElement >> "+sAttribute);
		String sGetValue="NoValue";
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (FnObjectExists(driver, sXPath).equals("true"))
				{
					System.out.println("Element found ="+sXPath);
					System.out.println("Element found ="+sXPath);
					sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute(sAttribute);


					// To check the existence of Text Box before setting the value
					int iCount = 0;
					while ( sGetValue == null )
					{
						iCount++;
						try
						{
							WebDriverWait wait = new WebDriverWait(driver,360);
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
							//flgFound = true;
						}
						catch (Exception e) 
						{
							System.out.println("waitFor - Fail ->" + e.getMessage());
						}
						if ( iCount == 3 )
						{
							System.out.println("Value is not availble in "+sXPath);
							sGetValue = "NoValue";
							break;
						}
					}
				}
			}
			System.out.println(sAttribute + " attribute does not exist in the xpath");		 
		}
		catch (Exception e) 
		{
			// BaseClass.test.log(Status.FAIL, "FnSetText not working
			// ->"+e.getMessage());
			System.out.println("FnGetTextFromElement not working ->" + e.getMessage());
			BaseTest.eFlgFound = "false";
		}
		System.out.println("FnGetTextFromElement >> "+sAttribute +">>"+sGetValue);
		return sGetValue;
	}
	
	/*'###############################################################
	'Function Name        : FnLinkClick
	'Function Description : To Click Element
	'Created by           : Sumit
	'Input Parameters     : Link text	-> Text of link 
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public void FnLinkClick(WebDriver driver, String sLinktext) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Link
				try 
				{ 
					WebDriverWait wait = new WebDriverWait(driver,360);
					wait.until(ExpectedConditions.elementToBeClickable(By.linkText(sLinktext)));
					flgFound = true;
				}
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				
				// Link not found
				if (flgFound == false)
				{
					System.out.println("cf =FnLinkClick - Fail - Object not found ->"+sLinktext);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				
				// Link found so clicking on the link
				else 
				{
					System.out.println("Link found ->="+sLinktext);
					//Mouse hover on element
					Actions action = new Actions(driver);
					WebElement link = driver.findElement(By.linkText(sLinktext));
					action.moveToElement(link).perform();

					// Highlight element
					JavascriptExecutor js=(JavascriptExecutor)driver; 
					js.executeScript("arguments[0].setAttribute('style', 'background: orange; border: 2px solid red;');", link);

					Thread.sleep(3000);
					link.click();
					js.executeScript("arguments[0].removeAttribute('style','')", link);
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnElementClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	
	/*'###############################################################
	'Function Name        : FnWrite
	'Function Description : write in the xls file
	'Input Parameters     : sFileName 	-> File name with path 
	'                     : sWrite		-> Value to be write
	'Output Parameters    :
	'Written By 		  : Ajeet P
	'################################################################*/
	public static void FnWrite(String sFileName, String sWrite) 
	{
		try 
		{
			File oFile = new File(sFileName);
			/* Opening a file in append mode */
			FileWriter writer = new FileWriter(oFile, true);
			writer.append(sWrite);
			writer.flush();
			writer.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*'###############################################################
	'Function Name        : FnTestCaseStatusReport
	'Function Description : To write in the report sheet
	'Input Parameters     : sReportSheetName 	-> File name with path 
	'                     : Column values		-> 9 column values 
	'Output Parameters    :
	'################################################################*/
	public void FnTestCaseStatusReport(String sStatus, String sResults) throws Exception 
	{
		System.out.println("*--FnTestCaseStatusReport");
		String sReportSheetName		= BaseTest.sReportSheetName;
		String sFunctionalModule	= BaseTest.sFunctionalModule;
		String sScenario 			= BaseTest.sScenario;
		String sScriptName 			= BaseTest.sScriptName;
		String sTestCaseDesc 		= BaseTest.sTestDescription;
		String sEnvironment 		= "BK_WIN_EXISTING";
		String sURL 				= System.getProperty("appUrl");
		String sBuild 				= System.getProperty("buildNo");
		String sOTMTestcaseID  		= BaseTest.sOTMTestcaseID;
		try 
		{
			File oF = new File(sReportSheetName);
			if (!oF.exists())
			{
				System.out.println("Report File is not Exist");
				FnCreateReportFile(sReportSheetName);
			}
			FnWrite(sReportSheetName,sFunctionalModule);
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sScenario);
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sScriptName);
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sOTMTestcaseID);
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sTestCaseDesc);
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sStatus.trim());
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sResults);
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sEnvironment);
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sURL);
			FnWrite(sReportSheetName,",");
			FnWrite(sReportSheetName,sBuild);
			if (sStatus.equalsIgnoreCase("Fail"))
			{
				//FnScreenCapture();
				FnWrite(sReportSheetName,",");
				//FnWrite(sReportSheetName,sFinalScreenshotFileName);
				FnWrite(sReportSheetName,"\n");
				BaseTest.extentTest.get().log(Status.FAIL,sResults);
				System.out.println("####Reported Successfully; Status="+sStatus+"| TestCase="+sTestCaseDesc+"| ScriptName="+sScriptName+"| Comments="+sResults);
				assertTrue(false,sResults);
			}
			else
			{
				BaseTest.extentTest.get().log(Status.PASS,sResults);
				FnWrite(sReportSheetName,"\n");
				System.out.println("####Reported Successfully; Status="+sStatus+"| TestCase="+sTestCaseDesc+"| ScriptName="+sScriptName+"| Comments="+sResults);
				assertTrue(true,sResults);				
			}
			
			
		} 
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
			//FnTestCaseStatusReport("Fail", "Common Function Exception occured ->\n"+e.getLocalizedMessage());
		}
	}
	
	/*'###############################################################
	'Function Name        : FnCreateReportFile
	'Function Description : Create a report sheet
	'Input Parameters     : sFileName 	-> File name with path 
	'                     : 
	'Output Parameters    :
	'Written By			  : Ajeet P
	'################################################################*/
	public static void FnCreateReportFile(String sFileName) {
		try {
			File oFile = new File(sFileName);
			FileWriter writer = new FileWriter(oFile, true);
			writer.append("Functional Module");
			writer.append(',');
			writer.append("Scenario");
			writer.append(',');
			writer.append("ScriptName");
			writer.append(',');
			writer.append("OTMTestcaseID");
			writer.append(',');
			writer.append("Test Description");
			writer.append(',');
			writer.append("Status");
			writer.append(',');
			writer.append("Results");
			writer.append(',');
			writer.append("Environment");
			writer.append(',');
			writer.append("URL");
			writer.append(',');
			writer.append("Build");
			writer.append(',');
			writer.append("ScreenShot");
			writer.append('\n');
			writer.flush();
			writer.close();
			System.out.println("Report File is Created");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*'###############################################################
	'Function Name        : FnWriteInPaymentCSVFile
	'Function Description : To write in the csv sheet
	'Input Parameters     : sReportSheetName 	-> File name with path 
	'                     : Column values		-> 9 column values 
	'Output Parameters    :
	'################################################################*/
	public void FnWriteInPaymentCSVFile(int iStartingRow, String sPaymentFileName, String sSheetName, String sWorkbook) throws Exception 
	{
		System.out.println("*--FnWriteInPaymentCSVFile");
		String sAccountId, sMatchType, sMatchValue, sTenderId, sTenderAmt, sPaymentAmt, sDateRecvd, sBankAcct, sPaymentType, sCheckNo, sCurrency, sAcctIdType, sAcctIdentifier, sPayCharType1, sPayCharVal1, sPayCharType2, sPayCharVal2, sPayCharType3, sPayCharVal3, sPayCharType4, sPayCharVal4, sPayCharType5, sPayCharVal5, sMICRId, sPayorId,sStmtId;
		sAccountId			=	FnGetCellValue(iStartingRow ,1, sSheetName, sWorkbook).toString().trim();
		sMatchType			=	FnGetCellValue(iStartingRow ,2, sSheetName, sWorkbook).toString().trim();
		sMatchValue			=	FnGetCellValue(iStartingRow ,3, sSheetName, sWorkbook).toString().trim();
		sTenderId			=	FnGetCellValue(iStartingRow ,4, sSheetName, sWorkbook).toString().trim();
		sTenderAmt			=	FnGetCellValue(iStartingRow ,5, sSheetName, sWorkbook).toString().trim();
		sPaymentAmt			=	FnGetCellValue(iStartingRow ,6, sSheetName, sWorkbook).toString().trim();
		sDateRecvd			=	FnGetCellValue(iStartingRow ,7, sSheetName, sWorkbook).toString().trim();
		sBankAcct			=	FnGetCellValue(iStartingRow ,8, sSheetName, sWorkbook).toString().trim();
		sPaymentType		=	FnGetCellValue(iStartingRow ,9, sSheetName, sWorkbook).toString().trim();
		sCheckNo			=	FnGetCellValue(iStartingRow ,10, sSheetName, sWorkbook).toString().trim();
		sCurrency			=	FnGetCellValue(iStartingRow ,11, sSheetName, sWorkbook).toString().trim();
		sAcctIdType			=	FnGetCellValue(iStartingRow ,12, sSheetName, sWorkbook).toString().trim();
		sAcctIdentifier		=	FnGetCellValue(iStartingRow ,13, sSheetName, sWorkbook).toString().trim();
		sPayCharType1		=	FnGetCellValue(iStartingRow ,14, sSheetName, sWorkbook).toString().trim();
		sPayCharVal1		=	FnGetCellValue(iStartingRow ,15, sSheetName, sWorkbook).toString().trim();
		sPayCharType2		=	FnGetCellValue(iStartingRow ,16, sSheetName, sWorkbook).toString().trim();
		sPayCharVal2		=	FnGetCellValue(iStartingRow ,17, sSheetName, sWorkbook).toString().trim();
		sPayCharType3		=	FnGetCellValue(iStartingRow ,18, sSheetName, sWorkbook).toString().trim();
		sPayCharVal3		=	FnGetCellValue(iStartingRow ,19, sSheetName, sWorkbook).toString().trim();
		sPayCharType4		=	FnGetCellValue(iStartingRow ,20, sSheetName, sWorkbook).toString().trim();
		sPayCharVal4		=	FnGetCellValue(iStartingRow ,21, sSheetName, sWorkbook).toString().trim();
		sPayCharType5		=	FnGetCellValue(iStartingRow ,22, sSheetName, sWorkbook).toString().trim();
		sPayCharVal5		=	FnGetCellValue(iStartingRow ,23, sSheetName, sWorkbook).toString().trim();
		sMICRId				=	FnGetCellValue(iStartingRow ,24, sSheetName, sWorkbook).toString().trim();
		sPayorId			=	FnGetCellValue(iStartingRow ,25, sSheetName, sWorkbook).toString().trim();
		sStmtId				=	FnGetCellValue(iStartingRow ,26, sSheetName, sWorkbook).toString().trim();
		
		
		System.out.println(sAccountId); 
		System.out.println(sMatchType);
		System.out.println(sMatchValue);
		System.out.println(sTenderId);
		System.out.println(sTenderAmt);
		System.out.println(sPaymentAmt);
		System.out.println(sDateRecvd);
		System.out.println(sBankAcct);
		System.out.println(sPaymentType);
		System.out.println(sCheckNo);
		System.out.println(sCurrency);
		System.out.println(sAcctIdType);
		System.out.println(sAcctIdentifier);
		System.out.println(sPayCharType1);
		System.out.println(sPayCharVal1);
		System.out.println(sPayCharType2);
		System.out.println(sPayCharVal2);
		System.out.println(sPayCharType3);
		System.out.println(sPayCharVal3);
		System.out.println(sPayCharType4);
		System.out.println(sPayCharVal4);
		System.out.println(sPayCharType5);
		System.out.println(sPayCharVal5);
		System.out.println(sMICRId);
		System.out.println(sPayorId);
		System.out.println(sStmtId);
		
		
		try 
		{
			File oF = new File(sPaymentFileName);
			if (!oF.exists()){
				System.out.println("Payment File is not Exist");
				FnCreatePaymentCSVFile(sPaymentFileName);
			}
			FnWrite(sPaymentFileName,sAccountId);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sMatchType);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sMatchValue);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sTenderId);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sTenderAmt);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPaymentAmt);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sDateRecvd);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sBankAcct);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPaymentType);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sCheckNo);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sCurrency);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sAcctIdType);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sAcctIdentifier);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharType1);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharVal1);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharType2);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharVal2);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharType3);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharVal3);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharType4);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharVal4);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharType5);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayCharVal5);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sMICRId);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sPayorId);
			FnWrite(sPaymentFileName,",");
			FnWrite(sPaymentFileName,sStmtId);
			
			FnWrite(sPaymentFileName,"\n");
			System.out.println("Payment File :"+sPaymentFileName+" Created Successfully");
		} 
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
			FnTestCaseStatusReport("Fail", "Common Function Exception occured ->\n"+e.getLocalizedMessage());
		}
	}
	
	/*'###############################################################
	'Function Name        : FnCreatePaymentCSVFile
	'Function Description : Create a Payment CSV sheet
	'Input Parameters     : sFileName 	-> File name with path 
	'                     : 
	'Output Parameters    :
	'Written By			  : Ajeet P
	'################################################################*/
	public static void FnCreatePaymentCSVFile(String sFileName) {
		try {
			File oFile = new File(sFileName);
			FileWriter writer = new FileWriter(oFile, true);
			writer.append("Account Id");
			writer.append(',');
			writer.append("Match Type");
			writer.append(',');
			writer.append("Match Value");
			writer.append(',');
			writer.append("Tender ID");
			writer.append(',');
			writer.append("Tender Amount");
			writer.append(',');
			writer.append("Payment Amount");
			writer.append(',');
			writer.append("Date Received");
			writer.append(',');
			writer.append("Bank Account");
			writer.append(',');
			writer.append("Payment Type");
			writer.append(',');
			writer.append("Check Number");
			writer.append(',');
			writer.append("Currency");
			writer.append(',');
			writer.append("Account ID Type");
			writer.append(',');
			writer.append("Account Identifier");
			writer.append(',');
			writer.append("Payment Char type1");
			writer.append(',');
			writer.append("Payment Char Value1");
			writer.append(',');
			writer.append("Payment Char type2");
			writer.append(',');
			writer.append("Payment Char Value2");
			writer.append(',');
			writer.append("Payment Char type3");
			writer.append(',');
			writer.append("Payment Char Value3");
			writer.append(',');
			writer.append("Payment Char type4");
			writer.append(',');
			writer.append("Payment Char Value4");
			writer.append(',');
			writer.append("Payment Char type5");
			writer.append(',');
			writer.append("Payment Char Value5");
			writer.append(',');
			writer.append("MICR ID");
			writer.append(',');
			writer.append("Payor Id");
			writer.append(',');
			writer.append("Statement Id");
			writer.append('\n');
			writer.flush();
			writer.close();
			System.out.println("Payment File is Created");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*'###############################################################
	'Function Name        : FnValueContainedInWebTable
	'Function Description : To check if a value is contained in a cell of a web table Web table Image
	'Created by           : Sumit
	'Input Parameters     : sWebTableXPath -> XPath of Web table
						  : sCompareValue -> Value to compare
						  : sColumn -> Column in which to search
	'Output Parameters    : Not Applicable
	'################################################################*/
	public String FnValueContainedInWebTable(WebDriver driver, String sXPath,String sCompareValue, int sColumn ) throws Exception
	{
		System.out.println("*--FnValueContainedInWebTable"+sCompareValue+sColumn);
		String sResult = "";
		boolean flgFound;
		flgFound = false;
		int sRowCount = 0;

		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of webtable
				try 
				{
					WebDriverWait wait = new WebDriverWait(driver,360);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
					flgFound = true;
				}
				catch (Exception e) {
					System.out.println("waitFor - Fail ->" + e.getMessage());
				}
			}
			
			// webtable not found
			if(flgFound == false)
			{
				System.out.println("webtable not found ->"+sXPath);
				FnScreenCapture();
				BaseTest.eFlgFound="false";
			}
			// webtable found so getting the value
			else
			{
				System.out.println("webtable found ->="+sXPath);
				List <WebElement> rows = driver.findElements(By.xpath(sXPath+"/tbody/tr/td[1]"));
				
				sRowCount = rows.size();
				System.out.println("No of rows are : " +sRowCount); 
				
				for(int i=1;i<=sRowCount;i++) // I count starts from 1 as there are Nested tables in Bill segment table otherwise I count will be 2
				{
					String sCompareText = driver.findElement(By.xpath(sXPath+"/tbody/tr["+i+"]/td["+sColumn+"]")).getText().replace("‑", "-");
					sCompareText = sCompareText.toUpperCase();
					System.out.println("Value from table(Found value)========="+sCompareText);
					sCompareValue = sCompareValue.toUpperCase();
					System.out.println("Value to match========="+sCompareValue);
					
					if(!sCompareText.contains(sCompareValue))
					{
						sResult = "FAIL";
						System.out.println("Result is ->"+sResult);
					}
					else{
							if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
						{
							sResult = "PASS";
							System.out.println("Result is ->"+sResult);
							break;
						}
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnValueContainedInWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return sResult;
	}
	
	/*####################################################################################
	'Function Name        : FnCheckMSG
	'Function Description : Checks alert or web message box
	'Input Parameters     : 
	'Output Parameters    : 
	'####################################################################################*/
	public String FnCheckMSG(WebDriver driver) throws Exception
	{
		System.out.println(">>>>>>>>>>--FnCheckMSG");
		String sGetValue = "NoValue";
		try 
		{     
			Alert alert= driver.switchTo().alert();
			sGetValue=alert.getText();
			alert.accept();
			if(sGetValue == "") 
			{
				sGetValue = "NoValue";
				System.out.println("alert was present -> "+sGetValue);
	        }
	    }
		catch (UnhandledAlertException e)
		{
			System.out.println("Unhandled alert");
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println("Alert data: " + alertText);
			alert.accept();
	    }
		catch (NoAlertPresentException e)
		{
			System.out.println("alert was not present");
	    } 
		return (sGetValue);
	}
	
	/*'###############################################################
	'Function Name        : FnPageVerify
	'Function Description : To verify the page existence
	'Created by           : Ajeet P
	'Input Parameters     : sXPath 			-> XPath of Element
	'                     : sPageName		-> Page Name for Reporting 
	'Output Parameters    : flgFound		-> "true" if page found
	'################################################################*/
	public void FnPageVerify(WebDriver driver, String sXPath, String sPageName) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				String flgFound;
				flgFound = "false";		
				
				FnWaitForElement(driver, 360, sXPath);

				String pagename= FnGetLabel(driver,sXPath);

				if(sPageName.toUpperCase().contains(pagename.toUpperCase()))
				{
						flgFound = "true";
				}
				if(flgFound.equalsIgnoreCase("true")) 
				{
					System.out.println("Page ["+sPageName+"] found by Object "+sXPath);
				}
				else 
				{
					System.out.println("Page ["+sPageName+"] not found by Object "+sXPath);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("FnPageVerify not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnAlertButtonClick
	'Function Description : To click on OK button on alert message
	'Written by  		  : Sumit
	'################################################################*/	
	public void FnAlertButtonClick(WebDriver driver) throws Exception
	{
		System.out.println("*--FnAlertButtonClick");
		try 
		{		
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of element
				if (isAlertPresent(driver)) 
				{
					flgFound = true;
				}
				// Element not found
				if (flgFound == false)
				{
					System.out.println("Alert box Button not found ");
					FnScreenCapture();
				}
				// Element found so clicking on the element
				else 
				{
					System.out.println("Alert box Button found ");
					Alert alert=driver.switchTo().alert();
					alert.accept();
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("FnAlertButtonClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : isAlertPresent
	'Function Description : It returns if the alert is present on not
	'Created by           : Sumit
	'Input Parameters     : Nothing 
	'Output Parameters    : Either of the following :
							(a) returns - true if the alert is present
							(b) returns - false if the alert is not present
	' Written by		  : Ajeet P
	'################################################################*/	
	public static boolean isAlertPresent(WebDriver driver) 
	{ 
	    try 
	    { 
	    	driver.switchTo().alert();
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   
	
	/*'###############################################################
	'Function Name        : FnConfirmMessage
	'Function Description : It captures the confirmation message and clicks on 'OK' button.
	'Created by           : Sumit
	'Input Parameters     : Alert Message XPath 
	'Output Parameters    : Approval Workflow ID (if present), else any other message that is displayed.
	'################################################################*/	
	public String FnConfirmMessage (WebDriver driver, String sXPath) throws Exception 
	{
		String sConfirmMessage = "GLOBALFLAGFALSE";
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				Alert alert=driver.switchTo().alert();
				sConfirmMessage=alert.getText();
				alert.accept();
			}
		}
		catch(Exception e)
		{
			System.out.println("FnConfirmMessage not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return (sConfirmMessage);
	}
	
	/*'###############################################################
	'Function Name        : FnTextBoxPressEnter
	'Function Description : To Click Image
	'Input Parameters     : sXPath 			-> XPath of Image 
	'Output Parameters    :
	'################################################################*/
	public void FnTextBoxPressEnter(WebDriver driver, String sXPath) throws Exception
	{
		System.out.println("*--FnTextBoxPressEnter");
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				int iCount = 0;
				boolean flgFound;
				flgFound = false;

				// To check the existence of Image before clicking the image
				while (iCount < 3)
				{
					System.out.println("Checking for Object Availability=>'"+sXPath+"'");
					iCount++;
					
					// To check the existence of element to be clicked
					FnWaitForElement(driver,360,sXPath);
					flgFound = true;
				}

				// Image not found
				if (flgFound == false)
				{
					System.out.println("TextBox not found ="+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound = "false";
				}

				// Image found so clicking on the image
				else
				{
					System.out.println("TextBox found ="+sXPath);
					Thread.sleep(1000);
					driver.findElement(By.xpath(sXPath)).sendKeys("");
					Thread.sleep(2000);
					WebElement textbox = driver.findElement(By.xpath(sXPath));
					textbox.sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					
					iCount = 0;
					String sObjectXPath = (String) FnCheckObjectExists(driver,sXPath);
					System.out.println("sObjectXPath is: "+sObjectXPath);
					if (sObjectXPath.equalsIgnoreCase("true")) {
						String sGetValue = (String) FnGetTextFromElement(driver,sXPath,"readonly");
						System.out.println("----------?>>>>>>>>>>>>>>>"+sGetValue);
						while (sGetValue.equalsIgnoreCase("False")){
							Thread.sleep(1000);
							
							driver.findElement(By.xpath(sXPath)).sendKeys("");
							Thread.sleep(2000);
							textbox = driver.findElement(By.xpath(sXPath));
							textbox.sendKeys(Keys.ENTER);
							Thread.sleep(2000);
							
							sObjectXPath = (String) FnCheckObjectExists(driver,sXPath);
							if (sObjectXPath.equalsIgnoreCase("true")) {
								sGetValue = (String)FnGetTextFromElement(driver, sXPath,"readonly");
								Thread.sleep(1000);
								iCount++;
								if (iCount == 3){
									System.out.println("Press Enter executes 3 times");
									break;
								}
							}else {
								System.out.println("Object Not found after Second, ...... Press enter"); 
								break;
							}
						}
					} else {
						System.out.println("Object Not found after first Press enter"); 
					}
				
				}
			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
		}
	}

	/*'###############################################################
	'Function Name        : FnCheckObjectExists
	'Function Description : To check object, but not to stop the script execution
	'Created by           : Sumit
	'Input Parameters     : sTextBoxXPath -> XPath of Text box 
	'Output Parameters    :
	'################################################################*/
	public String FnCheckObjectExists(WebDriver driver, String sXPath) throws Exception
	{
		String flgFound = "false";
		int iCount = 0;
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Object
				try 
				{
					while (iCount < 1)
					{
						iCount++;
						WebDriverWait wait = new WebDriverWait(driver,360);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
						flgFound = "true";
						break;
					}
				}
				catch (Exception e) 
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				
				// Object not found
				if(flgFound.equalsIgnoreCase("false"))
				{
					System.out.println("cf =FnCheckObjectExists - Object not found ->"+sXPath);
					FnScreenCapture();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("FnCheckObjectExists not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return(flgFound);
	}
	
	/*'###############################################################
	'Function Name        : FnGetCellValueFromWebTable
	'Function Description : To get value from web table from providing row and column number
	'Created by           : Sumit
	'Input Parameters     : sWebTableXPath	-> XPath of Web table
						  : iRow		-> row number
						  : iColumn		-> column number
	'Output Parameters    : Return the cell value
	'################################################################*/
	public String FnGetCellValueFromWebTable(WebDriver driver, String sXPath,int iRow, int iColumn) throws Exception
	{
		System.out.println("*--FnGetCellValueFromWebTable");
		String sCellValue = "NoValue";
		boolean flgFound = false;
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Web Table before trying to retrieve the column names
				try
				{
					WebDriverWait wait = new WebDriverWait(driver,360);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				} 
				// Web table not found
				if(flgFound == false)
				{
					System.out.println("Web table not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Web Table found so getting the value from required column
				else 
				{
					System.out.println("Web Table found ->"+sXPath);
					sCellValue=driver.findElement(By.xpath(sXPath+"/tbody/tr["+iRow+"]/td["+iColumn+"]")).getText().replace("‑", "-");

					if(sCellValue.isEmpty())
					{
						System.out.println("Cell value is empty...");
					}
					System.out.println("Cell value "+sCellValue);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnGetCellValueFromWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sCellValue;
	}
	
	/*'###############################################################
	'Function Name        : FnSetTextByKeyPress
	'Function Description : To set data in Text box and press tab
	'Created by           : Sumit
	'Input Parameters     : sTextBoxXPath -> XPath of Text box 
	'					  : sSetValue     -> Value to be set in the Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSetTextByKeyPress(WebDriver driver, String sXPath, String sSetValue) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Text Box before setting the value
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
					//e.printStackTrace();
				} 

				// Text box not found
				if(flgFound == false)
				{
					System.out.println("TextBox not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Text Box found so setting up the value
				else
				{
					System.out.println("TextBox found ->="+sXPath);
					driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
					Thread.sleep(2000);
					WebElement textbox = driver.findElement(By.xpath(sXPath));
					textbox.sendKeys(Keys.TAB);
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnSetTextByKeyPress not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnGetSelectedValue
	'Function Description : To get selected value in select box
	'Created by           : Sumit S
	'Input Parameters     : sSelectBoxXPath -> XPath of Select Box
	'Output Parameters    : Returns the value of selected text 
	'################################################################*/
	public String FnGetSelectedValue(WebDriver driver, String sXPath) throws Exception
	{
		String sGetValue;  // used to return the value get from Select Box
		sGetValue = "false";
		
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Drop down
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}

				// Drop down not found
				if (flgFound == false)
				{
					System.out.println("cf =FnSelectValue - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Drop down found so getting the value
				else 
				{
					Select select = new Select(driver.findElement(By.xpath(sXPath)));
					WebElement option = select.getFirstSelectedOption();
					String defaultItem = option.getText();
					System.out.println("Selected value in dropdown-->"+defaultItem );
					sGetValue=defaultItem;
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnGetSelectedValue not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
			//e.printStackTrace();
		}
		return sGetValue;
	}
	
	/*'###############################################################
	'Function Name        : FnButtonClick
	'Function Description : To Click Button
	'Created by           : Sumit
	'Input Parameters     : sXPath 			-> XPath of Text box 
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public void FnButtonClick(WebDriver driver, String sXPath) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of Button
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}

				// Button not found
				if (flgFound == false)
				{
					System.out.println("cf =FnButtonClick - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Button found so clicking on the element
				else 
				{
					System.out.println("Element found ->="+sXPath);
					//Mouse hover on element
					Actions action = new Actions(driver);
					WebElement ele = driver.findElement(By.xpath(sXPath));
					action.moveToElement(ele).perform();
					
					// Highlight element
					JavascriptExecutor js=(JavascriptExecutor)driver; 
					js.executeScript("arguments[0].setAttribute('style', 'background: orange; border: 2px solid red;');", ele);
					
					Thread.sleep(3000);
					js.executeScript("arguments[0].removeAttribute('style','')", ele);
					ele.click();				
					Thread.sleep(2000);
				}
			} 
		}
		catch (Exception e) 
		{
			System.out.println("FnButtonClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnButtonMouseOver
	'Function Description : To MouseOver Button
	'Created by           : Sumit
	'Input Parameters     : sXPath 			-> XPath of button 
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public void FnButtonMouseOver(WebDriver driver, String sXPath) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of button
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
					//e.printStackTrace();
				} 

				// Button not found
				if(flgFound == false)
				{
					System.out.println("Button not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Button found so mouse hover
				else
				{
					System.out.println("Button found ->="+sXPath);
					Actions action = new Actions(driver);
					WebElement ele = driver.findElement(By.xpath(sXPath));
					action.moveToElement(ele).perform();
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnButtonMouseOver not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnImageClick
	'Function Description : To Click Image
	'Written By           : Sanjeev Acharya
	'Input Parameters     : sXPath 			-> XPath of Image
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public void FnImageClick(WebDriver driver, String sXPath) throws Exception
	{
		System.out.println("*--FnImageClick");
		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				boolean flgFound;
				flgFound = false;
				if ((BaseTest.sfusion.equalsIgnoreCase("Y")) && (sXPath.contains("IM_USER_HOME"))
						|| (BaseTest.sfusion.equalsIgnoreCase("Y")) && (sXPath.contains("IM_SAVE"))
						|| (BaseTest.sfusion.equalsIgnoreCase("Y")) && (sXPath.contains("IM_menuButton")))
				{
					System.out.println("Log:Info:Fusion flow");
					if (sXPath.contains("IM_USER_HOME")) 
					{
						sXPath = "//span[@id='IM_USER_HOME']";
					}
					if (sXPath.contains("IM_menuButton"))
					{
						sXPath = "//span[@id='IM_menuButton']";
					}
					if (sXPath.contains("IM_SAVE")) 
					{
						sXPath = "//input[@id='IM_SAVE']";
					}
					try 
					{
						//WebDriverWait wait = new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;
					} 
					catch (Exception e)
					{
						System.out.println("waitFor - Fail ->" + e.getMessage());
					}
					
					// Image not found
					if (flgFound == false)
					{
						System.out.println("info:Image not found =" + sXPath);
						System.out.println("Warning:Image not found =" + sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound = "false";
						System.out.println("Fail : cf =FnImageMouseOver - Fail - Object not found->" + sXPath);
					}

					// Image found so click on image
					else 
					{
						System.out.println("INFO:Image found =" + sXPath);
						System.out.println("Comment:Image found =" + sXPath);
						driver.findElement(By.xpath(sXPath)).click();
					}
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Logger.info : Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			System.out.println("FnTestCaseStatusReport : Fail Common Function Exception occured ->\n"); // +erromsg(e)
		}
	}
	
	/*'###############################################################
	'Function Name        : FnImageMouseOver
	'Function Description : To MouseOver Image
	'Input Parameters     : sXPath 			-> XPath of Image
	'                     :  
	'Output Parameters    :
	'Written By			  : Sanjeev A
	'################################################################*/
	public void FnImageMouseOver(WebDriver driver, String sXPath) throws Exception 
	{
		System.out.println("*--FnImageMouseOver");
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;
				if ((BaseTest.sfusion.equalsIgnoreCase("Y")) && (sXPath.contains("IM_USER_HOME"))
						|| (BaseTest.sfusion.equalsIgnoreCase("Y")) && (sXPath.contains("IM_SAVE"))
						|| (BaseTest.sfusion.equalsIgnoreCase("Y")) && (sXPath.contains("IM_menuButton"))) 
				{
					System.out.println("Log:Info:Fusion flow");
					if (sXPath.contains("IM_USER_HOME")) 
					{
						sXPath = "//span[@id='IM_USER_HOME']";
					}
					if (sXPath.contains("IM_menuButton")) 
					{
						sXPath = "//span[@id='IM_menuButton']";
					}
					if (sXPath.contains("IM_SAVE")) 
					{
						sXPath = "//input[@id='IM_SAVE']";
					}
					try 
					{
						//WebDriverWait wait = new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;
					} 
					catch (Exception e) 
					{
						System.out.println("waitFor - Fail ->" + e.getMessage());
					}
					
					// Image not found
					if (flgFound == false) 
					{
						System.out.println("info:Image not found =" + sXPath);
						System.out.println("Warning:Image not found =" + sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound = "false";

						System.out.println("Fail : cf =FnImageMouseOver - Fail - Object not found->" + sXPath);
					}

					// Image found so mouse over on image
					else 
					{
						System.out.println("INFO:Image found =" + sXPath);
						System.out.println("Comment:Image found =" + sXPath);

						// web.image(sXPath).mouseOver();
						Actions action = new Actions(driver);
						WebElement xpath = driver.findElement(By.xpath(sXPath));
						action.moveToElement(xpath).build().perform();
					}
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Logger.info : Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			System.out.println("FnTestCaseStatusReport : Fail Common Function Exception occured ->\n"); // +erromsg(e)
		}
	}
	
	/*'###############################################################
	'Function Name        : FnElementMouseOver
	'Function Description : To Mouse Over Element
	'Created by           : Sumit
	'Input Parameters     : sXPath 			-> XPath of Element 
	'                     :  
	'Output Parameters    :
	'################################################################*/
	public void FnElementMouseOver(WebDriver driver, String sXPath) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of element
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
					//e.printStackTrace();
				} 

				// Element not found
				if(flgFound == false)
				{
					System.out.println("Element not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Element found so mouse hover
				else
				{
					System.out.println("Element found ->="+sXPath);
					Actions action = new Actions(driver);
					WebElement ele = driver.findElement(By.xpath(sXPath));
					action.moveToElement(ele).perform();
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnElementMouseOver not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'#############################################################################
	'Function Name        : FnVerifyObjectState
	'Function Description : To verify whether an object is in disable or enable state
	'Created by           : Sumit
	'Input Parameters     : sObjectXPath 	-> XPath of the Object on the page 
	'Output Parameters    : Returns the enable or disable state of the object
	'################################################################################*/
	public String FnVerifyObjectState(WebDriver driver, String sXPath) throws Exception
	{
		
		String sObjState = "Not Found";	
		
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{

				boolean flgFound;
				flgFound = false;

				// To check the existence of object
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}

				// Object not found
				if (flgFound == false)
				{
					System.out.println("cf =FnVerifyObjectState - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Object found so,checking the object's state (enable or disable)
				else 
				{
					WebElement ele = driver.findElement(By.xpath(sXPath));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView();", ele);
					System.out.println("Object found ->="+sXPath);
					boolean ObjState= driver.findElement(By.xpath(sXPath)).isEnabled();
					if (ObjState)
					{
						System.out.println("The object is in enable state->"+sXPath);
						sObjState="False"; //Disable false
					}
					else
					{
						System.out.println("The object is in disable state->"+sXPath);
						sObjState="True"; //Disable true
					}
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnVerifyObjectState not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return sObjState;
	}
	
	/*'###############################################################
	'Function Name        : FnGetTableColumnNames
	'Function Description : To get all column names of Web Table
	'Created by           : Sumit
	'Input Parameters     : sWebTAbleXPath -> XPath of Web table
	'Output Parameters    : Returns the column names of Web Table (comma delimited)
	'################################################################*/
	public String FnGetTableColumnNames(WebDriver driver, String sXPath) throws Exception
	{
		String sColumnNames;  // used to return the column names of a Web Table
		sColumnNames = "NoValue";
		String sColName;
		WebElement baseTable = driver.findElement(By.tagName("table"));
		WebElement tableRow;

		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Web Table before trying to retrieve the column names
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
					//e.printStackTrace();
				} 

				// Web table not found
				if(flgFound == false)
				{
					System.out.println("cf =FnGetTableColumnNames - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Web Table found so getting the value of column names
				else
				{
					System.out.println("Web Table found ->"+sXPath);
					List<WebElement>  col = driver.findElements(By.xpath(sXPath+"/thead/tr/th"));
					int sColumnCount = col.size();
					System.out.println("No of cols are : " +sColumnCount); 

					for(int i=2;i<=sColumnCount;i++) 
					{
						tableRow = baseTable.findElement(By.xpath(sXPath+"/thead/tr/th["+i+"]"));
						sColName = tableRow.getText();
						if (i==2)
						{
							sColumnNames = sColName;
						}
						else 
						{
							sColumnNames = sColumnNames + "," + sColName;
						}
					}
					Thread.sleep(2000);
				}					
			}
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			System.out.println("FnGetTableColumnNames not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return sColumnNames;	
	}
	
	/*'#######################################################################
	'Function Name        : isNumeric
	'Created by           : Sumit
	'########################################################################*/	
	public static boolean isNumeric(String value) 
	{
		try 
		{
			Double.parseDouble(value);
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	/*'###############################################################
	'Function Name        : FnGetValueFromWebTable
	'Function Description : To get value from web table
	'Created by           : Sumit
	'Input Parameters     : sWebTAbleXPath -> XPath of Web table
	'Input Parameter      : sSearchText    -> Text to search in the webtable to get required row
	'Input Parameter      : iColToSearch   -> Column number of text to search in the web table
	'Input Parameter      : iColToGet      -> Column number from which to get the value
	'Output Parameters    : Returns the value from Web table
	'################################################################*/
	public String FnGetValueFromWebTable(WebDriver driver, String sXPath, String sSearchText, int iColToSearch, int iColToGet) throws Exception
	{
		String sGetText = "NoValue";
		int sRowCount = 0;
		String sCompareText= "";
		boolean flgTextFound = false;
		boolean flgFound= false;
			
		WebElement baseTable = driver.findElement(By.tagName("table"));
		WebElement tableRow;
			
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Web Table before trying to retrieve the column values
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				} 

				// Web table not found
				if(flgFound == false)
				{
					System.out.println("Web table not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Web Table found so getting the value from required column
				else
				{
					System.out.println("Web Table found ->"+sXPath);
					
					List<WebElement>  rows = driver.findElements(By.xpath(sXPath+"/tbody/tr/td[1]"));
					sRowCount = rows.size();
					System.out.println("No of rows are : " +sRowCount); 
						
					for(int i=1;i<=sRowCount;i++) 
					{
						try 
						{
							tableRow = baseTable.findElement(By.xpath(sXPath+"/tbody/tr["+i+"]/td["+iColToSearch+"]"));
							sCompareText = tableRow.getText();
//							System.out.println(sCompareText);
						} 
						catch (Exception e) 
						{
							System.out.println("Not able to retrieve table cell value for Compare Text"); 
							sCompareText = "NoValue";
							e.getLocalizedMessage();
						}
						
						if (sCompareText == null) 
						{
							sCompareText = "NoValue";
						}
						
						sCompareText = sCompareText.trim(); 
							
						if(sSearchText.trim().equalsIgnoreCase(sCompareText.replace("‑", "-")))
						{ 
							flgTextFound = true;
							try 
							{
								tableRow = baseTable.findElement(By.xpath(sXPath+"/tbody/tr["+i+"]/td["+iColToGet+"]"));
								sGetText = tableRow.getText();
//								System.out.println(sGetText);
							} 
							catch (Exception e) 
							{
								System.out.println("Not able to retrive table cell value for Search Text");
								sGetText = "NoValue";
								e.getLocalizedMessage();
							}
							
							if (sGetText == null)
							{
								sGetText = "NoValue";
							}
							sGetText = sGetText.trim();
							break;
						}
						else if ((isNumeric(sSearchText) == true) && ((isNumeric(sCompareText))== true))
						{
							System.out.println("At NUMERIC value else loop");
							BigDecimal intFinal1 = new BigDecimal(sSearchText);
							BigDecimal intFinal2 = new BigDecimal(sCompareText);
							if(intFinal1.compareTo(intFinal2)==0)
							{
								flgTextFound = true;
								try
								{
									tableRow = baseTable.findElement(By.xpath(sXPath+"/tbody/tr["+i+"]/td["+iColToGet+"]"));
									sGetText = tableRow.getText();
									System.out.println(sGetText);
								} 
								catch (Exception e) 
								{
									System.out.println("NUMERIC value not found");
									sGetText = "NoValue";
									e.getLocalizedMessage();
								}
								
								if (sGetText == null){
									sGetText = "NoValue";
								}
								
								sGetText = sGetText.trim();
								break;
							}
						}
					}
					
					if (flgTextFound == true)
					{
						System.out.println("Text " + sSearchText + " to search in Web Table found"+sXPath);
					}
					else 
					{
						System.out.println("Text " + sSearchText + " to search in Web Table not found"+sXPath);
						sGetText = "NOTFOUND";
					}
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			System.out.println("FnGetValueFromWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sGetText;
	}
	
	/*'###############################################################
	'Function Name        : FnClickLinkOnWebTable
	'Function Description : To Click Web table link
	'Created by           : Sumit
	'Input Parameters     : sLnikXPath -> XPath of Link
	'Output Parameters    :Not Applicable
	'################################################################*/
	public void FnClickLinkOnWebTable(WebDriver driver, String sLinktext) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Link
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(sLinktext)));
					flgFound = true;
				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}

				// Link not found
				if (flgFound == false)
				{
					System.out.println("cf =FnClickLinkOnWebTable - Fail - Object not found ->"+sLinktext);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Link found so clicking on the link
				else 
				{
					System.out.println("Link found ->="+sLinktext);
					//Mouse hover on link
					Actions action = new Actions(driver);
					WebElement link = driver.findElement(By.linkText(sLinktext));
					action.moveToElement(link).perform();
					Thread.sleep(2000);
					link.click();
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnClickLinkOnWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnClickImageOnWebTable
	'Function Description : To Click Web table Image
	'Created by           : Sumit
	'Input Parameters     : sImageXPath -> XPath of Image
	'Output Parameters    : Not Applicable
	'################################################################*/
	public void FnClickImageOnWebTable(WebDriver driver, String sXPath) throws Exception
	{		
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Image
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}

				// Image not found
				if (flgFound == false)
				{
					System.out.println("cf =FnClickImageOnWebTable - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Click on Image
				else 
				{
					System.out.println("Image found ->="+sXPath);
					driver.findElement(By.xpath(sXPath)).click();		
					Thread.sleep(2000);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnClickImageOnWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
    'Function Name        : FnGetProp
    'Function Description : 
    'Created by            : Sumit
    'Input Parameters     : 
    'Output Parameters    : 
    '################################################################*/	
	public String FnGetProp(String sValue, String sPropFilePath) throws Exception
	{
		String value = "NoValue";
		try 
		{
			Properties properties = new Properties();
			properties.load(new FileInputStream(sPropFilePath));
			value = properties.getProperty(sValue);
		} 
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
			
		}
		return value;
	}
	
	/*'###############################################################
	'Function Name        : currentTime
	'Function Description : To get current time
	'Input Parameters     : 
	'Output Parameters    : Returns the value of time 
	'Written By           : Ajeet P
	'################################################################*/
	public String currentTime () 
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		Date date = new Date();
		String q = dateFormat.format(date);
		return q;
	}
	
	/*'###############################################################
	'Function Name        : replaceSingleQuote
	'Function Description : Replace the single quote
	'Input Parameters     : sValue as string
	'Output Parameters    : Returns sValue without single quote 
	'Written By           : Ajeet P
	'################################################################*/
	public String replaceSingleQuote (String sValue) throws Exception 
	{
		String q = "NoValue";
		try 
		{
			if (sValue != null) 
			{
				if (sValue.contains("'")) 
				{
					q = sValue.replaceAll("'", "''");
				}
				else 
				{
					q = sValue;
				}
			}
			else 
			{
				q = sValue;
			}
		}
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
			
		}
		return q;
	}
	
	/*'###############################################################
	'Function Name        : ipAdd
	'Function Description : Get the ipAddress
	'Input Parameters     : 
	'Output Parameters    : Returns ipAddress 
	'Written By           : Ajeet P
	'################################################################*/
	public String ipAdd() throws Exception 
	{
		String id = "NoValue";
		try 
		{
			java.net.InetAddress i = java.net.InetAddress.getLocalHost();
			id = i.getHostAddress();
		}
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
		}
		return id;
	}
	
	/*'###############################################################
	'Function Name        : FnSetDate
	'Function Description : To set data in Text box
	'Created By           : Sanjeev
	'Input Parameters     : sTextBoxXPath -> XPath of Text box 
	'					  : sSetValue     -> Value to be set in the Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSetDate(WebDriver driver, String sXPath, String sSetValue) throws Exception
	{
		System.out.println("*--FnSetDate >> "+sSetValue);
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				boolean flgFound;
				flgFound = false;
				sSetValue = getFormattedDate(sSetValue);

				// To check the existence of Text Box before setting the value
				try
				{
					System.out.println("Checking for Object Availability=>'"+sXPath+"'");
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				
				// Text box not found
				if(flgFound == false)
				{
					System.out.println("Date TextBox not found ->"+sXPath);
					//MasterCallingScript.test.log(Status.WARN, "Date TextBox not found ="+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
					//MasterCallingScript.test.log(Status.FAIL, "cf =FnSetDate - Fail - Object not found-> =" + sXPath);
				}
				
				// Text Box found so setting up the value
				else{
					System.out.println("TextBox found ->="+sXPath);
					//MasterCallingScript.test.log(Status.INFO, "Date TextBox found ="+sXPath);
			
					driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
					Thread.sleep(5000);
					driver.findElement(By.xpath(sXPath)).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					String sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
					int iCount = 0;
					while (sGetValue == null || !sSetValue.equalsIgnoreCase(sGetValue))
					{
						iCount++;
						driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
						Thread.sleep(5000);
						driver.findElement(By.xpath(sXPath)).sendKeys(Keys.ENTER);
						sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
						Thread.sleep(2000);
						if ((sGetValue == null || !sSetValue.equalsIgnoreCase(sGetValue))&& iCount == 3 )
						{
							System.out.println("Date "+sSetValue+" is not set to TextBox "+sXPath);
							//MasterCallingScript.test.log(Status.INFO,"Date "+sSetValue+" is not set to TextBox "+sXPath);
							break;
						}
					}
				}
			}
		}
		catch (Exception e) 
		{
			//MasterCallingScript.test.log(Status.FAIL, "FnSetText not working ->"+e.getMessage());
			System.out.println("FnSetDate not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : getFormattedDate
	'Function Description : Format date to mm-dd-yyyy
	'Created by           : Ajeet
	'Input Parameters     : strDate -> date
	'Output Parameters    : Formated date mm-dd-yyyy
	'################################################################*/
	public String getFormattedDate(String strDate) throws Exception 
	{
		String sDateFormat = "";
		if (strDate.substring(3,4).equalsIgnoreCase("/")||strDate.substring(2,3).equalsIgnoreCase("/"))
		{
			sDateFormat = "mm/dd/yyyy";
		}
		if (strDate.substring(3,4).equalsIgnoreCase("-")||strDate.substring(2,3).equalsIgnoreCase("-"))
		{
			sDateFormat = "mm-dd-yyyy";
		}
		
		Format formatter = new SimpleDateFormat(sDateFormat);
		Date date = (Date) formatter.parseObject(strDate);
		Format formatter1 = new SimpleDateFormat("mm-dd-yyyy");
		return formatter1.format(date);
	}
	
	
	/*'###############################################################
	'Function Name        : FnGetText
	'Function Description : To get data from Text Box
	'Created by           : Sumit
	'Input Parameters     : sTextBoxXPath -> XPath of Text box 
	'Output Parameters    : Returns the value of Text Box 
	'################################################################*/
	public String FnGetText(WebDriver driver, String sXPath) throws Exception
	{
		String sGetValue;  // used to return the value get from Text Box
		sGetValue = "NoValue";
		int iCount = 0; 
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;  
				flgFound = false;

				// To check the existence of Text Box before setting the value
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				
				// Text box not found
				if (flgFound == false)
				{
					System.out.println("cf =FnGetText - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				
				// Text Box found so getting the value
				else 
				{
					System.out.println("Text Box found ->="+sXPath);
					sGetValue=driver.findElement(By.xpath(sXPath)).getAttribute("value");

					while ( sGetValue == null )
					{
						iCount++;
						sGetValue=driver.findElement(By.xpath(sXPath)).getAttribute("value");
						Thread.sleep(1000);
						if ( sGetValue == null && iCount == 3 )
						{
							System.out.println("Value is not availble in "+sXPath);
							break;
						}
					}
					
					if (sGetValue == null)
					{
						sGetValue = "null";
					}
				System.out.println("Textbox value is ->"+sGetValue);
				}
			}
		}
		catch (Exception e) 
        {
			System.out.println("FnGetText not working ->");
			e.printStackTrace();
			BaseTest.eFlgFound="false";
			FnTestCaseStatusReport("Fail", "Common Function Exception occured ->\n"+erromsg(e));
        }

		return sGetValue;
	}
	
	/*'###############################################################
    'Function Name        : FnAlertMessage
    'Function Description : This function also checks if an alert window exists or not. 
    						If the alert window is present, it returns the alert message displayed.
	'Created by           : Sumit
    'Input Parameters     : Alert Message XPath 
    'Output Parameters    : The alert message displayed in the alert message box
    '################################################################*/
	public 	String FnAlertMessage(WebDriver driver)throws Exception 
	{
		System.out.println("*--FnAlertMessage");
		String sAlertMessage = "GLOBALFLAGFALSE" ;
		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{	
				if (isAlertPresent(driver)) 
				{
					Alert alert= driver.switchTo().alert();
					sAlertMessage=alert.getText();
					alert.accept();
				} 
				else 
				{
					System.out.println("\n" +"The alert message box not found : " ); 
					sAlertMessage = "No AlertMessage";
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("FnAlertMessage not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return (sAlertMessage);
	}
	
	/*'###############################################################
	'Function Name        : timeDiff
	'Function Description : Calculate the time difference by giving the start date and end date
	'Input Parameters     : sStartDate - Start Date
							sEndDate - End date
	'Output Parameters    : Returns the time difference in seconds 
	'Written By 		  : Ajeet P
	'################################################################*/
	public String timeDiff(String sStartDate, String sEndDate) throws Exception 
	{

		String q = "NoValue";
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");  
		Date d1 = null;
		Date d2 = null;
		try 
		{
			d1 = format.parse(sStartDate);
			d2 = format.parse(sEndDate);
			// Get seconds from each, and subtract.
			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000;         
			long diffMinutes = diff / (60 * 1000);         
			long diffHours = diff / (60 * 60 * 1000);                      
			System.out.println("Time in seconds: " + diffSeconds + " seconds."); 
			System.out.println("Time in minutes: " + diffMinutes + " minutes."); 
			System.out.println("Time in hours: " + diffHours + " hours."); 
			q = String.valueOf(diffSeconds);
		} 
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
		}
		return q;
	}

	
	/*'###############################################################
	'Function Name        : FnWindowClose
	'Function Description : To Close Window
	'Created by           : Sumit
	'Input Parameters     :  			
	'Output Parameters    :
	'################################################################*/
	public void FnWindowClose(WebDriver driver) throws Exception
	{
		System.out.println("*--FnWindowClose");
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;
				// To check the existence of Window
				try 
				{ 
					String parentWindow = driver.getWindowHandle();
					Set<String> handles =  driver.getWindowHandles();
					for(String windowHandle  : handles)
					{
						if(!windowHandle.equals(parentWindow))
						{
							driver.switchTo().window(windowHandle);
							Thread.sleep(2000);
							driver.close(); //closing child window
							driver.switchTo().window(parentWindow); //cntrl to parent window
							flgFound = true;
						}
					}

				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}

				// Window not found
				if (flgFound == false)
				{
					System.out.println("cf =FnWindowClose - Fail - Object not found ->");
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				else
				{
					System.out.println("Child Window successfully closed");
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnWindowClose not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnCompare
	'Function Description : To Compare the content of the two variables
	'Input Parameters     : Val1 	-> Value first 
	'					  : Val2 	-> Value second 
	'					  : sPassComment -> Pass comments
	'					  : sFailComment -> Fail comments  
	'Output Parameters    :  
	'Written By			  : Ajeet P
	'################################################################*/
	public void FnCompare(String Val1, String Val2, String sPassComment, String sFailComment, String sTestCaseName) throws Exception{
		System.out.println("*--FnCompare");
		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")){

				if (Val1 != null && Val2 != null && Val1.trim().equalsIgnoreCase(Val2.trim())){
					FnTestCaseStatusReport("Pass", sPassComment);
				}else{
					FnScreenCapture();
					FnTestCaseStatusReport("Fail", sFailComment);
				}
			}else{
				FnTestCaseStatusReport("Not Run", sTestCaseName);
			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			FnTestCaseStatusReport("Fail", "Common Function Exception occured ->\n"+erromsg(e));
		}

	}
	
	/*'###############################################################
	'Function Name        : FnCompareNull
	'Function Description : To Compare with premitive value
	'Input Parameters     : Val1 	-> Value first 
	'					  : Val2 	-> Value second 
	'					  : sPassComment -> Pass comments
	'					  : sFailComment -> Fail comments  
	'Output Parameters    :  
	'Written By			  : Ajeet P
	'################################################################*/
	public void FnCompareNull(String Val1, String Val2, String sPassComment, String sFailComment, String sTestCaseName) throws Exception{
		System.out.println("*--FnCompareNull");
		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")){

				if (Val1 == Val2){
					FnTestCaseStatusReport("Pass", sPassComment);
				}else{
					FnTestCaseStatusReport("Fail", sFailComment);
				}
			}else{
				FnTestCaseStatusReport("Not Run", sTestCaseName);
			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			FnTestCaseStatusReport("Fail", "Common Function Exception occured ->\n"+erromsg(e));
		}
	}
	
	/*'###############################################################
	'Function Name        : FnAppIdFromAlertMessage
	'Function Description : It returns the approval worklfow ID (with "TRANS") from alert message. If there is no approval workflow ID, then it returns the displayed alert message.
	'Created by           : Sumit
	'Input Parameters     : Alert Message XPath 
	'Output Parameters    : Either of the following :
							(a) Approval Workflow ID in the format of "TRANS111111111111111". (here the word 'TRANS' will be attached with Transaction ID)
							(b) Any other Alert Message found (other than the Approval Transaction ID)
							(c) Returns the message: "NOALERT" (if no alert box is found).
	'
	'################################################################*/	
	public String FnAppIdFromAlertMessage(WebDriver driver)throws Exception 
	{
		String sAlertMessage= "NOALERT";
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				try
				{
					// Switching to Alert
					Alert alert=driver.switchTo().alert();
				
					// Displaying alert message
					sAlertMessage = alert.getText();
					Thread.sleep(5000);
					System.out.println("Return for Alert Message " + sAlertMessage);
					// Accepting alert		
					alert.accept();
				}
				catch(Exception e)
				{
					System.out.println("No alert present");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("FnAppIdFromAlertMessage not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		} 
		return (sAlertMessage);
	}
	
	/*	'#############################################################################
    'Function Name        : FnVerifyButtonState
    'Function Description : To verify whether a Button is in disable or enable state
    'Created by           : Sumit
    'Input Parameters     : sObjectXPath 	-> XPath of the button on the page 
    'Output Parameters    : Returns the enable or disable state of the button
    '################################################################################*/
	public String FnVerifyButtonState(WebDriver driver, String sXPath) throws Exception
	{
		String sObjState = "Not Found";	
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Button
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}

				// Button not found
				if (flgFound == false)
				{
					System.out.println("cf =FnVerifyObjectState - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Button found so,checking the Button's state (enable or disable)
				else 
				{
					System.out.println("Object found ->="+sXPath);
					boolean ObjState= driver.findElement(By.xpath(sXPath)).isEnabled();
					if (ObjState)
					{
						System.out.println("The object is in enable state->"+sXPath);
						sObjState="False"; //Disable false
					}
					else
					{
						System.out.println("The object is in disable state->"+sXPath);
						sObjState="True"; //Disable true
					}
					Thread.sleep(2000);
				}
			} 
		}
		catch (Exception e) 
		{
			System.out.println("FnVerifyObjectState not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";

		}
		return sObjState;
	}
	
	/*'###############################################################
	'Function Name        : FnWindowCheck
	'Function Description : To Check if new Window is opened
	'Created by           : Sumit
	'Input Parameters     :  			
	'Output Parameters    :
	'################################################################*/
	public String FnWindowCheck(WebDriver driver) throws Exception
	{
		System.out.println("*--FnWindowCheck");
		boolean flgFound    = false;  		// used as a flag for object found
		String sGetText		= "NoValue";
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Window
				try 
				{ 
					String parentWindow = driver.getWindowHandle();
					Set<String> handles =  driver.getWindowHandles();
					for(String windowHandle  : handles)
					{
						if(!windowHandle.equals(parentWindow))
						{
							driver.switchTo().window(windowHandle);
							flgFound = true;
						}
					}

				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}

				// Window not found
				if (flgFound == false)
				{
					System.out.println("cf =FnWindowCheck - Fail - Object not found ->");
					FnScreenCapture();
					BaseTest.eFlgFound="false";
					sGetText= "false";
				}
				else
				{
					System.out.println("Child Window successfully opned");
					sGetText= "true";
					
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnWindowCheck not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sGetText;
	}
	
	/*'###############################################################
	'Function Name        : FnAppIdFromWebpage
	'Function Description : Capture Approval workflow ID / error message shown on Web Page.
	'Created by           : Sumit
	'Input Parameters     : Message XPath 
	'Output Parameters    : Output Parameters    : Either of the following :
    						(a) Approval Workflow ID in the format of "TRANS111111111111111". (here the word 'TRANS' will be attached with Transaction ID)
    						(b) If maker checker is OFF then it returns: "SAVEDYour changes have been saved successfully" (here the word SAVED will be attached to the message type)
    						(c) Returns: "ALERT" attached with any other message shown on browser [if a message other than (a) and (b) is found]
    						(d) Returns "NOMESSAGE" when there is no message displayed on the screen.
    						(e) Returns "PATHNOTFOUND" when path of the message is not found on the screen OR the relevant page is not open.
	'
	'################################################################*/	
	public String FnAppIdFromWebpage(WebDriver driver, String sXPath)throws Exception  
	{
		String sAlertMessage = "NoValue";
		String sAppTransId ;
		System.out.println("*--FnAppIdFromWebpage");	

		try {
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				//	if ( web.element(1,sXPath).exists()) 

				if (FnWindowCheck(driver).equals("true"))  
				{
					FnScreenCapture();
					sAlertMessage = driver.findElement(By.xpath(sXPath)).getText();
					
					// Checks if the value is not blank (i.e. it has some text dislayed )
					if (sAlertMessage != null) { 
						// Checks if the displayed message is an Approval Trans ID or not
						if (sAlertMessage.substring(0, 20).equals("Approval transaction")) {
							sAppTransId = sAlertMessage.substring(37, 52);
							sAppTransId = "TRANS" +sAppTransId ;
							System.out.println("Transaction ID is : " +sAppTransId);
							return (sAppTransId);
						}
						// Checks if the changes have been Saved. (Only in case when maker/checkr is OFF)
						if (sAlertMessage.substring(0, 22).equals("Your changes have been")) {
							sAlertMessage = sAlertMessage.replace("\n", " ");
							sAlertMessage = sAlertMessage.replace(",", "");
							sAlertMessage = "SAVED" +sAlertMessage ;
							System.out.println("The transaction is saved and the message displayed is: " +sAlertMessage);
							return (sAlertMessage);
						} else {	// For any other message that is displayed on the screen : shall display it here 
							sAlertMessage = sAlertMessage.replace("\n", " ");
							sAlertMessage = sAlertMessage.replace(",", "");
							sAlertMessage = "ALERT" +sAlertMessage ;
							System.out.println("The displayed alert message is : " +sAlertMessage);
							return (sAlertMessage);	
						}
						// Completes the checking of text message contents (if present).
					} else {// If no message is found on screen, it shall move here forth.
						sAlertMessage = "NOMESSAGE" ;
						System.out.println("No message displayed on the screen.");
						return (sAlertMessage);
					}
				} else {// If the path of message on the browser screen is not found, then it shall move here forth.
					sAlertMessage = "PATHNOTFOUND" ;
					System.out.println("Message Path not found.");
					return (sAlertMessage);
				}
				//return ("") ;
			}
		} catch (Exception e) {
			System.out.println("FnAppIdFromWebpage not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return (sAlertMessage);
	}
	
	/*'###############################################################
	'Function Name        : FnSelectBlankValue
	'Function Description : To select Blank value
	'Created by           : Sumit
	'Input Parameters     : sXPath -> XPath of element
	'Output Parameters    : 
	'################################################################*/
	public void FnSelectBlankValue(WebDriver driver, String sXPath) throws Exception
	{	
		System.out.println("*--FnSelectBlankValue");
		String sGetValue = "NoValue";
		int iCount = 0;

		try {
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (FnObjectExists(driver, sXPath).equals("true"))
				{
					System.out.println("Element found ="+sXPath);
					Select select = new Select(driver.findElement(By.xpath(sXPath)));
					select.selectByIndex(0);
					Thread.sleep(1000);
					driver.findElement(By.xpath(sXPath)).sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(1000);
					driver.findElement(By.xpath(sXPath)).sendKeys(Keys.ARROW_UP);
					Thread.sleep(1000);
					Thread.sleep(1000);
					
					
					WebElement sGetSelectedValue = select.getFirstSelectedOption();
					sGetValue = sGetSelectedValue.getText();

					iCount = 0;
					while (!"".equalsIgnoreCase(sGetValue))
					{
						iCount++;
						select.selectByIndex(0);
						Thread.sleep(1000);
						driver.findElement(By.xpath(sXPath)).sendKeys(Keys.ARROW_DOWN);
						Thread.sleep(1000);
						driver.findElement(By.xpath(sXPath)).sendKeys(Keys.ARROW_UP);
						Thread.sleep(1000);

						Thread.sleep(1000);
						WebElement sGetSelectedValue1 = select.getFirstSelectedOption();
						sGetValue = sGetSelectedValue1.getText();
						
						if ( iCount == 3 ){
							System.out.println("Not Set to Blank ="+sXPath);
							break;
						}
					}
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("FnSelectBlankValue not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
    'Function Name        : FnValidateSelectBoxValue
    'Function Description : To check if a value exists in a select Box.
    'Crated by            : Sumit
    'Input Parameters     : sXPath            -> XPath of Text box 
    '                     : sSelectValue      -> Value to be checked in the Text Box 
    'Output Parameters    : Returns True/false 
    '################################################################*/
	public String FnValidateSelectBoxValue(WebDriver driver, String sXPath, String sSelectValue) throws Exception
	{
		System.out.println("*--FnValidateSelectBoxValue >> "+sSelectValue);
		
		boolean flgFound, flgFoundSelectOption;
		flgFound = false;
		flgFoundSelectOption = false;
		String sReturnflg = "NoValue";
		int i=0;
		
		try
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSelectValue != null && !"".equalsIgnoreCase(sSelectValue))
				{
					// To check the existence of Drop down
					try 
					{ 
						//WebDriverWait wait=new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;
					} 

					catch (Exception e)
					{ 
						System.out.println("waitFor - Fail ->"+e.getMessage());
					}

					// Drop down not found
					if (flgFound == false)
					{
						System.out.println("cf =FnSelectValue - Fail - Object not found ->"+sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound="false";
					}

					// Checking Select option available 
					else 
					{
						Select option = new Select(driver.findElement(By.xpath(sXPath)));

						List<WebElement> allOptions = option.getOptions();
						for( i=0; i<allOptions.size(); i++) 
						{
							//System.out.println(allOptions.get(i).getText());
							if(allOptions.get(i).getText().equals(sSelectValue)) 
							{
								flgFoundSelectOption=true;
								break;
							}
						}
						if(flgFoundSelectOption) 
						{
							System.out.println("SelectBox value found -->"+sSelectValue);
							sReturnflg = "true";
						}
						else
						{
							System.out.println("SelectBox value not found-->"+sSelectValue);
							sReturnflg = "false";
						}
					}
				}
				else
				{
					System.out.println("Select value is blank or null");
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnValidateSelectBoxValue not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
			//e.printStackTrace();
		}
		return(sReturnflg);
	}
	
	/*'###############################################################
	'Function Name        : FnSetTextByKeyPressEnter
	'Function Description : To set data in Text box and press Enter
	'Created by           : Sumit
	'Input Parameters     : sTextBoxXPath -> XPath of Text box 
	'					  : sSetValue     -> Value to be set in the Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSetTextByKeyPressEnter(WebDriver driver, String sXPath, String sSetValue) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Text Box before setting the value
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
					//e.printStackTrace();
				} 

				// Text box not found
				if(flgFound == false)
				{
					System.out.println("TextBox not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				// Text Box found so setting up the value
				else
				{
					System.out.println("TextBox found ->=" + sXPath);
					driver.findElement(By.xpath(sXPath)).clear();
					driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
					Thread.sleep(1000);
					driver.findElement(By.xpath(sXPath)).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
				}					
			} 
		}
		catch (Exception e) 
		{
			System.out.println("FnSetTextByKeyPress not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnGetCurrentDate
	'Function Description : Get Current Date in MM-dd-yyyy format
	'Created by           : Ajeet
	'Input Parameters     : None
	'Output Parameters    : Current Date in MM-dd-yyyy format
	'################################################################*/
	public String FnGetCurrentDate() throws Exception 
	{
		String sCurrDate;

		Calendar currentDate = Calendar.getInstance();		
		SimpleDateFormat formatter =  new SimpleDateFormat("MM-dd-yyyy");		
		sCurrDate = formatter.format(currentDate.getTime());		
		System.out.println("Current Date is: "+ sCurrDate);
		return (sCurrDate);
	}
	
	/*'###############################################################
	'Function Name        : FnGetCurrentDay
	'Function Description : Get Current Day of system date
	'Created by           : Ajeet
	'Input Parameters     : None
	'Output Parameters    : Current Day
	'################################################################*/
	public String FnGetCurrentDay() throws Exception 
	{
		String sCurrDate;

		Calendar currentDate = Calendar.getInstance();		
		SimpleDateFormat formatter =  new SimpleDateFormat("MM-dd-yyyy");		
		sCurrDate = formatter.format(currentDate.getTime());		
		System.out.println("Current Date is: "+ sCurrDate);
		  
		Date MyDate = formatter.parse(sCurrDate);
		formatter.applyPattern("EEEE");
		String MyDate1 = formatter.format(MyDate);
		System.out.println("finalDay---------"+MyDate1);
		return (MyDate1);
	}
	
	/*'########################################################################
	'Function Name        : FnGetCellValueFromXLSX
	'Function Description : Get value from excelsheet (Integer of String only)
	'Written By           : Ajinkya Joshi
	'Input Parameters     : iRow -> Row number starts from '1'
	'					  : iCol -> Row number starts from '1'
	'					  : sSheetName -> Sheet name
	'				      : sfileName -> Full Name(path) of the excel sheet
	'Output Parameters    : Cell value (String or Integer)
	'############################################################################*/
	public String FnGetCellValueFromXLSX(XSSFWorkbook workBookGet,XSSFSheet sheetGet,XSSFCell cellGet) throws Exception 
	{
		System.out.println("*--FnGetCellValueFromXLSX");

		String sValue = "NoValue";
		
		try 
		{
					
			if (cellGet != null) 
			{
							
				switch (cellGet.getCellType()) 
				{
				case FORMULA:
					workBookGet.getCreationHelper().createFormulaEvaluator().evaluateAll();
					// Get the type of Formula
					switch (cellGet.getCachedFormulaResultType())
					{
					case STRING:
						sValue = cellGet.getStringCellValue();
						if (sValue == null || sValue == "" || sValue == " ")
						{
							sValue = "NoValue";
						}
						break;
					case NUMERIC:
						BigDecimal intFinal1 = new BigDecimal(cellGet.getNumericCellValue());
						sValue = intFinal1+"";
						CharSequence charSequence = ".";
						CharSequence charSequenceE = "E";
						if(sValue.contains(charSequenceE))
						{
							System.out.println("Not able to retrive numeric data. Please convert cell to TEXT");
							sValue = "NoValue";
						}
						if(sValue.contains(charSequence) && !sValue.contains(charSequenceE))
						{
							int a = sValue.indexOf(".");
							a++; 
							String sub = sValue.substring(a,sValue.length());
							int f = Integer.parseInt(sub);
							if(f == 0){
								sValue = sValue.substring(0,--a);
							}
						}
						break;
					default:
					}
					break;

				case NUMERIC:
					BigDecimal intFinal1 = new BigDecimal(cellGet.getNumericCellValue());
					sValue = intFinal1+"";
					CharSequence charSequence = ".";
					CharSequence charSequenceE = "E";
					if(sValue.contains(charSequenceE))
					{
						System.out.println("Not able to retrive numeric data. Please convert cell to TEXT");
						sValue = "NoValue";
					}
					if(sValue.contains(charSequence) && !sValue.contains(charSequenceE))
					{
						int a = sValue.indexOf(".");
						a++; 
						String sub = sValue.substring(a,sValue.length());
						int f = Integer.parseInt(sub);
						if(f == 0)
						{
							sValue = sValue.substring(0,--a);
						}
					}
					break;	

				case STRING:
					sValue = cellGet.getStringCellValue();
					if (sValue == null || sValue == "" || sValue == " ")
					{
						sValue = "NoValue";
					}
					break;		

				case BLANK:
					sValue = "";
					if (sValue == null || sValue == "" || sValue == " ")
					{
						sValue = "NoValue";
					}
					break;
					
				case BOOLEAN:
					sValue = cellGet.getStringCellValue();
					if (sValue == null || sValue == "" || sValue == " ")
					{
						sValue = "NoValue";
					}
					break;
				default:
				}
			} 
			else 
			{
				sValue = "NoValue";
			}
			workBookGet.close();
			Thread.sleep(5000);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			//getVariables().set("eFlgFound", "false");
		} 
		return sValue;
	}
	
	/*'########################################################################
	'Function Name        : FnGetCellValueFromXLS
	'Function Description : Get value from excelsheet (Integer of String only)
	'Written By           : Ajinkya Joshi
	'Input Parameters     : iRow -> Row number starts from '1'
	'					  : iCol -> Row number starts from '1'
	'					  : sSheetName -> Sheet name
	'				      : sfileName -> Full Name(path) of the excel sheet
	'Output Parameters    : Cell value (String or Integer)
	'############################################################################*/
	public String FnGetCellValueFromXLS(HSSFWorkbook workBookGet,HSSFSheet sheetGet,HSSFCell cellGet) throws Exception 
	{
		System.out.println("*--FnGetCellValueFromXLS");

		String sValue = "NoValue";
		
		try 
		{				
			if (cellGet != null) 
			{
							
				switch (cellGet.getCellType()) 
				{
				case FORMULA:
					workBookGet.getCreationHelper().createFormulaEvaluator().evaluateAll();
					// Get the type of Formula
					switch (cellGet.getCachedFormulaResultType())
					{
					case STRING:
						sValue = cellGet.getStringCellValue();
						if (sValue == null || sValue == "" || sValue == " ")
						{
							sValue = "NoValue";
						}
						break;
					case NUMERIC:
						BigDecimal intFinal1 = new BigDecimal(cellGet.getNumericCellValue());
						sValue = intFinal1+"";
						CharSequence charSequence = ".";
						CharSequence charSequenceE = "E";
						if(sValue.contains(charSequenceE))
						{
							System.out.println("Not able to retrive numeric data. Please convert cell to TEXT");
							sValue = "NoValue";
						}
						if(sValue.contains(charSequence) && !sValue.contains(charSequenceE))
						{
							int a = sValue.indexOf(".");
							a++; 
							String sub = sValue.substring(a,sValue.length());
							int f = Integer.parseInt(sub);
							if(f == 0)
							{
								sValue = sValue.substring(0,--a);
							}
						}
						break;
					default:
					}
					break;

				case NUMERIC:
					BigDecimal intFinal1 = new BigDecimal(cellGet.getNumericCellValue());
					sValue = intFinal1+"";
					CharSequence charSequence = ".";
					CharSequence charSequenceE = "E";
					if(sValue.contains(charSequenceE))
					{
						System.out.println("Not able to retrive numeric data. Please convert cell to TEXT");
						sValue = "NoValue";
					}
					if(sValue.contains(charSequence) && !sValue.contains(charSequenceE))
					{
						int a = sValue.indexOf(".");
						a++; 
						String sub = sValue.substring(a,sValue.length());
						int f = Integer.parseInt(sub);
						if(f == 0)
						{
							sValue = sValue.substring(0,--a);
						}
					}
					break;
					
				case STRING:
					sValue = cellGet.getStringCellValue();
					if (sValue == null || sValue == "" || sValue == " ")
					{
						sValue = "NoValue";
					}
					break;
					
				case BLANK:
					sValue = "";
					if (sValue == null || sValue == "" || sValue == " ")
					{
						sValue = "NoValue";
					}
					break;
					
				case BOOLEAN:
					sValue = cellGet.getStringCellValue();
					if (sValue == null || sValue == "" || sValue == " ")
					{
						sValue = "NoValue";
					}
					break;
				default:
				}
			} 
			else 
			{
				sValue = "NoValue";
			}
			workBookGet.close();
			Thread.sleep(5000);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			//getVariables().set("eFlgFound", "false");
		} 
		return sValue;
	}
	
	/*'########################################################################
	'Function Name        : FnGetCellValue
	'Function Description : Get value from excelsheet (Integer of String only)
	'Input Parameters     : iRow -> Row number starts from '1'
	'					  : iCol -> Row number starts from '1'
	'					  : sSheetName -> Sheet name
	'				      : sfileName -> Full Name(path) of the excel sheet
	'Output Parameters    : Cell value (String or Integer)
	'############################################################################*/
	public String FnGetCellValue(int iRow, int iCol, String sSheetName, String sfileName) throws Exception 
	{
		System.out.println("*--FnGetCellValue");
		iRow = iRow - 1;
		iCol = iCol - 1;
		String sValue = "NoValue";
		String sFileExtension = FilenameUtils.getExtension(sfileName);
		System.out.println("File extension is "+sFileExtension);
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				try 
				{
					 if(sFileExtension.equalsIgnoreCase("xls"))
					 {	 
						 InputStream s = new FileInputStream(sfileName);
						 HSSFWorkbook workBookGet = new HSSFWorkbook(s);
						 HSSFSheet    sheetGet    = workBookGet.getSheet(sSheetName);
						 HSSFCell cellGet = sheetGet.getRow(iRow).getCell(iCol); 
							
						 //sValue = FnGetCellValueFromXLS(workBookGet,sheetGet,cellGet);
						 if (cellGet != null) 
							{
											
								switch (cellGet.getCellType()) 
								{
								case FORMULA:
									workBookGet.getCreationHelper().createFormulaEvaluator().evaluateAll();
									// Get the type of Formula
									switch (cellGet.getCachedFormulaResultType())
									{
									case STRING:
										sValue = cellGet.getStringCellValue();
										if (sValue == null || sValue == "" || sValue == " ")
										{
											sValue = "NoValue";
										}
										break;
									case NUMERIC:
										BigDecimal intFinal1 = new BigDecimal(cellGet.getNumericCellValue());
										sValue = intFinal1+"";
										CharSequence charSequence = ".";
										CharSequence charSequenceE = "E";
										if(sValue.contains(charSequenceE))
										{
											System.out.println("Not able to retrive numeric data. Please convert cell to TEXT");
											sValue = "NoValue";
										}
										if(sValue.contains(charSequence) && !sValue.contains(charSequenceE))
										{
											int a = sValue.indexOf(".");
											a++; 
											String sub = sValue.substring(a,sValue.length());
											int f = Integer.parseInt(sub);
											if(f == 0)
											{
												sValue = sValue.substring(0,--a);
											}
										}
										break;
									default:
									}
									break;

								case NUMERIC:
									BigDecimal intFinal1 = new BigDecimal(cellGet.getNumericCellValue());
									sValue = intFinal1+"";
									CharSequence charSequence = ".";
									CharSequence charSequenceE = "E";
									if(sValue.contains(charSequenceE))
									{
										System.out.println("Not able to retrive numeric data. Please convert cell to TEXT");
										sValue = "NoValue";
									}
									if(sValue.contains(charSequence) && !sValue.contains(charSequenceE))
									{
										int a = sValue.indexOf(".");
										a++; 
										String sub = sValue.substring(a,sValue.length());
										int f = Integer.parseInt(sub);
										if(f == 0)
										{
											sValue = sValue.substring(0,--a);
										}
									}
									break;
									
								case STRING:
									sValue = cellGet.getStringCellValue();
									if (sValue == null || sValue == "" || sValue == " ")
									{
										sValue = "NoValue";
									}
									break;
									
								case BLANK:
									sValue = "";
									if (sValue == null || sValue == "" || sValue == " ")
									{
										sValue = "NoValue";
									}
									break;
									
								case BOOLEAN:
									sValue = cellGet.getStringCellValue();
									if (sValue == null || sValue == "" || sValue == " ")
									{
										sValue = "NoValue";
									}
									break;
								default:
								}
							} 
							else 
							{
								sValue = "NoValue";
							}
							workBookGet.close();
							s.close();
					 }
				     else if(sFileExtension.equalsIgnoreCase("xlsx"))
				     {
				    	 InputStream s = new FileInputStream(sfileName);
				    	 XSSFWorkbook workBookGet = new XSSFWorkbook(s);
				    	 XSSFSheet    sheetGet    = workBookGet.getSheet(sSheetName);
				    	 XSSFCell cellGet = sheetGet.getRow(iRow).getCell(iCol); 
							
				    	// sValue = FnGetCellValueFromXLSX(workBookGet,sheetGet,cellGet);						
				    	 if (cellGet != null) 
							{
											
								switch (cellGet.getCellType()) 
								{
								case FORMULA:
									workBookGet.getCreationHelper().createFormulaEvaluator().evaluateAll();
									// Get the type of Formula
									switch (cellGet.getCachedFormulaResultType())
									{
									case STRING:
										sValue = cellGet.getStringCellValue();
										if (sValue == null || sValue == "" || sValue == " ")
										{
											sValue = "NoValue";
										}
										break;
									case NUMERIC:
										BigDecimal intFinal1 = new BigDecimal(cellGet.getNumericCellValue());
										sValue = intFinal1+"";
										CharSequence charSequence = ".";
										CharSequence charSequenceE = "E";
										if(sValue.contains(charSequenceE))
										{
											System.out.println("Not able to retrive numeric data. Please convert cell to TEXT");
											sValue = "NoValue";
										}
										if(sValue.contains(charSequence) && !sValue.contains(charSequenceE))
										{
											int a = sValue.indexOf(".");
											a++; 
											String sub = sValue.substring(a,sValue.length());
											int f = Integer.parseInt(sub);
											if(f == 0)
											{
												sValue = sValue.substring(0,--a);
											}
										}
										break;
									default:
									}
									break;

								case NUMERIC:
									BigDecimal intFinal1 = new BigDecimal(cellGet.getNumericCellValue());
									sValue = intFinal1+"";
									CharSequence charSequence = ".";
									CharSequence charSequenceE = "E";
									if(sValue.contains(charSequenceE))
									{
										System.out.println("Not able to retrive numeric data. Please convert cell to TEXT");
										sValue = "NoValue";
									}
									if(sValue.contains(charSequence) && !sValue.contains(charSequenceE))
									{
										int a = sValue.indexOf(".");
										a++; 
										String sub = sValue.substring(a,sValue.length());
										int f = Integer.parseInt(sub);
										if(f == 0)
										{
											sValue = sValue.substring(0,--a);
										}
									}
									break;
									
								case STRING:
									sValue = cellGet.getStringCellValue();
									if (sValue == null || sValue == "" || sValue == " ")
									{
										sValue = "NoValue";
									}
									break;
									
								case BLANK:
									sValue = "";
									if (sValue == null || sValue == "" || sValue == " ")
									{
										sValue = "NoValue";
									}
									break;
									
								case BOOLEAN:
									sValue = cellGet.getStringCellValue();
									if (sValue == null || sValue == "" || sValue == " ")
									{
										sValue = "NoValue";
									}
									break;
								default:
								}
							} 
							else 
							{
								sValue = "NoValue";
							}
							workBookGet.close();
				    	 s.close();
				     }
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					//getVariables().set("eFlgFound", "false");
				} 
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
			e.printStackTrace();
			//getVariables().set("eFlgFound", "false");
			FnTestCaseStatusReport("Fail", "Common Function Exception occured ->\n"+erromsg(e));
		}
		
		return sValue;
	}
	
	/*'######################################################################
    'Function Name        : FnWriteCellValue
    'Function Description : Write value to excelsheet (String only)
    'Written By           : Sanjeev Acharya
    'Input Parameters     : iRow -> Row number starts from '1'
    '                     : iCol -> Row number starts from '1'
    '                     : sValue -> Value to be write in excel cell
    '                     : sfileName -> Full Name(path) of the excel sheet
    'Output Parameters    : -
    '########################################################################*/
	public void FnWriteCellValue(int iRow, int iCol, String sValue, String sSheetName, String sfileName) throws InterruptedException
    {
		System.out.println("*--FnWriteCellValue");
        iRow = iRow - 1;
        iCol = iCol - 1;
		String sFileExtension = FilenameUtils.getExtension(sfileName);
		System.out.println("File extension is "+sFileExtension);
        if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
        {
            try
            {
            	if(sFileExtension.equalsIgnoreCase("xls"))
            	{	
            		InputStream s = new FileInputStream(sfileName);
	                HSSFWorkbook workBook = new HSSFWorkbook(s);
	                HSSFSheet sheet = workBook.getSheet(sSheetName);
	                //FnWriteCellValueToXLS(workBook,sheet,iRow,iCol,sValue,sfileName);
	                Row r = sheet.getRow(iRow);
	            	if (r == null) 
	            	{
	            		r = sheet.createRow(iRow);
	            	}
	                    
	            	Cell c = r.getCell(iCol);
	            	if (c == null) 
	            	{
	            		c = r.createCell(iCol, CellType.STRING);
	            	}
	            	c.setCellValue(sValue);
	            	OutputStream out = new FileOutputStream(sfileName);
	            	workBook.write(out);
	            	out.flush();
	            	out.close();
	            	workBook.close();
	                s.close();
            	}
            	else if(sFileExtension.equalsIgnoreCase("xlsx"))
            	{	
            		InputStream s = new FileInputStream(sfileName);
	                XSSFWorkbook workBook = new XSSFWorkbook(s);
	                XSSFSheet sheet = workBook.getSheet(sSheetName);
	                //FnWriteCellValueToXLSX(workBook,sheet,iRow,iCol,sValue,sfileName);
	                Row r = sheet.getRow(iRow);
	            	if (r == null) 
	            	{
	            		r = sheet.createRow(iRow);
	            	}
	                    
	            	Cell c = r.getCell(iCol);
	            	if (c == null) 
	            	{
	            		c = r.createCell(iCol, CellType.STRING);
	            	}
	            	c.setCellValue(sValue);
	            	OutputStream out = new FileOutputStream(sfileName);
	            	workBook.write(out);
	            	out.flush();
	            	out.close();
	            	workBook.close();
	                s.close();
            	}
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
	
	/*'######################################################################
    'Function Name        : FnWriteCellValueToXLSX
    'Function Description : Write value to excelsheet (String only)
    'Written By           : Ajinkya Joshi
    'Input Parameters     : iRow -> Row number starts from '1'
    '                     : iCol -> Row number starts from '1'
    '                     : sValue -> Value to be write in excel cell
    '                     : sfileName -> Full Name(path) of the excel sheet
    'Output Parameters    : -
    '########################################################################*/
	public void FnWriteCellValueToXLSX(XSSFWorkbook workBook, XSSFSheet sheet,int iRow, int iCol,String sValue, String sfileName) throws IOException, InterruptedException
    {
        System.out.println("*--FnWriteCellValueToXLSX");
        
        try
        {
        	Row r = sheet.getRow(iRow);
        	if (r == null) 
        	{
        		r = sheet.createRow(iRow);
        	}
                
        	Cell c = r.getCell(iCol);
        	if (c == null) 
        	{
        		c = r.createCell(iCol, CellType.STRING);
        	}
        	c.setCellValue(sValue);
        	OutputStream out = new FileOutputStream(sfileName);
        	workBook.write(out);
        	out.flush();
        	out.close();
        	workBook.close();
        } 
        catch (FileNotFoundException e) 
        {
        	e.printStackTrace();
        }
    }
	
	/*'######################################################################
    'Function Name        : FnWriteCellValueToXLS
    'Function Description : Write value to excelsheet (String only)
    'Written By           : Ajinkya Joshi
    'Input Parameters     : iRow -> Row number starts from '1'
    '                     : iCol -> Row number starts from '1'
    '                     : sValue -> Value to be write in excel cell
    '                     : sfileName -> Full Name(path) of the excel sheet
    'Output Parameters    : -
    '########################################################################*/
	public void FnWriteCellValueToXLS(HSSFWorkbook workBook, HSSFSheet sheet,int iRow, int iCol,String sValue, String sfileName) throws IOException, InterruptedException
    {
        System.out.println("*--FnWriteCellValueToXLS");
        
        try
        {
            Row r = sheet.getRow(iRow);
            if (r == null) 
            {
                r = sheet.createRow(iRow);
            }
            
            Cell c = r.getCell(iCol);
            if (c == null) 
            {
                c = r.createCell(iCol, CellType.STRING);
            }
            c.setCellValue(sValue);
            OutputStream out = new FileOutputStream(sfileName);
            workBook.write(out);
            out.flush();
            out.close();
            workBook.close();
        } 
        catch (FileNotFoundException e) 
        {
        	e.printStackTrace();
        }
    }
	
    /*'###############################################################
	'Function Name        : FnValueStartsWithInWebTable
	'Function Description : To verify if the search result in a cell starts with any digit /character.
	'Created by           : Sumit
	'Input Parameters     : sWebTableXPath		-> XPath of Web table
						  : sStartsWith			-> First digit(s) to be searched for comparison 
						  : sColumn				-> Column in which to search
	'Output Parameters    : Any one of the following:
							(1) NoValue (if global flag found false) ; OR
							(2) true (if value found) ; OR
							(3) false (if value not found) 
	'################################################################*/
	public String FnValueStartsWithInWebTable(WebDriver driver, String sXPath,String sStartsWith, int sColumn ) throws Exception
	{	
		System.out.println("*--FnValueStartsWithInWebTable");
		String sOutputResult = "NoValue";
		String sTableExists = "false" ;
		int sRowCount = 0;
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				sTableExists =  FnObjectExists (driver, sXPath);
				if(sTableExists.equalsIgnoreCase("true")) 
				{
					System.out.println("-----Web Table found");
					List<WebElement>  rows = driver.findElements(By.xpath(sXPath+"/tbody/tr/td[1]"));
					sRowCount = rows.size();
					System.out.println("No of rows are : " +sRowCount); 
					int iCharLength = sStartsWith.length() ;
					for(int i=1;i<=sRowCount;i++) // I count starts from 1 as there are Nested tables in Bill segment table otherwise I count will be 2
					{
						String sCompareText = driver.findElement(By.xpath(sXPath+"/tbody/tr["+i+"]/td["+sColumn+"]")).getText().replace("‑", "-");
						sCompareText = sCompareText.trim();
						System.out.println("Text to compare from Webtable: "+sCompareText);

						if (iCharLength == 1)
						{
							if((sCompareText.toLowerCase().startsWith(sStartsWith)) || (sCompareText.toUpperCase().startsWith(sStartsWith)))
							{	
								sOutputResult = "true";
								System.out.println("Search result is found starting with first digit " +sStartsWith);
								System.out.println("Result is -> "+sOutputResult);
							}
							else
							{
								if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
								{
									sOutputResult = "false";
									System.out.println("Search result is not found starting with first digit " +sStartsWith);
									System.out.println("Result is -> "+sOutputResult);
									break;
								}
							}
						}
						if (iCharLength > 1)
						{
							if(sCompareText.startsWith(sStartsWith)) 
							{	
								sOutputResult = "true";
								System.out.println("Search result is found starting with " +sStartsWith);
								System.out.println("Result is -> "+sOutputResult);
							}
							else
							{
								if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
								{
									sOutputResult = "false";
									System.out.println("Search result is not found starting with " +sStartsWith);
									System.out.println("Result is -> "+sOutputResult);
									break;
								}
							}
						}
					}
				}	
			}	
		}
		catch (Exception e) 
		{
			System.out.println("FnValueStartsWithInWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return sOutputResult;			
	}
	
	/*'###############################################################
	'Function Name        : FnValueStartsAndContainsInWebTable
	'Function Description : To verify if the search result in a cell starts with any digit /character and also contains another character in it.
	'Created by           : Sumit
	'Input Parameters     : sWebTableXPath	-> XPath of Web table
						  : sStartsWith		-> First digit to be searched for comparison 
						  : sContains		-> Secnd digit that should be present in the search result
						  : sColumn -> Column in which to search
	'Output Parameters    : Any one of the following:
							(1) NoValue (if global flag found false) ; OR
							(2) true (if value found) ; OR
							(3) false (if value not found) 
	'################################################################*/	
	public String FnValueStartsAndContainsInWebTable(WebDriver driver, String sXPath,String sStartsWith, String sContains, int sColumn ) throws Exception
	{
		System.out.println("*--FnValueStartsAndContainsInWebTable");
		String sOutputResult = "NoValue";
		String sTableExists = "false" ;	
		int sRowCount = 0;
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				sTableExists =  FnObjectExists (driver, sXPath);
				if(sTableExists.equalsIgnoreCase("true")) 
				{
					System.out.println("-----Web Table found");
					List <WebElement> rows = driver.findElements(By.xpath(sXPath+"/tbody/tr/td[1]"));
					sRowCount = rows.size();
					System.out.println("No of rows are : " +sRowCount); 
	
					for(int i=1;i<=sRowCount;i++) // I count starts from 1 as there are Nested tables in Bill segment table otherwise I count will be 2
					{
						String sCompareText = driver.findElement(By.xpath(sXPath+"/tbody/tr["+i+"]/td["+sColumn+"]")).getText().replace("‑", "-");
						sCompareText = sCompareText.trim();
						System.out.println("Text to compare from Webtable: "+sCompareText);
	
						if(   ((sCompareText.toLowerCase().startsWith(sStartsWith)) || (sCompareText.toUpperCase().startsWith(sStartsWith)))  &&   ((sCompareText.substring(1).toLowerCase().contains(sContains)) || (sCompareText.substring(1).toUpperCase().contains(sContains)))   )
						{	
							sOutputResult = "true";
							System.out.println("Result has 'First digit' "+sStartsWith+" and also 'Contains' "+sContains+" in the search result: " +sCompareText);
							System.out.println("Result is -> "+sOutputResult);
						}
						else
						{
							if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
							{
								sOutputResult = "false";
								System.out.println("Result is not having 'First digit' as "+sStartsWith+" or does not 'Contain' "+sContains+" in the search result: " +sCompareText);
								System.out.println("Result is -> "+sOutputResult);
								break;
							}
						}
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnValueStartsAndContainsInWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return sOutputResult;
	}
	
	/*'############################################################### 
    'Function Name        : FnValueContainsInWebTable
    'Created by           : Sumit	  
    '################################################################*/	
	public String FnValueContainsInWebTable(WebDriver driver, String sXPath, String sContains, int sColumn ) throws Exception
	{
		System.out.println("*--FnValueContainsInWebTable");
		String sOutputResult = "NoValue";
		String sTableExists = "false" ;
		int sRowCount = 0;
		
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				sTableExists =  FnObjectExists (driver, sXPath);
				if(sTableExists.equalsIgnoreCase("true")) 
				{
					System.out.println("-----Web Table found");
					List<WebElement>  rows = driver.findElements(By.xpath(sXPath+"/tbody/tr/td[1]"));
					sRowCount = rows.size();
					System.out.println("No of rows are : " +sRowCount); 
					
					for(int i=1;i<=sRowCount;i++) // I count starts from 1 as there are Nested tables in Bill segment table otherwise I count will be 2
					{
						String sCompareText = driver.findElement(By.xpath(sXPath+"/tbody/tr["+i+"]/td["+sColumn+"]")).getText().replace("‑", "-");
						sCompareText = sCompareText.trim();
						
						if(   ((sCompareText.toLowerCase().contains(sContains)) || (sCompareText.toUpperCase().contains(sContains)))   )
						{	
							sOutputResult = "true";
							System.out.println("Result  'Contains' "+sContains+" in the search result: " +sCompareText);
							System.out.println("Result is -> "+sOutputResult);
						}
						else
						{
							if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
							{
								sOutputResult = "false";
								System.out.println("Result  'Contain' "+sContains+" in the search result: " +sCompareText);
								System.out.println("Result is -> "+sOutputResult);
								break;
							}
						}
					}
				}
				else
				{
					BaseTest.eFlgFound = "true";
					sOutputResult = "false";
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("FnValueContainsInWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return sOutputResult;
	}
	
	/*'###############################################################
	'Function Name        : FnSetBlankDate
	'Function Description : To set date in Text box
	'Created by           : Sanjeev
	'Input Parameters     : sTextBoxXPath -> XPath of Date Text box 
	'					  : sSetValue     -> Value to be set in the Date Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSetBlankDate(WebDriver driver, String sXPath, String sSetValue) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				boolean flgFound;
				flgFound = false;

				// To check the existence of Text Box before setting the value
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e) 
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				
				// Text box not found
				if(flgFound == false)
				{
					System.out.println("Date TextBox not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				
				// Text Box found so setting up the value
				else
				{
					System.out.println("TextBox found ->="+sXPath);
					driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
					Thread.sleep(1000);
					driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
					Thread.sleep(1000);
				}
			}
		}
		catch (Exception e) 
		{
			//MasterCallingScript.test.log(Status.FAIL, "FnSetText not working ->"+e.getMessage());
			System.out.println("FnSetText not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'#######################################################################################################################################
	'Function Name        : FnGetValueForAllRowsFromWebTable
	'Function Description : To get value from all rows of web table
	'Created by           : Sumit	 
	'Input Parameters     : sWebTableXPath 	-> 	XPath of Web table
	'Input Parameter      : sSearchText    	->	Text to be searched in all the rows of webtable
	'Input Parameter      : iColToSearch  	-> 	Column number , from where text needs to be searched
	'Input Parameter      : iColToGet     	-> 	Column number , from where text needs to be returned
	'Output Parameters    : Returns the value from Web table
	'#########################################################################################################################################*/
	public String FnGetValueForAllRowsFromWebTable(WebDriver driver, String sXPath, String sSearchText, int iColToSearch, int iColToGet) throws Exception{
		System.out.println("*--FnGetValueForAllRowsFromWebTable");
		int sRowCount 		= 0;
		boolean flgFound    = false;  		// used as a flag for object found
		boolean flgTextFound= false;; 		// used as a flag for text found			
		String sCompareText = "";
		String sGetText		= "NoValue";				
	
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Web Table before trying to retrieve the column values
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				} 
				// Web table not found
				if(flgFound == false)
				{
					System.out.println("Web table not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				
				// Web Table found so getting the value from required column
				else
				{
					System.out.println("Web Table found ->"+sXPath);
					List<WebElement>  rows = driver.findElements(By.xpath(sXPath+"/tbody/tr/td[1]"));
					sRowCount = rows.size();
					System.out.println("No of rows are : " +sRowCount); 
					for(int j=1;j<=sRowCount;j++)	// J count starts from 1 as there are Nested tables in Bill segment table otherwise J count will be 2
					{
						
						sCompareText = driver.findElement(By.xpath(sXPath+"/tbody/tr["+j+"]/td["+iColToSearch+"]")).getText().replace("‑", "-");
						System.out.println("Compare Text "+sCompareText);
						
						if(sSearchText.trim().equalsIgnoreCase(sCompareText.trim()))
						{
							flgTextFound = true;
							
							sGetText=driver.findElement(By.xpath(sXPath+"/tbody/tr["+j+"]/td["+iColToGet+"]")).getText().replace("‑", "-");
							sGetText = sGetText.trim();
						}
						else
						{
							flgTextFound = false;
							break;
						}
					}
					if (flgTextFound == true)
					{
						System.out.println("Text " + sSearchText + " to search in Web Table found"+sXPath);
					}
					else 
					{
						System.out.println("Text " + sSearchText + " to search in Web Table not found"+sXPath);
						sGetText = "NOTFOUND";
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnGetValueForAllRowsFromWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sGetText;
	}
	
	/*'###############################################################
	'Function Name        : FnSetTextPrecision
	'Written by  		  : Sanjeev
	'Function Description : To set value in Text box and then check its value
	'Input Parameters     : sTextBoxXPath 	-> XPath of Text box 
	'					  : sSetValue     	-> Input Value to set in the Text Box 
	'Output Parameters    : None 
	'################################################################*/
	public void FnSetTextPrecision(WebDriver driver, String sXPath, String sSetValue) throws Exception 
	{
		System.out.println("*--FnGetTextFromElement >> " + sSetValue);
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				String sGetValue = "";
				String sAfterDecimalVal = "";
				String sObjFound;
				int iSetValLength;
				sObjFound = FnObjectExists(driver, sXPath);
				if (sObjFound.equalsIgnoreCase("true")) // IF text box is found then put value in it.
				{
					if (sSetValue.contains(".")) // Check if the input value has decimal in it or not
					{
						System.out.println("The input value has decimal in it.");
					}
					else // If there is no decimal then add default precision value of 18 places - after the decimal
					{
						sSetValue = sSetValue + ".000000000000000000";
						System.out.println("The new value to set with decimal is :" + sSetValue);
					}
					
					// Steps to set value starts from here:
					driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
					Thread.sleep(1000);
					driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
					Thread.sleep(2000);
					// driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
					sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
					System.out.println("The value displayed in the text box is :" + sGetValue);
					// Steps to compare the two values starts from here:
					String[] sListValues;
					sListValues = sSetValue.split("\\.");
					sAfterDecimalVal = sListValues[1];
					System.out.println("The After Decimal value is :" + sAfterDecimalVal);
					iSetValLength = sAfterDecimalVal.length();
					System.out.println("The length of the input value is :" + iSetValLength);
					for (int iCount = 1; iCount < 19 - iSetValLength; iCount++) 
					{
						sSetValue = sSetValue + "0";
					}
					
					if (sSetValue.equalsIgnoreCase(sGetValue)) 
					{
						System.out.println("Pass : Both input and output values are same");
					} 
					else
					{
						System.out.println("Fail : Both input and output values are not same");
						BaseTest.eFlgFound = "false";
						System.out.println("Fail : CF -> FnSetTextPrecision - Fail - Precision Input and Output values do not match.");
					}
				}
			}
		}
		catch (Exception e) 
		{
			// MasterCallingScript.test.log(Status.FAIL, "FnSetText not working
			// ->"+e.getMessage());
			System.out.println("INFO:Common Function Exception occured ->" + e.getLocalizedMessage());
			BaseTest.eFlgFound = "false";
			// System.out.println("Fail", "Common Function Exception occured
			// ->\n"+e.erromsg(e));
		}
	}
		
	/*'##################################################################################################
	'Function Name        : FnSetTextWithTwoDecimalPlaces	
	'Function Description : To set value in Text box in x.00 format and then check its value
	'Written By           : Sanjeev
	'Input Parameters     : sTextBoxXPath 	-> XPath of Text box 
	'					  : sSetValue     	-> Input Value to be set in the Text Box 
	'Output Parameters    : None 
	'###################################################################################################*/
	public void FnSetTextWithTwoDecimalPlaces(WebDriver driver, String sXPath, String sSetValue) throws Exception 
	{
		System.out.println("*--FnSetTextWithTwoDecimalPlaces >> " + sSetValue);
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				String sGetValue = "";
				String sAfterDecimalVal = "";
				String sObjFound;
				int iSetValLength;
				sObjFound = FnObjectExists(driver, sXPath);
				if (sObjFound.equalsIgnoreCase("true")) // IF text box is found then put value in it.
				{
					if (sSetValue.contains(".")) // Check if the input value has decimal in it or not
					{
						System.out.println("The input value has decimal in it : " + sSetValue);
					} 
					else // If there is no decimal then add default precision value of 18 places - after the decimal
					{
						System.out.println("The input value doesn't have decimal in it : " + sSetValue);
						sSetValue = sSetValue + ".00";
						System.out.println("The newly created input value with decimal is :" + sSetValue);
					}
					// Steps to set value starts from here:
					driver.findElement(By.xpath(sXPath)).clear();
					driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
					Thread.sleep(1000);
					driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
					Thread.sleep(2000);
					// driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
					sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
					System.out.println("The value displayed in the text box is :" + sGetValue);
					// Steps to compare the two values starts from here:
					String[] sListValues;
					sListValues = sSetValue.split("\\.");
					sAfterDecimalVal = sListValues[1];
					System.out.println("The After Decimal value is :" + sAfterDecimalVal);
					iSetValLength = sAfterDecimalVal.length();
					System.out.println("The length of the input value is :" + iSetValLength);
					if (iSetValLength < 3) 
					{
						for (int iCount = 1; iCount < 3 - iSetValLength; iCount++) 
						{
							sSetValue = sSetValue + "0";
						}
						System.out.println("The final input value with Precison x.xx is : " + sSetValue);
						System.out.println("The value present in the text box is : " + sGetValue);
					} 
					else 
					{
						System.out.println("The final input value with Precison is : " + sSetValue);
					}

					// Retrieving the value from the Text Box
					sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
					System.out.println("The value displayed in the text box is :" + sGetValue);

					if (sGetValue.contains(",")) 
					{
						sGetValue = sGetValue.replace(",", "");
					}

					// Steps to compare the two values starts from here:
					if (sSetValue.equalsIgnoreCase(sGetValue)) 
					{
						System.out.println("Pass : Both input and output values are same");
					}
					else 
					{
						System.out.println("Fail : Both input and output values are not same");
						BaseTest.eFlgFound = "false";
						System.out.println("Fail : CF -> CF -> FnSetTextWithTwoDecimalPlaces - Fail - Precision Input and Output values do not match.");
					}
				}
			}
		}
		catch (Exception e)
		{
			// MasterCallingScript.test.log(Status.FAIL, "FnSetText not working
			// ->"+e.getMessage());
			System.out.println("INFO:Common Function Exception occured ->" + e.getLocalizedMessage());
			BaseTest.eFlgFound = "false";
			// System.out.println("Fail", "Common Function Exception occured
			// ->\n"+e.erromsg(e));
		}
	}
	
	/*'###############################################################
	'Function Name        : FnSwapValue
	'Written by  		  : Sumit
	'Function Description : To swap the sign of an input value Ex: $1,000.00 will be returned as $-1,000.00 
	'Input Parameters     : sSetValue -> The input value which needs to be swapped
	'Output Parameters    : Swapped value.
	'################################################################*/
	public String FnSwapValue (String sSetValue) throws Exception
	{
		System.out.println("*--FnSwapValue >> "+sSetValue);
		String sSwapValue = "NOVALUE" ;
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSetValue.substring(1).startsWith("-"))
				{
					String sPos1 = sSetValue.substring(0,1) ;
					String sPos2 = sSetValue.substring(2) ;
					sSwapValue = sPos1 + sPos2 ; // Return Positive value
				}
				if (!sSetValue.substring(1).startsWith("-"))
				{
					String sNeg1 = sSetValue.substring(0,1) ;
					String sNeg2 = sSetValue.substring(1) ;
					sSwapValue = sNeg1 + "-" + sNeg2 ; // Return Negative Value				
				}							
			}
			System.out.println("The input value is : "+sSetValue + " and the swapped value is : "+sSwapValue);
		} 
		catch (Exception e) 
		{
			System.out.println("FnSwapValue not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return sSwapValue;	
	}	
		
	/*'###############################################################
	'Function Name        : FnCheckValueFromWebTable
	'Created by			  : Sumit
	'Function Description : Check the given value is available in web table
							If object not found it does not set eFlgFound to false 
	'Input Parameters     : sQuery 			-> SQL statement
	'					  : sDbName 		-> Database name
	'					  : sDbUserName 	-> Database user name
	'					  : sDbPassword 	-> Database Password
	'					  : sDbMachineIP 	-> Database machine IP address
	'					  : sDbPort 		-> Database Porn Number
	'Output Parameters    : Returns the value for given SQL statement
	'################################################################*/
	public String FnCheckValueFromWebTable(WebDriver driver, String sXPath, String sSearchText, int iColToSearch, int iColToGet) throws Exception
	{
		System.out.println("*--FnCheckValueFromWebTable");
		String sGetText = "NoValue";
		int sRowCount = 0;
		String sCompareText= "";
		boolean flgTextFound = false;
		boolean flgFound= false;
		
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Web Table before trying to retrieve the column values
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				} 
				// Web table not found
				if(flgFound == false)
				{
					System.out.println("Web table not found ->"+sXPath);
					FnScreenCapture();
				}
				
				// Web Table found so getting the value from required column
				else
				{
					System.out.println("Web Table found ->"+sXPath);
					List<WebElement>  rows = driver.findElements(By.xpath(sXPath+"/tbody/tr/td[1]"));
					sRowCount = rows.size();
					System.out.println("No of rows are : " +sRowCount); 
					for(int j=1;j<=sRowCount;j++)	// J count starts from 1 as there are Nested tables in Bill segment table otherwise J count will be 2
					{
						sCompareText = driver.findElement(By.xpath(sXPath+"/tbody/tr["+j+"]/td["+iColToSearch+"]")).getText().replace("‑‘", "-");
						System.out.println("Compare Text "+sCompareText);
						
						if(sSearchText.trim().equalsIgnoreCase(sCompareText.trim()))
						{
							flgTextFound = true;
							sGetText=driver.findElement(By.xpath(sXPath+"/tbody/tr["+j+"]/td["+iColToGet+"]")).getText().replace("‑", "-");
							sGetText = sGetText.trim();
							break;
						}
					}
					if (flgTextFound == true)
					{
						System.out.println("Text " + sSearchText + " to search in Web Table found"+sXPath);
					}
					else 
					{
						System.out.println("Text " + sSearchText + " to search in Web Table not found"+sXPath);
						sGetText = "NOTFOUND";
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnCheckValueFromWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sGetText;
	}
		
	
	/*'###############################################################
	'Function Name        : FnConfirmDialogButtonClick
	'Function Description : To click on OK button on Confirm message
	'Written by  		  : Sumit
	'################################################################*/
	public void FnConfirmDialogButtonClick(WebDriver driver) throws Exception
	{
		System.out.println("*--FnConfirmDialogButtonClick");
		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of element
				if (isAlertPresent(driver)) 
				{
					flgFound = true;
				}
				// Element not found
				if (flgFound == false)
				{
					System.out.println("Confirm box Button not found ");
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Element found so clicking on the element
				else 
				{
					System.out.println("Confirm box Button found ");
					Alert alert=driver.switchTo().alert();
					alert.accept();
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("FnConfirmDialogButtonClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnConfirmDialogCloseButtonClick
	'Function Description : To click on Cancel button on Confirm message
	'Written by  		  : Sumit
	'################################################################*/
	public void FnConfirmDialogCancelButtonClick(WebDriver driver) throws Exception
	{
		System.out.println("*--FnConfirmDialogCancelButtonClick");
		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of element
				if (isAlertPresent(driver)) 
				{
					flgFound = true;
				}
				// Element not found
				if (flgFound == false)
				{
					System.out.println("Confirm box Button not found ");
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Element found so clicking on the element
				else 
				{
					System.out.println("Confirm box Button found ");
					Alert alert=driver.switchTo().alert();
					alert.dismiss();
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("FnConfirmDialogCloseButtonClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
	'Function Name        : FnSetNumber
	'Function Description : To set data in Text box
	'Written By           : Sanjeev
	'Input Parameters     : sTextBoxXPath -> XPath of Text box 
	'					  : sSetValue     -> Value to be set in the Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSetNumber(WebDriver driver, String sXPath, String sSetValue) throws Exception
	{
		System.out.println("*--FnSetNumber >> " + sSetValue);
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSetValue != null && !"".equalsIgnoreCase(sSetValue)) 
				{
					int iCount = 0;
					boolean flgFound;
					flgFound = false;

					// To check the existance of Text Box before seting the value
					System.out.println("Checking for Object Availability=>'" + sXPath + "'");
					try 
					{
						//WebDriverWait wait = new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;
					}
					catch (Exception e) 
					{
						System.out.println("waitFor - Fail ->" + e.getMessage());
					}
						
					// Text box not found
					if (flgFound == false) 
					{
						System.out.println("TextBox not found =" + sXPath);
						System.out.println("Warning:TextBox not found =" + sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound = "false";
						System.out.println("Fail:cf =FnSetText - Fail - Object not found->" + sXPath);
					}

					// Text Box found so setting up the value
					else 
					{
						System.out.println("Log:TextBox found =" + sXPath);
						System.out.println("Result:Comment:TextBox found =" + sXPath);

						// Steps to set value starts from here:
						driver.findElement(By.xpath(sXPath)).clear();
						Thread.sleep(1000);
						driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
						Thread.sleep(1000);
						driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
						Thread.sleep(2000);
						
						// driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
						String sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
						System.out.println("The value displayed in the text box is :" + sGetValue);
						Thread.sleep(1000);
						iCount = 0;
						while (sGetValue == null || !(Float.parseFloat(sSetValue.replace(",", "")) == Float.parseFloat(sGetValue.replace(",", ""))))
						{
							iCount++;
							driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
							Thread.sleep(1000);
							driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
							Thread.sleep(2000);
							FnCheckMSG(driver);
							// driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
							sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
							Thread.sleep(1000);
							if ((sGetValue == null || !(Float.parseFloat(sSetValue) == Float.parseFloat(sGetValue))) && iCount == 3) 
							{
								System.out.println("INFO:Value " + sSetValue + " is not set to TextBox " + sXPath);
								System.out.println("Result-Warning:Value " + sSetValue + " is not set to TextBox " + sXPath);
								break;
							}
						}
					}
				} 
				else 
				{
					System.out.println("Error:sSetValue is null, Please pass a value");
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			System.out.println("Fail:Common Function Exception occured ->\n"); // +erromsg(e)
		}
	}
		
	/*'###############################################################
	'Function Name        : FnMenuNavigation
	'Created by           : Sumit
	'################################################################*/
	public void FnMenuNavigation(WebDriver driver, String sXPath1, String sXPath2) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound;
				flgFound = false;
				driver.findElement(By.xpath(sXPath1)).click();
				Thread.sleep(2000);

				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath2)));
					flgFound = true;
				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				
				if (flgFound == false)
				{
					System.out.println("cf =FnMenuNavigation - Fail - Object not found ->"+sXPath2);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}

				else
				{
					System.out.println("Object found ->="+sXPath2);
					driver.findElement(By.xpath(sXPath2)).click();
					Thread.sleep(2000);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("FnMenuNavigation not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
		
	/*'###############################################################
	'Function Name        : FnGetBillAmountWithoutSymbol
	'Function Description : 
	'Written By           : Sanjeev Acharya
	'Input Parameters     : sValue -> Value to be set
	'Output Parameters    :
	'################################################################*/
	public String FnGetBillAmountWithoutSymbol(String sValue) throws Exception 
	{
		System.out.println("INFO: *--FnGetBillAmountWithoutSymbol");
		String intFinal; // used to return the value get from Text Box
		intFinal = "NoValue";
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				sValue = sValue.replace(",", "");
				if (!sValue.contains(".")) 
				{
					sValue = sValue + ".00";
					System.out.println("INFO : Value does not contain DOT so appending " + sValue);
				}
				
				String txt = sValue;
				String re1 = ".*?"; // Non-greedy match on filler
				String re2 = "(\\d+)"; // Integer Number 1
				String re3 = "(\\.)"; // Any Single Character 1
				String re4 = "(\\d+)"; // Integer Number 2
				Pattern p = Pattern.compile(re1 + re2 + re3 + re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
				Matcher m = p.matcher(txt);
				if (m.find()) 
				{
					String int1 = m.group(1);
					String c1 = m.group(2);
					String int2 = m.group(3);
					System.out.println("INFO:(" + int1.toString() + ")" + "(" + c1.toString() + ")" + "("+ int2.toString() + ")" + "\n");
					intFinal = int1.toString() + c1.toString() + int2.toString();
					System.out.println(intFinal);
					BigDecimal intFinal1 = new BigDecimal(intFinal);
					intFinal = intFinal1 + "";
					System.out.println("INFO" + intFinal);
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("INFO : Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			System.out.println("Fail : Common Function Exception occured ->\n"); // +erromsg(e)
		}
		return intFinal;
	}
	
	/*'#######################################################################################################################################
	'Function Name        : FnDateCompareForAllRowsFromWebTable
	'Created by           : Sumit
	'#########################################################################################################################################*/
	public String FnDateCompareForAllRowsFromWebTable(WebDriver driver, String sXPath, String sSearchText, int iColToSearch, int iColToGet) throws Exception
	{
		System.out.println("*--FnDateCompareForAllRowsFromWebTable");
		int sRowCount 		= 0;
		boolean flgFound    = false;  		// used as a flag for object found
		boolean flgTextFound= false;; 		// used as a flag for text found			
		String sCompareText = "";
		String sGetText		= "NoValue";
		
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Web Table before trying to retrieve the column names
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				} 
				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				
				// Web table not found
				if (flgFound == false)
				{
					System.out.println("Web table not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				
				// Web Table found so getting the value from required column
				else 
				{
					System.out.println("Web Table found ->"+sXPath);
					List<WebElement>  rows = driver.findElements(By.xpath(sXPath+"/tbody/tr/td[1]"));
					sRowCount = rows.size();
					System.out.println("No of rows are : " +sRowCount); 
					for(int j=1;j<=sRowCount;j++)	// J count starts from 1 as there are Nested tables in Bill segment table otherwise J count will be 2
					{
						sCompareText = driver.findElement(By.xpath(sXPath+"/tbody/tr["+j+"]/td["+iColToSearch+"]")).getText().replace("‑", "-");
						System.out.println("Compare Text "+sCompareText);
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						Date date1 = sdf.parse(sSearchText);
						Date date2 = sdf.parse(sCompareText);

						System.out.println("date1 "+sdf.format(date1));
						System.out.println("date2 "+sdf.format(date2));

						if(date1.compareTo(date2)>0)
						{
							System.out.println("Date1 is after Date2");
							flgTextFound = true;
							sGetText = "TRUE";
						}
						else if(date1.compareTo(date2)==0)
						{
							System.out.println("Date1 is equal to Date2");
							flgTextFound = true;
							sGetText = "TRUE";
						} 
						else 
						{
							flgTextFound = false;
							break;
						}
					}
					if (flgTextFound == true)
					{
						System.out.println("Text " + sSearchText + " to search in Web Table found"+sXPath);
					} 
					else 
					{
						System.out.println("Text " + sSearchText + " to search in Web Table not found"+sXPath);
						sGetText = "NOTFOUND";
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnDateCompareForAllRowsFromWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sGetText;
	}
		
	/*'##################################################################################
    'Function Name        : FnSetTextArea
    'Function Description : To set data in Text Area
    'Written By           : Sanjeev Acharya
    'Input Parameters     : sTextAreaXPath -> XPath of Text Area 
    '                               : sSetValue      -> Value to be set in the Text Area 
    'Output Parameters    :
    '####################################################################################*/
	public void FnSetTextArea(WebDriver driver, String sXPath, String sSetValue) throws Exception 
	{
		System.out.println("INFO : *--FnSetTextArea  >> " + sSetValue);
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSetValue != null && !"".equalsIgnoreCase(sSetValue))
				{
					int iCount = 0;
					boolean flgFound;
					flgFound = false;

					// To check the existance of Text Area before seting the value
					try 
					{
						//WebDriverWait wait = new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;
					} 
					catch (Exception e) 
					{
						System.out.println("waitFor - Fail ->" + e.getMessage());
					}

					// Text Area not found
					if (flgFound == false) 
					{
						System.out.println("INFO : TextArea not found = " + sXPath);
						System.out.println("WARN : TextArea not found = " + sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound = "false";
						System.out.println("ERROR : cf = FnSetTextArea - Fail - Object not found-> " + sXPath);
					}

					// Text Area found so setting up the value
					else 
					{
						System.out.println("INFO : TextArea found = " + sXPath);
						System.out.println("LOG:Comment : TextArea found = " + sXPath);
						if (sSetValue.equalsIgnoreCase("Blank")) 
						{
							sSetValue = "";
						}
						driver.findElement(By.xpath(sXPath)).clear();
						driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
						Thread.sleep(1000);
						driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
						Thread.sleep(2000);
						// driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
						String sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
						Thread.sleep(1000);
						if (sSetValue.equalsIgnoreCase("")) 
						{
							if (sGetValue == null) 
							{
								sGetValue = "";
							}
						}
						Thread.sleep(1000);
						iCount = 0;
						while (sGetValue == null || !sSetValue.equalsIgnoreCase(sGetValue)) 
						{
							iCount++;
							driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
							Thread.sleep(1000);
							driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
							Thread.sleep(2000);
							// driver.findElement(By.xpath(sXPath)).sendKeys(Keys.TAB);
							sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
							Thread.sleep(1000);
							if ((sGetValue == null || !sSetValue.equalsIgnoreCase(sGetValue)) && iCount == 3) 
							{
								System.out.println("info : Value '" + sSetValue + "' is not set to TextArea " + sXPath);
								System.out.println(" getStepResult().addWarning : Value " + sSetValue + " is not set to TextArea " + sXPath);
								break;
							}
						}
					}
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Logger.info : Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			System.out.println("FnTestCaseStatusReport : Fail Common Function Exception occured ->\n"); // +erromsg(e)
		}
	}
	
	
	
	/*'###############################################################
	'Function Name        : FnSetTextAtDialog
	'Function Description : To Choose/Browse File in dialog window. 
    'Written By           : Sanjeev Acharya
        'Input Parameters     : sXPath -> XPath of Text Area 
        '                     : sSetValue      -> Final File Path
        'Output Parameters    :
	'################################################################*/
	public void FnSetTextAtDialog(WebDriver sUploadDriver, String sXPath, String sSetValue) throws Exception
	{
		System.out.println("INFO : *--FnSetTextAtDialog  >> "+sSetValue);
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSetValue != null && !"".equalsIgnoreCase(sSetValue))
				{
					boolean flgFound;
					flgFound = false;
					
					// To check the existance of Text Box before seting the value
					try 
					{
						//WebDriverWait wait = new WebDriverWait(sUploadDriver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(sUploadDriver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;

					}
					catch (Exception e) 
					{
						System.out.println("waitFor - Fail ->" + e.getMessage());
					}
					
					// Text Area not found
					if (flgFound == false) 
					{
						System.out.println("INFO : dialog not found = " + sXPath);
						System.out.println("WARN : dialog not found = " + sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound = "false";
						System.out.println("ERROR : cf =FnSetTextAtDialog - Fail - Object not found-> " + sXPath);
					}
					
					// Text Box found so setting up the value
					else 
					{
						System.out.println("INFO : dialog found = " + sXPath);
						sUploadDriver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
						Thread.sleep(2000);	
					}
				}
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Logger.info : Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			System.out.println("FnTestCaseStatusReport : Fail Common Function Exception occured ->\n"); // +erromsg(e)
		}
	}
	
	/*'###############################################################################
	'Function Name        : FnClickAtDialogButton
	'Function Description : To Clik on Ok button after browsing File in dialog window. 
        'Written By           : Sanjeev Acharya
        'Input Parameters     : sXPath -> XPath of the button.
        'Output Parameters    :
	'#################################################################################*/
	public void FnClickAtDialogButton(WebDriver driver, String sXPath) throws Exception{
		System.out.println("*--FnClickAtDialogButton  >> "+sXPath);
		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				
					boolean flgFound;
					flgFound = false;
					
					// To check the existance of Text Box before seting the value
					try
					{
						//WebDriverWait wait = new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								.withTimeout(Duration.ofSeconds(20))
								.pollingEvery(Duration.ofSeconds(5))
								.ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;
					}
					catch (Exception e)
					{
						System.out.println("waitFor - Fail ->" + e.getMessage());
					}
					// Text Box not found
					if (flgFound == false) 
					{
						System.out.println("INFO : dialog not found = " + sXPath);
						System.out.println("WARN : dialog not found = " + sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound = "false";
						System.out.println("ERROR : cf =FnClickAtDialogButton - Fail - Object not found-> " + sXPath);
					}
					// Text Box found so setting up the value
					else {
						System.out.println("INFO : dialog found = " + sXPath);
						driver.findElement(By.xpath(sXPath)).click();
						Thread.sleep(2000);
						
					}
				}
			
			
		}catch (Exception e) {
			System.out.println("Logger.info : Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			System.out.println("FnTestCaseStatusReport : Fail Common Function Exception occured ->\n"); // +erromsg(e)
		}
	}
	
	/*'###############################################################
	'Function Name        : FnRequestVerify
	'Function Description : To verify Capture request sac=ved or not
	'Written By           : Sanjeev Acharya
	'Input Parameters     : sXPath 			-> XPath of Element
	'                     : sPageName		-> Page Name for Reporting 
	'Output Parameters    : flgFound		-> "true" if page found
	'################################################################*/
	public String FnRequestVerify(WebDriver driver, String sXPath, String sPageName) throws Exception 
	{
		System.out.println("INFO : *--FnRequestVerify");
		String flgFound;
		flgFound = "false";
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				try 
				{
					//WebDriverWait wait = new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					System.out.println("Page [" + sPageName + "] found by Object ["+ sXPath +"]");

				}
				catch (Exception e)
				{
					System.out.println("waitFor - Fail ->" + e.getMessage());
				}
				// Element on Page not found
				if (BaseTest.eFlgFound.equalsIgnoreCase("false"))
				{
					System.out.println("Page [" + sPageName + "] not found by Object " + sXPath);
					System.out.println("Warning:Page [" + sPageName + "] not found by Object " + sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound = "false";

					System.out.println("Report Fail:cf =FnPageVerify - Fail - Page [" + sPageName + "] not found by Object ");
				}
			}

		}
		catch (Exception e) 
		{
			System.out.println("Logger.info : Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			System.out.println("FnTestCaseStatusReport : Fail Common Function Exception occured ->\n"); // +erromsg(e)
		}
		return (flgFound);
	}
	
	/*'###############################################################
	'Function Name        : FnGetCellFromWebTable
	'Created by           : Sumit
	'################################################################*/
	public String FnGetCellFromWebTable(WebDriver driver, String sXPath,int iRow, int iColumn) throws Exception
	{
		System.out.println("*--FnGetCellFromWebTable");
		String sCellValue = "NoValue";
		boolean flgFound = false;

		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Web Table before trying to retrieve the column names
				try
				{
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				} 
				// Web table not found
				if(flgFound == false)
				{
					System.out.println("Web table not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Web Table found so getting the value from required column
				else 
				{
					System.out.println("Web Table found ="+sXPath);
					try 
					{
						sCellValue=driver.findElement(By.xpath(sXPath+"/tbody/tr["+iRow+"]/td["+iColumn+"]")).getText().replace("‑", "-");
					} 
					catch (Exception e) 
					{
						System.out.println("Not able to retrive table cell value for Search Text");
						sCellValue = "NoValue";
						e.getLocalizedMessage();
					}
					if (sCellValue == null)
					{
						sCellValue = "NoValue";
					}
					sCellValue = sCellValue.trim();
					System.out.println("Cell value "+sCellValue);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnGetCellValueFromWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sCellValue;
	}
	
	/*'###############################################################
	'Function Name        : FnSelectValueAtSearch
	'Written By 		  : Sumit S
	'################################################################*/
	public void FnSelectValueAtSearch(WebDriver driver, String sXPath, String sSelectValue) throws Exception
	{
		System.out.println("*--FnSelectValueAtSearch  >> "+sSelectValue);
		try
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSelectValue != null && !"".equalsIgnoreCase(sSelectValue))
				{
					boolean flgFound, flgFoundSelectOption;
					flgFound = false;
					flgFoundSelectOption = false;
					int i=0;
					int iCount = 0;
					// To check the existence of Select Box before setting the value
					try 
					{ 
						//WebDriverWait wait=new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;
					} 

					catch (Exception e)
					{ 
						System.out.println("waitFor - Fail ->"+e.getMessage());
					}
					// Dropdown not found
					if (flgFound == false)
					{
						System.out.println("cf =FnSelectValue - Fail - Object not found ->"+sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound="false";
					}
					// Checking Select option available 
					else 
					{
						Select select  = new Select(driver.findElement(By.xpath(sXPath)));
						List<WebElement> allOptions = select.getOptions();
						for(i=0; i<allOptions.size(); i++) 
						{
							String value= allOptions.get(i).getText();
							if (!(value == null))
							{
								value = value.toString().trim();
							}
							
							if (sSelectValue.equalsIgnoreCase(value))
							{
								System.out.println("SelectBox value found ="+sSelectValue);
								flgFoundSelectOption = true;
								break;
							}
						}
						
						if (flgFoundSelectOption == false)
						{
							System.out.println("SelectBox value not found ="+sSelectValue);
							FnScreenCapture();
							BaseTest.eFlgFound="false";
						}

						// Select Box found so setting up the value
						else 
						{
							System.out.println("SelectBox found ="+sXPath);
							select.selectByVisibleText(sSelectValue);
							WebElement option = select.getFirstSelectedOption();
							String sGetSelectedValue = option.getText();
							String sGetValue = sGetSelectedValue;
							iCount = 0;
							while (sGetValue == null || !sSelectValue.equalsIgnoreCase(sGetValue))
							{
								select.selectByVisibleText(sSelectValue);
								sGetSelectedValue = option.getText();
								sGetValue = sGetSelectedValue;
								iCount++;
								if (iCount == 3)
								{
									System.out.println("Value \""+sSelectValue+"\" is not set to SlectBox ["+sXPath+"]");
									break;
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnSelectValueAtSearch not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}			
	}
	
	/*'###############################################################
	'Function Name        : FnSetTextAtSearch
	'Written By 		  : Sumit S
	'################################################################*/
	public void FnSetTextAtSearch(WebDriver driver, String sXPath, String sSetValue) throws Exception
	{
		System.out.println("*--FnSetTextAtSearch  >> "+sSetValue);
		
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSetValue != null && !"".equalsIgnoreCase(sSetValue))
				{
					int iCount = 0;
					boolean flgFound;
					flgFound = false;

					// To check the existence of Text Box before setting the value
					try
					{
						//WebDriverWait wait=new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
						flgFound = true;
					}
					catch(Exception e)
					{
						System.out.println("waitFor - Fail ->"+e.getMessage());
						//e.printStackTrace();
					} 
					// Text box not found
					if(flgFound == false)
					{
						System.out.println("TextBox not found ->"+sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound="false";
					}
					// Text Box found so setting up the value
					else
					{
						System.out.println("TextBox found ->="+sXPath);

						if (sSetValue.equalsIgnoreCase("Blank"))
						{
							sSetValue = "";
						}
						driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
						String sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
						if (sSetValue.equalsIgnoreCase(""))
						{
							if (sGetValue == null){
								sGetValue = "";
							}
						}
						iCount = 0;
						while (sGetValue == null || !sSetValue.equalsIgnoreCase(sGetValue)){
							iCount++;
							driver.findElement(By.xpath(sXPath)).sendKeys(sSetValue);
							sGetValue = driver.findElement(By.xpath(sXPath)).getAttribute("value");
							if ((sGetValue == null || !sSetValue.equalsIgnoreCase(sGetValue))&& iCount == 3 ){
								System.out.println("Value '"+sSetValue+"' is not set to TextBox "+sXPath);
								break;
							}
						}
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnSetTextAtSearch not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}	
	}
	
	/*'###############################################################
	'Function Name        : FnSelectValueList
	'Created by           : Sumit
	'################################################################*/
	public String FnSelectValueList(WebDriver driver, String sXPath) throws Exception
	{
		System.out.println("*--FnSelectValueList  >> ");
		String sList = "NoValue";
		ArrayList<String> lList = new ArrayList<String>();
		try
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				boolean flgFound, flgFoundSelectOption;
				flgFound = false;
				flgFoundSelectOption = false;
				System.out.println(flgFoundSelectOption);
				int i=0;
				// To check the existence of Select Box before setting the value
				try 
				{ 
					//WebDriverWait wait=new WebDriverWait(driver, 20);
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							  .withTimeout(Duration.ofSeconds(20))
							  .pollingEvery(Duration.ofSeconds(5))
							  .ignoring(NoSuchElementException.class);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
					flgFound = true;
				} 

				catch (Exception e)
				{ 
					System.out.println("waitFor - Fail ->"+e.getMessage());
				}
				// Drop down not found
				if (flgFound == false)
				{
					System.out.println("cf =FnSelectValue - Fail - Object not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				else 
				{
					System.out.println("SelectBox found ="+sXPath);
					Select option = new Select(driver.findElement(By.xpath(sXPath)));
					List<WebElement>lList1 = option.getOptions();
					for (i=0; i<lList1.size(); i++)
					{
						String 		value = lList1.get(i).getText();
						if (value == null || value.isEmpty())
						{
							value = "Blank";
						}
						lList.add(value);
						
					}
					StringBuilder commaSepValueBuilder = new StringBuilder();
					for ( int w = 0; w< lList.size(); w++)
					{
						commaSepValueBuilder.append(lList.get(w));
						if ( w != lList.size()-1)
						{
							commaSepValueBuilder.append("#");
						}
					}
					sList = commaSepValueBuilder.toString();
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnSelectValue not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
			//e.printStackTrace();
		}
		return sList;
	}
	
	/*'###############################################################
	'Function Name        : FnGetAnyDate
	'Function Description : Get Any Date (Past or Future)
	'Created by           : Ajeet
	'Input Parameters     : Number Of Days 
	'                     :  
	'Output Parameters    :Past or Upcoming Date 
	'################################################################*/
	public String FnGetAnyDate(int sNoOfDayOld) throws Exception
	{
		String sDate = "";
		try 
		{
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, sNoOfDayOld);
			Date todate1 = cal.getTime();    
			String fromdate = format.format(todate1);				       
			String sBackDate= fromdate.toString().replace("/","-");
			sDate = sBackDate;
			System.out.println("New Date -->"+sBackDate);
			System.out.println("Date Returned -->"+sDate);
				
		}
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
            e.printStackTrace();
        }
		return sDate;
	}
	
	/*'###############################################################
	'Function Name        : FnGetAnyDateForTheYYYYMMDDFormat
	'Function Description : Get Any Date (Past or Future)
	'Created by           : Ajeet
	'Input Parameters     : Number Of Days 
	'                     :  
	'Output Parameters    :Past or Upcoming Date 
	'################################################################*/
	public String FnGetAnyDateForTheYYYYMMDDFormat(int sNoOfDayOld) throws Exception
	{
		String sDate = "";
		try 
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd"); 
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, sNoOfDayOld);
			Date todate1 = cal.getTime();    
			String fromdate = format.format(todate1);				       
			String sBackDate= fromdate.toString().replace("/","-");
			sDate = sBackDate;
			System.out.println("New Date -->"+sBackDate);
			System.out.println("Date Returned -->"+sDate);
		}
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
            e.printStackTrace();
        }
		return sDate;
	}
	
	/*'###############################################################
	'Function Name        : FnSetTextWithEnter
	'Function Description : To set data in Text box and press enter 
	'Created by           : Sanjeev S.
	'Input Parameters     : sTextBoxXPath -> XPath of Date Text box 
	'					  : sSetValue     -> Value to be set in the Date Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSetTextWithEnter(WebDriver driver, String sXPathofBox, String sXPathofDESC, String sSetValue) throws Exception
	{
		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				if (sSetValue != null && !"".equalsIgnoreCase(sSetValue))
				{
					boolean flgFound;
					flgFound = false;

					// To check the existence of Text Box before setting the value
					try
					{
						//WebDriverWait wait = new WebDriverWait(driver, 20);
						Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								  .withTimeout(Duration.ofSeconds(20))
								  .pollingEvery(Duration.ofSeconds(5))
								  .ignoring(NoSuchElementException.class);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPathofBox)));
						flgFound = true;
					} 
					catch (Exception e)
					{
						System.out.println("waitFor - Fail ->" + e.getMessage());
					}

					// Text box not found
					if (flgFound == false) {
						System.out.println("Date TextBox not found ->" + sXPathofBox);
						FnScreenCapture();
						System.out.println("cf =FnSetTextByKeyPress - Fail - Object not found->" + sXPathofBox);
						BaseTest.eFlgFound = "false";
					}

					// Text Box found so setting up the value
					else 
					{
						System.out.println("TextBox found ->=" + sXPathofBox);
						driver.findElement(By.xpath(sXPathofBox)).clear();
						Thread.sleep(1000);
						driver.findElement(By.xpath(sXPathofBox)).sendKeys(sSetValue);
						Thread.sleep(1000);
						driver.findElement(By.xpath(sXPathofBox)).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						boolean sExsit = driver.findElement(By.xpath(sXPathofDESC)).isDisplayed();
						// Replace below line with logger
						System.out.println("Value of Exis Flag ::" + sExsit);
						if (sExsit == true)
						{
							String sGetValue = driver.findElement(By.xpath(sXPathofDESC)).getText();
							// Replace below line with logger
							System.out.println("GETVALUE ::" + sGetValue);
							if (!sGetValue.equalsIgnoreCase(""))
							{
								// Replace below line with logger
								System.out.println("Value " + sSetValue + " is set to TextBox " + sXPathofBox);							
							}  
							else
							{
								System.out.println("Description Value Not Displayed :: " + sGetValue);
							}
						} 
						else 
						{
							System.out.println("Description Not Found for XPAth::" + sXPathofBox);
							System.out.println("Value " + sSetValue + " is not set to TextBox " + sXPathofBox);
						}
					}
				}
			}
		}

		catch (Exception e) {
			// MasterCallingScript.test.log(Status.FAIL, "FnSetText not working
			// ->"+e.getMessage());
			System.out.println("FnSetTextWithEnter not working ->" + e.getMessage());
			BaseTest.eFlgFound = "false";
		}
	  }
	
		
	/*'###############################################################
    'Function Name        : FnDefaultContentNavigation
    'Function Description : To Click Image
    'Input Parameters     :                         
    'Output Parameters    :
    'Created By 		  : Akshata
    '################################################################*/
	public void FnDefaultContentNavigation(WebDriver driver, String sFrame) throws Exception
    {
    	System.out.println("--FnDefaultContentNavigation");
    	try 
    	{
    		if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
    		{
    			if ((BaseTest.sfusion.equalsIgnoreCase("Y")) && (sFrame.contains("main")) || (BaseTest.sfusion.equalsIgnoreCase("Y")) && (sFrame.contains("tabPage")) || (BaseTest.sfusion.equalsIgnoreCase("Y")) && (sFrame.contains("tabMenu"))||(BaseTest.sfusion.equalsIgnoreCase("Y")&&(sFrame.contains("dashboard"))))
    			{
    				System.out.println("Log:Info:Fusion flow");
    				if (sFrame.contains("main")) 
    				{
    					driver.switchTo().defaultContent();
    					driver.switchTo().frame("main");
    				}
    				if (sFrame.contains("tabPage"))
    				{
    					driver.switchTo().defaultContent();
    					driver.switchTo().frame("main");
    					Thread.sleep(1000);
    					driver.switchTo().frame("tabPage");
    				}
    				if (sFrame.contains("tabMenu")) 
    				{
    					driver.switchTo().defaultContent();
    					driver.switchTo().frame("tabMenu");
    				}
    				if (sFrame.contains("dashboard")) 
    				{
    					driver.switchTo().defaultContent();
    					driver.switchTo().frame("main");
    					driver.switchTo().frame("dashboard");
    				}
    			}
    		}
    	}
    	catch (Exception e) 
    	{
    		System.out.println("Logger.info : Common Function Exception occured ->" + e.getLocalizedMessage());
    		e.printStackTrace();
    		BaseTest.eFlgFound = "false";
    		System.out.println("FnTestCaseStatusReport : Fail Common Function Exception occured ->\n"); // +erromsg(e)
        }
    }
    
    /*'######################################################################################################################
    'Function Name        : FnSetFrame
    'Function Description : To Click Image
    'Input Parameters     :
    'Output Parameters    :
    'Created By : Sanjeev
	'#########################################################################################################################*/
	public void FnSetFrame(WebDriver driver, String frameid) throws Exception
    { 
		Thread.sleep(2000);
    	try 
    	{
    		if(BaseTest.eFlgFound.equalsIgnoreCase("true"))
    		{
    			 
    			switch (frameid)
    			{
    			case "main":
    				// Switch to frame
    				FnImplicitWaitForElement(driver,10);
    				driver.switchTo().defaultContent();
    				FnImplicitWaitForElement(driver,10);
    				driver.switchTo().frame("main");
    				break;
                case "tabPage":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("tabPage");
                	break;
                case "dashboard":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("dashboard");
                	break;
                case "tabMenu":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("tabMenu");
                	break;
                case "uiMap":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("uiMap");
                	break; 
                case "topMenu":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("topMenu");
                	break;
                case "workSpace":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("workSpace");
                	break;  
                case "POSPrinter":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("POSPrinter");
                	break;
                case "printFrame":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("printFrame");
                	break;
                	
                case "CharGrid_character":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("CharGrid_character");
                    break;
                    
                case "ACC_IDENTIFIER":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("ACC_IDENTIFIER");
                    break;
                case "BsegGrid_Grid":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	 driver.switchTo().frame("tabPage");
                	 FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("BsegGrid_Grid");
                	break;
                case "FinlSumm_invConstGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("FinlSumm_invConstGrid");
                    break;
                case "zoneMapFrame_1":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("zoneMapFrame_1");
                    break;
                case "zoneMapFrame_2":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("zoneMapFrame_2");
                    break;
                case "zoneMapFrame_3":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
    				driver.switchTo().frame("main");
    				FnImplicitWaitForElement(driver,10);
    				driver.switchTo().frame("tabPage");
    				FnImplicitWaitForElement(driver,10);
    				driver.switchTo().frame("zoneMapFrame_3");
    				break;
                case "zoneMapFrame_4":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
    				driver.switchTo().frame("main");
    				FnImplicitWaitForElement(driver,10);
    				driver.switchTo().frame("tabPage");
    				FnImplicitWaitForElement(driver,10);
    				driver.switchTo().frame("zoneMapFrame_4");
    				break;
                case "brTree":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("brTree");
                    break;
                case "treeView_rsTreeEl":
               	 	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("tabPage");
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("treeView_rsTreeEl");
                	break;
                case "VALUE_PN":
                  	 // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("tabPage");
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("VALUE_PN");
                	break;
                case "USAGE_PN":
                 	 // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("tabPage");
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("USAGE_PN");
                	break;
                case "dataframe":
                	// Switch to frame
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().defaultContent();
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("main"); 
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("tabPage");
                	FnImplicitWaitForElement(driver,10);
                	driver.switchTo().frame("dataframe");
                	break;

                case "FinlSumm_bsegGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("FinlSumm_bsegGrid");
                    break;
                case "BCHG_LN_CHAR":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("BCHG_LN_CHAR");
                    break;
                case "payGridpaymentGri":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("payGridpaymentGri");
                    break;
                case "PAYMENT_SEG_GD":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("PAYMENT_SEG_GD");
                    break;
                case "AutoPay_Grid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("AutoPay_Grid");
                    break;
                case "resultGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,20);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,20);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,20);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,20);
                    driver.switchTo().frame("resultGrid");
                    break;
                case "datagrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("datagrid");
                    break;
                case "dataGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("dataGrid");
                    break;
                case "section2dataFrame_1":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("section2dataFrame_1");
                    break;
                case "BILL_FT":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("BILL_FT");
                    break;
                case "ADJTY_CHAR":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("ADJTY_CHAR");
                    break;
                case "PY_TNDR_CHAR":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("PY_TNDR_CHAR");
                    break;
                case "boStatusAccordionPage":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("boStatusAccordionPage");
                    break;
                case "STAT_ALG_GD:5":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("boStatusAccordionPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("STAT_ALG_GD:5");
                    break;
                case "LINKS_GRID":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("LINKS_GRID");
                    break;
                case "CALENDAR_HOLIDAY":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("CALENDAR_HOLIDAY");
                    break; 
                case "accGroupGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("accGroupGrid");
                    break;
                case "schedschedGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("schedschedGrid");
                    break;
                case "GridgridResult":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("GridgridResult");
                    break;
                case "Grid_resultGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("Grid_resultGrid");
                    break; 
                case "GRID":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("GRID");
                    break; 
                case "dataFrame_1":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("dataFrame_1");
                    break;  
                case "dataFrame_2":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("dataFrame_2");
                    break; 
                case "AT_ALG":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("AT_ALG");
                    break; 
                case "adjGridadjGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("adjGridadjGrid");
                    break;    
                case "rvTree_rvTreeEl":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("rvTree_rvTreeEl");
                    break; 
                case "mchDatamchData":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("mchDatamchData");
                    break; 
                case "POSS_XREF_GRID":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("POSS_XREF_GRID");
                    break; 
                case "PKAreaPKPanel":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("PKAreaPKPanel");
                    break; 
                case "jobStreamGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("jobStreamGrid");
                    break;
                case "SA_CHAR":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("SA_CHAR");
                    break;
                case "userDARGrid":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("userDARGrid");
                    break;
                case "ODP_TMP_ALG":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("ODP_TMP_ALG");
                    break;
                    
                case "wfmOptionsColl":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("wfmOptionsColl");
                    break;
                case "BJP":
                    // Switch to frame
                	FnImplicitWaitForElement(driver,10);
                    driver.switchTo().defaultContent();
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("main"); 
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("tabPage");
                    FnImplicitWaitForElement(driver,10);
                    driver.switchTo().frame("BJP");
                    break;
                }
    		}
    	}
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    		System.out.println("FnNavigation not working ->"+e.getMessage());
    		BaseTest.eFlgFound="false";
    		System.out.println("FnTestCaseStatusReport : Fail Common Function Exception occured ->\n"); // +erromsg(e)
    	}
    }
    
	 /*'###############################################################################################
    'Function Name        : FnVerifyRespectiveRowInWebTable
    'Function Description : To Click Image/icon/link in a respective row's column.
    'Input Parameters     : sXPath                -> XPath of WebTable
                            sXPathTC        -> XPath of column against which the row will be picked
    'Output Parameters    :
    '#################################################################################################*/
	public void FnVerifyRespectiveRowInWebTable(WebDriver driver, String sXPath, String sXPathTC ,String sExpectedValue) throws Exception
    {
    	System.out.println("*--FnVerifyRespectiveRowInWebTable");
    	try 
    	{
    		if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
    		{
    			// Grab the table
    			WebElement table = driver.findElement(By.xpath(sXPath));
    			
    			Thread.sleep(2000);
    			// Get all the rows from the table
    			List<WebElement> allRows = table.findElements(By.xpath((sXPath) + "/tbody/tr"));
    			int rowCount = allRows.size();
    			System.out.println("Rows present in the table : " + rowCount);
    			
    			int count = table.findElements(By.xpath(sXPathTC)).size();
    			//Navigate to respective Person
    			for (int i = 0; i < count; i++) 
    			{
    				String actualColValue = table.findElements(By.xpath(sXPathTC)).get(i).getText().replace(" ","-");
    				if (actualColValue.equalsIgnoreCase(sExpectedValue)) 
    					System.out.println("actualColValue : " + actualColValue + " matches sExpectedValue : " + sExpectedValue);
    			}
    		}
    	}
    	catch (Exception e) 
    	{
    		System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
    		e.printStackTrace();
    		BaseTest.eFlgFound = "false";
    		FnTestCaseStatusReport("Fail", "Common Function Exception occured ->\n"+erromsg(e));
        }
    }
	
	/*'###############################################################################################
    'Function Name        : FnClickLinkInRowOfWebTable
    'Function Description : To Click Image/icon/link in a respective row's column.
    'Input Parameters     : sXPath          -> XPath of WebTable
                            sXPathTC        -> XPath of column against which the row will be picked
                            sExpectedValue  -> Value against which the row will be picked.
                            sItem           -> link/img to be clicked.
    'Written By           : Sanjeev Acharya
    'Output Parameters    :
	'#################################################################################################*/
	public void FnClickLinkInRowOfWebTable(WebDriver driver, String sXPath, String sXPathTC, String sExpectedValue, String sItem,int ColNo) throws Exception 
    {      
    	System.out.println("*--FnSelectRespectiveRowInWebTable");
    	Boolean flg = false;
    	try 
    	{
    		if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
    		{
    			// Grab the table
    			WebElement table = driver.findElement(By.xpath(sXPath));
    			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    					.withTimeout(Duration.ofSeconds(20))
    					.pollingEvery(Duration.ofSeconds(5))
    					.ignoring(NoSuchElementException.class);
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
    			// Get all the rows from the table
    			List<WebElement> allRows = table.findElements(By.xpath((sXPath) + "/tbody/tr"));
    			int rowCount = allRows.size();
    			System.out.println("Rows present in the table : " + rowCount);

    			int count = table.findElements(By.xpath(sXPathTC)).size();
    			System.out.println("Count = "+count);
   
        			for (int i = 0; i < count; i++)  
        			   {
         				String actualColValue = table.findElements(By.xpath(sXPathTC)).get(i).getText().replace("‑", "-").replaceAll("([0-9]{15})", " ").replaceAll("([0-9]{10})", " ").trim();
         				System.out.println("actual value----->"+actualColValue);
         				System.out.println("expected value----->"+sExpectedValue);
         				if (actualColValue.equalsIgnoreCase(sExpectedValue.replace("‑", "-"))) 
         				{
         					if (sItem.equalsIgnoreCase("gotoIcon") || sItem.equalsIgnoreCase("editIcon"))
         					{
         						FnElementClick(driver, ((sXPath) + "/tbody/tr[" + (i + 1) + "]/td[" + ColNo + "]/a/span/img"));                                        
         						if(BaseTest.eFlgFound.equalsIgnoreCase("false"))
         							flg=false;
         						else
         						{
         							flg=true;
         							FnTestCaseStatusReport("Pass", sItem + " clicked");
         							System.out.println(sItem + " clicked ");
         							break;
         						}
         					}
         					else if (sItem.equalsIgnoreCase("link"))
         					{
         						FnElementClick(driver, ((sXPath) + "/tbody/tr[" + (i + 1) + "]/td[" + ColNo + "]/a/span"));
         						if(BaseTest.eFlgFound.equalsIgnoreCase("false"))
         							flg=false;
         						else
         						{
         							flg=true;
         							FnTestCaseStatusReport("Pass", sItem + " clicked");
         							System.out.println(sItem + " clicked ");
         							break;
         						}
         					}
         					else if (sItem.equalsIgnoreCase("contextImg")) 
         					{
         						FnElementClick(driver, ((sXPath) + "/tbody/tr[" + (i + 1) + "]/td[" + ColNo + "]/a/img"));
         						if(BaseTest.eFlgFound.equalsIgnoreCase("false"))
         							flg=false;
         						else
         						{
         							flg=true;
         							FnTestCaseStatusReport("Pass", sItem + " clicked");
         							System.out.println(sItem + " clicked ");
         							break;
         						}
         					}
         					else if (sItem.equalsIgnoreCase("BroadcastIcon"))
         					{
         						FnElementClick(driver, ((sXPath) + "/tbody/tr[" + (i + 1) + "]/td/table/tbody/tr/td/img"));
         						if(BaseTest.eFlgFound.equalsIgnoreCase("false"))
         							flg=false;
         						else
         						{
         							flg=true;
         							FnTestCaseStatusReport("Pass", sItem + " clicked");
         							System.out.println(sItem + " clicked ");
         							break;
         						}
         					}

         					else if (sItem.equalsIgnoreCase("AddIcon"))
         					{
         						FnElementClick(driver, ((sXPath) + "/tbody/tr[" + (i + 1) + "]/td[" + ColNo + "]/img"));
         						if(BaseTest.eFlgFound.equalsIgnoreCase("false"))
         							flg=false;
         						else
         						{
         							flg=true;
         							FnTestCaseStatusReport("Pass", sItem + " clicked");
         							System.out.println(sItem + " clicked ");
         							break;
         						}
         					}
         					else if (sItem.equalsIgnoreCase("Checkbox"))
         					{
         						FnElementClick(driver, ((sXPath) + "/tbody/tr[" + (i + 1) + "]/td[" + ColNo + "]/input"));
         						if(BaseTest.eFlgFound.equalsIgnoreCase("false"))
         							flg=false;
         						else
         						{
         							flg=true;
         							FnTestCaseStatusReport("Pass", sItem + " clicked");
         							System.out.println(sItem + " clicked ");
         							break;
         						}
         					}
         					else if (sItem.equalsIgnoreCase("Row"))
         					{
         						FnElementClick(driver, ((sXPath) + "/tbody/tr[" + (i + 1) + "]/td[" + ColNo + "]/span"));
         						if(BaseTest.eFlgFound.equalsIgnoreCase("false"))
         							flg=false;
         						else
         						{
         							flg=true;
         							FnTestCaseStatusReport("Pass", sItem + " clicked");
         							System.out.println(sItem + " clicked ");
         							break;
         						}
         					}
         				}
         			}
    			if (flg.equals(false)) 
    			{
    				FnTestCaseStatusReport("Fail", sItem + " was not clicked");
    				System.out.println(sItem + " was not clicked");
    			}
    		}
    	} 
    	catch (Exception e) 
    	{
    		System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
    		e.printStackTrace();
    		BaseTest.eFlgFound = "false";
    		FnTestCaseStatusReport("Fail","Common Function Exception occured ->\n" + erromsg(e));
    		System.out.println("FnClickLinkInRowOfWebTable Exception occured");
    	}
    }
	
	/*'###############################################################################################
	'Function Name        : FnVerifyColumnValueOfRowInWebTable
	'Function Description : To Click Image/icon/link in a respective row's column.
	'Input Parameters     : sXPath 			-> XPath of WebTable
	                        sXPathTC        -> XPath of column against which the row will be picked
	'Output Parameters    :
	'#################################################################################################*/
	public void FnVerifyColumnValueOfRowInWebTable(WebDriver driver, String sXPath, String sXPathTC, int sStartingRow, int sStartingCol, String sSheetName, String sfileName, String colToVerify) throws Exception 
	{
		Boolean flg = false;
		String actualColValue = null;
		String sExpectedValue = null;
		WebElement ele;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("*--FnVerifyRespectiveRowInWebTable");
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// Grab the table
				WebElement table = driver.findElement(By.xpath(sXPath));
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						  .withTimeout(Duration.ofSeconds(20))
						  .pollingEvery(Duration.ofSeconds(5))
						  .ignoring(NoSuchElementException.class);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath)));
			
				// Verify single column value in a single row
				List<WebElement> allRows = table.findElements(By.xpath((sXPath) + "/tbody/tr"));
				int rowCount = allRows.size();
				System.out.println("Rows present in the table : " + rowCount);
				if (colToVerify.equalsIgnoreCase("SingleColumn")) 
				{
					for (int i = 0; i < rowCount; i++) 
					{
						sExpectedValue = FnGetCellValue(sStartingRow, (sStartingCol + i), sSheetName, sfileName).replace("‑", "-").replace("NoValue", "").replaceAll("([0-9]{15})", " ").replaceAll("([0-9]{10})", " ").trim();

						actualColValue = table.findElements(By.xpath(sXPathTC)).get(i).getText().replace("‑", "-").replaceAll("([0-9]{15})", " ").replaceAll("([0-9]{10})", " ").trim();
				
					    flg = false;
						if (actualColValue.equalsIgnoreCase(sExpectedValue)) 
						{
							ele = driver.findElements(By.xpath(sXPathTC)).get(i);
							js.executeScript("arguments[0].setAttribute('style', 'background: orange; border: 2px solid red;');",ele);
							Thread.sleep(3000);
							js.executeScript("arguments[0].removeAttribute('style','')", ele);
							
							FnTestCaseStatusReport("Pass ", "actualColValue : " + actualColValue + " matches sExpectedValue : " + sExpectedValue);
							flg = true;
							break;
						}
						if (flg.equals(false))
						{
							FnTestCaseStatusReport("Fail", "actualColValue : " + actualColValue + " does not match sExpectedValue : " + sExpectedValue);
							
						}
					}
				}
				
				else if (colToVerify.equalsIgnoreCase("SingleColInEachRow")) 
				{
					for (int i = 0; i < rowCount; i++) 
					{
						sExpectedValue = FnGetCellValue(sStartingRow, (sStartingCol + i), sSheetName, sfileName).replace("‑", "-").replace("NoValue", "").replaceAll("([0-9]{15})", " ").replaceAll("([0-9]{10})", " ").trim();

						actualColValue = table.findElements(By.xpath(sXPathTC)).get(i).getText().replace("‑", "-").replaceAll("([0-9]{15})", " ").replaceAll("([0-9]{10})", " ").trim();
				
					    flg = false;
						if (actualColValue.equalsIgnoreCase(sExpectedValue)) 
						{
							ele = driver.findElements(By.xpath(sXPathTC)).get(i);
							js.executeScript("arguments[0].setAttribute('style', 'background: orange; border: 2px solid red;');",ele);
							Thread.sleep(3000);
							js.executeScript("arguments[0].removeAttribute('style','')", ele);
							

							FnTestCaseStatusReport("Pass ", "actualColValue : " + actualColValue + " matches sExpectedValue : " + sExpectedValue);
							flg = true;
						}
						if (flg.equals(false))
						{
							FnTestCaseStatusReport("Fail", "actualColValue : " + actualColValue + " does not match sExpectedValue : " + sExpectedValue);
							
						}
					}
				}
				else if (colToVerify.equalsIgnoreCase("EachColInEachRow")) 
				{
					for (int i = 0; i < rowCount; i++) 
					{
						// Get all the columns from the table
						List<WebElement> allColumns = table.findElements(By.xpath((sXPath) + "/thead/tr/th"));
						int colCount = allColumns.size();
						System.out.println("Columns present in the table : " + colCount);
						for (int j = 0; j < colCount; j++) 
						{
							sExpectedValue = FnGetCellValue((sStartingRow + i), (sStartingCol + j), sSheetName,	sfileName).replace("‑", "-").replace("NoValue", "").replaceAll("([0-9]{15})", " ").replaceAll("([0-9]{10})", " ").trim();

							actualColValue = table.findElements(By.xpath((sXPath) + "/tbody/tr[" + (i + 1) + "]/td")).get(j).getText().replace("‑", "-").replaceAll("([0-9]{15})", " ").replaceAll("([0-9]{10})", " ").trim();

							flg = false;
							if (actualColValue.equalsIgnoreCase(sExpectedValue)) 
							{
								 ele = driver.findElements(By.xpath((sXPath) + "/tbody/tr[" + (i + 1) + "]/td")).get(j);
								js.executeScript("arguments[0].setAttribute('style', 'background: orange; border: 2px solid red;');",ele);
								Thread.sleep(3000);
								js.executeScript("arguments[0].removeAttribute('style','')", ele);
								FnTestCaseStatusReport("Pass","actualColValue : " + actualColValue + " matches sExpectedValue : "+ sExpectedValue);
								flg = true;
							}
							if (flg.equals(false))
							{
								FnTestCaseStatusReport("Fail", "actualColValue : " + actualColValue + " does not match sExpectedValue : " + sExpectedValue);
								
							}
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			FnTestCaseStatusReport("Fail","Common Function Exception occured ->\n" + erromsg(e));
			
		}
	}
	
	/*'###############################################################################################
	'Function Name        : FnVerifyZoneValues
	'Function Description : To Click Image/icon/link in a respective row's column.
	'Input Parameters     : sXPath 			-> XPath of WebTable
	                        sXPathTC        -> XPath of column against which the row will be picked
	'Output Parameters    :
	'#################################################################################################*/
	public void FnVerifyZoneValues(WebDriver driver, String sXPath, int sStartingRow, int sStartingCol, String sSheetName, String sWorkbook , String sPassMessage , String sFailMessage) throws Exception 
	{
		System.out.println("*--FnVerifyZoneValues");
		try 
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{

				List<WebElement> actualAccInfoList=driver.findElements(By.xpath(sXPath));
				ArrayList<String> actualAccInfoArrayList=new ArrayList<String>();
				
			
				for(int i=0;i<actualAccInfoList.size();i++)
				{	
					actualAccInfoArrayList.add(actualAccInfoList.get(i).getText().replace("‑", "-"));
				}
				
				actualAccInfoArrayList.removeAll(Arrays.asList(null,""));
				ArrayList<String> actualDisplayedAccInfoArrayList=new ArrayList<String>();
				actualDisplayedAccInfoArrayList=actualAccInfoArrayList;
			
				ArrayList<String> expectedAccInfoArrayList=new ArrayList<String>();
				for(int i=0;i<actualDisplayedAccInfoArrayList.size();i++)
				{
					String expectedAccInfoList=FnGetCellValue(sStartingRow, i+sStartingCol, sSheetName, sWorkbook).replace("‑", "-");
					expectedAccInfoArrayList.add(expectedAccInfoList.replace("NoValue", ""));
				}
				if(actualDisplayedAccInfoArrayList.equals(expectedAccInfoArrayList))
				{
					FnTestCaseStatusReport("Pass", sPassMessage);
					System.out.println("pass " + sPassMessage);
				}
				else
				{
					FnTestCaseStatusReport("Fail", sFailMessage );
					System.out.println("Fail " + sFailMessage);
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			FnTestCaseStatusReport("Fail","Common Function Exception occured ->\n" + erromsg(e));
			
		}
	}

	
	public void assertEquals(String pagename, String sExpPageName) throws Exception 
	{
		try 
		{
			Assert.assertEquals(pagename,sExpPageName);
			BaseTest.extentTest.get().log(Status.INFO,"Login Sucessful");
		}
		catch(AssertionError error) 
		{
			BaseTest.extentTest.get().log(Status.INFO,"Login Unsucessful");
            Markup m = MarkupHelper.createLabel("Value not matched", ExtentColor.RED);
            BaseTest.extentTest.get().log(Status.FAIL, m);
            throw new Exception("Problem connecting to server");
		}
	}

	/*'###############################################################
	'Function Name        : FnGetCellValueFromWebTable
	'Function Description : To get value from web table from providing row and column number
	'Created by           : Abhishek
	'Input Parameters     : sWebTableXPath	-> XPath of Web table
						  : iRow		-> row number
						  : iColumn		-> column number
	'Output Parameters    : Return the cell value
	'################################################################*/
	public String FnGetCellValueFromWebTabl(WebDriver driver, String sXPath,int iRow, int iColumn) throws Exception
	{
		System.out.println("*--FnGetCellValueFromWebTable");
		String sCellValue = "NoValue";
		boolean flgFound = false;
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Web Table before trying to retrieve the column names
				try
				{
					WebDriverWait wait = new WebDriverWait(driver,60);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				} 
				// Web table not found
				if(flgFound == false)
				{
					System.out.println("Web table not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Web Table found so getting the value from required column
				else 
				{
					System.out.println("Web Table found ->"+sXPath);
					sCellValue=driver.findElement(By.xpath(sXPath+"/tbody["+iRow+"]/tr/td["+iColumn+"]")).getText().replace("‑", "-");

					if(sCellValue.isEmpty())
					{
						System.out.println("Cell value is empty...");
					}
					System.out.println("Cell value "+sCellValue);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnGetCellValueFromWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sCellValue;
	}  	
	
	/*'###############################################################
	'Function Name        : FnGetCellValueFromWebTable
	'Function Description : To get value from web table from providing row and column number
	'Created by           : Abhishek
	'Input Parameters     : sWebTableXPath	-> XPath of Web table
						  : iRow		-> row number
						  : iColumn		-> column number
	'Output Parameters    : Return the cell value
	'################################################################*/
	public String FnGetCellValueFromWebTbl(WebDriver driver, String sXPath,int iRow, int iColumn) throws Exception
	{
		System.out.println("*--FnGetCellValueFromWebTable");
		String sCellValue = "NoValue";
		boolean flgFound = false;
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				// To check the existence of Web Table before trying to retrieve the column names
				try
				{
					WebDriverWait wait = new WebDriverWait(driver,60);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
					flgFound = true;
				}
				catch(Exception e)
				{
					System.out.println("waitFor - Fail ->"+e.getMessage());
				} 
				// Web table not found
				if(flgFound == false)
				{
					System.out.println("Web table not found ->"+sXPath);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Web Table found so getting the value from required column
				else 
				{
					System.out.println("Web Table found ->"+sXPath);
					sCellValue=driver.findElement(By.xpath(sXPath+"/tr["+iRow+"]/td["+iColumn+"]")).getText().replace("‑", "-");

					if(sCellValue.isEmpty())
					{
						System.out.println("Cell value is empty...");
					}
					System.out.println("Cell value "+sCellValue);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("FnGetCellValueFromWebTable not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
		return  sCellValue;
	}  	
	
	/*'###############################################################
	'Function Name        : FnSelectValueByPartialText
	'Function Description : To select the option from Select box (Dropdown) matching the text
	'Created by           : Sumit
	'Input Parameters     : sXPath 			-> XPath of Text box 
	'                     : sSelectValue	-> Value to be set in the Text Box 
	'Output Parameters    :
	'################################################################*/
	public void FnSelectValueByPartialText(WebDriver driver, String sXPath, String sSelectValue) throws Exception
	{
		try 
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true")) 
			{
				if (sSelectValue != null && !"".equalsIgnoreCase(sSelectValue))
				{
					boolean flgFound;
					flgFound = false;

					// To check the existence of Dropdown
					try 
					{ 
						WebDriverWait wait = new WebDriverWait(driver,360);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sXPath)));
						flgFound = true;
					} 
					catch (Exception e)
					{ 
						System.out.println("waitFor - Fail ->"+e.getMessage());
					}
					
					// Dropdown not found
					if (flgFound == false)
					{
						System.out.println("cf =FnSelectValue - Fail - Object not found ->"+sXPath);
						FnScreenCapture();
						BaseTest.eFlgFound="false";
					}

					// Checking Select option available 
					else 
					{					    
						WebElement ele = driver.findElement(By.xpath(sXPath));
	                    FnScrollToElement(driver,ele);  
						Select option = new Select(ele);
						Boolean found = false;
						List<WebElement> allOptions = option.getOptions();
						
						for(int i=0; i<allOptions.size(); i++) 
						{
							
							if(allOptions.get(i).getText().contains(sSelectValue)); 
							{
								found=true;
								System.out.println("Drop Down value "+allOptions.get(i).getText());
								Thread.sleep(2000);
								allOptions.get(i).click();;
							}

						}
						
						if(found) 
						{
							System.out.println("Dropdown Value exists-->"+sSelectValue);
						}
						else
						{
							System.out.println("Dropdown value not exists-->"+sSelectValue);
							FnScreenCapture();
						}
					}
				}
				else
				{
					System.out.println("Select value is blank or null");
				}
			}
		}
		catch (Exception e) 
        {
               System.out.println("FnSelectValue not working ->");
               BaseTest.eFlgFound="false";
               e.printStackTrace();
        }

	}
	
	/*'###############################################################
	'Function Name        : FnWaitForElementtoBeClickable
	'Function Description : To wait for element
	'Created by           : Ajinkya J
	'Input Parameters     : Driver,Time in seconds to wait,XPath of element to wait
	'					  :
	'Output Parameters    :
	'################################################################*/
	
	public void FnWaitForWebElementToBeClickable(WebDriver driver, int timeOutInSeconds,WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
   	
	
	/*'###############################################################
	'Function Name        : FnSelectAllCheckBoxesByList
	'Function Description : To Select check box
	'Created by           : Ajinkya
	'Input Parameters     : sXPath 			-> XPath of element
	'                     :
	'Output Parameters    :
	'################################################################*/
	public void FnSelectAllCheckBoxesByList(WebDriver driver, List<WebElement> ele) throws Exception
	{
		try
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
                int iSizeOfCheckBoxList = ele.size();
                System.out.println("Number of checkboxes are "+iSizeOfCheckBoxList);
                for (WebElement IndivisualCheckBox : ele)
                {
                	if (IndivisualCheckBox.isEnabled())
                	{
                        Thread.sleep(3000);
                    	FnSelectCheckBoxByWebElement(driver, IndivisualCheckBox);              		
                	}	
                }	
			}
		}
		catch (Exception e)
		{
			System.out.println("FnElementClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}	

	
	/*'###############################################################
	'Function Name        : FnSelectCheckBoxByWebElement
	'Function Description : To Select check box
	'Created by           : Ajinkya
	'Input Parameters     : sXPath 			-> XPath of element
	'                     :
	'Output Parameters    :
	'################################################################*/
	public void FnSelectCheckBoxByWebElement(WebDriver driver, WebElement ele) throws Exception
	{
		try
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				boolean flgFound;
				flgFound = false;
				
				// To check the existence of element to be clicked
				FnImplicitWaitForElement(driver, 10);
				flgFound = true;
						
				// Element not found
				if (flgFound == false)
				{
					System.out.println("cf =FnElementClick - Fail - Object not found ->"+ele);
					FnScreenCapture();
					BaseTest.eFlgFound="false";
				}
				// Element found so clicking on the element
				else
				{
	                Thread.sleep(5000);
	                FnScrollToElement(driver,ele);
	                if(!ele.isSelected())
	                {
	                	highLightElement(driver, ele);
	                    FnClickOnElementByJavaScriptExecutor(driver,ele);
	                    Thread.sleep(5000);      	
	                }	
	                assertTrue(ele.isSelected(),"Check box is Selected now");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("FnElementClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}
	
	/*'###############################################################
    'Function Name        : FnClearTextFieldUsingActionClass
    'Function Description : To clear value from text field for element
    'Created by           : AkshataS
    'Input Parameters     : Driver 
    '                                  :  
    'Output Parameters    :
	'################################################################*/
	public void FnClearTextFieldUsingActionClass(WebDriver driver,String sXPath) throws InterruptedException 
	{
		Actions actions = new Actions(driver);
		Thread.sleep(3000);
		actions.click(driver.findElement(By.xpath(sXPath))).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
		Thread.sleep(5000);
    }
	
	/*'###############################################################
	'Function Name        : FnVerifyErrorMessage
	'Function Description : To Verify expected and actual error messages
	'Created by           : Ajinkya
	'Input Parameters     :
	'                     :
	'Output Parameters    :
	'################################################################*/
	public void FnVerifyErrorMessage(int iStartingRow, String sSheetName, String sWorkbook) throws Exception
	{
		try
		{
			if(BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				String sActualErrorMessage,sExpectedErrorMessage;
				
				sActualErrorMessage	   =   FnGetCellValue(iStartingRow,1, sSheetName, sWorkbook).toString().trim();
				sExpectedErrorMessage  =   FnGetCellValue(iStartingRow,2, sSheetName, sWorkbook).toString().trim();
				if (sActualErrorMessage.contains(sExpectedErrorMessage))
				{
					FnTestCaseStatusReport("Pass","Error Message is matched. Expected Error message is = "+sExpectedErrorMessage+ " Actual Error message is = "+sActualErrorMessage);
				}
				else
				{
					FnTestCaseStatusReport("Fail","Error Message is not matched. Expected Error message is = "+sExpectedErrorMessage+ " Actual Error message is = "+sActualErrorMessage);
				}	
			}
		}
		catch (Exception e)
		{
			System.out.println("FnElementClick not working ->"+e.getMessage());
			BaseTest.eFlgFound="false";
		}
	}

    
	/*'#######################################################################################################################################################################################################################################################################################################################
	'Function Name        : FnSendReportEmail
	'Function Description : Function to send Email to the Recipient by attaching cvs and html reports 
	'Input Parameters     : sReportSheetName    -> Complete path along with csv file name
	'                       PathofHTML -> Complete path along with html file name 
	'Output Parameters    : void
	'#########################################################################################################################################################################################################################################################################################################################*/
	public static void FnSendReportEmail()  throws Exception
	{
		/* prepare for sending email */
		String filenameHTML = System.getProperty("user.dir") +"/reports/"+sExtentReportName; //sFilePath+getVariables().get("sHTMLReport");
	    System.out.println("HTML File to be attached = "+filenameHTML);
	   
	    String filenameCSV = sReportSheetName;//sFilePath+getVariables().get("sCSVReport");
	    System.out.println("CSV File to be attached = "+filenameCSV);
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.host", host);
	    props.put("mail.from", from);

	    Session session = Session.getInstance(props);

	    try
	    {
	    	InetAddress myHost = InetAddress.getLocalHost();
	        System.out.println(myHost.getHostName());
	        from = myHost.getHostName()+ "@oracle.com";
	    }
	    catch (UnknownHostException ex)
	    {
	    	ex.printStackTrace();
	    }
	        
	    try
	    {
	    	MimeMessage msg = new MimeMessage(session);
	    	msg.setFrom(new InternetAddress(from));
	    	InternetAddress[] address = InternetAddress.parse(to);
	    	msg.setRecipients(Message.RecipientType.TO, address);
	    	msg.setSubject("Automation Execution Report", "utf-8");
	    	msg.setSentDate(new Date());
	      
	    	MimeBodyPart mbp1 = new MimeBodyPart();
	    	mbp1.setText(emailbody, "utf-8");
	    	
	    	MimeBodyPart mbp2 = new MimeBodyPart();
	    	FileDataSource fds1 = new FileDataSource(filenameHTML);
	    	mbp2.setDataHandler(new DataHandler(fds1));
	    	mbp2.setFileName(MimeUtility.encodeWord(fds1.getName()));
	    	
	    	MimeBodyPart mbp3 = new MimeBodyPart();
	    	FileDataSource fds2 = new FileDataSource(filenameCSV);
	    	mbp3.setDataHandler(new DataHandler(fds2));
	    	mbp3.setFileName(MimeUtility.encodeWord(fds2.getName()));
	    	
	    	Multipart mp = new MimeMultipart();
	    	mp.addBodyPart(mbp1);
	    	mp.addBodyPart(mbp2);
	    	mp.addBodyPart(mbp3);
	     	msg.setContent(mp);
	    	try
			{
				System.out.println("Sending...");
				Transport.send(msg);
				System.out.println("Email sent!");
			}
			catch (Exception ex) 
	    	{
				System.out.println("The email was not sent.");
				System.out.println("Error message: " + ex.getMessage());
			}
				
	    }
	    catch(MessagingException mex)
	    {
	    	System.out.println("¥n--Exception handling in msgsendsample.java");
	    	mex.printStackTrace();
	    }
	    catch(java.io.UnsupportedEncodingException uex)
	    { 
	        			
	    }
	}
}
