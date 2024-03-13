package com.practo.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features= {".//FeatureFiles/"}, 
		glue = "com.practo.stepDefinitions", 
		plugin = {"pretty","html:reports/report.html"},
		dryRun=false,
		monochrome=true
		
		)

public class TestRunner {

}
