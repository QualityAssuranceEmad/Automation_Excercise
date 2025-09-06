package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.Logout;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.SignupLoginPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import org.testng.annotations.*;

public class LogOutTest extends BaseTest {
    @Description("Verify User Can Login Successfully")
    @Test
    public void login() {
        new SignupLoginPage(driver).navigate().isLoginLabelVisible()
                .enterLoginEmail(testData.getJsonData("email"))
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton();
        new NavigationBarComponent(driver).verifyUserLoggedIn(testData.getJsonData("name"));
        new Logout(driver).clickLogout();
        new SignupLoginPage(driver).navigate().isLoginLabelVisible();

    }
    @Description("Verify User Can Logout Successfully")
    @Test
    public void logout() {

        new Logout(driver).clickLogout();
        new SignupLoginPage(driver).navigate().isLoginLabelVisible();
    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("login_Correct_Data");
        driver = new GuiDrivers();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }


    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }

}
