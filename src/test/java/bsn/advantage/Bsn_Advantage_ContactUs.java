
// ContactUs
// Created by Mayra Juarez

package bsn.advantage;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Bsn_Advantage_ContactUs extends BasePage {

    public Bsn_Advantage_ContactUs(WebDriver driver) {
        super(driver);
    }

    public void Run(String category, String product, String email, String subject) {
        this.clickElement(By.xpath("//*[text()='CONTACT US' and @class='menu navLinks roboto-regular ng-scope']"));
        this.delay(2);
        this.selectFromComboboxByText(By.name("categoryListboxContactUs"), category);
        this.selectFromComboboxByText(By.name("productListboxContactUs"), product);

        this.inputData(By.name("emailContactUs"), email);
        this.inputData(By.name("subjectTextareaContactUs"), subject);
        this.clickElement(By.id("send_btnundefined"));

        String actualMessage = this.getTextFromElement(By.xpath("//*[@id='registerSuccessCover']/div/p"));

        String expectedMessage = "Thank you for contacting Advantage support.";
        Assert.assertEquals(actualMessage, expectedMessage);

        this.clickElement(By.xpath("//*[text()=' CONTINUE SHOPPING ']"));
    }
}