package com.bookmyshow.TestScripts.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_GeneralMeths;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_properties;

public class SeatSelectionPage extends GUI_automation_GeneralMeths {

	//	private String _pageURL;
	protected WebDriver _driver;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	private static Logger Log = Logger.getLogger("devLogger");

	public SeatSelectionPage(final WebDriver driver) {
		super(driver);
		_driver = driver;
	}

	@FindBy(xpath="//a[@id='cntbook']//span[text()='Proceed']")
	WebElement proceed_button ;

	@SuppressWarnings("unused")

	public void selectTheSeatsForMovie(int nOfSeats) throws Exception{

		Log.info("SELECTING THE SEATS FOR THE MOVIE ");
		try{
			//		int num = 0;
			//		int num2 =0;
			//int seat_nr=0;
			//		int st_no=0;
			waitForAnElementToBeVisible(By.xpath("//div[contains(@id,'B_')]"),15);
			waitForAnElementToBeVisible(By.xpath("//div[contains(@id,'C_')]"),15);
			List<WebElement> seatLocation = _driver.findElements(By.xpath("//div[contains(@id,'B_')]"));
			List<WebElement> seatLocation2 = _driver.findElements(By.xpath("//div[contains(@id,'C_')]"));
			if(seatLocation.size()>nOfSeats){
				for (int i=0;i<seatLocation.size();i++) {
					//	seat_nr = Integer.parseInt(seatLocation.get(i).getText());
					seatLocation.get(i).click();
					break;

					//				for(int j=0;j<i;j++){
					//					num = seat_nr;
					//					num++;
					//				}
					//				st_no = i;
					//			}
					//			if(++num != seat_nr){
					//				List<WebElement> seatLocation2= _driver.findElements(By.xpath("//div[contains(@id,'B_2')]"));
					//				if(seatLocation2.size()>nOfSeats){
					//					seatLocation2.get(st_no).click();
					//				}
					//			}
					//			else{
					//				seatLocation.get(st_no).click();
					//				
					//
					//			}

				}
			}
			else{
				if(seatLocation2.size()>nOfSeats){
					for (int i=0;i<seatLocation2.size();i++) {
						//seat_nr = Integer.parseInt(seatLocation2.get(i).getText());
						seatLocation2.get(i).click();
						break;
					}
				}
				else{
					Assert.fail("There are not Enough Seats to book");
				}
			}
		}
		catch(Exception e){
			throw new Exception("FAILED WHILE SELECTING THE SEATS FOR THE MOVIE " + "\n getHomePageText " +e.getLocalizedMessage());
		}
	}
	public void clickOnProceed() throws Exception{
		Log.info("CLICKING ON PROCEED AFTER SELECTING THE SEATS ");
		try{
		waitForAnElementToBeClickable(proceed_button, 5);
		proceed_button.click();
	}
		catch(Exception e){
			throw new Exception("FAILED WHILE CLICKING ON PROCEED AFTER SELECTING THE SEATS " + "\n getHomePageText " +e.getLocalizedMessage());
		}
	}



}



