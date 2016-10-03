package com.makaan.Dictionary;

public class PropertyPage {

	public static String TringCallNow = "//div[@class='call-screen-content']";

	public static String MakaanLogo = "//a[@title='get your home at makaan']";

	public static String ProjectImage = "(//div[@class='galinlinewrap ']//img)[1]";
	public static String Leftbuuton = ".//div[@data-type='left-click']";
	public static String galleryTotalCount = ".//span[@class='lg-counter-all']";
	public static String imagegallery = "(//div[@class='lg-img-wrap'])[1]//img";
	public static String galleryNext = ".//div[@data-type='right-click']";
	public static String ProjectName = "//div[@class='type-col']//h1";
	public static String galleryClose = ".//span[@class='lg-close mobile-close lg-icon']";
	public static String DefaultImage = "//div[@data-track-scroll='1']//img[@data-src='https://content.makaan.com/17/0/569/2939994.png?width=360&height=270']";

	public static String ProjectHeader = ".//div[@class='top-hdng clearfix']";
	public static String Shortlist = "(//i[@class='icon-makaan'])[2]";
	
	public static String Navigatorbar = ".//div[@data-type='navigation-container']";
	public static String Property_Type = ".//div[@class='imp-data-wrap']//span[@itemprop='name']";

	public static String Property_Size = ".//div[@class='imp-data-wrap']//span[@class='size']";

	public static String ShortlistIcon = ".//i[@class='icon-makaan']";

	public static String Shortlisted = ".//i[@class='icon-makaanfill']";

	public static String EMIDetails = ".//div[@class='emi-wrap emi']/div";

	public static String PriceDetails = ".//div[@class='price-wrap']//meta[1]";

	public static String TabOverview = "//div[@data-goto='overview']//span[@class='triangle']";

	//public static String TabAmenity = "//div[@data-goto='amenity']";
	public static String FloorPlanLink = "//div[@data-goto='floorplan']//span[@class='triangle']";

	public static String SpecificationInTabFloorPlan = "//div[@id='specification']";

	public static String TabSimilar = "//div[@class='similar-pane']";

	public static String TabProject = "//div[@data-goto='project']";

	public static String TabLocality = "//div[@data-goto='locality']";

	public static String OverviewCard = ".//div[@id='overview']//h2";
	public static String AmenityLink="//div[@data-goto='amenity']//span[@class='triangle']";

	public static String AmenityCard = "//div[@class='amenities-wrap js-list-wrap']//h2";

	public static String FloorPlanCard = ".//div[@id='specification']//h2";
	
	public static String FloorPlanIMG = "//div[@id='floor-plan']//img";

	public static String Similar_property = "//div[@class='similar-pane']//h2";
	
	public static String SimilarpropertyWindow = "//div[@class='similar-pane']";
	//(//div[@class='propCard'])[2]//img
	public static String PropertyCardImage = "//li[@itemprop='itemListElement'][";
	//public static String PropertyNextImage = "//div[@id='slider_window']//div[@data-type='next-btn']/i";
	//public static String PropertyPreviousImage = "//div[@id='slider_window']//div[@data-type='back-btn']/i";
	//public static String PropertyNextDisable = "//div[@id='slider_window']//div[@class='btn-next hide1024nbelow js-next disable']";
	//public static String PropertyPreviousDisable = "//div[@id='slider_window']//div[@class='btn-prev hide1024nbelow js-prev disable']";

	public static String Locality = "//div[@class='sbcol-card locality-wrap']//h2";
	
	public static String ProjectScore = "(//div[@class='score-wrap'])[1]";
	public static String BuilderCard =  "(//div[@id ='builder'])//h2";
	public static String BuilderDescription = "//div[@class='txt-desc js-desk']";
	
	public static String Builderfacts = "//ul[@class='bfacts-list clearfix']";
	public static String OnGoingProject = "(//div[@class='val builder-link'])[1]";
	public static String PastProject = "(//div[@class='val builder-link'])[2]";
	public static String LocalityScore = "(//div[@class='score-wrap'])[2]";

	public static String knowmoreProject =  "(//div[@id='project']//div[@class='know-more'])[1]";
	public static String moreProject = "(//div[@id='project']//div[@class='know-more'])[1]";
	public static String LocalityScoreclose = "//div[@id='localityScorePopup']//span[contains(text(),'Got it')]";
	public static String LocalityDesk  ="//div[@class='sbcol-card locality-wrap']";
	public static String knowmoreLocality  = "//div[@class='sbcol-card locality-wrap']//a[@class='more-link']";
	public static String CityHeroShot = "//section[@class='city-cover']";
	public static String CityHeroshot = "//div[@class='heroimg']";
	
