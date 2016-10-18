package com.makaan.Middleware;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.makaan.Dictionary.Home;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;
import com.makaan.Middleware.HeaderFooter_Middleware;


public class HomeMiddleware {

	public static Webhelper wb = null;
	public static Home dict = null;
	static WebDriver driver = null;
	String result = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");
	public static HeaderFooter_Middleware md = null;

	public HomeMiddleware() {
		wb = new Webhelper();
		md = new HeaderFooter_Middleware();
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
			wb.WaitUntillVisiblility(Home.MakaanLogo);
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

	public String VerifyAppButton() throws NoSuchElementException, TimeoutException, InterruptedException, IOException {
		System.out.println("Inside test Validate Download button");

		wb.WaitUntill(Home.DownloadApp);
		if (wb.IsElementPresent(Home.DownloadAppImage)) {
			System.out.println("download app button is present");
			String res = NewTab(Home.DownloadApp, Home.GooglePlayLogo, "mobileapp");
			if (res.contains("Pass")) {
				System.out.println("Dowload App is verified successfully");
			} else {
				return ("Fail:Download app was not verified successfully ");
			}
		} else {
			return ("Fail:Mobile Phone Image was not present on Download app Button");
		}
		return ("Pass: Verify Download button was Verified Successfully");

	}

	public String VerifyMainImage() throws NoSuchElementException, TimeoutException, InterruptedException {
		System.out.println("Inside test Validate Main Image");
		wb.WaitUntill(Home.MainImage);
		if (wb.IsElementPresent(Home.MainImage)) {
			System.out.println("Main Image on Page is present");
		} else {
			return ("Fail:Main Image was not present on Home Page");
		}
		return ("Pass: Main Image was Verified o Home page");

	}

	public String ValidateSearchBox() throws InterruptedException, TimeoutException {
		Thread.sleep(1000);
		wb.PageRefresh();
		Thread.sleep(2000);
		wb.WaitUntillVisiblility(Home.SearchBox);
		if (wb.IsElementPresent(Home.SearchBox)) {
			System.out.println("Search box exist");
			if (wb.IsElementPresent(Home.BuyTab) && (wb.IsElementPresent(Home.RentTab))) {
				System.out.println("buy and rent tab is present on search box");
				wb.ClickbyXpath(Home.RentTab);
				String Text = wb.getAttribute(Home.SearchPlaceholder, "placeholder");
				if (Text.contains("builder")) {
					return ("Fail: Place holder Text Contains Builder for Rent Tab " + Text);
				}
				wb.ClickbyXpath(Home.BuyTab);
				Text = wb.getAttribute(Home.SearchPlaceholder, "placeholder");
				if (!(Text.contains("builder"))) {
					return ("Fail: Place holder Text does not Contains Builder for Buy Tab " + Text);
				}
			} else {
				return ("Fail: buy and renttab is not present on search box");
			}
			if (wb.IsElementPresent(Home.SearchLens)) {
				System.out.println("Search lens was present on search bar");
				wb.ClickbyId(Home.SearchBox);
				if (wb.IsElementPresent(Home.SearchLensmoved)) {
					return ("Pass: Search Lens movement is captured");
				} else {
					return ("Fail: Search Lens movement was not captured and verify Search Lens is passed");
				}
			} else {
				return ("Fail: Search Lens was not present on card");
			}
		} else {
			return ("Fail: not able find Search box on home page");
		}
	}

	public String VerifySpotlighProjects()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside test Validate SpotLight Project");
		Thread.sleep(2000);
		driver = wb.getDriver();

		WebElement element = driver.findElement(By.xpath(Home.SponsoredProject));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		if (wb.IsElementPresentById("home_sponsored_project")) {
			System.out.println("Sponsored Projects are Present on Page");
			wb.WaitUntillVisiblility(Home.SponsoredLeft);
			if (wb.IsElementPresent(Home.SponsoredLeft) && wb.IsElementPresent(Home.SponsoredRight)) {
				Thread.sleep(5000);
				wb.PageRefresh();
				wb.ClickbyXpath("//div[@class='round-carousel']//div[@id='next-button']");
				Thread.sleep(2000);
				wb.WaitUntillVisiblility(Home.SponsoredActive);
				String ProjectName = wb.getText(Home.SponsoredActive);
				wb.WaitUntillVisiblility(Home.SponsoredActiveKnowMore);
				wb.ClickbyXpath(Home.SponsoredActiveKnowMore);
				Thread.sleep(2000);
				String URL = wb.CurrentURL();
				if (URL.contains("isSponsored=true")) {
					System.out.println("Url Found on Page is" + URL);
					int res = Webhelper.Get_Response(URL);
					if (res == 200) {
						System.out.println("Response code got from " + URL + "is " + res);
						if (wb.IsElementPresent(Home.VerifyProject)) {
							System.out.println("Project link is opening");
							String Name = wb.getAttribute(Home.VerifyProject, "title");
							Name = Name.toLowerCase();
							ProjectName = ProjectName.toLowerCase();
							Name = wb.Splitter(Name, " " , 1);
							if (ProjectName.contains(Name)) {
								wb.Back();
								return ("Pass: Spotlight Projects was Verified Successfully");
							} else {
								wb.Back();
								Thread.sleep(1000);
								return ("Fail: Project is different from Mentioned in Spotlight Projects" + " " + Name
										+ " " + ProjectName);
							}
						} else {
							wb.Back();
							Thread.sleep(1000);
							return ("Fail: Elemnet was not present on Locality Page" + Home.VerifyProject);
						}
					} else {
						wb.Back();
						Thread.sleep(1000);
						return ("Fail: Response code got from " + URL + "is " + res);
					}
				} else {
					wb.Back();
					Thread.sleep(1000);
					return ("Fail: Project was not sponsored");
				}
			} else {
				return ("Fail: Left or Right Button was not present on Sponsored Project");
			}

		} else {
			return ("Fail: Home Sponsored Project Was not present on Home Page");
		}

	}

