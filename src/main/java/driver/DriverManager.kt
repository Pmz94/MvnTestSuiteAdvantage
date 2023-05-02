package driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.slf4j.LoggerFactory
import org.testng.Assert
import java.time.Duration
import java.util.logging.Level

object DriverManager {

	private lateinit var driver: WebDriver
	private const val browser = "chrome"
	private const val hidden = false
	private val maxTimeout = Duration.ofSeconds(10)
	private val log = LoggerFactory.getLogger(this.javaClass)

	/**
	 * Funcion notNull como la de swift
	 * val someString: String? = null
	 * someString.notNull { doSomething(it) }
	 *
	 * fun doSomething(param: String) {
	 *     // do your stuff here
	 * }
	 * */
	fun <T: Any> T?.notNull(f: (it: T) -> Unit) {
		if(this != null) f(this)
	}

	private fun newDriver() {
		log.info("Creando driver")

		when(browser) {
			"chrome" -> {
				val opt = ChromeOptions()
				if(hidden) opt.addArguments("headless")
				WebDriverManager.chromedriver().setup()
				driver = ChromeDriver(opt)
				// driver = WebDriverManager.chromedriver().capabilities(chromeOptions()).create()
			}

			"firefox" -> {
				val opt = FirefoxOptions()
				if(hidden) opt.addArguments("headless")
				WebDriverManager.firefoxdriver().setup()
				driver = FirefoxDriver(opt)
			}

			else -> Assert.fail("$browser no es un navegador configurado")
		}

		driver.manage().window().maximize()
		// driver.manage().timeouts().pageLoadTimeout(maxTimeout)
		driver.manage().timeouts().implicitlyWait(maxTimeout)
	}

	private fun hasQuit(): Boolean {
		return try {
			driver.title
			false
		} catch(e: WebDriverException) {
			true
		}
	}

	@JvmStatic
	fun getDriver(): WebDriver {
		if(!this::driver.isInitialized || hasQuit()) newDriver()
		log.info("Obteniendo driver")
		return driver
	}

	fun resetDriver() {
		if(this::driver.isInitialized) {
			log.info("Cerrando driver")
			driver.quit()
		}
	}

	private fun chromeOptions(): ChromeOptions {
		val chromeOpts = ChromeOptions()
		chromeOpts.addArguments("--window-size=1920,1080")
		chromeOpts.addArguments("--disable-gpu") //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
		chromeOpts.addArguments("enable-automation") // https://stackoverflow.com/a/43840128/1689770
		chromeOpts.addArguments("--no-sandbox") //https://stackoverflow.com/a/50725918/1689770
		chromeOpts.addArguments("--disable-infobars") //https://stackoverflow.com/a/43840128/1689770
		chromeOpts.addArguments("--disable-dev-shm-usage") //https://stackoverflow.com/a/50725918/1689770
		chromeOpts.addArguments("--disable-browser-side-navigation") //https://stackoverflow.com/a/49123152/1689770
		chromeOpts.addArguments("--disable-gpu") //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
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
}