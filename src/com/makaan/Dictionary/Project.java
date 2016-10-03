package com.makaan.Dictionary;

public class Project {

	public static String MakaanLogo = "//a[@title='get your home at makaan']";
	public static String ProjectImage = "(//div[@class='galinlinewrap ']//img)[1]";
	public static String Leftbuuton = ".//div[@data-type='left-click']";
	public static String galleryTotalCount = ".//span[@class='lg-counter-all']";
	public static String imagegallery = "(//div[@class='lg-img-wrap'])[1]//img"; 
public static String galleryNext = ".//div[@data-type='right-click']";
public static String ProjectName =  "//div[@class='type-col']//h1";
	public static String galleryClose = ".//span[@class='lg-close mobile-close lg-icon']";
	public static String DefaultImage ="//div[@data-track-scroll='1']//img[@data-src='https://content.makaan.com/17/0/569/2939994.png?width=360&height=270']";
	
	public static String ProjectHeader = ".//div[@class='top-hdng clearfix']";
	public static String Shortlist = "//div[@class='top-hdng clearfix']//i[@class='icon-makaan']";
	public static String Shortlisted =  "//div[@class='top-hdng clearfix']//i[@class='icon-makaanfill']";
public static String Navigatorbar = ".//div[@data-type='navigation-container']";

	public static String Configuration = "//div[@data-goto='configuration']";
	
	public static String TabOverview = "//div[@data-track-label='Overview']//span[@class='triangle']";	
	
	public static String SimilarProject = "//div[@data-goto='similar_slider_window']";
	
	public static String Locality = "//div[@data-goto='locality']";
			
	public static String Builder =	"//div[@data-goto='builder']";
	
	public static String TabConfiguration =  "//div[@id ='bucket-configuration']/h2";
	
	public static String OverviewCard = "(//div[@id='overview']//h2)[1]";
	
	public static String BuilderCard =  "(//div[@id ='builder'])//h2";
	
	public static String LocalityCard = "(//div[@class='score-wrap'])[2]";
		
	public static String TabSimilar = "//div[@data-goto='similar-property']";
	
	public static String TabProject = "//div[@data-goto='project']";
	
	public static String SimilarProjectCard = ".//div[@id='similar-property']//h2";
	
	public static String OverviewReadMore = ".//a[@data-type='show-btn']";
	
	public static String OverviewDescription = ".//span[@itemprop='description']";
	
	public static String KeyDetails = "//div[@class='exco kd-list js-list']";
	
	public static String KeyDetailsValue = "((//div[@id='overview']//div[@data-type='list'])[1]//div[@class='listitem'])";
	
	public static String AmenityCard = "//div[@class='amenities-wrap js-list-wrap']";
	public static String AmenityItems = "//div[@class='exco icons-list js-list js-mobscroll active']";
	public static String ViewMoreAmenity =  "//a[@data-track-label='Amenities']/span[@class='get-more-txt']";
	
	public static String ViewLessAmenity = "//a[@data-track-label='Amenities']/span[@class='get-less-txt']";
	public static String SpecificationCard ="//div[@class='specifictions-wrap js-list-wrap']";
	
	public static String SpecificationItem = "//div[@class='exco spec-type-list js-list active']";
	public static String ViewMoreSpecification =  "//a[@data-track-label='Specifications']/span[@class='get-more-txt']";
	public static String ViewLessSpecification =  "//a[@data-track-label='Specifications']/span[@class='get-more-txt']";
	
	public static String PriceTrendNoData = "//div[@class='highcharts-no-data']/span[contains(text(),'No Data Available')]";
	
	public static String PriceTrendCard= "//div[@class='price-trends-wrap']//h2";
	
	public static String PreapprovedLoan = "//div[@id='homeloan']/h2";
	
	public static String PreapprovedBanks= "//div[@class='banklogo-wrap']";
	
