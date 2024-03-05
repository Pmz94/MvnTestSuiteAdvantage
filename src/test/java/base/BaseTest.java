package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.simple.SimpleLogger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    public Logger log = LoggerFactory.getLogger(BaseTest.class);

    /**
     * setup
     * Funcion que setea el driver
     * @param browser String
     * @param url String
     */
    @Parameters({"browser", "url"})
    @BeforeClass
    public void setup(
        @Optional("chrome") String browser,
        @Optional("https://advantageonlineshopping.com") String url
    ) {
        // Configuracion de log
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "debug");
        System.setProperty(SimpleLogger.LOG_FILE_KEY, "System.out");
        log.info("defaultLogLevel:" + System.getProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY));
        log.info("logFile:" + System.getProperty(SimpleLogger.LOG_FILE_KEY));

        // Configuracion de WebDriver
        browser = System.getProperty("browser", browser);
        String cachePath = "driverCache";
        driver = switch (browser.toLowerCase()) {
            case "chrome" -> WebDriverManager.chromedriver().cachePath(cachePath).create();
            case "firefox" -> WebDriverManager.firefoxdriver().cachePath(cachePath).create();
            case "edge" -> WebDriverManager.edgedriver().cachePath(cachePath).create();
            default -> throw new InvalidArgumentException("Invalid browser: " + browser);
        };

        // No mezclar implicit y explicit waits
        // https://www.selenium.dev/documentation/webdriver/waits/#implicit-waits
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Configuracion de resolucion
        String resolution = System.getProperty("resolution", "1366x768");
        if (resolution.equalsIgnoreCase("fullscreen") || resolution.equalsIgnoreCase("maximize")) {
            driver.manage().window().maximize();
        } else {
            String[] res = resolution.split("x");
            Dimension d = new Dimension(Integer.parseInt(res[0]), Integer.parseInt(res[1]));
            driver.manage().window().setSize(d);
        }

        log.info("Navigating to: " + url);
        driver.get(url);
    }

    @AfterClass
    public void teardown() {
        delay(3);
        driver.quit();
    }

    /**
     * delay Funcion para facilitar el Thread sleep
     * @param segundos int
     */
    public void delay(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            print(e.toString());
        }
    }

    public void print(String texto) {
        log.info(texto);
    }

    public WebElement findElement(String by, String l) {
        By crit = By.xpath(l);
        switch (by.toLowerCase().trim()) {
            case "xpath" -> crit = By.xpath(l);
            case "id" -> crit = By.id(l);
            case "name" -> crit = By.name(l);
            case "classname" -> crit = By.className(l);
        }
        return driver.findElement(crit);
    }
}