package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactUsPage {

    private final GuiDrivers driver;

    public ContactUsPage(GuiDrivers driver) {
        this.driver = driver;
    }
    private final String contactUsEndpoint = "/contact_us";
    private final String filePath = "src/test/resources/uploads/invoice.txt";
    //Locators
    private final By contactUsLabel = By.xpath("//h2[normalize-space()='Get In Touch']");
    private final By name = By.xpath("//input[@data-qa='name']");
    private final By email = By.xpath("//input[@data-qa='email']");
    private final By subject = By.xpath("//input[@data-qa='subject']");
    private final By message = By.xpath("//textarea[@name='message']");
    private final By uploadFile = By.name("upload_file");
    private final By submitButton = By.xpath("//input[@data-qa='submit-button']");
    private final By successMessage = By.xpath("//div[@class='status alert alert-success']");
    private final By clickOnHomeButton = By.xpath("//span[i[@class='fa fa-angle-double-left'] and contains(text(), 'Home')]");
    //Actions
    @Step("Navigate to contact us page")
    public ContactUsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + contactUsEndpoint);
        return this;
    }
    @Step("Fill contact us form")
    public ContactUsPage fillContactUsForm(String name, String email, String subject, String message) {
        driver.element().typing(this.name, name)
                .typing(this.email, email)
                .typing(this.subject, subject)
                .typing(this.message, message)
                .uploadFile(uploadFile, filePath)
                        .clicing(submitButton);
        driver.alert().acceptAlert();
        return this;



    }
    @Step("Click on Home button")
    public HomePage clickOnHomeButton() {
        driver.element().clicing(clickOnHomeButton);
        return new HomePage(driver);
    }

    //Validations
    @Step("Verify success message")
    public ContactUsPage verifyContactUsFormSubmittedSuccessfully(String expectedMessage) {
        String actualMessage = driver.element().getText(successMessage);
        driver.verification().Equals(actualMessage,expectedMessage, "Form was successfully submitted!");

        return this;
    }

}
