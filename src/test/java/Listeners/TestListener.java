package Listeners;

import ai.planz.dev.Browsers.BrowserManager;
import ai.planz.dev.Logs.AllureReportManager;
import ai.planz.dev.Logs.WriteLogs;
import ai.planz.dev.helpers.EvidenceHelper;
import ai.planz.dev.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onFinish(ITestContext result) {

    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
        // Log đường dẫn chụp màn hình để xác minh
        WriteLogs.info("Screenshot path: " + PropertiesHelper.getValue("SCREENSHOT_PATH"));

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        WriteLogs.error("Test failed: " + result.getName());
        try {
            WebDriver driver = BrowserManager.getDriver();
            EvidenceHelper.captureScreenshot(driver, result.getName());
        } catch (Exception e) {
            WriteLogs.error("Exception while taking screenshot: " + e.getMessage());
        }
        //Allure Report
        AllureReportManager.saveTextLog(result.getName() + " is failed.");
        AllureReportManager.saveScreenshotPNG();
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        WriteLogs.info("Test skipped: " + result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WriteLogs.info("Test passed: " + result.getName());

    }
}