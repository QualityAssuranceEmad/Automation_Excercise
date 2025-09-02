package com.automationExercise.listeners;

import com.automationExercise.FilesUtils;
import com.automationExercise.drivers.UiTest;
import com.automationExercise.drivers.WebDriverProvider;
import com.automationExercise.media.ScreenRecordManager;
import com.automationExercise.media.ScreenshotsManager;
import com.automationExercise.utils.dataReader.PropertyReader;
import com.automationExercise.utils.logs.LogsManager;
import com.automationExercise.utils.reports.*;
import com.automationExercise.validations.Validation;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class TestNGListeners implements ISuiteListener, IExecutionListener, IInvokedMethodListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
        suite.getXmlSuite().setName("Automation Exercise");
    }

    @Override
    public void onExecutionStart() {
        LogsManager.info("Test Execution started");
        try {
            cleanTestOutputDirectories();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogsManager.info("Directories cleaned");
        createTestOutputDirectories();
        LogsManager.info("Directories created");
        PropertyReader.loadProperties();
        LogsManager.info("Properties loaded");
        AllureEnvironmentManager.setEnvironmentVariables();
        LogsManager.info("Allure environment set");
    }

    @Override
    public void onExecutionFinish() {
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.info("Test Execution Finished");
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (testResult.getInstance().getClass().isAnnotationPresent(UiTest.class)) {
                ScreenRecordManager.startRecording();
            }
            LogsManager.info("Test Case " + testResult.getName() + " started");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!method.isTestMethod()) return;

        WebDriver driver = null;

        // Try to get WebDriver from test class if it implements WebDriverProvider
        if (testResult.getInstance() instanceof WebDriverProvider provider) {
            driver = provider.getWebDriver();
        }

        // Stop recording if UiTest is present
        if (testResult.getInstance().getClass().isAnnotationPresent(UiTest.class)) {
            ScreenRecordManager.stopRecording(testResult.getName());
            AllureAttachmentManager.attachRecords(testResult.getName());
        }

        // Take screenshot (always if driver available)
        if (driver != null) {
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS ->
                        ScreenshotsManager.takeFullPageScreenshot(driver, "passed-" + testResult.getName());
                case ITestResult.FAILURE ->
                        ScreenshotsManager.takeFullPageScreenshot(driver, "failed-" + testResult.getName());
                case ITestResult.SKIP ->
                        ScreenshotsManager.takeFullPageScreenshot(driver, "skipped-" + testResult.getName());
            }
        } else {
            LogsManager.warn("No WebDriver available for screenshots in: " + testResult.getName());
        }

        // Run soft assertion checks
        Validation.assertAll(testResult);

        // Attach logs always
        AllureAttachmentManager.attachLogs();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " skipped");
    }

    // === Helpers ===
    private void cleanTestOutputDirectories() throws IOException {
        // clean allure results but keep history
        File resultsDir = AllureConstants.RESULTS_FOLDER.toFile();
        if (resultsDir.exists()) {
            for (File file : resultsDir.listFiles()) {
                if (!file.getName().equals("history")) {
                    FileUtils.deleteQuietly(file);
                }
            }
        }
        FilesUtils.cleanDirectory(new File(ScreenshotsManager.SCREENSHOTS_PATH));
        FilesUtils.cleanDirectory(new File(ScreenRecordManager.RECORDINGS_PATH));
        FilesUtils.cleanDirectory(new File(LogsManager.LOGS_PATH));
        FilesUtils.forceDelete(new File(LogsManager.LOGS_PATH + File.separator + "logs.log"));
    }

    private void createTestOutputDirectories() {
        FilesUtils.createDirectory(ScreenshotsManager.SCREENSHOTS_PATH);
        FilesUtils.createDirectory(ScreenRecordManager.RECORDINGS_PATH);
    }
}
