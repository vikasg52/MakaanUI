package com.makaan.Middleware;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.makaan.Dictionary.SERP;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;
import com.makaan.Middleware.LoginMiddleware;

public class SERPMiddleware {

	public static Webhelper wb = null;
	public static SERP dict = null;
	public static WebDriver driver;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");
	public static LoginMiddleware login = null;

	public SERPMiddleware() {
		System.out.println("inside SERP Middleware Constructor");

		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();
		login = new LoginMiddleware();

		System.out.println("inside Middleware Constructor");

	}

	public static boolean OpenURL(String Page) throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		String URL = null;
		if (Page.equals("City SERP")) {
			URL = ReadSheet("SERP", "URL", 2);
		} else if (Page.equals("Locality SERP")) {
			URL = ReadSheet("LocalitySERP", "URL", 2);
		}
		wb.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		res = wb.Get_Response(URL);
		if (res == 200) {
			System.out.println("Response code got from URL " + res);
			System.out.println("Waiting till Makaan logo found on page");
			wb.WaitUntillVisiblility(dict.MakaanLogo);
		} else {
			System.err.println("Response code got from URL " + res);
			return false;
		}
		return true;
	}

	public static String ReadSheet(String Sheet, String Col_Name, int row_id)
			throws IOException, NoSuchElementException, TimeoutException {

		String data = xls.getCellData(Sheet, Col_Name, row_id);
		System.out.println("Data from sheet " + data);

		return (data);
	}

	public String ValiadateBuyFilters()
			throws TimeoutException, NoSuchElementException, IOException, InterruptedException {

		String Path = null;
		ArrayList arr = new ArrayList<String>();
		Thread.sleep(2000);
		wb.WaitUntill(dict.filterbar);
		if (wb.IsElementPresent(dict.filterbar)) {
			System.out.println("filter bar is present");
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
			Thread.sleep(3000);
			arr.add(getPropertyValues(dict.PropertyCount));
			if (VerifyFilters("Buy")) {
			} else {
				return ("False: Filter verification is failed for buy /rent toggle");
			}
			Thread.sleep(2000);
			SetBudget("Buy", 2);
			Thread.sleep(3000);
			arr.add(getPropertyValues(dict.PropertyCount));
			if (VerifyFilters("Buy")) {

			} else {
				return ("Fail: Filter verification is failed for set Budget");
			}
			Thread.sleep(2000);
			Path = dict.BedroomType.concat(ReadSheet("SERP", "Bedroom", 2)).concat("')]");
			wb.ClickbyXpath(dict.Bedroom);
			// wb.WaitUntillVisiblility(Path);
			wb.ClickbyXpath(Path);
			// wb.WaitUntillVisiblility(dict.Bedroom);
			// wb.ClickbyXpath(dict.Bedroom);
			Thread.sleep(3000);
			arr.add(getPropertyValues(dict.PropertyCount));
			if (VerifyFilters("Buy")) {

			} else {
				return ("Fail: Filter verification is failed for Bedrooms");
			}
			ValidateMorefilters("Buy");
			Thread.sleep(3000);
			arr.add(getPropertyValues(dict.PropertyCount));
			if (VerifyFilters("Buy")) {

			} else {
				return ("Fail: Filter verification is failed for More Filters");
			}
			/*
			 * wb.WaitUntillVisiblility(dict.SortByVal);
			 * wb.ClickbyXpath(dict.SortByVal);
			 * wb.WaitUntillVisiblility(dict.SortHightolow); /*MO-1239
			 * wb.ClickbyXpath(dict.SortHightolow); Thread.sleep(1000);
			 * arr.add(getPropertyValues(dict.PropertyCount)); if
			 * (VerifyFilters("sale")) {
			 * 
			 * } else { System.err.println(
			 * "Filter verification is failed for Sort by val"); return false; }
			 */
			String res = Reset(arr, "Buy");
			return res;

		} else {
			return ("Fail: Filter bar was not present");
		}

	}

	public boolean VerifyFilters(String Type) throws IOException, TimeoutException {
		wb.WaitUntillVisiblility(dict.MakaanLogo);
		String URL = wb.CurrentURL();
		int res = wb.Get_Response(URL);
		if (res == 200) {
			if (Type.equalsIgnoreCase("Buy")) {
				Type = "sale";
			}
			System.out.println("Response Code of " + URL + "is " + res);
			if (wb.getText(dict.PropertyType).contains(Type)) {
			} else {
				System.err.println("Filters did not apllied for " + Type);
				return false;
			}
		} else {
			System.err.println("Response Code of " + URL + "is " + res);
			return false;
		}
		return true;
	}

	public String ValiadateRentFilters()
			throws InterruptedException, TimeoutException, NoSuchElementException, IOException {
		Thread.sleep(2000);
		String Path = null;
		ArrayList<String> arr = new ArrayList();
		// wb.WaitUntill(dict.filterbar);
		if (wb.IsElementPresent(dict.filterbar)) {
			System.out.println("filter bar is present");
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
			Thread.sleep(3000);
			arr.add(getPropertyValues(dict.PropertyCount));
			if (VerifyFilters("rent")) {
			} else {
				return ("Fail: Filter verification is failed for buy /rent toggle");

			}
			SetBudget("Rent", 3);
			Thread.sleep(3000);
			arr.add(getPropertyValues(dict.PropertyCount));
			if (VerifyFilters("rent")) {

			} else {
				return ("FAil: Filter verification is failed for set Budget");
			}
			Path = dict.BedroomType.concat(ReadSheet("SERP", "Bedroom", 3)).concat("')]");
			Thread.sleep(1000);
			wb.ClickbyXpath(dict.Bedroom);
			// wb.WaitUntillVisiblility(Path);
			wb.ClickbyXpath(Path);
			Thread.sleep(3000);
			arr.add(getPropertyValues(dict.PropertyCount));
			if (VerifyFilters("rent")) {

			} else {
				return ("Fail: Filter verification is failed for Bedrooms");
			}
			ValidateMorefilters("rent");
			Thread.sleep(3000);
			arr.add(getPropertyValues(dict.PropertyCount));
			if (VerifyFilters("rent")) {

			} else {
				return ("Fail: Filter verification is failed for More Filters");
			}
			/*
			 * wb.WaitUntillVisiblility(dict.SortByVal);
			 * wb.ClickbyXpath(dict.SortByVal);
			 * wb.WaitUntillVisiblility(dict.SortHightolow);
			 * wb.ClickbyXpath(dict.SortHightolow); Thread.sleep(1000);
			 * arr.add(getPropertyValues(dict.PropertyCount)); /*MO-1239 if
			 * (VerifyFilters("rent")) {
			 * 
			 * } else { System.err.println(
			 * "Filter verification is failed for Sort by val"); return false; }
			 */
			String res = Reset(arr, "Rent");
			return res;
		} else {
			return ("Fail: Filter bar was not present");
		}
	}

	public void SetBudget(String Type, int RowId)
			throws InterruptedException, NoSuchElementException, IOException, TimeoutException {
		String Path = null;
		Path = dict.BudgetMinValue.concat(ReadSheet("SERP", Type + " Min", RowId)).concat("']");
		wb.ClickbyXpath(dict.Budget);
		wb.ClickbyXpath(dict.BudgetMin);
		wb.ClickbyXpath(Path);
		Path = dict.BudgetMaxValue.concat(ReadSheet("SERP", Type + " Max", RowId)).concat("']");
		Thread.sleep(1000);
		// wb.WaitUntillVisiblility(dict.Budget);
		wb.ClickbyXpath(dict.Budget);
		wb.ClickbyXpath(dict.BudgetMax);
		// wb.WaitUntillVisiblility(Path);
		wb.ClickbyXpath(Path);
	}

	public void ValidateMorefilters(String Type) throws TimeoutException, InterruptedException {
		System.out.println("Inside More filters");
		// wb.WaitUntillVisiblility(dict.MoreFilter);
		Thread.sleep(1000);
		wb.ClickbyXpath(dict.MoreFilter);
		// wb.WaitUntillVisiblility(dict.PropertyTypeAppartment);
		wb.ClickbyXpath(dict.PropertyTypeAppartment);
		// wb.WaitUntillVisiblility(dict.PostedByAgent);
		wb.ClickbyXpath(dict.PostedByAgent);
		// wb.WaitUntillVisiblility(dict.SelectBathroom);
		wb.ClickbyXpath(dict.SelectBathroom);
		if (Type.equalsIgnoreCase("Buy")) {
			// wb.WaitUntillVisiblility(dict.AgeofPropertyAny);
			wb.ClickbyXpath(dict.AgeofPropertyAny);
			// wb.WaitUntillVisiblility(dict.SelectNew_Resale);
		}
		// wb.WaitUntillVisiblility(dict.MoreFilter);
		wb.ClickbyXpath(dict.MoreFilter);
	}

	public String getPropertyValues(String Value) throws TimeoutException {
		wb.WaitUntillVisiblility(dict.PropertyCount);
		return wb.getText(Value);
	}

	public String Reset(ArrayList<String> arr, String Type)
			throws TimeoutException, NoSuchElementException, IOException, InterruptedException {
		String Path = null;
		ArrayList arr1 = new ArrayList<String>();
		Thread.sleep(3000);
		System.out.println("Inside reset filters");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println("Value of array at arr" + " " + i + " " + arr.get(i));
		}
		// wb.WaitUntillVisiblility(dict.filterbar);
		if (wb.IsElementPresent(dict.filterbar)) {
			/*
			 * 
			 * wb.ClickbyXpath(dict.SortByVal); MO-1239
			 * wb.ClickbyXpath(".//label[@for='sortBy1']"); Thread.sleep(1000);
			 * arr1.add(getPropertyValues(dict.PropertyCount)); if
			 * (arr1.get(0).equals(arr.get(3))) { System.out.println(
			 * "Property count are matching after removing sort by value filter"
			 * + arr.get(3) + " " + arr1.get(0)); } else { System.err.println(
			 * "Property Counts are not matching after removing sort by value filter"
			 * + arr.get(3) + " " + arr1.get(0)); return false; }
			 */
			ValidateMorefilters(Type);
			Thread.sleep(4000);
			arr1.add(getPropertyValues(dict.PropertyCount));
			Thread.sleep(2000);
			if (arr.get(2).equals(arr1.get(0))) {
				System.out.println(
						"Property count are matching after removing more filters" + arr.get(2) + " " + arr1.get(0));
			} else {
				return ("Fail: Property Counts are not matching after removing More filters" + arr.get(2) + " "
						+ arr1.get(0));

			}
			Thread.sleep(3000);
			Path = dict.BedroomType.concat(ReadSheet("SERP", "Bedroom", 2)).concat("')]");
			// wb.WaitUntillVisiblility(dict.Bedroom);
			wb.ClickbyXpath(dict.Bedroom);
			// wb.WaitUntillVisiblility(Path);
			wb.ClickbyXpath(Path);
			Thread.sleep(4000);
			arr1.add(getPropertyValues(dict.PropertyCount));
			Thread.sleep(2000);
			if (arr.get(1).equals(arr1.get(1))) {

				System.out
						.println("count are matching after removing bedroom filters" + arr.get(1) + " " + arr1.get(1));
			} else {
				return ("Fail: Property Counts are not matching after removing Bedroom Filter " + arr.get(1) + " "
						+ arr1.get(1));
			}
			Thread.sleep(3000);
			wb.ClickbyXpath(dict.Budget);
			// wb.WaitUntillVisiblility(dict.BudgetMin);
			wb.ClickbyXpath(dict.BudgetMin);
			wb.ClickbyXpath(dict.BudgetReset0);
			// wb.WaitUntillVisiblility(dict.Budget);
			wb.ClickbyXpath(dict.Budget);
			// wb.WaitUntillVisiblility(dict.BudgetMax);
			wb.ClickbyXpath(dict.BudgetMax);
			wb.clearbox(dict.BudgetMax);
			wb.enterTextByxpath(dict.BudgetMax, 0);
			wb.ClickbyXpath(dict.Budget);
			Thread.sleep(4000);
			// wb.WaitUntillVisiblility(dict.PropertyCount);
			arr1.add(getPropertyValues(dict.PropertyCount));
			Thread.sleep(2000);
			if (arr.get(0).equals(arr1.get(2))) {
				System.out.println("Property count are matching after removing set budget filter" + arr.get(0) + " "
						+ arr1.get(2));
			} else {
				return ("Fail: Property Counts are not matching for removing set Budget filter" + arr.get(0) + " "
						+ arr1.get(2));
			}

		}
		return ("Pass: Filter Verified Successfullt for " + Type);
	}

	public String ValidateSellerType(String Type1) throws InterruptedException, IOException, TimeoutException {
		System.out.println("Inside Validate Seller Type");
		int res = 0;
		String URL = null;
		if (Type1.equalsIgnoreCase("Buy")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
			Thread.sleep(2000);
		} else if (Type1.equalsIgnoreCase("Rent")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
			Thread.sleep(2000);
		}
		if (VerifyFilters(Type1)) {
			System.out.println("Filter Applied Suucessfully for " + Type1);
		} else {
			return ("Fail: Filter did not applied successfully for " + Type1);
		}
		String Type = wb.getText(dict.SellerType);
		if (Type.contains("agent")) {
			wb.ClickbyXpath(dict.SellerLink);
			Thread.sleep(1000);
			wb.CurrentURL();
			URL = wb.CurrentURL();
			res = wb.Get_Response(URL);
			if (res == 200) {
				System.out.println("Response code got from " + URL + "is " + res);
				if (wb.IsElementPresent(dict.VerifyAgent)) {
					System.out.println(wb.getText(dict.VerifyAgent));
				} else {
					wb.Back();
					return ("Fail: Validate seller Link was not present");
				}
			} else {
				wb.Back();
				return ("Fail: Response code got from " + URL + "is " + res);
			}
		} else if (Type.contains("builder")) {
			wb.ClickbyXpath(dict.SellerLink);
			Thread.sleep(1000);
			URL = wb.CurrentURL();
			res = wb.Get_Response(URL);
			if (res == 200) {
				System.out.println("Response code got from " + URL + "is " + res);
				if (wb.IsElementPresent(dict.VerifyBuilder)) {
					System.out.println(wb.getText(dict.VerifyBuilder));
				} else {
					wb.Back();
					return ("Fail: Elemnt was not present on Agent Page");
				}
			} else {
				wb.Back();
				return ("Fail: Response code got from " + URL + "is " + res);
			}
		} else {
			return ("Pass: Seller Type is otherthan agent or Builder");
		}
		wb.Back();
		Thread.sleep(1000);
		return ("Pass: Seller Type verified successfully");
	}

	public String VerifyLocality(String Type) throws IOException, InterruptedException, TimeoutException {
		System.out.println("Inside Locality Link verification");
		String URL = null;
		int res = 0;
		if (Type.equalsIgnoreCase("Buy")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
			Thread.sleep(2000);
		} else if (Type.equalsIgnoreCase("rent")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
			Thread.sleep(2000);
		}
		if (VerifyFilters(Type)) {
			System.out.println("Filter Applied Suucessfully for " + Type);
		} else {
			return ("Fail: Filter did not applied successfully for " + Type);
		}
		if (wb.IsElementPresent(dict.LocationLink)) {
			wb.ClickbyXpath(dict.LocationLink);
			Thread.sleep(1000);
			URL = wb.CurrentURL();
			res = wb.Get_Response(URL);
			if (res == 200) {
				System.out.println("Response code got from " + URL + "is " + res);
				Thread.sleep(2000);
				if (wb.IsElementPresent(dict.VerifyLocality)) {
					System.out.println("Locality link is opening");
				} else {
					System.out.println("Elemnet was not present on Locality Page");
					wb.Back();
					return ("Fail: Elemnet was not present on Locality Page" + dict.VerifyLocality);
				}
			} else {
				wb.Back();
				return ("Fail: Response code got from " + URL + "is " + res);
			}
		} else {
			wb.Back();
			return ("Fail: Some error occurred while opening from Location link ");
		}
		wb.Back();
		Thread.sleep(1000);
		return ("Pass: Verified Locality Successfully");
	}

	public String VerifyProject(String Type) throws InterruptedException, IOException, TimeoutException {
		System.out.println("Inside Project Link verification");
		String URL = null;
		wb.PageRefresh();
		int res = 0;
		if (Type.equalsIgnoreCase("Buy")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
			Thread.sleep(2000);
		} else if (Type.equalsIgnoreCase("rent")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
			Thread.sleep(2000);
		}
		if (VerifyFilters(Type)) {
			System.out.println("Filter Applied Suucessfully for " + Type);
		} else {
			return ("Fail: Filter did not applied successfully for " + Type);
		}
		wb.scrolldown(dict.ProjectLink);
		wb.scrollup(dict.ProjectLink);
		try {
			if (wb.IsElementPresent(dict.ProjectLink)) {
				wb.ClickbyXpath(dict.ProjectLink);
				Thread.sleep(1000);
				URL = wb.CurrentURL();
				res = wb.Get_Response(URL);
				if (res == 200) {
					System.out.println("Response code got from " + URL + "is " + res);
					if (wb.IsElementPresent(dict.VerifyProperty)) {
						System.out.println("Project link is opening");
					} else {
						wb.Back();
						Thread.sleep(1000);
						return ("Fail: Elemnet was not present on Locality Page" + dict.VerifyProperty);
					}
				} else {
					wb.Back();
					Thread.sleep(1000);
					return ("Fail: Response code got from " + URL + "is " + res);
				}

			}
		} catch (NoSuchElementException e) {
			return ("Pass: Project link was not present");
		}
		wb.Back();
		Thread.sleep(1000);
		return ("Pass: Project Link Verified successfully");
	}

	public String VerifyReadMore(String Type) throws InterruptedException, IOException, TimeoutException {
		System.out.println("Inside Read More Link verification");
		if (Type.equalsIgnoreCase("Buy")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
		} else if (Type.equalsIgnoreCase("rent")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
		}
		Thread.sleep(4000);
		if (VerifyFilters(Type)) {
			System.out.println("Filter Applied Suucessfully for " + Type);
		} else {
			return ("Fail: Filter did not applied successfully for " + Type);
		}
		wb.PageRefresh();
		if (SwitchWindow(dict.ViewMoreProperty)) {
		} else {
			return ("Fail: Some error occurred while opening from read more link");
		}
		return ("Pass: Read more is verified successfully for Read more for " + Type);
	}

	public String VerifyPropertyLink(String Type) throws InterruptedException, IOException, TimeoutException {
		System.out.println("Inside Property Link verification");
		wb.PageRefresh();
		if (Type.equalsIgnoreCase("Buy")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
			Thread.sleep(3000);
		} else if (Type.equalsIgnoreCase("Rent")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
			Thread.sleep(3000);
		}
		if (VerifyFilters(Type)) {
			System.out.println("Filter Applied Suucessfully for " + Type);
		} else {
			return ("Fail: Filters did not applied successfully for " + Type);
		}
		if (SwitchWindow(dict.PropertyLink)) {
			System.out.println("Property Link opened");
		} else {
			return ("Fail: Some error occurred while opening from property link");
		}
		return ("Pass: Property Link is verified for " + Type);
	}

	public String VerifyImageOnCard(String Type) throws InterruptedException, TimeoutException, IOException {
		System.out.println("Inside Property card verification");
		int GalleryCount = 0;
		int SERPcount = 0;
		/*
		 * if (Type.equals("Buy")) { wb.ClickbyXpath(dict.buy_rentdropdown);
		 * wb.ClickbyXpath(dict.buy); Thread.sleep(2000); }
		 */ if (Type.equalsIgnoreCase("Rent")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
			Thread.sleep(2000);
		}
		if (VerifyFilters(Type)) {
			System.out.println("Filter Applied Suucessfully for " + Type);
		} else {
			return ("Fail: Filter Not Applied for " + Type);
		}
		String PropertyCard = dict.FirstPropertycard + "1']";
		wb.WaitUntillVisiblility(PropertyCard);
		String URL = wb.CurrentURL();
		if (wb.IsElementPresent(PropertyCard)) {
			if (URL.contains("www.makaan.com")) {
				Thread.sleep(2000);
				wb.ClickbyXpath("(//img[contains(@data-src,'https://content.makaan.com/')])[1]");
			} else {
				wb.ClickbyXpath("(//img[contains(@data-src,'https://content.makaan-ws.com/')])[1]");
			}
			Thread.sleep(1000);

			// wb.ClickbyXpath(PropertyCard+dict.imagegallery);
			if (wb.IsElementPresent(dict.galleryTotalCount) && wb.IsElementPresent(dict.imagegallery)) {
				System.out.println("Gallery Total count" + wb.getText(dict.galleryTotalCount));
				GalleryCount = Integer.parseInt(wb.getText(dict.galleryTotalCount));
				if (GalleryCount > 1) {
					wb.ClickbyXpath(dict.galleryNext);
					wb.ClickbyXpath(dict.galleryNext);
				}
				wb.ClickbyXpath(dict.galleryClose);
			} else {
				return ("Fail: Images or Count in Gallery are not present");
			}
			wb.WaitUntillVisiblility(dict.MakaanLogo);
			String Imagecount = PropertyCard + dict.ImageCount;
			if (GalleryCount > 1) {
				if (wb.IsElementPresent(Imagecount)) {
					SERPcount = Integer.parseInt(wb.Splitter(wb.getText(Imagecount), "/", 1));
					System.out.println(SERPcount);
					if (GalleryCount == SERPcount) {
						try {
							if (wb.IsElementPresent(dict.DefaultImage + "[1]")) {
								System.err.println("Default image is present on the property card");
								return ("False: Dafault image on Card is present");
							}
						} catch (NoSuchElementException e) {
							System.out.println("Default image was not present on card ");
						}
						wb.ClickbyXpath(PropertyCard + dict.Nextimage);
					} else {
						return ("Fail: Image count is different in both GAllery and on SERP page ");
					}
				} else {
					return ("Fail: Image count is not present on SERP page ");
				}
			}

		} else {
			return ("Fail: Property Card was not present on SERP Page");
		}
		return ("Pass: Image on Galeery are verified");
	}

	public boolean SwitchWindow(String Path) throws InterruptedException, IOException {
		String URL = null;
		driver = wb.getDriver();
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.xpath(Path)).click();
		Thread.sleep(2000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		URL = driver.getCurrentUrl();
		System.out.println("URL of opened window is " + URL);
		int res = wb.Get_Response(URL);
		if (res == 200) {
			System.out.println("Response code got from " + URL + "is " + res);
			if (wb.IsElementPresent(dict.VerifyProperty)) {
				driver.close();
				driver.switchTo().window(parentHandle);
				return true;
			} else
				driver.close();
			driver.switchTo().window(parentHandle);
			System.err.println("Page does not open due to response code");
			return false;
		} else {
			System.err.println("Response code got from " + URL + "is " + res);
			driver.close();
			driver.switchTo().window(parentHandle);
			return false;
		}
	}

	public String VerifyShortList(String Type) throws InterruptedException, IOException, TimeoutException {
		System.out.println("Inside validate shortlist property");
		Thread.sleep(3000);
		wb.PageRefresh();
		if (Type.equalsIgnoreCase("Buy")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
			Thread.sleep(2000);
		} else if (Type.equalsIgnoreCase("Rent")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
			Thread.sleep(2000);
		}
		if (VerifyFilters(Type)) {
			System.out.println("Filter Applied Suucessfully for " + Type);
		} else {
			return ("Fail: Filter did not applied Successfully for " + Type);
		}
		Thread.sleep(2000);
		wb.WaitUntillVisiblility(dict.MakaanLogo);
		wb.ClickbyXpath(dict.Shortlist);
		if (wb.IsElementPresent(dict.Shortlisted)) {
			System.out.println("property is been shortlisted");
		} else {
			System.err.println("There is problem in shortlisting property");
			Thread.sleep(1000);
			return ("There is problem in shortlisting property");
		}

		return ("Pass: Shortlist property is been validated successfully");
	}

	public String SetAlert() throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside Test Verify Set Alert");
		wb.PageRefresh();
		wb.ClickbyXpath(dict.SetAlert);
		String email = null;
		if (wb.getText(dict.SetAlertBox).contains("stay informed")) {
			wb.enterTextByxpath(dict.SetAlertName, "testSearch");

			email = ReadSheet("Login", "UserName", 4);
			wb.enterTextByxpath(dict.SetAlertEmail, email);

			wb.ClickbyXpath(dict.SetAlertsubmit);

			if (wb.getText(dict.SetAlertMesage).contains("you've already set an alert for this requirement")) {
				return ("Fail: already set alert for this try later");
			} else if (wb.getText(dict.SetAlertMesage).contains("successfully")) {
				System.out.println("Successfully saved");

				CleanSaveSearch();
			}
		} else {
			return ("Fail: Set alert button is not workin, popup does not open");
		}
		return ("Pass: Set Alert Verified Successfully");
	}

	public void CleanSaveSearch() throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		wb.GetURL(ReadSheet("Login", "URL", 2));
		login.MakaanLogin();
		wb.WaitUntillVisiblility(dict.BuyerJourney);

		wb.ClickbyXpath(dict.BuyerJourney);
		Thread.sleep(2000);
		if (wb.getText(dict.SaveSearchText).equalsIgnoreCase("testsearch")) {
			wb.ClickbyXpath(dict.DeleteSaveSearch);
			wb.ClickbyXpath(dict.RemoveButton);
		}
	}

	public String Pagination() throws InterruptedException {
		System.out.println("Inside test Pagination Verification");
		Thread.sleep(2000);
		wb.scrolldown(dict.Pagination);
		wb.scrollup(dict.Pagination);
		Thread.sleep(2000);
		wb.ClickbyXpath(dict.NextPage);
		String URL = wb.CurrentURL();
		wb.scrolldown(dict.Pagination);
		wb.scrollup(dict.Pagination);
		if (URL.contains(wb.getText(dict.PageActive))) {
			System.out.println("Pagination was successful");
		} else {

			return ("Fail: pagination do not occur");
		}

		return ("Pass: Pagination verified successfully");
	}

	public String SideCards(String Type) throws TimeoutException, InterruptedException, IOException {
		System.out.println("Validating Side Cards");

		/*
		 * As per AB Testing Changes this part of code is commented if
		 * (wb.IsElementPresent(dict.TrackJourney)) {
		 * wb.WaitUntill(dict.TrackJourney); wb.ClickbyXpath(dict.TrackJourney);
		 * Thread.sleep(1000); URL = wb.CurrentURL(); int res =
		 * wb.Get_Response(URL); if (res == 200) { if
		 * (URL.contains("my-journey")) { System.out.println(
		 * "track your journey card was verified"); } else { System.err.println(
		 * "track your journey card was not verified as URL did not open");
		 * return false; } } else { System.err.println("Response code got from "
		 * + URL + "is " + res); return false; } wb.Back(); } wb.scrolldown();
		 * wb.scrollup(dict.AppCard); //closechat(); if
		 * (wb.IsElementPresent(dict.AppCard)) { driver = wb.getDriver(); String
		 * parentHandle = driver.getWindowHandle();
		 * driver.findElement(By.xpath(dict.AppCard)).click();
		 * Thread.sleep(1000); for (String winHandle :
		 * driver.getWindowHandles()) { driver.switchTo().window(winHandle); }
		 * URL = driver.getCurrentUrl(); int res = wb.Get_Response(URL); if (res
		 * == 200) { System.out.println("URL of opened window is " + URL);
		 * 
		 * if (URL.contains("apps")) { System.out.println(
		 * "download app card was pesent"); } else { System.err.println(
		 * "Download app card was not present"); driver.close();
		 * driver.switchTo().window(parentHandle); return false; }
		 * driver.close(); driver.switchTo().window(parentHandle); } else {
		 * System.err.println("Response code got from " + URL + "is " + res);
		 * driver.close(); driver.switchTo().window(parentHandle); return false;
		 * } }
		 */
		wb.PageRefresh();
		Thread.sleep(2000);
		closechat();
		Thread.sleep(3000);
		wb.scrollup(dict.Buy_RentCard);
		wb.WaitUntillVisiblility(dict.Buy_RentCard);
		if (wb.IsElementPresent(dict.Buy_RentCard)) {
			String Text = wb.getText(dict.Buy_RentText);
			wb.ClickbyXpath(dict.Buy_RentCard);
			Thread.sleep(1000);
			wb.WaitUntillVisiblility(dict.MakaanLogo);
			if (Text.contains("buy")) {
				if (wb.getText(dict.Buy_RentText).contains("rent")) {
					System.out.println("Side card Buy / rent Property is validated ");
				} else {
					return ("Fail: Side card Buy / rent Property is Not working");
				}
			} else if (Text.contains("rent")) {
				if (wb.getText(dict.Buy_RentText).contains("buy")) {
					System.out.println("Side card Buy / rent Property is validated ");
				} else {
					return ("Fail: Side card Buy / rent Property is Not working");
				}
			}
		}
		if (Type.equals("City SERP")) {
			for (int i = 1; i < 5; i++) {
				wb.scrolldown();
				wb.scrollup(dict.LocalityCard);
				wb.scrollup(dict.LocalityCard);
				System.out.println("Validating Locality Cards");

				wb.WaitUntillVisiblility(dict.LocalityCard);
				String Path = dict.LocalityLink + i + "]";
				String Locality = ".//li[" + i + dict.LocalityText;
				String rsult = VerifyLocalityCard(Path);
				if (rsult.contains("Pass")) {
					System.out.println("REturned true from Locality Verification");
					Thread.sleep(1000);
					wb.Back();
					Thread.sleep(1000);
					wb.WaitUntillVisiblility(dict.MakaanLogo);

				} else {
					// System.out.println("Locality link is not opening " +
					// wb.getText(Locality));
					wb.Back();
					return ("Fail: " + rsult);
				}

			}
		}
		wb.WaitUntillVisiblility(dict.MakaanLogo);
		return ("Pass : Side cards are validated successfully");
	}

	public String VerifyLocalityCard(String Path) throws IOException, TimeoutException {
		wb.ClickbyXpath(Path);
		wb.WaitUntillVisiblility(dict.MakaanLogo);
		String URL = wb.CurrentURL();
		int res = wb.Get_Response(URL);
		if (res == 200) {
			System.out.println("Response of URl is " + res);
			// String about = wb.getText(dict.KnowMore);
			// String Locality = Path+dict.Locality;
			// about = wb.Splitter(about," ");
			// if(!(Locality.contains(wb.getText(about)))){
			// return false;
			// }

		} else {
			return ("Fail: Response of URl is which is failure " + res);
		}
		return ("Pass: Response of URl is which is passed " + res);
	}

	public static void CloseAll() {
		db.Close();
		wb.CloseBrowser();
	}

	public String mapList() throws IOException, TimeoutException {
		System.out.println("Inside test Toggle bhaviour of map and list");
		String count = getPropertyValues(dict.PropertyCount);
		if (wb.IsElementPresent(dict.ListView)) {
			System.out.println("List View button was present");
			if (wb.IsElementPresent(dict.MapView)) {
				System.out.println("Map View button was present on Page");
				wb.ClickbyXpath(dict.MapView);
				String URL = wb.CurrentURL();
				int res = wb.Get_Response(URL);
				if (res == 200) {
					System.out.println("Response of URl is " + res);
					if (wb.IsElementPresent(dict.MapModule)) {
						String countmap = getPropertyValues(dict.PropertyCount);
						if (countmap.equals(count)) {
							System.out.println("Verification for map view is passed");
						} else {
							return ("Fail: Verification for map view is not passed");
						}
					}
				} else {
					return ("Fail: Response of URl is which is failure " + res);
				}
			} else {
				return ("Fail: List View button was not present on Page");
			}
			if (wb.IsElementPresent(dict.ListView)) {
				System.out.println("List View button was present on Page");
				wb.ClickbyXpath(dict.ListView);
				String URL = wb.CurrentURL();
				int res = wb.Get_Response(URL);
				if (res == 200) {
					System.out.println("Response of URl is " + res);
					if (wb.IsElementPresent(dict.ListArea)) {
						String countList = getPropertyValues(dict.PropertyCount);
						if (countList.equals(count)) {
							System.out.println("Verification for List view is passed");
						} else {
							return ("Fail: Verification for List view is not passed");
						}
					}
				} else {
					return ("Fail: Response of URl is which is failure " + res);
				}
			} else {
				return ("Fail: List View button was not present on Page");
			}
		} else {
			return ("Fail: Map View button was not present on page");
		}
		return ("Pass: Toggle Between Map And List verified successfully");
	}

	public String VerifyKnowMoreLink(String Type) throws IOException, TimeoutException, InterruptedException {
		System.out.println("Inside Validating Know More Link");
		if (Type.equalsIgnoreCase("Buy")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
			Thread.sleep(2000);
		} else if (Type.equalsIgnoreCase("rent")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.rent);
			Thread.sleep(2000);
		}
		if (VerifyFilters(Type)) {
			System.out.println("Filter Applied Suucessfully for " + Type);
		} else {
			return ("Fail: Filter did not applied for " + Type);
		}
		Thread.sleep(2000);
		wb.PageRefresh();
		Thread.sleep(2000);
		wb.WaitUntillVisiblility(dict.KnowMore);
		if (wb.IsElementPresent(dict.KnowMore)) {
			System.out.println("Clicking on Know More link on page");
			wb.ClickbyXpath(dict.KnowMore);
			String URL = wb.CurrentURL();
			int res = wb.Get_Response(URL);
			if (res == 200) {
				System.out.println("Response of URl is " + res);
				if (wb.IsElementPresent(dict.CityHeroshot)) {
					System.out.println("Verification for Know More Link is passed");
				} else {
					wb.Back();
					return ("Fail: Verification for Know More is not passed as Hero shot image was not present on Page");
				}
			} else {
				wb.Back();
				return ("Fail: Response of URl is which is failure " + res);
			}
		} else {
			return ("Fail: know more Link was not present on SERP page");

		}
		wb.Back();
		return ("Pass: Know More link was validated for " + Type);
	}

	public String SponsoredLink() throws InterruptedException, TimeoutException {
		System.out.println("Inside Validating Sponsored Link");
		wb.ClickbyXpath(dict.buy_rentdropdown);
		wb.ClickbyXpath(dict.buy);
		Thread.sleep(2000);
		closechat();
		wb.WaitUntillVisiblility(dict.SortByVal);
		wb.ClickbyXpath(dict.SortByVal);
		wb.WaitUntillVisiblility(dict.Relevance);
		wb.ClickbyXpath(dict.Relevance);
		for (int i = 3; i < 10;) {
			String PropertyCard = dict.FirstPropertycard + i + "']";
			System.out.println(PropertyCard);
			Thread.sleep(2000);
			// wb.scrolldown(PropertyCard);
			// wb.scrollup(PropertyCard);
			// wb.WaitUntillVisiblility(PropertyCard);
			String Sponsored = PropertyCard + dict.Sponsored;
			String Seller = PropertyCard + dict.SponsoredSeller;
			if (wb.IsElementPresent(PropertyCard)) {
				if ((wb.IsElementPresent(Sponsored)) && (wb.IsElementPresent(Seller))) {
					System.out.println("Verified Sposored Link");
				} else {
					return ("Fail: Sponsored link was not present on card");
				}

			} else {
				return ("Fail: Sponsored Link was not present on card" + i);
			}
			i = i + 3;

		}
		return ("Pass: Sponsored Link was tested successfully");
	}

	public String SortingFilterbyPrice(String Type) throws TimeoutException, InterruptedException {
		System.out.println("Inside Validating Sorting Filters");
		int Price = 0, Price2 = 0;
		String Value = null, Value2 = null;
		wb.PageRefresh();
		if (Type.equals("lowtohigh")) {
			wb.WaitUntillVisiblility(dict.SortByVal);
			wb.ClickbyXpath(dict.SortByVal);
			wb.WaitUntillVisiblility(dict.SortLowtoHigh);
			wb.ClickbyXpath(dict.SortLowtoHigh);
			// wb.ClickbyXpath(dict.SortByVal);
		} else if (Type.equals("hightolow")) {
			wb.WaitUntillVisiblility(dict.SortByVal);
			wb.ClickbyXpath(dict.SortByVal);
			wb.WaitUntillVisiblility(dict.SortHightolow);
			wb.ClickbyXpath(dict.SortHightolow);
			// wb.ClickbyXpath(dict.SortByVal);
		}

		Thread.sleep(3000);
		for (int i = 1, j = 2; i < 4; i++, j++) {
			String PropertyCard = dict.FirstPropertycard + i + "']";
			String PropertyCard2 = dict.FirstPropertycard + j + "']";
			String PriceValue = PropertyCard + dict.Propertyprice;
			String Price2Value = PropertyCard2 + dict.Propertyprice;
			System.out.println(PriceValue);
			System.out.println(Price2Value);
			Value = PropertyCard + dict.INR;
			Value2 = PropertyCard2 + dict.INR;
			Price = PriceEvaluate(wb.getAttribute(Value, "content"));
			Price2 = PriceEvaluate(wb.getAttribute(Value2, "content"));
			System.out.println("Price on card " + i + " " + Price);
			System.out.println("Price on card " + j + " " + Price2);
			if (Type.equals("lowtohigh")) {
				if (Price < Price2 || Price == Price2) {
					System.out.println("Price is in Sorted Order for low to high");
				} else {
					return ("Fail: Price is not in sorted order for low to high");
				}
			} else if (Type.equals("hightolow")) {
				if (Price > Price2 || Price == Price2) {
					System.out.println("Price is in Sorted Order for high to low");
				} else {
					return ("Fail: Price is not in sorted order for high to low");
				}
			}
		}
		return ("Pass: SortingFilterbyPrice Validate successfully for " + Type);
	}

	public String filterbyRelevance(String Type) throws InterruptedException, TimeoutException {
		String result = null;
		if (Type.equals("City SERP")) {
			result = SponsoredLink();
		} else if (Type.equals("Locality SERP")) {
			wb.ClickbyXpath(dict.buy_rentdropdown);
			wb.ClickbyXpath(dict.buy);
			Thread.sleep(2000);
			closechat();
			wb.WaitUntillVisiblility(dict.SortByVal);
			wb.ClickbyXpath(dict.SortByVal);
			wb.WaitUntillVisiblility(dict.Relevance);
			wb.ClickbyXpath(dict.Relevance);
			Thread.sleep(2000);
			if (wb.IsElementPresent(dict.RelevanceFilter)) {
				result = "Pass: Filter Applied Successfully for Relavance";
			} else {
				result= "Fail: Filter did not Applied Successfully for Relavance";
			}
		}
		if (result.contains("Pass")) {
			System.out.println("Filter applied for relevance");
			return (result);
		} else {
			return (result);
		}
	}

	public String filterbySellerRating() throws TimeoutException, InterruptedException {
		wb.PageRefresh();
		wb.WaitUntillVisiblility(dict.SortByVal);
		wb.ClickbyXpath(dict.SortByVal);
		wb.ClickbyXpath(dict.SortSellerRating);
		wb.ClickbyXpath(dict.SortByVal);
		for (int i = 1, j = 2; i < 5; i++, j++) {
			String PropertyCard = dict.FirstPropertycard + i + "']";
			String PropertyCard2 = dict.FirstPropertycard + j + "']";
			String Rating = PropertyCard + dict.SellerRating;
			String Rating2 = PropertyCard2 + dict.SellerRating;
			System.out.println(Rating);
			System.out.println(Rating2);
			Thread.sleep(2000);
			driver = wb.getDriver();
			int rating = Integer.parseInt((wb.getAttribute(Rating, "data-sellerrating")));
			int rating2 = Integer.parseInt(wb.getAttribute(Rating2, "data-sellerrating"));
			if (rating > rating2 || rating == rating2) {
				System.out.println("Listing are in Sorted order for Seller rating");
			} else {
				return ("Fail: Listing are not in Sorted order for Seller rating");

			}
		}
		return ("Pass: Listing are in Sorted order for Seller rating");
	}

	public String SortingFilter(String Type) throws TimeoutException, InterruptedException {
		System.out.println("Inside Validating Sorting Filters");
		String result = SortingFilterbyPrice("lowtohigh");
		if (result.contains("Pass")) {
		} else {
			return result;
		}
		result = SortingFilterbyPrice("hightolow");
		if (result.contains("Pass")) {
		} else {
			return result;
		}
		result = filterbyRelevance(Type);
		if (result.contains("Pass")) {
		} else {
			return result;
		}
		result = filterbySellerRating();
		if (result.contains("Pass")) {
		} else {
			return result;
		}

		return result;
	}

	public static void closechat() {
		try {
			if (wb.IsElementPresentById("inner-wrapper")) {
				// wb.ClickbyXpath(".//textarea[@id='input']");
				wb.ClickbyXpath(".//div[@class='cross']");
				System.out.println("Closed mchat popup");
			} else {
				System.out.println("mchat popup was not present");
			}
		} catch (Exception e) {
			System.out.println("Chat popup was not present on page");
		}
	}

	public int PriceEvaluate(String Price) {
		int Token1 = 0, Append = 1;
		String Type, Token = null;
		if ((Price.contains("Cr")) || (Price.contains("L"))) {
			List<String> elephantList2 = Arrays.asList(Price.split(" "));
			String Value = elephantList2.get(0);
			Price = elephantList2.get(1);
			for (int i = 0; i < elephantList2.size(); i++) {
				System.out.println("For loop in start" + elephantList2.get(i) + i);

			}
			if (Price.contains("Cr")) {
				Type = "Cr";
				Append = 10000000;
			} else {
				Type = "L";
				Append = 100000;
			}

			if (Value.contains(".")) {
				List<String> elephantList = Arrays.asList(Value.split("\\."));
				Token = elephantList.get(0) + elephantList.get(1);
				if (elephantList.get(1).length() > 1) {
					System.out.println("Token from ." + Token);
					Token1 = Integer.parseInt(Token);
					Token1 = Token1 * (Append / 100);
					// return Token1;
				} else {
					Token1 = (Integer.parseInt(Token)) * (Append / 10);
				}
				return Token1;
			} else {
				Token1 = (Integer.parseInt(Value)) * Append;
				return Token1;
			}
		} else if (Price.contains(",")) {
			List<String> elephantList = Arrays.asList(Price.split(","));
			System.out.println("Else if array" + elephantList.get(0));
			List<String> elephantList1 = Arrays.asList(elephantList.get(1).split(" "));
			Token = elephantList.get(0) + elephantList1.get(0);
			Token1 = Integer.parseInt(Token);
			return Token1;
		} else {
			List<String> elephantList = Arrays.asList(Price.split(" "));
			return Integer.parseInt(elephantList.get(0));
		}

	}

	public String ValidateViewNumberforListedType() throws TimeoutException, IOException, InterruptedException {
		System.out.println("Inside Test Validate View Number for Listed Type");
		if (VerifyPostedFilter("agent")) {
			wb.PageRefresh();
			Thread.sleep(2000);
			if (VerifyLeadPopup("Agent").contains("Fail")) {
				return ("Fail: View Phone Pop Up was not present for Agent");
			}
		} else {
			return ("Fail: Filter Did not applied for Agent");
		}
		if (VerifyPostedFilter("builder")) {
			wb.PageRefresh();
			Thread.sleep(2000);
			if (VerifyLeadPopup("Builder").contains("Fail")) {
				return ("Fail: View Phone Pop Up was not present for Builder");
			}
		} else {
			return ("Fail: Filter Did not applied for Builder");
		}
		if (VerifyPostedFilter("owner")) {
			wb.PageRefresh();
			Thread.sleep(2000);
			if (VerifyLeadPopup("Owner").contains("Fail")) {
				return ("Fail: View Phone Pop Up was not present for Owner");
			}
		} else {
			return ("Fail: Filter Did not applied for Owner");
		}
		return ("Pass:Validated View Number for Listed Type");
	}

	public String VerifyLeadPopup(String Type) throws TimeoutException, InterruptedException {
		wb.WaitUntillVisiblility(dict.ViewPhone);
		wb.ClickbyXpath(dict.ViewPhone);
		if (wb.IsElementPresentById("leadPopup")) {
			System.out.println("Lead popup is present");
		} else {
			return ("Fail: View Phone Pop Up was not present for " + Type);
		}
		wb.ClickbyXpath(dict.ClosePopup);
		Thread.sleep(1000);
		return ("Pass: View Phone Popup was present for " + Type);
	}

	public boolean VerifyPostedFilter(String FilterType) throws TimeoutException, IOException, InterruptedException {
		wb.WaitUntillVisiblility(dict.MoreFilter);
		wb.ClickbyXpath(dict.MoreFilter);
		Thread.sleep(3000);
		if (FilterType.equalsIgnoreCase("Agent")) {
			wb.WaitUntillVisiblility(dict.PostedByAgent);
			wb.ClickbyXpath(dict.PostedByAgent);
		} else if (FilterType.equalsIgnoreCase("Builder")) {
			wb.WaitUntillVisiblility(dict.PostedByAgent);
			wb.ClickbyXpath(dict.PostedByAgent);
			wb.ClickbyXpath(dict.PostedByBuilder);
			wb.PageRefresh();
		} else if (FilterType.equalsIgnoreCase("Owner")) {
			wb.WaitUntillVisiblility(dict.PostedByOwner);
			wb.ClickbyXpath(dict.PostedByBuilder);
			wb.ClickbyXpath(dict.PostedByOwner);
			wb.PageRefresh();

		}
		Thread.sleep(2000);
		String URL = wb.CurrentURL();
		int res = wb.Get_Response(URL);
		if (res == 200) {
			if (URL.contains(FilterType)) {
				System.out.println("Response Code of " + URL + "is " + res);
				System.out.println("Filter Applied Successfully for " + FilterType);
			} else {
				System.err.println("Filter Did not applied Successfully for " + FilterType);
				System.out.println("Response Code of " + URL + "is " + res);
				return false;
			}
		} else {
			System.err.println("Response Code of " + URL + "is " + res);
			return false;
		}
		return true;
	}

}