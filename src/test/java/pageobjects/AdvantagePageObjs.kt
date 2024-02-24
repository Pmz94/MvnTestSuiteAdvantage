package pageobjects

import org.openqa.selenium.By
import core.pageactions.PageActions
import core.utilities.Asserts

class AdvantagePageObjs: PageActions() {

	private val NAVBAR_LOGO_BTN: By = By.className("logo")
	private val NAVBAR_MENU_USER_BTN = By.id("menuUser")
	private val NAVBAR_MENU_USER_PANE = By.className("PopUp")
	private val NAVBAR_USERNAME_LABEL = By.xpath("//*[@id='menuUserLink']/span")
	private val NAVBAR_SEARCH_BTN = By.id("searchSection")
	private val NAVBAR_SEARCH_FIELD = By.id("autoComplete")
	private val NAVBAR_SEARCH_CLOSE_BTN = By.xpath("//div[@data-ng-click='closeSearchForce()']")
	private val MENU_SEARCH_PANE = By.className("searchPopUp")
	private val MENU_SEARCH_PANE_VIEW_ALL_BTN = By.xpath("//a[@class='roboto-medium viewAll ng-scope']")
	private val MENU_SEARCH_PANE_RESULTS_PRODUCT = "(//a[@class='product ng-scope']//p[normalize-space() = '%s']/..)"
	private val SEARCH_RESULTS_PAGE_TITLE = By.id("searchResultLabel")
	private val TOLOWERCASE = "translate(normalize-space(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')"
	private val SEARCH_RESULTS_PAGE_PRODUCT = "//a[$TOLOWERCASE='%s']"
	private val PRODUCT_PAGE_NAME = By.xpath("//div[@id='Description']/h1")
	private val MENU_USER_PANE_USERNAME_FIELD = By.xpath("//input[@name='username']")
	private val MENU_USER_PANE_PASSWORD_FIELD = By.xpath("//input[@name='password']")
	private val MENU_USER_PANE_SIGN_IN_BTN = By.xpath("//button[@id='sign_in_btn']")
	private val MENU_USER_PANE_CREATE_ACCOUNT_BTN = By.xpath("//*[@data-ng-click='createNewAccount()']")
	private val CREATE_ACCOUNT_PAGE_TITLE = By.xpath("//h3[text()='CREATE ACCOUNT']")
	private val CREATE_ACCOUNT_USERNAME_FIELD = By.xpath("//*[@name='usernameRegisterPage']")
	private val CREATE_ACCOUNT_EMAIL_FIELD = By.xpath("//*[@name='emailRegisterPage']")
	private val CREATE_ACCOUNT_PASSWORD_FIELD = By.xpath("//*[@name='passwordRegisterPage']")
	private val CREATE_ACCOUNT_CONFIRM_PASSWORD_FIELD = By.xpath("//*[@name='confirm_passwordRegisterPage']")
	private val CREATE_ACCOUNT_I_AGREE_CHECKBOX = By.xpath("//*[@name='i_agree']")
	private val CREATE_ACCOUNT_REGISTER_BTN = By.id("register_btn")
	private val textFieldsMapMenuUserPane = HashMap<String, (String) -> Unit>()
	private val textFieldsMapCreateAccount: HashMap<String, (String) -> Unit> = HashMap()

	init {
		textFieldsMapMenuUserPane["Username"] = { data -> this.inputData(MENU_USER_PANE_USERNAME_FIELD, data) }
		textFieldsMapMenuUserPane["Password"] = { data -> this.inputData(MENU_USER_PANE_PASSWORD_FIELD, data) }

		textFieldsMapCreateAccount["Username"] = { data -> this.inputData(CREATE_ACCOUNT_USERNAME_FIELD, data) }
		textFieldsMapCreateAccount["Email"] = { data -> this.inputData(CREATE_ACCOUNT_EMAIL_FIELD, data) }
		textFieldsMapCreateAccount["Password"] = { data -> this.inputData(CREATE_ACCOUNT_PASSWORD_FIELD, data) }
		textFieldsMapCreateAccount["Confirm password"] = { data -> this.inputData(CREATE_ACCOUNT_CONFIRM_PASSWORD_FIELD, data) }
	}

