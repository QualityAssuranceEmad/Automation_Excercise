package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.CartPage;
import com.automationExercise.pages.components.HomePage;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubscriptionCartPageTest extends BaseTest {
    @Description("Verify User Can Login Successfully")
    @Test
    public void SubscriptionCartPageTest() {
        new NavigationBarComponent(driver)
                .navigate()
                .verifyHomePageVisible()
                .clickCartButton();
        new CartPage(driver)
                .isSubscriptionLabelVisible()
                .enterEmail(testData.getJsonData("email"))
                .verifySubscriptionMessage(testData.getJsonData("messages.successMessage"));


    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("subscription_Data");
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
