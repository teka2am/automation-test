package selenium;

import configuration.TestConfigs;
import io.github.bonigarcia.wdm.WebDriverManager;
import logging.Log;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import reporting.Reporter;

import java.time.Duration;

public class DriverFactory {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final int defaultPageLoadTimeoutInSecond = 60;

    public static WebDriver initDriver() {
        WebDriver driver;

        switch (TestConfigs.getBrowserName()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                Reporter.warning("Property [" + TestConfigs.TEST_PROPERTIES_BROWSER_NAME + "=" + TestConfigs.getBrowserName() + "] is not defined properly. Default [" + TestConfigs.TEST_PROPERTIES_BROWSER_NAME + "] will be set as [" + TestConfigs.DEFAULT_BROWSER_NAME + "]");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(defaultPageLoadTimeoutInSecond));
        driver.manage().window().maximize();
        tlDriver.set(driver);
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public static String getScreenshot() {
        String screenshotBase64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        return screenshotBase64;
    }

    public static synchronized void quitDriver() {
        try {
            tlDriver.get().getWindowHandles();
            tlDriver.get().quit();
            Log.info("Driver has been quit.");
        } catch (NoSuchSessionException e) {
            Log.info("Currently has no webdriver.");
        }
    }
}
