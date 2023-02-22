package testbase;

import configuration.TestConfigs;
import logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import selenium.DriverFactory;

@Listeners({AllureListener.class})
public class UnitTestBase {
    @BeforeClass(description = "Setup webdriver")
    public void setup(){
        DriverFactory.initDriver();
    }

    @AfterClass(description = "Close browser")
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
