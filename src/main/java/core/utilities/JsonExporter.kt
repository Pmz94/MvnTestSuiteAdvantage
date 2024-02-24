package core.utilities

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.apache.commons.text.StringEscapeUtils
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import others.CustomerDetails
import java.io.File
import java.io.FileWriter

class JsonExporter {
	private val basepath: String = System.getProperty("user.dir")
	private val jsonfiles = "$basepath\\src\\test\\resources\\jsonfiles"

	fun export(a: Array<Any>, fileName: String = "singleJson") {
		val js = JSONArray()

		for(i in a.indices) {
			val g = Gson()
			val jsonString = g.toJson(a[i])
			js.add(jsonString)
		}

		val jo = JSONObject()
		jo["data"] = js
		val unescapeString = StringEscapeUtils.unescapeJava(jo.toJSONString())
		val finalString = unescapeString.replace("\"{", "{").replace("}\"", "}")

		FileWriter("$jsonfiles\\${fileName}.json").use { file -> file.write(finalString) }
	}

	fun import(filePath: String) {
		val basepath: String = System.getProperty("user.dir")
		val jsonfiles = "$basepath\\src\\test\\resources\\jsonfiles"

		val o = ObjectMapper()
		val file = "$jsonfiles\\${filePath}.json"
		val courseName: CustomerDetails = o.readValue(File(file), CustomerDetails::class.java)
		println(courseName.courseName)
	}
}