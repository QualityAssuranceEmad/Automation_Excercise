package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.CategoryPage;
import com.automationExercise.pages.components.HomePage;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrandTest extends BaseTest {
    @Test
    public void verifyHomePage()
    {
        new NavigationBarComponent(driver)
                .navigate()
                .clickProductsButton();

    }
    @Test(dependsOnMethods = "verifyHomePage")
    public void verifyBrandPage()
    {
        new HomePage(driver)
                .isBrandsLabelVisible()
                .clickOnMenCategory();
        new CategoryPage(driver)
                .isBrandDisplayed()
                .clickOnBibaBrand()
                .isBibaBrandDisplayed();
    }
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("removeProduct");
        driver = new GuiDrivers();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
