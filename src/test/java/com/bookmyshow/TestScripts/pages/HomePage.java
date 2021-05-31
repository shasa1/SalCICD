package com.bookmyshow.TestScripts.pages;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookmyshow.Constants.Constant;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_GeneralMeths;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_properties;
import java.awt.event.KeyEvent;

public class HomePage extends GUI_automation_GeneralMeths {

	private static final String movietoSelect = "//div[contains(@data-search-filter,'movies-"+Constant.movieName+"')]//div[@class='book-button']//a" ;
	private static Logger Log = Logger.getLogger("devLogger");
	protected WebDriver _driver;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	public HomePage(final WebDriver driver){
		super(driver);
		_driver = driver;
		System.out.println(" movie to select:"+ movietoSelect);
	}

	@FindBy(css=".fl h1")
	WebElement welcometxt;

	//@FindBy(xpath=movietoSelect)
	//WebElement bookNowButton;

	@FindBy(xpath="//*[text()='View Recent Transactions']")
	WebElement viewTransactionLink;

	/* Variables*/
	String expectedPagetxtvalue= null;

	public String getHomePageText() throws Exception{
		Log.info("GETING THE HOMEPAGE TEXT ");
		try{
			expectedPagetxtvalue = welcometxt.getText();
		}
		catch(Exception e){
			throw new Exception("FAILED GETING THE HOMEPAGE TEXT  " + "\n getHomePageText " +e.getLocalizedMessage());
		}
		return expectedPagetxtvalue;
	}

	public void bookMovietickets() throws Exception{
		Log.info("SCROLLING THE HOMEPAGE WINDOW ");
		try{
			waitForAnElementToBeClickable(By.className("mv-section-head"),5);
			_driver.findElement(By.className("mv-section-head")).click();
			JavascriptExecutor jse = (JavascriptExecutor)_driver;
			//jse.executeScript("window.scrollBy(0,500)", "");
			jse.executeScript("scroll(0,450);");
			//jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			
			//((JavascriptExecutor)_driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//*[@placeholder='Search for Movies']")));
		}
		catch (Exception e){
			throw new Exception("FAILED WHILE SCROLLING THE HOMEPAGE WINDOW  " + "\n bookMovietickets " +e.getLocalizedMessage());
		}
		try{
			Log.info("CLICKING THE DESIRED MOVIE TO SELECT ");
	//		WebDriverWait wait = new WebDriverWait(_driver, 10);
		//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(movietoSelect)));
			waitForAnElementToBeClickable(By.xpath(movietoSelect),5);
			javaScriptClick(By.xpath(movietoSelect));
		}
		catch(Exception e){
			throw new Exception("FAILED WHILE CLICKING THE DESIRED MOVIE TO SELECT  " + "\n bookMovietickets " +e.getLocalizedMessage());
		}
	}
}
