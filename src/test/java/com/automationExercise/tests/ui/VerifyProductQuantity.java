package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.CartPage;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.ProductDeatilsPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import org.testng.annotations.*;

public class VerifyProductQuantity  extends BaseTest {
    @Description("Verify User Can Login Successfully")
    @Test
    public void SubscriptionCartPageTest() {
        new NavigationBarComponent(driver)
                .navigate()
                .verifyHomePageVisible()
                .clickProductsButton()
                .isAllProductsLabelVisible()
                .waitUntilAtLeastOneProductIsVisible()
                .viewProductOfFirstProduct();
        new ProductDeatilsPage(driver)
                .increaseQuantity(testData.getJsonData("productDetails.quantity"));
    }
    @Test
public void verifyProductDetails() {
            new CartPage(driver)
                    .validateProductInCart(
                            testData.getJsonData("productDetails.productName"),
                            testData.getJsonData("productDetails.productPrice"),
                            testData.getJsonData("productDetails.quantity"),
                            testData.getJsonData("productDetails.total"));

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
