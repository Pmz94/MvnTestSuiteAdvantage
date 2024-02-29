
// Checkout
// Created by Pedro Muñoz

package bsn.advantage;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Bsn_Advantage_Checkout extends BasePage {

    public boolean emptyCar = true;
    public String emptyCarMessage = "";

    public Bsn_Advantage_Checkout(WebDriver driver) {
        super(driver);
    }

    public void Run(String safepay_user, String safepay_password) {
        this.clickElement(By.id("shoppingCartLink"));

        // Ver si hay productos en el carrito
        WebElement l = this.findByVisibility(By.xpath("//*[@id='shoppingCart']/div/label"));

        if (!l.isDisplayed()) {
            this.clickElement(By.id("checkOutButton"));
            delay(3);
            this.clickElement(By.id("next_btn"));

            // Check payment method
            this.clickElement(By.xpath("//*[@id='paymentMethod']/div/div[1]/div[1]/input"));

            // payment account credentials
            this.inputData(By.name("safepay_username"), safepay_user);
            this.inputData(By.name("safepay_password"), safepay_password);

            // checkbox
            WebElement checkbox1 = this.findByVisibility(By.name("save_safepay"));
            if (checkbox1.isSelected()) checkbox1.click();

            // Total
            String total1 = this.getTextFromElement(By.xpath("//*[@id='userCart']/div[2]/label[2]/span"));

            delay(2);

            // WebElement payButton = driver.findElement(By.id("pay_now_btn_SAFEPAY"));
            // print("" + payButton.isEnabled());
            // if(payButton.isEnabled()) payButton.click();

            this.clickElement(By.xpath("//*[@id='pay_now_btn_SAFEPAY']"));

            delay(4);

            String mensaje = this.getTextFromElement(By.xpath("//*[@id='orderPaymentSuccess']/h2/span"));
            print(mensaje);

            int pass = 0;

            if (mensaje.equals("Thank you for buying with Advantage")) {
                print("mensaje validado");
                pass++;
            }

            String rastreo = this.getTextFromElement(By.id("trackingNumberLabel"));
            if (!rastreo.isEmpty()) {
                print("Num rastreo obtenido");
                pass++;
            }

            String orden = this.getTextFromElement(By.id("orderNumberLabel"));
            if (!orden.isEmpty()) {
                print("Num orden obtenido");
                pass++;
            }

            String total2 = this.getTextFromElement(By.xpath("//*[@id='orderPaymentSuccess']/div/div[3]/div[3]/label/a"));
            if (total1.equals(total2)) {
                print("Pagaste justo lo que costo");
            }

            Assert.assertEquals(pass, 3);
        } else {
            String texto = l.getText().trim();
            print("No hay productos en el carrito");
            Assert.assertEquals(texto, "Your shopping cart is empty");
        }
    }
}