package com.bookmyshow.TestScripts.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_GeneralMeths;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_base;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_properties;

public class DisplayFirstPage extends GUI_automation_GeneralMeths{

	private String _pageURL;
	protected WebDriver _driver;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	private static Logger Log = Logger.getLogger("devLogger");
	
	public DisplayFirstPage(final WebDriver driver) {
		super(driver);
		_driver = driver;
		_pageURL = GUI_automation_base.getUrl();
	}

	@FindBy(id="preSignIn")
	WebElement signInLink;


	public void navigateToPageAndWait() {
		_driver.get(_pageURL);
	}
	public void gotoLoginPage() throws Exception{
		Log.info("CLICKING ON LOGIN PAGE");
		try{
		//signInLink.click();	
		}
		catch(Exception e){
			throw new Exception("FAILED WHILE CLICKING ON LOGIN PAGE " + "\n getHomePageText " +e.getLocalizedMessage());
		}
	}
	public String getFirstpageTitle(){
		String pageTitle = _driver.getTitle();
		return pageTitle;
	}

	public String getFirstPageUrl(){
		
		return _driver.getCurrentUrl();
	}
}
