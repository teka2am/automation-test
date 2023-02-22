package testscript;

import configuration.TestConfigs;
import objects.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import selenium.DriverFactory;
import testbase.Reporting;
import testbase.UnitTestBase;

public class TestBase extends UnitTestBase {

    @BeforeMethod(description = "Open test page")
    protected void setup_method() {
        String testSiteURL = TestConfigs.getTestSiteURL();
        DriverFactory.getDriver().get(testSiteURL);
        Reporting.info("Open test page [" + testSiteURL + "]");
    }

    protected HomePage homePage() {
        return new HomePage();
    }
}
