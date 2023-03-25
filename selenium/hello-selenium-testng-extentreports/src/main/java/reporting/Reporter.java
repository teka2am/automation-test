package reporting;

import com.aventstack.extentreports.MediaEntityBuilder;
import logging.Log;
import org.testng.Assert;
import selenium.DriverFactory;

import static reporting.ReportManager.getCurrentTest;

public class Reporter {

    public static void pass(String message){
        Log.info(message);
        getCurrentTest().pass(message);
    }

    public static void warning(String message){
        Log.warning(message);
        getCurrentTest().warning(message);
    }

    public static void info(String message){
        Log.info(message);
        getCurrentTest().info(message);
    }

    public static void fail(String message){
        String based64ScreenShot = DriverFactory.getScreenshot();
        Log.info(message);
        getCurrentTest().fail(message).fail(MediaEntityBuilder.createScreenCaptureFromBase64String(based64ScreenShot).build());
        Log.info("[Screenshot has been taken]");
        Assert.fail(message);
    }

    public static void fail(String message, Throwable error){
        String based64ScreenShot = DriverFactory.getScreenshot();
        Log.info(message + "::ErrorStackTrace:: " + error.getStackTrace().toString());
        getCurrentTest().fail(message).fail(error).fail(MediaEntityBuilder.createScreenCaptureFromBase64String(based64ScreenShot).build());
        Log.info("[Screenshot has been taken]");
        Assert.fail(message + "::ErrorStackTrace:: " + error.getStackTrace().toString());
    }
}
