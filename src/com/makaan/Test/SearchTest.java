package com.makaan.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makaan.Middleware.SearchMiddleware;

public class SearchTest {

	SearchMiddleware smw = new SearchMiddleware();

	@BeforeClass
	public void InitiateDriver() {
		System.out.println("Inside Test Initiate Driver");
		try {
			smw.OpenURL();
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Search Box due to element not found on Page");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open Search page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	 //@Test(enabled =false)
	@Test(priority = 0)
	public void ValidateSearchBox() {
		System.out.println("Inside Test Validate Search Box");
		try {
			String res = smw.ValidateSearch();
			if (res.contains("Pass")) {
				System.out.println("Search Box is validated");
			} else {
				Assert.assertTrue(false, res);
			}
		}
		 catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Search BOx due to element was not present on Page");
		}
	}

	@Test(enabled =false)
	//@Test(priority = 10)
	public void ValidatePopularSuggestions() throws InterruptedException  {
		System.out.println("Inside Test Validate Popular Suggestions");
		try {
			String res = smw.ValidateSuggestions();
			if (res.contains("Pass")) {
				System.out.println("Popular Suggestions are validated");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Popular Suggestions due to element was not present on Page" +n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to verify Popular Suggestions as suggestions were not present in box");
		}
	}

	 //@Test(enabled =false)
	@Test(priority = 2)
	public void ValidateBuyLocalities() throws InterruptedException  {
		try {
			
			String res = smw.ValidateBuyLocality();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for buy Localities");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Buy Localities due to element  was not present on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open Buy excel sheet");
		}
	}

	 //@Test(enabled =false)
	@Test(priority = 3)
	public void ValidateRentLocalities() throws InterruptedException  {
		System.out.println("Inside Test Validate Rent Locality");
		try {
			String res = smw.ValidateRentLocality();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for rent Localities");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Rent Localities due to element not found on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to verify Rent Localitie excel sheet");
		}
	}

	 //@Test(enabled =false)
	@Test(priority = 4)
	public void ValidateBuyProject() throws InterruptedException  {
		System.out.println("Inside Test Validate Buy Project");
		try {
			String res = smw.ValidateBuyProject();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for Buy Project");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Buy Projects due to element not found on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to verify Buy Projects excel sheet");
		}
	}

	 //@Test(enabled =false)
	@Test(priority = 5)
	public void ValidateRentProject() throws InterruptedException {
		System.out.println("Inside Test Validate Rent Project");
		try {
			String res = smw.ValidateRentProject();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for Rent Project");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Rent Projects due to element not found on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to verify Rent Projects excel sheet");
		}
	}

	 //@Test(enabled =false)
	@Test(priority = 6)
	public void ValidateBuilder() throws InterruptedException  {
		System.out.println("Inside Test Validate Builder");
		try {
			String res = smw.ValidateBuilder();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for builder");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Builders due to element not found on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to verify Builders excel sheet");
		}
	}

	//@Test(enabled = false)
	 @Test(priority = 7)
	public void ValidateBuyLandmark() throws InterruptedException  {
		System.out.println("Inside Test Validate Landmark buy");
		System.out.println("Inside Test Validate Builder");
		try {
			String res = smw.ValidateLandmarksBuy();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for Landmark");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Buy Landmarks due to element not found on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to verify  Buy Landmarks excel sheet");
		}
	}

	//@Test(enabled = false)
	 @Test(priority = 8)
	public void ValidateRentLandmark() throws InterruptedException {
		System.out.println("Inside Test Validate Landmark Rent");
		try {
			String res = smw.ValidateLandmarksRent();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for Landmark");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Rent Landmarks due to element not found on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to verify Rent Landmarks excel sheet");
		}
	}

	 //@Test(enabled =false)
	@Test(priority = 1)
	public void ValidateSuburbBuy() throws InterruptedException {
		System.out.println("Inside Test Validate Suburb");
		
		try {
			String res = smw.ValidateSuburbBuy();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for Suburb Buy");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Buy Suburb due to element not found on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to verify Buy Suburb excel sheet");
		}
	}

	 //@Test(enabled =false)
	@Test(priority = 9)
	public void ValidateSuburbRent() throws Exception {
		System.out.println("Inside Test Validate Suburb Rent");
		
		try {
			String res = smw.ValidateSuburbRent();
			if (res.contains("Pass")) {
				System.out.println("Search Box Validated for Suburb rent");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to verify Rent Suburb due to element not found on Page" +n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to verify Rent Suburb excel sheet");
		}
	}

	@AfterClass
	public void Close() {
		try {
			smw.CloseAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
