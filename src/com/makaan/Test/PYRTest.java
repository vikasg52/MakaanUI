package com.makaan.Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.makaan.Middleware.PYRMiddleware;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class PYRTest {

	PYRMiddleware mw = new PYRMiddleware();

	@BeforeClass
	public void InitiateDriver() {
		try {
			System.out.println("Inside Test Initiate Driver");
			mw.OpenURL();

		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find Makaan Logo on Page");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}

	// @Test (enabled = false)
	@Test(priority = 1)
	public void ValidatePYRBuy() throws InterruptedException {
		System.out.println("Inside Test Validate PYR Buy");
		
		try {
			String res = PYRMiddleware.PYRBuy("");
			if (res.contains("Pass")) {
				System.out.println("PYR Buy is validated");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify PYR BUY due to element not found on page" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (SQLException e) {
			Assert.assertTrue(false, "not able to connect with DB");
		}

	}

	// @Test (enabled = false)
	@Test(priority = 2)
	public void ValidatePYRRent() throws InterruptedException{
		System.out.println("Inside Test Validate PYR Rent");
		try {
			String res = PYRMiddleware.PYRRent();
			if (res.contains("Pass")) {
				System.out.println("PYR Rent is validated");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify PYR Rent due to element not found on page" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}catch (SQLException e) {
			Assert.assertTrue(false, "not able to connect with DB");
		}

	}

	@AfterClass
	public void Close() {
		try {
			PYRMiddleware.CloseAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
