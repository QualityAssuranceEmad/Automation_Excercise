package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.ProductDeatilsPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyAllProductsTest extends BaseTest {
    @Description(" Verify user is navigated to test cases page successfully")
    @Test
    public void testCases() {
        new NavigationBarComponent(driver)
                .navigate()
                .verifyHomePageVisible()
                .clickProductsButton()
                .isAllProductsLabelVisible()
                .waitUntilAtLeastOneProductIsVisible()
                .viewProductOfFirstProduct();
        new ProductDeatilsPage(driver)
                .verifyProductsDetailsPageUrl()
                .validateProductDeatils(testData.getJsonData("productDetails.productName")
                        ,testData.getJsonData("productDetails.productCategory"),
                        testData.getJsonData("productDetails.productPrice")
                ,testData.getJsonData("productDetails.productAvailability"),
                testData.getJsonData("productDetails.productCondition"),
                testData.getJsonData("productDetails.productBrand"));

    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("ProductDetailsAndReview_Data");
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
