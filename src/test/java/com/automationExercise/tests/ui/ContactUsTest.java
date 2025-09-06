package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.pages.components.ContactUsPage;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.SignupLoginPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Owner("Emad Maher Abd ElHamied")
@Story("Contact Us Tests")
@UiTest
public class ContactUsTest extends BaseTest {
    @Description("Verify User Can Login Successfully")
    @Test
    public void contactUs() {
        new NavigationBarComponent(driver)
                .navigate()
                .verifyHomePageVisible()
                .clickContactUs();
        new ContactUsPage(driver)
                .fillContactUsForm(testData.getJsonData("name"),
                        testData.getJsonData("email"),
                        testData.getJsonData("subject"),
                        testData.getJsonData("message"))
                .verifyContactUsFormSubmittedSuccessfully(testData.getJsonData("messages.successMessage"))
                .clickOnHomeButton();

    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("contactUs_Data");
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
