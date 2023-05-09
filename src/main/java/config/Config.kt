package config

import org.slf4j.LoggerFactory
import org.testng.Assert
import java.io.FileInputStream
import java.util.*

object Config {
	private const val fileName: String = "config.properties"
	private val properties = Properties()
	private val log = LoggerFactory.getLogger(this.javaClass)
	private lateinit var config: ConfigModel

	init { readConfig() }

	private fun readConfig() {
		try {
			log.info("Obteniendo configuracion")
			val fis = FileInputStream(fileName)
			properties.load(fis)
		} catch(e: Exception) {
			e.printStackTrace()
			Assert.fail("No se pudo leer el archivo $fileName")
		}
	}

	private fun get(key: String, default: String): String = properties.getProperty(key, default)

	fun getConfigData(): ConfigModel {
		if(!this::config.isInitialized) {
			val browser = this.get("browser", "chrome")
			val hidden = this.get("hidden", "false")
			val pageLoadTimeout = this.get("pageLoadTimeout", "20")
			val elementFindTimeout = this.get("elementFindTimeout", "8")

			config = ConfigModel(
				browser = browser,
				hidden = hidden.toBoolean(),
				pageLoadTimeout = pageLoadTimeout.toLong(),
				elementFindTimeout = elementFindTimeout.toInt()
			)

			log.info("Configuracion establecida:\n$config")
		}

		return config
	}
}
