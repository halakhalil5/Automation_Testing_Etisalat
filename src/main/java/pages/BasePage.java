package pages;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import utils.ExtentTestManager;
import utils.Logger;
import utils.TestListener;

public class BasePage {

    protected AndroidDriver driver;


    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        Logger.setDriver(driver);
    }

    protected WebElement waitForElement(By locator) {
        return waitForElement(locator, 10);
    }


    protected WebElement waitForElement(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator, String stepName) {
        try {
            Logger.pass(stepName);
            waitForElement(locator).click();
        } catch (Exception e) {
            Logger.fail("Failed at step: " + stepName + " | Locator: " + locator);
            throw new RuntimeException(e);
        }
    }

    protected void type(By locator, String text, String stepName) {
        try {
            waitForElement(locator).sendKeys(text);
            Logger.pass(stepName + " -> " + text);
        } catch (Exception e) {
            Logger.fail("Failed to type at step: " + stepName + " | Locator: " + locator);
            throw new RuntimeException(e);
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return waitForElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void handlePopup(By popupLocator, By closeButtonLocator) {
        if (isElementDisplayed(popupLocator)) {
            click(closeButtonLocator, "Popup closed");
            Logger.log("Popup detected and closed");
        } else {
            Logger.log("No popup displayed");
        }
    }


//    //
//    public WebElement scrollToElement(By locator) {
//        WebElement element = null;
//        try {
//            element = driver.findElement(locator);
//        } catch (Exception e) {
//            // keep scrolling until found
//            driver.findElement(
//                    AppiumBy.androidUIAutomator(
//                            "new UiScrollable(new UiSelector().scrollable(true))" +
//                                    ".scrollIntoView(new UiSelector().resourceId(\""
//                                    + locator.toString().split(": ")[1] + "\"))"
//                    )
//            );
//            element = driver.findElement(locator);
//        }
//        return element;
//    }


    public void scrollToElement(By locator) {
        try {
            driver.findElement(locator);
        } catch (Exception e) {
            driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(15)" +
                                    ".scrollIntoView(new UiSelector().resourceIdMatches(\"" + locator + "\"))"
                    )
            );
        }
    }


    protected void waitForElementToBeClickable(By locator, int timeout, String stepName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            Logger.pass("Element clickable: " + stepName);
        } catch (Exception e) {
            Logger.fail("Element not clickable: " + stepName + " | Locator: " + locator.toString());
            throw new RuntimeException(e);
        }
    }


//    private void captureScreenshot(String stepName) {
//        try {
//            File srcFile = driver.getScreenshotAs(OutputType.FILE);
//            String path = "target/screenshots/" + System.currentTimeMillis() + ".png";
//            Files.copy(srcFile.toPath(), Paths.get(path));
//            ExtentTestManager.getTest().addScreenCaptureFromPath(path, stepName);
//        } catch (Exception ex) {
//            System.out.println("Could not capture screenshot: " + ex.getMessage());
//        }
//    }


}
