package com.practo.pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practo.utils.ExcelUtils;

public class Search extends BasePage {

	public Search(WebDriver driver) {
		super(driver);
	}

	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@FindBy(xpath = "//input[@data-qa-id='omni-searchbox-keyword']")
	WebElement inputSpecialist;

	@FindBy(xpath = "//div[@data-qa-id='omni-suggestion-main']")
	WebElement specializationSearchFirstSuggestion;

	@FindBy(xpath = "//span[@data-qa-id='others-speciality']")
	WebElement otherSpecializations;

	@FindBy(xpath = "//p[@class='test-name' and text()='Dentist']")
	WebElement dentistOtherSpecializations;

	// Input specialization of doctor in the search bar
	public void searchForSpecialist(String specialization) {
		try {
			inputSpecialist.clear();
			inputSpecialist.sendKeys(specialization);
			Thread.sleep(3000);
			if (specializationSearchFirstSuggestion.isDisplayed()) {
				wait.until(ExpectedConditions
						.refreshed(ExpectedConditions.elementToBeClickable(specializationSearchFirstSuggestion)));
				js.executeScript("arguments[0].click()", specializationSearchFirstSuggestion);
			}
		} catch (Exception e) {
			otherSpecializations.click();
			dentistOtherSpecializations.click();
		}
	}

	@FindBy(xpath = "//input[@data-qa-id='omni-searchbox-locality']")
	WebElement inputLocation;

	@FindBy(xpath = "//div[@data-qa-id='omni-suggestion-main' and text()='Chennai']")
	WebElement inputConfirmation;

	// Input required location in the location search bar
	public void searchForLocation(String location) {
		if (inputLocation.getAttribute("value").equals(location)) {
			// do nothing. we have the location already.
		} else {
			inputLocation.clear();
			inputLocation.sendKeys(location);
			if (inputConfirmation.isDisplayed()) {
				try {
					inputConfirmation.click();
				} catch (StaleElementReferenceException e) {
				}
			}
		}
	}

	@FindBy(xpath = "//div[@data-qa-id='doctor_review_count_section']")
	WebElement patientStoriesDropdown;

	@FindBy(xpath = "//ul[@data-qa-id='doctor_review_count_list']/li[@class='c-dropdown__list__item']")
	List<WebElement> patientStoriesCountSelector;

	@FindBy(xpath = "//h1")
	WebElement numberOfDoctors;

	// Select filter by number of stories required
	public void filterByPatientStories(String numberOfStories) {
		patientStoriesDropdown.click();
		patientStoriesCountSelector.get(1).click();
	}

