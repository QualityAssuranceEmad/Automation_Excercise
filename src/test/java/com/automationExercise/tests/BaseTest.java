package com.automationExercise.tests;

import com.automationExercise.drivers.GuiDrivers;
import com.automationExercise.drivers.WebDriverProvider;
import com.automationExercise.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {
protected GuiDrivers driver;
protected JsonReader testData ;





    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
