package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.Reporter;

import java.time.Duration;

public class WebActions {
    private WebDriver driver;
    private WebDriverWait defaultDriverWait;
    private final int defaultWaitingTimeInSeconds = 60;

    public WebActions(){
        driver = DriverFactory.getDriver();
        defaultDriverWait = new WebDriverWait(driver, Duration.ofSeconds(defaultWaitingTimeInSeconds));
    }

    protected void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void focusByJSUsingElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
        } catch (Exception ex) {
            Reporter.fail(ex.getMessage(), ex);
        }
    }

     protected boolean waitElementVisible(WebElement element) {
        try {
            defaultDriverWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException | TimeoutException ex) {
            return false;
        } catch (Exception ex) {
            Reporter.fail(ex.getMessage(), ex);
            throw new AssertionError(ex.getMessage());
        }
    }

    protected boolean waitElementClickable(WebElement webElement) {
        try {
            defaultDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected <T> void inputText(T elementAttr, String elementName, String strInput) {
        try {
            WebElement element = null;
            if (elementAttr.getClass().getName().contains("By")) {
                element = driver.findElement((By) elementAttr);
            } else {
                element = (WebElement) elementAttr;
            }
            waitElementVisible(element);

            element.click();
            element.clear();
            element.sendKeys(strInput);
            if (elementName.toLowerCase().contains("password")) {
                strInput = "********";
            }
            Reporter.pass("Input value [" + strInput + "] into [" + elementName + "]");
        } catch (Exception ex) {
            Reporter.fail(ex.getMessage(), ex);
        }
    }

    protected <T> void click(T elementAttr, String elementName) {
        try {
            WebElement element = null;
            if (elementAttr.getClass().getName().contains("By")) {
                element = driver.findElement((By) elementAttr);
            } else {
                element = (WebElement) elementAttr;
            }
            waitElementVisible(element);
            waitElementClickable(element);

            focusByJSUsingElement(element);
            element.click();
            sleep(1000);
            Reporter.pass("Clicked on [" + elementName + "] successfully");
        } catch (Exception ex) {
            Reporter.fail("Failed to click on element!", ex);
        }
    }

    protected void verifyElementPresent(WebElement element, String elementName) {
        try {
            defaultDriverWait.until(ExpectedConditions.visibilityOf(element));
            Reporter.pass("[" + elementName + "] presences on page correctly");
        } catch (Exception ex) {
            Reporter.fail(ex.getMessage(), ex);
        }
    }
}
