package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.SignupLoginPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginUserWithIncorrectEmail extends BaseTest {
    @Description("Verify User Can't Login Successfully With incorrect email and password")
    @Test
    public void loginWithIncorrectEmail() {
        new SignupLoginPage(driver).navigate().isLoginLabelVisible()
                .enterLoginEmail(testData.getJsonData("wrongEmail"))
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .isLoginErrorVisible(testData.getJsonData("messages.error"));
    }
    @Description("Verify User Can't Login Successfully With incorrect password")
    @Test
    public void loginWithIncorrectPassword() {
        new SignupLoginPage(driver).navigate().isLoginLabelVisible()
                .enterLoginEmail(testData.getJsonData("email"))
                .enterLoginPassword(testData.getJsonData("wrongPassword"))
                .clickLoginButton()
                .isLoginErrorVisible(testData.getJsonData("messages.error"));
    }
    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("login_Correct_Data");
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
