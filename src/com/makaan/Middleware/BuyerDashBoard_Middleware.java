package com.makaan.Middleware;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.makaan.Dictionary.BuyerDashboard;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;

import java.io.IOException;

public class BuyerDashBoard_Middleware {

	public static Webhelper wb = null;
	public static BuyerDashboard dict = null;
	public static WebDriver driver = null;;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");

	public BuyerDashBoard_Middleware() {
		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();

		System.out.println("inside Middleware Constructor");

	}

	public String OpenURL() throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		String URL = ReadSheet("Login", "URL", 2);
		wb.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		res = wb.Get_Response(URL);
		System.out.println("Response code got from URL " + res);
		if (res == 200) {
			System.out.println("Waiting till Makaan logo found on page");
			wb.WaitUntillVisiblility(dict.MakaanLogo);
		} else {
			return ("Fail in Geeting Response 200 from URL" + URL);
		}
		return ("Passed in getting Response 200 from URL" + URL);
	}

	public static String ReadSheet(String Sheet, String Col_Name, int row_id)
			throws IOException, NoSuchElementException, TimeoutException {

		String data = xls.getCellData(Sheet, Col_Name, row_id);
		System.out.println("Data from sheet " + data);

		return (data);
	}

	public void CloseBrowser() {
		db.Close();
		wb.CloseBrowser();

	}

	public String SearchVerify() throws IOException, InterruptedException, NoSuchElementException, TimeoutException {
		int count = 0;
		System.out.println("This test will verify existance of Makaan IQ and Alliances Card on Search TAb");
		wb.ClickbyXpath(dict.BuyerJourney);
		String URL = wb.CurrentURL();
		int res = wb.Get_Response(URL);
		ArrayList<String> arr = new ArrayList();
		if (res == 200) {
			if (wb.IsElementPresent(dict.MyjourneyTab) && (wb.IsElementPresent(dict.PartnerTab))) {
				wb.ClickbyXpath(dict.MyjourneyTab);
				wb.ClickbyXpath(dict.SavedSearchTab);
				Thread.sleep(1000);
				if (wb.IsElementPresent(dict.FindSearchCard)) {
					try {

						if (wb.IsElementPresent(dict.AlliancesCard)) {
							wb.ClickbyXpath(dict.AlliancesCard);
							wb.WaitUntillVisiblility(dict.CityPopup);
							Thread.sleep(1000);
							wb.Back();
						} else {
							ArrayList<WebElement> arrlist = new ArrayList();
							arrlist = wb.getElements(".//a[@data-type='track-article']");
							for (int i = 0; i < arrlist.size(); i++) {

								String result = SwitchWindow((arrlist.get(i).toString()) + "[" + i + 1 + "]",
										dict.MakaanIQ, "iq");
								if (result.contains("Pass")) {
									System.out.println("Saved Search is validate of makaan Iq cards");
								} else {
									count++;
								}
							}
						}
					} catch (Exception e) {
						ArrayList<WebElement> arrlist = new ArrayList();
						arrlist = wb.getElements(dict.Cards);
						for (int i = 0; i < arrlist.size(); i++) {

							String result = SwitchWindow((arrlist.get(i).toString()) + "[" + i + 1 + "]", dict.MakaanIQ,
									"iq");
							if (result.contains("Pass")) {
								System.out.println("Saved Search is validate of makaan Iq cards");
							} else {
								count++;
							}
						}
					}
					if (count > 0) {
						return ("Fail: MakaanIq cards are not validated for saved search" + res);
					}
				} else {
					return ("Fail: Find Your Search card was not present");
				}
			} else {
				return ("Fail: My Journey or Alliance Tab was not present in Buyer Journey ");
			}

		} else {
			return ("Fail: response of " + URL + "is " + res);
		}
		return ("Saved search Makaan IQ and alliances cards are verified successfully");

	}

	public String SwitchWindow(String webElement, String Element, String Value)
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		int res = 0;
		driver = wb.getDriver();
		String parentHandle = driver.getWindowHandle();

		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElement)));
		driver.findElement(By.xpath(webElement)).click();
		Thread.sleep(2000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		String URL = driver.getCurrentUrl();
		res = wb.Get_Response(URL);
		System.out.println("URL of opened window is " + URL);
		if (res == 200) {
			System.out.println("response code from " + URL + "is " + res);
			if (URL.contains(Value)) {
				wb.WaitUntillVisiblility(Element);
				if (wb.IsElementPresent(Element)) {
					System.out.println("Element is present on page");
				} else {
					driver.close();
					driver.switchTo().window(parentHandle);
					return ("Fail: Element was not present in URL " + URL + Element);
				}
			} else {
				driver.close();
				driver.switchTo().window(parentHandle);
				return ("Fail: URL did not contain desired value " + URL + "Value is " + Value + "Elemnt is" + Element);
			}
		} else {
			driver.close();
			driver.switchTo().window(parentHandle);
			return ("Fail: response code from " + URL + "is " + res + Element);
		}
		driver.close();
		driver.switchTo().window(parentHandle);
		return ("Pass: Verify Successfully");
	}

}
