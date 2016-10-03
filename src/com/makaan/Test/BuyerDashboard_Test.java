package com.makaan.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makaan.Middleware.BuyerDashBoard_Middleware;


public class BuyerDashboard_Test {


	BuyerDashBoard_Middleware By = new BuyerDashBoard_Middleware();

	@BeforeClass
	public void InitiateDriver() {
		try {
			System.out.println("Inside Test Initiate Driver");

			String Res = By.OpenURL();
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
	public void VerifySearch() throws Exception {
		System.out.println("Inside Test Verify Search");
		try {
			String res= By.SearchVerify();
					if(res.contains("Pass")){
				System.out.println("Successfully completed VeriFy Search Functionality");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false,
					"not able to validate Saved Search Functionality in Buyer Journey as element was not found on window");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate Saved Search due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}
	@AfterClass
	public void Close() {
		try {
			By.CloseBrowser();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
