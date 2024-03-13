package com.practo.pageObjects;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HealthAndWellness extends BasePage{

	public HealthAndWellness(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//button[@class='mweb-hide sticky_side_bar text-alpha u-text--bold']")
	WebElement scheduleDemoNavButton;
	
	// Click the "Schedule A Demo" scroll navigation button
	public void clickScheduleDemoNavButton() {
		scheduleDemoButton.click();
	}

	@FindBy(id="name")
	WebElement nameInputSchedule;

	@FindBy(id="organizationName")
	WebElement orgInputSchedule;

	@FindBy(id="contactNumber")
	WebElement contactInputSchedule;

	@FindBy(id="officialEmailId")
	WebElement emailInputSchedule;

	@FindBy(id="organizationSize")
	WebElement orgSizeSelectSchedule;

	@FindBy(id="interestedIn")
	WebElement interestedInSelectSchedule;
	
	@FindBy(xpath="//button[text()='Schedule a demo']")
	public WebElement scheduleDemoButton;

	// Input name
	public void fillName(String name) {
		nameInputSchedule.sendKeys(name);
	}
	// Input Organization name
	public void fillOrganization(String orgName) {
		orgInputSchedule.sendKeys(orgName);
	}
	// Input Contact Information
	public void fillContact(String contact) {
		contactInputSchedule.sendKeys(contact);
	}
	// Input Email
	public void fillEmail(String email) {
		emailInputSchedule.clear();
		emailInputSchedule.sendKeys(email);
	}
	
	// Select organization from dropdown
	public void selectOrgSize(String orgSize) {
		Select orgSizeSelect = new Select(orgSizeSelectSchedule);
		orgSizeSelect.selectByVisibleText(orgSize);
	}

	// Select Interest from dropdown
	public void selectInterest(String interestedIn) {
		Select interestedInSelect = new Select(interestedInSelectSchedule);
		interestedInSelect.selectByVisibleText(interestedIn);
	}
	
	// Check and return the status of the Schedule a Demo button
	public Boolean isScheduleDemoButtonEnabled() {
		return scheduleDemoButton.isEnabled();
	}
	
	// Click the Schedule a Demo button
	public void submitForm() {
		scheduleDemoButton.click();
	}
	
	@FindBy(xpath="/html/body/div[3]/div/div/div/div[1]")
	WebElement thankYouMessage;
	
	// Fetch Thank You message after form submission
	public String fetchThankYouMessage() {
		if (thankYouMessage.isDisplayed() == false) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOf(thankYouMessage));
		}
		try {
			return thankYouMessage.getText();
		} catch (NoSuchElementException e) {
			try {
				/*
				 * Sometimes the user is required to fill the captcha
				 * more than two times. This is emergency extra time.
				 * */
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return thankYouMessage.getText();
		}
	}

}
