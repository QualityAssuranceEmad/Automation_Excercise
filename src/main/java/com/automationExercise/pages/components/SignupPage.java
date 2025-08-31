package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private final GuiDrivers driver;

    public SignupPage(GuiDrivers driver) {
        this.driver = driver;
    }

    //Locators
    //private final By title = By.xpath("//input[@value='Mr']");//dynamic locator
    private final By name = By.xpath("//input[@data-qa='name']");
    private final By email = By.xpath("//input[@data-qa='email']");
    private final By password = By.xpath("//input[@data-qa='password']");
    private final By days = By.id("days");
    private final By months = By.id("months");
    private final By years = By.id("years");
    private final By newsletter = By.id("newsletter");
    private final By offers = By.id("optin");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.xpath("//button[@data-qa='create-account']");
    private final By accountCreatedMessage = By.xpath("//b[contains(text(), 'Account Created!')]");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");
    //Actions
    @Step("chose title {titleValue}")
    private SignupPage choseTitle(String titleValue) {
        By title = By.xpath("//input[@value='" + titleValue + "']");
        driver.element().clicing(title);
        return this;

    }

    @Step("fill registration form")
    public SignupPage fillRegistrationForm(String title,String passwordValue,
                                           String dayValue, String monthValue, String yearValue,
                                           String firstNameValue, String lastNameValue,
                                           String companyValue, String address1Value,
                                           String address2Value, String countryValue,
                                           String stateValue, String cityValue,
                                           String zipcodeValue, String mobileNumberValue) {
        choseTitle(title);
        driver.element().typing(password, passwordValue);
        driver.element().selectFromDropdown(days, dayValue);
        driver.element().selectFromDropdown(months, monthValue);
        driver.element().selectFromDropdown(years, yearValue);
        driver.element().clicing(newsletter);
        driver.element().clicing(offers);
        driver.element().typing(firstName, firstNameValue);
        driver.element().typing(lastName, lastNameValue);
        driver.element().typing(company, companyValue);
        driver.element().typing(address1, address1Value);
        driver.element().typing(address2, address2Value);
        driver.element().selectFromDropdown(country, countryValue);
        driver.element().typing(state, stateValue);
        driver.element().typing(city, cityValue);
        driver.element().typing(zipcode, zipcodeValue);
        driver.element().typing(mobileNumber, mobileNumberValue);

        return this;
    }
    @Step("click on create account button")
    public SignupPage clickOnCreateAccountButton() {
        driver.element().clicing(createAccountButton);
        return this;
    }
    @Step("click on continue button")
    public NavigationBarComponent clickOnContinueButton() {
        driver.element().clicing(continueButton);
        return new NavigationBarComponent(driver);
    }
    //Validations
@Step("validate account created successfully")
    public SignupPage validateAccountCreatedSuccessfully() {
   driver.verification().isElementVisible(accountCreatedMessage);
    return this;

    }

}
