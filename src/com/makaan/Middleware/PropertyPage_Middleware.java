package com.makaan.Middleware;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.makaan.Dictionary.PropertyPage;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;
import com.makaan.Middleware.ConnectNowMiddleware;

public class PropertyPage_Middleware {

	public static Webhelper wb = null;
	public static PropertyPage dict = null;
	public static WebDriver driver;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("./Files/Marketplace.xls");

	public static ConnectNowMiddleware cn = null;

	public PropertyPage_Middleware() {
		System.out.println("inside PropertyPage Middleware Constructor");

		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();
		cn = new ConnectNowMiddleware();

		System.out.println("inside Middleware Constructor");

	}

	public void OpenURL() throws Exception {

		String URL = ReadSheet("PropertyPage", "URL", 2);
		Webhelper.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		wb.WaitUntill(PropertyPage.MakaanLogo);

	}

	public String ReadSheet(String Sheet, String Col_Name, int row_id)
			throws IOException, NoSuchElementException, TimeoutException {

		String data = xls.getCellData(Sheet, Col_Name, row_id);
		System.out.println("Data from sheet " + data);

		return (data);
	}

	public void NavigatetoNEwTab() {
		System.out.println("Navigating to property page");
		wb.ClickbyXpath("//div[@data-track-scroll='1']//div[@class='proplink-wrap']");

	}

	public String ValidateProjectImage() throws TimeoutException, InterruptedException {
		System.out.println(
				"In this case we will validate Image Count Image count, next button, full view of image, Name on card");
		int GalleryCount = 0;
		int SERPcount = 0;

		String URL = wb.CurrentURL();
		if (wb.IsElementPresent(PropertyPage.ProjectImage)) {
				wb.ClickbyXpath(PropertyPage.ProjectImage);
			Thread.sleep(1000);
			if (wb.IsElementPresent(PropertyPage.galleryTotalCount) && wb.IsElementPresent(PropertyPage.imagegallery)) {
				System.out.println("Gallery Total count" + wb.getText(PropertyPage.galleryTotalCount));
				GalleryCount = Integer.parseInt(wb.getText(PropertyPage.galleryTotalCount));
				if (GalleryCount > 1) {
					wb.ClickbyXpath(PropertyPage.galleryNext);
					wb.ClickbyXpath(PropertyPage.galleryNext);
				}
				Thread.sleep(2000);
				wb.ClickbyXpath(PropertyPage.galleryClose);
			} else {
				return ("Fail: Images or Count in Gallery are not present");
			}
			wb.WaitUntillVisiblility(PropertyPage.MakaanLogo);
			if (GalleryCount > 1) {
					try {
						if (wb.IsElementPresent(PropertyPage.DefaultImage + "[1]")) {
							System.err.println("Default image is present on the property card");
							return ("False: Dafault image on Card is present");
						}
					} catch (NoSuchElementException e) {
						System.out.println("Default image was not present on card ");
					}
			} 
		}
		return ("Pass: Image on Gallery are verified");
	}

	public String ValidateProjectHeader() throws NoSuchElementException {
		System.out.println("Inside Test Validate Project Header");
		if (wb.IsElementPresent(PropertyPage.ProjectHeader)) {
			String Name = wb.Splitter(wb.getAttribute(PropertyPage.ProjectName, "title"), " ", 1);
			System.out.println("Name: " + Name);
			if (wb.CurrentURL().contains(Name.toLowerCase())) {
				if (wb.IsElementPresent(PropertyPage.Shortlist)) {
					System.out.println("Header Verifed Successfully");
				} else {
					return ("Fail: Shortlist Icon was not present");
				}

			} else {
				return ("Fail: Poject Name is not matching with URL");
			}
		} else {
			return ("Fail: Header of project was not present");
		}
		return ("Pass: Header was Verified");
	}

