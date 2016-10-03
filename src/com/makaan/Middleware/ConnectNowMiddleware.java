package com.makaan.Middleware;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.makaan.Dictionary.ConnectNow;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;

import java.io.IOException;
import java.sql.*;

public class ConnectNowMiddleware {

	public static Webhelper wb = null;
	public static ConnectNow dict = null;
	public static WebDriver driver = null;;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");

	public ConnectNowMiddleware() {
		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();

		System.out.println("inside Middleware Constructor");

	}

	public String OpenURL() throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		String URL = ReadSheet("CallNow", "URL", 2);
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

	public String ConnectNow()
			throws NoSuchElementException, TimeoutException, InterruptedException, IOException, SQLException {
		String OTP = null;
		System.out.println("Inside Call Now Test");
		wb.WaitUntillVisiblility(dict.ConnectNowButton);
		wb.Jsclickbyxpath(dict.ConnectNowButton);
		System.out.println(" element has been clicked ");
		if (wb.IsElementPresentById("leadPopup")) {
			System.out.println("Lead popup is present");
			System.out.println("Selecting Country code for india");
			if (!(wb.IsElementPresent(dict.LeadDropDown))) {
				System.err.println("Country code dropdown of Connect now is not present");
			}
			Thread.sleep(1000);
			wb.WaitUntillVisiblility(dict.MobileNumberInput);
			wb.ClickbyXpath(dict.MobileNumberInput);
			String MobileNumber = ReadSheet("CallNow", "Phone Number", 2);

			wb.enterTextByxpath(dict.MobileNumberInput, MobileNumber);
			Thread.sleep(2000);
			wb.ClickbyXpath(dict.CallNowSubmit);
			Thread.sleep(1000);

			if (wb.getText(dict.Error).contains("error")) {
				System.err.println("some error occurred while submiting Call Now Lead");
				return ("Fail: Error found while submitting Error");
			}

			else if (wb.IsElementPresent(dict.OTP_Screen)) {
				System.out.println("OTP screen appeared on lead form");
				if (ValidateBeforeOTP()) {
					System.out.println("ptigercrm.ENQUIRY_TEMP is updated before VErify OTP");
				} else {
					return ("Fail: ptigercrm.ENQUIRY_TEMP was not updated before Verify OTP");
				}
				if (VerifyOTP().contains("Fail")) {
					return ("Fail: some error occurred while submiting OTP from View Number");
				}
			}
			Thread.sleep(2000);
			if (wb.IsElementPresent(dict.TringCallNow)) {
				System.out.println("Lead form has successfully submitted");
				System.out.println("Now submitting Lead for Share my number");
				try {
					if (wb.IsElementPresent(dict.ShareMyNumber)) {
						wb.ClickbyXpath(dict.ShareMyNumber);
						if (ValidateShareNumber()) {

						} else {
							return ("Fail: Share Number Lead was npot Present in DB");
						}
						wb.WaitUntillVisiblility(dict.Thank);
						if (wb.IsElementPresent(dict.Thank)) {
							Thread.sleep(2000);
							wb.Jsclickbyxpath(dict.ThanksSubmit);
							// wb.ClickbyXpath(dict.ThanksSubmit);

						} else if (wb.getText(dict.Error).contains("error")) {
							wb.ClickbyXpath(dict.Skip);
							wb.WaitUntillVisiblility(dict.ThanksSubmit);
							wb.ClickbyXpath(dict.ThanksSubmit);
							return ("Fail: Some error occurred while submiting Share My datails Lead");
						}
					}
				} catch (NoSuchElementException e) {
					System.out.println("Share my number for similar properties was not present for this property");
					Thread.sleep(2000);
					wb.ClickbyXpath(dict.ThanksSubmit);
				}
			}

			return ("Pass: Connect Now Functionality completed successfully");
		} else {
			return ("Fail: Lead popup did not open");
		}

	}

