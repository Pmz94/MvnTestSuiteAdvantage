package base;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Browsers {

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
        @Optional("https://academybugs.com/find-bugs") String url
    ) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        delay(3);
    }

    @AfterClass
    public void teardown() {
        delay(3);
        driver.close();
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

    public void dismissAlert() {
        // Esperar a que salga el alert y hacerle dismiss
        delay(1);
        driver.switchTo().alert().accept();
    }

    public void print(String texto) {
        System.out.println(texto);
    }
}