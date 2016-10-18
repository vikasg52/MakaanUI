package com.makaan.Middleware;

import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.makaan.Dictionary.Search;

public class SearchMiddleware {

	public static Webhelper wb = null;
	public static Search dict = null;
	public static WebDriver driver;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");
	String result = null;

	public SearchMiddleware() {
		System.out.println("inside Search Middleware Constructor");

		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();
		System.out.println("inside Middleware Constructor");

	}

	public boolean OpenURL() throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		String URL = ReadSheet("Search", "URL", 2);
		Webhelper.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		res = Webhelper.Get_Response(URL);
		if (res == 200) {
			System.out.println("Response code got from URL " + res);
			System.out.println("Waiting till Makaan logo found on page");
			wb.WaitUntillVisiblility(Search.MakaanLogo);
		} else {
			System.err.println("Response code got from URL " + res);
			return false;
		}
		return true;
	}

	public String ReadSheet(String Sheet, String Col_Name, int row_id)
			throws IOException, NoSuchElementException, TimeoutException {

		String data = xls.getCellData(Sheet, Col_Name, row_id);
		System.out.println("Data from sheet " + data);

		return (data);
	}

	public String ValidateSearch() {
		if (wb.IsElementPresent(Search.SearchBox)) {
			System.out.println("Search box exist");
			if (wb.IsElementPresent(Search.BuyTab) && (wb.IsElementPresent(Search.RentTab))) {
				System.out.println("buy and renttab is present on search box");
			} else {
				return ("Fail: buy and renttab is not present on search box");
			}

		} else {
			return ("Fail: not able find Search box on home page");
		}

		return ("Pass: Search Box Validation is completed ");
	}

	public static void closechat()  {
		try {
			wb.WaitUntill(Search.chatPopup);
			if (wb.IsElementPresent(Search.chatPopup)) {
				// wb.ClickbyXpath(".//textarea[@id='input']");
				wb.scrollinView(Search.CloseChat);
				wb.ClickbyXpath(Search.CloseChat);
				System.out.println("Closed mchat popup");
			}
		} catch (Exception e) {
			System.out.println("mchat popup was not present");
		}

	}
	public String ValidateSuggestions() throws TimeoutException, InterruptedException {
		List<String> arr = new ArrayList<String>();
		wb.WaitUntill(Search.SearchBox);
		//closechat();
		wb.ClickbyXpath(Search.SearchBox);
		Thread.sleep(2000);
		wb.WaitUntillVisiblility(Search.Suggestiontype);
		arr = wb.GetElementvalues(Search.Suggestiontype);
		for (int i = 0; i < arr.size(); i++) {
			System.out.println("element on Search bar is: " + arr.get(i));
			if ((arr.get(i).contains("mumbai")) || (arr.get(i).contains("gurgaon"))) {
				System.out.println("Popular Suggestions found on page are " + arr.get(i));
			} else {
				return ("Fail: Popular Suggestions was not present on search box");			
			}
		}
		return ("Pass: Popular Suggestions were present on page");

	}

	public String ValidateBuyLocality()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		result = ValidateGeneric("Buy", "Location");
		if (result.contains("Pass")) {	
			return ("Pass: All Localities been Tested for Buy Tab Successfuly");
		
		} else {
			return ("Fail: All Localities Tested for Buy Tab are not successfull "+result);
		}

	}

	public String ValidateRentLocality()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		result = ValidateGeneric("Rent", "Location"); 
			if (result.contains("Pass")) {
			return ("Pass: All Localities been Tested for Rent Tab Successfuly");
		} else {
			return ("Fail: All Localities Tested for Rent Tab are not successfull "+result);
		}

	}

	public String ValidateBuyProject()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		
		result = ValidateGeneric("Buy", "Project"); 
			if (result.contains("Pass")) {
			return ("Pass: All Project has been Tested for Buy Tab Successfuly");
		} else {
			return ("Fail: All Project Tested for Buy Tab are not successfull" +result);
		}

	}

	public String ValidateRentProject()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		result = ValidateGeneric("Rent", "Project");
			if (result.contains("Pass")) {
			return ("Pass: All Project has been Tested for Rent Tab Successfuly");
		} else {
			return ("Fail: All Project Tested for Rent Tab are not successfull");
		}

	}

	public String ValidateBuilder()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		result = ValidateGeneric("Buy", "Builder");
			if (result.contains("Pass")) {
			return ("Pass: All Builder has been Tested for Buy Tab Successfuly");
		} else {
			return ("Fail: All Builder Tested for Buy Tab are not successfull"+result);
		}

	}

	public String ValidateLandmarksBuy()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		
		result = ValidateGeneric("Buy", "Landmark");
			if (result.contains("Pass")) {
			return ("Pass: All Landmarks has been Tested for Buy Tab Successfuly");
		} else {
			return ("Fail: All Landmark Tested for Buy Tab are not successfull" +result);
		}

	}

	public String ValidateLandmarksRent()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		result = ValidateGeneric("Rent", "Landmark");
		if (result.contains("Pass")) {
			return ("Pass: All Landmarks has been Tested for Rent Tab Successfuly");
		} else {
			return ("Fail: All Landmark Tested for Rent Tab are not successfull");
		}

	}

	public String ValidateSuburbRent()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
			result = ValidateGeneric("Rent", "Suburb");
		if (result.contains("Pass")) {
			return ("Pass: All suburb has been Tested for Rent Tab Successfuly");
		} else {
			return ("Fail: All suburb Tested for Rent Tab are not successfull");
		}

	}

	public String ValidateSuburbBuy()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
			result = ValidateGeneric("Buy", "Suburb");
		if (result.contains("Pass")) {
			return ("Pass: All suburb has been Tested for Buy Tab Successfuly");
	
		} else {
			return ("Fail: All Suburb Tested for Buy Tab are not successfull "+result );
		}

	}

	public String ValidateLandmarkBuy()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		result = ValidateGeneric("Buy", "Landmark");
		if (result.contains("Pass")) {
			return ("Pass: All Landmark has been Tested for Buy Tab Successfuly");
		} else {
			return ("Fail:All Landmark Tested for Buy Tab are not successfull "+result);
		}

	}

	public String ValidateLandmarkRent()
			throws NoSuchElementException, TimeoutException, IOException, InterruptedException {
		String result = null;
		result= ValidateGeneric("Rent", "Landmark");
		if(result.contains("Pass")){
			return ("Pass: All Landmark has been Tested for rent Tab Successfuly");
		} else {
				return ("All Landmark Tested for Rent Tab are not successfull "+result);
		}

	}

	public String ValidateGeneric(String Tab, String Column)
			throws TimeoutException, NoSuchElementException, IOException, InterruptedException {
		List<String> arr = new ArrayList<String>();
		String TabSelect = null;
		wb.WaitUntill(Search.SearchBoxInput);
		wb.ClickbyXpath(Search.SearchBoxInput);
		List<String> arr1 = new ArrayList<String>();
		if (Tab.equals("Buy")) {
			TabSelect = Search.BuyTab;
		} else if (Tab.equals("Rent")) {
			TabSelect = Search.RentTab;
		}
		Thread.sleep(2000);
		for (int i =2; i < 6; i++) {
			arr1.add(ReadSheet("search", Column, i));
			System.out.println(arr1.toString());
		}
			for (int j = 0; j < arr1.size(); j++) {
				System.out.println("value of index is: " + j);
				String result = null;
				closechat();
				wb.WaitUntill(TabSelect);
				wb.ClickbyXpath(TabSelect);
				closechat();
				wb.WaitUntill(Search.SearchBoxInput);
				wb.enterTextByxpath(Search.SearchBoxInput, arr1.get(j));
				closechat();
				System.out.println("data in array sheet is: " + arr1.get(j));
				wb.WaitUntillVisiblility(Search.Suggestiontype);
				arr = wb.GetElementvalues(Search.Suggestiontype);
				if (Column.equals("Landmark")) {
					for (int k = 0; k < arr.size(); k++) {
						if (wb.getText(Search.WrongSearch).contains("sorry")) {
							return ("Fail: No Search Suggestions found for " +Column +" "+arr1.get(j));

						} else if ((arr.get(k).contains(arr1.get(j)))
								&& ((wb.getText(Search.Propertytype)).equalsIgnoreCase(Column))) {
							System.out.println("Search for Landmark is matching with data provided: " + arr.get(k));
							System.out.println("value of search index is:" +k);
							int m = k + 1;
							String Var = Search.FirstSearchSuggestion + m + "']";
							 result = ValidateSearchURL(Var);
							if (result.contains("Pass")) {
								System.out.println("return true from validate Search page");
								break;
								//return true;
							} else {
								return ("Fail: The Page validation was not validated for "+arr1.get(j));
							}
						} else if(k >arr.size()){
							return ("Fail: Not able to find value in suggestions "+arr1.get(j));
						}
						else{
							return ("Fail: Not able to verify Property Type with suggestion "+arr1.get(j));
							
						}
					}
					
				} else if (Column.equals("Builder")) {
					for (int k = 0; k < arr.size(); k++) {
						if (wb.getText(Search.WrongSearch).contains("sorry")) {
							return ("Fail: No Search Suggestions found" + "Builder" + arr1.get(j));
						} else if ((arr.get(k).contains(arr1.get(j)))
								&& ((wb.getText(Search.Propertytype)).equalsIgnoreCase(Column))) {
							System.out.println("Search for Builder is matching with data provided: " + arr1.get(j));
							System.out.println("value of k" +k);
							int m = k + 1;
							String Var = Search.FirstSearchSuggestion + m + "']";
							result = ValidateSearchURL(Var);
							if (result.contains("Pass")) {
								System.out.println("return true from validate Search page");
								break;
							} else {
								return ("Fail: The Page validation was not validated for " +arr1.get(j));
							}
						} else if(k >arr.size()){
							return ("Fail: Not able to find value in suggestions "+ arr1.get(j));
						
						}
						else{
							return ("Fail: Not able to verify Property Type with suggestion "+arr1.get(j));
						}
					}
					
				} else {
					for (int k = 0; k < arr.size(); k++) {
						if (arr.get(k).equalsIgnoreCase(arr1.get(j))) {
							System.out.println("Search for Locality is matching with data provided: " + arr.get(k));
							int m = k + 1;
							System.out.println("value of k" +k);
							String Var = Search.FirstSearchSuggestion + m + "']";
							System.out.println(Var);
							result = ValidateSearchURL(Var);
							if (result.contains("Pass"))  {
								System.out.println("return true from validate Search page");
								break;
							} else {
								return ("Fail: The Page validation was not validated for "+arr1.get(j));
								
							}
						} else if (wb.getText(Search.WrongSearch).contains("sorry")) {
							return ("Fail: No Search Suggestions found "+arr1.get(j));
						} else if(k >arr.size()){
							return ("Fail: Not able to find value in suggestions "+arr1.get(j));
						}
						else{
							return ("Fail: Not able to verify Property Type with suggestion "+arr1.get(j));
						}
					}
					
				}
			}
			closechat();
		
			if (wb.IsElementSelected(Search.BuyTab)) {
				wb.WaitUntill(Search.RentTab);
				wb.ClickbyXpath(Search.RentTab);
				Thread.sleep(2000L);
			} else if (wb.IsElementSelected(Search.RentTab)) {
				wb.WaitUntill(Search.BuyTab);
				Thread.sleep(3000L);
				wb.ClickbyXpath(Search.BuyTab);
			}
	
		return ("Pass ");

	}

	public String ValidateSearchURL(String Element) throws IOException, InterruptedException {
		wb.ClickbyXpath(Element);
		String URL = wb.CurrentURL();
		int res = Webhelper.Get_Response(URL);
		if (res == 200) {
			System.out.println("Response Code of " + URL + "is " + res);
			Thread.sleep(4000);
			if (wb.IsElementPresent(".//div[@class='lsfix clearfix']")) {
				System.out.println("page content load successfully");
			} else {
				wb.Back();
				return ("Fail: Element was not present on page "+Element);
			}
		} else {
			wb.Back();
			return ("Fail:Response Code of " + URL + "is " + res);
		}
		wb.Back();
		return ("Pass: Page Validation is completed successfully");

	}

	public static void CloseAll() {
		db.Close();
		wb.CloseBrowser();
	}
}