	public String VerifyOTP() throws TimeoutException, NoSuchElementException, IOException, InterruptedException {
		String OTP = "4192";
		wb.enterTextByxpath(dict.OTP_input, OTP);
		wb.WaitUntillVisiblility(dict.Emailinput);
		wb.enterTextByxpath(dict.Emailinput, ReadSheet("CallNow", "Email", 2));
		wb.WaitUntillVisiblility(dict.Nameinput);
		wb.enterTextByxpath(dict.Nameinput, ReadSheet("CallNow", "Name", 2));
		wb.WaitUntillVisiblility(dict.SubmitOTP);
		wb.ClickbyXpath(dict.SubmitOTP);
		Thread.sleep(2000);
		wb.WaitUntillVisiblility(dict.InvalidOTP);
		if (wb.getText(dict.InvalidOTP).equals("otp is invalid")) {
			System.out.println("Click on resend OTP");
			Thread.sleep(4000);
			closechat();
			wb.scrolldown(dict.resendOTP);
			wb.scrollup(dict.resendOTP);
			Thread.sleep(2000);
			wb.ClickbyXpath(dict.resendOTP);
			Thread.sleep(2000);
			try {
				if (wb.getText(dict.resendOTPMessage).equals("we have sent an otp")) {
					System.out.println("OTP is resent");
					OTP = GetOTP();
					System.out.println("OTP found " + OTP);
					Thread.sleep(2000);
					wb.clearbox(dict.OTP_input);
					Thread.sleep(2000);
					wb.enterTextByxpath(dict.OTP_input, OTP);
					wb.WaitUntillVisiblility(dict.SubmitOTP);
					wb.ClickbyXpath(dict.SubmitOTP);

				}
			} catch (Exception e) {
				System.err.println("OTP resend is failed");
				return ("Fail: some error occurred while Resending OTP from View Number");
			}

		} else if (wb.getText(dict.Error).contains("error")) {
			return ("Fail: some error occurred while submiting OTP from View Number");
		}
		return ("Pass: OTP Submitted successfully");

	}

	public String GetOTP()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException, SQLException {
		String id = null;
		System.out.println("Inside verify OTP from DB");
		String Database = "use user ;";
		String Query = "select id from users where EMAIL = 'email_".concat(ReadSheet("CallNow", "Phone Number", 2))
				.concat("@email.com'");
		Thread.sleep(2000);// wait added to connect db after this time so that
							// table can be updated
		db.Connect();
		// String Phone = ReadSheet("CallNow", "Phone Number", 2);
		ResultSet rs = db.Execute(Query, Database);
		while (rs.next()) {
			id = rs.getString("ID");
			System.out.println("id " + id);
		}
		Query = "select otp from user.user_otps where user_id =".concat("'" + id + "'");
		rs = db.Execute(Query, Database);

		while (rs.next()) {
			id = rs.getString("OTP");
			System.out.println("OTP found is " + id);
		}
		return id;
	}

	public boolean ValidateShareNumber()
			throws InterruptedException, NoSuchElementException, IOException, TimeoutException, SQLException {
		String Database = "use proptiger;";
		String Query = "select * from ENQUIRY order by id desc limit 5";
		Thread.sleep(30000);
		db.Connect();
		String Phone = ReadSheet("CallNow", "Phone Number", 2);
		String Email = ReadSheet("CallNow", "Email", 2);

		ResultSet rs = db.Execute(Query, Database);
		while (rs.next()) {
			String id = rs.getString("ID");
			if (rs.getString("PHONE").equals(Phone) && (rs.getString("EMAIL").equals(Email))) {
				System.out.println("id " + id);
				System.out.println("Mobile number " + rs.getString("PHONE"));
				return true;
			}
		}
		System.err.println("Can not find phone number or Email in leads");
		return false;
	}

