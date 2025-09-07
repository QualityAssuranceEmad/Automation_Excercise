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
    private final By addToCartButton = By.xpath("//button[@type='button']");
    private final By viewCartButton = By.cssSelector(".modal-body a[href='/view_cart']");

    private final By productNameLabel = By.cssSelector(".product-information > h2");
    private final By categoryLabel = By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]");
    private final By productPriceLabel = By.xpath("//div[@class='product-information']/span/span");
    private final By productAvailabilityLabel = By.cssSelector(".product-information p:nth-of-type(2)");
    private final By productConditionLabel = By.cssSelector(".product-information p:nth-of-type(3)");
    private final By productDescriptionLabel = By.cssSelector(".product-information p:nth-of-type(4)");
    private final By reviewerName = By.id("name");
    private final By reviewEmail = By.id("email");
    private final By reviewTextArea = By.id("review");
    private final By submitReviewButton = By.id("button-review");
    private final By reviewSuccessMessage = By.xpath("//div[@class='alert-success alert']/span");
    private final By increaseQuantityButton = By.id("quantity");

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
    @Step("Increase Quantity")
    public ProductDeatilsPage increaseQuantity(String quantity) {
        driver.element().typing(increaseQuantityButton, quantity)
                .clicing(addToCartButton)
                .clicing(viewCartButton);
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

    @Step("validate products deatils are correct")
    public ProductDeatilsPage validateProductDeatils(String name, String category, String price,
                                                     String availability,String condition,String description) {
        String actualProductName = driver.element().getText(productNameLabel);
        LogsManager.info("Actual Name: " + actualProductName);
        String actualProductCategory = driver.element().getText(categoryLabel);
        String categoryValue = actualProductCategory.replace("Category: ", "").trim();
        LogsManager.info("Actual Category: " + actualProductCategory);
        String actualProductPrice = driver.element().getText(productPriceLabel);
        LogsManager.info("Actual Price: " + actualProductPrice);
        String actualProductAvailability = driver.element().getText(productAvailabilityLabel);
        String availabilityValue = actualProductAvailability.replace("Availability: ", "").trim();
        LogsManager.info("Actual Availability: " + actualProductAvailability);
        String actualProductCondition = driver.element().getText(productConditionLabel);
        String conditionValue = actualProductCondition.replace("Condition: ", "").trim();
        LogsManager.info("Actual Condition: " + actualProductCondition);
        String actualProductDescription = driver.element().getText(productDescriptionLabel);
        String descriptionValue = actualProductDescription.replace("Description: ", "").trim();
        LogsManager.info("Actual Description: " + actualProductDescription);
        driver.validation().Equals(actualProductName, name, "Product Name Not Match");
        driver.validation().Equals(categoryValue, category, "Product Category Not Match");
        driver.validation().Equals(actualProductPrice, price, "Product Price Not Match");
        driver.validation().Equals(availabilityValue, availability, "Product Availability Not Match");
        driver.validation().Equals(conditionValue, condition, "Product Condition Not Match");
        driver.validation().Equals(descriptionValue, description, "Product Description Not Match");
        return this;
    }

    @Step("Validate success message is visible: '{message}'")
    public ProductDeatilsPage isReviewSuccessMessageVisible(String message) {
        String actualMessage = driver.element().getText(reviewSuccessMessage);
        LogsManager.info("Actual Message: " + actualMessage);
        driver.verification().Equals(actualMessage, message, "Message Not Match");
        return this;
    }

    @Step("Verify that Test Cases page is visible successfully")
    public ProductDeatilsPage verifyProductsDetailsPageUrl() {
        driver.verification().assertPageUrl(PropertyReader.getProperty("baseUrlWeb") + productDetails);
        return this;
    }

}
