package others

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.apache.commons.text.StringEscapeUtils
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.sql.*

fun main() {
	val guias = "--------------------"
	val basepath: String = System.getProperty("user.dir")
	val jsonfiles = "$basepath\\src\\test\\resources\\jsonfiles"

	Class.forName("com.mysql.cj.jdbc.Driver")

	try {
		println("Conectandose a la BD")
		val conn: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/business", "root", "")

		val st = conn.createStatement()
		val query = "SELECT * FROM customerinfo WHERE PurchasedDate <= CURDATE() AND Location = 'Asia';"
		println("Ejecutando query:")
		println(query)
		val rs: ResultSet = st.executeQuery(query)
		var a = arrayOf<CustomerDetails>()
		val js = JSONArray()

		while(rs.next()) {
			val c = CustomerDetails()
			c.courseName = rs.getString(1)
			c.purchaseDate = rs.getString(2)
			c.amount = rs.getInt(3)
			c.location = rs.getString(4)
			a += c

			if(rs.isFirst) println(guias)
			println(c.courseName)
			println(c.purchaseDate)
			println(c.amount)
			println(c.location)
			println(guias)
		}

		for(i in a.indices) {
			val oj = ObjectMapper()
			val file = "$jsonfiles\\customerInfo$i.json"
			oj.writeValue(File(file), a[i])
			val g = Gson()
			val jsonString = g.toJson(a[i])
			js.add(jsonString)
		}

		val jo = JSONObject()
		jo["data"] = js
		val unescapeString = StringEscapeUtils.unescapeJava(jo.toJSONString())
		val finalString = unescapeString.replace("\"{", "{").replace("}\"", "}")
		println(finalString)

		FileWriter("$jsonfiles\\singleJson.json").use { file -> file.write(finalString) }

		conn.close()
	} catch(ex: SQLException) {
		println("No se pudo conectar a la BD")
	} catch(ex: IOException) {
		println(ex.message)
	}
}