package stepdefinitions

import driver.DriverManager
import io.cucumber.java.en.When
import org.testng.Assert
import pageobjects.legacy.advantage.bsn.*

class AdvantageStepDfn {

	private val driver = DriverManager.getDriver()

	@When("I create an account with this user: {string}, this email: {string} and this password: {string}")
	fun createAccount(user: String, email: String, password: String) {
		val userr = Bsn_CreateAccount(driver)
		userr.user = user
		userr.email = email
		userr.password = password
		userr.Run()
	}

	@When("I login with user: {string} and password {string}")
	fun login(user: String, password: String) {
		val login = Bsn_Login(driver)
		login.user = user
		login.password = password
		login.Run()
	}

	@When("I search for a random product")
	fun search() {
		val search = Bsn_Search(driver)
		search.Run()
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