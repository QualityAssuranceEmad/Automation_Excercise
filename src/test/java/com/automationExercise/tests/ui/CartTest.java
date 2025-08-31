package com.automationExercise.tests.ui;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.pages.components.CartPage;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.ProductsPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Ui Cart Tests")
@Epic("Automation Exercise")
@Owner("Emad Maher Abd ElHamied")
@Story("Cart Tests")
@UiTest
public class CartTest extends BaseTest {
    @Description("adding product to cart and assert adding it in the cart page")
    @Test
    public void addProductToCart() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .isItemAddedToCartLabelVisible(testData.getJsonData("productAddedMessages.cartAdded"))
                .clickViewCart();
        new CartPage(driver)
                .validateProductInCart(testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"))
                .removeProductFromCart(testData.getJsonData("product.name"))
                .isEmptyCartMessageVisible(testData.getJsonData("Messages.emptyCart"));


    }
    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("cart_Data");
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
