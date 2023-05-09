package pageobjects

import org.openqa.selenium.By
import org.testng.Assert
import pageactions.PageActions

object AdvantagePageObjs: PageActions() {

	private val NAVBAR_MENU_USER_BTN: By = By.id("menuUser")
	private val NAVBAR_MENU_USER_PANE = By.className("PopUp")
	private val NAVBAR_USERNAME = By.xpath("//*[@id='menuUserLink']/span")
	private val MENU_USER_PANE_USERNAME_FIELD = By.xpath("//input[@name='username']")
	private val MENU_USER_PANE_PASSWORD_FIELD = By.xpath("//input[@name='password']")
	private val MENU_USER_PANE_SIGN_IN_BTN = By.xpath("//button[@id='sign_in_btnundefined']")
	private val MENU_USER_PANE_CREATE_ACCOUNT_BTN = By.xpath("//*[@data-ng-click='createNewAccount()']")
	private val CREATE_ACCOUNT_PAGE_TITLE = By.xpath("//h3[text() = 'CREATE ACCOUNT']")
	private val CREATE_ACCOUNT_USERNAME_FIELD = By.xpath("//*[@name='usernameRegisterPage']")
	private val CREATE_ACCOUNT_EMAIL_FIELD = By.xpath("//*[@name='emailRegisterPage']")
	private val CREATE_ACCOUNT_PASSWORD_FIELD = By.xpath("//*[@name='passwordRegisterPage']")
	private val CREATE_ACCOUNT_CONFIRM_PASSWORD_FIELD = By.xpath("//*[@name='confirm_passwordRegisterPage']")
	private val CREATE_ACCOUNT_I_AGREE_CHECKBOX = By.xpath("//*[@name='i_agree']")
	private val CREATE_ACCOUNT_REGISTER_BTN = By.id("register_btnundefined")
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

	fun openUserMenu() {
		this.clickElement(NAVBAR_MENU_USER_BTN)
	}

	fun verifyIfUserMenuPaneIsOpen() {
		val isVisible = this.isPageObjectVisible(NAVBAR_MENU_USER_PANE)
		Assert.assertTrue(isVisible, "User menu pane visibility")
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
		Assert.assertEquals(actualTitle, expectedTitle)
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
		val actualUsername = this.findElement(NAVBAR_USERNAME).text
		Assert.assertEquals(actualUsername, expectedUser)
	}
}