	public String VerifyFindJoyVideo() throws InterruptedException, TimeoutException {
		wb.PageRefresh();
		System.out.println("Inside test Validate FindJoy Video");
		WebElement element = driver.findElement(By.xpath(Home.FindJoyVedio));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(3000);
		if (wb.IsElementPresentById("home-video")) {
			System.out.println("Find Joy Vedio is Present on Page");
			Thread.sleep(1000);
			wb.ClickbyId("home-video");
			Thread.sleep(2000);
		} else {
			return ("Fail:Player Display Button was not present on page ");
		}
		return ("Pass: Vedio played successfully");
	}

	public String VerifyMchatCard() throws InterruptedException, TimeoutException {
		System.out.println("Inside test Validate Mchat Card");
		WebElement element = driver.findElement(By.xpath(Home.MchatCard));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(3000);
		wb.WaitUntillVisiblility(Home.MchatCard);
		if (wb.IsElementPresent(Home.MchatCard)) {
			System.out.println("Mchat Card is Present on Page");
		} else {
			return ("Fail:Mchat Card was not present on page ");
		}
		return ("Pass: Mchat Card is Present on Page");
	}

	public String VerifyHighScoring() throws InterruptedException, TimeoutException {
		System.out.println("Inside test Validate High Score Card");
		WebElement element = driver.findElement(By.xpath(Home.HighScoringCard));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(3000);
		wb.WaitUntillVisiblility(Home.HighScoringCard);
		if (wb.IsElementPresent(Home.HighScoringCard)) {
			System.out.println("High Scoring Card is Present on Page");
		} else {
			return ("Fail:High Scoring Card was not present on page ");
		}
		return ("Pass: High Scoring Card is Present on Page");
	}

