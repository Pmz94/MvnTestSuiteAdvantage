package pageobjects

class PageObjectManager {

	private var pageObjectManager: PageObjectManager? = null
	var hrm = HrmPageObjects()

	companion object Factory {
		fun getInstance(): PageObjectManager = PageObjectManager()
	}

	fun resetPageObjects() {
		hrm.reset()
	}
}
