package com.practo.stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.practo.pageObjects.HealthAndWellness;
import com.practo.pageObjects.HomePage;
import com.practo.utils.DriverSetup;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HealthAndWellnessSteps {
	
	WebDriver driver = DriverSetup.getDriver();
	HomePage hp = new HomePage(driver);
	HealthAndWellness hw = new HealthAndWellness(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@Given("user navigates to Health and Wellness Plan page")
	public void user_navigates_to_health_and_wellness_plan_page() {
		hp.navigateToHealthWellness();
	}

	@When("user scrolls down to the bottom of the page")
	public void scroll_down_until_schedule_a_demo_navigation_helper_is_visible() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	@And("user clicks the Schedule a demo button")
	public void user_clicks_the_schedule_a_demo_button() {
		hw.clickScheduleDemoNavButton();
	}

	@Then("verify the page is scrolled up to the top")
	public void verify_the_page_is_scrolled_up_to_the_top() {
		long scrollPosition = (long) js.executeScript("return window.pageYOffset;");
		Point point = hw.scheduleDemoButton.getLocation();
		int scheduleDemoButtonPosition = point.getY();

		Assert.assertEquals(scrollPosition, scheduleDemoButtonPosition);
	}
	
	@When("user inputs {string} in name")
	public void user_inputs_in_name(String name) {
		hw.fillName(name);
	}

	@And("user inputs {string} in organization name")
	public void user_inputs_in_organization_name(String orgName) {
		hw.fillOrganization(orgName);
	}

	@And("user inputs {string} in Contact Number")
	public void user_inputs_in_contact_number(String contact) {
		hw.fillContact(contact);
	}

	@And("user inputs {string} in Official Email ID")
	public void user_inputs_in_official_email_id(String email) {
		hw.fillEmail(email);
	}

	@And("user selects Organization size: {string}")
	public void user_selects_organization_size(String orgSize) {
		if (orgSize.equals("")) {
			// do nothing to leave the field empty.
		} else {
			hw.selectOrgSize(orgSize);
		}
	}

	@And("user selects {string} in Interested in")
	public void user_selects_in_interested_in(String interest) {
		if (interest.equals("")) {
			// do nothing to leave the field empty.
		} else {
			hw.selectInterest(interest);
		}
	}

	@Then("verify Schedule a demo button is disabled")
	public void verify_schedule_a_demo_button_is_disabled() {
		Assert.assertEquals(hw.isScheduleDemoButtonEnabled(), false);
		System.out.println("Schedule a demo button is disabled.");
	}

	@Then("verify Schedule a demo button is enabled")
	public void verify_schedule_a_demo_button_is_enabled() {
		Assert.assertEquals(hw.isScheduleDemoButtonEnabled(), true);
		System.out.println("Schedule a demo button is enabled.");
	}
	
	@And("user clicks Schedule a demo button")
	public void user_clicks_schedule_a_demo_button() {
		hw.submitForm();
	}
	
	@Then("capture thank you message")
	public void capture_thank_you_message() {
		Assert.assertEquals(hw.fetchThankYouMessage(), "THANK YOU");
	}
	
}
