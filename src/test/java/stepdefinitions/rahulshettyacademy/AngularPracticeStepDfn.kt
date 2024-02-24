package stepdefinitions.rahulshettyacademy

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import pageobjects.rahulshettyacademy.AngularPracticePageObjs

class AngularPracticeStepDfn {

	private val pageObjects = AngularPracticePageObjs()

	@When("I put in the {string} field the value of {string}")
	fun iPutInTheFieldTheValueOf(field: String, value: String) {
		pageObjects.inputToField(field, value)
	}

	@When("I check the checkbox on the page")
	fun iCheckTheCheckboxOnThePage() {
		pageObjects.checkTheCheckbox()
	}

	@When("I select the {string} option from the combobox on the Angular practice page")
	fun iSelectTheOptionFromTheCombobox(gender: String) {
		pageObjects.selectFromCobobox(gender)
	}

	@When("I select the {string} option from the radiobuttons")
	fun iSelectTheOptionFromTheRadiobuttons(radiobutton: String) {
		pageObjects.selectRadioButton(radiobutton)
	}

	@When("I click on the submit button")
	fun iClickOnTheSubmitButton() {
		pageObjects.clickOnSubmit()
	}

	@Then("I verify the success alert appears with the message {string}")
	fun iVerifyTheSuccessAlertAppearsWithTheMessage(message: String) {
		pageObjects.verifyAlertMessage(message)
	}
}