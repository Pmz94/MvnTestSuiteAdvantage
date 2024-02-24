package core.pageactions

import core.config.Config
import core.config.DriverManager
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.LoggerFactory
import org.testng.Assert
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Duration
import java.util.stream.Collectors

/**
 * Acciones que puedes realizar en una pagina
 */
abstract class PageActions {

	private var driver: WebDriver = DriverManager.getDriver()
	private val pageLoadTimeout = Config.data.pageLoadTimeout
	private val elementFindTimeout = Config.data.elementFindTimeout
	private val elementNotFoundMessage = "No se encontro el elemento %s en %s segundos"
	private val log = LoggerFactory.getLogger(this.javaClass)

	// BROWSER

	protected fun navigateTo(url: String) {
		checkIfBroken(url)
		if(getUrl() == "data:,") {
			driver.get(url)
		} else {
			driver.navigate().to(url)
		}
		waitUntilPageIsReady()
	}

	protected open fun checkIfBroken(url: String) {
		val statusCode = getLinkStatusCode(url)
		if(statusCode >= 400) {
			Assert.fail("Error $statusCode: No se encontro la pagina $url")
		}
	}

	protected fun isLinkBroken(url: String): Boolean {
		return getLinkStatusCode(url) >= 400
	}

	private fun getLinkStatusCode(url: String): Int {
		return try {
			val conn = URL(url).openConnection() as HttpURLConnection
			conn.setRequestMethod("HEAD")
			conn.connect()
			conn.getResponseCode()
		} catch(e: IOException) {
			Assert.fail(e.localizedMessage)
			0
		}
	}

	/**
	 * Para obtener el link actual de la pagina en la que andas
	 */
	protected fun getUrl(): String {
		return driver.currentUrl
	}

	/**
	 * Para obtener el titulo que tenga la pestaña en el navegador
	 */
	protected open fun getBrowserTitle(): String {
		return driver.title
	}

	/**
	 * Para regresar a la vista anterior en el navegador
	 */
	protected fun goBack() {
		driver.navigate().back()
	}

	protected fun refresh() {
		driver.navigate().refresh()
		this.delay(4)
	}

	protected fun getBrowserTabsCount(): Int {
		val tabCount: List<String> = driver.windowHandles.toList()
		return tabCount.size
	}

	// TABS

	protected fun switchToWindow(windowTitle: String) {
		var switched = false
		for(handle in driver.windowHandles) {
			driver.switchTo().window(handle)
			println("Window title: " + getBrowserTitle())
			if(this.getBrowserTitle().contains(windowTitle)) {
				switched = true
				break
			}
		}

		if(!switched) Assert.fail("No hay una ventana con el nombre '$windowTitle'")
	}

	// IFRAMES

	protected fun switchToFrame(nameOrId: String) {
		driver.switchTo().frame(nameOrId)
	}

	// ALERTS

	protected fun getTextFromAlert(): String {
		return driver.switchTo().alert().text
	}

	protected fun acceptAlert() {
		this.delay(1)
		driver.switchTo().alert().accept()
	}

	/**
	 * Para cerrar un alert del navegador
	 */
	protected fun dismissAlert() {
		// Esperar a que salga el alert y hacerle dismiss
		this.delay(1)
		driver.switchTo().alert().accept()
	}

	// DELAY

	/**
	 * Para facilitar el Thread sleep
	 * @param segundos que hay que esperar
	 */
	protected fun delay(segundos: Int = 2) {
		try {
			Thread.sleep(segundos * 1000L)
		} catch(e: InterruptedException) {
			log.error(e.message)
		}
	}

	// ACTIONS

	/**
	 * Para borrar lo que haya en un <input>
	 * @param locator Ubica el elemento en la pagina
	 */
	protected open fun clearData(locator: By) {
		this.clearData(this.findByVisibility(locator))
	}

	/**
	 * Para borrar lo que haya en un <input>
	 * @param element al que se le hara la accion
	 */
	protected open fun clearData(element: WebElement) {
		val s = Keys.chord(Keys.CONTROL, "a")
		element.sendKeys(s, Keys.BACK_SPACE)
	}

