package com.selenium.cucumber.cucumberCallingSmoke;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C://Users/rohit.labhe/git/Cucumber_Automation/ThirdCucumberProject/cucumberCallingSmoke/src/test/java/feature",
        glue = {"stepDefination"},
        plugin = {"json:target/cucumberNew.json"} 
        )

public class AppTest 
{
	public static void main(String[] args)
	{
		
	}
}