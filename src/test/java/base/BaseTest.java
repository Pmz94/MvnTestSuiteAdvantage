package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

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
        String cachePath = "driverCache";
        driver = switch (browser.toLowerCase()) {
            case "chrome" -> WebDriverManager.chromedriver().cachePath(cachePath).create();
            case "firefox" -> WebDriverManager.firefoxdriver().cachePath(cachePath).create();
            default -> throw new InvalidArgumentException("Invalid browser: " + browser);
        };
        // DO NOT MIX IMPLICIT WAITS AND EXPLICIT WAITS
        // https://www.selenium.dev/documentation/webdriver/waits/#implicit-waits
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.get(url);
    }

    @AfterClass
    public void teardown() {
        delay(3);
        driver.quit();
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
        System.out.println(texto);
    }
}