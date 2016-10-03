package com.makaan.Webhelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Webhelper {

	static WebDriver driver = null;

	public static void InitiateDriver() throws TimeoutException{

		//FirefoxProfile profile = new FirefoxProfile();

		/*profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
		profile.setPreference("startup.homepage_welcome_url", "about:blank");
		profile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
		profile.setPreference("browser.startup.homepage", "about:blank");
		System.setProperty("webdriver.firefox.bin", "/opt/firefox/firefox");
		System.setProperty("webdriver.chrome.driver","/home/surabhi/labs/AutomationBuyerWeb/lib/chromedriver");
		driver = new ChromeDriver();*/
		System.setProperty("webdriver.gecko.driver","/Users/vikas/Documents/workspace/MakaanUI/lib/geckodriver");
		driver = new FirefoxDriver();
		
		//driver.manage().window().setSize(new Dimension(1366, 768));
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		}
  public void GetURL(String URL) {
		System.out.println("opening URL throgh webhelper");
		System.out.println(URL + " url in webhelper");
		driver.get(URL);

		// busyIndicationWait(driver);
		// driver.navigate().refresh();

	}

	public void busyIndicationWait(WebDriver driver) throws TimeoutException {

		new WebDriverWait(driver, 7000)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.className("updating")));
	}

	public void WaitUntillVisiblility(String webElement) throws TimeoutException {

		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElement)));

	}

	public void WaitUntill(String element) throws TimeoutException {
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));

	}

	public void WaitUntillID(String element) throws TimeoutException {
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.id(element)));

	}

	public void WaitUntillIDVisibility(String element) throws TimeoutException {
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
	}

	public boolean IsElementPresent(String element) throws NoSuchElementException {
		if (driver.findElement(By.xpath(element)).isDisplayed()) {
			System.out.println("Element is present " + element);
			return true;
		} else {
			return false;
		}

	}

	public boolean IsElementPresentById(String element) throws NoSuchElementException {
		if (driver.findElement(By.id(element)).isDisplayed()) {
			System.out.println("Element is present " + element);
			return true;
		} else {
			return false;
		}

	}


	public WebDriver getDriver() {
		return driver;
	}
	/*
	 * public HtmlUnitDriver getDriver() { return driver; }
	 */

	public void ClickbyXpath(String webElement) throws NoSuchElementException {
		driver.findElement(By.xpath(webElement)).click();
		System.out.println("inside webhelper click element" + webElement);
	}

	public void DoubleClick(WebElement webElement) throws NoSuchElementException {
		Actions action = new Actions(driver).doubleClick(webElement);
		action.build().perform();
		
	}
	
	public void ClickbyLink(String webElement) throws NoSuchElementException {
		driver.findElement(By.linkText(webElement)).click();
		System.out.println("inside webhelper click element by link" + webElement);
	}

	public void HoverandClick(String HoverPath, String clickPath) throws NoSuchElementException {
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(HoverPath));
		action.moveToElement(we).moveToElement(driver.findElement(By.xpath(clickPath))).click().build().perform();
	}

	public void ClickbyId(String Id) throws NoSuchElementException {
		driver.findElement(By.id(Id)).click();

	}

	public void CloseBrowser() {
		driver.close();
		driver.quit();

	}

	public void enterTextByID(String Path, String Value) throws NoSuchElementException {
		WebElement element = driver.findElement(By.id(Path));
		element.sendKeys(Value);
	}

	public void enterTextByxpath(String Path, String Value) throws NoSuchElementException {
		WebElement element = driver.findElement(By.xpath(Path));
		element.sendKeys(Value);

	}

	public void enterTextByxpath(String Path, int Value) throws NoSuchElementException {
		WebElement element = driver.findElement(By.xpath(Path));

		element.sendKeys(String.valueOf(Value));

	}

	public String getText(String Element) throws NoSuchElementException {
		String text = driver.findElement(By.xpath(Element)).getText();
		System.out.println("Text on page throgh get text is: " + text);
		return text;
	}

	public String getAttribute(String Element, String Tag) throws NoSuchElementException {
		String text = driver.findElement(By.xpath(Element)).getAttribute(Tag);
		System.out.println("Attribute on page throgh get text is:" + text);
		return text;
	}
	public boolean IsElementPresentbyLink(String element) throws NoSuchElementException {
		if (driver.findElement(By.linkText(element)).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean IsElementSelected(String element) throws NoSuchElementException {
		try {
			driver.findElement(By.xpath(element)).isSelected();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<String> GetElementvalues(String Path) throws NoSuchElementException {
		List<WebElement> elements = driver.findElements(By.xpath(Path));
		List<String> str = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			str.add(elements.get(i).getText());
		}
		return str;
	}

	public void Slider(String Path) throws NoSuchElementException {

		WebElement slider = driver.findElement(By.xpath(Path));
		Actions moveSlider = new Actions(driver);
		Action action = moveSlider.dragAndDropBy(slider, 30, 0).build();

		action.perform();

	}

	public void Back() {
		driver.navigate().back();
	}

	public String CurrentURL() {
		String URL = driver.getCurrentUrl();
		return URL;
	}

	public void scrollup(String Path) throws InterruptedException {

		WebElement element = driver.findElement(By.xpath(Path));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(4000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-300)");
		Thread.sleep(4000);

	}

	public void scrolldown() throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
		Thread.sleep(4000);

	}

	public void scrolldown(String Path) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(Path));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);

	}
	
	
	
	public ArrayList<WebElement> getElements(String Path) throws InterruptedException {
		ArrayList<WebElement> arr = new ArrayList();
		arr = (ArrayList<WebElement>) driver.findElements(By.xpath(Path));
		return arr;
	} 
	

	public void Jsclickbyxpath(String Path)throws NoSuchElementException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(Path)));

	}

	public void PageRefresh()
	{
		driver.navigate().refresh();
	}

	public void clearbox(String Path) throws NoSuchElementException {
		driver.findElement(By.xpath(Path)).clear();
	}

	public static int Get_Response(String u) throws IOException {
		int res = 0;
		URL url = new URL(u);
		try {

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("HEAD");

			res = con.getResponseCode();
			System.out.println("rseponse code found is "+res);
			if (res / 100 == 3) {
				System.out.println("response: " + res);
				String location = con.getHeaderField("Location");
				if (location != null) {
					System.out.println("URL Moved to location " + location);
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return res;
	}
	
	public String Splitter(String Text, String Pattern, int Index){
		List<String> elephantList = Arrays.asList(Text.split(Pattern));
		return(elephantList.get(Index));
	}
}
