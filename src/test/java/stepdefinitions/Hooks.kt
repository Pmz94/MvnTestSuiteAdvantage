package stepdefinitions

import driver.DriverManager
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import org.slf4j.LoggerFactory
import pageobjects.PageObjectManager

class Hooks {

	private val log = LoggerFactory.getLogger(this.javaClass)

	@Before
	fun before(scenario: Scenario) {
		DriverManager.getDriver()
		log.info("Iniciando escenario: ${scenario.name}")
	}

	@After
	fun quit(scenario: Scenario) {
		if(scenario.isFailed) log.info("Escenario fallido")
		DriverManager.resetDriver()
		PageObjectManager.resetPageObjects()
		log.info("Escenario terminado: ${scenario.name}\n")
	}
}
