package com.makaan.Dictionary;

public class BuyerDashboard {

	public static String MakaanLogo = "(//img[starts-with(@src,'data:image/png')])[1]";
	
	public static String BuyerJourney = ".//div[@class='profile-pic']//a";
	
	public static String MyjourneyTab = ".//div[@data-controller='buyerDashboardPage']//a[contains(text(),'journey')]";
	
	public static String PartnerTab = ".//div[@data-controller='buyerDashboardPage']//a[contains(text(),'partner')]";
	
	public static String SavedSearchTab =  ".//a[@data-track-action ='CLICK_Savedsearches']";
	
	public static String FindSearchCard = ".//div[@class='saved-search']/div";
	
	public static String AlliancesCard = ".//div[@class='propCard']//a[contains(text(),'book')][1]";
	
	public static String CityPopup = ".//div[@class='city-popup']";
	
	public static String MakaanIQ = ".//img[@title='makaan iQ']";
	
	public static String Cards = ".//a[@data-type='track-article']";
}
