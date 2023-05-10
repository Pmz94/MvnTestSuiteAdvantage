package pageactions

import config.Config
import config.DriverManager
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.*

open class PageActions {

	private val driver: WebDriver = DriverManager.getDriver()
	private val elementFindTimeout = Config.getConfigData().elementFindTimeout

	/**
	 * Para cerrar un alert del navegador
	 */
	protected fun dismissAlert() {
		// Esperar a que salga el alert y hacerle dismiss
		this.delay(1)
		driver.switchTo().alert().accept()
	}

	/**
	 * Para regresar a la vista anterior en el navegador
	 */
	protected fun goBack() {
		driver.navigate().back()
	}

	/**
	 * Para facilitar el Thread sleep
	 *
	 * @param segundos que hay que esperar
	 */
	protected fun delay(segundos: Int = 2) {
		try {
			Thread.sleep(segundos * 1000L)
		} catch(e: InterruptedException) {
			e.printStackTrace()
		}
	}

	protected open fun clearData(locator: By) {
		this.clearData(this.findByVisibility(locator))
	}

	protected open fun clearData(element: WebElement) {
		element.clear()
	}

	protected open fun inputData(locator: By, data: String) {
		this.inputData(this.findByVisibility(locator), data)
	}

	protected open fun inputData(element: WebElement, data: String) {
		element.clear()
		element.sendKeys(data)
	}

	protected open fun inputDataAndEnter(field: By, text: String) {
		val stateElement: WebElement = this.findByVisibility(field)
		stateElement.clear()
		stateElement.sendKeys(text)
		stateElement.sendKeys(Keys.ENTER)
	}

	fun fillUpTextFields(inputMap: Map<String, String>, textFieldsMap: HashMap<String, (String) -> Unit>) {
		inputMap.forEach { (field, data) -> textFieldsMap[field]?.let { it(data) } }
	}

	protected fun clickElement(by: By) {
		this.clickElement(this.findByClickable(by))
	}

	/**
	 * Attempts to click on element up to 3 times
	 * Sometimes helps with StaleState and ClickIntercept
	 *
	 * @param element WebElement
	 * @param attempt number of attempts tried
	 */
	protected open fun clickElement(element: WebElement, attempt: Int = 0) {
		try {
			element.click()
		} catch(e: Exception) {
			if(attempt < 2) {
				clickElement(element, attempt + 1)
			} else {
				throw e
			}
		}
	}

	protected fun isPageObjectVisible(locator: By, timeout: Int = 5): Boolean {
		try {
			this.findByVisibility(locator, timeout)
		} catch(e: Exception) {
			return false
		}
		return true
	}

	protected fun isPageObjectPresent(locator: By, timeout: Int = 5): Boolean {
		try {
			this.findByPresence(locator, timeout)
		} catch(e: Exception) {
			return false
		}
		return true
	}

	protected fun findAnyElement(by: By): WebElement {
		return driver.findElement(by)
	}

	protected fun findElement(locator: By): WebElement {
		return this.findByVisibility(locator)
	}

	protected open fun findElements(locator: By, timeout: Int): List<WebElement> {
		try {
			this.findByPresence(locator, timeout)
		} catch(e: Exception) {
			return ArrayList()
		}
		return driver.findElements(locator)
	}

	protected fun findByVisibility(locator: By, timeout: Int = elementFindTimeout): WebElement {
		val msg = String.format("No se encontro el elemento %s en %s segundos", locator.toString(), timeout)
		return WebDriverWait(driver, Duration.ofSeconds(timeout.toLong())).withMessage(msg)
			.ignoring(StaleElementReferenceException::class.java)
			.until(ExpectedConditions.visibilityOfElementLocated(locator))
	}

	protected fun findByPresence(locator: By, timeout: Int = elementFindTimeout): WebElement {
		val msg = String.format("No se encontro el elemento %s en %s segundos", locator.toString(), timeout)
		return WebDriverWait(driver, Duration.ofSeconds(timeout.toLong())).withMessage(msg)
			.ignoring(StaleElementReferenceException::class.java)
			.until(ExpectedConditions.presenceOfElementLocated(locator))
	}

	protected fun findByClickable(locator: By, timeout: Int = elementFindTimeout): WebElement {
		val msg = String.format("No se encontro el elemento %s en %s segundos", locator.toString(), timeout)
		return WebDriverWait(driver, Duration.ofSeconds(timeout.toLong())).withMessage(msg)
			.ignoring(StaleElementReferenceException::class.java)
			.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)))
	}
}
