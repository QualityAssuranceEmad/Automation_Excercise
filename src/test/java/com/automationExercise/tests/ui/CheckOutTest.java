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
public class CheckOutTest extends BaseTest {
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

    @Test(dependsOnMethods = {"testLoginWithRegisteredUser_shouldSucceed","testRegisterNewAccount_shouldSucceed"})
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
    @Test(dependsOnMethods ={"testAddProductToCart_shouldShowInCartPage","testLoginWithRegisteredUser_shouldSucceed","testRegisterNewAccount_shouldSucceed"} )
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
