package runners

import io.cucumber.testng.AbstractTestNGCucumberTests
import io.cucumber.testng.CucumberOptions

@CucumberOptions(
	tags = "",
	features = ["src/test/resources/features/hrm.feature"],
	glue = ["stepdefinitions"],
	plugin = []
)
class HrmRunnerTest : AbstractTestNGCucumberTests()
