package core.config

import org.slf4j.LoggerFactory
import org.testng.Assert
import java.io.FileInputStream
import java.util.Properties

object Config {
	private const val fileName: String = "config.properties"
	private val properties = Properties()
	private lateinit var configModel: ConfigModel
	private val log = LoggerFactory.getLogger(this.javaClass)
	val data: ConfigModel = getConfigData()

	init { readConfig() }

	private fun readConfig() {
		try {
			log.info("Obteniendo configuracion")
			val fis = FileInputStream(fileName)
			properties.load(fis)
		} catch(e: Exception) {
			log.error(e.localizedMessage)
			Assert.fail("No se pudo leer el archivo $fileName")
		}
	}

	private fun get(key: String, default: String): String = properties.getProperty(key, default)

	private fun getConfigData(): ConfigModel {
		if(!this::configModel.isInitialized) {
			val browser = get("browser", "chrome")
			val hidden = get("hidden", "false")
			val pageLoadTimeout = get("pageLoadTimeout", "20")
			val elementFindTimeout = get("elementFindTimeout", "8")

			configModel = ConfigModel(
				browser = browser,
				hidden = hidden.toBoolean(),
				pageLoadTimeout = pageLoadTimeout.toLong(),
				elementFindTimeout = elementFindTimeout.toInt()
			)

			log.info("Configuracion establecida:\n$configModel")
		}

		return configModel
	}
}
