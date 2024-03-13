package com.practo.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {".//FeatureFiles/"}, 
		glue = "com.practo.stepDefinitions", 
		plugin = {"pretty","html:reports/report.html","rerun:target/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		dryRun=false,
		monochrome=true
		)

public class TestNGRunner extends AbstractTestNGCucumberTests{
	
}

