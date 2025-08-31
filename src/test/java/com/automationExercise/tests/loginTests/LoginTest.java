package com.automationExercise.tests.loginTests;
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
@Feature("Ui Login Tests")
@Epic("Automation Exercise")
@Owner("Emad Maher Abd ElHamied")
@Story("Login Tests")

//@Severity(SeverityLevel.BLOCKER)
public class LoginTest extends BaseTest {

    String timestamp = TimeManager.getSimpleTimestamp();

    //Test
    @Description("Verify User Can Login Successfully")
    @Test
    public void LoginTC() {
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))

                .verifyUserCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLoggedIn(testData.getJsonData("name"));
        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();


    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("Login_Data");
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
