package stepdefinitions

import core.config.DriverManager
import core.utilities.Asserts
import io.cucumber.java.en.And
import io.cucumber.java.en.Given

class BaseStepDfn {

	private val driver = DriverManager.getDriver()

	@Given("I'm on page {string}")
	fun goToPage(url: String) {
		driver.navigate().to(url)
	}

	@And("Wait {int} seconds")
	fun wait(seconds: Int) {
		try {
			Thread.sleep(seconds * 1000L)
		} catch(e: InterruptedException) {
			Asserts.fail("No se pudo esperar")
		}
	}
}