package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TestCasesPage {
    private final GuiDrivers driver;
    public TestCasesPage(GuiDrivers driver) {
        this.driver = driver;
    }
    private final String testCasesEndpoint = "/test_cases";
    // Locators
    private final By testCasesLabel = By.xpath("//a[@href='/test_cases']");
    // Actions
    @Step("Navigate to Test Cases page")
    public TestCasesPage navigateToTestCasesPage() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + testCasesEndpoint);
        return this;
    }
    @Step("click on Test Cases button")
    public TestCasesPage navigate() {
        driver.element().clicing(testCasesLabel);
        return this;
    }
    // Validations
    @Step("Verify that Test Cases page is visible successfully")
    public TestCasesPage verifyTestCasesPageUrl() {
        driver.verification().assertPageUrl(PropertyReader.getProperty("baseUrlWeb") + testCasesEndpoint);
        return this;
    }

}
