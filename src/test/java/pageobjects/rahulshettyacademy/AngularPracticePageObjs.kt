package pageobjects.rahulshettyacademy

import org.openqa.selenium.By
import core.pageactions.PageActions
import core.utilities.Asserts

class AngularPracticePageObjs: PageActions() {

	private val NAME_FIELD = By.name("name")
	private val EMAIL_FIELD = By.name("email")
	private val PASSWORD_FIELD = By.id("exampleInputPassword1")
	private val CHECKBOX = By.id("exampleCheck1")
	private val GENDER_COMBOBOX = By.id("exampleFormControlSelect1")
	private val RADIOBUTTONS_GROUP = "//label[@for='exampleFormControlRadio1']/..//label[text() = '%s']//..//input"
	private val BIRTHDATE_FIELD = By.xpath("//input[@type='date']")
	private val SUBMIT_BUTTON = By.xpath("//input[@type='submit']")
	private val ALERT_SUCCESS = By.xpath("//div[contains(@class, 'alert-success')]")
	private val textFieldsMap = HashMap<String, (String) -> Unit>()

	init {
		textFieldsMap["Name"] = { data -> this.inputData(NAME_FIELD, data) }
		textFieldsMap["Email"] = { data -> this.inputData(EMAIL_FIELD, data) }
		textFieldsMap["Password"] = { data -> this.inputData(PASSWORD_FIELD, data) }
		textFieldsMap["Date of Birth"] = { data -> this.inputData(BIRTHDATE_FIELD, data) }
	}

	fun inputToField(field: String, value: String) {
		textFieldsMap[field]?.let { it(value) }
	}

	fun checkTheCheckbox() {
		val checkbox = this.findElement(CHECKBOX)
		if(!checkbox.isSelected) checkbox.click()
	}

	fun selectFromCobobox(gender: String) {
		this.selectFromComboboxByText(GENDER_COMBOBOX, gender)
	}

	fun selectRadioButton(radiobutton: String) {
		val xpath = By.xpath(String.format(RADIOBUTTONS_GROUP, radiobutton))
		this.clickElement(xpath)
	}

	fun clickOnSubmit() {
		this.clickElement(SUBMIT_BUTTON)
	}

	fun verifyAlertMessage(expectedMessage: String) {
		val actualMessage = this.getTextFromElement(ALERT_SUCCESS)
		Asserts.assertTrue(actualMessage.contains(expectedMessage), "Alert message contains \"$expectedMessage\"")
	}
}