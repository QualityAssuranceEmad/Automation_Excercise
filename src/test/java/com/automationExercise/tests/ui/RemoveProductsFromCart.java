package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.CartPage;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.ProductsPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RemoveProductsFromCart extends BaseTest {
    @Test
    public void verifyHomePage()
    {
        new NavigationBarComponent(driver)
                .navigate()
                .verifyHomePageVisible();

    }
    @Test(dependsOnMethods = "verifyHomePage")
    public void testAddProductToCart() {
        new ProductsPage(driver)
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .isItemAddedToCartLabelVisible(testData.getJsonData("messages.productAdded"))
                .clickOnContinueShopping();

    }
    @Test(dependsOnMethods = {"testAddProductToCart","verifyHomePage"})
    public void cartPage() {

        new NavigationBarComponent(driver)
                .clickCartButton();
        new CartPage(driver)
                .validateProductInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"));
    }
    @Test(dependsOnMethods = {"cartPage","testAddProductToCart","verifyHomePage"})
    public void removeProductFromCart() {
        new CartPage(driver)
                .removeProductFromCart(testData.getJsonData("product.name"))
                .isEmptyCartMessageVisible(testData.getJsonData("messages.removeProduct"));
    }
    //Configurations
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
