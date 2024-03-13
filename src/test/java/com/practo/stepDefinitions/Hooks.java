package com.practo.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.practo.utils.DriverSetup;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	static WebDriver driver;
	static Properties p;
	
	@Before
	public static void setup() throws IOException{
		driver = DriverSetup.initializeBrowser();		
		p = DriverSetup.getProperties();
		driver.manage().window().maximize();
		driver.get(p.getProperty("appUrl"));
	}

	@After
	public static void tearDown() {
		driver.quit();
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {
		TakesScreenshot ts=(TakesScreenshot) driver;
    	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
    	scenario.attach(screenshot, "image/png", scenario.getName());
	}

}
