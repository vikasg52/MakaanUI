package com.makaan.Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.makaan.Middleware.PropertyPage_Middleware;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class PropertyPage_Test {

	PropertyPage_Middleware pm = new PropertyPage_Middleware();

	@BeforeClass
	public void InitiateDriver() throws NoSuchElementException {
		try {
			System.out.println("Inside Test Initiate Driver");

			pm.OpenURL();
			

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "not able to Initiate Driver due to exception");
		}

	}
	//@Test (enabled = false)
	@Test(priority = 1)
	public void ValidatepropertyImage() throws Exception {
		System.out.println("Inside Test Validate Property Image");
		try {
			String res = pm.ValidateProjectImage();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find Image on Project Page "+n);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		}

	}
	//@Test (enabled = false)
	@Test(priority = 2)
	public void ValidateInfo() throws Exception {
		System.out.println("Inside Test Validate Property Info");
		try {
			String res = pm.ValidateProjectHeader();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Validate Info on Project Page "+n);
		}
	}
	@Test(priority = 3)
	public void ValidateBar() throws Exception {
		System.out.println("Inside Test Validate Naviagtion Bar");
		try {
			String res = pm.ValidateNavigatorBar();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Validate Bar on Project Page "+n);
		}
	}

	@Test(priority = 4)
	public void ValidateLead() throws Exception {
		System.out.println("Inside Test Validate Lead");
		System.out.println("Inside Test Verify Lead");
		try {
			String res = pm.ValidateLead();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Verify Locality Card on Page "+n);
		}catch (SQLException s) {
			Assert.assertTrue(false, "not able to open DB"+ s);
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet" +i);
		}
	}
	@Test(priority =12)
	//@Test(enabled = false)
		public void VerifyOverview() throws InterruptedException {
			System.out.println("Inside Test Verify Project Overview");
			try {
				String res = pm.ValidateOverviewCard();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			} catch (NoSuchElementException n) {
				Assert.assertTrue(false, "not able to Verify Overview Card on Page "+n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to open page due to time out");
			} 
		}
	@Test(priority =6)
	//@Test(enabled = false)
		public void VerifyAmenityCard() throws InterruptedException {
			System.out.println("Inside Test Verify Property Amenities Card");
			try {
				String res = pm.ValidateAmenityCard();
				if (res.contains("Pass")) {
					System.out.println(res);
				} else {
					Assert.assertTrue(false, res);
				}
			} catch (NoSuchElementException n) {
				Assert.assertTrue(false, "not able to Verify Amenities Card on Page "+n);
			} catch (TimeoutException t) {
				Assert.assertTrue(false, "not able to open page due to time out");
			} 
		}
	
@Test(priority =7)
//@Test(enabled = false)
public void VerifyFloorPlan() throws InterruptedException {
	System.out.println("Inside Test Verify Property Amenities Card");
	try {
		String res = pm.ValidateFloorPlan();
		if (res.contains("Pass")) {
			System.out.println(res);
		} else {
			Assert.assertTrue(false, res);
		}
	} catch (NoSuchElementException n) {
		Assert.assertTrue(false, "not able to Verify FloorPlan as Floor plan was not present under tab floorplan"+n);
	} catch (TimeoutException t) {
		Assert.assertTrue(false, "not able to open page due to time out");
	} 
}
	
@Test(priority =8)
//@Test(enabled = false)
public void VerifySimilar() throws InterruptedException {
	System.out.println("Inside Test Verify similar Card");
	try {
		String res = pm.ValidateSimilarProperty();
		if (res.contains("Pass")) {
			System.out.println(res);
		} else {
			Assert.assertTrue(false, res);
		}
	} catch (NoSuchElementException n) {
		Assert.assertTrue(false, "not able to Verify Similar Property Card"+n);
	} catch (TimeoutException t) {
		Assert.assertTrue(false, "not able to open page due to time out");
	} catch (IOException i) {
		Assert.assertTrue(false, "not able to open excel sheet" +i);
	}
}
@Test(priority =9)
//@Test(enabled = false)
public void VerifyBuillder() throws InterruptedException {
	System.out.println("Inside Test Verify About Builder Card");
	try {
		String res = pm.AboutBuilderCard();
		if (res.contains("Pass")) {
			System.out.println(res);
		} else {
			Assert.assertTrue(false, res);
		}
	} catch (NoSuchElementException n) {
		Assert.assertTrue(false, "not able to Verify About Builder Card"+n);
	}catch (IOException i) {
		Assert.assertTrue(false, "not able to open excel sheet" +i);
	}
}
@Test(priority =10)
//@Test(enabled = false)
public void VerifyLocality() throws InterruptedException {
	System.out.println("Inside Test Verify About Locality Card");
	try {
		String res = pm.AboutLocalityCard();
		if (res.contains("Pass")) {
			System.out.println(res);
		} else {
			Assert.assertTrue(false, res);
		}
	} catch (NoSuchElementException n) {
		Assert.assertTrue(false, "not able to Verify About Locality Card"+n);
	}catch (IOException i) {
		Assert.assertTrue(false, "not able to open excel sheet" +i);
	}
}

@Test(priority =11)
//@Test(enabled = false)
public void VerifyContactSeller() throws InterruptedException, TimeoutException, SQLException {
	System.out.println("Inside Test Verify Contact Seller Card");
	try {
		String res = pm.ValidateContactSeller();
		if (res.contains("Pass")) {
			System.out.println(res);
		} else {
			Assert.assertTrue(false, res);
		}
	} catch (NoSuchElementException n) {
		Assert.assertTrue(false, "not able to Verify Contact Seller"+n);
	}catch (IOException i) {
		Assert.assertTrue(false, "not able to open excel sheet" +i);
	}
}


@Test(priority =5)
public void VerifyBreadCrumb() throws InterruptedException, IOException {
	System.out.println("Inside Test Verify Breadcrumb");
	try {
		String res = pm.BreadCrumb();
		if (res.contains("Pass")) {
			System.out.println(res);
		} else {
			Assert.assertTrue(false, res);
		}
	} catch (NoSuchElementException n) {
		Assert.assertTrue(false, "not able to Verify BreadCrumb on Page "+n);
	}
}
	
	@AfterClass
	public void Close() {
		try {
			pm.CloseAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
