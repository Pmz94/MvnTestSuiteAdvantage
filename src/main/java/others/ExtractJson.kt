package others

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

fun main() {
	val basepath: String = System.getProperty("user.dir")
	val jsonfiles = "$basepath\\src\\test\\resources\\jsonfiles"

	val o = ObjectMapper()
	val file = "$jsonfiles\\customerInfo0.json"
	val courseName: CustomerDetails = o.readValue(File(file), CustomerDetails::class.java)
	println(courseName.courseName)
}