package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    public NavigationBarComponent navigationBar;
    private GuiDrivers driver;
    private final String signupLoginEndpoint = "/login";

    public SignupLoginPage(GuiDrivers driver) {

        this.driver = driver;
        navigationBar = new NavigationBarComponent(driver);
    }
  //Locators
    private final By loginEmail = By.xpath("//input[@data-qa='login-email']");
    private final By loginPassword = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");
    private final By signupLoginName = By.xpath("//input[@placeholder='Name']");
    private final By signupLoginEmail = By.xpath("//input[@data-qa='signup-email']");
    private final By signupLoginButton = By.xpath("//button[@data-qa='signup-button']");
    private final By loginLabel = By.cssSelector(".login-form>h2");
    private final By loginError = By.cssSelector(".login-form p");
    private final By registerError = By.xpath("//p[contains(.,'Email Address already exist!')]");

    //Actions
    @Step("Navigate to Signup/Login page")
    public SignupLoginPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+signupLoginEndpoint);
        return this;
    }
    @Step("Enter name {email} in login field ")
    public SignupLoginPage enterLoginEmail(String email) {
        driver.element().typing(loginEmail, email);
        return this;
    }
    @Step("Enter password {password} in login field ")
    public SignupLoginPage enterLoginPassword(String password) {
        driver.element().typing(loginPassword, password);
        return this;
    }
    @Step("Click login button")
    public SignupLoginPage clickLoginButton() {
        driver.element().clicing(loginButton);
        return this;
    }
    @Step("Enter name: {name} in signup field ")
    public SignupLoginPage enterSignUpName(String name) {
    driver.element().typing(signupLoginName, name);
    return this;
    }
@Step("Enter email: {email} in signup field ")
    public SignupLoginPage enterSignUpEmail(String email) {
        driver.element().typing(signupLoginEmail, email);
        return this;
    }
    @Step("Click signup button")
    public SignupLoginPage clickSignUpButton() {
        driver.element().clicing(signupLoginButton);
        return new SignupLoginPage(driver);
    }
    //Validations
    @Step("Verify login label is visible")
    public SignupLoginPage isLoginLabelVisible() {
        driver.verification().isElementVisible(loginLabel);
        return this;
    }
    @Step("Verify login error message is visible")
    public SignupLoginPage isLoginErrorVisible(String errorMessageExpected) {
        String errorMessageActual = driver.element().getText(loginError);
        driver.verification().Equals(errorMessageActual, errorMessageExpected,"Error message is not as expected");
        return this;
    }
    @Step("Verify register error message is visible")
    public SignupLoginPage isRegisterErrorVisible(String errorMessageExpected) {
        String errorMessageActual = driver.element().getText(registerError);
        driver.verification().Equals(errorMessageActual, errorMessageExpected," Error message is not as expected");
        return this;
    }
}
