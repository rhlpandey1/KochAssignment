package xpheno.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.IxigoBookingPage;
import xpheno.constants.Constants;
import xpheno.driverconfig.Base;
import xpheno.utils.Utilities;

public class DemoTest extends Base {
	public static Logger log=LogManager.getLogger(DemoTest.class.getName());
	@BeforeTest
	public void initialize() throws IOException
	{
		driver=initializeDriver();
		//driver.get(Utilities.getProperty("url"));
	}
	
	@Test(priority=1)
	public void launchApplicationAndValidate() throws IOException, InterruptedException
	{
		
		driver.get(Utilities.getProperty("url"));
		log.info("Application launched");
		driver.manage().window().maximize();
		log.info("Title is :->"+driver.getTitle());
		assertEquals(Constants.TITLE, driver.getTitle());
		log.info("Current Url is :->"+driver.getCurrentUrl());
		assertEquals(Constants.CURRENT_URL, driver.getCurrentUrl());
	}
	@Test(priority=2)
	public void booking() {
		IxigoBookingPage ixigoBookingPage=new IxigoBookingPage(driver);
		ixigoBookingPage.enterFromToDetails(ixigoBookingPage.getFrom(),Constants.FROM_PLACE);
		ixigoBookingPage.enterFromToDetails(ixigoBookingPage.getTo(),Constants.TO_PLACE);
		ixigoBookingPage.selectDeptDate("17", "November");
		//ixigoBookingPage.selectReturnDate("24", "December");
		ixigoBookingPage.clickSearch();
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
}
