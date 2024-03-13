package com.practo.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.practo.pageObjects.HomePage;
import com.practo.utils.DriverSetup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {
	
	WebDriver driver = DriverSetup.getDriver();
	HomePage hp = new HomePage(driver);
	
	@Given("user opens practo home page")
	public void user_opens_practo() {
		System.out.println("Practo is open");
	}

	@When("user clicks on {string}")
	public void user_clicks_on(String navItem) {
		hp.visitNavLinks(navItem);
	}

	@Then("verify page title {string}")
	public void verify_page_title(String title) {
		Assert.assertEquals(title, driver.getTitle());
	}
}