	public String ValidateNavigatorBar() throws NoSuchElementException, InterruptedException {
		System.out.println("inside test validate Navigator on Project Image");
		if (wb.IsElementPresent(PropertyPage.Navigatorbar)) {
			System.out.println("Validating functionality of bar");
			if (ClickandMove(PropertyPage.TabOverview, PropertyPage.OverviewCard, "description")) {
			} else {
				return ("Fail: Overview Tab was not Validated ");
			}
			Thread.sleep(1000);
			Thread.sleep(3000);
			wb.ClickbyXpath(PropertyPage.FloorPlanLink);
			if (ClickandMove(PropertyPage.SpecificationInTabFloorPlan, PropertyPage.FloorPlanCard, "specifications")) {
			} else {
				return ("Fail: Floor Plan tab was not validated");
			}
			Thread.sleep(1000);
			if (ClickandMove(PropertyPage.TabSimilar, PropertyPage.Similar_property, "similar")) {
			} else {
				return ("Fail: Similar Property Tab was not validated");
			}
			Thread.sleep(1000);
			if (ClickandMove(PropertyPage.TabProject, PropertyPage.ProjectScore, " ")) {
			} else {
				return ("Fail: Project Tab was not validated");
			}
			if (ClickandMove(PropertyPage.Locality, PropertyPage.LocalityScore, " ")) {
			} else {
				return ("Fail: Locality Tab was not validated");
			}
		} else {
			return ("Fail: Navigator bar is not present");
		}
		return ("Pass: validate Navigator on Project Page");
	}

	public boolean ClickandMove(String Path, String Tab, String Element) throws InterruptedException {
		System.out.println("Inside Click and move");
		wb.PageRefresh();
		Thread.sleep(2000);
		wb.scrolldown(Path);
		wb.scrollup(Path);
		if (wb.IsElementPresent(Path)) {
			Thread.sleep(1000);
			wb.ClickbyXpath(Path);
			Thread.sleep(1000);
			// wb.ClickbyXpath(Path);
			// Thread.sleep(3000);
			if (Path.contains("locality") || Path.contains("project")) {
				if (wb.IsElementPresent(Tab)) {
					return true;
				} else {
					return false;
				}
			} else {
				if (wb.getText(Tab).contains(Element)) {
					System.out.println(Element + " card was present");
					return true;
				} else {
					System.err.println(Element + " card was not present");
					return false;
				}
			}
		} else {
			System.err.println(Path + " card was not present");
			return false;
		}

	}

	public String ValidateOverviewCard() throws NoSuchElementException, InterruptedException, TimeoutException {
		System.out.println("inide test validate Configuration Card");
		driver = wb.getDriver();
		wb.scrolldown(PropertyPage.OverviewCard);
		Thread.sleep(2000);
		wb.scrollup(PropertyPage.OverviewReadMore);
		Thread.sleep(3000);
		wb.ClickbyXpath(PropertyPage.OverviewReadMore);
		wb.WaitUntillVisiblility(PropertyPage.OverviewDescription);
		wb.scrolldown(PropertyPage.OverviewReadMore);
		wb.scrollup(PropertyPage.OverviewReadMore);
		wb.ClickbyXpath(PropertyPage.OverviewReadMore);
		try {
			if (wb.IsElementPresent(PropertyPage.KeyDetails)) {
				wb.scrolldown(PropertyPage.KeyDetails);
				wb.WaitUntillVisiblility(PropertyPage.KeyDetails);
				Thread.sleep(2000);
				List<WebElement> ConfElement = driver.findElements(By.xpath(PropertyPage.KeyDetails));
				for (int i = 0; i < ConfElement.size(); i++) {
					System.out.println(ConfElement.get(i).getText());
				}
			}
		} catch (NoSuchElementException e) {
			return ("Fail: Card was not present" + e);
		}

		return ("Pass: Overview Section is been validated");
	}

