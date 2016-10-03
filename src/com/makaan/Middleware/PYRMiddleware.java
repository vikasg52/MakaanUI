package com.makaan.Middleware;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.makaan.Dictionary.PYRDictionary;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;
//import com.sun.org.glassfish.external.statistics.annotations.Reset;

public class PYRMiddleware {

	public static Webhelper wb = null;
	public static PYRDictionary dict = null;
	public static WebDriver driver = null;;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");

	public PYRMiddleware() {
		wb = new Webhelper();
		db = new ConnectDB();
		System.out.println("inside Middleware Constructor");

	}

	public boolean OpenURL() throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		String URL = ReadSheet("PYR", "URL", 2);
		wb.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		res = wb.Get_Response(URL);
		System.out.println("Response code got from URL " + res);
		if (res == 200) {
			System.out.println("Waiting till Makaan logo found on page");
			wb.WaitUntillVisiblility(dict.MakaanLogo);
		} else {
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

	public static String PYRBuy(String Path)
			throws NoSuchElementException, InterruptedException, TimeoutException, IOException, SQLException {
		System.out.println("Validate PYR form for BUY Tab");
		if (Path.equals(" ")) {
			Path=dict.PYRLink;
		}	
			if (PYRRequirement("Buy",Path)) {
				UserDetails();
				Thread.sleep(1000);
				wb.ClickbyXpath(dict.PYRSubmit);
				String result = VerifyPYR();
				return result;
			} else {
				return ("Fail: Error while filling requirements");
			}
		
		
	}

	public static String PYRRent()
			throws NoSuchElementException, InterruptedException, SQLException, IOException, TimeoutException {
		System.out.println("Validate PYR form for Rent Tab");
		if (PYRRequirement("Rent", dict.PYRLink)) {
			wb.ClickbyXpath(dict.PYRSubmit);
			Thread.sleep(2000);
			try {
				if (wb.IsElementPresent(dict.ErrorPage)) {
					wb.ClickbyXpath(dict.Errorclose);
					return ("Fail: No Seller is available plz try later");
				}
			} catch (Exception e) {

			}
			String result = VerifyEnquiry("rent");
			wb.ClickbyXpath(dict.ClosePopup);
			if (result.contains("Pass")) {
				System.out.println("Enquiry is updated");
			} else {
				return result;
			}
			return result;
		} else {
			return ("Fail: Error while filling requirements");
		}
	}

	public static void UserDetails()
			throws InterruptedException, NoSuchElementException, IOException, TimeoutException {
		System.out.println("Entering User Details");
		Thread.sleep(2000);
		String UserName = ReadSheet("PYR", "UserName", 2);
		wb.enterTextByxpath(dict.UserName, UserName);
		Thread.sleep(2000);
		String Email = ReadSheet("PYR", "Email", 2);
		wb.enterTextByxpath(dict.UserEmail, Email);
		Thread.sleep(2000);
		String phone = "8527019365";
		wb.enterTextByxpath(dict.UserPhone, phone);

	}

	public static int GetOTP()
			throws NoSuchElementException, IOException, TimeoutException, SQLException, InterruptedException {
		System.out.println("Get OTP from database");
		ResultSet rs = null;
		String userID = null;
		int OTP = 0;
		String DB = "use user";
		String Query1 = "select id from users where email = '".concat(ReadSheet("PYR", "Email", 2)).concat("' ;");
		Thread.sleep(3000);
		rs = db.Execute(Query1, DB);
		while (rs.next()) {
			userID = rs.getString("id");
			System.out.println("User Id is " + userID);
		}
		String Query2 = "select * from user.user_otps where user_id = '".concat(userID).concat("' ;");
		rs = db.Execute(Query2, DB);
		Thread.sleep(5000);
		while (rs.next()) {
			OTP = rs.getInt("otp");
			System.out.println("OTP found in table is: " + OTP);
		}
		return OTP;
	}

	public static String VerifyEnquiry(String Type)
			throws InterruptedException, SQLException, NoSuchElementException, IOException, TimeoutException {
		System.out.println("Get Enquiry from database");
		String Database = "use proptiger ;";
		String Query = "Select * from ENQUIRY order by id desc limit 10; ";
		ResultSet rs = null;
		System.out.println("Verifying enquiry table");
		Thread.sleep(30000);
		db.Connect();
		rs = db.Execute(Query, Database);
		while (rs.next()) {
			String email = rs.getString("email");
			System.out.println(email);
			if (email.equals(ReadSheet("PYR", "Email", 2))) {
				if (rs.getString("Lead_Sale_Type").equalsIgnoreCase(Type)) {

				} else {
					return ("Fail: Enquiry is not updated for " + Type);
				}
			}
		}
		return ("Pass: Enquiry Table is updated");
	}

	public static String VerifyPYR()
			throws NoSuchElementException, InterruptedException, SQLException, IOException, TimeoutException {
		System.out.println("Verifing PYR");
		Thread.sleep(3000);
		int OTP = 0;
		try {
			wb.WaitUntillVisiblility(dict.OTPPage);
			if (wb.IsElementPresent(dict.OTPPage)) {
				System.out.println("PYR form submited");
				db.Connect();
				OTP = GetOTP();
				if (OTP == 0) {
					return ("Fail: OTP found in DB is " + OTP);
				} else {
					wb.enterTextByxpath(dict.OTPBox, OTP);
					System.out.println("Entered Otp");
					wb.WaitUntillVisiblility(dict.ClosePopup);
					wb.ClickbyXpath(dict.ClosePopup);
					if (VerifyEnquiry("buy").contains("Pass")) {
						System.out.println("Enquiry is updated");
					} else {
						return ("Fail: Enquiry is not updated");
					}
				}
			}
		} catch (Exception e) {
			try {
				if (wb.IsElementPresent(dict.ErrorPage)) {
					System.err.println("No Seller is available plz try later");
					wb.ClickbyXpath(dict.Errorclose);
					return ("Fail: NO seller was present ");
				}
			} catch (Exception ne) {
				if (wb.IsElementPresent(".//div[@class='thanks-image']")) {
					System.out.println("Thanks image appeared case passed");
					wb.ClickbyXpath(dict.ClosePopup);
					if (VerifyEnquiry("buy").contains("Pass")) {
						System.out.println("Enquiry is updated");
						return ("Pass: Enquiry is updated ");
					}
					System.err.println("Enquiry table is not updated");
					return ("Fail: Enquiry Table is not updated");
				}
			}

		}
		return ("Pass: PYR is verified Successfully ");
	}

	public static boolean PYRRequirement(String Tab, String Path)
			throws InterruptedException, TimeoutException, NoSuchElementException, IOException {
		System.out.println("Inside PYR VAlidation");
		wb.ClickbyXpath(Path);
		Thread.sleep(2000);
		if (wb.IsElementPresent(dict.PYRForm)) {
			if (Tab.equalsIgnoreCase("buy")) {
				wb.WaitUntillVisiblility(dict.rentTab);
				wb.ClickbyXpath(dict.rentTab);
				wb.WaitUntillVisiblility(dict.buyTab);
				wb.ClickbyXpath(dict.buyTab);
			} else if (Tab.equalsIgnoreCase("rent")) {
				wb.ClickbyXpath(dict.buyTab);
				wb.ClickbyXpath(dict.rentTab);
			}
			wb.Slider(dict.Slider);
			System.out.println("Minimum Range is " + wb.getText(dict.MinBudget));
			System.out.println("MAximum Range is " + wb.getText(dict.MaxBudget));
			String Bedroom = ReadSheet("PYR", "Bedroom", 2);
			Thread.sleep(1000);
			wb.ClickbyXpath(dict.Bedroom.concat(Bedroom).concat("']"));
			wb.WaitUntillVisiblility(dict.Property);
			wb.ClickbyXpath(dict.Property);
			String PropertyType = ReadSheet("PYR", "Property Type", 2);
			wb.ClickbyXpath(dict.PropertyType.concat(PropertyType).concat("']"));
			wb.ClickbyXpath(dict.Apply);
			wb.WaitUntillVisiblility(dict.Location);
			wb.ClickbyXpath(dict.Location);
			String LocalityValue = ReadSheet("PYR", "Locality", 2);
			Thread.sleep(4000);
			wb.WaitUntillVisiblility(dict.Locality);
			wb.enterTextByxpath(dict.Locality, LocalityValue);
			Thread.sleep(1000);
			wb.getText(dict.GetfirstElement);
			Thread.sleep(2000);
			wb.ClickbyXpath(dict.GetfirstElement);
			Thread.sleep(3000);
			wb.ClickbyXpath(dict.Apply);
		} else {
			System.err.println("PYR form was not available");
			return false;
		}
		return true;
	}

	public static void closechat() throws Exception {
		if (wb.IsElementPresentById("inner-wrapper")) {
			wb.ClickbyXpath(".//textarea[@id='input']");
			wb.ClickbyXpath(".//div[@class='cross']");
			System.out.println("Closed mchat popup");
		} else {
			System.out.println("mchat popup was not present");
		}
	}

	public static void CloseAll() {
		db.Close();
		wb.CloseBrowser();
	}
}
