package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.utils.dataReader.PropertyReader;
import com.automationExercise.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private final GuiDrivers driver;

    public ProductsPage(GuiDrivers driver) {
        this.driver = driver;
    }

    //Variables
    private final String productPage = "/products";
    //Locators
    private final By allProductsLabel = By.xpath("//h2[contains(@class,'title text-center')]");
    private final By searchField = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By itemAddedLabel = By.xpath("//p[normalize-space()='Your product has been added to cart.']");
    private final By viewCartButton = By.cssSelector(".modal-body a[href='/view_cart']");
    private final By continueShoppingButton = By.xpath("//button[.='Continue Shopping']");
    private final By products = By.cssSelector(".features_items .col-sm-4");
    private final By viewProductOfFirstProduct = By.cssSelector("a[href='/product_details/1']");


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
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productPage);
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

    @Step("view product of first product")
    public ProductsPage viewProductOfFirstProduct() {
        driver.element().clicing(viewProductOfFirstProduct);
        return this;
    }

    @Step("Wait until at least one product is visible")
    public ProductsPage waitUntilAtLeastOneProductIsVisible() {
        List<WebElement> productElements = driver.findElements(By.cssSelector("div.features_items div.col-sm-4"));
        int productCount = productElements.size();

        LogsManager.info("Total products found: " + productCount);

        if (productCount == 34) {
            LogsManager.info("✅ The page contains exactly 34 products.");
        } else {
            LogsManager.warn("❌ The page does not contain 34 products. Found " + productCount);
        }

        List<String> productNames = new java.util.ArrayList<>();
        LogsManager.info("\n--- Product Names ---");
        for (WebElement product : productElements) {
            try {
                WebElement productNameElement = product.findElement(By.tagName("p"));
                String productName = productNameElement.getText();
                LogsManager.info(productName);
                productNames.add(productName);
            } catch (Exception e) {
                LogsManager.error("Could not find product name for an element.", e.getMessage());
            }
        }

        // ✅ Attach all product names to Allure
        com.automationExercise.utils.reports.AllureAttachmentManager.attachProductNames(
                "Product List from Products Page",
                productNames
        );

        // Example action (optional)
        viewProduct("Blue Top");

        return this;
    }


    //Validations
    @Step("Verify All Products Label is Visible")
    public ProductsPage isAllProductsLabelVisible() {
        driver.verification().isElementVisible(allProductsLabel);
        return this;
    }

    @Step("Verify Product Details for {productName} and {productPrice} are Correct")
    public ProductsPage validateProductDetails(String productName, String productPrice) {
        // تأكد المنتج ظهر في الصفحة قبل hover
        String actualProductName = driver.element().getText(productName(productName));
        String actualProductPrice = driver.element().getText(productPrice(productName));

        LogsManager.info("Validating Product Details For: " + actualProductName + " with Price " + productPrice);

        driver.validation().Equals(actualProductName, productName, "Product name is not correct");
        driver.validation().Equals(actualProductPrice, productPrice, "Product price is not correct");
        return this;
    }

    @Step("Verify Item Added To Cart Label is Visible")
    public ProductsPage isItemAddedToCartLabelVisible(String itemLabel) {
        {
            String actualText = driver.element().getText(itemAddedLabel);
            driver.verification().Equals(actualText, itemLabel, "Item added to cart label is not visible");
            return this;
        }
    }
}
