package pageactions

import driver.DriverManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.util.*

class PageActions {

	private val driver: WebDriver

	init {
		driver = DriverManager.getDriver()
	}

	protected fun findElement(by: String, l: String?): WebElement {
		var crit = By.xpath(l)
		when(by.lowercase(Locale.getDefault()).trim { it <= ' ' }) {
			"xpath" -> crit = By.xpath(l)
			"id" -> crit = By.id(l)
			"name" -> crit = By.name(l)
			"classname" -> crit = By.className(l)
		}
		return driver.findElement(crit)
	}

	protected fun goBack() {
		driver.navigate().back()
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

	protected fun print(texto: String?) {
		println(texto)
	}
}