	/**
	 * Para poner texto en un elemento que lo reciba
	 * @param locator Ubica el elemento en la pagina
	 * @param data Lo que se va escribir en el elemento
	 */
	protected open fun inputData(locator: By, data: String) {
		this.inputData(this.findByVisibility(locator), data)
	}

	/**
	 * Para poner texto en un elemento que lo reciba
	 * @param element al que se le va agregar texto
	 * @param data Lo que se va escribir en el elemento
	 */
	protected open fun inputData(element: WebElement, data: String) {
		this.clearData(element)
		element.sendKeys(data)
	}

	/**
	 * Para poner texto en un elemento y luego simular que se presiona la tecla enter
	 * @param locator Ubica el elemento en la pagina
	 * @param data Lo que se va escribir en el elemento
	 */
	protected open fun inputDataAndEnter(locator: By, data: String) {
		val element: WebElement = this.findByVisibility(locator)
		this.inputData(element, data)
		element.sendKeys(Keys.ENTER)
	}

	/**
	 * Para poner texto en un elemento y luego simular que se presiona la tecla tab
	 * @param locator Ubica el elemento en la pagina
	 * @param data Lo que se va escribir en el elemento
	 */
	protected open fun inputDataAndTab(locator: By, data: String) {
		val element: WebElement = this.findByVisibility(locator)
		this.inputData(element, data)
		element.sendKeys(Keys.TAB)
	}

	protected fun inputDataAndSelectOptionFromTypeahead(locator: By, data: String, typeahead: By, index: Int = 0) {
		val element = this.findByVisibility(locator)
		// Clear current data from textfield
		this.clearData(element)
		// Click on textfield
		val js = driver as JavascriptExecutor
		js.executeScript("arguments[0].click();", element)
		// Write into textfield
		element.sendKeys(data)
		this.waitUntilVisible(typeahead)
		// Go to first option
		element.sendKeys(Keys.DOWN)
		// Go to specified option by index
		var timesDown = 0
		while(timesDown != index) {
			element.sendKeys(Keys.DOWN)
			timesDown++
		}
		// Select option
		element.sendKeys(Keys.ENTER)
	}

	protected fun inputDataAndSelectOptionFromTypeahead(
		locator: By,
		data: String,
		typeaheadOptions: By,
		contains: String
	) {
		val element = this.findByVisibility(locator)
		// Clear current data from textfield
		this.clearData(element)
		// Click on textfield
		val js = driver as JavascriptExecutor
		js.executeScript("arguments[0].click();", element)
		// Write into textfield
		element.sendKeys(data)
		this.waitUntilVisible(typeaheadOptions)
		val list = this.findElements(typeaheadOptions)
		val act = Actions(driver)
		for(listItem in list) {
			if(listItem.text.contains(contains)) {
				act.moveToElement(listItem).click().build().perform()
				break
			}
		}
	}

	/**
	 * Para llenar los campos especificados con sus respectivos valores
	 * @param inputMap Nombres de campos y valores
	 * @param textFieldsMap Donde se van a buscar los campos para ponerle sus valores
	 */
	fun fillUpTextFields(inputMap: Map<String, String>, textFieldsMap: HashMap<String, (String) -> Unit>) {
		inputMap.forEach { (field, data) -> textFieldsMap[field]?.let { it(data) } }
	}

	/**
	 * Para escoger una opcion de un combobox que tenga el texto especificado
	 * @param locator Ubica el elemento en la pagina
	 * @param text que se buscara en el combobox
	 */
	protected open fun selectFromComboboxByText(locator: By, text: String) {
		val combobox = Select(this.findByVisibility(locator))
		combobox.selectByVisibleText(text)
	}

	/**
	 * Para dar click un elemento hasta 3 veces
	 * Sometimes helps with StaleState and ClickIntercept
	 * @param locator Ubica el elemento en la pagina
	 */
	protected fun clickElement(locator: By) {
		this.clickElement(this.findByClickable(locator))
	}

	/**
	 * Para dar click un elemento hasta 3 veces
	 * Sometimes helps with StaleState and ClickIntercept
	 * @param element al que se le va dar click
	 * @param attempt Numero de intentos que dara click
	 */
	protected open fun clickElement(element: WebElement, attempt: Int = 0) {
		try {
			element.click()
		} catch(e: Exception) {
			if(attempt < 2) {
				this.clickElement(element, attempt + 1)
			} else {
				throw e
			}
		}
	}