	public String ValidateAmenityCard() throws NoSuchElementException, InterruptedException, TimeoutException {
		System.out.println("inide test validate Amenity Card");
		 wb.ClickbyXpath(PropertyPage.AmenityLink);
		 Thread.sleep(3000L);
		if (wb.IsElementPresent(PropertyPage.AmenityCard)) {
			wb.scrolldown(PropertyPage.AmenityCard);
			wb.scrollup(PropertyPage.AmenityCard);
			wb.WaitUntillVisiblility(PropertyPage.AmenityCard);
			wb.ClickbyXpath(PropertyPage.ViewMoreAmenity);
			wb.WaitUntillVisiblility(PropertyPage.AmenityItems);
			/*
			 * List<WebElement> ConfElement =
			 * driver.findElements(By.xpath(dict.AmenityItems)); for (int i = 0;
			 * i < ConfElement.size(); i++) {
			 * System.out.println(ConfElement.get(i).getText()); }
			 */
			wb.scrolldown(PropertyPage.ViewLessAmenity);
			wb.scrollup(PropertyPage.ViewLessAmenity);
			Thread.sleep(1000);
			wb.ClickbyXpath(PropertyPage.ViewLessAmenity);
		} else {
			return ("Fail: Amenities Card was not present");
		}
		return ("Pass: Amenities card was validated Successfully");
	}

	public String ValidateFloorPlan() throws NoSuchElementException, InterruptedException, TimeoutException {
		System.out.println("inide test validate FloorPlan Card");
		if (wb.IsElementPresent(PropertyPage.FloorPlanCard)) {
			wb.scrolldown(PropertyPage.FloorPlanCard);
			wb.scrollup(PropertyPage.FloorPlanCard);
			wb.WaitUntillVisiblility(PropertyPage.FloorPlanCard);
			if (wb.IsElementPresent(PropertyPage.FloorPlanIMG)) {
				System.out.println("FLoor Plan Image was present on card");
			} else {
				return ("Fail: FLoor Plan Image was not present on card");
			}
		} else {
			return ("Fail: Floor Plan Card was not present");
		}
		return ("Pass: Floor Plan was validated Successfully");
	}

	public String ValidateSimilarProperty()
			throws NoSuchElementException, InterruptedException, TimeoutException, IOException {
		System.out.println("inide test validate Similar Property Card");
		if (wb.IsElementPresent(PropertyPage.Similar_property)) {
			wb.scrolldown(PropertyPage.Similar_property);
			wb.scrollup(PropertyPage.Similar_property);
			wb.WaitUntillVisiblility(PropertyPage.Similar_property);
			if (wb.IsElementPresent(PropertyPage.SimilarpropertyWindow)) {
				for (int i = 1; i < 5; i++) {
					if (wb.IsElementPresent((PropertyPage.PropertyCardImage+i+"]"))) {
						Thread.sleep(4000);
						wb.ClickbyXpath(((PropertyPage.PropertyCardImage+i+"]")));
						Thread.sleep(3000);
						String URL = wb.CurrentURL();
						int res = Webhelper.Get_Response(URL);
						if (res == 200) {
							if (wb.IsElementPresent(PropertyPage.ProjectHeader)) {
								Thread.sleep(1000);
								wb.Back();
							} else {
								wb.Back();
								Thread.sleep(1000);
								return ("Fail: Header was not present on Similar Property Page");
							}

						} else {
							wb.Back();
							Thread.sleep(1000);
							return ("Fail: response of URl is " + res);
						}
					} else {
						Thread.sleep(1000);
						return ("Fail: Image was not present on Similar Property on card " + i);
					}
				}
			} else {
				return ("Fail: Similar Property cards were not present");
			}
		}
		wb.scrolldown(PropertyPage.Similar_property);
		wb.scrollup(PropertyPage.Similar_property);
		closechat();
		if (wb.IsElementPresent(PropertyPage.SimilarpropertyWindow) && wb.IsElementPresent(PropertyPage.PropertyCardImage)) 
		{
		return ("Pass: Similar Property Cards are Validated");
		}
		else
			return ("Fail: Similar Property Cards are not Validated");
	}

