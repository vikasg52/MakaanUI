package com.makaan.Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.makaan.Middleware.SERPMiddleware;

public class LocalitySERPTest {

	SERPMiddleware smw = new SERPMiddleware();

	@BeforeClass
	public void InitiateDriver() {
		System.out.println("Inside Test Initiate Driver");
		try {
			smw.OpenURL("Locality SERP");
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find Makaan Logo on Page");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(priority = 3)
	 //@Test(enabled = false)
	public void BuyFilters() throws InterruptedException {
		System.out.println("Inside Test ValidateBuy Filters");
		try {
			String res = smw.ValiadateBuyFilters();
			if (res.contains("Pass")) {
				System.out.println("Buy Filters are validated");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Buy Filters as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Buy Filters due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(priority = 4)
	// @Test(enabled = false)
	public void RentFilters() throws InterruptedException {
		System.out.println("Inside Test Validate Rent Filters");
		try {
			String res = smw.ValiadateRentFilters();
			if (res.contains("Pass")) {
				System.out.println("Rent Filters are validated");
			} else {
				System.out.println("Rent filters are not validated");
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Rent Filters as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Rent Filters due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(priority = 1)
	// @Test(enabled = false)
	public void ValidateCardBuy() throws InterruptedException {
		System.out.println("Inside Test Validate card");
		try {
			String res = smw.VerifyImageOnCard("Buy");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Cards as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(priority = 2)
	// @Test(enabled = false)
	public void ValidateCardRent() throws InterruptedException, IOException {
		System.out.println("Inside Test Validate card");
		try {
			String res = smw.VerifyImageOnCard("rent");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Cards as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(priority = 5)
	// @Test(enabled = false)
	public void ValidatePropertyBuy() throws InterruptedException {
		System.out.println("Inside Test Validate Property Link");
		try {
			String res = smw.VerifyPropertyLink("Buy");
			if (res.contains("Pass")) {
				System.out.println("Verify Property link is validated Buy");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Property Link as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate due to time out");
		}
	}

	@Test(priority = 6)
	// @Test(enabled = false)
	public void ValidatePropertyRent() throws InterruptedException {
		System.out.println("Inside Test Validate Property Link Rent");
		try {
			String res = smw.VerifyPropertyLink("rent");
			if (res.contains("Pass")) {
				System.out.println("Verify Property link is validated");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Property Link as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate due to time out");
		}
	}

	@Test(priority = 7)
	// @Test(enabled = false)
	public void ValidateReadMoreBuy() throws InterruptedException {
		System.out.println("Inside Test Validate Read More Link for buy");
		try {
			String res = smw.VerifyReadMore("Buy");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validateRead More as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate due to time out");
		}
	}

	@Test(priority = 8)
	// @Test(enabled = false)
	public void ValidateReadMorerent() throws InterruptedException {
		System.out.println("Inside Test Validate Read More Link for Rent");
		try {
			String res = smw.VerifyReadMore("rent");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validateRead More as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate due to time out");
		}
	}

	@Test(priority = 9)
	// @Test(enabled = false)
	public void ValidateSellerBuy() throws InterruptedException {
		System.out.println("Inside Test Validate Seller Type");
		try {
			String res = smw.ValidateSellerType("Buy");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Sellers Link as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate due to time out");
		}

	}

	@Test(priority = 10)
	// @Test(enabled = false)
	public void ValidateSellerRent() throws InterruptedException, TimeoutException {
		System.out.println("Inside Test Validate Seller Type");
		try {
			String res = smw.ValidateSellerType("rent");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Sellers Link as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate due to time out");
		}
	}

	@Test(priority = 11)
	// @Test(enabled = false)
	public void ValidateLocalityBuy() throws IOException, InterruptedException {
		System.out.println("Inside Test Validate Locality");

		try {
			String res = smw.VerifyLocality("Buy");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Localities as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate due to time out");
		}
	}

	@Test(priority = 12)
	// @Test(enabled = false)
	public void ValidateLocalityRent() throws IOException, InterruptedException {
		System.out.println("Inside Test Validate Locality");
		try {
			String res = smw.VerifyLocality("rent");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, "res");
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Localities as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate due to time out");
		}
	}

	@Test(priority = 13)
	// @Test(enabled = false)
	public void ValidateProjectBuy() throws InterruptedException, IOException {
		System.out.println("Inside Test Validate Project");

		try {
			String res = smw.VerifyProject("Buy");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Project Link as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Valiadate due to time out");
		}
	}

	@Test(priority = 15)
	// @Test(enabled = false)
	public void ValidateShortlistBuy() throws InterruptedException {
		System.out.println("Inside Test Validate Shortlist");
		try {
			String res = smw.VerifyShortList("Buy");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Localities as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate due to time out");
		}
	}

	@Test(priority = 16)
	// @Test(enabled = false)
	public void ValidateShortlistrent() throws InterruptedException {
		System.out.println("Inside Test Validate Shortlist");

		try {
			String res = smw.VerifyShortList("rent");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Localities as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate due to time out");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 17)
	public void Validatepagination() throws InterruptedException {
		System.out.println("Inside Test Validate Pagination");
		try {
			String res = smw.Pagination();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Paginations as element was not found on window" + n);
		}
	}

	// @Test(enabled = false)
	@Test(priority = 18)
	public void ValidateSidecards() throws InterruptedException {
		System.out.println("Inside Test Validate Sidecards");
		try {
			String res = smw.SideCards("Locality SERP");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Side cards as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Side Cards due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 19)
	public void ValidateMapListToggle() throws InterruptedException {
		System.out.println("Inside Test Validate Map-List Toggle");

		try {
			String res = smw.mapList();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Map-List Toggle as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Map-List Toggle due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	@Test(enabled = false)
	// @Test(priority = 20)
	public void ValidateViewMoreBuy() throws InterruptedException {
		System.out.println("Inside Test Validate ViewMoreLink");
		try {
			String res = smw.VerifyKnowMoreLink("Buy");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate ViewMore Link as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate ViewMore Link due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 21)
	public void ValidateViewMoreRent() throws InterruptedException {
		System.out.println("Inside Test Validate ViewMoreLink");
		try {
			String res = smw.mapList();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate ViewMore Link as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate ViewMore Link due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 23)
	public void ValidateSortingFilter() throws InterruptedException {
		System.out.println("Inside Test Validate Sorting Filter");
		try {
			String res = smw.SortingFilter("Locality SERP");
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Sorting Filter as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Sorting Filter due to time out");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 24)
	public void ValidateViewNumberForListedType() throws InterruptedException {
		System.out.println("Inside Test Validate View Number For Listed Type");
		try {
			String res = smw.ValidateViewNumberforListedType();
			if (res.contains("Pass")) {
				System.out.println("View Number For Listed Type is validated");
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Set Alert as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Set Alert due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	// @Test(enabled = false)
	@Test(priority = 25)
	public void ValidateSetAlert() throws InterruptedException {
		System.out.println("Inside Test Validate SetAlert");
		try {
			String res = smw.SetAlert();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Set Alert as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Set Alert due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
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
