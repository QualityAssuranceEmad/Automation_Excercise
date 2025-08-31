package com.automationExercise.pages.components;

import com.automationExercise.drivers.GuiDrivers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Logout {
    private final GuiDrivers driver;
    public Logout(GuiDrivers driver) {
        this.driver = driver;
    }

    private final By logout = By.xpath("//a[i[@class='fa fa-lock']]");
    @Step("Click on Logout button")
    public Logout clickLogout() {
        driver.element().clicing(logout);
        return new Logout(driver);
    }
}
