package com.makaan.Middleware;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;
import com.makaan.Dictionary.HeaderFooter;

public class HeaderFooter_Middleware {

	public static Webhelper wb = null;
	public static HeaderFooter dict = null;
	public static WebDriver driver = null;;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");

	public HeaderFooter_Middleware() {
		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();

		System.out.println("inside Middleware Constructor");

	}

	public String ValidateHeaderFooter()
			throws InterruptedException, NoSuchElementException, IOException, TimeoutException {
		System.out.println("Inside Test Validate Header Footer from Header FooterMiddleware");
		String Value = null;
		String Result = null;
		wb.scrolldown(dict.Footer);
		Thread.sleep(1000);
		if (wb.IsElementPresent(dict.AboutMakaan)) {
			if (wb.IsElementPresent(dict.PropertiesinIndia)) {
				for (int i = 1; i < 16; i++) {
					Value = wb.getText("(" + dict.PropertiesinIndia + "//a)[" + i + "]");
					Value = wb.Splitter(Value, " ", 1);
					Result = NewTab("(" + dict.PropertiesinIndia + "//a)[" + i + "]", dict.SERPValidate, Value);
					if (Result.contains("Fail")) {
						return Result;
					}
					Thread.sleep(1000);
					wb.scrolldown(dict.Footer);
					Thread.sleep(2000);
				}
			} else {
				return ("Fail: Properties in India Section was not present in footer");
			}
			closechat();
			if (wb.IsElementPresent(dict.QuickLinks)) {
				for (int j = 1; j < 6;) {
					Value = wb.getText("(" + dict.QuickLinks + "//li)[" + j + "]");
					if (j == 2 || j == 4) {
						Value = wb.Splitter(Value, "-", 1);
					} else if (j == 3) {
						Value = wb.Splitter(Value, " ", 0);
					} else {
						Value = wb.Splitter(Value, " ", 1);
					}
					System.out.println("Value after split is " + Value);
					System.out.println("Value of j " + j);
					if (j == 1) {
						Result = NewTab("(" + dict.QuickLinks + "//li)[" + j + "]", dict.aboutusPage, Value);
					} else if (j == 5) {
						Result = NewTab("(" + dict.QuickLinks + "//li)[" + j + "]", dict.Mediaresource, Value);
					} else {
						Result = NewTab("(" + dict.QuickLinks + "//li)[" + j + "]", dict.TextCentre, Value);
					}
					if (Result.contains("Fail")) {
						return Result;
					}
					j++;
					Thread.sleep(1000);
					wb.scrolldown(dict.Footer);
					Thread.sleep(2000);
				}
			} else {
				return ("Fail: Quick Links were not present in footer");
			}
			closechat();
			if (wb.IsElementPresent(dict.NetworkSites)) {
				for (int j = 1; j < 4;) {
					Value = wb.getText("(" + dict.NetworkSites + "//a)[" + j + "]");
					Value = wb.Splitter(Value, "-", 0);
					System.out.println("Value after split is " + Value);
					System.out.println("Value of j " + j);
					Thread.sleep(2000);
					if (j == 1) {
						Result = SwitchWindow("(" + dict.NetworkSites + "//a)[" + j + "]", dict.ProptigerImg, Value);
					} else if (j == 2) {
						Result = SwitchWindow("(" + dict.NetworkSites + "//a)[" + j + "]", dict.VerifyMakaanIQ, "iq");
					} else {
						Result = SwitchWindow("(" + dict.NetworkSites + "//a)[" + j + "]", dict.MansionGlobal, Value);
					}
					if (Result.contains("Fail")) {
						return Result;
					}
					j++;
					Thread.sleep(1000);
					wb.scrolldown(dict.Footer);
					Thread.sleep(2000);
				}

			} else {
				return ("Fail: Network Sites Links were not present in footer");
			}

			closechat();
			if (wb.IsElementPresent(dict.SocialLinks)) {
				Result = SwitchWindow(dict.FacebookIcon, dict.VerifyFB, "facebook");
				if (Result.contains("Fail")) {
					return Result;
				}
				Thread.sleep(1000);
				wb.scrolldown(dict.Footer);
				Thread.sleep(2000);
				Result = SwitchWindow(dict.GoogleIcon, dict.VerifyGoogle, "google");
				if (Result.contains("Fail")) {
					return Result;
				}
				Thread.sleep(1000);
				wb.scrolldown(dict.Footer);
				Thread.sleep(2000);
				Result = SwitchWindow(dict.TwitterIcon, dict.VerifyTwitter, "twitter");
				if (Result.contains("Fail")) {
					return Result;
				}
				Thread.sleep(1000);
				wb.scrolldown(dict.Footer);
				Thread.sleep(2000);
				Result = SwitchWindow(dict.LinkedInIcon, dict.VerifyLinkedIn, "linked");
				if (Result.contains("Fail")) {
					return Result;
				}
			} else {
				return ("Fail: Network Sites Links were not present in footer");
			}
			Thread.sleep(1000);
			wb.scrolldown(dict.Footer);
			Thread.sleep(2000);
			if (wb.IsElementPresent(dict.MoreSEOlinkbutton)) {
				wb.ClickbyXpath(dict.MoreSEOlinkbutton);
				Thread.sleep(1000);
				if (wb.IsElementPresent(dict.MoreSEOlinks)) {
					System.out.println("SEO Links were present in footer");
				} else {
					return ("Fail: More Seo Links were not present in SEO Filters");
				}
			} else {
				return ("Fail: More button is not present in SEO Filters");
			}

		} else {
			return ("Fail: About Makaan Section was not present in footer");
		}

		return ("Pass: Header Footer Section Verified Successfully");
	}

