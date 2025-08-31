package com.automationExercise.tests.ui;
import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.ProductsPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Feature("Ui Product Tests")
@Epic("Automation Exercise")
@Owner("Emad Maher Abd ElHamied")
@Story("Product Tests")
@UiTest
public class ProductTest extends BaseTest {
    @Description("Verify User Can Login Successfully")
    @Test
    public void searchProductWithOutLogin() {
        new ProductsPage(driver)
                .navigate()
                .searchProduct(testData.getJsonData("searchProduct.name"))
                .validateProductDetails(testData.getJsonData("searchProduct.name"),
                        testData.getJsonData("searchProduct.price"));
    }
    @Test
    public void addProductToCart() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .isItemAddedToCartLabelVisible(testData.getJsonData("Messages.cartAdded"));
    }
    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("product_Data");
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
