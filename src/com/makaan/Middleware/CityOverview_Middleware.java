package com.makaan.Middleware;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.makaan.Dictionary.CityOverview;
import com.makaan.Utilities.ConnectDB;
import com.makaan.Utilities.ExcelOperation;
import com.makaan.Utilities.Xls_Reader;
import com.makaan.Webhelper.Webhelper;
import com.makaan.Middleware.PYRMiddleware;

public class CityOverview_Middleware {

	public static Webhelper wb = null;
	public static CityOverview dict = null;
	public static WebDriver driver = null;;
	public static ExcelOperation ex = null;
	public static ConnectDB db = null;
	public static Xls_Reader xls = new Xls_Reader("Files/Marketplace.xls");
	public static PYRMiddleware pyr = null;

	public CityOverview_Middleware() {
		wb = new Webhelper();
		ex = new ExcelOperation();
		db = new ConnectDB();
		pyr= new PYRMiddleware();
		System.out.println("inside Middleware Constructor");

	}

	public boolean OpenURL() throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		String URL = ReadSheet("CityOverview", "URL", 2);
		wb.InitiateDriver();
		System.out.println("Opening URL through Middleware");
		wb.GetURL(URL);
		res = wb.Get_Response(URL);
		if (res == 200) {
			System.out.println("Response code got from URL " + res);
			System.out.println("Waiting till Makaan logo found on page");
			wb.WaitUntillVisiblility(dict.MakaanLogo);
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

	public String VerifyHeader() throws TimeoutException, InterruptedException {
		wb.WaitUntillVisiblility(dict.MakaanLogo);
		if (wb.IsElementPresent(dict.PageHeader)) {
			if (wb.IsElementPresent(dict.Buy_Rent)) {
				wb.ClickbyXpath(dict.buy_rentdropdown);
				wb.WaitUntillVisiblility(dict.buy);
				if (wb.IsElementPresent(dict.ListProperty)) {
					System.out.println("seller link is present");
					Thread.sleep(2000);
					wb.ClickbyXpath(dict.ListProperty);
					wb.WaitUntillVisiblility(dict.LoginPop);
					if (wb.IsElementPresent(dict.LoginPop)) {
						System.out.println("Login popup is open to connect with seller link");
						wb.WaitUntill(dict.Closepopup);
						wb.ClickbyXpath(dict.Closepopup);
					} else {
						return ("Fail: Login popup was not present on seller link button ");
					}
				} else {
					return ("Fail: List your property button in header is not present");
				}
			} else {
				return ("Fail: Buy/Rent drop box on page is not present");
			}

		} else {
			return ("Fail: Header of page is not present");
		}
		return ("Pass: Logi Form is validated");
	}

	public String VerifyImage() throws TimeoutException, InterruptedException {
		System.out.println(
				"Inside Test Validate heroShot Image on the page with annual price growth and annual price yield");
		if (wb.IsElementPresent(dict.HeroShot)) {
			if (wb.IsElementPresent(dict.AnnualGrowth) && (wb.IsElementPresent(dict.AnnualRental))) {
				System.out.println(" Annual growth of City is " + wb.getText(dict.AnnualGrowth));
				System.out.println(" Annual Average Rent of City is " + wb.getText(dict.AnnualRental));
			} else {
				return ("Fail:Annual Growth or Annual Rental on page is not present");
			}

		} else {
			return ("Fail:Image on page is not present");
		}
		return ("Pass:Image and price growth on page is present and verified");

	}

	public String VerifyDescription() throws TimeoutException, InterruptedException {
		System.out.println("Inside Test Validate Read more/less description with heading is present");
		if (wb.IsElementPresent(dict.CityDescription)) {
			wb.ClickbyXpath(dict.readMoreDesc);
			Thread.sleep(2000);
			if (wb.IsElementPresent(dict.Description)) {
				wb.scrolldown(dict.readLessDesc);
				wb.ClickbyXpath(dict.readLessDesc);
			} else {
				return ("Fail:Read more button is not working on descripton card");
			}
		} else {
			return ("Fail:Description on page is not present");
		}
		return ("Pass:Description on page is present and verified");
	}

	public String VerifyNavigationBar() throws TimeoutException, InterruptedException {
		System.out.println("Inside Test Validate Navigation Bar");
		if (wb.IsElementPresent(dict.Navigatorbar)) {
			System.out.println("Validating functionality of bar");
			if (ClickandMove(dict.ProjectsTab, dict.ProjectCard, "projects")) {
			} else {
				return ("Fail: Sponsored Pojects Tab was not Valiadated");
			}
			Thread.sleep(1000);
			if (ClickandMove(dict.PriceTrendTab, dict.PriceTrendCard, "Price Trend")) {
			} else {
				return ("Fail: Price Trend Tab was not Validated ");
			}
			Thread.sleep(1000);
			if (ClickandMove(dict.PropertyRangeTab, dict.PropertyRangeCard, "property")) {
			} else {
				return ("Fail: Property Range Tab was not validated");
			}
			if (ClickandMove(dict.PropertyinCity, dict.PropertyinCityCard, "properties")) {
			} else {
				return ("Fail: properties in city Tab was not validated");
			}
			if (ClickandMove(dict.PopularLocality, dict.PopularLocalityCard, "localities")) {
			} else {
				return ("Fail: Popular Locality Tab was not validated");
			}
			if (ClickandMove(dict.TopProjects, dict.TopProjectsCard, "projects")) {
			} else {
				return ("Fail: Top Projects Tab was not validated");
			}
		} else {
			return ("Fail: Navigator bar is not present");
		}

		return ("Pass:Navigation Bar is validated on Page");

	}

	public String NewTab(String Path, String Element, String Value)
			throws NoSuchElementException, IOException, TimeoutException {
		int res = 0;
		wb.ClickbyXpath(Path);
		wb.WaitUntill(Element);

		String URL = wb.CurrentURL();
		res = wb.Get_Response(URL);
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
			if (wb.getText(Tab).contains(Element)) {
				System.out.println(Element + " card was present");
				return true;
			} else {
				System.err.println(Element + " card was not present");
				return false;
			}
		} else {
			System.err.println(Path + " card was not present");
			return false;
		}

	}