	public String ValidateLead()
			throws NoSuchElementException, InterruptedException, TimeoutException, IOException, SQLException {
		String OTP = null;
		System.out.println("Inside Call Now Test");
		Thread.sleep(5000);
		wb.scrollup(PropertyPage.LeadDropDown);
		//wb.scrollup(PropertyPage.LeadDropDown);
		//Thread.sleep(2000);
		System.out.println("Selecting Country code for india");
		if (!(wb.IsElementPresent(PropertyPage.LeadDropDown))) {
			System.err.println("Country code dropdown of Connect now is not present");
		}
		Thread.sleep(1000);
		closechat();
		wb.WaitUntillVisiblility(PropertyPage.MobileNumberInput);
		String MobileNumber = ReadSheet("CallNow", "Phone Number", 2);
		Thread.sleep(1000);

		wb.ClickbyXpath(PropertyPage.MobileNumberInput);
		Thread.sleep(1000);
		wb.enterTextByxpath(PropertyPage.MobileNumberInput, MobileNumber);
		Thread.sleep(1000);
		wb.ClickbyXpath(PropertyPage.CallNowSubmit);
		Thread.sleep(1000);

		if (wb.getText(PropertyPage.Error).contains("error")) {
			System.err.println("some error occurred while submiting Call Now Lead");
			return ("Fail: Error found while submitting Error");
		}

		else if (wb.IsElementPresent(PropertyPage.OTP_Screen)) {
			System.out.println("OTP screen appeared on lead form");
			if (cn.ValidateBeforeOTP()) {
				System.out.println("ptigercrm.ENQUIRY_TEMP is updated before VErify OTP");
			} else {
				return ("Fail: ptigercrm.ENQUIRY_TEMP was not updated before Verify OTP");
			}
			closechat();
			if (cn.VerifyOTP().contains("Fail")) {
				return ("Fail: some error occurred while submiting OTP from Connect Now");
			}
		}
		Thread.sleep(2000);
		if (wb.IsElementPresent(PropertyPage.TringCallNow)) {
			//wb.WaitUntillVisiblility(PropertyPage.Thank);
			if (wb.IsElementPresent(PropertyPage.TringCallNow)) {
				// Thread.sleep(4000);
				// wb.Jsclickbyxpath(dict.ThanksSubmit);
			}

			else if (wb.getText(PropertyPage.Error).contains("error")) {
				wb.ClickbyXpath(PropertyPage.Skip);
				wb.WaitUntillVisiblility(PropertyPage.ThanksSubmit);
				wb.ClickbyXpath(PropertyPage.ThanksSubmit);
				return ("Fail: Some error occurred while submiting Share My datails Lead");
			}
		}

		String Result = cn.VerifyEnquiry();
		if (Result.contains("Pass")) {
			System.out.println(Result);
		} else {
			return ("Fail: Enquiry Table did not Updated popup did not open" + Result);
		}
		return ("Pass: Connect Now Functionality completed successfully");

	}

	public static void closechat() {
		try {
			if (wb.IsElementPresentById("inner-wrapper")) {
				// wb.ClickbyXpath(".//textarea[@id='input']");
				wb.ClickbyXpath(".//div[@class='cross']");
				System.out.println("Closed mchat popup");
			}
		} catch (Exception e) {
			System.out.println("mchat popup was not present");
		}

	}

