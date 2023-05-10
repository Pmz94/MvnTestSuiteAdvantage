package stepdefinitions

import config.DriverManager
import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import legacy.advantage.bsn.*
import org.testng.Assert
import pageobjects.AdvantagePageObjs

class AdvantageStepDfn {

	private val driver = DriverManager.getDriver()
	private val pageObjects = AdvantagePageObjs

	@When("I click on the user menu button in the page navbar")
	fun iClickOnTheUserMenuButton() {
		pageObjects.openUserMenu()
	}

	@Then("I verify the User Menu pane is open")
	fun iVerifyTheUserMenuPaneIsOpen() {
		pageObjects.verifyIfUserMenuPaneIsOpen()
	}

	@When("I fill up the next fields in the User Menu pane")
	fun iFillUpTheNextFieldsInTheUserMenuPane(values: Map<String, String>) {
		pageObjects.fillUpMenuUserPaneTextFields(values)
	}

	@Then("I click on the Sign Up button on the User Menu pane")
	fun iClickOnTheSignUpButtonOnTheUserMenuPane() {
		pageObjects.clickOnSignInBtn()
	}

	@When("I click on the Create Account button in the User Menu pane")
	fun iClickOnTheCreateAccountButtonInTheUserMenuPane() {
		pageObjects.clickOnCreateAccountBtn()
	}

	@Then("I verify I am on the Create Account page")
	fun iVerifyiAmOnTheCreateAccountPage() {
		pageObjects.verifyCreateAccountPage()
	}

	@When("I fill up the next fields in the Create Account page")
	fun iFillUpTheNextFieldsInTheCreateAccountPage(values: Map<String, String>) {
		pageObjects.fillUpCreateAccountTextFields(values)
	}

	@Then("I agree to the www.AdvantageOnlineShopping.com Conditions of Use and Privacy Notice in the Create Account page")
	fun iAgreeToTheConditionsOfUseAndPrivacyNotice() {
		pageObjects.agreeToTheTerms()
	}

	@When("I click on the register button on the Create Account page")
	fun iClickOnTheRegisterButton() {
		pageObjects.clickOnRegisterBtn()
	}

	@Then("I verify the username {string} appears in the page navbar")
	fun iVerifyTheUsernameAppearsOnTheNavbar(expectedUsername: String) {
		pageObjects.checkIfLoggedIn(expectedUsername)
	}

	// TODO: Borrar cuando no se use
	@When("I login with user: {string} and password {string}")
	fun login(user: String, password: String) {
		val login = Bsn_Login(driver)
		login.user = user
		login.password = password
		login.Run()
	}

	@When("I click on the Search icon on the page navbar")
	fun iClickOnTheSearchIconOnThePageNavbar() {
		pageObjects.clickOnSearchBtn()
	}

	@Then("I write {string} on the search field on the page navbar")
	fun iWriteOnTheSearchFieldOnThePageNavbar(search: String) {
		pageObjects.search(search)
	}

	@Then("I verify the Search Results pane is visible on the page navbar")
	fun iVerifyTheSearchResultsPaneIsVisible() {
		pageObjects.verifyNavbarSearchResultsPane()
	}

	@When("I click on the View All button on the Search Results pane")
	fun iClickOnTheViewAllButtonOnTheSearchResultsPane() {
		pageObjects.clickOnViewAllSearches()
	}

	@Then("I close the Search Results pane on the page navbar")
	fun iCloseTheSearchResultsPane() {
		pageObjects.closeSearchResultPane()
	}

	@Then("I verify I am on the Search Results page")
	fun iVerifyIAmOnTheSearchResultsPage() {
		pageObjects.verifySearchResultsPage()
	}

	@Then("I verify there is a product called {string} on the Search Results page")
	fun iSearchForAProductCalledOnTheSearchResultsPage(product: String) {
		pageObjects.findProductOnSearchPage(product)
	}

	@And("I click on the main logo on the page navbar")
	fun iClickOnTheMainLogo() {
		pageObjects.goToIndex()
	}

	@Then("I verify there is a product called {string} on the Search Results pane")
	fun iSearchForAProductCalledOnTheSearchResultsPane(product: String) {
		pageObjects.findProductOnSearchPane(product)
	}

	@When("I click on the product called {string} on the Search Results pane")
	fun iClickOnTheProductCalledOnTheSearchResultsPane(product: String) {
		pageObjects.clickOnProductOnSearchPane(product)
	}

	@Then("I verify I am on the product page for {string}")
	fun iVerifyIAmOnTheProductPageFor(product: String) {
		pageObjects.verifyProductPage(product)
	}

	@When("I add 3 products to the cart")
	fun addingToCart() {
		login("Team003", "Team3")

		val addCart = Bsn_AddingToCart(driver)
		addCart.Run()
	}

	@When("I checkout 3 products")
	fun checkout() {
		login("Team003", "Team3")

		val addCart = Bsn_AddingToCart(driver)
		addCart.Run()

		val checkout = Bsn_Checkout(driver)
		checkout.safepay_user = "Team003"
		checkout.safepay_password = "Team3"
		checkout.Run()
	}

	@When("I send a message to their contact with this email {string}")
	fun contactUs(email: String) {
		val contact = Bsn_ContactUs(driver)
		contact.email = email
		contact.Run()
	}

	@When("I chat with the page bot")
	fun chat() {
		val chat = Bsn_Chat(driver)
		val isCheck = chat.chatOpen()
		Assert.assertTrue(isCheck)
	}
}