	fun goToIndex() {
		this.clickElement(NAVBAR_LOGO_BTN)
	}

	fun openUserMenu() {
		this.clickElement(NAVBAR_MENU_USER_BTN)
	}

	fun clickOnSearchBtn() {
		this.clickElement(NAVBAR_SEARCH_BTN)
	}

	fun search(search: String) {
		this.inputData(NAVBAR_SEARCH_FIELD, search)
	}

	fun verifyNavbarSearchResultsPane() {
		val isVisible = this.isPageObjectVisible(MENU_SEARCH_PANE)
		Asserts.assertTrue(isVisible, "Menu Search pane visibility")
	}

	fun clickOnViewAllSearches() {
		this.clickElement(MENU_SEARCH_PANE_VIEW_ALL_BTN)
	}

	fun closeSearchResultPane() {
		this.clickElement(NAVBAR_SEARCH_CLOSE_BTN)
	}

	fun verifySearchResultsPage() {
		val expectedTitle = "Search result"
		val actualTitle = this.findElement(SEARCH_RESULTS_PAGE_TITLE).text
		Asserts.assertTrue(actualTitle.contains(expectedTitle), "Verifying Search Results page")
	}

	fun findProductOnSearchPage(expectedName: String) {
		val locator = By.xpath(String.format(SEARCH_RESULTS_PAGE_PRODUCT, expectedName.lowercase()))
		val isFound = this.isPageObjectVisible(locator)
		Asserts.assertTrue(isFound, "Found product in Search page with name $expectedName")
	}

	fun findProductOnSearchPane(expectedName: String) {
		val locator = By.xpath(String.format(MENU_SEARCH_PANE_RESULTS_PRODUCT, expectedName.uppercase()))
		val isFound = this.isPageObjectVisible(locator)
		Asserts.assertTrue(isFound, "Found product in Search pane with name $expectedName")
	}

	fun clickOnProductOnSearchPane(name: String) {
		val locator = By.xpath(String.format(MENU_SEARCH_PANE_RESULTS_PRODUCT, name.uppercase()))
		this.clickElement(locator)
	}

	fun verifyProductPage(productName: String) {
		val actualName = this.findElement(PRODUCT_PAGE_NAME).text
		Asserts.assertEquals(productName.uppercase(), actualName.uppercase(), "Product page name")
	}

	fun verifyIfUserMenuPaneIsOpen() {
		val isVisible = this.isPageObjectVisible(NAVBAR_MENU_USER_PANE)
		Asserts.assertTrue(isVisible, "User menu pane visibility")
	}

	fun fillUpMenuUserPaneTextFields(inputMap: Map<String, String>) {
		this.fillUpTextFields(inputMap, textFieldsMapMenuUserPane)
	}

	fun clickOnSignInBtn() {
		this.clickElement(MENU_USER_PANE_SIGN_IN_BTN)
	}

	fun clickOnCreateAccountBtn() {
		this.clickElement(MENU_USER_PANE_CREATE_ACCOUNT_BTN)
	}

	fun verifyCreateAccountPage() {
		val expectedTitle = "CREATE ACCOUNT"
		val actualTitle = this.findElement(CREATE_ACCOUNT_PAGE_TITLE).text
		Asserts.assertEquals(expectedTitle, actualTitle, "Page title")
	}

	fun fillUpCreateAccountTextFields(inputMap: Map<String, String>) {
		this.fillUpTextFields(inputMap, textFieldsMapCreateAccount)
	}

	fun agreeToTheTerms() {
		val checkbox = this.findByClickable(CREATE_ACCOUNT_I_AGREE_CHECKBOX)
		if(!checkbox.isSelected) this.clickElement(checkbox)
	}

	fun clickOnRegisterBtn() {
		this.clickElement(CREATE_ACCOUNT_REGISTER_BTN)
	}

	fun checkIfLoggedIn(expectedUser: String) {
		val actualUsername = this.findElement(NAVBAR_USERNAME_LABEL).text
		Asserts.assertEquals(expectedUser, actualUsername, "Logged in")
	}
}