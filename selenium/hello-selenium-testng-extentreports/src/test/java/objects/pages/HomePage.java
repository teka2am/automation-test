package objects.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reporting.Reporter;
import selenium.DriverFactory;
import selenium.WebActions;

public class HomePage extends WebActions {
    public HomePage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    //OBJECTS
    @FindBy(xpath = "//input[@name='q']")
    private WebElement inputSearchBox;

    @FindBy(xpath = "//div[not(@style='display:none')]/div/div/center/input[@name='btnK']")
    private WebElement buttonSearch;

    //ACTIONS
    public HomePage inputSearchTerm(String searchTerm) {
        inputText(inputSearchBox, "inputSearchBox", searchTerm);
        return this;
    }

    public HomePage clickOnSearchButton() {
        click(buttonSearch, "buttonSearch");
        return this;
    }

    //VALIDATION
    public HomePage verifyHomePage(){
        verifyElementPresent(inputSearchBox, "inputSearchBox");
        verifyElementPresent(buttonSearch, "buttonSearch");
        return this;
    }
}
