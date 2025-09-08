package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CategoryPage {
    private final GuiDrivers driver;

    public CategoryPage(GuiDrivers driver) {
        this.driver = driver;
    }
    //Locators
    private final By validateWomenCategory = By.xpath("//h2[text()='Women - Dress Products']");
    private final By validateBrands = By.xpath("//h2[text()='Brand - Polo Products']");
    private final By brandBiba = By.xpath("(//span[@class='pull-right'])[8]");

    private final By jeansMen = By.cssSelector("a[href='/category_products/3']");
    private final By menCategory = By.cssSelector("a[href='#Men']");
    private final By validateJeansCategory = By.xpath("//h2[text()='Men - Tshirts Products']");
    private final By validateBiba = By.xpath("//h2[text()='Brand - Biba Products']");
    //Actions
@Step("Click on Men category")
    public CategoryPage clickOnMenCategory() {
        driver.element().clicing(menCategory)
                .clicing(jeansMen);
        return this;
    }
@Step("click on biba brand")
    public CategoryPage clickOnBibaBrand() {
        driver.element().clicing(brandBiba);
        return this;
    }
    //Validations
    @Step("Verify that category page is displayed")
    public CategoryPage isCategoryPageDisplayed() {
        driver.verification().isElementVisible(validateWomenCategory);
        return this;
    }
    @Step("Verify that jeans category is displayed")
    public CategoryPage isJeansCategoryDisplayed() {
        driver.verification().isElementVisible(validateJeansCategory);
        return this;
    }
    @Step("Verify that polo brand is displayed")
    public CategoryPage isBrandDisplayed() {
        driver.verification().isElementVisible(validateBrands);
        return this;
    }
    @Step("Verify that biba brand is displayed")
    public CategoryPage isBibaBrandDisplayed() {
        driver.verification().isElementVisible(validateBiba);
        return this;
    }
}
