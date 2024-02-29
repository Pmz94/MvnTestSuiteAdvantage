
// AddingToCart
// Created by Samuel Arriola

package bsn.advantage;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Bsn_Advantage_AddingToCart extends BasePage {

    public Bsn_Advantage_AddingToCart(WebDriver driver) {
        super(driver);
    }

    public void Run() {
        delay(3);
        // 3 | click | id=speakersLink |
        this.clickElement(By.id("speakersLink"));
        delay(2);
        // 4 | click | linkText=Bose Soundlink Bluetooth Speaker III |
        this.clickElement(By.linkText("Bose Soundlink Bluetooth Speaker III"));
        delay(1);
        // 5 | click | name=save_to_cart |
        this.clickElement(By.name("save_to_cart"));
        // 6 | click | linkText=HOME |
        this.clickElement(By.linkText("HOME"));

        delay(3);
        // 7 | click | id=tabletsImg |
        this.clickElement(By.id("tabletsImg"));
        delay(2);
        // 8 | click | css=ul > .ng-scope:nth-child(1) |
        this.clickElement(By.cssSelector("ul > .ng-scope:nth-child(1)"));
        delay(1);
        // 9 | click | name=save_to_cart |
        this.clickElement(By.name("save_to_cart"));
        // 10 | click | linkText=HOME |
        this.clickElement(By.linkText("HOME"));
        delay(3);
        // 11 | click | id=laptopsImg |
        this.clickElement(By.id("laptopsImg"));
        delay(2);
        // 12 | click | linkText=HP Chromebook 14 G1(ENERGY STAR) |
        this.clickElement(By.linkText("HP Chromebook 14 G1(ENERGY STAR)"));
        delay(1);
        // 13 | click | name=save_to_cart |
        this.clickElement(By.name("save_to_cart"));
    }
}