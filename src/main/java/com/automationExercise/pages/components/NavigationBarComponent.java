package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import com.automationExercise.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.Logs;

public class NavigationBarComponent {
    private final GuiDrivers driver;

    public NavigationBarComponent(GuiDrivers driver) {
        this.driver = driver;
    }

    //Locators
    private final By homeButton = By.xpath("//a[.= ' Home']");
    private final By productsButton = By.xpath("//a[i[@class='material-icons card_travel']]");
    private final By cartButton = By.xpath("//a[.=' Cart']");
    private final By signupLogin = By.xpath("//a[contains(.,' Signup / Login')]");

    private final By deleteAccount = By.xpath("//a[i[@class='fa fa-trash-o']]");
    private final By videoTutorials = By.xpath("//a[i[@class='fa fa-youtube-play']]");
    private final By contactUs = By.xpath("//a[i[@class='fa fa-envelope']]");
    private final By testCases = By.xpath("//a[contains(.,' Test Cases')]");
    private final By apiTesting = By.xpath("//a[contains(.,' API Testing')]");
    private final By homePageLabel = By.cssSelector("h1 > span");
    private final By userLabel = By.tagName("b");


    //Actions
    @Step("Navigate to the home page")
    public NavigationBarComponent navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on Home button")
    public NavigationBarComponent clickHomeButton() {
        driver.element().clicing(homeButton);
        return this;
    }

    @Step("Click on Products button")
    public ProductsPage clickProductsButton() {
        driver.element().clicing(productsButton);
        return new ProductsPage(driver);
    }

    @Step("Click on Cart button")
    public CartPage clickCartButton() {
        driver.element().clicing(cartButton);
        return new CartPage(driver);
    }

    @Step("Click on Signup/Login button")
    public SignupLoginPage clickSignupLogin() {
        driver.element().clicing(signupLogin);
        return new SignupLoginPage(driver);
    }



    @Step("Click on Delete Account button")
    public DeleteAccountPage clickDeleteAccount() {
        driver.element().clicing(deleteAccount);
        return new DeleteAccountPage(driver);
    }

    @Step("Click on Video Tutorials button")
    public VideoTutorialsPage clickVideoTutorials() {
        driver.element().clicing(videoTutorials);
        return new VideoTutorialsPage(driver);
    }

    @Step("Click on Contact Us button")
    public ContactUsPage clickContactUs() {
        driver.element().clicing(contactUs);
        return new ContactUsPage(driver);
    }

    @Step("Click on Test Cases button")
    public TestCasesPage clickTestCases() {
        driver.element().clicing(testCases);
        return new TestCasesPage(driver);
    }

    @Step("Click on API Testing button")
    public ApiTestingPage clickApiTesting() {
        driver.element().clicing(apiTesting);
        return new ApiTestingPage(driver);
    }


    //Validations
    @Step("Verify that home page is visible successfully")
    public NavigationBarComponent verifyHomePageVisible() {
        driver.verification().isElementVisible(homePageLabel);
        return this;
    }

    @Step("Verify that user is logged in as {expectedUserName}")
    public NavigationBarComponent verifyUserLoggedIn(String expectedUserName) {
        String actualText = driver.element().getText(userLabel);
        LogsManager.info("User is logged in as: " + actualText);
        driver.verification().Equals(actualText, expectedUserName, "User name does not match");
        return this;
    }

}
