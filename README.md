# Casos de pruebas en pagina web

Proyecto de pruebas de casos de uso de https://www.advantageonlineshopping.com

## Hecho con:

- TestNG
- Selenium

## Funciones

### delay(int) -> void
Funcion mas practica que `Thread.sleep()` ya que no necesita estar rodeada de un try-catch

### print(string) -> void
Funcion mas corta que `System.out.println()`

### findElement(string, string) -> WebElement
Funcion para obtener un elemento web especificando bajo que criterio.

Buscar por:
- Xpath: `findElement("xpath", "//*[@type='text']")`
- Id: `findElement("id", "identificacion")`
- Name: `findElement("name", "identificacion")`
- Class Name: `findElement("className", "identificacion")`