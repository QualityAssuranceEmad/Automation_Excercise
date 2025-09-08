package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.CategoryPage;
import com.automationExercise.pages.components.HomePage;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.ProductsPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RecommendedItemsTest extends BaseTest {
    @Test
    public void verifyRecommendedItems()
    {
        new HomePage(driver)
                .navigate()
                .isRecommendedItemsDisplayed()
                .clickOnAddToCartButton();

    }
    @Test(dependsOnMethods = "verifyRecommendedItems")
    public void productDetailsTest() {
        new HomePage(driver)
                .isProductNameDisplayed();
    }
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("ProductDetailsAndReview_Data");
        driver = new GuiDrivers();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
