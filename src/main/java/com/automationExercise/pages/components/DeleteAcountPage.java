package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DeleteAcountPage {
    private final GuiDrivers driver;

    public DeleteAcountPage(GuiDrivers driver) {
        this.driver = driver;
    }
    // Locators
    private final By clickContinueButton = By.xpath("//a[@data-qa='continue-button']");

    // Actions
    @Step("Click on Continue button")
    public DeleteAcountPage clickContinueButton() {
        driver.element().clicing(clickContinueButton);
        return new DeleteAcountPage(driver);
    }

    // Validations
}
