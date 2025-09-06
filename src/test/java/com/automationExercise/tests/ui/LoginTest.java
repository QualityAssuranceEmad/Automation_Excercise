package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.ProductsPage;
import com.automationExercise.pages.components.SignupLoginPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.TimeManager;
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
@Story("Login Tests")
@UiTest
public class LoginTest extends BaseTest {
    @Description("Verify User Can Login Successfully")
    @Test
    public void login() {
        new SignupLoginPage(driver).navigate().isLoginLabelVisible()
                .enterLoginEmail(testData.getJsonData("email"))
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton();
        new NavigationBarComponent(driver).verifyUserLoggedIn(testData.getJsonData("name"));
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
