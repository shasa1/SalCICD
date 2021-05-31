package com.bookmyshow.TestScripts;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.bookmyshow.Constants.Constant;
import com.bookmyshow.TestScripts.BaseClasses.GUI_automation_base;
import com.bookmyshow.TestScripts.pages.BillDetailsPage;
import com.bookmyshow.TestScripts.pages.ConfirmOrderPage;
import com.bookmyshow.TestScripts.pages.DisplayFirstPage;
import com.bookmyshow.TestScripts.pages.HomePage;
import com.bookmyshow.TestScripts.pages.LoginPage;
import com.bookmyshow.TestScripts.pages.SeatSelectionPage;
import com.bookmyshow.TestScripts.pages.ShowTimeSelectionPage;

public class TestBookingFlow extends GUI_automation_base{

	private DisplayFirstPage _register;
	
	//Page Objects
	
	LoginPage lPage;
	HomePage hp;
	ShowTimeSelectionPage stsp;
	SeatSelectionPage ssp;
	ConfirmOrderPage cop;
	BillDetailsPage bdp;

	@BeforeMethod
	public void setUp() throws Exception{
		super.setUp(getBrowser());
		//PropertyConfigurator.configure("config/log4j.properties");
		_register = PageFactory.initElements(_driver, DisplayFirstPage.class);
	}

	@Test//(dataProvider="LoginTest")
	public void testBookMovieTickets() throws Exception{

		_register.navigateToPageAndWait();
		
		//goto Sign In page
		_register.gotoLoginPage();

		//do Login
		lPage = new LoginPage(_driver);
		PageFactory.initElements(_driver,lPage);
		//lPage.doLogin(Constant.username,Constant.password);
		
		//get the title and assert if its correct		
		String LoginPageTitle = lPage.getTitle();
		Assert.assertEquals("Pune Movie Tickets Online Booking & Showtimes near you - BookMyShow", LoginPageTitle,"The Login Has failed due to Invalid Credentials");
//	
//		waitexplicitly(3);
//		
//		//CLick and Book the desired movie
//		hp = new HomePage(_driver);
//		PageFactory.initElements(_driver, hp);
//		hp.bookMovietickets();
//		
//		getCurrentLocation();
//		//Select the theatre and Time of the movie
//		stsp = new ShowTimeSelectionPage(_driver);
//		PageFactory.initElements(_driver, stsp);
//		stsp.setlectMovieDate(Constant.movieDate);
//		WaitForAJAX();
//		
//		getCurrentLocation();
//		stsp.selectTheatreAndTime(Constant.TheatrName, 1600);
//		waitexplicitly(2);
//		stsp.selectNumberOfSeats(Constant.noOfSeats);
//
//		//Select the Seats for the movie
//	    ssp = new SeatSelectionPage(_driver);
//		PageFactory.initElements(_driver, ssp);
//		WaitForAJAX();
//		ssp.selectTheSeatsForMovie(Constant.noOfSeats);
//		//Take the screenShot for the seats that have been booked
//		takeScreenShot();
//		ssp.clickOnProceed();
//		
//		//Confirm the Order and select m-tickets and proceed
//		cop = new ConfirmOrderPage(_driver);
//		PageFactory.initElements(_driver, cop);
//		WaitForAJAX();
//		cop.clickOnMticketsandProceed();
//		
//		//Fill in the CC details to avail the buy one get One Offer
//		bdp = new BillDetailsPage(_driver);
//		PageFactory.initElements(_driver, bdp);
//		WaitForAJAX();
//		bdp.checkTheOffer();
	}
		

	@AfterMethod
	public void tearDown(){
		_driver.close();
		_driver.quit();
	}

}