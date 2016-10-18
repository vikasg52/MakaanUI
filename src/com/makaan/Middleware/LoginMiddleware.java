package com.makaan.Middleware;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.makaan.Dictionary.Login;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;

public class LoginMiddleware {

	public static Webhelper wb = null;
	public static Login dict = null;
	public static WebDriver driver = null;;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");

	public LoginMiddleware() {
		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();

		System.out.println("inside Middleware Constructor");

	}

	public boolean OpenURL() throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		String URL = ReadSheet("Login", "URL", 2);
		Webhelper.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		res = Webhelper.Get_Response(URL);
		if (res == 200) {
			System.out.println("Response code got from URL " + res);
			System.out.println("Waiting till Makaan logo found on page");
			SearchMiddleware.closechat();
			wb.WaitUntillVisiblility(Login.MakaanLogo);
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

	public String LoginForm() throws TimeoutException {
		wb.WaitUntillVisiblility(Login.Login);
		if (wb.IsElementPresent(Login.Login)) {
			return ("Pass: Login Form is validated");

		} else {
			return ("Fail: Login form is not validated");
		}

	}

	public boolean  SocialLogin()
			throws InterruptedException, TimeoutException, NoSuchElementException, IOException {
		Thread.sleep(3000);
		SearchMiddleware.closechat();
		wb.WaitUntillVisiblility(Login.Login);
        SearchMiddleware.closechat();
		wb.Jsclickbyxpath(Login.Login);
		wb.WaitUntill(Login.facebookLogin);
		//wb.WaitUntill(Login.facebookLogin);
		if (wb.IsElementPresent(Login.facebookLogin) && wb.IsElementPresent(Login.GoogleLoginWindow)) {
			System.out.println("Social login button  pesent");
			if (VerifyFBLogin() && VerifyGoogleLogin()) {
				System.out.println("Social login is verified");
				//wb.WaitUntillIDVisibility(Login.closeLogin);
				//wb.ClickbyXpath(Login.closeLogin);
			}
			else {
				wb.WaitUntillVisiblility(Login.closeLogin);
				wb.Jsclickbyxpath(Login.closeLogin);
				System.err.println("Social login can not be verified due to error");
				return false;
			}
		}
		return true;
	}

	public static boolean VerifyFBLogin() throws TimeoutException, InterruptedException, IOException {
		System.out.println("verifying fb login");
		//Thread.sleep(2000);
		driver = wb.getDriver();
		String parentHandle = driver.getWindowHandle();
		wb.WaitUntillVisiblility(Login.facebookLogin);
		driver.findElement(By.xpath(Login.facebookLogin)).click();
		Thread.sleep(2000);

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);

		}
		try {
			wb.WaitUntillVisiblility(Login.FBUserId);
			if (driver.findElement(By.xpath(Login.FBUserId)).isDisplayed()) {
				System.out.println("Element is present on Switch Window");
				driver.findElement(By.xpath(Login.FBUserId)).sendKeys(ReadSheet("Login", "UserName", 2));
				driver.findElement(By.xpath(Login.FBUserPass)).sendKeys(ReadSheet("Login", "Password", 2));
				driver.findElement(By.xpath(Login.FBLoginButton)).click();
				Thread.sleep(5000);
                driver.switchTo().window(parentHandle);
				/*
				 * String UserName = ReadSheet("Login", "UserName", 2); UserName
				 * = UserName.substring(0, 1); if
				 * (wb.getText(dict.VerifyMakaanLogin1).equalsIgnoreCase(
				 * UserName)) { System.out.println("Login successfull");
				 * Thread.sleep(2000); MakaanLogout(); return true; } else {
				 * System.out.println("Fb Login doesnot happen"); return false;
				 * }
				 */

			}
		} catch (NoSuchElementException ne) {
			System.err.println("Some error occurred in fb Login ");
			return false;
		}
		MakaanLogout();
		return true;
	}

	public static boolean VerifyGoogleLogin() throws TimeoutException, InterruptedException, IOException {
		Thread.sleep(2000);
		System.out.println("verifying social login");
		wb.WaitUntill(Login.Login);
		SearchMiddleware.closechat();
		wb.Jsclickbyxpath(Login.Login);
		Thread.sleep(2000);
		driver = wb.getDriver();
		String parentHandle = driver.getWindowHandle();
		driver.findElement(By.xpath(Login.GoogleLoginWindow)).click();
		Thread.sleep(2000);

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);

		}
		try {
			wb.WaitUntill(Login.GoogleUserId);
			if (driver.findElement(By.xpath(Login.GoogleUserId)).isDisplayed()) {
				System.out.println("Element is present on Google form");
				driver.findElement(By.xpath(Login.GoogleUserId)).sendKeys(ReadSheet("Login", "UserName", 3));
				driver.findElement(By.xpath(Login.GoogleNext)).click();
				wb.WaitUntill(Login.GooglePass);
				driver.findElement(By.xpath(Login.GooglePass)).sendKeys(ReadSheet("Login", "Password", 3));
				driver.findElement(By.xpath(Login.GoogleLogin)).click();
				Thread.sleep(4000);
				driver.switchTo().window(parentHandle);
				/*
				 * String UserName = ReadSheet("Login", "UserName", 2); UserName
				 * = UserName.substring(0, 1); if
				 * (wb.getText(dict.VerifyMakaanLogin1).equalsIgnoreCase(
				 * UserName)) { System.out.println("Login successfull");
				 * Thread.sleep(2000);
				 * 
				 * return true; } else { System.out.println(
				 * "Fb Login doesnot happen"); return false; }
				 */

			}
		} catch (NoSuchElementException ne) {
			System.err.println("can not find Google Used Id");
			return false;
		}
		MakaanLogout();
		return true;
	}

	public boolean MakaanLogin() throws TimeoutException, NoSuchElementException, IOException, InterruptedException {
		System.out.println("inside mkaan login");
		Thread.sleep(3000);
		wb.WaitUntill(Login.Login);
		SearchMiddleware.closechat();
		wb.Jsclickbyxpath(Login.Login);
		wb.WaitUntillVisiblility(Login.BasicLogin);
		if (wb.IsElementPresent(Login.BasicLogin)) {
			System.out.println("makaan login button is present on form");
			wb.Jsclickbyxpath(Login.BasicLogin);
			wb.WaitUntillVisiblility(Login.MakaanForm);
			if (wb.IsElementPresent(Login.MakaanForm)) {
				System.out.println("makaan login form is present");
				String Username = ReadSheet("Login", "UserName", 4);
				String Password = ReadSheet("Login", "Password", 4);
				wb.enterTextByID(Login.UserName, Username);
				Thread.sleep(1000);
				wb.enterTextByID(Login.Password, Password);
				wb.WaitUntill(Login.BasicLoginButton);
				wb.Jsclickbyxpath(Login.BasicLoginButton);
				wb.WaitUntillVisiblility(Login.VerifyMakaanLogin1);
				String Initials = Username.substring(0, 1);

				if (wb.getText(Login.VerifyMakaanLogin1).equalsIgnoreCase(Initials)) {
					System.out.println("Login successfull");
					Thread.sleep(2000);

				} else if (wb.IsElementPresent(Login.VerifyMakaanLogin)) {
					System.err.println("Error found while makaan Login closing popup now");
					SearchMiddleware.closechat();
					wb.Jsclickbyxpath(Login.closeLogin);
					return false;
				}

			} else {
				System.err.println("Not able to find makaan form");
				return false;
			}
		} else {
			System.err.println("Basic Login button does not found");
			return false;
		}
		return true;
	}

	public static boolean ForgetPassword()
			throws TimeoutException, NoSuchElementException, IOException, InterruptedException {
		System.out.println("inside Forgotpassword in middleware");
		SearchMiddleware.closechat();
		wb.WaitUntillVisiblility(Login.Login);
		wb.Jsclickbyxpath(Login.Login);
		wb.WaitUntill(Login.BasicLogin);
		wb.Jsclickbyxpath(Login.BasicLogin);
		wb.WaitUntill(Login.Forgotpassword);
		if (wb.IsElementPresent(Login.Forgotpassword)) {
			System.out.println("Forgot password button is present on form");
			wb.Jsclickbyxpath(Login.Forgotpassword);
			wb.WaitUntill(Login.ForgetPasswordWindow);
			if (wb.getText(Login.ForgetPasswordWindow).equals("reset password")) {
				System.out.println("Forget password button on window is present");
				String Username = ReadSheet("Login", "UserName", 4);

				wb.enterTextByxpath(Login.ForgotpasswordInput, Username);
				wb.WaitUntill(Login.ForgetSubmit);
				wb.Jsclickbyxpath(Login.ForgetSubmit);
				wb.WaitUntillVisiblility(Login.VerifyForgotPassword);
				String Message = wb.getText(Login.VerifyForgotPassword);
				if (Message.contains("sorry")) {
					System.err.println("error occured while reset password may be wrong email or network issue");
					wb.ClickbyXpath(Login.closeLogin);
					return false;
				} else if (Message.contains("a link to reset password has been sent to your email id")) {
					if (VerifyEmail(Username)) {
						System.out.println("successfully verify email for forgot password");
						return true;
					} else
						System.err.println("verification failed for forgot password email ");
					return false;
				}
				return true;
			} else {
				System.err.println("Reset password window did not open");
				return false;
			}
		}

		else {
			System.err.println("Forgot password button does not present");
			return false;
		}

	}

	public static boolean VerifyEmail(String data) throws InterruptedException {
		ResultSet rs = null;
		String id = null;
		String Query = "select * from users where email = '" + data + "' order by id desc limit 1;";
		String Database = "use user";
		try {
			db.Connect();
			rs = db.Execute(Query, Database);
			while (rs.next()) {
				// Retrieve by column name
				id = rs.getString("id");
				System.out.println("id: " + id);
			}
			Query = "select * from notification_generated where user_id = " + id + " order by id desc limit 1;";
			Database = "use notification";
			rs = db.Execute(Query, Database);
			while (rs.next()) {
				// Retrieve by column name
				String status = rs.getString("status");
				System.out.println("status: of forgot password mail " + status);
				if (status.equals("Scheduled") || status.equals("Sent") || status.equals("Generated")) {
					System.out.println("Forgot email veification is done successfully");
				} else {
					System.err.println("mail was not sent status of mail is " + status);
					return false;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return true;
	}

	public static boolean ResetPassword() throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		String URL = GetToken();
		wb.GetURL(URL);
		SearchMiddleware.closechat();
		int res = Webhelper.Get_Response(URL);
		if(res ==200){
			System.out.println("Response code of "+ URL + "is "+ res);
		if (wb.IsElementPresent(Login.Newpassword)) {
			wb.enterTextByxpath(Login.Newpassword,"abcd1234");
			wb.enterTextByxpath(Login.Confirmpassword, "abcd1234");
			wb.Jsclickbyxpath(Login.resetPassword);
			Thread.sleep(4000);
			wb.WaitUntillVisiblility(Login.Login);
			if (wb.IsElementPresent(Login.Login)) {
				System.out.println("Reset password Case Passed");
			} else if (wb.IsElementPresent(Login.VerifyresetPassword)) {
				System.err.println("Some error occured while reseting password");
				return false;
			}
			return true;
		} else {
			System.err.println("URL did not open for reset password");

			return false;
		}
	}else{
		System.err.println("Response code of "+ URL + "is "+ res);
		return false;
	}
	}

	public static String GetToken() throws NoSuchElementException, IOException, TimeoutException {
		String data = ReadSheet("Login", "UserName", 4);
		ResultSet rs = null;
		String id = null;
		String URL = null;
		String Query = "select * from users where email = '" + data + "' order by id desc limit 1 ;";
		String Database = "use user";
		try {
			db.Connect();
			rs = db.Execute(Query, Database);
			while (rs.next()) {
				// Retrieve by column name
				id = rs.getString("id");
				System.out.println("id: " + id);
			}
			Query = "select * from forum_user_token where user_id =  " + id + ";";
			rs = db.Execute(Query, Database);
			while (rs.next()) {
				// Retrieve by column name
				String Token = rs.getString("token");
				System.out.println("token of forgot password mail " + Token);
				URL = ReadSheet("Login", "URL", 2);
				URL = URL.concat("/reset-password?token=");
				URL = URL.concat(Token);
			}

		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
		return URL;
	}

	public static boolean MakaanLogout() throws TimeoutException, InterruptedException {
		System.out.println("inside MakaanLogout in middleware");
		//wb.scrollinView(Login.MenuDrawer);
		wb.WaitUntill(Login.MenuDrawer);
		SearchMiddleware.closechat();
		if(wb.IsElementPresent(Login.MenuDrawer))
		{
		System.out.println("Menu drawer is present..");}
		else{
			wb.PageRefresh(); 
			Thread.sleep(3000L);}
		wb.Jsclickbyxpath(Login.MenuDrawer);
		wb.WaitUntillVisiblility(Login.Logout);
		if (wb.IsElementPresent(Login.Logout)) {
			SearchMiddleware.closechat();
			wb.Jsclickbyxpath(Login.Logout);
			Thread.sleep(1000);
			System.out.println("successfully logged out");
			return true;
		} else {
			System.err.println("Not able to find Logout button on form");
			return false;
		}

	}

	public boolean MakaanSignup() throws TimeoutException, NoSuchElementException, IOException, InterruptedException {
		System.out.println("inside SignUp in middleware");
		Thread.sleep(2000);
		SearchMiddleware.closechat();
		wb.WaitUntillVisiblility(Login.Login);
		wb.Jsclickbyxpath(Login.Login);
		Thread.sleep(2000);
		if (wb.IsElementPresent(Login.Signup)) {
			System.out.println("Create Acount button is present on form");
			wb.Jsclickbyxpath(Login.Signup);
			Thread.sleep(1000);
			wb.WaitUntillVisiblility(Login.SignupWindow);
			if (wb.getText(Login.SignupWindow).equals("let the joy begin")) {
				System.out.println("Signup window is present");
				String Username = ReadSheet("Login", "UserName", 3);
				String Pass = ReadSheet("Login", "Password", 3);
				Username = Splitter(Username);
				String Name = ReadSheet("Login", "Name", 4);
				wb.enterTextByID(Login.SignupName, Name);

				wb.enterTextByID(Login.SignupEmail, Username);

				wb.enterTextByID(Login.SignupPassword, Pass);
				wb.Jsclickbyxpath(Login.SignupSubmit);
				Thread.sleep(3000);
				if (wb.getText(Login.VerifySignup).equalsIgnoreCase(Name)) {
					wb.Jsclickbyxpath(Login.skipseller);
					System.out.println("Sign up was successfull");
					if (wb.getText(Login.VerifyMakaanLogin1).equalsIgnoreCase(Username.substring(0, 1))) {
						System.out.println("Login successfull");
						return true;
					}
				} else if (wb.getText(Login.ErrorSignup).contains("User already registered")) {
					System.err.println("Not able to perform signup successfully as user already registered");
					wb.Jsclickbyxpath(Login.closeLogin);
					return false;
				}
				else if (wb.getText(Login.ErrorSignup).contains("error")) {
					System.err.println("Not able to perform signup successfully as Throws some error");
					wb.Jsclickbyxpath(Login.closeLogin);
					SearchMiddleware.closechat();
					return false;
				}

			} else {
				System.err.println("Signup button do not open");
				return false;
			}
			return true;
		} else {
			System.err.println("Create account button does not present");
			return false;
		}

	}

	public String Splitter(String Value) {
		Random r = new Random();
		int Low = 10;
		int High = 1000;
		int Result = r.nextInt(High - Low) + Low;
		String front = Value.substring(0, 11);
		String back = Value.substring(11, 20);
		front = front + "+" + Result + back;
		System.out.println("String " + front);
		return front;

	}

	public static void CloseAll() {
		db.Close();
		wb.CloseBrowser();
	}

}