	protected fun clickWithJavaScript(locator: By) {
		this.clickWithJavaScript(this.findByPresence(locator))
	}

	protected open fun clickWithJavaScript(element: WebElement) {
		val js = driver as JavascriptExecutor
		js.executeScript("arguments[0].click();", element)
	}

	/**
	 * Para simular que pones el cursor encima de un elemento
	 * @param locator Ubica el elemento en la pagina
	 */
	protected open fun hoverOver(locator: By) {
		this.hoverOver(this.findByVisibility(locator))
	}

	/**
	 * Para simular que pones el cursor encima de un elemento
	 * @param element al que se le pasara el cursor por encima
	 */
	protected open fun hoverOver(element: WebElement) {
		val actions = Actions(driver)
		actions.moveToElement(element).perform()
	}

	protected fun getAttribute(locator: By, attribute: String): String {
		return this.findByPresence(locator).getAttribute(attribute)
	}

	/**
	 * Para obtener el texto de un elemento
	 * @param locator Ubica el elemento en la pagina
	 */
	protected open fun getTextFromElement(locator: By): String {
		return this.findByVisibility(locator).text
	}

	/**
	 * Para obtener el texto de un elemento con javascript
	 * @param locator Ubica el elemento en la pagina
	 */
	protected open fun getTextWithJavascript(locator: By): String {
		val list = this.getTextListWithJavascript(locator)
		if(list.isEmpty()) throw NoSuchElementException("No se encontro el elemento con $locator")
		return list[0]
	}

	/**
	 * Para obtener los textos en un elemento con javascript
	 * @param locator Ubica el elemento en la pagina
	 */
	protected open fun getTextListWithJavascript(locator: By): List<String> {
		this.findByPresence(locator)
		var byType = ""
		if(locator.toString().startsWith("By.xpath: ")) byType = "xpath"
		else if(locator.toString().startsWith("By.cssSelector: ")) byType = "cssSelector"
		Assert.assertNotEquals(byType, "", "Tipo de locator By desconocido: $locator")
		val xpath = locator.toString().replaceFirst("By.$byType: ", "")

		val js = driver as JavascriptExecutor
		val jsScript = "return getTextList(arguments[0], arguments[1]); ${getTextListScript()}"
		val arrTexts = js.executeScript(jsScript, byType, xpath)

		return if(arrTexts is List<*>) {
			val a: List<String> = arrTexts.filterIsInstance<String>()
			a.stream().map { o: String -> o.trim() }.collect(Collectors.toList())
			a
		} else {
			listOf()
		}
	}

	protected fun getSelectedTextFromCombobox(locator: By): String {
		return this.getSelectedTextFromCombobox(this.findByVisibility(locator))
	}

	protected open fun getSelectedTextFromCombobox(element: WebElement): String {
		val select = Select(element)
		return select.firstSelectedOption.text
	}

	// IS

	/**
	 * Para ver si un elemento es visible en la pagina
	 * @param locator Ubica el elemento en la pagina
	 * @param timeout Segundos que tardara en ver si es visible
	 */
	protected fun isPageObjectVisible(locator: By, timeout: Int = 5): Boolean {
		try {
			this.findByVisibility(locator, timeout)
		} catch(e: Exception) {
			return false
		}
		return true
	}

	/**
	 * Para ver si un elemento existe en la pagina
	 * independientemente de que se pueda ver a simple vista
	 * @param locator Ubica el elemento en la pagina
	 * @param timeout Segundos que tardara en ver si existe
	 */
	protected fun isPageObjectPresent(locator: By, timeout: Int = 5): Boolean {
		try {
			this.findByPresence(locator, timeout)
		} catch(e: Exception) {
			return false
		}
		return true
	}

	// FINDS

	/**
	 * Para buscar un elemento en la pagina
	 * @param locator Ubica el elemento en la pagina
	 */
	protected fun findAnyElement(locator: By): WebElement {
		return driver.findElement(locator)
	}

