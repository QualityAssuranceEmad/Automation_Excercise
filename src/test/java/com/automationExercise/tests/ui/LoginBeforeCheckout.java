package com.automationExercise.tests.ui;

import com.automationExercise.apis.UserManagementAPI;
import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.pages.components.*;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.TimeManager;
import com.automationExercise.utils.dataReader.JsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginBeforeCheckout extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();
    @Test
    public void verifyHomePage()
    {
        new NavigationBarComponent(driver)
                .navigate()
                .verifyHomePageVisible();

    }
    @Test(dependsOnMethods = "verifyHomePage")
    public void loginBeforeCheckout()
    {
        new NavigationBarComponent(driver)
                .clickSignupLogin()
                .enterLoginEmail(testData.getJsonData("email"))
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton();
        new NavigationBarComponent(driver).verifyUserLoggedIn(testData.getJsonData("name"));


    }
    @Test(dependsOnMethods = {"loginBeforeCheckout","verifyHomePage"})
    public void testAddProductToCart() {
        new ProductsPage(driver)
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .isItemAddedToCartLabelVisible(testData.getJsonData("messages.productAdded"))
                .clickOnContinueShopping();

    }
    @Test(dependsOnMethods = {"testAddProductToCart","loginBeforeCheckout","verifyHomePage"})
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
    @Test(dependsOnMethods = {"cartPage","testAddProductToCart","loginBeforeCheckout","verifyHomePage"})
    public void ClickProceedToCheckout() {
        new CartPage(driver)
                .clickOnProceedToCheckoutButton();


    }
    @Test(dependsOnMethods = {"ClickProceedToCheckout","cartPage","testAddProductToCart","loginBeforeCheckout","verifyHomePage"})
public void continuePlaceOrder() {
        new CartPage(driver)
                .validateProductInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"));
        new CheckoutPage(driver)
                .addCommentOnMyOrder(testData.getJsonData("comment.commentMessage"));


    }
    @Test(dependsOnMethods = {"continuePlaceOrder","ClickProceedToCheckout","cartPage","testAddProductToCart","loginBeforeCheckout","verifyHomePage"})
    public void continuePayment() {
        new CheckoutPage(driver)
                .clickOnPlaceOrderButton()
                .fillCardInformation(testData.getJsonData("payment.nameOnCard"),
                        testData.getJsonData("payment.cardNumber"),
                        testData.getJsonData("payment.cvv"),
                        testData.getJsonData("payment.expiryMonth"),
                        testData.getJsonData("payment.expiryYear"))
                .verifyPaymentSuccessMessage(testData.getJsonData("PaymentMessages.paymentSuccessMessage"));
    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("login_Correct_Data");
        driver = new GuiDrivers();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
