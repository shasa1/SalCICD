package com.bookmyshow.TestScripts.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_GeneralMeths;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_properties;
import com.bookmyshow.Constants.Constant;



public class BillDetailsPage extends GUI_automation_GeneralMeths {


	protected WebDriver _driver;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	private static Logger Log = Logger.getLogger("devLogger");

	public BillDetailsPage(final WebDriver driver) {
		super(driver);
		_driver = driver;

	}

	@FindBy(id="txtEmail")
	WebElement emailAdd;

	@FindBy(id="txtMobile")
	WebElement mobileAdd;

	@FindBy(xpath="//*[@id='dContinueContactSec']//a[text()='Continue']")
	WebElement continueButton;

	@FindBy(id="dOffersHeadTitle")
	WebElement collapseOffers;

	@FindBy(id="offTab_cc-dc-nb")
	WebElement selectCCoffer;

	@FindBy(xpath="//*[@id='offTabBody_cc-dc-nb']//b[text()='ICICI Bank Buy 1 Get 1 Offer - Credit']")
	WebElement ccRadioButton;

	@FindBy(id="offCode_cc-dc-nb")
	WebElement ccTextBox;

	@FindBy(id="offApplyBtn_cc-dc-nb")
	WebElement applyButton;


	public void shareContactDetails(){

	}

	public void checkTheOffer() throws Exception{
		Log.info("AVAILING THE BUY 1 GET 1 OFFER ");
		try{

			waitForAnElementToBeVisible(collapseOffers,5);
			collapseOffers.click();
			waitForAnElementToBeClickable(selectCCoffer,5);
			selectCCoffer.click();
			waitForAnElementToBeVisible(ccRadioButton,2);
			ccRadioButton.click();
			waitForAnElementToBeVisible(ccTextBox,2);
			ccTextBox.sendKeys(Constant.ccDetails);

			applyButton.click();
		}
		catch(Exception e){
			throw new Exception("FAILED WHILE AVAILING THE BUY 1 GET 1 OFFER " + "\n getHomePageText " +e.getLocalizedMessage());
		}
	}

}
