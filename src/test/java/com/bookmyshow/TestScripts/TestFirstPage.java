package com.bookmyshow.TestScripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_base;
import com.bookmyshow.TestScripts.pages.DisplayFirstPage;


public class TestFirstPage extends GUI_automation_base{

	private DisplayFirstPage _register;

	@BeforeMethod
	public void setUp() throws Exception{
		super.setUp(getBrowser());
		//PropertyConfigurator.configure("config/log4j.properties");
		_register = PageFactory.initElements(_driver, DisplayFirstPage.class);
	}
	@Test(enabled=false)
	public void testTitle(){
		//Open the URL
		_register.navigateToPageAndWait();
		//check if on correct URL and page
		Assert.assertEquals("https://in.bookmyshow.com/Mumbai/movies",_register.getFirstPageUrl(),"The URL of the FirstPage is not correct");
	//	Assert.assertEquals("Altoro", _register.getFirstpageTitle(),"The title is not correct");
			
	}
	
	@AfterMethod
	public void tearDown(){
		_driver.close();
		_driver.quit();

//		String verificationErrorString = verificationErrors.toString();
//
//		if (!"".equals(verificationErrorString)) {
//			fail(verificationErrorString);
//		}
	}
	
	
}