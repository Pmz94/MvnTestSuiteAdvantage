package runners

import io.cucumber.testng.AbstractTestNGCucumberTests
import io.cucumber.testng.CucumberOptions

@CucumberOptions(
	tags = "",
	features = ["src/test/resources/features/advantage.feature"],
	glue = ["stepdefinitions"],
	plugin = []
)
class AdvantageRunnerTest: AbstractTestNGCucumberTests()
