package com.automationExercise.drivers;

import com.automationExercise.utils.dataReader.PropertyReader;
import com.automationExercise.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;

public class SafariFactory extends AbstractDriver {
    public SafariOptions getOptions() {
        SafariOptions options = new SafariOptions();
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    @Override
    public WebDriver createDriver() {
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless"))
        {
            return new SafariDriver(getOptions());
        } else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URL("http://"+ remoteHost + ":" + remotePort+ "/wd/hub").toURI().toURL(),
                        getOptions()
                );
            }catch (Exception e) {
                LogsManager.error("Error while creating RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Error while creating RemoteWebDriver", e);
            }

        } else {
            LogsManager.error("Invalid execution type: " +
                    PropertyReader.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " +
                    PropertyReader.getProperty("executionType"));

        }
    }
}
