package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {
    private final GuiDrivers driver;

    public CheckoutPage(GuiDrivers driver) {
        this.driver = driver;
    }
    private final String checkoutPageEndPoint = "/checkout";

    //Locators
    private final By validateCheckOutLabel = By.xpath("//h4[@class='modal-title w-100']");
    private final By continueOnCartButton = By.xpath("//button[contains(.,'Continue On Cart')]");
    private final By registerLoginButton = By.xpath("//u[contains(.,'Register / Login')]");
    private final By addComment=By.xpath("//*[@class='form-control']");

    private final By placeOrderButton = By.xpath("//a[contains(.,'Place Order')]");
    //Delivery Address Locators
    private final By deliveryName = By.xpath("//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']");
    private final By deliveryCompany = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][1]");
    private final By deliveryAddress1 = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][2]");
    private final By deliveryAddress2 = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][3]");
    private final By deliveryCity = By.xpath("//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']");
    private final By deliveryCountry = By.xpath("//ul[@id='address_delivery']//li[@class='address_country_name']");
    private final By deliveryPhone = By.xpath("//ul[@id='address_delivery']//li[@class='address_phone']");
    //Billing Address Locators
    private final By billingName = By.xpath("//ul[@id='address_invoice']//li[@class='address_firstname address_lastname']");
    private final By billingCompany = By.xpath("//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][1]");
    private final By billingAddress1 = By.xpath("//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][2]");
    private final By billingAddress2 = By.xpath("//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][3]");
    private final By billingCity = By.xpath("//ul[@id='address_invoice']//li[@class='address_city address_state_name address_postcode']");

    private final By billingCountry = By.xpath("//ul[@id='address_invoice']//li[@class='address_country_name']");
    private final By billingPhone = By.xpath("//ul[@id='address_invoice']//li[@class='address_phone']");

    //Actions

@Step("navigate to Checkout Page")
    public CheckoutPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + checkoutPageEndPoint);
        return this;
    }
    @Step("add comment on my order")
    public CheckoutPage addCommentOnMyOrder(String comment)
    {
        driver.element().typing(addComment,comment);
        return this;
    }
    @Step("click on place order button")
    public PaymentPage clickOnPlaceOrderButton() {
        driver.element().clicing(placeOrderButton);
        return new PaymentPage(driver);
    }

    ///Validation
    @Step("validate Checkout page Your delivery address")
    public CartPage validateYourDeliveryAddress(String title,String fName,String lName, String company, String address1,
                                                   String address2, String city,String state,String zepCode, String country, String phone) {
        String actualName = driver.element().getText(deliveryName);
        String actualCompany = driver.element().getText(deliveryCompany);
        String actualAddress1 = driver.element().getText(deliveryAddress1);
        String actualAddress2 = driver.element().getText(deliveryAddress2);
        String actualCity = driver.element().getText(deliveryCity);
        String actualCountry = driver.element().getText(deliveryCountry);
        String actualPhone = driver.element().getText(deliveryPhone);
        driver.validation().Equals(actualName, (title+". "+fName+" "+lName), "Delivery Name Not Match");
        driver.validation().Equals(actualCompany, company, "Delivery Company Not Match");
        driver.validation().Equals(actualAddress1, address1, "Delivery Address1 Not Match");
        driver.validation().Equals(actualAddress2, address2, "Delivery Address2 Not Match");
        driver.validation().Equals(actualCity, (zepCode + " " +city+" " + state), "Delivery City Not Match");
        driver.validation().Equals(actualCountry, country, "Delivery Country Not Match");
        driver.validation().Equals(actualPhone, phone, "Delivery Phone Not Match");
        return new CartPage(driver);
    }
    @Step("validate Checkout page Your billing address")
    public CartPage validateYourBillingAddress(String title,String fName,String lName, String company, String address1,
                                                   String address2, String city,String state,String zepCode, String country, String phone) {
        String actualName = driver.element().getText(billingName);
        String actualCompany = driver.element().getText(billingCompany);
        String actualAddress1 = driver.element().getText(billingAddress1);
        String actualAddress2 = driver.element().getText(billingAddress2);
        String actualCity = driver.element().getText(billingCity);
        String actualCountry = driver.element().getText(billingCountry);
        String actualPhone = driver.element().getText(billingPhone);
        driver.validation().Equals(actualName, (title+". "+fName+" "+lName), "Billing Name Not Match");
        driver.validation().Equals(actualCompany, company, "Billing Company Not Match");
        driver.validation().Equals(actualAddress1, address1, "Billing Address1 Not Match");
        driver.validation().Equals(actualAddress2, address2, "Billing Address2 Not Match");
        driver.validation().Equals(actualCity, (zepCode + " " +city+" " + state), "Billing City Not Match");
        driver.validation().Equals(actualCountry, country, "Billing Country Not Match");
        driver.validation().Equals(actualPhone, phone, "Billing Phone Not Match");
        return new CartPage(driver);
    }
}
