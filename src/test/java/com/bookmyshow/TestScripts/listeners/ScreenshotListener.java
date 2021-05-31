package com.bookmyshow.TestScripts.listeners;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_base;
import java.io.File;
import java.util.Date;




public class ScreenshotListener extends GUI_automation_base implements ITestListener  {

	private static WebDriver driver;
	private static String fileSeperator = System.getProperty("file.separator");
	public static File targetFile;
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");

		driver = _driver;

		String testClassName = getTestClassName(result.getInstanceName()).trim();

		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";

		if (driver != null) {
			String imagePath = ".." + fileSeperator + "Screenshots"
					+ fileSeperator + "Results" + fileSeperator + testClassName
					+ fileSeperator
					+ takeScreenShot(driver, screenShotName, testClassName);
			reportLogScreenshot(targetFile);
			System.out.println("Screenshot can be found : " + imagePath);
		}
	}
	
	
	
	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

	public static String takeScreenShot(WebDriver driver,
			String screenShotName, String testName) {
		try {
			File file = new File("Screenshots" + fileSeperator + "Results");
			if (!file.exists()) {
				System.out.println("File created " + file);
				file.mkdir();
			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + testName, screenShotName);
			FileUtils.copyFile(screenshotFile, targetFile);
			

			return screenShotName;
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
			return null;
		}
	}
	
	
	
	public static void reportLogScreenshot(File file) {
	      System.setProperty("org.uncommons.reportng.escape-output", "false");
	  
	      String absolute = file.getAbsolutePath();
	      //System.out.println("Absolute path:"+file.getAbsolutePath());
	      //System.out.println("AbsoluteFile:"+file.getAbsoluteFile());
	      int beginIndex = absolute.indexOf(".");
	      String relative = absolute.substring(beginIndex).replace(".\\","");
	      String screenShot = relative.replace('\\','/');
	     // String pngName = file.getAbsoluteFile().toString();
	     // String afinalPng = pngName.replace(".png.png",".png");
	     // System.out.println("Png name:"+afinalPng);
	      
	Reporter.log("<a href=\""+file.getAbsoluteFile()+"\"><p align=\"left\">Error screenshot at " + new Date()+ "</p>");
	Reporter.log("<p><img width=\"500\" src=\"" + file.getAbsoluteFile()  + "\" alt=\"screenshot at " + new Date()+ "\"/></p></a><br />"); 
	}
	
	////@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
}