	public String AboutBuilderCard() throws InterruptedException, IOException {
		System.out.println("Inside Validation of About Builder Card");
		String PastProjects = null;
		String OnGoingProjects = "1";
		driver = wb.getDriver();
		wb.scrolldown(PropertyPage.knowmoreProject);
		wb.scrollup(PropertyPage.knowmoreProject);
		if (wb.IsElementPresent(PropertyPage.ProjectScore)) {
			wb.ClickbyXpath(PropertyPage.knowmoreProject);
			Thread.sleep(3000);
			String URL = wb.CurrentURL();
			int res = Webhelper.Get_Response(URL);
			if (res == 200) {
				if (wb.IsElementPresent(PropertyPage.ProjectHeader)) {
					wb.Back();
					Thread.sleep(2000);
				} else {
					wb.Back();
					Thread.sleep(1000);
					return ("Fail: Header was not present on Similar Property Page");
				}

			} else {
				wb.Back();
				Thread.sleep(1000);
				return ("Fail: response of URl is " + res);
			}
		} else {
			return ("Fail: Project Score was not present ");
		}
		if (wb.IsElementPresent(PropertyPage.BuilderDescription)) {
			wb.scrolldown(PropertyPage.BuilderDescription);
			wb.scrollup(PropertyPage.BuilderDescription);
			List<WebElement> ConfElement = driver.findElements(By.xpath(PropertyPage.BuilderDescription));
			for (int i = 0; i < ConfElement.size(); i++) {
				System.out.println(ConfElement.get(i).getText());
			}
			if (wb.IsElementPresent(PropertyPage.Builderfacts)) {
				ConfElement = driver.findElements(By.xpath(PropertyPage.Builderfacts));
				for (int i = 0; i < ConfElement.size(); i++) {
					System.out.println(ConfElement.get(i).getText());
				}
				PastProjects = wb.getText(PropertyPage.PastProject);
				wb.ClickbyXpath(PropertyPage.PastProject);
				Thread.sleep(2000);
				String URL = wb.CurrentURL();
				int res = Webhelper.Get_Response(URL);
				if (res == 200) {
					Thread.sleep(1000);
					if (wb.IsElementPresent("//div[@class='bbcard']")) {
						if (PastProjects.equals(wb.getText("//div[@class='bcol'][3]//div[@class='val']"))) {
							wb.Back();
						} else {
							wb.Back();
							return ("Fail: Past Project Count is not matching");
						}
					} else {
						wb.Back();
						return ("Fail: Header was not present on Builder Page");
					}
				} else {
					wb.Back();
					return ("Fail: response of URl is on Ongoing Project" + res);
				}
				wb.scrolldown(PropertyPage.BuilderDescription);
				wb.scrollup(PropertyPage.BuilderDescription);
				Thread.sleep(2000);
				//OnGoingProjects = wb.getText(PropertyPage.OnGoingProject);
				closechat();
				wb.ClickbyXpath(PropertyPage.OnGoingProject);
				Thread.sleep(2000);
				URL = wb.CurrentURL();
				System.out.println(URL);
				res = Webhelper.Get_Response(URL);
				if (res == 200) {
					Thread.sleep(2000);
					if (wb.IsElementPresent("//div[@class='bbcard']")) {
						if (OnGoingProjects.equals(wb.getText("//div[@class='bcol'][2]//div[@class='val']"))) {
							wb.Back();
						} else {
							wb.Back();
							return ("Fail: Ongoing Project Count is not matching");
						}
					} else {
						wb.Back();
						return ("Fail: Header was not present on Builder Page");
					}
				} else {
					wb.Back();
					return ("Fail: response of URl is " + res);
				}
				if (wb.IsElementPresent(PropertyPage.moreProject)) {
					wb.scrolldown(PropertyPage.moreProject);
					wb.scrollup(PropertyPage.moreProject);
					Thread.sleep(3000);
					wb.ClickbyXpath(PropertyPage.moreProject);
					Thread.sleep(2000);
					URL = wb.CurrentURL();
					res = Webhelper.Get_Response(URL);
					if (res == 200) {
						if (wb.IsElementPresent("//div[@class='bbcard']")) {
							System.out.println(wb.getText("//div[@class='f-count-wrap']/span"));
							wb.Back();
						} else {
							wb.Back();
							return ("Fail: Header was not present on Builder Page");
						}
					} else {
						wb.Back();
						return ("Fail: response of URl is " + res);
					}
				} else {
					return ("Fail: know more link on about builder card was not present");
				}
			} else {
				return ("Fail: Builder Facts were not present");
			}
		} else {
			return ("Fail: Builder Description was not present");
		}
		return ("Pass: About Builder Card is been validated");
	}

	public String GetValueFromDB(String Query, String Value)
			throws InterruptedException, SQLException, TimeoutException {
		String URL = wb.CurrentURL();
		int len = URL.length();
		String Id = URL.substring(len - 6, len);

		String Database = "use proptiger ;";
		Query = Query + Id + "' ;";
		Thread.sleep(3000);
		db.Connect();

		ResultSet rs = db.Execute(Query, Database);
		while (rs.next()) {
			Value = rs.getString(Value);

		}
		return Value;
	}