	/**
	 * Para buscar un elemento en la pagina
	 * @param locator Ubica el elemento en la pagina
	 */
	protected fun findElement(locator: By): WebElement {
		return this.findByVisibility(locator)
	}

	/**
	 * Para buscar varios elementos en la pagina
	 * @param locator Ubica el elemento en la pagina
	 */
	protected open fun findElements(locator: By): List<WebElement> {
		try {
			this.findByPresence(locator)
		} catch(e: Exception) {
			return listOf()
		}
		return driver.findElements(locator)
	}

	protected fun findElements(locator: By, inside: By): List<WebElement> {
		return try {
			val parentElement = this.findByPresence(inside)
			parentElement.findElements(locator)
		} catch(e: Exception) {
			listOf()
		}
	}

	protected open fun findByVisibility(locator: By, timeout: Int = elementFindTimeout): WebElement {
		val timeoutDuration = Duration.ofSeconds(timeout.toLong())
		val msg = String.format(elementNotFoundMessage, locator.toString(), timeout)
		val w = WebDriverWait(driver, timeoutDuration)
		w.withMessage(msg).ignoring(StaleElementReferenceException::class.java)
		return w.until(ExpectedConditions.visibilityOfElementLocated(locator))
	}

	protected open fun findByPresence(locator: By, timeout: Int = elementFindTimeout): WebElement {
		val timeoutDuration = Duration.ofSeconds(timeout.toLong())
		val msg = String.format(elementNotFoundMessage, locator.toString(), timeout)
		val w = WebDriverWait(driver, timeoutDuration)
		w.withMessage(msg).ignoring(StaleElementReferenceException::class.java)
		return w.until(ExpectedConditions.presenceOfElementLocated(locator))
	}

	protected open fun findByClickable(locator: By, timeout: Int = elementFindTimeout): WebElement {
		val timeoutDuration = Duration.ofSeconds(timeout.toLong())
		val msg = String.format(elementNotFoundMessage, locator.toString(), timeout)
		val w = WebDriverWait(driver, timeoutDuration)
		w.withMessage(msg).ignoring(StaleElementReferenceException::class.java)
		return w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)))
	}

	// WAITS

	protected open fun waitUntilInvisible(locator: By, timeout: Int = elementFindTimeout) {
		val timeoutDuration = Duration.ofSeconds(timeout.toLong())
		val msg = String.format(elementNotFoundMessage, locator.toString(), timeout)
		val w = WebDriverWait(driver, timeoutDuration)
		w.withMessage(msg).ignoring(StaleElementReferenceException::class.java)
		w.until(ExpectedConditions.invisibilityOfElementLocated(locator))
	}

	protected open fun waitUntilVisible(locator: By, timeout: Int = elementFindTimeout) {
		this.findByVisibility(locator, timeout)
	}

	protected open fun waitUntilPresent(locator: By, timeout: Int = elementFindTimeout) {
		this.findByPresence(locator, timeout)
	}

	protected open fun waitUntilClickable(locator: By, timeout: Int = elementFindTimeout) {
		this.findByClickable(locator, timeout)
	}

	/**
	 * Para esperar a que la pagina termine de cargar completamente
	 */
	protected fun waitUntilPageIsReady() {
		val timeoutDuration = Duration.ofSeconds(pageLoadTimeout)
		val js = driver as JavascriptExecutor
		val w = WebDriverWait(driver, timeoutDuration)
		w.until { js.executeScript("return document.readyState") == "complete" }
	}

	protected fun waitForAlert() {
		val timeoutDuration = Duration.ofSeconds(pageLoadTimeout)
		val w = WebDriverWait(driver, timeoutDuration)
		w.until(ExpectedConditions.alertIsPresent())
	}

	// Funciones totalmente privadas solo para esta clase

	/**
	 * Para obtener el javascript que obtiene el texto de los elementos
	 */
	private fun getTextListScript(): String {
		val filePath = "src/main/resources/getTextList.js"
		return try {
			String(Files.readAllBytes(Paths.get(filePath)))
		} catch(e: IOException) {
			Assert.fail("No se pudo leer el archivo $filePath")
			""
		}
	}
}
