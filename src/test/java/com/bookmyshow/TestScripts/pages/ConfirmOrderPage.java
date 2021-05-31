package com.bookmyshow.TestScripts.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_GeneralMeths;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_properties;
import com.bookmyshow.Constants.Constant;


public class ConfirmOrderPage extends GUI_automation_GeneralMeths {


	protected WebDriver _driver;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	private static Logger Log = Logger.getLogger("devLogger");
	
	public ConfirmOrderPage(final WebDriver driver) {
		super(driver);
		_driver = driver;
	
	}

	//@FindBy(xpath="//*[@id='shmticket']//*[@class='type-details']")
	//WebElement mticketsRadio;


	@FindBy(id="prePay")
	WebElement proceedToPay;

	public void clickOnMticketsandProceed() throws Exception{
		Log.info("CLICKING ON MTICKETS AND PROCEEDING ");
		try{
		JavascriptExecutor executor = (JavascriptExecutor)_driver;
		//	Actions actions = new Actions(_driver);
		WebDriverWait wait = new WebDriverWait(_driver, 10);
		WebElement mticketsElem = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id='mticket']/..//label[text()='M-Ticket']"))));
		Thread.sleep(2000);
		executor.executeScript("arguments[0].click();", mticketsElem);

		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='mticket']/..//label[text()='M-Ticket']")));	
		//	List<WebElement> ListMtickets = _driver.findElements(By.xpath("//*[@type='radio' and @id='mticket']"));
		//_driver.findElement(By.xpath("//*[@id='mticket']/..//label[text()='M-Ticket']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(proceedToPay));	
		proceedToPay.click();

		if(Constant.AdultMovie)
			_driver.findElement(By.id("btnAduPopupAccept")).click();
	}
		catch(Exception e){
			throw new Exception("FAILED WHILE CLICKING ON MTICKETS AND PROCEEDING " + "\n getHomePageText " +e.getLocalizedMessage());
		}

	}
}
