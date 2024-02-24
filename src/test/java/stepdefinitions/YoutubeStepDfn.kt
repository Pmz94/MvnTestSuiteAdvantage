package stepdefinitions

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import pageobjects.YoutubePageObjs

class YoutubeStepDfn {

	private val pageObjects = YoutubePageObjs()

	@When("I search for {string}")
	fun iSearchFor(search: String) {
		pageObjects.search(search)
	}

	@Then("I verify I'm on the search results page for {string}")
	fun iVerifyImOnTheSearchResultsPage(search: String) {
		pageObjects.verifySearchPage(search)
	}
}