	public static String ProjectCardImage = "(//div[@class='propCard'])[";
	public static String ProjectNextImage = "//div[@id='similar_slider_window']//div[@data-type='next-btn']/i";
	public static String ProjectPreviousImage = "//div[@id='similar_slider_window']//div[@data-type='back-btn']/i";
	public static String ProjectNextDisable = "//div[@id='similar_slider_window']//div[@class='btn-next hide1024nbelow js-next disable']";
	public static String ProjectPreviousDisable = "//div[@id='similar_slider_window']//div[@class='btn-prev hide1024nbelow js-prev disable']";
	
	public static String BuilderDescription = "//div[@class='bdesc-col clearfix']";
	
	public static String Builderfacts = "//ul[@class='bfacts-list clearfix']";
	public static String PastProject = "(//div[@class='val builder-link'])[1]";
	public static String OnGoingProject = "(//div[@class='val builder-link'])[2]";
	
	public static String knowmoreProject =  "//div[@id='builder']//div[@class='know-more']";
	public static String LocalityScoreclose = "//div[@id='localityScorePopup']//span[contains(text(),'Got it')]";
	public static String LocalityDesk  ="//div[@id='locality']//span[@class='js-desk']";
	public static String knowmoreLocality  = "//div[@id='locality']//div[@class='know-more']/a";
	
	public static String BreadcrumbCard  =  "//div[@data-module='breadcrumb']";
	public static String BreadcrumbList = "//div[@class='breadcrumb-list']";
	public static String BreadcrumbSERP = "//a[@data-track-label='City']";
	public static String BreadcrumbHome = "//a[@data-track-label='Home']";
	public static String BreadcrumbCity = "(//span[@class='bclink'])[2]";
	
	public static String HomeMainImage = ".//div[@class='poster-image']";
	public static String SERPfilterbar = ".//div[@class='lsfix clearfix']";
	
	public static String CityHeroShot = "//div[@class='heroimg']";
	
	public static String CityHeroshot = ".//div[@id='city-overview']";
	
	public static String LocalityNeighbourHood = "//div[@class='neighborhood-wrap']";
	public static String NeighbourHoodTabs = "//div[@class='neighbourhood-places']//div[@data-module='tabs']";
	
	public static String MasterPlanTabs = "//div[@class='svgoptions']//span[@data-type='optionClicked']";
	
	public static String MapHeader = "//div[@class='svg-header']";
	
	public static String NearByHeader = "//div[@class='listhdng']";
	
	public static String NearByTabs = "//div[@class='mod-content nearbyamenity-tabs']/span";
	public static String ConnectNowButton = "(//a[contains(text() , 'connect now') and @data-type='openLeadForm'])[1]";
	public static String LeadDropDown  = "(//div[@data-type='single-select-dropdown'])[1]//i";
	public static String MobileNumberInput = "//div[@data-type='MAIN_TOP_SELLER_PHONE']//input";
	public static String CallNowSubmit = "//a[@data-type='MAIN_TOP_SELLER_SUBMIT']";
	public static String Error = ".//span[@class='show-error']";
	public static String OTP_Screen  = ".//div[@class='otp-image clearfix']";
	
	public static String OTP_input  = ".//div[@data-type='OTP_OTP']//input";
	
	public static String Emailinput  =".//div[@data-type='OTP_EMAIL']//input";
	
	public static String Nameinput  =".//div[@data-type='OTP_NAME']//input";
	
	public static String SubmitOTP  = ".//a[@data-type='OTP_SUBMIT']";
	
	public static String InvalidOTP  = ".//div[@data-message='OTP']";
	public static String TringCallNow= ".//div[@class='tring-pane']/div[1]";
	
	public static String ThanksSubmit = "//a[@data-type='SIMILAR_GOTIT']";
	public static String Thank = ".//div[@class='lead-heading clearfix']//following-sibling::div[@class='le-content']";
	public static String Skip = ".//a[@data-type='SIMILAR_SKIP']";
	
	
}