	public String AboutLocalityCard() throws InterruptedException, IOException {
		System.out.println("Inside Verify Locality Card");
		String Result = null;
		wb.scrolldown(PropertyPage.Locality);
		wb.scrollup(PropertyPage.Locality);
		Thread.sleep(2000);
		if (wb.IsElementPresent(PropertyPage.LocalityDesk)) {
			System.out.println("Locality Description was present on card");
		} else {
			return ("Fail: Locality Description was not present on card");
		}
		wb.ClickbyXpath(PropertyPage.LocalityScore);
		Thread.sleep(1000);
		if (wb.IsElementPresentById("localityScorePopup")) {
			wb.ClickbyXpath(PropertyPage.LocalityScoreclose);
			System.out.println("Locality score pop up was present");
		} else {
			return ("Fail: Locality Score Popup was not present");
		}
		if (wb.IsElementPresent(PropertyPage.knowmoreLocality)) {
			wb.scrolldown(PropertyPage.Locality);
			wb.scrollup(PropertyPage.Locality);
			Result = ValidatePage(PropertyPage.knowmoreLocality, PropertyPage.CityHeroshot);
			if (Result.contains("Pass")) {

			} else {
				return ("Fail: Know More Link Was not Validated " + Result);
			}

		} else {
			return ("Fail: view more Locality card was not present");
		}
		
		Thread.sleep(2000);
		wb.scrolldown(PropertyPage.LocalityNeighbourHood);
		wb.scrollup(PropertyPage.LocalityNeighbourHood);
		Thread.sleep(2000);
		if (wb.IsElementPresent(PropertyPage.LocalityNeighbourHood)) {
			if (wb.IsElementPresent(PropertyPage.NeighbourHoodTabs)) {
				Thread.sleep(1000);
				wb.ClickbyXpath("(" + PropertyPage.NeighbourHoodTabs + "//li)[1]");
				Thread.sleep(1000);
				for (int j = 1; j < 4; j++) {
					wb.ClickbyXpath("(" + PropertyPage.MasterPlanTabs + ")[" + j + "]");
					Thread.sleep(1000);
					if (wb.IsElementPresent(PropertyPage.MapHeader)) {

					} else {
						return ("Fail: Map Header was not present on MAsterPlan");
					}
				}
				wb.ClickbyXpath("(" + PropertyPage.NeighbourHoodTabs + "//li)[2]");
				for (int j = 1; j < 6; j++) {
					wb.ClickbyXpath("(" + PropertyPage.NearByTabs + ")[" + j + "]");
					Thread.sleep(2000);
					if (wb.IsElementPresent(PropertyPage.NearByHeader)) {

					} else {
						return ("Fail: Map Header was not present on NearBy");
					}
				}
				wb.ClickbyXpath("(" + PropertyPage.NeighbourHoodTabs + "//li)[3]");
				if (wb.IsElementPresent("//input[@data-type='searchText']")) {

				} else {
					return ("Fail: Search Bar was not present on Commute");
				}
			} else {
				return ("Fail: Neighbour hood Tabs are not present");
			}
		} else {
			return ("Fail: Know Your Neighbour hood card is not present");
		}
		return ("Pass: About Locality Card is been validated");
	}

	public String ValidatePage(String Path, String Element) throws InterruptedException, IOException {
		if (wb.IsElementPresent(Path)) {
			wb.ClickbyXpath(Path);
			Thread.sleep(2000);
			String URL = wb.CurrentURL();
			int res = Webhelper.Get_Response(URL);
			if (res == 200) {
				Thread.sleep(3000);
				if (wb.IsElementPresent(Element)) {
					System.out.println("Element was Present on Card");
				} else {
					wb.Back();
					return ("Fail: Element was not present on card");
				}
			} else {
				wb.Back();
				return ("Fail: response of URl is " + res);
			}
		} else {
			return ("Fail: " + Path + "is not present on card");
		}
		wb.Back();
		return ("Pass: Page Validation is passed");
	}

