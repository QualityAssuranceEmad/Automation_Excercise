package com.automationExercise.tests.ui;

import com.automationExercise.apis.UserManagementAPI;
import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.pages.components.*;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.TimeManager;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Feature("Ui CheckOut Tests")
@Epic("Automation Exercise")
@Owner("Emad Maher Abd ElHemied")
@Story("CheckOut Tests")
@UiTest
public class AskingRegisterTOCheckOutTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();
    //Tests
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
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .isItemAddedToCartLabelVisible(testData.getJsonData("messages.productAdded"))
                .clickOnContinueShopping();

    }
    @Test(dependsOnMethods = {"verifyHomePage","testAddProductToCart"})
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
    @Test(dependsOnMethods = {"cartPage","verifyHomePage","testAddProductToCart"})
    public void testCheckout_shouldDisplayCorrectAddresses() {
        new CartPage(driver)
                .clickOnProceedToCheckoutButton();
                new CheckoutPage(driver)
                        .clickOnLoginRegisterButton();

    }
    @Test(dependsOnMethods = {"testCheckout_shouldDisplayCorrectAddresses","cartPage","verifyHomePage","testAddProductToCart"})
    public void RegisterNewAccountToCheckOut() {  new UserManagementAPI().createRegisterUserAccount(
                    testData.getJsonData("name"),
                    testData.getJsonData("email") + timestamp + "@gmail.com",
                    testData.getJsonData("password"),
                    testData.getJsonData("titleMale"),
                    testData.getJsonData("day"),
                    testData.getJsonData("month"),
                    testData.getJsonData("year"),
                    testData.getJsonData("firstName"),
                    testData.getJsonData("lastName"),
                    testData.getJsonData("companyName"),
                    testData.getJsonData("address1"),
                    testData.getJsonData("address2"),
                    testData.getJsonData("country"),
                    testData.getJsonData("state"),
                    testData.getJsonData("city"),
                    testData.getJsonData("zipcode"),
                    testData.getJsonData("mobileNumber"))
            .verifyUserCreatedSuccessfully();

    }

    @Test(dependsOnMethods = {"RegisterNewAccountToCheckOut","testCheckout_shouldDisplayCorrectAddresses","cartPage","verifyHomePage","testAddProductToCart"})
    public void testLoginWithRegisteredUser_shouldSucceed() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLoggedIn(testData.getJsonData("name"));

    }

@Test(dependsOnMethods = {"testLoginWithRegisteredUser_shouldSucceed","testCheckout_shouldDisplayCorrectAddresses","cartPage","verifyHomePage","testAddProductToCart"})
public void ContinuePlaceOrder() {
    new NavigationBarComponent(driver)
            .clickCartButton();
    new CartPage(driver)
            .clickOnProceedToCheckoutButton();
    new CartPage(driver)
            .validateProductInCart(
                    testData.getJsonData("product.name"),
                    testData.getJsonData("product.price"),
                    testData.getJsonData("product.quantity"),
                    testData.getJsonData("product.total"));
    new CheckoutPage(driver)
            .addCommentOnMyOrder(testData.getJsonData("comment.commentMessage"));


}
@Test(dependsOnMethods = {"ContinuePlaceOrder","testLoginWithRegisteredUser_shouldSucceed","testCheckout_shouldDisplayCorrectAddresses","cartPage","verifyHomePage","testAddProductToCart"})
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
@Test(dependsOnMethods = {"continuePayment","testLoginWithRegisteredUser_shouldSucceed","testCheckout_shouldDisplayCorrectAddresses","cartPage","verifyHomePage","testAddProductToCart"})
public void deleteUser()
{
    new UserManagementAPI().deleteUserAccount(
                    testData.getJsonData("email") + timestamp + "@gmail.com",
                    testData.getJsonData("password"))
            .verifyUserDeletedSuccessfully();
    new DeleteAcountPage(driver).clickContinueButton();

}

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("checkOut_Data");
        driver = new GuiDrivers();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }

}