	public boolean ValidateBeforeOTP()
			throws InterruptedException, NoSuchElementException, IOException, TimeoutException, SQLException {
		String Database = "use ptigercrm ;";
		String Query = "select * from ENQUIRY_TEMP order by id desc limit 5";
		Thread.sleep(30000);
		db.Connect();
		String Phone = ReadSheet("CallNow", "Phone Number", 2);
		ResultSet rs = db.Execute(Query, Database);
		while (rs.next()) {
			String id = rs.getString("ID");
			if (rs.getString("PHONE").equals(Phone)) {
				System.out.println("id " + id);
				System.out.println("Mobile number " + rs.getString("PHONE"));
				return true;
			}
		}
		System.out.println("Can not find phone number in leads");
		return false;
	}

	public String ViewNumber(String Type)
			throws TimeoutException, NoSuchElementException, IOException, InterruptedException, SQLException {
		System.out.println("Inside ViewPhone Test");
		Thread.sleep(3000);
		if (Type.equals("Inner Lead")) {
			wb.WaitUntillVisiblility(dict.ConnectNowButton);
			wb.Jsclickbyxpath(dict.ConnectNowButton);
		} else {
			wb.WaitUntillVisiblility(dict.ViewPhoneButton);
			wb.Jsclickbyxpath(dict.ViewPhoneButton);
			Thread.sleep(3000);
		}
		if (wb.IsElementPresentById("leadPopup")) {
			System.out.println("Lead popup is present");
			if (!(Type.equals("Inner Lead"))) {
				if (!(wb.IsElementPresent(dict.SellerDetails))) {
					return ("FAIL: Seller Deatil card was not pesent from SERP View Phone Button");
				}
			}
			System.out.println("Selecting Country code for india");
			if (!(wb.IsElementPresent(dict.LeadDropDown))) {
				System.err.println("Country code dropdown of Connect now is not present");
			}
			Thread.sleep(1000);
			wb.WaitUntillVisiblility(dict.MobileNumberInput);
			wb.ClickbyXpath(dict.MobileNumberInput);
			String MobileNumber = ReadSheet("CallNow", "Phone Number", 2);
			wb.enterTextByxpath(dict.MobileNumberInput, MobileNumber);
			Thread.sleep(2000);
			if (Type.equals("Inner Lead")) {
				wb.WaitUntillVisiblility(dict.ViewPhone);
				wb.Jsclickbyxpath(dict.ViewPhone);
			} else {
				wb.WaitUntillVisiblility(dict.VerifyViewPhone);
				wb.Jsclickbyxpath(dict.VerifyViewPhone);
			}
			Thread.sleep(1000);
			if (wb.getText(dict.Error).contains("error")) {
				System.err.println("some error occurred while submiting Call Now Lead");
				return ("Fail: Error found while View Phone ");
			}
			/*if (Type.equals("Inner Lead")) {
				if (wb.IsElementPresent(dict.VerifyText)) {
					wb.enterTextByxpath(dict.MobileNumberInput, ReadSheet("CallNow", "Phone Number", 2));
					wb.ClickbyXpath(dict.VerifyViewPhone);
					if (wb.getText(dict.Error).contains("error")) {
						System.err.println("some error occurred while submiting Call Now Lead");
						return ("Fail: Error found while View Phone ");
					}
				} else {
					return ("FAIL: rify Your Number Form was not present for Inner Lead");
				}
			}
			if (wb.IsElementPresent(dict.OTP_Screen)) {
				System.out.println("OTP screen appeared on lead form");
				if (ValidateBeforeOTP()) {
					System.out.println("ptigercrm.ENQUIRY_TEMP is updated before VErify OTP");
				} else {
					return ("Fail: ptigercrm.ENQUIRY_TEMP was not updated before Verify OTP");
				}
				if (VerifyOTP().contains("Fail")) {
					return ("Fail: some error occurred while submiting OTP from View Number");
				}
			} else {
				return ("FAIL: OTP Screen was not present for View Phone");
			}*/
			/*if (Type.equals("Inner Lead")) {
				if (wb.IsElementPresent(dict.TringCallNow)) {
					System.out.println("OTP Submitted for View Number");
					if (wb.getText(dict.ViewPhone).contains("na")) {
						return ("Fail:Phone Number contains N/A");
					}
					wb.WaitUntillVisiblility(dict.Skip);
					wb.ClickbyXpath(dict.Skip);
					wb.WaitUntillVisiblility(dict.Thank);
					wb.ClickbyXpath(dict.ThanksSubmit);

				}*/
			//} else {
				wb.WaitUntillVisiblility(dict.SellerDetails);
				if (wb.IsElementPresent(dict.SellerDetails)) {
					System.out.println("seller detail is displayed");
					if (wb.getText(dict.PhoneText).equals("na")) {
						return ("Fail: There is no number present on view phone showing n/a");
					} else {
						System.out.println("Phone number of selller is: " + wb.getText(dict.PhoneText));
						System.out.println("Phone number was present on View phone");
					}
					Thread.sleep(2000);
					wb.Jsclickbyxpath(dict.PhoneSubmit);

				} else {
					return ("Seller details card was not present in View Number");
				}
			}
			else {
			return ("Fail: Lead popup did not open");
		}
		return ("Pass: View Phone number case got passed");

	}

