package com.makaan.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.makaan.Middleware.Project_Middleware;


public class ProjectTest {

	Project_Middleware pm = new Project_Middleware();

	@BeforeClass
	public void InitiateDriver() {
		System.out.println("Inside Test Initiate Driver");
		

		try {
			String res = pm.OpenURL();
			if (res.contains("Pass")) {
				System.out.println("Login Button is present");
			} else {
				Assert.assertTrue(false, res);
			}
		}catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to find Makaan Logo on Page");
		} catch (TimeoutException t) {
			Assert.assertTrue(false, "not able to open page due to time out");
		} catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}

	
	@Test(priority =2)
	//@Test(enabled = false)
	public void VerifyProjectImage() throws InterruptedException {
		System.out.println("Inside Test Verify Project Image");
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
	
	@Test(priority =3)
	//@Test(enabled = false)
	public void VerifyHeader() throws  InterruptedException {
		System.out.println("Inside Test Verify Project Header");
		try {
			String res = pm.ValidateProjectHeader();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Verify Header on Page" +n);
		} 
	}
	@Test(priority =1)
	public void VerifyLead() throws InterruptedException{
		System.out.println("Inside Test Verify Lead");
		try {
			String res = pm.ConnectNow();
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
	@Test(priority =4)
	//@Test(enabled = false)
	public void VerifyNavigationBar() throws InterruptedException {
		System.out.println("Inside Test Verify Project Navigation Bar");
		try {
			String res = pm.ValidateNavigatorBar();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Verify Navigation Bar on Page "+n);
		}
	}
	
	@Test(priority =5)
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
	public void VerifySimilarProjects() throws InterruptedException {
		System.out.println("Inside Test Verify Similar Project");
		try {
			String res = pm.ValidateSimilarProject();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Verify Similar Projects on Page "+n);
		}catch (IOException i) {
			Assert.assertTrue(false, "not able to open excel sheet");
		}
	}
	@Test(priority =7)
	//@Test(enabled = false)
	public void VerifyAboutBuilder() throws InterruptedException, IOException {
		System.out.println("Inside Test Verify Builder Card");
		try {
			String res = pm.AboutBuilderCard();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Verify Builder Card on Page "+n);
		}
	}
	
	@Test(priority =8)
	//@Test(enabled = false)
	public void VerifyAboutLocality() throws InterruptedException, IOException {
		System.out.println("Inside Test Verify Locality Card");
		try {
			String res = pm.AboutLocalityCard();
			if (res.contains("Pass")) {
				System.out.println(res);
			} else {
				Assert.assertTrue(false, res);
			}
		} catch (NoSuchElementException n) {
			Assert.assertTrue(false, "not able to Verify Locality Card on Page "+n);
		}
	}
	@Test(priority =9)
	//@Test(enabled = false)
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
			Assert.assertTrue(false, "not able to Verify Locality Card on Page "+n);
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