	public String VerifyCashBackCard() throws InterruptedException, TimeoutException, IOException {
		System.out.println("Inside test Validate CashBack Card");
		wb.PageRefresh();
		WebElement element = driver.findElement(By.xpath(Home.CashBackCard));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(3000);
		wb.WaitUntillVisiblility(Home.CashBackCard);
		if (wb.IsElementPresent(Home.CashBackPuzzle)) {
			System.out.println("Puzzle Image on cash back card was present");
			if (wb.IsElementPresent(Home.CashBackButton)) {
				wb.ClickbyXpath(Home.CashBackButton);
				Thread.sleep(1000);
				String URL = wb.CurrentURL();
				if (URL.contains("redeemnow")) {
					int res = Webhelper.Get_Response(URL);
					if (res == 200) {
						System.out.println("Response code got from " + URL + "is " + res);
						if (wb.IsElementPresent(Home.verifyCashback)) {
							System.out.println("Cash Back Page rendered");
						} else {
							wb.Back();
							Thread.sleep(1000);
							return ("Fail: Elemnet was not present on Locality Page" + Home.VerifyProject);
						}
					} else {
						wb.Back();
						Thread.sleep(1000);
						return ("Fail: Response code got from " + URL + "is " + res);
					}
				} else {
					wb.Back();
					return ("Fail:Cash Back Card did not open");
				}
			} else {
				return ("Fail:Cash Back Button was not present on card");
			}
		} else {
			return ("Fail:Puzzle image was not present on card");
		}
		wb.Back();
		Thread.sleep(1000);
		return ("Pass: High Scoring Card is Present on Page");
	}

	public String VerifyMplus() throws InterruptedException, TimeoutException {
		System.out.println("Inside test Validate Mplus  Card");
		WebElement element = driver.findElement(By.xpath(Home.Mplus));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(3000);
		wb.WaitUntillVisiblility(Home.Mplus);
		if (wb.IsElementPresent(Home.Mplus)) {
			System.out.println("Mplus Crad is Present on Page");
		} else {
			return ("Fail:Mplus Card was not present on page ");
		}
		return ("Pass: Mplus Card is Present on Page");
	}

