package stepdefinitions

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import pageobjects.HrmPageObjs

class HrmStepDfn {

	private val pageOjects = HrmPageObjs()

	@Then("User makes sure it is on HRMLogin page")
	fun iMakeSureIAmOnHRMLoginPage() {
		pageOjects.verifyLoginPage()
	}

	@When("User logins with username {string} and password {string}")
	fun goToHomePage(userName: String, passWord: String) {
		pageOjects.login(userName, passWord)
	}

	@Then("User should be on the {string} page")
	fun verifyLogin(title: String) {
		pageOjects.verifyNavbarTitle(title)
	}

	@Then("User should be able to see error message {string}")
	fun verifyErrorMessage(expectedErrorMessage: String) {
		pageOjects.verifyErrorMessage(expectedErrorMessage)
	}

	@When("User clicks on {string} button on the Sidebar")
	fun userClicksOnTheButtonOnTheSidebar(button: String) {
		pageOjects.goToSidebarOption(button)
	}

	@When("User clicks on {string} button on the PIM Sidebar")
	fun userClicksOnTheButtonOnThePimSidebar(button: String) {
		pageOjects.goToMyInfoSidebarOption(button)
	}

	@Then("User verifies the PIM header title is {string}")
	fun verifyPimHeader(title: String) {
		pageOjects.verifyMyInfoHeaderTitle(title)
	}

	@Then("User makes sure the following fields have these values")
	fun userMakesSureTheFollowingInputsHaveTheseValues(expectedValues: Map<String, String>) {
		pageOjects.verifyFieldsValues(expectedValues)
	}
}
