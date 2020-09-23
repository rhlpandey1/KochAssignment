package pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.List;

import javax.lang.model.element.Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IxigoBookingPage {
	public static Logger log=LogManager.getLogger(IxigoBookingPage.class.getName());
	public WebDriver driver;
	//Constructor(default)
    public IxigoBookingPage() {
    }
    //Constructor(parameterized)
	 public IxigoBookingPage(WebDriver driver) {
		 this.driver=driver;
	     PageFactory.initElements(driver, this);
	 }
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'flight-form')]//input)[1]")
	 private WebElement txtbxFrom;
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'flight-form')]//input)[2]")
	 private WebElement txtbxTo;
	 @FindBy(how=How.CSS,using="[class*='autocompleter-scroll-cntr']>div")
	 private List<WebElement> fromToList;
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'Departure')]//following-sibling::input")
	 private WebElement txtbxCalendarDept;
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'Return')]//following-sibling::input")
	 private WebElement txtbxCalendarReturn;
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'rd-month-label')])[2]")
	 private WebElement deptMonthLabel;
	 @FindBy(how=How.XPATH,using="(//button[contains(@class,'ixi-icon-arrow') and contains(@class,'rd-next')])[1]")
	 private WebElement arrowIcon;
	 @FindBy(how=How.XPATH,using="(//button[contains(@class,'ixi-icon-arrow') and contains(@class,'rd-next')])[1]")
	 private WebElement arrowIconRet;
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'u-ripple')])[1]")
	 private WebElement buttonSearch;
	 
	 @FindBy(how=How.XPATH,using="//span[contains(text(),'Round Trip')]")
	 private WebElement lblRoundTrip;
	 
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'rd-month-label')])[4]")
	 private WebElement returnMonthLabel;
	 
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'ixi-icon-cross')])[1]")
	 private WebElement clearFromDate;
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'ixi-icon-cross')])[2]")
	 private WebElement clearToDate;
	 @FindBy(how=How.CSS,using="[class*='passanger-class-input']>div>input")
	 private WebElement inputTraveller;
	 @FindBy(how=How.CSS,using="[class^='close-btn'][class$='ixi-icon-cross']")
	 private WebElement iconCloseTraveller;
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'Non stop')]")
	 private WebElement filterNonStop;
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'1 stop')]/../../span[contains(@class,'checkbox-button')]")
	 private WebElement filterOneStop;
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'1+ stops')]")
	 private WebElement filterMoreThanOneStop;
	 @FindBy(how=How.XPATH,using="(//div[contains(text(),'Early Morning')]/../button)[1]")
	 private WebElement filterDepartureEarlyMorning;
	 @FindBy(how=How.XPATH,using="(//div[contains(text(),'Mid Day')]/../button)[1]")
	 private WebElement filterDepartureMidDay;
	 @FindBy(how=How.XPATH,using="(//div[contains(text(),'Night')]/../button)[1]")
	 private WebElement filterDepartureNight;
	 
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'Airlines')]")
	 private WebElement filterAirlines;
	 @FindBy(how=How.XPATH,using="//div[contains(@class,'price-group')]//div[contains(@class,'c-price-display')]/span[2]")
	 private List<WebElement> priceList;
	 @FindBy(how=How.XPATH,using="//div[contains(@class,'time-group')]/div[1][contains(@class,'time')]")
	 private WebElement deptDate;
	 @FindBy(how=How.XPATH,using="//div[contains(@class,'airline-text')]/div[1][contains(@class,'u-text-ellipsis')]")
	 private WebElement airLinesName;
	
	 public WebElement getFrom() {
		 return txtbxFrom;
	 }
	 public WebElement getTo() {
		 return txtbxTo;
	 }
	 public WebElement selectDate(String date) {
		 return driver.findElement(By.xpath("(//table[contains(@class,'rd-days')]//div[contains(text(),'"+date+"')])[2]"));
	 }
	 public WebElement selectReturnDate(String date) {
		 return driver.findElement(By.xpath("(//table[contains(@class,'rd-days')]//div[contains(text(),'"+date+"')])[4]"));
	 }
	 public WebElement selectTravellersCount(int adult) {
		 return driver.findElement(By.xpath("(//div[contains(text(),'Adult')]//following::span[contains(@data-val,'"+adult+"')])[1]"));
	 }
	 
	 public WebElement clearFrom() {
		 return clearFromDate;
	 }
	 public WebElement clearTo() {
		 return clearToDate;
	 }
	public void enterFromToDetails(WebElement clear,WebElement textBox,String place) throws InterruptedException {

		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(textBox));
		textBox.click();
		clear.click();
		textBox.sendKeys(place);
		Thread.sleep(3000);
		for (WebElement option : fromToList)

		{
			int size=fromToList.size();
			if (option.getText().contains(place))

			{

				option.click();

				break;

			}

		}

	}
	public void selectDeptDate(String day,String month) {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(txtbxCalendarDept));
		txtbxCalendarDept.click();
		while(!deptMonthLabel.getText().contains(month))
		{
			arrowIcon.click();
		}

		selectDate(day).click();
	}
	public void selectReturnDate(String day,String month) {
		clickRoundTrip();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(txtbxCalendarReturn));
		txtbxCalendarReturn.click();
		while(!returnMonthLabel.getText().contains(month))
		{
			arrowIconRet.click();
		}

		selectReturnDate(day).click();
	}
	
	public void clickRoundTrip() {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(lblRoundTrip));
		lblRoundTrip.click();
	}
	public void selectTravellers(int count) {
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(inputTraveller));
		inputTraveller.click();
		selectTravellersCount(count).click();
		wait.until(ExpectedConditions.elementToBeClickable(iconCloseTraveller));
		iconCloseTraveller.click();
	}
	public void clickSearch() {
		buttonSearch.click();
	}
	
	public void validateFilters() {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(filterNonStop));
		assertTrue(filterNonStop.isDisplayed());
		assertTrue(filterOneStop.isDisplayed());
		assertTrue(filterMoreThanOneStop.isDisplayed());
		assertTrue(filterDepartureEarlyMorning.isDisplayed());
		assertTrue(filterDepartureMidDay.isDisplayed());
		assertTrue(filterDepartureNight.isDisplayed());
		assertTrue(filterAirlines.isDisplayed());
	}
	public void printAirlineDetails(int fare) {
		for(WebElement ele:priceList) {
			if(Integer.parseInt(ele.getText())<fare) {
				System.out.println(deptDate.getText());
				System.out.println(airLinesName.getText());
			}
		}
	}
}
