package com.bookmyshow.TestScripts.pages;

import java.awt.Robot;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_GeneralMeths;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_properties;


public class ShowTimeSelectionPage extends GUI_automation_GeneralMeths {

	WebElement AshowTime;

	//private String _pageURL;
	protected WebDriver _driver;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	private static Logger Log = Logger.getLogger("devLogger");
	

	public ShowTimeSelectionPage(final WebDriver driver) {
		super(driver);
		_driver = driver;
		
	}

	//	@FindBy(xpath="//div[@data-type='showtimes']")
	//	WebElement showTimes;

	@FindBy(xpath="//div[@data-type='prices']")
	WebElement priceDropDown;

	@FindBy(xpath="//label[@for='filter-60-100']")
	WebElement price60100;

	@FindBy(xpath="//label[@for='filter-101-200']")
	WebElement price100200;

	@FindBy(xpath="//label[@for='filter-201-300']")
	WebElement price200300;

	@FindBy(xpath="//a[@class='__venue-name']//strong")
	List<WebElement> listOfTheatres;

	@FindBy(xpath="//a[@class='__venue-name']//strong")
	List<WebElement> showTimings;


	//	@FindBy(xpath="//div[@data-type='showtimes']")
	//	WebElement showTimes;

	public String getTitle(){
		return _driver.getTitle();
	}


	public void setlectMovieDate(int Mdate) throws Exception{
		Log.info("SELECTING THE MOVIEDATE ");
		try{
									
		_driver.findElement(By.xpath("//*[@id='showDates']//div[text()='"+String.format("%02d", Mdate)+"']")).click();
	}
		catch(Exception e){
			throw new Exception("FAILED WHILE SELECTING THE MOVIEDATE " + "\n getHomePageText " +e.getLocalizedMessage());
		}
	}

	public void selectPriceRange(){
		priceDropDown.click();
		price60100.click();
		price100200.click();
		price200300.click();
	}

	public void selectTheatreAndTime(String TheatreName,int showtm) throws Exception{
		Log.info("SELECTING THE THEATRE AND TIME FOR THE MOVIE ");
		try{
		boolean NoShowTimeAvail=true;
		boolean NoMovietheatreAvail=true;
		boolean NoShowTime=true;
		int count=0;
		int shtime;
		List<WebElement> TheatreList = _driver.findElements(By.xpath("//a[@class='__venue-name']//strong"));
		if(TheatreList.size() < 1)
			Assert.fail("There is no Movie Theatre that is showing this movie");
		for (int i=0; i<TheatreList.size(); i++) {
			if(TheatreList.get(i).getText().contains(TheatreName)){
				NoMovietheatreAvail=false; 
				//	WebElement DesiredTheatre = TheatreList.get(i);
				List<WebElement> showTimes = _driver.findElements(By.xpath("//li[contains(@data-name,'"+TheatreName+"')]//div[@data-online='Y']"));
				if(showTimes.size()<1)
					Assert.fail("No Active Showtimes available ");
				for (int j = 0; j < showTimes.size(); j++) {
					String times = showTimes.get(j).getText();
					if(times.endsWith("PM")){
						NoShowTimeAvail=false;
						String newtimewithsuffix = times.replaceAll("[:]","");
						//System.out.println("The PM time after suffix change is: "+newtimewithsuffix.substring(0,3));
						shtime = Integer.parseInt(newtimewithsuffix.substring(0,4));
						//System.out.println("The time is:" +shtime);

						//Adding 1200 hrs for PM
						int newshtime = shtime+1200;					
						if (newshtime < 2390 && newshtime >= 1830 ){
							NoShowTime = false;

							//							JavascriptExecutor jse = (JavascriptExecutor)driver;
							//							jse.executeScript("")
							//System.out.println(""+showTimes.get(j));
							waitForAnElementToBeClickable(By.xpath("//li[contains(@data-name,'"+TheatreName+"')]//div[@data-online='Y']//a[contains(.,'"+showTimes.get(j).getText()+"')]"), 15);
							javaScriptClick(By.xpath("//li[contains(@data-name,'"+TheatreName+"')]//div[@data-online='Y']//a[contains(.,'"+showTimes.get(j).getText()+"')]"));
							//							showTimes.get(j).click();
							//Actions mouse=new Actions(_driver);
							//mouse.moveToElement(showTimes.get(j)).click().build().perform();
							count++;
							if (count==1)
								break;
						}	

					}
					else if(times.endsWith("AM")){
						String newtimewithsuffix = times.replaceAll("[:]","");
						//System.out.println("The AM time after suffix change is: "+newtimewithsuffix.substring(0,3));
						shtime = Integer.parseInt(newtimewithsuffix.substring(0,4));
						//System.out.println("The time is:" +shtime);
					}
					else {
						Assert.fail("The movie time is not selected");
					}
				}			
			}
			
		}
		if(NoMovietheatreAvail){
			Assert.fail("The Theatre that you have selected is not screening the movie");
		}
		if(NoShowTimeAvail){
			Assert.fail("The Evening ShowTime is not available for this show.");
		}
		if(NoShowTime){
			Assert.fail("No Evening Show is available for the selected Date and Theatre ");
		}
		
	}
		catch(Exception e){
			throw new Exception("FAILED WHILE SELECTING THE THEATRE AND TIME FOR THE MOVIE " + "\n getHomePageText " +e.getLocalizedMessage());
		}

	}

	public void selectNumberOfSeats(int nOfSeats) throws Exception{
		Log.info("SELECTING THE NUMBER OF SEATS FOR THE MOVIE ");
		try{
		waitForAnElementToBeVisible(By.xpath("//ul[@id='popQty']//li[@id='pop_"+nOfSeats+"']"),15);
		_driver.findElement(By.xpath("//ul[@id='popQty']//li[@id='pop_"+nOfSeats+"']")).click();
		}
		catch(Exception e){
			throw new Exception("FAILED WHILE SELECTING THE NUMBER OF SEATS FOR THE MOVIE " + "\n getHomePageText " +e.getLocalizedMessage());
		}
	}
}

