package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    private final GuiDrivers driver;
    public HomePage(GuiDrivers driver) {
        this.driver = driver;
    }

    // Locators
    private final By subscriptionLabel = By.xpath("//h2[text()='Subscription']");
    private final By emailField = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By subscriptionMessage = By.xpath("//div[@class='alert-success alert']");

    //Actions
    @Step("enter Email {email} in subscription field ")
    public HomePage enterEmail(String email) {
        driver.element().typing(emailField, email)
                .clicing(subscribeButton);
        return this;
    }
    //Validations
    @Step("validate subscription label is visible")
    public HomePage isSubscriptionLabelVisible() {
        driver.verification().isElementVisible(subscriptionLabel);
        return this;
    }
    @Step("Verify subscription message {message} is visible")
    public HomePage verifySubscriptionMessage(String message) {
        String actualMessage = driver.element().getText(subscriptionMessage);
        driver.verification().Equals(actualMessage, message, "Subscription message is not visible");
        return this;
    }
}
