package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    private final GuiDrivers driver;

    public HomePage(GuiDrivers driver) {
        this.driver = driver;
    }

    private final String homePageUrl = "/";

    // Locators
    private final By subscriptionLabel = By.xpath("//h2[text()='Subscription']");
    private final By emailField = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By subscriptionMessage = By.xpath("//div[@class='alert-success alert']");
    private final By categoryLabel = By.xpath("//h2[text()='Category']");
    private final By brandsLabel = By.xpath("//h2[text()='Brands']");
    private final By brandPolo = By.xpath("(//span[@class='pull-right'])[1]");
    private final By womenCategory = By.cssSelector("a[href='#Women']");
    private final By menCategory = By.cssSelector("a[href='#Men']");
    private final By kidsCategory = By.cssSelector("a[href='#Kids']");
    private final By dressWomen = By.cssSelector("a[href='/category_products/1']");
    private final By topWomen = By.cssSelector("a[href='/category_products/2']");
    private final By sareeWomen = By.cssSelector("a[href='/category_products/7']");
    private final By recomendedLabel = By.xpath("//h2[text()='recommended items']");
    private final By clickAddToCartButton = By.xpath("(//div[@class='productinfo text-center']/p[.='Blue Top']//following-sibling::a)[2]");
    private final By clickViewCartButton = By.xpath("//u[normalize-space()='View Cart']");
    private final By validateproductName = By.xpath("//a[@href='/product_details/1']");

    //Actions
    @Step("Navigate to home page")
    public HomePage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + homePageUrl);
        return this;
    }

    @Step("enter Email {email} in subscription field ")
    public HomePage enterEmail(String email) {
        driver.element().typing(emailField, email)
                .clicing(subscribeButton);
        return this;
    }

    @Step("Click on Women category")
    public HomePage clickOnWomenCategory() {
        driver.element().clicing(womenCategory)
                .clicing(dressWomen);
        return this;
    }

    @Step("Click on Men category")
    public HomePage clickOnMenCategory() {
        driver.element().clicing(menCategory)
                .clicing(brandPolo);
        return this;
    }

    @Step("Click on Kids category")
    public HomePage clickOnKidsCategory() {
        driver.element().clicing(kidsCategory);
        return this;
    }

    @Step("Click on Add to cart button")
    public HomePage clickOnAddToCartButton() {
        driver.element().clicing(clickAddToCartButton)
                .clicing(clickViewCartButton);
        return this;
    }

    //Validations
    @Step("validate subscription label is visible")
    public HomePage isSubscriptionLabelVisible() {
        driver.verification().isElementVisible(subscriptionLabel);
        return this;
    }

    @Step("Verify subscription message {message} is visible")
    public HomePage verifySubscriptionMessage(String message) {
        String actualMessage = driver.element().getText(subscriptionMessage);
        driver.verification().Equals(actualMessage, message, "Subscription message is not visible");
        return this;
    }

    @Step("validate category label is visible")
    public HomePage isCategoryLabelVisible() {
        driver.verification().isElementVisible(categoryLabel);
        return this;
    }

    @Step("validate brands label is visible")
    public HomePage isBrandsLabelVisible() {
        driver.verification().isElementVisible(brandsLabel);
        return this;
    }

    @Step("validate recommended Items is displayed")
    public HomePage isRecommendedItemsDisplayed() {
        driver.verification().isElementVisible(recomendedLabel);
        return this;
    }

    @Step("validate product name is displayed")
    public HomePage isProductNameDisplayed() {
        driver.verification().isElementVisible(validateproductName);
        return this;
    }
}