	public String VerifyEnquiry()
			throws InterruptedException, NoSuchElementException, IOException, TimeoutException, SQLException {
		String Database = "use ptigercrm ;";
		String Query = "select * from LEAD_TMP_UPLOAD where MOBILE=8527019365  AND USER_ENQUIRY_TIME>= (now() - interval 30 minute) order by id desc limit 5;";

		Thread.sleep(5000);
		db.Connect();
		String Phone = ReadSheet("CallNow", "Phone Number", 2);
		ResultSet rs = db.Execute(Query, Database);
		while (rs.next()) {
			String id = rs.getString("ID");
			if (rs.getString("MOBILE").equals(Phone)) {
				System.out.println("id " + id);
				System.out.println("Mobile number " + rs.getString("MOBILE"));
				return ("Pass: Lead drop was successful for connect now found in LEAD_TMP_UPLOAD");
			}
		}
		System.out.println("Can not find phone number in leads");
		return ("Fail: Lead Drop was not successfull as LEAD_TMP_UPLOAD did not get updated");
	}

	public void CloseBrowser() {
		db.Close();
		wb.CloseBrowser();

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

	public String ContactSellers()
			throws InterruptedException, TimeoutException, NoSuchElementException, IOException, SQLException {
		System.out.println("Inside Contact Seller Test");
		Thread.sleep(2000);
		wb.WaitUntillVisiblility(dict.ContactSellerCard);
		if (wb.IsElementPresent(dict.ContactSellerCard)) {
			wb.ClickbyXpath(dict.ContactSellerCard);
			wb.scrollup(dict.ThankContactSeller);
			Thread.sleep(2000);
			System.out.println("Text on page is " + wb.getText(dict.TitleContactSeller));
			Thread.sleep(2000);
			wb.ClickbyXpath(dict.InputPhone);
			Thread.sleep(2000);
			String PhoneNumber = ReadSheet("CallNow", "Phone Number", 2);
			wb.enterTextByxpath(dict.InputPhone, PhoneNumber);
			Thread.sleep(2000);
			wb.ClickbyXpath(dict.SubmitContactSeller);
			Thread.sleep(2000);
			if (wb.IsElementPresent(dict.ThankContactSeller)) {
				System.out.println("Thanks Window displayed, enquiry submitted on UI");
				if (VerifyEnquiry().contains("Pass")) {
					System.out.println("Enquiry was present in table");
					return ("Pass: Enquiry was present in table");
				} else {
					System.out.println("Not able to find lead in Enquiry table");
					return ("Fail: Not able to find lead in Enquiry table");
				}
			} else if (wb.IsElementPresent(dict.ErrorSubmit)) {

			}
			return ("Fail: Some error occurred while submiiting Contact Seller");

		} else {
			return ("Fail: Contact Seller card was not present on SERP Page");
		}

	}
}
