package pageobjects

object PageObjectManager {

	val hrm = HrmPageObjects

	fun resetPageObjects() {
		hrm.reset()
	}
}