package testscript;

import org.testng.annotations.Test;
import testbase.TestManager;

public class GoogleHomePage_Test extends TestBase {
    @Test
    public void tc0001_GoogleHomePage() {
        TestManager.startStep("Verify home page");
        homePage().verifyHomePage();

        TestManager.startStep("Input search term and click button search");
        homePage().inputSearchTerm("Test abc").clickOnSearchButton();
    }

    @Test
    public void tc0002_GoogleHomePage() {
        homePage()
                .verifyHomePage()
                .inputSearchTerm("Test abc")
                .clickOnSearchButton();
    }
}