	// Return number of doctors displayed as result
	public int numberOfDoctors() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(numberOfDoctors.getText().split("\\s+")[0]);
	}

	@FindBy(xpath = "//*[@class='u-d-inlineblock u-color--white u-c-pointer']")
	WebElement allFiltersDropdown;

	@FindBy(xpath = "//label[@for='Fees0']")
	WebElement feesBelow500RadioButton;

	@FindBy(xpath = "//label[@for='Fees1']")
	WebElement feesAbove500RadioButton;

	@FindBy(xpath = "//label[@for='Fees2']")
	WebElement feesAbove1000RadioButton;

	@FindBy(xpath = "//label[@for='Fees3']")
	WebElement feesAbove2000RadioButton;

	@FindBy(xpath = "//span[@data-qa-id='consultation_fee']")
	List<WebElement> feesVerification;

	// Open and display all filters
	public void clickAllFilters() {
		allFiltersDropdown.click();
	}

	// Select the required fee range
	public void selectFeeRange(String feeRange) {
		if (feeRange.equals("0-500")) {
			feesBelow500RadioButton.click();
		} else if (feeRange.equals("Above 500")) {
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(feesAbove500RadioButton)));
			feesAbove500RadioButton.click();
		} else if (feeRange.equals("Above 1000")) {
			feesAbove1000RadioButton.click();
		} else if (feeRange.equals("Above 2000")) {
			feesAbove2000RadioButton.click();
		}
	}

	@FindBy(xpath = "//div[@data-qa-id='doctor_card']//div[@class='info-section']")
	public List<WebElement> doctorInfoCard;

	List<String> doctorName = new ArrayList<String>();
	List<String> doctorSpecialization = new ArrayList<String>();
	List<String> doctorExperience = new ArrayList<String>();
	List<String> doctorLocation = new ArrayList<String>();
	List<String> doctorFee = new ArrayList<String>();

	// Output top five doctor names
	public void outputDoctorNames() {
			try {
				ExcelUtils.write("DoctorDetails", 0, 0, "Doctor Name");
				ExcelUtils.write("DoctorDetails", 0, 1, "Doctor Specialization");
				ExcelUtils.write("DoctorDetails", 0, 2, "Doctor Experience");
				ExcelUtils.write("DoctorDetails", 0, 3, "Doctor Location");
				ExcelUtils.write("DoctorDetails", 0, 4, "Doctor Fee");
			} catch (IOException e) {
				e.printStackTrace();
			}
		for (int i = 0; i < 5; i++) {
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(doctorInfoCard.get(i))));
			doctorName.add(doctorInfoCard.get(i).getText().split("\\n+")[0]);
			doctorSpecialization.add(doctorInfoCard.get(i).getText().split("\\n+")[1]);
			doctorExperience.add(doctorInfoCard.get(i).getText().split("\\n+")[2]);
			doctorLocation.add(doctorInfoCard.get(i).getText().split("\\n+")[3]);
			doctorFee.add(doctorInfoCard.get(i).getText().split("\\n+")[4]);

			System.out.println("Doctor Name: " + doctorName.get(i));
			System.out.println("Doctor Specialization: " + doctorSpecialization.get(i));
			System.out.println("Doctor Experience: " + doctorExperience.get(i));
			System.out.println("Doctor Location: " + doctorLocation.get(i));
			System.out.println("Doctor Fee: " + doctorFee.get(i));
			System.out.println("\n");
			try {
				ExcelUtils.write("DoctorDetails", i+1, 0, doctorName.get(i));
				ExcelUtils.write("DoctorDetails", i+1, 1, doctorSpecialization.get(i));
				ExcelUtils.write("DoctorDetails", i+1, 2, doctorExperience.get(i));
				ExcelUtils.write("DoctorDetails", i+1, 3, doctorLocation.get(i));
				ExcelUtils.write("DoctorDetails", i+1, 4, doctorFee.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Verify if the fee range asked for matches the results
	public Boolean seperateAndVerifyDoctorFees(List<WebElement> infoCard, String condition) {
		for (int i = 0; i < 5; i++) {
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(infoCard.get(i))));
			doctorFee.add(infoCard.get(i).getText().split("\\n+")[4].replaceAll("[^0-9]", ""));
		}

		Boolean result = false;

		for (String fee : doctorFee) {
			if (condition.equals("0-500")) {
				if (Integer.parseInt(fee) <= 500) {
					result = true;
				}
			} else if (condition.equals("Above 500")) {
				if (Integer.parseInt(fee) >= 500) {
					result = true;
				}
			}
		}

		return result;
	}

	@FindBy(xpath = "//label[@for='Availability1']")
	WebElement availableTodayRadioButton;

	@FindBy(xpath = "//label[@for='Availability2']")
	WebElement availableTomorrowRadioButton;

	@FindBy(xpath = "//span[@data-qa-id='availability_text']")
	List<WebElement> verifyAvailability;
	
	// Select required availability of doctor
	public void selectAvailability(String availability) {
		if (availability.equals("Available Today")) {
			availableTodayRadioButton.click();
		} else {
			availableTomorrowRadioButton.click();
		}
	}

	// Verify if the selected availability is satisfied
	public Boolean verifyAvailability(String availability) {
		Boolean result = false;
		for (int i = 0; i < 5; i++) {
			String buttonText = verifyAvailability.get(i).getText();
			if (buttonText.equals(availability)) {
				result = true;
			}
		}
		return result;
	}

	@FindBy(xpath = "//label[@for='Consult-type0']")
	WebElement videoConsultRadioButton;

	@FindBy(xpath = "//button[@data-qa-id='consult_cta']")
	List<WebElement> verifyVideoConsultAvailability;

	// Click video consult radio button
	public void selectVideoConsult() {
		videoConsultRadioButton.click();
	}
	
	// Verify if the doctors displayed are available for video consult
	public Boolean verifyVideoConsult() {
		Boolean result = false;
		for (int i = 0; i < verifyVideoConsultAvailability.size(); i++) {
			if (verifyVideoConsultAvailability.get(i).isDisplayed()) {
				result = true;
			}
		}
		return result;
	}

	@FindBy(xpath = "//button[text()='anna nagar']")
	WebElement clickLocation;

	@FindBy(xpath = "//*[@class='u-grey_3-text uv2-spacer--xs-top']")
	WebElement verifyLocation;

	// Select required location
	public void selectLocation(String location) {
		clickLocation.click();
	}
	
	// verify if the required location is selected
	public Boolean locationVerification(String location) {
		if (verifyLocation.getText().contains(location)) {
			return true;
		} else {
			return false;
		}
	}

	@FindBy(xpath = "//div[@data-qa-id='years_of_experience_section']")
	WebElement experienceDropdown;

	@FindBy(xpath = "//li[@aria-label='5+ Years of experience']")
	WebElement experienceFivePlus;

	// Select experience
	public void selectExperience(String experience) {
		experienceDropdown.click();
		if (experience.equalsIgnoreCase("5+ Years of experience")) {
			experienceFivePlus.click();
		}
	}

	@FindBy(xpath = "//div[@data-qa-id='sort_by_section']")
	WebElement sortDropdown;

	@FindBy(xpath = "//li[@data-qa-id='doctor_review_count']")
	WebElement sortSelect;

	// Select sort
	public void selectSort(String sort) {
		sortDropdown.click();
		if (sort.equals("Number of patient stories - High to low")) {
			sortSelect.click();
		}
	}

}
