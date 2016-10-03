package com.makaan.Dictionary;

public class ConnectNow {

		public static String MakaanLogo = "//a[@title='get your home at makaan']";
		
		public static String ConnectNowButton = "(//a[contains(text() , 'connect now') and @data-type='openLeadForm'])[1]";
		
		public static String ViewPhoneButton = "(//a[contains(text() , 'view phone') and @data-type='openLeadFormViewPhone'])[1]";
		
		public static String MobileNumberInput = ".//div[@data-type='MAIN_PHONE']//input";
		
		public static String Error = ".//span[@class='show-error']";
		
		public static String TringCallNow= "//div[@class='current-call-details']";
		
		public static String Skip = ".//a[@data-type='SIMILAR_SKIP']";
		
		public static String Thank = ".//div[@class='lead-heading clearfix']//following-sibling::div[@class='le-content']";
		
		public static String ThanksSubmit = "//a[contains(text(),'got it')]";
		//(//a[contains(text() , 'got it') and @data-type='THANKS_SUMIT'])
		
		public static String CallNowSubmit = ".//a[@data-type='MAIN_SUBMIT']";
		
		public static String ViewPhone = ".//a[@data-type='MAIN_VIEW_PHONE']";
		
		public static String SellerDetails = ".//div[@class='seller-dtls']";
		
		public static String PhoneText = ".//div[@class='see-phoneno']";
		
		public static String PhoneSubmit = ".//a[@data-type='VIEW_PHONE_SUMIT']";
		
		public static String ShareMyNumber = ".//a[@data-type='SIMILAR_SUBMIT']";
		
		public static String ContactSellerCard = ".//div[@data-module='topSellerPyr']";
		
		public static String PreviousContactSellerCard = ".//div[@data-track-scroll='3']";
		
		public static String TitleContactSeller = ".//div[@data-module='topSellerPyr']//div[@class='title']";
		
		public static String InputPhone = ".//div[@data-type='first_phone']//input" ;
		
		public static String ThankContactSeller = ".//div[@data-module='topSellerPyr']//div[@class='thank-pane']";
		
		public static String SubmitContactSeller  = ".//div[@data-type='first_submit']";
		public static String ErrorSubmit  =  ".//div[@data-type='first_submit']/span[@class='show-error']";
		
		public static String IndiaDropDown  = ".//div[@id='leadPopup']//li[contains(text(),'India')]";
		
		public static String LeadDropDown  = "//div[@class='leadenquiry-container step1 clearfix']";
		
		public static String OTP_Screen  = ".//div[@class='otp-image clearfix']";
		
		public static String OTP_input  = ".//div[@data-type='OTP_OTP']//input";
		
		public static String Emailinput  =".//div[@data-type='OTP_EMAIL']//input";
		
		public static String Nameinput  =".//div[@data-type='OTP_NAME']//input";
		
		public static String SubmitOTP  = ".//a[@data-type='OTP_SUBMIT']";
		
		public static String InvalidOTP  = ".//div[@data-message='OTP']";
		
		public static String resendOTP  = ".//a[contains(text(),'resend')]";
		
		public static String resendOTPMessage  = ".//span[@class='show-error']/span";
		
		public static String VerifyText  = "//div[contains(text(),'verify your number')]";
		
		public static String VerifyViewPhone  =  "//a[@data-type='VIEW_PHONE']";
		
		
		
		
		
	}



