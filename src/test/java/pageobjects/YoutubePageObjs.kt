package pageobjects

import core.pageactions.PageActions
import core.utilities.Asserts
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class YoutubePageObjs: PageActions() {

	private val SEARCH_INPUT = By.xpath("//input[@id='search']")
	private val SEARCH_BTN = By.xpath("//button[@id='search-icon-legacy']")
	private val SEARCH_VIDEO_RESULT = By.xpath("//div[@id='contents']//ytd-video-renderer")

	fun search(search: String) {
		this.inputData(SEARCH_INPUT, search)
		this.clickElement(SEARCH_BTN)
	}

	fun verifySearchPage(expectedTitle: String) {
		this.waitUntilPageIsReady()
		val url = this.getUrl()
		val expectedParam = expectedTitle.replace(" ", "+")
		val expectedEndpoint = "/results?search_query=$expectedParam"
		Asserts.assertTextContains(expectedEndpoint, url, "Browser title")
	}

	fun playVideo(position: Int) {
		val videos: List<WebElement> = this.findElements(SEARCH_VIDEO_RESULT)
		val video: WebElement = videos[position]
		this.clickElement(video)
	}
}