	public String VerifySpotlighProjects()
			throws NoSuchElementException, IOException, TimeoutException, InterruptedException {
		System.out.println("Inside test Validate SpotLight Project");
		Thread.sleep(2000);
		driver = wb.getDriver();

		WebElement element = driver.findElement(By.xpath(dict.TopProjectsCard));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		if (wb.IsElementPresentById("city_sponsored_project")) {
			System.out.println("Sponsored Projects are Present on Page");
			wb.WaitUntillVisiblility(dict.SponsoredLeft);
			if (wb.IsElementPresent(dict.SponsoredLeft) && wb.IsElementPresent(dict.SponsoredRight)) {
				Thread.sleep(5000);
				wb.PageRefresh();
				wb.ClickbyXpath("//div[@class='round-carousel']//div[@id='next-button']");
				Thread.sleep(2000);
				wb.WaitUntillVisiblility(dict.SponsoredActive);
				String ProjectName = wb.getText(dict.SponsoredActive);
				wb.WaitUntillVisiblility(dict.SponsoredActiveKnowMore);
				wb.ClickbyXpath(dict.SponsoredActiveKnowMore);
				Thread.sleep(2000);
				String URL = wb.CurrentURL();
				if (URL.contains("isSponsored=true")) {
					System.out.println("Url Found on Page is" + URL);
					int res = wb.Get_Response(URL);
					if (res == 200) {
						System.out.println("Response code got from " + URL + "is " + res);
						if (wb.IsElementPresent(dict.VerifyProject)) {
							System.out.println("Project link is opening");
							String Name = wb.getAttribute(dict.VerifyProject, "title");
							Name = Name.toLowerCase();
							ProjectName = ProjectName.toLowerCase();
							Name = wb.Splitter(Name, " ", 1);
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
							return ("Fail: Elemnet was not present on Locality Page" + dict.VerifyProject);
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

	public String VerifyPriceTrend()
			throws InterruptedException, NoSuchElementException, IOException, TimeoutException {
		System.out.println("Inside Test Verify Price Trends");
		String Result = null, Value = null;
		if (wb.IsElementPresent(dict.PriceTrendCard)) {
			wb.scrolldown(dict.PriceTrendCard);
			Thread.sleep(1000);
			if (wb.IsElementPresent(dict.PriceGraph)) {
				if (wb.IsElementPresent(dict.Localities)) {
					for (int i = 1; i < 5; i++) {
						Value = wb.getText(dict.Localities);
						Value = wb.Splitter(Value, " ", 1);
						Result = NewTab(dict.CityTopLocality + i + "]", dict.Localityheroimg, Value);
						Thread.sleep(1000);
						wb.scrolldown(dict.PriceTrendCard);
						if (Result.contains("Fail")) {
							return Result;
						}
					}
				} else {
					return ("Fail:Top Localities are not present on page");
				}
			} else {
				return ("Fail: Price Trend graph are not present on page");
			}
		} else {
			return ("Fail: Price Trend was not Verified as Price Trend card was not present on page");
		}

		return ("Pass: Price Trend was Verified Successfully");
	}

	public String VerifyPropertyinCity() throws InterruptedException{
		System.out.println("Inside Test Verify Property in city Card");
		wb.scrolldown(dict.PropertyinCityCard);
		Thread.sleep(1000);
		for(int i =1; i<6;i++){
			if(wb.IsElementPresent(dict.PropertyinCity +i+"]")){
				System.out.println(wb.getText(dict.PropertyInCityType1+i+dict.PropertyInCityType2));
			}else{
				return("Fail: property type cards are not present on page");
			}
		}
		return ("Pass: Property Range was Verified Successfully");
	}
	
	public String VerifyPropertyRange() throws InterruptedException, NoSuchElementException, IOException, TimeoutException{
		System.out.println("Inside Test Verify Property Range Card");
		String result = null;
		wb.scrolldown(dict.PropertyRangeCard);
		Thread.sleep(1000);
		wb.ClickbyXpath(dict.PropertyRangeBuyFilter);
		wb.ClickbyXpath(dict.SelectBuy);
		Thread.sleep(2000);
		wb.ClickbyXpath(dict.PropertyTypeFilter);
		wb.ClickbyXpath(dict.PropertyTypeApartment);
		Thread.sleep(2000);
		if(wb.IsElementPresent(dict.ViewProperty)){
			Thread.sleep(2000);
			result = NewTab(dict.ViewProperty, dict.SERPfilterbar, "property");
			if(result.contains("Fail")){
				return ("Fail: View Property button was not verified "+result);
			}
		}else{
			return ("Fail: View Property button was not presenton page"); 
		}
		
		return ("Pass: Property Range was Verified Successfully");
	}
	
	public String VerifyLead() throws InterruptedException, NoSuchElementException, TimeoutException, IOException, SQLException{
		String Result = null;
		wb.scrolldown(dict.LeadSection);
		Thread.sleep(1000);
		Result= pyr.PYRBuy(dict.LeadForm);
		if(Result.contains("Fail")){
			return Result;
		}
		return("Pass: Lead form verified successfully");
	}
	
	public String VerifyPopularLocality() throws InterruptedException {
		String Result = null;
		wb.scrolldown(dict.PopularLocality);
		Thread.sleep(1000);
		wb.ClickbyXpath(dict.RentTab);
		Thread.sleep(1000);
		if(wb.IsElementPresent(dict.RentWindow)){
			
		}else{
			return("Fail: No links are present on Rent Tab");
		}
		Thread.sleep(1000);
		wb.ClickbyXpath(dict.BuyTab);
		Thread.sleep(1000);
		if(wb.IsElementPresent(dict.BuyWindow)){			
		}else{
			return("Fail: No links are present on Buy Tab");
		}
		Thread.sleep(1000);
		return("Pass: Lead form verified successfully");
	}
	
	public String VerifyTopProjects() throws InterruptedException {
		String Result = null;
		wb.scrolldown(dict.TopProjects);
		Thread.sleep(1000);
		if(wb.IsElementPresent(dict.TopProjectswindow)){
			
		}else{
			return("Fail: No links of Top Projects are present");
		}
		return("Pass: Lead form verified successfully");
	}
	
	
}
