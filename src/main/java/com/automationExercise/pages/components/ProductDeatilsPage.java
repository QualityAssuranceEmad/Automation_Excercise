package com.automationExercise.pages.components;


import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import com.automationExercise.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDeatilsPage {
    private final GuiDrivers driver;

    public ProductDeatilsPage(GuiDrivers driver) {
        this.driver = driver;
    }

    private final String productDetails = "/product_details/1";


    //Locators
    private final By productNameLabel = By.cssSelector(".product-information > h2");
    private final By productPriceLabel = By.xpath("//div[@class='product-information']/span/span");
    private final By reviewerName = By.id("name");
    private final By reviewEmail = By.id("email");
    private final By reviewTextArea = By.id("review");
    private final By submitReviewButton = By.id("button-review");
    private final By reviewSuccessMessage = By.xpath("//div[@class='alert-success alert']/span");


    //Actions
    @Step("navagate to Product Deatils Page")
    public ProductDeatilsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productDetails);
        return this;
    }

    @Step("Enter Reviewer Name: '{name}' and Email: '{email}' and Review: '{review}'")
    public ProductDeatilsPage enterReviewDetails(String name, String email, String review) {
        driver.element().typing(reviewerName, name)
                .typing(reviewEmail, email)
                .typing(reviewTextArea, review);
        return this;
    }

    @Step("Submit Review")
    public ProductDeatilsPage clickOnSubmitReview() {
        driver.element().clicing(submitReviewButton);
        return this;
    }

    //Validations
    @Step("Validate Product Details")
    public ProductDeatilsPage validateProductDetails(String name, String price) {
        String actiualProductName = driver.element().getText(productNameLabel);
        LogsManager.info("Actual Name: " + actiualProductName);
        String actiualProductPrice = driver.element().getText(productPriceLabel);
        LogsManager.info("Actual Price: " + actiualProductPrice);
        driver.validation().Equals(actiualProductName, name, "Product Name Not Match");
        driver.validation().Equals(actiualProductPrice, price, "Product Price Not Match");
        return this;
    }

    @Step("Validate success message is visible: '{message}'")
    public ProductDeatilsPage isReviewSuccessMessageVisible(String message) {
        String actualMessage = driver.element().getText(reviewSuccessMessage);
        LogsManager.info("Actual Message: " + actualMessage);
        driver.verification().Equals(actualMessage, message, "Message Not Match");
        return this;
    }
}
