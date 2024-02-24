package pageobjects.rahulshettyacademy

import org.openqa.selenium.By
import core.pageactions.PageActions
import core.utilities.Asserts

class LoginPagePracticePageObjs: PageActions() {

	private val NAME_FIELD = By.id("username")
	private val PASSWORD_FIELD = By.id("password")
	private val RADIOBUTTONS_GROUP = "//span[contains(text(),'%s')]/.."
	private val CONFIRM_PANE_OK_BTN = By.id("okayBtn")
	private val COMBOBOX = By.xpath("//div[@class='form-group']//select")
	private val CHECKBOX = By.id("terms")
	private val SIGNIN_BTN = By.id("signInBtn")
	private val NAVBAR_BRAND = By.xpath("//app-shop/nav//a[@class='navbar-brand']")
	private val ADD_TO_CART_BUTTONS = By.xpath("//app-card//button")
	private val CHECKOUT_BTN = By.xpath("//nav//a[contains(text(), 'Checkout')]")
	private val CONTINUE_SHOPPING_BTN = By.xpath("(//button[@type='button'])[6]")
	private val textFieldsMap = HashMap<String, (String) -> Unit>()

	init {
		textFieldsMap["Username"] = { data -> this.inputData(NAME_FIELD, data) }
		textFieldsMap["Password"] = { data -> this.inputData(PASSWORD_FIELD, data) }
	}

	fun inputToField(fieldValues: Map<String, String>) {
		fieldValues.forEach { (field, value) -> textFieldsMap[field]?.let { it(value) } }
	}

	fun clickOnRadioButton(radioButtonLabel: String) {
		val xpath = By.xpath(String.format(RADIOBUTTONS_GROUP, radioButtonLabel))
		this.clickElement(xpath)
	}

	fun verifyConfirmPaneAppeared() {
		Asserts.assertTrue(this.isPageObjectVisible(CONFIRM_PANE_OK_BTN))
	}

	fun verifyConfirmPaneDisappeared() {
		this.waitUntilInvisible(CONFIRM_PANE_OK_BTN)
		Asserts.assertTrue(!this.isPageObjectVisible(CONFIRM_PANE_OK_BTN))
	}

	fun clickOnOkayButton() {
		this.clickElement(CONFIRM_PANE_OK_BTN)
	}

	fun selectFromCobobox(option: String) {
		this.selectFromComboboxByText(COMBOBOX, option)
	}

	fun agreeTermsAndConditions() {
		this.clickElement(CHECKBOX)
	}

	fun clickOnSignInBtn() {
		this.clickElement(SIGNIN_BTN)
	}

	fun verifyShopPage() {
		Asserts.assertTrue(this.isPageObjectPresent(NAVBAR_BRAND), "Viewing Shop page")
	}

	fun addAllProductsToCart() {
		val buttons = this.findElements(ADD_TO_CART_BUTTONS)
		buttons.forEach { button -> this.clickElement(button) }
	}

	fun verifyHowManyItemsInCart(qty: Int) {
		val text = this.getTextWithJavascript(CHECKOUT_BTN)
		Asserts.assertTextContains(qty.toString(), text, "Items in cart")
	}

	fun clickOnCheckoutBtn() {
		this.clickElement(CHECKOUT_BTN)
	}

	fun verifyCartPage() {
		Asserts.assertTrue(this.isPageObjectVisible(CONTINUE_SHOPPING_BTN), "Viewing Cart page")
	}
}