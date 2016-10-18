package com.makaan.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makaan.Middleware.LoginMiddleware;

public class LoginTest {

	LoginMiddleware mw = new LoginMiddleware();

	@BeforeClass
	public void InitiateDriver() {
		System.out.println("Inside Test Initiate Driver");
		try {
			System.out.println("Inside Test Initiate Driver");
			if (mw.OpenURL()) {
				System.out.println("Url Response is 200, and page rendered on Screen");
			} else {
				Assert.assertTrue(false, "not able to open page due to Response code");
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find Makaan Logo on Page");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}

	@Test(priority=0)
	public void ValidateLoginButton() {
		System.out.println("Inside Test Validate Login Button");
		
		try {
			String res = mw.LoginForm();
			if (res.contains("Pass")) {
				System.out.println("Login Button is present");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}

	@Test(priority=1)
	public void ValidateSocialLogin() throws InterruptedException {
		System.out.println("Inside Test Validate Social Login");
		try {
			if (mw.SocialLogin()) {
				System.out.println("social login present");
			} else {

				Assert.assertTrue(false, "not able to validate Social login form");
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Validate Social Login on Page due to element not found" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(priority=2)
	 //@Test (enabled = false)
	public void ValidateForgotPassword() throws InterruptedException {
		System.out.println("Inside Test Forgot password");
		try {
			if (mw.ForgetPassword()) {

				System.out.println("Verified Forgot password");
			} else {
				Assert.assertTrue(false, "not able to Verify Forgot password  form");
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find Makaan Logo on Page" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

		@Test(priority=3)
	 //@Test (enabled = false)
	public void ValidateResetPassword() throws InterruptedException {
		System.out.println("Inside Test Reset password");
		try {
			if (mw.ResetPassword()) {

				System.out.println("Verified Reset password");
			} else {
				Assert.assertTrue(false, "not able to Verify Reset password  form");
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify reset password due to element not found" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(priority=4)
	 //@Test (enabled = false)
	public void ValidateMakaanLogin() throws InterruptedException {
		System.out.println("Inside Test Validate Makaan Login");
		try {
			if (mw.MakaanLogin()) {
				System.out.println("Verified Makaan Login");
			} else {
				Assert.assertTrue(false, "not able to validate MakaanLogin form");
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find Makaan login due to element not found on Page" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(priority=5)
	// @Test (enabled = false)
	public void ValidateMakaanLogout() throws InterruptedException {
		System.out.println("Inside Test Validate Makaan Logout");
		try {
			if (mw.MakaanLogout()) {
				System.out.println("Verified Makaan Logout");
			} else {
				Assert.assertTrue(false, "not able to validate MakaanLogout form");
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify makaan Logout due to element not found on Page" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}

	@Test(priority=6)
	// @Test (enabled = false)
	public void Signup() throws InterruptedException {
		System.out.println("Inside Test Validate Makaan Signup");
		try {
			if (mw.MakaanSignup()) {
				System.out.println("Verified Signup Functionality");

			} else {
				Assert.assertTrue(false, "not able to validate MakaanSignup form:");
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Signup due to element not found on Page" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@AfterClass
	public void Close() {
		try {
			mw.CloseAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
