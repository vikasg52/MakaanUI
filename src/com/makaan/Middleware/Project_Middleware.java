package com.makaan.Middleware;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.makaan.Dictionary.Project;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;
import com.makaan.Middleware.ConnectNowMiddleware;

public class Project_Middleware {

	public static Webhelper wb = null;
	public static Project dict = null;
	public static WebDriver driver;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");
	public static ConnectNowMiddleware cn = null;

	public Project_Middleware() {
		System.out.println("inside Project Middleware Constructor");

		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();
		cn = new ConnectNowMiddleware();
		System.out.println("inside Middleware Constructor");

	}

	public static String OpenURL() throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		String URL = ReadSheet("Project", "URL", 2);
		wb.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		res = wb.Get_Response(URL);
		if (res == 200) {
			System.out.println("Response code got from URL " + res);
			System.out.println("Waiting till Makaan logo found on page");
			wb.WaitUntillVisiblility(dict.MakaanLogo);
		} else {
			return ("Fail: Response code got from URL " + res);

		}
		return ("Pass: URL open Successfully");
	}

	public static String ReadSheet(String Sheet, String Col_Name, int row_id)
			throws IOException, NoSuchElementException, TimeoutException {

		String data = xls.getCellData(Sheet, Col_Name, row_id);
		System.out.println("Data from sheet " + data);

		return (data);
	}

	public String ValidateProjectImage() throws TimeoutException, InterruptedException {
		System.out.println(
				"In this case we will validate Image Count Image count, next button, full view of image, Name on card");
		int GalleryCount = 0;
		int SERPcount = 0;

		String URL = wb.CurrentURL();
		if (wb.IsElementPresent(dict.ProjectImage)) {
				wb.ClickbyXpath(dict.ProjectImage);
			Thread.sleep(1000);
			if (wb.IsElementPresent(dict.galleryTotalCount) && wb.IsElementPresent(dict.imagegallery)) {
				System.out.println("Gallery Total count" + wb.getText(dict.galleryTotalCount));
				GalleryCount = Integer.parseInt(wb.getText(dict.galleryTotalCount));
				if (GalleryCount > 1) {
					wb.ClickbyXpath(dict.galleryNext);
					wb.ClickbyXpath(dict.galleryNext);
				}
				Thread.sleep(1000);
				wb.ClickbyXpath(dict.galleryClose);
			} else {
				return ("Fail: Images or Count in Gallery are not present");
			}
			wb.WaitUntillVisiblility(dict.MakaanLogo);
		}
		return ("Pass: Image on Gallery are verified");
	}

	public String ValidateProjectHeader() throws NoSuchElementException, InterruptedException {
		System.out.println("Inside Test Validate Project Header");
		if (wb.IsElementPresent(dict.ProjectHeader)) {
			String Name = wb.Splitter(wb.getAttribute(dict.ProjectName, "title"), " ", 1);
			System.out.println("Name: " + Name);
			if (wb.CurrentURL().contains(Name.toLowerCase())) {
				Thread.sleep(2000);
				if (wb.IsElementPresent(dict.Shortlist)) {
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
		System.out.println("inide test validate Navigator on Project Image");
		if (wb.IsElementPresent(dict.Navigatorbar)) {
			System.out.println("Validating functionality of bar");
			if (ClickandMove(dict.Configuration, dict.TabConfiguration, "configurations")) {
			} else {
				return ("Fail: Configuration Tab was not Valiadated");
			}
			Thread.sleep(1000);
			if (ClickandMove(dict.TabOverview, dict.OverviewCard, "description")) {
			} else {
				return ("Fail: Overview Tab was not Validated ");
			}
			Thread.sleep(1000);
			if (ClickandMove(dict.SimilarProject, dict.SimilarProjectCard, "similar")) {
			} else {
				return ("Fail: Similar Project Tab was not validated");
			}
			if (ClickandMove(dict.Builder, dict.BuilderCard, "builder")) {
			} else {
				return ("Fail: Builder Tab was not validated");
			}
			if (ClickandMove(dict.Locality, dict.LocalityCard, " ")) {
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
		if (wb.IsElementPresent(Path)) {
			Thread.sleep(1000);
			wb.ClickbyXpath(Path);
			Thread.sleep(1000);
			wb.ClickbyXpath(Path);
			Thread.sleep(3000);
			if (Path.contains("Locality")) {
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
		wb.scrolldown(dict.OverviewCard);
		wb.WaitUntillVisiblility(dict.OverviewCard);
		Thread.sleep(2000);
		wb.scrollup(dict.OverviewReadMore);
		Thread.sleep(1000);
		wb.ClickbyXpath(dict.OverviewReadMore);
		wb.WaitUntillVisiblility(dict.OverviewDescription);
		wb.ClickbyXpath(dict.OverviewReadMore);
		try {
			if (wb.IsElementPresent(dict.KeyDetails)) {
				wb.scrolldown(dict.KeyDetails);
				wb.WaitUntillVisiblility(dict.KeyDetails);
				Thread.sleep(2000);
				List<WebElement> ConfElement = driver.findElements(By.xpath(dict.KeyDetails));
				for (int i = 0; i < ConfElement.size(); i++) {
					System.out.println(ConfElement.get(i).getText());
				}
			}
			if (wb.IsElementPresent(dict.AmenityCard)) {
				wb.scrolldown(dict.AmenityCard);
				wb.scrollup(dict.AmenityCard);
				wb.WaitUntillVisiblility(dict.AmenityCard);
				wb.ClickbyXpath(dict.ViewMoreAmenity);
				Thread.sleep(2000);
				List<WebElement> ConfElement = driver.findElements(By.xpath(dict.AmenityItems));
				for (int i = 0; i < ConfElement.size(); i++) {
					System.out.println(ConfElement.get(i).getText());
				}
				Thread.sleep(1000);
				wb.scrolldown(dict.ViewLessAmenity);
				wb.scrollup(dict.ViewLessAmenity);
				Thread.sleep(1000);
				wb.ClickbyXpath(dict.ViewLessAmenity);
			}
			Thread.sleep(1000);
			if (wb.IsElementPresent(dict.SpecificationCard)) {
				wb.scrolldown(dict.SpecificationCard);
				wb.scrollup(dict.SpecificationCard);
				wb.WaitUntillVisiblility(dict.SpecificationCard);
				wb.ClickbyXpath(dict.ViewMoreSpecification);
				Thread.sleep(2000);
				List<WebElement> ConfElement = driver.findElements(By.xpath(dict.SpecificationItem));
				for (int i = 0; i < ConfElement.size(); i++) {
					System.out.println(ConfElement.get(i).getText());
				}
			}
			Thread.sleep(2000);
			if (wb.IsElementPresent(dict.PriceTrendCard)) {
				wb.scrolldown(dict.PriceTrendCard);
				wb.scrollup(dict.PriceTrendCard);
				wb.WaitUntillVisiblility(dict.PriceTrendCard);
				try {
					if (wb.IsElementPresent(dict.PriceTrendNoData)) {
						return ("Fail: Price Trend graph was not present");
					}
				} catch (NoSuchElementException e) {
				}
			}

			if (wb.IsElementPresent(dict.PreapprovedLoan)) {
				wb.scrolldown(dict.PreapprovedLoan);
				wb.scrollup(dict.PreapprovedLoan);
				try {
					if (wb.IsElementPresent(dict.PreapprovedBanks)) {
					}
				} catch (NoSuchElementException e) {
					return ("Fail: Banks are not present on Pre-approved Loan Section");
				}
			}
		} catch (NoSuchElementException e) {
			return ("Fail: Card was not present" + e);
		}

		return ("Pass: Overview Section is been validated");

	}
	
	

	public String ValidateSimilarProject() throws InterruptedException, IOException {
		System.out.println("Inside Test Validate Similar Project");
		String ProjectName = null;
		String ProjectName2 = null;
		wb.scrolldown(dict.SimilarProjectCard);
		wb.scrollup(dict.SimilarProjectCard);
		if (wb.IsElementPresent(dict.SimilarProject)) {
			for (int i = 1; i < 5; i++) {
				if (wb.IsElementPresent((dict.ProjectCardImage) + i + "]//img")) {
					ProjectName = wb.getAttribute((dict.ProjectCardImage) + i + "]//img", "alt");
					System.out.println(ProjectName);
					Thread.sleep(2000);
					wb.ClickbyXpath((dict.ProjectCardImage) + i + "]");
					Thread.sleep(3000);
					String URL = wb.CurrentURL();
					int res = wb.Get_Response(URL);
					if (res == 200) {
						if (wb.IsElementPresent(dict.ProjectHeader)) {
							ProjectName2 = wb.getAttribute(dict.ProjectName, "title");
							System.out.println(ProjectName2);
							if ((ProjectName).equals(ProjectName2)) {
								wb.Back();
								Thread.sleep(1000);
							}
							
						} else {
							wb.Back();
							Thread.sleep(1000);
							return ("Fail: Header was not present on Project Page");
						}
						
					} else {
						wb.Back();
						Thread.sleep(1000);
						return ("Fail: response of URl is " + res);
					}
				} else {
					Thread.sleep(1000);
					return ("Fail: Image was not present on Similar Project on card " + i);
				}
			}
		} else {
			return ("Fail: Similar Project cards were not present");
		}
		wb.scrolldown(dict.SimilarProjectCard);
		wb.scrollup(dict.SimilarProjectCard);
		if (wb.IsElementPresent(dict.ProjectNextImage) && wb.IsElementPresent(dict.ProjectPreviousImage)){
			Thread.sleep(2000);
			closechat();
			Thread.sleep(2000);
			wb.ClickbyXpath(dict.ProjectNextImage);
			Thread.sleep(2000);
			try{
			if (wb.IsElementPresent(dict.ProjectNextDisable)) {
				System.out.println("Next Iage is not present on Similar Project");
			} else {
				return ("Fail: More than four projects are present on Similar Project Card");
			}
			Thread.sleep(2000);
			wb.ClickbyXpath(dict.ProjectPreviousImage);
			Thread.sleep(2000);
			if (wb.IsElementPresent(dict.ProjectPreviousDisable)) {
				System.out.println("Previous Iage is not present on Similar Project");
			} else {
				return ("Fail: More than four projects are present on Similar Project Card");
			}
			}catch(NoSuchElementException e){
				System.out.println("Next or previous disable is not present");
			}
		} else {
			return ("Fail: Next or Previous button was not present on Project page");
		}
		return ("Pass: Similar Project Cards were Validated");
	}

	public String AboutBuilderCard() throws InterruptedException, IOException {
		System.out.println("Inside Validation of About Builder Card");
		String PastProjects = null;
		String OnGoingProjects = null;
		driver = wb.getDriver();
		wb.scrolldown(dict.BuilderCard);
		wb.scrollup(dict.BuilderCard);
		if (wb.IsElementPresent(dict.BuilderDescription)) {
			List<WebElement> ConfElement = driver.findElements(By.xpath(dict.BuilderDescription));
			for (int i = 0; i < ConfElement.size(); i++) {
				System.out.println(ConfElement.get(i).getText());
			}
			if (wb.IsElementPresent(dict.Builderfacts)) {
				ConfElement = driver.findElements(By.xpath(dict.Builderfacts));
				for (int i = 0; i < ConfElement.size(); i++) {
					System.out.println(ConfElement.get(i).getText());
				}
				try {
					PastProjects = wb.getText(dict.PastProject);
					wb.ClickbyXpath(dict.PastProject);
					Thread.sleep(2000);
					String URL = wb.CurrentURL();
					int res = wb.Get_Response(URL);
					if (res == 200) {
						if (wb.IsElementPresent("//div[@class='bbcard']")) {
							if (PastProjects.equals(wb.getText("//div[@class='bcol'][2]//div[@class='val']"))) {
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
						return ("Fail: response of URl is " + res);
					}
					wb.scrolldown(dict.BuilderCard);
					wb.scrollup(dict.BuilderCard);
					Thread.sleep(1000);
					OnGoingProjects = wb.getText(dict.OnGoingProject);
					wb.ClickbyXpath(dict.OnGoingProject);
					Thread.sleep(2000);
					URL = wb.CurrentURL();
					res = wb.Get_Response(URL);
					if (res == 200) {
						if (wb.IsElementPresent("//div[@class='bbcard']")) {
							if (OnGoingProjects.equals(wb.getText("//div[@class='bcol'][3]//div[@class='val']"))) {
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
					if (wb.IsElementPresent(dict.knowmoreProject)) {
						wb.scrolldown(dict.knowmoreProject);
						wb.scrollup(dict.knowmoreProject);
						Thread.sleep(3000);
						wb.ClickbyXpath(dict.knowmoreProject);
						Thread.sleep(2000);
						URL = wb.CurrentURL();
						res = wb.Get_Response(URL);
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

				} catch (NoSuchElementException e) {
					System.out.println("Past projects or On going Projects are not present on Page");
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
		Thread.sleep(2000);
		wb.scrolldown(dict.LocalityCard);
		wb.scrollup(dict.LocalityCard);
		if (wb.IsElementPresent(dict.LocalityDesk)) {
			System.out.println("Locality Description was present on card");
		} else {
			return ("Fail: Locality Description was not present on card");
		}
		wb.ClickbyXpath(dict.LocalityCard);
		Thread.sleep(1000);
		if (wb.IsElementPresentById("localityScorePopup")) {
			wb.ClickbyXpath(dict.LocalityScoreclose);
			System.out.println("Locality score pop up was present");
		} else {
			return ("Fail: Locality Score Popup was not present");
		}
		if (wb.IsElementPresent(dict.knowmoreLocality)) {
			wb.scrolldown(dict.LocalityCard);
			wb.scrollup(dict.LocalityCard);
			Result = ValidatePage(dict.knowmoreLocality, dict.CityHeroShot);
			if (Result.contains("Pass")) {

			} else {
				return ("Fail: Know More Link Was not Validated " + Result);
			}

		} else {
			return ("Fail: view more Locality card was not present");
		}
		wb.scrolldown(dict.LocalityNeighbourHood);
		wb.scrollup(dict.LocalityNeighbourHood);
		Thread.sleep(2000);
		if (wb.IsElementPresent(dict.LocalityNeighbourHood)) {
			if (wb.IsElementPresent(dict.NeighbourHoodTabs)) {
				Thread.sleep(1000);
				wb.ClickbyXpath("(" + dict.NeighbourHoodTabs + "//li)[1]");
				Thread.sleep(1000);
				for (int j = 1; j < 4; j++) {
					wb.ClickbyXpath("(" + dict.MasterPlanTabs + ")[" + j + "]");
					Thread.sleep(1000);
					if (wb.IsElementPresent(dict.MapHeader)) {

					} else {
						return ("Fail: Map Header was not present on MAsterPlan");
					}
				}
				wb.ClickbyXpath("(" + dict.NeighbourHoodTabs + "//li)[2]");
				for (int j = 1; j < 6; j++) {
					wb.ClickbyXpath("(" + dict.NearByTabs + ")[" + j + "]");
					Thread.sleep(1000);
					if (wb.IsElementPresent(dict.NearByHeader)) {

					} else {
						return ("Fail: Map Header was not present on NearBy");
					}
				}
				wb.ClickbyXpath("(" + dict.NeighbourHoodTabs + "//li)[3]");
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

	public String BreadCrumb() throws InterruptedException, IOException {
		String Result = null;
		wb.scrolldown(dict.BreadcrumbCard);
		wb.scrollup(dict.BreadcrumbCard);
		if (wb.IsElementPresent(dict.BreadcrumbList)) {
			Result = ValidatePage(dict.BreadcrumbHome, dict.HomeMainImage);
			if (Result.contains("Pass")) {
				wb.scrolldown(dict.BreadcrumbCard);
				wb.scrollup(dict.BreadcrumbCard);
				Result = ValidatePage(dict.BreadcrumbCity, dict.CityHeroshot);
				if (Result.contains("Pass")) {
					wb.scrolldown(dict.BreadcrumbCard);
					wb.scrollup(dict.BreadcrumbCard);
					Result = ValidatePage(dict.BreadcrumbSERP, dict.SERPfilterbar);
					if (Result.contains("Pass")) {
						System.out.println("Breadcrum Validated ");
					} else {
						return ("Fail: SERP Page did not open from Bread Crumb" + Result);
					}
				} else {
					return ("Fail: SERP Page did not open from Bread Crumb" + Result);
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

	public String ValidatePage(String Path, String Element) throws InterruptedException, IOException {
		if (wb.IsElementPresent(Path)) {
			wb.ClickbyXpath(Path);
			Thread.sleep(2000);
			String URL = wb.CurrentURL();
			int res = wb.Get_Response(URL);
			if (res == 200) {
				Thread.sleep(2000);
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

	public String ConnectNow()throws NoSuchElementException, TimeoutException, InterruptedException, IOException, SQLException {
		String OTP = null;
		System.out.println("Inside Call Now Test");
		Thread.sleep(2000);
		wb.scrollup(dict.LeadDropDown);
		wb.scrollup(dict.LeadDropDown);
		Thread.sleep(2000);
			System.out.println("Selecting Country code for india");
			if (!(wb.IsElementPresent(dict.LeadDropDown))) {
				System.err.println("Country code dropdown of Connect now is not present");
			}
			Thread.sleep(3000);
			closechat();
			wb.WaitUntillVisiblility(dict.MobileNumberInput);
			String MobileNumber = ReadSheet("CallNow", "Phone Number", 2);
			Thread.sleep(1000);
			
			wb.ClickbyXpath(dict.MobileNumberInput);
			Thread.sleep(1000);
			wb.enterTextByxpath(dict.MobileNumberInput, MobileNumber);
			Thread.sleep(1000);
			wb.scrolldown(dict.CallNowSubmit);
			wb.scrollup(dict.CallNowSubmit);
			wb.ClickbyXpath(dict.CallNowSubmit);
			Thread.sleep(1000);

			if (wb.getText(dict.Error).contains("error")) {
				System.err.println("some error occurred while submiting Call Now Lead");
				return ("Fail: Error found while submitting Error");
			}

			else if (wb.IsElementPresent(dict.OTP_Screen)) {
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
			if (wb.IsElementPresent(dict.TringCallNow)) {
				wb.WaitUntillVisiblility(dict.Thank);
				if (wb.IsElementPresent(dict.Thank)) {
					Thread.sleep(4000);
					wb.Jsclickbyxpath(dict.ThanksSubmit);
				}

				else if (wb.getText(dict.Error).contains("error")) {
					wb.ClickbyXpath(dict.Skip);
					wb.WaitUntillVisiblility(dict.ThanksSubmit);
					wb.ClickbyXpath(dict.ThanksSubmit);
					return ("Fail: Some error occurred while submiting Share My datails Lead");
				}
			}
			
			String Result = cn.VerifyEnquiry();
			if(Result.contains("Pass")){
				System.out.println(Result);
			}else{
				return ("Fail: Enquiry Table did not Updated popup did not open" +Result);
			}
			return ("Pass: Connect Now Functionality completed successfully");

	}
	public static void closechat()  {
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
	
}
