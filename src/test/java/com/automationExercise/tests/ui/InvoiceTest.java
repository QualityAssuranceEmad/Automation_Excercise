package com.automationExercise.tests.ui;

import com.automationExercise.apis.UserManagementAPI;
import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.pages.components.*;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.TimeManager;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Ui Payment Tests")
@Epic("Automation Exercise")
@Owner("Emad Maher Abd ElHamied")
@Story("Payment Tests")
@UiTest
public class InvoiceTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();

    //Tests
    @Description("adding product to cart and assert adding it in the cart page")
    @Test
    public void testRegisterNewAccount_shouldSucceed() {
        new UserManagementAPI().createRegisterUserAccount(
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

    @Test(dependsOnMethods = "testRegisterNewAccount_shouldSucceed")
    public void testLoginWithRegisteredUser_shouldSucceed() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLoggedIn(testData.getJsonData("name"));

    }

    @Test(dependsOnMethods = {"testLoginWithRegisteredUser_shouldSucceed", "testRegisterNewAccount_shouldSucceed"})
    public void testAddProductToCart_shouldShowInCartPage() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .isItemAddedToCartLabelVisible(testData.getJsonData("messages.productAdded"))
                .clickViewCart();
        new CartPage(driver)
                .validateProductInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"));

    }

    @Test(dependsOnMethods = {"testAddProductToCart_shouldShowInCartPage", "testLoginWithRegisteredUser_shouldSucceed", "testRegisterNewAccount_shouldSucceed"})
    public void testCheckout_shouldDisplayCorrectAddresses() {
        new CartPage(driver)
                .clickOnProceedToCheckoutButton()
                .validateYourDeliveryAddress(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber"));
        new CheckoutPage(driver).validateYourBillingAddress(
                testData.getJsonData("titleMale"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName"),
                testData.getJsonData("companyName"),
                testData.getJsonData("address1"),
                testData.getJsonData("address2"),
                testData.getJsonData("city"),
                testData.getJsonData("state"),
                testData.getJsonData("zipcode"),
                testData.getJsonData("country"),
                testData.getJsonData("mobileNumber"));

    }
    @Test(dependsOnMethods = {"testCheckout_shouldDisplayCorrectAddresses", "testLoginWithRegisteredUser_shouldSucceed", "testRegisterNewAccount_shouldSucceed"})
    public void testPlaceOrder_shouldSucceed() {
        new CheckoutPage(driver)
                .clickOnPlaceOrderButton()
                .fillCardInformation(testData.getJsonData("payment.nameOnCard"),
                        testData.getJsonData("payment.cardNumber"),
                        testData.getJsonData("payment.cvv"),
                        testData.getJsonData("payment.expiryMonth"),
                        testData.getJsonData("payment.expiryYear"))
                .verifyPaymentSuccessMessage(testData.getJsonData("messages.paymentSuccess"));
    }

@Test(dependsOnMethods = {"testPlaceOrder_shouldSucceed", "testCheckout_shouldDisplayCorrectAddresses", "testLoginWithRegisteredUser_shouldSucceed", "testRegisterNewAccount_shouldSucceed"})
public void downloadInvoice(){
    new PaymentPage(driver)
            .clickOnDownloadInvoiceButton()
            .verifyInvoiceDownloadedSuccessfully(testData.getJsonData("invoice"));
}
        @Test(dependsOnMethods = {"downloadInvoice","testPlaceOrder_shouldSucceed", "testCheckout_shouldDisplayCorrectAddresses", "testLoginWithRegisteredUser_shouldSucceed", "testRegisterNewAccount_shouldSucceed"})
        public void deleteAccount(){
            new UserManagementAPI().deleteUserAccount(
                            testData.getJsonData("email") + timestamp + "@gmail.com",
                            testData.getJsonData("password"))
                    .verifyUserDeletedSuccessfully();

        }
    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("payment_Data");
        driver = new GuiDrivers();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }


}
