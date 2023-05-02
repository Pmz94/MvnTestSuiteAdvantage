package pageactions

import driver.DriverManager
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.*

open class PageActions {

	private val driver: WebDriver = DriverManager.getDriver()

	protected fun findElement(by: String, l: String): WebElement {
		var crit = By.xpath(l)
		when(by.lowercase(Locale.getDefault()).trim { it <= ' ' }) {
			"xpath" -> crit = By.xpath(l)
			"id" -> crit = By.id(l)
			"name" -> crit = By.name(l)
			"classname" -> crit = By.className(l)
		}
		return driver.findElement(crit)
	}

	protected fun findElement(by: By): WebElement {
		return driver.findElement(by)
	}

	protected fun goBack() {
		driver.navigate().back()
	}

	protected fun delay() {
		delay(2);
	}

	/**
	 * delay Funcion para facilitar el Thread sleep
	 *
	 * @param segundos int
	 */
	protected fun delay(segundos: Int) {
		try {
			Thread.sleep(segundos * 1000L)
		} catch(e: InterruptedException) {
			e.printStackTrace()
		}
	}

	protected fun dismissAlert() {
		// Esperar a que salga el alert y hacerle dismiss
		delay(1)
		driver.switchTo().alert().accept()
	}

	protected fun print(texto: String) {
		println(texto)
	}

	protected open fun clearData(element: WebElement) {
		element.clear()
	}

	protected open fun inputData(locator: By, data: String) {
		val element: WebElement = this.findElement(locator)
		inputData(element, data)
	}

	protected open fun inputDataAndEnter(field: By, text: String) {
		val stateElement: WebElement = this.findElement(field)
		stateElement.clear()
		stateElement.sendKeys(text)
		stateElement.sendKeys(Keys.ENTER)
	}

	protected open fun inputData(element: WebElement, data: String) {
		element.clear()
		element.sendKeys(data)
	}

	protected fun byVisibility(locator: By, timeout: Int): WebElement {
		val msg = String.format("No se encontro el elemento %s en %s segundos", locator.toString(), timeout)
		return WebDriverWait(driver, Duration.ofSeconds(timeout.toLong())).withMessage(msg)
			.ignoring(StaleElementReferenceException::class.java)
			.until(ExpectedConditions.visibilityOfElementLocated(locator))
	}

	protected fun byPresence(locator: By, timeout: Int): WebElement {
		val msg = String.format("No se encontro el elemento %s en %s segundos", locator.toString(), timeout)
		return WebDriverWait(driver, Duration.ofSeconds(timeout.toLong())).withMessage(msg)
			.ignoring(StaleElementReferenceException::class.java)
			.until(ExpectedConditions.presenceOfElementLocated(locator))
	}
}
