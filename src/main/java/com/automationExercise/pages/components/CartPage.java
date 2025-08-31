package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private final GuiDrivers driver;
    public NavigationBarComponent navigationBarComponent;

    public CartPage(GuiDrivers driver) {
        this.driver = driver;
    }
    private String cartPageEndPoint = "/view_cart";

    //Locators
    private final By proceedToCheckoutButton = By.xpath("//div[@class='col-sm-6']//a[contains(text(),'Proceed To Checkout')]");
    private final By emptyCartMessage = By.xpath("//*[@id=\"empty_cart\"]/p/b");
    //Dynamic Locator
    private By productName(String productName) {
        return By.xpath("(//h4/a[.='"+productName+"'])[1]");
    }
    private By productPrice(String productName) {
        return By.xpath("(//h4/a[.='"+productName+"']//following::td[@class='cart_price']/p)[1]");
    }
    private By productQuantityValue(String productName) {
        return By.xpath("(//h4/a[.='"+productName+"']//following::td[@class='cart_quantity']/button)[1]");
    }
    private By productTotalPrice(String productName) {
        return By.xpath("(//h4/a[.='"+productName+"']//following::td[@class='cart_total']/p)[1]");
    }
    private By removeProductButton(String productName) {
        return By.xpath("(//h4/a[.='"+productName+"']//following::td[@class='cart_delete']/a)[1]");
    }


    //Actions
@Step("navigate to Cart Page")
    public CartPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+ cartPageEndPoint);
        return this;
    }
    @Step("click on Proceed To Checkout button")
    public CheckoutPage clickOnProceedToCheckoutButton() {
        driver.element().clicing(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }
    @Step("remove product: '{productName}' from cart")
    public CartPage removeProductFromCart(String productName) {
        driver.element().clicing(removeProductButton(productName));
        return this;
    }

    //Validations
    @Step("validate product: '{productName}' with price: '{price}', quantity: '{quantity}' " +
            "and total price: '{totalPrice}' in cart")
    public CartPage validateProductInCart(String productName, String price, String quantity, String totalPrice) {
        String actualProductName = driver.element().getText(productName(productName));
        String actualProductPrice = driver.element().getText(productPrice(productName));
        String actualProductQuantity = driver.element().getText(productQuantityValue(productName));
        String actualProductTotalPrice = driver.element().getText(productTotalPrice(productName));
        driver.validation().Equals(actualProductName, productName, "Product Name Not Match");
        driver.validation().Equals(actualProductPrice, price, "Product Price Not Match");
        driver.validation().Equals(actualProductQuantity, quantity, "Product Quantity Not Match");
        driver.validation().Equals(actualProductTotalPrice, totalPrice, "Product Total Price Not Match");
        return this;
    }
    @Step("Validate empty cart message is visible: '{message}'")
    public CartPage isEmptyCartMessageVisible(String message) {
        String actualMessage = driver.element().getText(emptyCartMessage);
        driver.validation().Equals(actualMessage, message, "Message Not Match");
        return this;
    }
}
