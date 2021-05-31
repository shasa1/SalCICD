package com.bookmyshow.TestScripts.BaseClasses;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.bookmyshow.Constants.Constant;
import com.bookmyshow.TestScripts.listeners.ScreenshotListener;

public class GUI_automation_base {

	public static WebDriver _driver;
	public static RemoteWebDriver driver;

	private static String URL = null;
	private static String fileSeperator = System.getProperty("file.separator");
	private static String BROWSER = null;
	public  void onTestFailure(ITestResult result)
	{

	}
	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	DesiredCapabilities capabilities = new DesiredCapabilities();

	/**
	 * JUnit setup should call this via
	 * 
	 * super.setUp()
	 * 
	 * By doing so, the mentioned Driver for WebDriver will be instantiated.
	 * 
	 * @throws Exception
	 */
	public void setUp(final String BROWSER) throws Exception {

		if (BROWSER.contentEquals("firefox")){
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAssumeUntrustedCertificateIssuer(false);
			String path = "D:\\Downloads_sel";
	        profile.setPreference("browser.download.folderList", 2);
	        profile.setPreference("browser.download.dir", path);
	        profile.setPreference("browser.download.alertOnEXEOpen", false);
	        profile.setPreference("browser.helperApps.neverAsksaveToDisk", "application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
	        profile.setPreference("browser.download.manager.showWhenStarting", false);
	        profile.setPreference("browser.download.manager.focusWhenStarting", false);
	        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
	        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
	        profile.setPreference("browser.download.manager.closeWhenDone", false);
	        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
	        profile.setPreference("browser.download.manager.useWindow", false);
	        profile.setPreference("browser.download.manager.showWhenStarting", false);
	        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
	        profile.setPreference("pdfjs.disabled", true);
			_driver = new FirefoxDriver(profile);
		}else if (BROWSER.contentEquals("internetExplorer")){
			System.setProperty("webdriver.ie.driver", "InputTestData/IEDriverServer.exe");
			_driver = new InternetExplorerDriver();
		}else if (BROWSER.contentEquals("chrome")){
			System.setProperty("webdriver.chrome.driver", "InputTestData/chromedriver.exe");
			String downloadFilepath = "/path/to/download";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("intl.accept_languages", "nl");
			chromePrefs.put("disable-popup-blocking", "true");
			chromePrefs.put("download.prompt_for_download",true);
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			_driver = new ChromeDriver(options);
		}
		
//		if (BROWSER.equalsIgnoreCase("remote")){
//			URI asd = new URI("http://localhost:4444/wd/hub");
//			driver = new RemoteWebDriver(asd, capabilities);
//		}


		// --- choose one ------------------

		// _driver = new HtmlUnitDriver(true);

		_driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//_driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS);
		_driver.manage().window().maximize();
	}

	/**
	 * Build up the URL via the properties file.
	 * 
	 * @return
	 */
	public static String getUrl() {
		// TODO: EY: Should this be done in the constructor instead?
		if (URL == null) {
			try {
				URL = _properties
						.getProperty(GUI_automation_properties.BASEURL);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			assert URL.contains("http"); // should look like a URL
		}
		return URL;
	}

	/**
	 * Build up the BROWSER via the properties file.
	 * 
	 * @return
	 */
	public static String getBrowser() {
		// TODO: EY: Should this be done in the constructor instead?
		if (BROWSER == null) {
			try {
				BROWSER = _properties
						.getProperty(GUI_automation_properties.BROWSER);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return BROWSER;
	}

	public static void WaitForAJAX(){

		if(_driver.findElement(By.xpath("//*[@id='dBusy']//p")).isDisplayed()){
			WebDriverWait wait = new WebDriverWait(_driver, 25);
			wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//*[@id='dBusy']//p"))));
		}
	}
	
	
	public void ignoreDownloadWindow() throws Exception{
	
		waitexplicitly(1000);
		new Actions(_driver).sendKeys(Keys.CANCEL).build().perform();
		//rob.keyPress(KeyEvent.VK_ESCAPE);
		//rob.keyRelease(KeyEvent.VK_ESCAPE);
	}

	
	public void getCurrentWindowFocus(){
		_driver.switchTo().defaultContent();
	}
	
	public void waitForVisibility(final By theElement,int timeoutInSeconds) {
		try {
			
			WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(theElement));
		} catch (Exception e) {
			Reporter.log("There was an issue in finding the webelement, " + theElement.getClass() + e.getMessage());
			e.printStackTrace();
		}
	}

	public void waitexplicitly(int timeinSeconds) throws Exception{
		int tm = timeinSeconds * 1000;
		Thread.sleep(tm);
	}

	public void takeScreenShot(){

		//	ScreenshotListener sl = new ScreenshotListener();
		String testClassName = "MovieTickets";
		String screenShotName = ""+Constant.movieName+"Screen" + ".png";

		if (_driver != null) {
			String imagePath = ".." + fileSeperator + "Screenshots"
					+ fileSeperator + "Results" + fileSeperator + testClassName
					+ fileSeperator
					+ ScreenshotListener.takeScreenShot(_driver, screenShotName, testClassName);
			System.out.println("Screenshot can be found : " + imagePath);
		}
	}

	public void waitForAnElementToBeVisible(final By theElement,int timeoutInSeconds) {
		try {
			WebElement element = _driver.findElement(theElement);
			WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Reporter.log("There was an issue in finding the webelement, " + theElement.getClass() + e.getMessage());
			e.printStackTrace();
		}
	}

	public void waitForAnElementToBeClickable(final By theElement,int timeoutInSeconds) {
		try {
			WebElement element = _driver.findElement(theElement);
			WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			Reporter.log("There was an issue in finding the webelement, " + theElement.getClass() + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void javaScriptClick(By theElement) {
		WebElement element = _driver.findElement(theElement);
		((JavascriptExecutor) _driver).executeScript("arguments[0].click();", element);
	}


	public boolean isElementPresent(By by) {
		try {
			_driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public  String getText(By theElement) {

		WebElement element = _driver.findElement(theElement);
		return element.getText().trim();
	}

	public  String getCurrentLocation() {
		return _driver.getCurrentUrl().trim();
	}

	public  void switchToDefaultFrame() {
		_driver.switchTo().defaultContent();
	}

	public  void switchToFrame(String id) {
		_driver.switchTo().frame(id);
	}

	public  void switchToFrame(By theElement) {
		WebElement element = _driver.findElement(theElement);
		_driver.switchTo().frame(element);
	}

	public  void refreshPage() {
		_driver.navigate().refresh();
	}

	public boolean isTextPresent(String textValue) {
		// Reporter.log("Looking for the element...: " + textValue);
		System.out.println("Looking for the element...: " + textValue);
		WebElement webElement = _driver.findElement(By.tagName("html"));
		if (webElement.getText().contains(textValue)) {
			return true;
		} else {
			System.out.println("Element not found : " + textValue);
			return false;
		}
	}
	
	public void selectWindow(int window) {
		Set<String> winNames = _driver.getWindowHandles();
		_driver.switchTo().window((String) winNames.toArray()[window - 1]);
	}
	
	public  void mouseover(By theElement) {
		//new Actions(_driver).moveToElement(theElement).perform();
		new Actions(_driver).moveToElement((WebElement) theElement).build().perform();
	}
	
	public void selectDropDown(By theSelectElement, String valToSelect) {
		WebElement element = _driver.findElement(theSelectElement);
		Select select = new Select(element);
		// Get a list of the options
		List<WebElement> options = select.getOptions();
		// For each option in the list, verify if it's the one you want and then
		// click it
		for (WebElement we : options) {
			if (we.getText().equals(valToSelect)) {
				we.click();
				break;
			}
		}

	}

		public String selectedItem(By theSelectElement) {
		WebElement element = _driver.findElement(theSelectElement);
		Select select = new Select(element);
		WebElement anElement = select.getFirstSelectedOption();
		return anElement.getText();
	}
		
		
		public boolean isElementChecked(By theElement) {
			WebElement element = _driver.findElement(theElement);
			boolean retValue = false;
			if (element.isSelected()) {
				retValue = true;
			}
			return retValue;
		}
		
		public  void selectWindowCancel(int window) throws Exception {
			//Logger.info("Inside the select window cancel");
			String parentWindowHandle = _driver.getWindowHandle();
			Set<String> winNames = _driver.getWindowHandles();
			_driver.switchTo().alert().dismiss();
			Thread.sleep(3000);
			_driver.switchTo().window(parentWindowHandle); 
		}
		
		public void dragDrop(By element, By target) {
			WebElement elementSource = _driver.findElement(element);
			WebElement elementDestination = _driver.findElement(target);
			(new Actions(_driver)).dragAndDrop(elementSource, elementDestination).perform();

		}
		
		public  void deleteCookies() {
			_driver.manage().deleteAllCookies();
		}
		
		public  void AcceptAlert() {
			Alert alert = _driver.switchTo().alert();
			alert.accept();

		}
		
		public void closePresentWindow() throws Exception {
	        try {
	            _driver.close();
	        } catch (Exception e) {
	            throw new Exception(
	                    "ISSUE IN CLOSING THE 'window'" + "\nMETHOD:clickOnCloseWindow\n" + e
	                            .getLocalizedMessage());
	        }
	    }
		
		public void closePopUp() throws InterruptedException{
			String parent = _driver.getWindowHandle();
			Set<String>pops=_driver.getWindowHandles();
	        {
	        Iterator<String>it = pops.iterator();
	        while (it.hasNext()) {
	            String popupHandle=it.next().toString();
	            if(!popupHandle.contains(parent))
	            {
	            _driver.switchTo().window(popupHandle);
	            System.out.println("Popu Up Title: "+ _driver.switchTo().window(popupHandle).getTitle());
	            _driver.close();
	            Thread.sleep(5000);
	            }
	        }
		}
		}
		
		public void selectByIndex(By element, int index) {
			WebElement element1 = _driver.findElement(element);
			Select select = new Select(element1);
			List<WebElement> options = select.getOptions();
			int size = options.size();
			if (size > 0) {
				select.selectByIndex(index);
			}
		}
		
		public  void clearAndEnterValueInTextBox(By by, String value) throws InterruptedException{
			WebElement textBox = _driver.findElement(by);
			Assert.assertTrue(textBox.isEnabled(), "Text Box is enabled");
			textBox.sendKeys(Keys.CONTROL + "a");
			textBox.sendKeys(Keys.DELETE);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			textBox.sendKeys(value);

		}
		
		public void handleAlert() {
			try {
				WebDriverWait wait = new WebDriverWait(_driver, 60);
				boolean alertPresent = false;
				if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
					alertPresent = false;
				} else {
					alertPresent = true;
				}
				if (alertPresent) {
					Alert alert = _driver.switchTo().alert();
					alert.accept();
				}
			} catch (UnhandledAlertException e) {

			}
		}
		
		
		public void switchToNewWindow() throws Exception {
			for (String winHandle : _driver.getWindowHandles()) {
				_driver.switchTo().window(winHandle);
			}
		}

		public  String getWindowName() throws Exception {
			String windowName = _driver.getWindowHandle();
			return windowName;
		}

		public  boolean isChildWindowPresent() throws Exception {
			Set<String> windows = _driver.getWindowHandles();
			int size = windows.size();
			return size > 1;
		}

		public  void switchToParentWindow(String windowName) throws Exception {
			windowName = windowName.toString();
			_driver.switchTo().window(windowName);
			Thread.sleep(3000);
		}
		
		public  String getPageTitle() {
			return _driver.getTitle().trim();
		}
		
		public  void compareTwoStrings(String Actual, String Expected, String Message) {
			Assert.assertEquals(Actual, Expected, Message);
		}
	
}

