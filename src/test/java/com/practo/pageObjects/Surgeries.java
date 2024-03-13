package com.practo.pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.practo.utils.ExcelUtils;

public class Surgeries extends BasePage {

	public Surgeries(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@data-qa-id='city-selector-container']")
	WebElement cityDropdown;
	
	@FindBy(xpath="//div[@data-qa-id='city-name']/h1[text()='Bangalore']")
	WebElement cityBangalore;
	
	@FindBy(xpath="//*[@id=\"__next\"]/main/div/div[7]/div/div/div[2]/div/div[2]/div/div/div[6]/div[1]/h1")
	WebElement cityBangaloreSurgeries;
	
	@FindBy(xpath="//div[@data-qa-id='city-name']/h1[text()='Chennai']")
	WebElement cityChennai;

	@FindBy(xpath="//div[@data-qa-id='ailment-selector-container']")
	WebElement ailmentDropdown;
	
	@FindBy(xpath="//div[contains(@class, 'text-12px')]/h1[text()='Carpal Tunnel Syndrome']")
	WebElement ailmentCarpelTunnel;

	@FindBy(xpath="//div[contains(@class, 'text-12px')]/h1[text()='ACL Repair']")
	WebElement ailmentACLRepair;
	
	@FindBy(id="Name-Gen-Lead-Form")
	WebElement nameInput;
	
	@FindBy(id="Phone-Gen-Lead-Form")
	WebElement contactNumberInput;
	
	@FindBy(xpath="//button[@data-qa-id='book-appointment-cta']")
	WebElement bookAppointmentButton;
	
	@FindBy(className="mobile-text-preview")
	WebElement mobileNumberVerification;
	
	@FindBy(xpath="//iframe[@data-qa-id='otp-modal-iframe']")
	WebElement frame;
	
	@FindBy(xpath="//div[text()='Invalid User Name']")
	WebElement errorMessageName;

	@FindBy(xpath="//div[text()='Invalid Phone Number']")
	WebElement errorMessageContact;
	
	@FindBy(xpath="//p[text()='Cataract']/parent::div")
	WebElement surgeryNameCataract;

	@FindBy(xpath="//p[text()='Kidney Stone']/parent::div")
	WebElement surgeryNameKidneyStone;
	
	@FindBy(xpath="//p[contains(text(), 'Book an appointment for')]")
	WebElement surgeriesFormMessage;
	
	@FindBy(id="Name-AIlment-Lead-Form")
	WebElement surgeriesFormNameInput;
	
	@FindBy(id="Phone-AIlment-Lead-Form")
	WebElement surgeriesFormPhoneInput;
	
	@FindBy(xpath="//span[text()='City*']/parent::div")
	WebElement surgeriesFormCityDropdown;
	
	@FindBy(xpath="//button[text()='Book Appointment' and @class='ailmentLeadForm-module_submit-cta__oKug8']")
	WebElement surgeriesBookAppointmentButton;
	
	@FindBy(xpath="//div[text()='Invalid User Name']")
	WebElement surgeriesErrorMessageName;
	
	@FindBy(xpath="//div[text()='Invalid Phone Number']")
	WebElement surgeriesErrorMessageContact;
	
	@FindBy(id="mobile_token")
	WebElement surgeriesOtpInput;
	
	@FindBy(xpath="//p[@data-qa-id='surgical-solution-ailment-name']")
	List<WebElement> popularSurgeries;
	
	// Output surgeries listed under popular surgeries
	public void outputPopularSurgeries() {
		try {
			ExcelUtils.write("Surgeries", 0, 0, "Surgery Name");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < popularSurgeries.size(); i++) {
			System.out.println(popularSurgeries.get(i).getText());
			try {
				ExcelUtils.write("Surgeries", i+1 , 0, popularSurgeries.get(i).getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Total Count of Popular Surgeries: " + popularSurgeries.size());
	}
	
	// Select city from dropdown
	public void selectCityFromDropdown(String city) {
		cityDropdown.click();
		if (city.equals("Bangalore")) {
			cityBangalore.click();
		}
		else {
			cityChennai.click();
		}
	}
	
	// Select ailment from dropdown
	public void selectAilmentFromDropdown(String ailment) {
		ailmentDropdown.click();
		if (ailment.equals("Carpal Tunnel Syndrome")) {
			ailmentCarpelTunnel.click();
		}
		else {
			ailmentACLRepair.click();
		}
	}
	
	// Input name
	public void nameInputSurgeriesForm(String name) {
		nameInput.sendKeys(name);
	}
	
	// Input contact information
	public void contactInputSurgeriesForm(String contact) {
		contactNumberInput.sendKeys(contact);
	}
	
	// Click book appointment button
	public void bookAppointment() {
		bookAppointmentButton.click();
	}
	
	// Verify mobile number from the OTP pop-up
	public String verifyMobileNumber() {
		driver.switchTo().frame(frame);
		return mobileNumberVerification.getText();
	}
	
	// read error message displayed under name
	public String getNameErrorMessage() {
		return errorMessageName.getText();
	}
	
	// read error message displayed under contact
	public String getContactErrorMessage() {
		return errorMessageContact.getText();
	}
	
	// click surgery name asked for
	public void clickSurgery(String surgeryName) {
		if (surgeryName.equals("Cataract")) {
			surgeryNameCataract.click();
		}
		else {
			surgeryNameKidneyStone.click();
		}
	}

	// return form opening message
	public String returnSurgeriesFormMessage() {
		return surgeriesFormMessage.getText();
	}
	
	// Input name
	public void fillSurgeryName(String name) {
		surgeriesFormNameInput.sendKeys(name);
	}

	// Input contact information
	public void fillSurgeryContact(String contact) {
		surgeriesFormPhoneInput.sendKeys(contact);
	}

	// Select city
	public void selectCitySurgeryForm(String city) {
		surgeriesFormCityDropdown.click();
		if (city.equals("Bangalore")) {
			cityBangaloreSurgeries.click();
		}
	}

	// Submit form
	public void submitSurgeryForm() {
		surgeriesBookAppointmentButton.click();
	}

}
