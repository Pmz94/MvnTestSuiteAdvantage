package stepdefinitions

import core.config.DriverManager
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import org.slf4j.LoggerFactory

class Hooks {

	private val log = LoggerFactory.getLogger(this.javaClass)

	@Before
	fun before(scenario: Scenario) {
		DriverManager.startDriver()
		log.info("Iniciando escenario: ${scenario.name}")
	}

	@After
	fun quit(scenario: Scenario) {
		DriverManager.closeDriver()
		if(scenario.isFailed) log.info("Escenario fallido")
		log.info("Escenario terminado: ${scenario.name}\n")
	}
}