	public String ValidateContactSeller()
			throws InterruptedException, NoSuchElementException, IOException, TimeoutException, SQLException {
		System.out.println("Inside Test Validation of Contact Seller");
		Thread.sleep(1000);
		wb.scrolldown(PropertyPage.ContactSellerWrap);
		wb.scrollup(PropertyPage.ContactSellerWrap);
		Thread.sleep(1000);
		if (wb.IsElementPresent(PropertyPage.ContactSellerHead)) {
			if (wb.IsElementPresent(PropertyPage.ContactSellerBody)) {
				Thread.sleep(1000);
				wb.ClickbyXpath(PropertyPage.ContactCallBtn);
				Thread.sleep(2000);
				if (wb.IsElementPresent(PropertyPage.SellerNumber)) {
					wb.ClickbyXpath(PropertyPage.ContactSellerLead);
					if (wb.IsElementPresent(PropertyPage.ContactSellerLeadForm)) {
						Thread.sleep(1000);
						wb.ClickbyXpath(PropertyPage.InputName);
						wb.clearbox(PropertyPage.InputName);
						wb.enterTextByxpath(PropertyPage.InputName, "qatester");
						Thread.sleep(1000);
						wb.ClickbyXpath(PropertyPage.InputMail);
						wb.clearbox(PropertyPage.InputMail);
						wb.enterTextByxpath(PropertyPage.InputMail, "qatester@tester.com");
						Thread.sleep(1000);
						wb.ClickbyXpath(PropertyPage.InputPhone);
						wb.clearbox(PropertyPage.InputPhone);
						wb.enterTextByxpath(PropertyPage.InputPhone, "8527019365");
						Thread.sleep(2000);
						wb.ClickbyXpath(PropertyPage.SubmitLead);
						Thread.sleep(2000);
						if (wb.IsElementPresent(PropertyPage.Thankyou)) {
							System.out.println("Lead submitted from UI now checking in DB");
							wb.ClickbyXpath(PropertyPage.ThankyouContinue);
							String Result = cn.VerifyEnquiry();
							if (Result.contains("Pass")) {
								System.out.println(Result);
							} else {
								return ("Fail: Enquiry Table did not Updated popup did not open" + Result);
							}
						} else {
							return ("Fail: some error occurred while submitting lead");
						}

					} else {
						return ("Fail: Lead Form was not present");
					}
				} else {
					return ("Fail: Seller Number wasnot present in Contact seller section ");
				}
			} else {
				return ("Fail: No Seller was present on Contact Seller");
			}
		} else {
			return ("Fail: Contact Seller Head was not presemnt");
		}
		return ("Pass: Contact Seller ");

	}

	public String BreadCrumb() throws InterruptedException, IOException {
		String Result = null;
		Thread.sleep(5000L);
		//wb.scrolldown(PropertyPage.BreadcrumbCard);
		//wb.scrollup(PropertyPage.BreadcrumbCard);
		if (wb.IsElementPresent(PropertyPage.BreadcrumbList)) {
			Result = ValidatePage(PropertyPage.BreadcrumbHome, PropertyPage.HomeMainImage);
			if (Result.contains("Pass")) {
				Thread.sleep(4000);
				//wb.scrolldown(PropertyPage.BreadcrumbCard);
				//wb.scrollup(PropertyPage.BreadcrumbCard);
				Result = ValidatePage(PropertyPage.BreadcrumbCity, PropertyPage.CityHeroShot);
				if (Result.contains("Pass")) {
					Thread.sleep(2000);
					//wb.scrolldown(PropertyPage.BreadcrumbCard);
					//wb.scrollup(PropertyPage.BreadcrumbCard);
					Result = ValidatePage(PropertyPage.BreadcrumbSERP, PropertyPage.SERPfilterbar);
					if (Result.contains("Pass")) {
						Thread.sleep(2000);
						//wb.scrolldown(PropertyPage.BreadcrumbCard);
						//wb.scrollup(PropertyPage.BreadcrumbCard);
						Result = ValidatePage(PropertyPage.BreadcrumbProject, PropertyPage.ProjectHeader);
						if (Result.contains("Pass")) {
							Thread.sleep(2000);
							System.out.println("Breadcrum Validated ");
						} else {
							return ("Fail: Project Page did not open from Bread Crumb" + Result);
						}
					} else {
						return ("Fail: SERP Page did not open from Bread Crumb" + Result);
					}
				} else {
					return ("Fail: City Page did not open from Bread Crumb" + Result);
				}
			} else {
				return ("Fail: Home Page did not open from Bread Crumb" + Result);
			}

		} else {
			return ("Fail: bread crumb was not present");
		}
		return ("Pass: breadcrumb validated");
	}

	public static void CloseAll() {
		db.Close();
		wb.CloseBrowser();

	}

}
