package com.automationExercise.utils.reports;

import com.automationExercise.utils.dataReader.PropertyReader;
import com.automationExercise.utils.logs.LogsManager;
import com.automationExercise.utils.reports.AllureConstants;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;

import java.io.File;

public class AllureEnvironmentManager {
    public static void setEnvironmentVariables() {
        // Use a null-safe method to get system properties
        String osName = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");

        // The getProperty() method from PropertyReader should be used for custom properties
        String browserType = PropertyReader.getProperty("browserType");
        String executionType = PropertyReader.getProperty("executionType");
        String baseUrlWeb = PropertyReader.getProperty("baseUrlWeb");

        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", osName != null ? osName : "Unknown")
                        .put("Java Version", javaVersion != null ? javaVersion : "Unknown")
                        .put("Browser", browserType != null ? browserType : "Unknown")
                        .put("Execution Type", executionType != null ? executionType : "Unknown")
                        .put("URL", baseUrlWeb != null ? baseUrlWeb : "Unknown")
                        .build(),
                AllureConstants.RESULTS_FOLDER.toFile().getAbsolutePath()
        );

        LogsManager.info("Allure environment variables set.");
        AllureBinaryManager.downloadAndExtract();
    }
}