package testscript;

import org.testng.annotations.Test;

public class GoogleHomePage_test extends TestBase {
    @Test(description = "TC0001 - Verify Google homepage and input search term")
    public void tc0001_GoogleHomePage() {
        homePage()
                .verifyHomePage()
                .inputSearchTerm("Test abc")
                .clickOnSearchButton();
    }

    @Test(description = "TC0002 - Verify Google homepage and input search term")
    public void tc0002_GoogleHomePage() {
        homePage()
                .verifyHomePage()
                .inputSearchTerm("Test abc")
                .clickOnSearchButton();
    }
}