	public static String LocalityNeighbourHood = "//div[@class='neighborhood-wrap']";
	public static String NeighbourHoodTabs = "//div[@class='neighbourhood-places']//div[@data-module='tabs']";
public static String MasterPlanTabs = "//div[@class='svgoptions']//span[@data-type='optionClicked']";
	
	public static String MapHeader = "//div[@class='svg-header']";
	
	public static String NearByHeader = "//div[@class='listhdng']";
	
	public static String NearByTabs = "//div[@class='mod-content nearbyamenity-tabs']/span";
	public static String HomeMainImage = ".//div[@class='poster-image']";
	public static String SERPfilterbar = ".//div[@class='list-sidebar clearfix']";
	public static String BreadcrumbCard  =  "//div[@class='breadcrumb-wrap']";
	public static String BreadcrumbList = "//div[@class='breadcrumb-list']";
	public static String BreadcrumbSERP = "//a[@data-track-label='City']";
	public static String BreadcrumbHome = "//a[@data-track-label='Home']";
	public static String BreadcrumbCity = "(//span[@class='bclink'])[2]";
	public static String BreadcrumbProject = "(//span[@class='bclink'])[4]";
	
	public static String ContactSellerWrap = "//div[@class='other-sellers-wrap']";
	public static String ContactSellerHead = "//div[@class='g-head']";
	public static String ContactSellerBody = "//div[@class='g-body']";
	public static String ContactCallBtn = "//a[@data-type='callBtn']";
	public static String ContactSellerLead = "//a[contains(text(),'get in touch')]";
	public static String ContactSellerLeadForm = "//div[@class='leadform-wrap clearfix']";
	public static String InputName = "//input[@name='client-name']";
	public static String InputMail = "//input[@name='client-email']";
	public static String InputPhone ="(//input[@name='client-phone-num'])[2]";
	public static String SubmitLead= "//input[@data-type='submit-lead']";
	public static String Thankyou= "//div[@class='thank-you-wrap']";
	public static String ThankyouContinue= "//div[@class='thank-you-continue']";
	
	public static String SellerNumber = "//a[@class='link-call lc-desktop js-phonelink active']";
	
public static String OverviewReadMore = ".//a[@data-type='show-btn']";
	
	public static String OverviewDescription = ".//span[@itemprop='description']";
	
	public static String KeyDetails = "//div[@class='exco kd-list js-list']";
	
	public static String KeyDetailsValue = "((//div[@id='overview']//div[@data-type='list'])[1]//div[@class='listitem'])";
	
	public static String AmenityItems = "//div[@class='exco icons-list js-list js-mobscroll active']";
	public static String ViewMoreAmenity =  "//a[@data-track-label='Amenities']/span[@class='get-more-txt']";
	
	public static String ViewLessAmenity = "//a[@data-track-label='Amenities']/span[@class='get-less-txt']";
	public static String SpecificationCard ="//div[@class='specifictions-wrap js-list-wrap']";
	
	public static String SpecificationItem = "//div[@class='exco spec-type-list js-list active']";
	public static String ViewMoreSpecification =  "//a[@data-track-label='Specifications']/span[@class='get-more-txt']";
	public static String ViewLessSpecification =  "//a[@data-track-label='Specifications']/span[@class='get-more-txt']";
	
	
	public static String LeadDropDown  = "(//div[@data-type='single-select-dropdown'])[1]//i";
	public static String MobileNumberInput = "//div[@data-type='MAIN_PHONE']//input";
	public static String CallNowSubmit = "//a[@data-type='MAIN_SUBMIT']";
	public static String Error = ".//span[@class='show-error']";
	public static String OTP_Screen  = ".//div[@class='otp-image clearfix']";
	
	public static String OTP_input  = ".//div[@data-type='OTP_OTP']//input";
	
	public static String Emailinput  =".//div[@data-type='OTP_EMAIL']//input";
	
	public static String Nameinput  =".//div[@data-type='OTP_NAME']//input";
	
	public static String SubmitOTP  = ".//a[@data-type='OTP_SUBMIT']";
	
	public static String InvalidOTP  = ".//div[@data-message='OTP']";
	//public static String TringCallNow= ".//div[@class='tring-pane']/div[1]";
	
	public static String ThanksSubmit = "//a[@data-type='SIMILAR_GOTIT']";
	//public static String Thank = ".//div[@class='lead-heading clearfix']//following-sibling::div[@class='le-content']";
	public static String Skip = ".//a[@data-type='SIMILAR_SKIP']";
}
