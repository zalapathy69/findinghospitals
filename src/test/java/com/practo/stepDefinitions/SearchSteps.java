package com.practo.stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.practo.pageObjects.Search;
import com.practo.utils.DriverSetup;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {

	WebDriver driver = DriverSetup.getDriver();
	Search sh = new Search(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	int noOfDoctors;
	
	@Given("user selects location as {string}")
	public void user_selects_location_as(String location) {
		sh.searchForLocation(location);
	}

	@When("user inputs {string} in search")
	public void user_inputs_in_search(String specialization) {
		sh.searchForSpecialist(specialization);
	}
	
	@And("fetch the number of doctors displayed")
	public void fetch_the_number_of_doctors_displayed() {
		noOfDoctors = sh.numberOfDoctors();
	}

		/*
		 * All static waits at filter selections are
		 * necessary because the page takes
		 * time to load after the click happens.
		 */

	@And("user filters by patient stories: {string}")
	public void user_filters_by_patient_stories(String stories) {
		sh.filterByPatientStories(stories);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("verify the number of doctors is changed")
	public void verify_the_number_of_doctors_is_changed() {
		Assert.assertNotEquals(sh.numberOfDoctors(), noOfDoctors);
	}
	
	@And("user clicks on All filters")
	public void user_clicks_on_all_features() {
		sh.clickAllFilters();
	}
	
	@And("user selects: {string} under fees")
	public void user_selects_under_fees(String feeRange) {
		sh.selectFeeRange(feeRange);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("verify if the listed doctors have the right fee requirement {string}")
	public void verify_if_the_listed_doctors_have_the_right_fee_requirement(String priceCheck) {
		Assert.assertTrue(sh.seperateAndVerifyDoctorFees(sh.doctorInfoCard, priceCheck));
	}
	
	@And("user selects: {string} under availability")
	public void user_selects_under_availability(String availability) {
		sh.selectAvailability(availability);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("verify if the availability displayed above book clinic visit matches {string}")
	public void verify_if_the_availability_displayed_above_book_clinic_visit_matches(String availability) {
		Assert.assertEquals(sh.verifyAvailability(availability), true);
	}
	
	@And("user selects {string} under Consult type")
	public void user_selects_under_consult_type(String consultType) {
		sh.selectVideoConsult();
	}
	
	@Then("verify if results displayed are of video consult")
	public void verify_if_results_displayed_are_of_video_consult() {
		Assert.assertEquals(sh.verifyVideoConsult(), true);
	}
	
	@And("user clicks {string} under location")
	public void user_clicks_anna_nagar_under_location(String location) {
		sh.selectLocation(location);
	}
	
	@Then("verify if the area is selected: {string}")
	public void verify_if_the_area_is_selected(String area) {
		Assert.assertEquals(sh.locationVerification(area), true);
	}
	
	@And("user selects {string} under Experience")
	public void user_selects_under_experience(String experience) {
		sh.selectExperience(experience);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@And("user selects {string} under sort")
	public void user_selects_under_sort(String sort) {
		sh.selectSort(sort);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Then("record top five doctor names")
	public void record_top_five_doctor_names() {
		sh.outputDoctorNames();
	}

}
