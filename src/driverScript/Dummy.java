package driverScript;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfun.FuntionLibrary;
import utilities.ExcelFileUtil;
import utilities.PropertyFileUtil;

public class Dummy {
      public  static String res="";
	  public static WebDriver driver;
	  static ExtentReports report;
		static ExtentTest test;
	  public static void main(String[] args) throws Exception {	
	  ExcelFileUtil exl=new ExcelFileUtil();
		
	  for( int i=1;i<=exl.rowCount("MasterTestCases");i++){ 
		  
			 if (exl.getData("MasterTestCases", i, 2).equalsIgnoreCase("y")){
				 
				 String tcmodule = exl.getData("MasterTestCases", i, 1);

					report=new ExtentReports("D:\\pintu\\StockAccountingHybrid\\Reports\\reports"+tcmodule+FuntionLibrary.curdate()+".html");
					test=report.startTest(tcmodule);
				
				 for (int j=1;j<=exl.rowCount(tcmodule);j++){
					 String Description = exl.getData(tcmodule,j,0);
					 String Function_Name = exl.getData(tcmodule,j,1);
					 String Locator_Type = exl.getData(tcmodule,j,2);
					 String Locator_Value  = exl.getData(tcmodule,j,3);
					 String Test_Data = exl.getData(tcmodule,j,4);
                     try{
                    	 
					 if(Function_Name.equalsIgnoreCase("startBrowser")){
						   driver= FuntionLibrary.startBrowser();
						   test.log(LogStatus.INFO, Description);
					  } 
					  else  if(Function_Name.equalsIgnoreCase("openApplication")){
						  FuntionLibrary.OpenApplication(driver);
						  test.log(LogStatus.INFO, Description);
					  }else if(Function_Name.equalsIgnoreCase("waitForElement")){
						  FuntionLibrary.waitForElement(driver,Locator_Type ,Locator_Value ,Test_Data );
						  test.log(LogStatus.INFO, Description);
					  }else if (Function_Name.equalsIgnoreCase("typeAction")){
						  FuntionLibrary.typeaction(driver,Locator_Type ,Locator_Value ,Test_Data);
						  test.log(LogStatus.INFO, Description);
					  }else if (Function_Name.equalsIgnoreCase("clickAction")){
						  FuntionLibrary.clickaction(driver,Locator_Type ,Locator_Value);
						  test.log(LogStatus.INFO, Description);
					  }else if (Function_Name.equalsIgnoreCase("closeBrowser")){
						  FuntionLibrary.close(driver);
						  test.log(LogStatus.INFO, Description);
					  }
					res="PASS";
					exl.setData(tcmodule, j, 5,res);
					test.log(LogStatus.PASS, Description);
                    }catch(Exception e){
	                res="FAIL";
	                exl.setData(tcmodule, j, 5, res);
	                
	                
	                String  date =FuntionLibrary.curdate();
	                File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcFile, new File("D:\\pintu\\StockAccountingHybrid\\ScreenShot\\Screenshots\\"+Description+date+".png"));
					  test.log(LogStatus.FAIL, Description);
				//	test.addScreenCapture("D:\\pintu\\StockAccountingHybrid\\ScreenShot\\Screenshots\\"+Description+date+".png");
					test.log(LogStatus.INFO,test.addScreenCapture("D:\\pintu\\StockAccountingHybrid\\ScreenShot\\Screenshots\\"+Description+date+".png"));
	                //test.log(LogStatus.FAIL, Description);
	                break;
                    }
				 }
     				if( res.equalsIgnoreCase("PASS")){
     					exl.setData("MasterTestCases", i, 3, "PASS");
     				}else{
     					exl.setData("MasterTestCases", i, 3, "FAIL");
     				}               	
				  report.endTest(test);
				  report.flush();
			 }else{
				 exl.setData("MasterTestCases", i, 3, "not executed");
			 }
			
		}
		 
		     
     }
    }      	