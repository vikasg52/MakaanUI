package com.makaan.Dictionary;

public class SERP {

	public static String MakaanLogo = "//a[@title='get your home at makaan']";
	
	//public static String MakaanLogo = ".//div[@class='makaan-logo']";

	public static String filterbar = "//div[@id='mod-filter-1']";

	public static String buy_rentdropdown = ".//div[@data-type='categoryDropdown']/i";

	public static String buy = ".//ul[@class='dd ddbuyrent']/li[1]";
	public static String rent = " .//ul[@class='dd ddbuyrent']/li[2]";

	public static String Budget = ".//span[@data-default='Budget']";
	public static String BudgetMin = ".//div[@data-type='budget']//input[@placeholder='Min']";
	public static String BudgetMax = ".//div[@data-type='budget']//input[@placeholder='Max']";
	public static String BudgetMinValue = ".//div[@data-target = 'range-dropdown-options-wrapper-min']/div[@data-value= '";
	public static String BudgetMaxValue = ".//div[@data-target = 'range-dropdown-options-wrapper-max']/div[@data-value= '";

	public static String Bedroom = ".//span[@data-default='Bedroom']";
	public static String BedroomType = ".//div[@data-type='beds']//span[@class='boxoption']/span[contains(. ,'";

	public static String PropertyArea = ".//div[@data-type='property-area-dropdown']/span";

	public static String MoreFilter = ".//div[@class='tfcol more-filter-wrap']";

	public static String PropertyTypeAppartment = ".//span[@class='txt'][contains(text(),'Apartment')]";

	public static String PostedByAgent = ".//span[contains(text(),'Agent')]";
	
	public static String PostedByBuilder = ".//div[@data-type='postedBy']//span[contains(text(),'Builder')]";
	
	public static String PostedByOwner = ".//div[@data-type='postedBy']//span[contains(text(),'Owner')]";

	public static String AgeofPropertyAny = ".//label[@for='ageOfProperty3']//span[@class='boxoption']//span[contains(text(),'Any')]";

	public static String SortByVal = "//div[@class='sortby-val']";

	public static String ResetButton = ".//span[@class='resetbtn']";

	public static String SelectBathroom = ".//div[@class='fwrap fbath']//span[@class='boxoption']//span[contains(text(),'1')]";

	public static String SelectNew_Resale = ".//div[@data-type='newOrResale']//div[@data-type='single-select-dropdown']";
	public static String Selectnew = ".//div[@class='select-dropdown']//li[@data-value='new']";
	
	public static String PropertyCount = ".//span[@class='val-count']";
	
	public static String PropertyType = ".//h1[@class='val-loc']";
	
	public static String FirstPropertycard = ".//div[@data-track-scroll='" ;
	
	public static String ImageCount = "//div[@class='img-count']" ;
	
	public static String Nextimage = "//div[@class='btn-next']";
	
	public static String PropertyLink = "//div[@data-track-scroll='1']//div[@class='proplink-wrap']";
	
	public static String Propertyrate = ".//div[@data-track-scroll='1']//div[@class='rate']//span[@class='val']";
	
	public static String Propertyprice = "//div[@class='price']//span[@class='val']";
	
	public static String ViewMoreProperty = ".//div[@data-track-scroll='1']//div[@class='otherDetails']/a";
	
	public static String SellerType = ".//div[@data-track-scroll='1']//span[@class='seller-type']";
	
	public static String SellerLink = ".//div[@data-track-scroll='1']//div[@class='seller-info']";
	
	public static String VerifyAgent = ".//div[@class='broker-name']";
	
	public static String VerifyBuilder =  ".//div[@class='b-name']";
	
	public static String VerifyProperty = ".//div[@data-container='carousel']";

	public static String LocationLink = ".//div[@data-track-scroll='1']//a[@class='loclink']";
	
	public static String ProjectLink = ".//div[@data-track-scroll='3']//a[@class='projName']";
	
	public static String VerifyLocality =".//div[@class='mainname']";
	
	public static String SetAlertBox = ".//div[@data-module='saveSearch']//div[@class='basic-hdr']";
	
