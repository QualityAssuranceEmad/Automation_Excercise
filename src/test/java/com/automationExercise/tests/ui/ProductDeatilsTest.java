package com.automationExercise.tests.ui;
import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.pages.components.NavigationBarComponent;
import com.automationExercise.pages.components.ProductsPage;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UiTest
public class ProductDeatilsTest extends BaseTest {
    // Write your test cases here
    @Test
    public void productDetailsTestWithOutLogin() {
        new ProductsPage(driver).navigate()
                .viewProductDetails(testData.getJsonData("productDetails.productName"))
                .validateProductDetails(testData.getJsonData("productDetails.productName"),
                        testData.getJsonData("productDetails.productPrice"));
    }
    @Test
    public void addReviewToProductWithOutLogin() {
        new ProductsPage(driver).navigate()
                .viewProductDetails(testData.getJsonData("productDetails.productName"))
                .enterReviewDetails(testData.getJsonData("reviews.reviewName"),
                        testData.getJsonData("reviews.reviewEmail"),
                        testData.getJsonData("reviews.comment"))
                .clickOnSubmitReview()
                .isReviewSuccessMessageVisible(testData.getJsonData("Messages.reviewAdded"));
    }
    //Configurations
    @BeforeClass
    public void beforeClass() {

        testData = new JsonReader("ProductDetailsAndReview_Data");
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
