package legacy.academybugs.tsd;

import legacy.BaseClass;
import legacy.academybugs.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Tsd_SearchByCategory extends BaseClass {
	ArrayList<Product> products = new ArrayList<>();
	Product fall_coat = new Product("ec_product_li_fall-coat", Product.categorias.WEEKEND);
	Product boho_bangle_bracelet = new Product("ec_product_li_boho-bangle-bracelet", Product.categorias.BRACELETS);
	Product buddha_bracelet = new Product("ec_product_li_buddha-bracelet", Product.categorias.BRACELETS);
	Product anchor_bracelet = new Product("ec_product_li_anchor-bracelet", Product.categorias.BRACELETS);

	@Test
	public void searchByCategory() {
		cargarProductos();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		delay(2);

		driver.findElement(By.xpath("//*[contains(text(),\"Accept cookies\")]")).click();
		WebElement product = driver.findElement(By.id("ec_product_li_dnk-yellow-shoes"));
		product.click();

		WebElement mnu_categoryAccesories = driver.findElement(By.id("menu2"));
		mnu_categoryAccesories.click();
		delay(1);

		WebElement mnu_categoryBracelets = driver.findElement(By.id("submenu2"));
		mnu_categoryBracelets.click();

		List<WebElement> listProducts = driver.findElements(By.className("ec_product_li"));
		for(WebElement prod : listProducts) {
			String id = prod.getAttribute("id");
			print(prod.getAttribute("id"));
			Product.categorias cat = findById(products, id).categoria;
			print("categoria: " + cat.toString());
			if(cat != Product.categorias.BRACELETS) Assert.fail();
		}
	}

	public void cargarProductos() {
		products.add(anchor_bracelet);
		products.add(boho_bangle_bracelet);
		products.add(buddha_bracelet);
		products.add(fall_coat);
	}

	public static Product findById(ArrayList<Product> listProducts, String id) {
		return listProducts.stream().filter(product -> id.equals(product.getId())).findFirst().orElse(null);
	}
}