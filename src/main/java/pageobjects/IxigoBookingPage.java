package pageobjects;

import java.util.List;

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
	 @FindBy(how=How.CSS,using="[class*='autocompleter-scroll-cntr']")
	 private List<WebElement> fromToList;
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'Departure')]//following-sibling::input")
	 private WebElement txtbxCalendarDept;
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'Return')]//following-sibling::input")
	 private WebElement txtbxCalendarReturn;
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'rd-month-label')])[2]")
	 private WebElement deptMonthLabel;
	 @FindBy(how=How.XPATH,using="(//button[contains(@class,'ixi-icon-arrow') and contains(@class,'rd-next')])[1]")
	 private WebElement arrowIcon;
	 @FindBy(how=How.XPATH,using="(//div[contains(@class,'u-ripple')])[1]")
	 private WebElement buttonSearch;
	 
	 
	 
	 public WebElement getFrom() {
		 return txtbxFrom;
	 }
	 public WebElement getTo() {
		 return txtbxTo;
	 }
	 public WebElement selectDate(String date) {
		 return driver.findElement(By.xpath("(//table[contains(@class,'rd-days')]//div[contains(text(),'"+date+"')])[2]"));
	 }
	public void enterFromToDetails(WebElement textBox,String place) {

		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(textBox));
		textBox.clear();
		textBox.sendKeys(place);
		for (WebElement option : fromToList)

		{
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
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(txtbxCalendarReturn));
		txtbxCalendarReturn.click();
		while(!deptMonthLabel.getText().contains(month))
		{
			arrowIcon.click();
		}

		selectDate(day).click();
	}
	public void clickSearch() {
		buttonSearch.click();
	}
}
