package testbase;

import io.qameta.allure.Attachment;
import logging.Log;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import selenium.DriverFactory;

public class AllureListener implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "SCREENSHOT")
    public byte[] saveFailureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Failure Message", type = "text/plain")
    public static String saveTextMessage(String message) {
        return message;
    }

    @Override
    public void onStart(ITestContext context) {
        Log.info("++++++++++ START TEST: [" + context.getName() + "] ++++++++++");
        context.setAttribute("WebDriver", DriverFactory.getDriver());
    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            DriverFactory.getDriver().getWindowHandles();
            DriverFactory.getDriver().quit();
            Log.info("Driver has been quit.");
        } catch (NoSuchSessionException e) {
        }

        Log.info("++++++++++ END TEST: [" + context.getName() + "] ++++++++++" + System.getProperty("line.separator"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("START TEST: [" + getTestMethodName(result) + "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("TEST SUCCESS: [" + getTestMethodName(result) + "]" + System.getProperty("line.separator"));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        String message = "Screenshot captured at browser window [" + driver.getTitle() + " - " + driver.getCurrentUrl() + "]";
        if (driver != null) {
            saveFailureScreenshot(driver);
            Log.error("Failure message: ", result.getThrowable().getMessage());
            Log.info(message);
            saveTextMessage(message);
        }
        Log.info("TEST FAILED: [" + getTestMethodName(result) + "]" + System.getProperty("line.separator"));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.error("Failure message: ", result.getThrowable().getMessage());
        Log.info("TEST SKIPPED: [" + getTestMethodName(result) + "]" + System.getProperty("line.separator"));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("TEST FAILED but it is in defined success ratio: [" + getTestMethodName(result) + "]" + System.getProperty("line.separator"));
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

}
