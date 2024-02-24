package core.config

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.SessionId
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.LoggerFactory
import org.testng.Assert
import java.time.Duration
import java.util.logging.Level

/**
 * Gestor principal del driver
 */
object DriverManager {

	private var driver: WebDriver? = null
	private var currentSession: SessionId? = null
	private var lastSession: SessionId? = null
	private val config = Config.data
	private val log = LoggerFactory.getLogger(this.javaClass)
	private val pageLoadTimeout = Duration.ofSeconds(config.pageLoadTimeout)

	/**
	 * Para crear un nuevo driver
	 */
	fun startDriver() {
		if(driver == null || hasQuit(driver!!)) driver = newDriver()
	}

	/**
	 * Para obtener el driver
	 */
	fun getDriver(): WebDriver {
		if(hasQuit(driver!!)) Assert.fail("Esta sesion ya esta terminada")
		if(currentSession == lastSession) Assert.fail("Usando driver de sesion pasada: $lastSession, sesion actual: $currentSession")
		log.info("Obteniendo driver")
		return driver!!
	}

	/**
	 * Para cerrar el driver
	 */
	fun closeDriver() {
		log.info("Cerrando driver - sessionID: $currentSession")
		driver?.quit()
		driver = null
		lastSession = currentSession
		currentSession = null
	}

	/**
	 * Para crear un nuevo driver
	 */
	private fun newDriver(): WebDriver {
		log.info("Creando driver")
		var thisDriver: WebDriver? = null
		when(config.browser) {
			"chrome" -> thisDriver = setupChromeDriver()
			"firefox" -> thisDriver = setupFirefoxDriver()
			"edge" -> thisDriver = setupEdgeDriver()
			else -> Assert.fail("${config.browser} no es un navegador configurado")
		}

		currentSession = (thisDriver as RemoteWebDriver).sessionId
		log.info("Driver creado - sessionID: $currentSession")
		thisDriver.manage().window().maximize()
		thisDriver.manage().timeouts().implicitlyWait(pageLoadTimeout)
		thisDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeout)
		waitUntilPageIsReady(thisDriver)
		return thisDriver
	}

	/**
	 * Para saber si el driver ya cerro su sesion
	 */
	private fun hasQuit(thisDriver: WebDriver): Boolean {
		return try {
			thisDriver.title
			false
		} catch(e: WebDriverException) {
			log.info(e.message)
			true
		}
	}

	/**
	 * Para crear un driver para Google Chrome
	 */
	private fun setupChromeDriver(): WebDriver {
		val opt = ChromeOptions()
		// val opt = chromeOptions()
		if(config.hidden) opt.addArguments("headless")
		opt.setPageLoadStrategy(PageLoadStrategy.NORMAL)
		return WebDriverManager.chromedriver().capabilities(opt).create()
	}

	/**
	 * Para crear un driver para Mozilla Firefox
	 */
	private fun setupFirefoxDriver(): WebDriver {
		val opt = FirefoxOptions()
		if(config.hidden) opt.addArguments("headless")
		opt.setPageLoadStrategy(PageLoadStrategy.NORMAL)
		return WebDriverManager.firefoxdriver().capabilities(opt).create()
	}

	/**
	 * Para crear un driver para Microsoft Edge
	 */
	private fun setupEdgeDriver(): WebDriver {
		val opt = EdgeOptions()
		if(config.hidden) opt.addArguments("headless")
		opt.setPageLoadStrategy(PageLoadStrategy.NORMAL)
		return WebDriverManager.edgedriver().capabilities(opt).create()
	}

	/**
	 * Para configurar opciones para el driver de Chrome
	 */
	private fun chromeOptions(): ChromeOptions {
		val chromeOpts = ChromeOptions()
		chromeOpts.addArguments("--window-size=1920,1080")
		chromeOpts.addArguments("--disable-gpu") // https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
		chromeOpts.addArguments("enable-automation") // https://stackoverflow.com/a/43840128/1689770
		chromeOpts.addArguments("--no-sandbox") // https://stackoverflow.com/a/50725918/1689770
		chromeOpts.addArguments("--disable-infobars") // https://stackoverflow.com/a/43840128/1689770
		chromeOpts.addArguments("--disable-dev-shm-usage") // https://stackoverflow.com/a/50725918/1689770
		chromeOpts.addArguments("--disable-browser-side-navigation") // https://stackoverflow.com/a/49123152/1689770
		chromeOpts.addArguments("--disable-gpu") // https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
		chromeOpts.addArguments("disable-features=NetworkService")
		chromeOpts.addArguments("--disable-extensions")
		chromeOpts.addArguments("--dns-prefetch-disable")
		chromeOpts.addArguments("--remote-allow-origins=*")
		chromeOpts.setPageLoadStrategy(PageLoadStrategy.NORMAL)
		val logPrefs = LoggingPreferences()
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL)
		chromeOpts.setCapability("goog:loggingPrefs", logPrefs)
		return chromeOpts
	}

	/**
	 * Para esperar a que la pagina termine de cargar completamente
	 */
	private fun waitUntilPageIsReady(driver: WebDriver) {
		val js = driver as JavascriptExecutor
		val w = WebDriverWait(driver, pageLoadTimeout)
		w.until { js.executeScript("return document.readyState") == "complete" }
	}
}
