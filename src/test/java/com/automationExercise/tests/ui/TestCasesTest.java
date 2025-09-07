package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.ContactUsPage;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCasesTest  extends BaseTest {
    @Description(" Verify user is navigated to test cases page successfully")
    @Test
    public void testCases() {
        new NavigationBarComponent(driver)
                .navigate()
                .verifyHomePageVisible()
                .clickTestCases()
                .verifyTestCasesPageUrl();
    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("");
    }

    @BeforeMethod
    public void setUp() {
        driver = new GuiDrivers();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();

    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}