package com.makaan.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makaan.Middleware.CityOverview_Middleware;

public class CityOverview_Test {

	CityOverview_Middleware cm = new CityOverview_Middleware();

	@BeforeClass
	public void InitiateDriver() {
		try {
			System.out.println("Inside Test Initiate Driver");
			if (cm.OpenURL()) {
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
	
	@Test(priority = 1)
	public void ValidateHeader() throws InterruptedException {
		System.out.println("Inside Test Validate Header on Overview Page");
		
		try {
			String res = cm.VerifyHeader();
			if (res.contains("Pass")) {
				System.out.println("Header is present");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}
	
	@Test(priority = 2)
	public void ValidateImage() throws InterruptedException {
		System.out.println("Inside Test Validate Image and Annual Growth");
		
		try {
			String res = cm.VerifyImage();
			if (res.contains("Pass")) {
				System.out.println(" Validated Image and Annual Growth");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}
	@Test(priority = 3)
	public void ValidateDescription() throws InterruptedException {
		System.out.println("Inside Test Validate Description");
		
		try {
			String res = cm.VerifyImage();
			if (res.contains("Pass")) {
				System.out.println(" Validated Image and Annual Growth");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}

	
	@Test(priority = 4)
	public void ValidateNavigation() throws InterruptedException {
		System.out.println("Inside Test Validate NavigationBar");
		
		try {
			String res = cm.VerifyNavigationBar();
			if (res.contains("Pass")) {
				System.out.println(" Validated Navigation bar");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}
	
	@Test(priority = 5)
	public void ValidateProjects() throws InterruptedException, IOException {
		System.out.println("Inside Test Validate Sponsored Project");
		
		try {
			String res = cm.VerifySpotlighProjects();
			if (res.contains("Pass")) {
				System.out.println(" Validated spotlight projects");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}
	
	@Test(priority = 6)
	public void ValidatePriceTrends() throws InterruptedException, IOException {
		System.out.println("Inside Test Validate Price Trends");
		
		try {
			String res = cm.VerifyPriceTrend();
			if (res.contains("Pass")) {
				System.out.println(" Validated price Trendz");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}
	@Test(priority = 7)
	public void ValidatePropertyGraph() throws InterruptedException, IOException {
		System.out.println("Inside Test Validate Property Graph");
		
		try {
			String res = cm.VerifyPropertyRange();
			if (res.contains("Pass")) {
				System.out.println(" Validated property Rage Card");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}
	}
	@Test(priority = 8)
	public void ValidatePropertyinCity() throws InterruptedException, IOException {
		System.out.println("Inside Test Validate Property in City");
		
		try {
			String res = cm.VerifyPropertyinCity();
			if (res.contains("Pass")) {
				System.out.println(" Validated property in City Card");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		} 
	}
	
	@Test(priority = 9)
	public void ValidateLead() throws InterruptedException, IOException, SQLException, TimeoutException {
		System.out.println("Inside Test Validate Lead form on City Page");
		
		try {
			String res = cm.VerifyLead();
			if (res.contains("Pass")) {
				System.out.println(" Validated Lead form on City Page");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		}  
	}
	@Test(priority = 10)
	public void ValidatePropularLocality() throws InterruptedException, IOException, SQLException, TimeoutException {
		System.out.println("Inside Test Validate Popular Localities");
		
		try {
			String res = cm.VerifyPopularLocality();
			if (res.contains("Pass")) {
				System.out.println(" Validated Popular Localities on City Page");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		}  
	}
	
	@Test(priority = 11)
	public void ValidateTopProjects() throws InterruptedException, IOException, SQLException, TimeoutException {
		System.out.println("Inside Test Validate Top Projects");
		
		try {
			String res = cm.VerifyTopProjects();
			if (res.contains("Pass")) {
				System.out.println(" Validated Top Projects on City Page");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find element due to element not found"+n);
		}  
	}
	
}
