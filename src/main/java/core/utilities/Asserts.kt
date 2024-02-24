package core.utilities

import org.slf4j.LoggerFactory
import org.testng.Assert

object Asserts {

	private val log = LoggerFactory.getLogger(this.javaClass)
	private const val DELTA: Float = 0.01F

	fun fail(message: String) {
		Assert.fail(message)
	}

	fun assertEquals(expected: Any, actual: Any, label: String = "Value equals") {
		val msg = "$label, Expected: '$expected', Actual: '$actual'"
		log.info(msg)
		Assert.assertEquals(actual, expected, label)
	}

	fun assertEquals(expected: Number,actual: Number, label: String = "Value equals") {
		val msg = "$label, Expected: $expected, Actual: $actual"
		log.info(msg)
		Assert.assertEquals(actual.toFloat(), expected.toFloat(), DELTA, label)
	}

	fun assertTrue(condition: Boolean, label: String = "Condition") {
		log.info("$label, expected to be true")
		Assert.assertTrue(condition, label)
	}

	fun assertFalse(condition: Boolean, label: String = "Condition") {
		log.info("$label, expected to be false")
		Assert.assertFalse(condition, label)
	}

	fun assertTextContains(expected: String, actual: String, label: String = "Value") {
		val msg = "$label text expected to contain '$expected' inside '$actual'"
		log.info(msg)
		Assert.assertTrue(actual.contains(expected), msg)
	}
}
