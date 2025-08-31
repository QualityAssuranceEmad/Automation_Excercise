package com.automationExercise.tests.registrationTests;

import com.automationExercise.apis.UserManagementAPI;
import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.SignupLoginPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.TimeManager;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Feature("Ui Register Tests")
@Epic("Automation Exercise")
@Owner("Emad Maher Abd ElHamied")
@Story("Register Tests")
@UiTest
public class RegisterExistingEmail extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();
    @Description("Verify User Can't Register Successfully With Existing Email")
    @Test
    public void SignUpTC_1() {
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber"))
                .verifyUserCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterSignUpName(testData.getJsonData("name"))
                .enterSignUpEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .clickSignUpButton()
                .isRegisterErrorVisible(testData.getJsonData("messages.error"));
        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();

    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData=new JsonReader("SignUp_Data");
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
