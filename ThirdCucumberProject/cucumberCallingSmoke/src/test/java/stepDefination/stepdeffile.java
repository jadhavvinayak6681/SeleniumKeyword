package stepDefination;

import SmokeTesting.SmokeTesting;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class stepdeffile {

	static String args[];

	@Given("^I want to write a step with precondition$")
	public void i_want_to_write_a_step_with_precondition() throws Throwable 
	{
		// Write code here that turns the phrase above into concrete actions
		System.out.println("***********************************In execution");
		SmokeTesting.main(args);
	}

	@Given("^some other precondition$")
	public void some_other_precondition() throws Throwable 
	{
		// Write code here that turns the phrase above into concrete actions
	}

	@When("^I complete action$")
	public void i_complete_action() throws Throwable
	{
		// Write code here that turns the phrase above into concrete actions
	}
}
