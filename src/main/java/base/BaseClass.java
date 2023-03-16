package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.time.Duration;

public class BaseClass {
	protected WebDriver driver;

	/**
	 * setup
	 * Funcion que setea el driver
	 *
	 * @param browser String
	 * @param url     String
	 */
	@Parameters({ "browser", "hidden", "url" })
	@BeforeClass(alwaysRun = true)
	public void setup(
		@Optional("chrome") String browser,
		@Optional("false") Boolean hidden,
		@Optional("https://academybugs.com/find-bugs") String url
	) {
		switch(browser) {
			case "chrome" -> {
				ChromeOptions opt = new ChromeOptions();
				if(hidden) opt.addArguments("headless");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(opt);
			}
			case "firefox" -> {
				FirefoxOptions opt = new FirefoxOptions();
				if(hidden) opt.addArguments("headless");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(opt);
			}
			default -> {
				System.out.println("Imposible iniciar " + browser + ", iniciando chrome");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		}

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		if(!hidden) {
			driver.manage().window().maximize();
			// driver.manage().window().setSize(new Dimension(1024, 768));
			// Point p = driver.manage().window().getPosition();
			// Dimension d = driver.manage().window().getSize();
			// driver.manage().window().setPosition(new Point((d.getHeight() - p.getX()), (d.getWidth() - p.getY())));
		}

		delay(3);
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		delay(3);
		driver.close();
		driver.quit();
	}

	public WebElement findElement(String by, String l) {
		By crit = By.xpath(l);
		switch(by.toLowerCase().trim()) {
			case "xpath" -> crit = By.xpath(l);
			case "id" -> crit = By.id(l);
			case "name" -> crit = By.name(l);
			case "classname" -> crit = By.className(l);
		}
		return driver.findElement(crit);
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
			e.printStackTrace();
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