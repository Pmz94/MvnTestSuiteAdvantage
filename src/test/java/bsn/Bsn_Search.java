
// Search
// Created by Walter Medina

package bsn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Browsers;

public class Bsn_Search extends Browsers {
	private static final Integer ID = 1;
	private static final Integer XPATH = 2;
	private static final Integer TEN_SECONDS = 10;
	public String baseUrl = "https://advantageonlineshopping.com/";
	public String searchIcon = "searchSection";
	public String searchInput = "autoComplete";
	public String category = "speakers";
	public String viewAllOption = "//a[@class='roboto-medium viewAll ng-scope']";
	public String closeSearch = "//div[@data-ng-click='closeSearchForce()']";
	public String specificProduct = "//a[normalize-space()='Bose Soundlink Bluetooth Speaker III']";
	public String boseSpeaker = "//a[@class='product ng-scope']//img";
	public String productTitle = "//h1[@class='roboto-regular screen768 ng-binding']";
	public String specificProductName = "bose soundlink bluetooth speaker III";

	public Bsn_Search(WebDriver driver) {
		this.driver = driver;
	}

	public void findElementAndClick(String selector, Integer selectorType) {
		WebElement element = selectorType == ID ? driver.findElement(By.id(selector)) : driver.findElement(By.xpath(selector));
		element.click();
	}

	public void fillSearchInput(String selector, Integer selectorType, String text) {
		WebElement inputSearch = selectorType == ID ? driver.findElement(By.id(selector)) : driver.findElement(By.id(selector));
		inputSearch.clear();
		inputSearch.sendKeys(text);
	}

	public void waitElementAndClick(String selector) {
		WebDriverWait waitForElement = new WebDriverWait(driver, TEN_SECONDS);
		WebElement element = waitForElement.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void Run() {
		findElementAndClick(searchIcon, ID);
		fillSearchInput(searchInput, ID, category);

		findElementAndClick(viewAllOption, XPATH);
		waitElementAndClick(closeSearch);

		WebElement product = driver.findElement(By.xpath(specificProduct));
		String productName = product.getText();

		driver.get(baseUrl);
		findElementAndClick(searchIcon, ID);
		fillSearchInput(searchInput, ID, productName);

		waitElementAndClick(boseSpeaker);

		WebElement titleProductFound = driver.findElement(By.xpath(productTitle));
		String productNameFound = titleProductFound.getText();
		Assert.assertEquals(specificProductName.toUpperCase(), productNameFound);
	}
}