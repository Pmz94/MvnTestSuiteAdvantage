package stepdefinitions

import config.DriverManager
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.testng.Assert

class HrmStepDfn {

	private val driver: WebDriver = DriverManager.getDriver()

	@Then("I make sure I am on HRMLogin page")
	fun iMakeSureIAmOnHRMLoginPage() {
		val title = driver.findElement(By.xpath("//h5[contains(@class, 'orangehrm-login-title')]"))
		Assert.assertEquals(title.text, "Login")
	}

	@When("User enters username as {string} and password as {string}")
	fun goToHomePage(userName: String, passWord: String) {
		// login to application
		driver.findElement(By.name("username")).sendKeys(userName)
		driver.findElement(By.name("password")).sendKeys(passWord)
		driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).submit()
		// go the next page
	}

	@Then("User should be able to login sucessfully and new page open")
	fun verifyLogin() {
		val homePageHeading = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[1]/div/p")).text
		// Verify new page - HomePage
		Assert.assertEquals(homePageHeading, "Time at Work")
	}

	@Then("User should be able to see error message {string}")
	fun verifyErrorMessage(expectedErrorMessage: String) {
		val actualErrorMessage = driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")).text
		// Verify Error Message
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage)
	}
}