	public String VerifySeller() throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside test Verify Seller Link");
		wb.WaitUntillVisiblility(Home.SellerLink);
		if (wb.IsElementPresent(Home.SellerLink)) {
			System.out.println("seller link is present");
			Thread.sleep(2000);
			wb.ClickbyXpath(Home.SellerLink);
			wb.WaitUntillVisiblility(Home.LoginPop);
			if (wb.IsElementPresent(Home.LoginPop)) {
				System.out.println("Login popup is open to connect with seller link");
			} else {
				return ("Fail: Login popup was not present on seller link button ");
			}
		} else {
			return ("FAil: Seller link was not present");
		}
		wb.WaitUntill(Home.Closepopup);
		wb.ClickbyXpath(Home.Closepopup);
		return ("Pass: Seller Link is verified");

	}

	public String VerifyBuyerJourney()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside test Verify Buyer Journey Link");
		Thread.sleep(2000);
		wb.WaitUntill(Home.Buyerjourney);
		if (wb.IsElementPresent(Home.Buyerjourney)) {
			System.out.println("Buyer Journey link is present");
			result = NewTab(Home.Buyerjourney, Home.ValidateBuyerJourney, "journey");
			if (result.contains("Pass")) {
				System.out.println("Buyer Journey button was verified");
			} else {
				System.err.println("Buyer journey button was not successfullt verified");
				return ("Fail: " + result);
			}

		} else {
			return ("Fail: Buyer Journey link was not present");
		}
		return ("Pass: Buyer Journey Verified Successfully");

	}

	public String VerifyMenuDrawerBuyerJourney() throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside test Verify Menu Drawer BuyerJourney Link");
		Thread.sleep(1000);
			System.out.println("Menu Drawer is present");
			closechat();
			wb.ClickbyXpath(Home.MenuDrawer);
			if (wb.IsElementPresent(Home.MenuDrawerJourney)) {
				System.out.println("Buyer Journey in drawer is present");
				wb.ClickbyXpath(Home.MenuDrawer);
				result = NewTab(Home.MenuDrawerJourney, Home.ValidateBuyerJourney, "journey");
				if (result.contains("Pass")) {
					System.out.println("Buyer Journey button was verified in menu drawer");
				} else {
					return ("Fail: " + result);
				}
		}
		return ("Pass: Buyer Journey in Menu Drawer is verified successfully");
	}

	public String VerifyMenuDrawerCities() throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside Validate top cities in drawer");
		Thread.sleep(2000);
		wb.IsElementPresent(Home.MenuDrawer);
		wb.ClickbyXpath(Home.MenuDrawer);
		int count = 0;
		String Element = null;
		// Thread.sleep(2000);
		if (wb.IsElementPresent(Home.TopCities)) {
			System.out.println("Link in drawer is present");
			wb.ClickbyXpath(Home.TopCities);
			closechat();
			// Thread.sleep(2000);
			for (int i = 1; i < 12; i++) {
				closechat();
				String Path = Home.TopCityGeneric+ i+"]";
				String City = wb.getAttribute(Path, "data-track-label");
				System.out.println("City Name is "+City);
				if (City.contains("all")) {
					City = "all";
					Element = Home.allCity;
					closechat();
				} else {
					City = City.toLowerCase();
					Element = Home.CityCover;
				}
				if(i==4||i==6||i==7){
					System.out.println("closing chat box when i = "+i);
				closechat();
				}
				result = NewTab(Path, Element, City);
				if (result.contains("Pass")) {
					System.out.println(City + " is verified in menu drawer");
				} else {
					System.err.println("Fail: " + result);
					count++;
				}
				Thread.sleep(1000);
				wb.ClickbyXpath(Home.MenuDrawer);
				 Thread.sleep(1000);
			}

		} else {
			return ("Fail: Top City link was not present in drawer");
		}

		if (count > 0) {
			return ("Fail: One or more city link was not verified due to error ");
		}
		return ("Pass: City Links were verified in Drawer");
	}

	public String VerifyMenuDrawerBuilder()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside Validate top Builder in drawer");
		int count = 0;
		String Element = null;
		wb.WaitUntillVisiblility(Home.MenuDrawer);
		if (wb.IsElementPresent(Home.TopBuilder)) {
			System.out.println("Link in drawer is present");
			wb.ClickbyXpath(Home.TopBuilder);
			// Thread.sleep(2000);
			for (int i = 1; i < 11; i++) {
				
				String Path = Home.TopBuilderGeneric+ i+"]";
				String Builder =  (wb.getAttribute(Path, "data-track-label")).toLowerCase();
				if (Builder.contains("all")) {
					Builder = "all";
					Element = Home.allBuilders;
				} else {
					for (String retval : Builder.split(" ")) {
						System.out.println(retval);
						Builder = retval;
						break;
					}
					Element = Home.BuilderCover;
				}
				if(i==3||i==6){
					closechat();
					}
				result = NewTab(Path, Element, Builder);
				if (result.contains("Pass")) {
					System.out.println(Builder + " is verified in menu drawer");
				} else {
					System.err.println(Builder + " is not verified in menu drawer" +result);
					count++;
				}
				Thread.sleep(1000);
				wb.ClickbyXpath(Home.MenuDrawer);
			}

		} else {
			return ("Fail: Top Builder link was not present in drawer");
		}

		if (count > 0) {
			return ("Fail: One or more builder link was not verified due to error ");
		}
		return ("Pass: Builder Links were verified in Drawer");
	}

	public String VerifyMenuDrawerBrokers()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside Validate top Brokers in drawer");
		int count = 0;
		String Element = null;
		 Thread.sleep(2000);
	    //wb.IsElementPresent(dict.MenuDrawer);
		wb.ClickbyXpath(Home.MenuDrawer);
		wb.WaitUntillVisiblility(Home.TopBrokers);
		closechat();
		if (wb.IsElementPresent(Home.TopBrokers)) {
			System.out.println("Link of brokers in drawer is present");
			wb.ClickbyXpath(Home.TopBrokers);
			Thread.sleep(2000);
			for (int i = 1; i < 11; i++) {
				String Path = Home.TopBrokerGeneric +i+"]";
				String Broker = (wb.getAttribute(Path, "data-track-label")).toLowerCase();
					for (String retval : Broker.split(" ")) {
						System.out.println(retval);
						Broker = retval;
						break;
					}
					if(i==3||i==6){
						closechat();
						}
					Element = Home.BrokerCover;
				result = NewTab(Path, Element, Broker);
				if (result.contains("Pass")) {
					System.out.println(Broker + " is verified in menu drawer");
				} else {
					System.err.println(Broker + " is not verified in menu drawer");
					count++;
				}
				Thread.sleep(1000);
				wb.getDriver().navigate().back();
				Thread.sleep(1000);
				wb.ClickbyXpath(Home.MenuDrawer);
				Thread.sleep(2000);
				wb.ClickbyXpath(Home.TopBrokers);
			}
			result = NewTab("//a[@href='/all-brokers']", Home.allBrokers,"all");
			if (result.contains("Pass")) {
				System.out.println("All Brokers Link is verified in menu drawer");
			} else {
				System.err.println("All Brokers Links is not verified in menu drawer");
				count++;
			}
			
		} else {
			return ("Fail: Top Broker link was not present in drawer");
		}

		if (count > 0) {
			return ("Fail: One or more broker link was not verified due to error ");
		}
		return ("Pass: Broker Links were Verified in Drawer");
	}

	public String VerifyMenuDrawerMakaanIQ()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Valiadting Makaan IQ in Drawer");
		wb.PageRefresh();
		Thread.sleep(2000);
		closechat();
		wb.ClickbyXpath(Home.MenuDrawer);
		wb.WaitUntill(Home.MakaanIQ);
		if (wb.IsElementPresent(Home.MakaanIQ)) {
			System.out.println("MakaanIQ in drawer is present");
			result = SwitchWindow(Home.MakaanIQ, Home.VerifyMakaanIQ, "iq");
			if (result.contains("Pass")) {
				System.out.println("Makaan IQ is verified successfully");
			} else {
				return ("Fail: Makaan IQ was not verified successfully" +result);
			}
			return ("Pass MakaanIQ link was verified in Drawer");
		} else {
			return ("Fail: Makaan IQ link was not present in drawer");
		}
	}

	public String VerifyMenuDrawerApp()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Valiadting APP in Drawer");
		if (wb.IsElementPresent(Home.MenuDrawerApp)) {
			System.out.println("App in drawer is present");
			if (wb.IsElementPresent(Home.AppImageDrawer)) {
				result = NewTab(Home.MenuDrawerApp, Home.GooglePlayLogo, "mobileapp");
				if (result.contains("Pass")) {
					System.out.println("Dowload App is verified successfully");
				} else {
					wb.PageRefresh();
					return ("Fail: Download app was not verified as Some Problem with the download Link");
				}
			} else {
				return ("Fail: App Image in Drawer is not present");
			}
			wb.PageRefresh();
			return ("Pass: App Link inn drawer verified successfully");
		} else {
			wb.PageRefresh();
			return ("Fail: Download app link was not present in drawer");
		}
	}

	public String VerifyListYourProperty()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Valiadting  List your Prperty in Drawer");
		Thread.sleep(3000);
		wb.ClickbyXpath(Home.MenuDrawer);
		wb.WaitUntillVisiblility(Home.ListYourProerty);
		closechat();
		if (wb.IsElementPresent(Home.ListYourProerty)) {
			System.out.println(" List your Prperty in drawer is present");
			closechat();
			String res = SwitchWindow(Home.ListYourProerty, Home.VerifyListYourProerty, "seller-journey");
			if (res.contains("Pass")) {
				System.out.println("List Your Property is verified successfully");
			} else {
				wb.Back();
				return ("Fail:List Your Property was not verified as Some Problem with the download Link");
			}
			wb.Back();
			return ("Pass:List Your Property drawer verified successfully");
		} else {
			wb.Back();
			return ("Fail:List Your Property link was not present in drawer");
		}
	}

	public static void CloseAll() {
		db.Close();
		wb.CloseBrowser();
	}

	public static void closechat()  {
		try {
			if (wb.IsElementPresentById("inner-wrapper")) {
				// wb.ClickbyXpath(".//textarea[@id='input']");
				wb.ClickbyXpath("//div[@class='cross']");
				System.out.println("Closed mchat popup");
			}
		} catch (Exception e) {
			System.out.println("mchat popup was not present");
		}

	}

	public String NewTab(String Path, String Element, String Value)
			throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		closechat();
		wb.ClickbyXpath(Path);
		wb.WaitUntill(Element);
		String URL = wb.CurrentURL();
		res = Webhelper.Get_Response(URL);
		if (res == 200) {
			System.out.println("response code from " + URL + "is " + res);
			if (URL.contains(Value)) {
				wb.WaitUntillVisiblility(Element);
				if (wb.IsElementPresent(Element)) {
					System.out.println("Element is present on page");
				} else {
					wb.Back();
					return ("Fail: Element was not present in URL " + URL + " " + Element);
				}
			} else {
				wb.Back();
				return ("Fail:URL did not contain desired value " + URL + "And Value is " + Value);
			}
		} else {
			wb.Back();
			return ("Fail: response code from " + URL + "is " + res);
		}
		wb.Back();
		return ("Pass: Verified Successfully");
	}

	public String SwitchWindow(String Path, String Element, String Value)
			throws NoSuchElementException, TimeoutException, InterruptedException, IOException {
		int res = 0;
		driver = wb.getDriver();
		String parentHandle = driver.getWindowHandle();
		try{
		if(wb.IsElementPresent("")){
			
		}}catch(Exception e){
			
		}
		wb.WaitUntillVisiblility(Path);
		Thread.sleep(2000);
		driver.findElement(By.xpath(Path)).click();
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		String URL = driver.getCurrentUrl();
		res = Webhelper.Get_Response(URL);
		System.out.println("URL of opened window is " + URL);
		if (res == 200) {
			System.out.println("response code from " + URL + "is " + res);
			if (URL.contains(Value)) {
				wb.WaitUntillVisiblility(Element);
				if (wb.IsElementPresent(Element)) {
					System.out.println("Element is present on page");
				} else {
					driver.close();
					driver.switchTo().window(parentHandle);
					return ("Fail: Element was not present in URL " + URL);
				}
			} else {
				driver.close();
				driver.switchTo().window(parentHandle);
				return ("Fail: URL did not contain desired value " + URL + "Value is " + Value);
			}
		} else {
			driver.close();
			driver.switchTo().window(parentHandle);
			return ("Fail: response code from " + URL + "is " + res);
		}
		driver.close();
		driver.switchTo().window(parentHandle);
		return ("Pass: Verify Successfully");
	}

	public String DownloadAppSection()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		wb.PageRefresh();
		Thread.sleep(1000);
		wb.scrolldown(Home.DownloadAppSection);
		if (wb.IsElementPresent(Home.DownloadAppSectionImage)) {
			wb.WaitUntill(Home.Inputnumber);
			wb.enterTextByxpath(Home.Inputnumber, "9212746345");
			wb.ClickbyXpath(Home.GetApp);
			Thread.sleep(3000);
			if (wb.getText(Home.ErrorApp).contains("successfully")) {
				System.out.println("App link send successfully sent");
			} else {
				return ("Fail: app link was not successfully sent");
			}
		} else {
			return ("Fail: Image on Download App Section was not pesent");
		}

		return ("Pass: Download App Section is verfied successfully");
	}
	
	
	public String ValidateFooter() throws NoSuchElementException, InterruptedException, IOException, TimeoutException{
		System.out.println("Validating Footer Now");
		String Result = md.ValidateHeaderFooter();
		return Result;
	}

}

