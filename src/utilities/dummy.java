package utilities;



import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfun.FuntionLibrary;

public class dummy {

	public static void main(String[] args) throws Exception {
		
		
		//ExtentReports report =new ExtentReports("C:\\Users\\Public\\Desktop\\myreport.html");
	
		ExtentReports report =new ExtentReports("D:\\myreport.html");
		System.out.println("execution started");
		
		ExtentTest writter=report.startTest("loginTest");
		
		WebDriver driver1=FuntionLibrary.startBrowser();
		System.out.println("execution started");
		writter.log(LogStatus.INFO, "execution started");
        System.out.println(driver1);
        FuntionLibrary.OpenApplication(driver1);
        System.out.println("open application");
        writter.log(LogStatus.INFO, "open application");
        FuntionLibrary.waitForElement(driver1, "id", "username", "10");
        writter.log(LogStatus.INFO, "wait for username");
        FuntionLibrary.typeaction(driver1, "id", "username", "admin");
        writter.log(LogStatus.INFO, "entered data for username");
        FuntionLibrary.waitForElement(driver1, "id", "password", "10");
        writter.log(LogStatus.INFO, "wait for the password");
        FuntionLibrary.typeaction(driver1, "id", "password", "master");
        writter.log(LogStatus.INFO, " entered the the vaule for the password");
//        Thread.sleep(5000);
        FuntionLibrary.waitForElement(driver1, "id", "btnsubmit", "10");
        writter.log(LogStatus.INFO, "wait for login button");
        FuntionLibrary.clickaction(driver1, "id", "btnsubmit");
        writter.log(LogStatus.INFO, "click on login ");
        FuntionLibrary.close(driver1);
        writter.log(LogStatus.PASS, "execution successful");
        report.endTest(writter);
        report.flush();
	}

}
