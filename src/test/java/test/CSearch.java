package test;

// Search
// Created by Walter Medina

import base.Navegadores;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CSearch extends Navegadores {

	public String search_icon = "searchSection";
	public String search_input = "autoComplete";
	public String product_name_to_find = "bose soundlink bluetooth";
	public String view_all_options = "//a[@class='roboto-medium viewAll ng-scope']";
	public String bose_speaker = "//img[@id='20']";

	@Test
	public void findSpecificProduct() {
		WebElement btnSearch = driver.findElement(By.id(search_icon));
		btnSearch.click();

		WebElement inputSearch = driver.findElement(By.id(search_input));
		inputSearch.clear();
		inputSearch.sendKeys(product_name_to_find);

		WebElement viewElement = driver.findElement(By.xpath(view_all_options));
		viewElement.click();

		WebElement specificSpeaker = driver.findElement(By.xpath(bose_speaker));
		specificSpeaker.click();

		// regresar al index
		driver.findElement(By.xpath("/html/body/header/nav/div")).click();
	}
}
