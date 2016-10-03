package com.makaan.Dictionary;

public class CityOverview {

	public static String MakaanLogo = "//a[@title='get your home at makaan']";
	public static String PageHeader  = "//header[@class='header']";
	public static String Buy_Rent = "//div[@class='buy-rent text-center']";
	public static String buy_rentdropdown = ".//div[@data-type='categoryDropdown']/i";

	public static String buy = ".//ul[@class='dd ddbuyrent']/li[1]";
	public static String rent = " .//ul[@class='dd ddbuyrent']/li[2]";
	
	public static String ListProperty = "//li[@class='sellersitelink']";
	public static String LoginPop = ".//div[@class='login-popup']";
	public static String Closepopup = ".//span[@data-type='close-login']";
	
	public static String HeroShot = "//div[@class='blackTransparentBack']";
	public static String AnnualGrowth = "//section[@class='city-info']//li[1]/div[@class='b-font']";
	public static String AnnualRental = "//section[@class='city-info']//li[2]/div[@class='b-font']";
	public static String CityDescription = "//section[@class='city-overview']";
	public static String readMoreDesc = "//div[@class='overview-desc clearfix']//a[contains(text(),'read more')]";
	public static String readLessDesc = "//div[@class='overview-desc clearfix']//a[contains(text(),'read less')]";
	public static String Description = "//div[@data-module='stripContent']";
	
	public static String Navigatorbar = "//div[@data-placeholder='navigation']";
	
	public static String ProjectsTab = "//div[@data-goto='sponsored']";
	public static String PriceTrendTab = "//div[@data-goto='cityMarketAnalysis']";
	public static String PropertyRangeTab = "//div[@data-goto='cityPropertyRange']";
	public static String PropertyinCity = "//div[@data-goto='propertiesInCity']";
	public static String PopularLocality = "//div[@data-goto='cityPopularLocalities']";
	public static String TopProjects = "//div[@data-goto='cityTopProjects']";
	
	public static String ProjectCard = "//section[@id='sponsored']//h2";
	public static String PriceTrendCard="//section[@id='cityMarketAnalysis']//h2";
	public static String PropertyRangeCard = "//section[@id='cityPropertyRange']//h2";
	public static String PropertyinCityCard ="//section[@id='propertiesInCity']//h2";
	public static String PopularLocalityCard = "//section[@id='cityPopularLocalities']//h2";
	public static String TopProjectsCard = "//section[@id='cityTopProjects']//h2";
	
	public static String SponsoredLeft = ".//div[@id='city_sponsored_project']//i[@class='icon-chevron-left']";
	public static String SponsoredRight = "//div[@id='city_sponsored_project']//i[@class='icon-chevron-right']";
	public static String SponsoredActive = ".//div[@class='slide slider-js']";
	public static String SponsoredActiveKnowMore = ".//div[@class='slide slider-js']//span[@class='know-more']";
	public static String SponsoredActiveProjectinfo = ".//div[@class='slide slider-js']//span[@class='proj-name']";
	public static String VerifyProject = ".//div[@class='type-col']/h1";
	
	public static String PriceGraph = "//div[@id='price-demand']";
	public static String Localities = "//div[contains(@class,'graph-details clearfix')]";
	public static String CityTopLocality = "(//a[@data-type='cityTopLocality'])[";
	public static String Localityheroimg = "//div[@class='heroimg']";
	
	public static String PropertyRangeBuyFilter = "(//div[@data-type='single-select-dropdown-head'])[1]";
	public static String SelectBuy = "(//li[@data-value='buy'])[1]";
	
	public static String PropertyTypeFilter = "//div[@data-type='propertyType']//div[@data-type='multi-select-dropdown']";
	public static String PropertyTypeApartment = "(//input[@value='apartment'])[1]";
	
	public static String ViewProperty = "//a[@data-type='cityViewProperty']";
	public static String SERPfilterbar = ".//div[@class='list-sidebar clearfix']";
	
	public static String PropertyInCityImages = "//ul[@class='type-properties']/li[";
	public static String PropertyInCityType1 = "//ul[@class='type-properties']/li[";
	public static String PropertyInCityType2 =	"]//li[@class='links-title']//span[@itemprop='name']";
	
	public static String LeadSection = "//section[@class='leadform']";
	public static String LeadForm = "//div[@data-type='openPyrForm']";
	
	public static String RentTab = "//li[@id='rent_tabs_head']";
	public static String BuyTab = "//li[@id='buy_tabs_head']";
	public static String BuyWindow = "//div[@id='locality_window_buy']";
	public static String RentWindow = "//div[@id='locality_window_rent']";
	
	public static String TopProjectswindow = "//div[@data-window='project_window']";


}
