package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    private final GuiDrivers driver;

    public PaymentPage(GuiDrivers driver) {
        this.driver = driver;
    }
    private String paymentEndPoint="/payment";
    //Locators
     private final By nameOnCard=By.name("name_on_card");
     private final By numberOnCard=By.name("card_number");
     private final By cvc= By.name("cvc");
     private final By cardMonthExpiry=By.name("expiry_month");
     private final By cardYearExpiry=By.name("expiry_year");
     private final By payButton=By.id("submit");
     private final By paymentSuccessMessage=By.xpath("//p[.='Congratulations! Your order has been confirmed!']");
     private final By continueButton=By.xpath("//a[.='Continue']");
     private final By donlownloadInvoiceButton=By.xpath("//a[.='Download Invoice']");

    //Actions
    @Step("navigate to payment page")
    public PaymentPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+paymentEndPoint);
        return this;
    }
    @Step("fill card information")
    public PaymentPage fillCardInformation(String nameOnCard, String numberOnCard,
                                           String cvc, String cardMonthExpiry, String cardYearExpiry){
       driver.element().typing(this.nameOnCard,nameOnCard)
               .typing(this.numberOnCard,numberOnCard)
               .typing(this.cvc,cvc)
               .typing(this.cardMonthExpiry,cardMonthExpiry)
               .typing(this.cardYearExpiry,cardYearExpiry);
       driver.element().clicing(payButton);
        return this;
    }
    @Step("click on continue button")
    public PaymentPage clickOnContinueButton(){
        driver.element().clicing(continueButton);
        return this;
    }
    @Step("click on download invoice button")
    public PaymentPage clickOnDownloadInvoiceButton(){
        driver.element().clicing(donlownloadInvoiceButton);
        return this;
    }


    //Validat
    @Step("verify payment success message")
    public PaymentPage verifyPaymentSuccessMessage(String expectedMessage){
        driver.verification().Equals(driver.element().getText(paymentSuccessMessage),expectedMessage,
                "Congratulations! Your order has been confirmed!");
        return this;
    }

    public PaymentPage verifyInvoiceDownloadedSuccessfully(String invoiceName) {
        driver.verification().assertFileExists(invoiceName,"Invoice not downloaded successfully");
         return this;
    }
}