	public static String SetAlert = ".//div[@class='set-alert-wrap']";
	
	public static String SetAlertName = ".//div[@id='savesearch_name']//input[@name='name']";
	
	public static String SetAlertEmail = ".//div[@id='savesearch_email']//input[@name='name']";
	
	public static String SetAlertMesage =".//div[@class='field-wrap']/span[@data-message='message']";
	
	public static String SetAlertsubmit = ".//a[@data-type='saveAlertButton']";
	
	public static String BuyerJourney = ".//div[@class='profile-pic']//a";
	
	public static String SaveSearchText = ".//div[@class='save-search-ellip']";
	
	public static String DeleteSaveSearch = ".//span/i[@class='icon-close']";
	
	public static String RemoveButton = ".//div[@class='cancel-options']/button[contains(.,'remove')]";
	
	public static String PageActive = ".//div[@class='pagination']//span[@class='active']";
	
	public static String Pagination = ".//div[@class='pagination']";
	
	public static String NextPage =	".//div[@class='pagination']//i[@class='icon-chevron-right']";
	
	public static String TrackJourney = ".//a[@data-type='dashboard-link']//div[@class='sbc-link']";
	
	public static String Shortlist = ".//div[@data-track-scroll='1']//i[@class='icon-makaan']";
	
	public static String Shortlisted = ".//div[@data-track-scroll='1']//i[@class='icon-makaanfill']";
	
	public static String AppCard = ".//a[@data-type='download-app']//div[@class='sbc-link']";
	
	public static String SortHightolow = ".//label[@for='sortBy1']";
	
	public static String SortLowtoHigh = ".//label[@for='sortBy2']";
	public static String Relevance = ".//label[@for='sortBy0']";
	
	public static String RelevanceFilter =  "//div[@data-default='Relevance']";
	
	public static String SortSellerRating = ".//label[@for='sortBy3']";
	
	public static String Buy_RentCard = ".//div[@data-type='buy-rent-toggle']";
	
	public static String Buy_RentText = ".//div[@data-type='buy-rent-toggle']//strong";
	
	public static String LocalityLink = ".//ul[@class='similar-list']/li[";
	
	public static String LocalityText = "]//div[@class='locname']//span";
	
	public static String LocalityCard = ".//div[@id='similarContent']";
	
	public static String BudgetReset0 = ".//div[@data-type = 'budget']//div[@data-value='0']";
	
	public static String BudgetResetAny = ".//div[@data-type = 'budget']//div[@data-value='']";
	
	public static String ListView = ".//div[@data-type='list-view']";
	
	public static String MapView = ".//div[@data-type='map-view']";
	
	public static String MapModule =".//div[@id='map-module']";
	
	public static String ListArea = ".//div[@class='list-mainarea']";
	
	public static String KnowMore = ".//a[@data-type='knowMoreAbout']";
	
	public static String CityHeroshot = ".//div[@id='city-overview']";
	
	public static String SponsoredSeller = "//span[@class='seller-type']";
	
	public static String Sponsored = "//div[@class='sponsored']";
	
	public static String DefaultImage ="//div[@data-track-scroll='1']//img[@data-src='https://content.makaan.com/17/0/569/2939994.png?width=360&height=270']";
	
	public static String INR = "//div[@class='price']//span[@class='val']/meta[1]";
	
	public static String SellerRating = "//div[@data-module='sellerRating']";
	
	public static String imagegallery = "(//div[@class='img-slide'])[1]//img"; 
	
	public static String galleryTotalCount = ".//span[@class='lg-counter-all']";

	public static String galleryProperty = ".//div[@class='lg-inner']//img";
	
	public static String galleryNext = ".//div[@data-type='right-click']";
	
	public static String galleryClose = ".//span[@class='lg-close mobile-close lg-icon']";
	
	public static String LastPage = "//div[@class='pagination']//li[7]";
	
	public static String Locality = "//a/span";
	
	public static String ViewPhone = "(//a[contains(text() , 'view phone') and @data-type='openLeadFormViewPhone'])[1]";
	public static String ClosePopup = ".//div[@class='close-popup']/i";

	
}