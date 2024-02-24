package stepdefinitions.rahulshettyacademy

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import pageobjects.rahulshettyacademy.LoginPagePracticePageObjs

class LoginPagePracticeStepDfn {

	private val pageObjects = LoginPagePracticePageObjs()

	@When("I enter in the following fields the next values on the Login page")
	fun iEnterInTheFollowingFieldsTheNextValues(fieldValues: Map<String, String>) {
		pageObjects.inputToField(fieldValues)
	}

	@When("I select the {string} radiobutton on the Login page")
	fun iSelectTheRadiobutton(radioButtonLabel: String) {
		pageObjects.clickOnRadioButton(radioButtonLabel)
	}

	@Then("I verify a confirm pane appeared on the Login page")
	fun iVerifyAConfirmPaneAppeared() {
		pageObjects.verifyConfirmPaneAppeared()
	}

	@When("I click on the Okay button from the confirm pane on the Login page")
	fun iClickOnTheOkayButtonOnTheConfirmPane() {
		pageObjects.clickOnOkayButton()
	}

	@Then("I verify the confirm pane disappeared on the Login page")
	fun iVerifyTheConfirmPaneDisappeared() {
		pageObjects.verifyConfirmPaneDisappeared()
	}

	@When("I select the {string} option from the combobox on the Login page")
	fun iSelectTheOptionFromTheCombobox(option: String) {
		pageObjects.selectFromCobobox(option)
	}

	@When("I Agree to the terms and conditions on the Login page")
	fun iAgreeToTheTermsAndConditions() {
		pageObjects.agreeTermsAndConditions()
	}

	@When("I click on the sign in button on the Login page")
	fun iClickOnTheSignInBtn() {
		pageObjects.clickOnSignInBtn()
	}

	@Then("I verify I am on the shop page")
	fun iVerifyIAmOnTheShopPage() {
		pageObjects.verifyShopPage()
	}

	@When("I add all items to cart on the shop page")
	fun iAddAllItemsToCart() {
		pageObjects.addAllProductsToCart()
	}

	@Then("I verify I have {int} items on cart on the shop page")
	fun iVerifyIHaveItemsOnCart(items: Int) {
		pageObjects.verifyHowManyItemsInCart(items)
	}

	@When("I click on the checkout button on the shop page")
	fun iClickOnTheCheckoutButton() {
		pageObjects.clickOnCheckoutBtn()
	}

	@Then("I verify I am on the cart view")
	fun iVerifyIAmOnCartView() {
		pageObjects.verifyCartPage()
	}
}