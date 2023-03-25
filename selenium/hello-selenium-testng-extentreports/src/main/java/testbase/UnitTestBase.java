package testbase;

import configuration.TestConfigs;
import logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reporting.ReportManager;
import selenium.DriverFactory;

public class UnitTestBase {
    @BeforeSuite(description = "Setup report")
    public void beforeSuite(){
        ReportManager.startReport();
    }
    @BeforeClass(description = "Setup WebDriver")
    public void beforeClass(){
        DriverFactory.initDriver();
    }

    @AfterClass(description = "Close browser")
    public void afterClass(){
        DriverFactory.quitDriver();
    }

    @AfterSuite(description = "Finish report")
    public void afterSuite(){
        ReportManager.finishing();
    }
}