	public String NewTab(String Path, String Element, String Value)
			throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		wb.ClickbyXpath(Path);
		wb.WaitUntill(Element);

		String URL = wb.CurrentURL();
		res = wb.Get_Response(URL);
		if (res == 200) {
			System.out.println("response code from " + URL + "is " + res);
			if (URL.contains(Value)) {
				wb.WaitUntillVisiblility(Element);
				if (wb.IsElementPresent(Element)) {
					System.out.println("Element is present on page");
				} else {
					wb.Back();
					return ("Fail: Element was not present in URL " + URL + " " + Element);
				}
			} else {
				wb.Back();
				return ("Fail:URL did not contain desired value " + URL + "And Value is " + Value);
			}
		} else {
			wb.Back();
			return ("Fail: response code from " + URL + "is " + res);
		}
		wb.Back();
		return ("Pass: Verified Successfully");
	}

	public String SwitchWindow(String Path, String Element, String Value)
			throws NoSuchElementException, TimeoutException, InterruptedException, IOException {
		int res = 0;
		driver = wb.getDriver();
		String parentHandle = driver.getWindowHandle();
		try {
			if (wb.IsElementPresent("")) {

			}
		} catch (Exception e) {

		}
		wb.WaitUntillVisiblility(Path);
		Thread.sleep(2000);
		driver.findElement(By.xpath(Path)).click();
		Thread.sleep(5000);
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
				// Thread.sleep(1000);
				if (wb.IsElementPresent(Element)) {
					System.out.println("Element is present on page");
				} else {
					driver.close();
					driver.switchTo().window(parentHandle);
					return ("Fail: Element was not present in URL " + URL);
				}
			} else {
				driver.close();
				driver.switchTo().window(parentHandle);
				return ("Fail: URL did not contain desired value " + URL + "Value is " + Value);
			}
		} else {
			driver.close();
			driver.switchTo().window(parentHandle);
			return ("Fail: response code from " + URL + "is " + res);
		}
		driver.close();
		driver.switchTo().window(parentHandle);
		return ("Pass: Verify Successfully");
	}

	public static void closechat() {
		try {
			if (wb.IsElementPresentById("inner-wrapper")) {
				// wb.ClickbyXpath(".//textarea[@id='input']");
				wb.ClickbyXpath(".//div[@class='cross']");
				System.out.println("Closed mchat popup");
			}
		} catch (Exception e) {
			System.out.println("mchat popup was not present");
		}

	}
}
