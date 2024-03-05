
// Checkout
// Created by Pedro Muñoz

package bsn.advantage;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Bsn_Advantage_Checkout extends BasePage {

    private final By shoppingCartLink = By.id("shoppingCartLink");
    private final By noProductsInCartMessage = By.xpath("//div[@id='shoppingCart']/div/label");
    private final By checkOutButton = By.id("checkOutButton");
    private final By nextBtn = By.id("next_btn");
    private final By safepayRadioBtn = By.xpath("//div[@id='paymentMethod']/div/div[1]/div[1]/input");
    private final By safepayUsernameField = By.name("safepay_username");
    private final By safepayPasswordField = By.name("safepay_password");
    private final By safepaySaveCheckbox = By.name("save_safepay");
    private final By totalAmount = By.xpath("//div[@id='userCart']/div[2]/label[2]/span");
    private final By payNowBtnSafepay = By.id("pay_now_btn_SAFEPAY");
    private final By orderPaymentSuccessMessage = By.xpath("//*[@id='orderPaymentSuccess']/h2/span");

    public Bsn_Advantage_Checkout(WebDriver driver) {
        super(driver);
    }

    public void Run(String safepay_user, String safepay_password) {
        // Home page
        this.clickElement(shoppingCartLink);

        // Cart page
        // Ver si hay productos en el carrito
        if (!this.isPageObjectVisible(noProductsInCartMessage)) {
            this.clickElement(checkOutButton);
            // Order payment page part 1
            this.clickElement(nextBtn);

            // Order payment page part 2
            // Check payment method
            this.clickElement(safepayRadioBtn);

            // payment account credentials
            this.inputData(safepayUsernameField, safepay_user);
            this.inputData(safepayPasswordField, safepay_password);

            // checkbox
            this.clickCheckBox(safepaySaveCheckbox, false);

            // Total
            String total1 = this.getTextFromElement(totalAmount);

            this.clickElement(payNowBtnSafepay);

            // Success page
            String mensaje = this.getTextFromElement(orderPaymentSuccessMessage);
            print(mensaje);

            int pass = 0;

            Assert.assertEquals(mensaje, "Thank you for buying with Advantage");
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
            String texto = this.getTextFromElement(noProductsInCartMessage).trim();
            print("No hay productos en el carrito");
            Assert.assertEquals(texto, "Your shopping cart is empty");
        }
    }
}