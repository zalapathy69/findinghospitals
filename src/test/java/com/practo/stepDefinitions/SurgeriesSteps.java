package com.practo.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.practo.pageObjects.HomePage;
import com.practo.pageObjects.Surgeries;
import com.practo.utils.DriverSetup;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SurgeriesSteps {
	WebDriver driver = DriverSetup.getDriver();
	HomePage hp = new HomePage(driver);
	Surgeries sg = new Surgeries(driver);
	
	@Given("user navigates to Surgeries from home page")
	public void user_navigates_to_surgeries_from_home_page() {
		hp.visitNavLinks("Surgeries");
	}

	@When("user selects {string} from the city dropdown on surgeries")
	public void user_selects_from_the_city_dropdown_on_surgeries(String city) {
		sg.selectCityFromDropdown(city);
	}

	@And("user selects {string} from the ailment dropdown")
	public void user_selects_from_the_ailment_dropdown(String ailment) {
		sg.selectAilmentFromDropdown(ailment);
	}

	@And("user inputs {string} in name on surgeries")
	public void user_inputs_in_name_on_surgeries(String name) {
		sg.nameInputSurgeriesForm(name);
	}

	@And("user inputs {string} in Contact Number on surgeries")
	public void user_inputs_in_contact_number_on_surgeries(String contact) {
		sg.contactInputSurgeriesForm(contact);
	}

	@And("click Book Appointment button")
	public void click_book_appointment_button() {
		sg.bookAppointment();
	}

	@Then("capture mobile number in otp popup")
	public void capture_mobile_number_in_otp_popup() {
		Assert.assertEquals(sg.verifyMobileNumber(), "+919879879877");
	}
	
	@Then("capture error message displayed below name")
	public void capture_error_message_displayed_below_name() {
		Assert.assertEquals(sg.getNameErrorMessage(), "Invalid User Name");
	}
	
	@Then("capture error message displayed below contact number")
	public void capture_error_message_displayed_below_contact_number() {
		Assert.assertEquals(sg.getContactErrorMessage(), "Invalid Phone Number");
	}
	
	@When("user clicks on {string} under Popular Surgeries")
	public void user_clicks_on_under_popular_surgeries(String surgeryName) {
		sg.clickSurgery(surgeryName);
	}

	@Then("verify the message displayed above the surgery form contains {string}")
	public void verify_the_message_displayed_above_the_surgery_form_contains(String surgeryName) {
		if (sg.returnSurgeriesFormMessage().contains(surgeryName)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
	
	@When("enter {string} in name in popular surgery form")
	public void enter_in_name_in_popular_surgery_form(String name) {
		sg.fillSurgeryName(name);
	}

	@And("enter {string} in contact number in popular surgery form")
	public void enter_in_contact_number_in_popular_surgery_form(String contact) {
		sg.fillSurgeryContact(contact);
	}

	@And("select {string} in the city dropdown in popular surgery form")
	public void select_in_the_city_dropdown_in_popular_surgery_form(String city) {
		sg.selectCitySurgeryForm(city);
	}

	@And("user clicks on Book Appointment button in popular surgery form")
	public void user_clicks_on_book_appointment_button_in_popular_surgery_form() {
		sg.submitSurgeryForm();
	}
	
	@When("extract surgeries listed under Popular Surgeries")
	public void extract_surgeries_listed_under_popular_surgeries() {
		System.out.println("List of Popular Surgeries extracted.");
	}

	@And("display them in console output")
	public void display_them_in_console_output() {
		sg.outputPopularSurgeries();
	}
}
