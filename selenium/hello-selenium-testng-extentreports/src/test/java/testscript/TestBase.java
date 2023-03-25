package testscript;

import configuration.TestConfigs;
import objects.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import reporting.Reporter;
import selenium.DriverFactory;
import testbase.TestManager;
import testbase.UnitTestBase;

import java.lang.reflect.Method;

public class TestBase extends UnitTestBase {

    @BeforeMethod(description = "Open test page")
    protected void beforeMethod(Method method) {
        String className = this.getClass().getSimpleName().replaceFirst("Test_", "");
        String methodName = method.getName().replaceFirst("tc", "TC");

        TestManager.startTestCase("[" + className + "] " + methodName);

        String testSiteURL = TestConfigs.getTestSiteURL();
        DriverFactory.getDriver().get(testSiteURL);
        Reporter.pass("Open test page [" + testSiteURL + "]");
    }

    @AfterMethod(description = "End test case")
    protected void afterMethod() {
        TestManager.endTestCase();
    }

    protected HomePage homePage() {
        return new HomePage();
    }
}
