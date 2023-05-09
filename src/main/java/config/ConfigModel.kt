package config

data class ConfigModel(val browser: String, val hidden: Boolean, val pageLoadTimeout: Long, val elementFindTimeout: Int) {
	override fun toString(): String {
		return String.format(
			"""
			-> Navegador: %s
			-> Esconder navegador: %s
			-> Tiempo maximo de espera para cargar la pagina (segundos): %s
			-> Tiempo maximo de espera para encontrar un elemento (segundos): %s
			""".trimIndent(),
			browser,
			hidden,
			pageLoadTimeout,
			elementFindTimeout
		)
	}
}