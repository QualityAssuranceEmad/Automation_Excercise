package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import com.automationExercise.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {
    private final GuiDrivers driver;

    public ProductsPage(GuiDrivers driver) {
        this.driver = driver;
    }
//Variables
    private   final String productPage="/products";
    //Locators
    private final By searchField = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By itemAddedLabel = By.xpath("//p[normalize-space()='Your product has been added to cart.']");
    private final By viewCartButton = By.cssSelector(".modal-body a[href='/view_cart']");
    private final By continueShoppingButton = By.xpath("//button[.='Continue Shopping']");

    //Dynamic locators
    private By productName(String productName) {
        return By.xpath("//div[@class='productinfo text-center']/p[.='" + productName + "']");
        //div[@class='productinfo text-center']/p[.='Blue Top']
    }

    private By productPrice(String productName) {
        return By.xpath("//div[@class='productinfo text-center']/p[.='" + productName + "']//preceding-sibling::h2");
    }//div[@class='productinfo text-center']/p[.='Blue Top']/preceding-sibling::h2

    private By hoverOnProduct(String productName) {
        return By.xpath("//div[@class='productinfo text-center']/p[.='" + productName + "']");
    }

    private By addToCartButton(String productName) {
        return By.xpath("//div[@class='productinfo text-center']/p[.='" + productName + "']//following-sibling::a");
    }
    private By viewProduct(String productName) {
        return By.xpath("//p[.='" + productName + "']//following::div[@class='choose'][1]");
    }



    //Actions
    @Step("Navigate to Products Page")
public ProductsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+productPage);
        return this;
    }
    @Step("Search For Product: {productName}")
public ProductsPage searchProduct(String productName) {
        driver.element().typing(searchField, productName)
                .clicing(searchButton);
        return this;
    }
    @Step("Add Product '{productName}' to Cart")
public ProductsPage clickOnAddToCart(String productName) {
        driver.element()
                .clicing(addToCartButton(productName));
        return this;

    }
    @Step("Click on View Products")
    public ProductDeatilsPage viewProductDetails(String productName) {
        driver.element().clicing(viewProduct(productName));
        return new ProductDeatilsPage(driver);
    }
    @Step("Click on View Cart ")
    public ProductsPage clickViewCart() {
        driver.element().clicing(viewCartButton);
            return this;
    }
    @Step("Click on Continue Shopping Button")
    public ProductsPage clickOnContinueShopping() {
        driver.element().clicing(continueShoppingButton);
        return this;
    }
    //Validations

    @Step("Verify Product Details for {productName} and {productPrice} are Correct")
    public ProductsPage validateProductDetails(String productName,String productPrice) {
        // تأكد المنتج ظهر في الصفحة قبل hover
        String actualProductName = driver.element().getText(productName(productName));
        String actualProductPrice = driver.element().getText(productPrice(productName));

        LogsManager.info("Validating Product Details For: "+actualProductName+" with Price "+productPrice);

        driver.validation().Equals(actualProductName, productName, "Product name is not correct");
        driver.validation().Equals(actualProductPrice, productPrice, "Product price is not correct");
        return this;
    }
    @Step("Verify Item Added To Cart Label is Visible")
    public  ProductsPage isItemAddedToCartLabelVisible(String itemLabel) {
        {
            String actualText = driver.element().getText(itemAddedLabel);
            driver.verification().Equals(actualText, itemLabel, "Item added to cart label is not visible");
            return this;
        }
    }
}
