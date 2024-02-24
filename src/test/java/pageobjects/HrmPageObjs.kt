package pageobjects

import org.openqa.selenium.By
import core.pageactions.PageActions
import core.utilities.Asserts

class HrmPageObjs: PageActions() {

	private val LOGIN_TITLE = By.xpath("//h5[contains(@class,'orangehrm-login-title')]")
	private val LOGIN_USERNAME_FIELD = By.name("username")
	private val LOGIN_PASSWORD_FIELD = By.name("password")
	private val LOGIN_LOGIN_BTN = By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
	private val LOGIN_ERROR_MESSAGE = By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")
	private val NAVBAR_TITLE = By.xpath("//*[@id='app']/div[1]/div[1]/header/div[1]/div[1]/span/h6")
	private val SIDEBAR_BTN = "//ul[@class='oxd-main-menu']//li//a//span[text()='%s']/.."
	private val MYINFO_SIDEBAR_BTN = "//div[@class='orangehrm-tabs']/div/a[text()='%s']"
	private val MYINFO_HEADER_TITLE = "//h6[contains(@class,'orangehrm-main-title') and text()='%s']"
	private val MYINFO_JOB_JOINED_DATE_FIELD = By.xpath("//label[@class='oxd-label' and text()='Joined Date']/../..//input")
	private val MYINFO_JOB_JOB_SPECIFICATION_FIELD = By.xpath("//label[@class='oxd-label' and text()='Job Specification']/../..//p")
	private val MYINFO_JOB_FIELDS = "//label[@class='oxd-label' and text()='%s']/../..//div[@class='oxd-select-text-input']"

	fun verifyLoginPage() {
		val title = this.findElement(LOGIN_TITLE).text
		Asserts.assertEquals("Login", title, "Login title")
	}

	fun login(userName: String, passWord: String) {
		this.inputData(LOGIN_USERNAME_FIELD, userName)
		this.inputData(LOGIN_PASSWORD_FIELD, passWord)
		this.clickElement(LOGIN_LOGIN_BTN)
	}

	fun verifyNavbarTitle(expectedTitle: String) {
		val homePageHeading = this.findElement(NAVBAR_TITLE).text
		Asserts.assertEquals(expectedTitle, homePageHeading, "Navbar title")
	}

	fun verifyErrorMessage(expectedErrorMessage: String) {
		val actualErrorMessage = this.findElement(LOGIN_ERROR_MESSAGE).text
		Asserts.assertEquals(expectedErrorMessage, actualErrorMessage)
	}

	fun goToSidebarOption(name: String) {
		val btn = By.xpath(String.format(SIDEBAR_BTN, name))
		this.clickElement(btn)
	}

	fun goToMyInfoSidebarOption(name: String) {
		val btn = By.xpath(String.format(MYINFO_SIDEBAR_BTN, name))
		this.clickElement(btn)
	}

	fun verifyMyInfoHeaderTitle(expectedTitle: String) {
		val headerTitle = this.findElement(By.xpath(String.format(MYINFO_HEADER_TITLE, expectedTitle))).text
		Asserts.assertEquals(expectedTitle, headerTitle, "My Info header title")
	}

	fun verifyFieldsValues(fieldValues: Map<String, String>) {
		for((field, expectedValue) in fieldValues) {
			val by = By.xpath(String.format(MYINFO_JOB_FIELDS, field))
			val actualValue = this.getTextWithJavascript(by)
			Asserts.assertEquals(expectedValue, actualValue, "$field field")
		}
	}
}
