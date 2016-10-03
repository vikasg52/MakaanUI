package com.makaan.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makaan.Middleware.ConnectNowMiddleware;;

public class ConnectNowTest {

	ConnectNowMiddleware in = new ConnectNowMiddleware();

	@BeforeClass
	public void InitiateDriver() {
		try {
			System.out.println("Inside Test Initiate Driver");

			String Res = in.OpenURL();
			System.out.println(Res);
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find Makaan Logo on Page");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}

	@Test(priority = 1)
	// @Test(enabled = false)
	public void ConnectNow() throws Exception {
		System.out.println("Inside Test Connect Now");
		try {
			String res= in.ConnectNow();
					if(res.contains("Pass")){
				System.out.println("Successfully completed Connect Now Functionality");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false,
					"not able to validate connect Now functionality as element was not found on window" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate connect Now due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}
	@Test(priority = 2)
	// @Test (enabled = false)
	public void ValidateDB() throws InterruptedException {
		System.out.println("Inside Test VAlidateDB");
		try {
			String res= in.VerifyEnquiry();
			if (res.contains("Pass")) {
				System.out.println("Successfully completed Validate DB Test");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate DB due to time out" );
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (SQLException s) {
			Assert.assertTrue(false, "not able to open DB");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 3)
	public void ViewNumberInLead() throws InterruptedException {
		System.out.println("Inside Test View Phone Number");
		try {
			String res = in.ViewNumber("Inner Lead");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false,
					"not able to validate View Phone Number functionality as element was not found on window" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate View Phone Number due to time out");
		}catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (SQLException s) {
			Assert.assertTrue(false, "not able to open DB");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 4)
	public void ViewNumberonSERP() throws InterruptedException {
		System.out.println("Inside Test View Phone Number");
		try {
			String res = in.ViewNumber("Inner Lead");
			if (res.contains("Pass")) {
				System.out.println("res");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false,
					"not able to validate View phone number on SERP functionality as element was not found on window");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate View phone number on SERP due to time out");
		}catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (SQLException s) {
			Assert.assertTrue(false, "not able to open DB");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 5)
	public void ContactSeller() throws InterruptedException {
		System.out.println("Inside Test Contact Seller");
		try {
			String res = in.ContactSellers();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate DB due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false,
					"not able to validate Contact Seller functionality as element was not found on window ");
		} catch (SQLException s) {
			Assert.assertTrue(false, "not able to open DB");
		}
	}

	@AfterClass
	public void Close() {
		try {
			in.CloseBrowser();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
