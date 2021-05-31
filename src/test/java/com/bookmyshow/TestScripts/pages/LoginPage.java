package com.bookmyshow.TestScripts.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_GeneralMeths;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_properties;

public class LoginPage extends GUI_automation_GeneralMeths{

	//	private String _pageURL;
	protected WebDriver _driver;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	private static Logger Log = Logger.getLogger("devLogger");

	public LoginPage(final WebDriver driver) {
		super(driver);
		_driver = driver;
		PropertyConfigurator.configure("config/log4j.properties");
	}

	@FindBy(id="iUserName")
	WebElement usernameTxtbox;

	@FindBy(id="iPwd")
	WebElement passwordTxtbox;

	@FindBy(xpath="//div[text()='SIGN IN']")
	WebElement loginButton;

	public void doLogin(String uname,String pass) throws Exception{
		Log.info("DOING LOGIN ");
		try{
			//waitForAnElementToBeVisible(usernameTxtbox, 2);
			usernameTxtbox.sendKeys(uname);
			//waitForAnElementToBeVisible(passwordTxtbox, 2);
			passwordTxtbox.sendKeys(pass);
			//waitForAnElementToBeVisible(loginButton, 2);
			loginButton.click();
		}
		catch(Exception e){
			throw new Exception("FAILED WHILE DOING LOGIN " + "\n getHomePageText " +e.getLocalizedMessage());
		}
	}
	public String getTitle() throws Exception{
		Log.info("GETTING THE TITLE FROM LOGINPAGE ");
		try{
			return _driver.getTitle();
		}
		catch(Exception e){
			throw new Exception("FAILED WHILE GETTING THE TITLE FROM LOGINPAGE " + "\n getHomePageText " +e.getLocalizedMessage());
		}
	}
}
