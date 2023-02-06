package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browsers {
	protected WebDriver driver;
	public String user = "Team32";
	public String email = "team3@mail.com";
	public String password = "Team03";

	/**
	 * setup Funcion que setea el driver
	 *
	 * @param browser String
	 * @param url     String
	 */
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void setup(
		@Optional("chrome") String browser,
		@Optional("https://www.advantageonlineshopping.com") String url
	) {
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void teardown() {
		delay(3);
		driver.close();
		driver.quit();
	}

	/**
	 * delay Funcion para facilitar el Thread sleep
	 *
	 * @param segundos int
	 */
	public void delay(int segundos) {
		try {
			Thread.sleep(segundos * 1000L);
		} catch(InterruptedException e) {
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