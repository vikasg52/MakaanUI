package com.makaan.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makaan.Middleware.HomeMiddleware;
import com.makaan.Middleware.SearchMiddleware;

public class HomeTest {

	HomeMiddleware hm = new HomeMiddleware();

	@BeforeClass
	public void InitiateDriver() {
		try {
			System.out.println("Inside Test Initiate Driver");
			if (hm.OpenURL()) {
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
	
	@Test(priority = 0)
		public void CloseChat()  {
			System.out.println("Inside Test Close Chat");
				 hm.closechat();
		}
	
	
	
	 	@Test(priority = 1)
		//@Test(enabled = false)
		public void ValidateMainImage() throws InterruptedException {
			System.out.println("Inside Test Validate Main button");
			try {
				String res = hm.VerifyMainImage();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			} catch (NoSuchElementException n) {
				Assert.assertTrue(false, "not able to validate Main Image as element was not found on window" + n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able validate Main Image due to time out");
			} 

		}
	

	 @Test(priority = 2)
	//@Test(enabled = false)
	public void ValidateDownloadAppButton() throws InterruptedException, IOException {
		System.out.println("Inside Test Validate Download app button");
		try {
			String res = hm.VerifyAppButton();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Download app button as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able validate App Button due to time out");
		} 

	}

	 @Test(priority = 3)
	public void ValidateSellerLink() throws InterruptedException {
		System.out.println("Inside Test Validate Seller Link");
		try {
			String res = hm.VerifySeller();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Seller Link Button as element was not found on window"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate seller link due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}

	 	@Test(priority = 4)
		//@Test(enabled = false)
		public void ValidateBuyerJourney() throws InterruptedException {
			System.out.println("Inside Test Validate Buyer Journey");
			try {
				String res = hm.VerifyBuyerJourney();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			}  catch (NoSuchElementException n) {
				Assert.assertTrue(false, "not able to validate Buyer Journey Button as element was not found on window"+n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to validate Buyer Journey link due to time out");
			} catch (IOException i) {
				Assert.assertTrue(false, "not able to open excel sheet");
			}
		}
	 
	 @Test(priority = 5)
		//@Test(enabled = false)
		public void BuyerJourneyDrawer() throws InterruptedException {
			System.out.println("Inside Test Validate Journey in Drawer");
			try {
				String res = hm.VerifyMenuDrawerBuyerJourney();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			} catch (NoSuchElementException n) {
				Assert.assertTrue(false,
						"not able to validate Buyer Journey in  Menu Drawer Button as element was not found on window" +n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to validate Buyer Journey  in  Menu Drawer link due to time out");
			} catch (IOException i) {
				Assert.assertTrue(false, "not able to open excel sheet");
			}
		}

		 @Test(priority = 6)
		//@Test(enabled = false)
		public void CityDrawer() throws InterruptedException {
			System.out.println("Inside Test Validate Top City in Drawer");
			try {
				String res = hm.VerifyMenuDrawerCities();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			}catch (NoSuchElementException n) {
				Assert.assertTrue(false,
						"not able to validate City in  Menu Drawer Button as element was not found on window" +n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to validate City in  Menu Drawer link due to time out");
			} catch (IOException i) {
				Assert.assertTrue(false, "not able to open excel sheet");
			}
		}

		@Test(priority = 7)
		 //@Test(enabled = false)
		public void BuilderDrawer() throws InterruptedException {
			System.out.println("Inside Test Validate Top Builder in Drawer");
			try {
				String res = hm.VerifyMenuDrawerBuilder();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			} catch (NoSuchElementException n) {
				Assert.assertTrue(false, "not able to validate Builder in  Menu Drawer as element was not found on window"+n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to validate Builder in  Menu Drawer link due to time out");
			} catch (IOException i) {
				Assert.assertTrue(false, "not able to open excel sheet");
			}
		}

		 @Test(priority = 8)
		//@Test(enabled = false)
		public void BrokerDrawer() throws InterruptedException {
			System.out.println("Inside Test Validate Top Brokers in Drawer");
			try {
				String res = hm.VerifyMenuDrawerBrokers();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			}catch (NoSuchElementException n) {
				Assert.assertTrue(false,
						"not able to Top Brokers in  Menu Drawer Button as element was not found on window" +n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to Top Brokers in  Menu Drawer link due to time out");
			} catch (IOException i) {
				Assert.assertTrue(false, "not able to open excel sheet");
			}
		}

		 @Test(priority = 9)
		//@Test(enabled = false)
		public void MakaanIQDrawer() throws InterruptedException {
			System.out.println("Inside Test Validate MAkaan IQ in Drawer");
			try {
				String res = hm.VerifyMenuDrawerMakaanIQ();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			} catch (NoSuchElementException n) {
				Assert.assertTrue(false,
						"not able to Validate Makaan IQ in drawer Button as element was not found on window"+n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to Validate Makaan IQ in drawer link due to time out");
			} catch (IOException i) {
				Assert.assertTrue(false, "not able to open excel sheet");
			}
		}

		@Test(priority = 10)
		//@Test(enabled = false)
		public void AppDrawer() throws InterruptedException {
			System.out.println("Inside Test Validate Download App in Drawer");
			try {
				String res = hm.VerifyMenuDrawerApp();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			} catch (NoSuchElementException n) {
				Assert.assertTrue(false,
						"not able to Validate Download App in  Drawer Button as element was not found on window"+n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to Validate Download App in  Drawer link due to time out");
			} catch (IOException i) {
				Assert.assertTrue(false, "not able to open excel sheet");
			}
		}

		 @Test(priority = 11)
		//@Test(enabled = false)
		public void ListProperty() throws InterruptedException {
			System.out.println("Inside Test Validate List Property in Drawer");
			try {
				String res = hm.VerifyListYourProperty();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			} catch (NoSuchElementException n) {
				Assert.assertTrue(false,
						"not able to Validate List your Prperty in  Drawer Button as element was not found on window"+n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to Validate  List your Prperty in  Drawer link due to time out");
			} catch (IOException i) {
				Assert.assertTrue(false, "not able to open excel sheet");
			}
		}

	 @Test(priority = 12)
	//@Test(enabled = false)
	public void ValidateSearchPlaceHolder() throws InterruptedException {
		System.out.println("Inside Test Validate Search Box");
		try {
			String res = hm.ValidateSearchBox();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Search button as element was not found on window" + n);
		}

	}
	 
	 @Test(priority = 13)
	//@Test(enabled = false)
	public void ValidateSpotlightProject() throws InterruptedException {
		System.out.println("Inside Test Validate Spotlight Project");
		try {
			String res = hm.VerifySpotlighProjects();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Search button as element was not found on window" + n);
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Search button link due to time out");
		}

	}

	 @Test(priority = 14)
	//@Test(enabled = false)
	public void ValidateFindJoy() throws InterruptedException {
		System.out.println("Inside test Validate FindJoy Video");
		try {
			String res = hm.VerifyFindJoyVideo();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Joy as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Joy link due to time out");
		}

	}

	 @Test(priority = 15)
	//@Test(enabled = false)
	public void ValidateMchatCard() throws InterruptedException {
		System.out.println("Inside test Validate Mchat Card");
		try {
			String res = hm.VerifyMchatCard();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Mchat Card as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Mchat due to time out");
		}

	}

	 @Test(priority = 16)
	//@Test(enabled = false)
	public void ValidateHighscoring() throws InterruptedException {
		System.out.println("Inside test Validate HighScoring Card");
		try {
			String res = hm.VerifyHighScoring();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate High Scoring as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate High Scoring due to time out");
		}

	}

	 @Test(priority = 17)
	//@Test(enabled = false)
	public void ValidateCashBackCard() throws InterruptedException, IOException {
		System.out.println("Inside test Validate CashBack Card");
		try {
			String res = hm.VerifyCashBackCard();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate CashBack Card as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate CAsh Back Card due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}

	 @Test(priority = 18)
	//@Test(enabled = false)
	public void ValidateMplusCard() throws InterruptedException, IOException {
		System.out.println("Inside test Validate Mplus Card");
		try {
			String res = hm.VerifyMplus();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to validate Mplus Card as element was not found on window" + n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to validate Mplus Card due to time out");
		}

	}

	 
	@Test(priority = 19)
	// @Test(enabled = false)
	public void VerifyAppSection() throws InterruptedException {
		System.out.println("Inside Test Verify App Section");
		try {
			String res = hm.DownloadAppSection();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Validate App Section as element was not found on window"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Validate App Section link due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}

	
	@Test(priority = 20)
	 //@Test(enabled = false)
	public void VerifyFooter() throws InterruptedException {
		System.out.println("Inside Test Verify Footer");
		try {
			String res = hm.ValidateFooter();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Validate Footer as element was not found on window"+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to Validate Footer due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}

	}

	
	
	@AfterClass
	public void Close() {
		try {
			hm.CloseAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
