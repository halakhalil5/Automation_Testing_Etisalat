package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.Logger;

public class UsagePage extends BasePage {
    public UsagePage(AndroidDriver driver) {
        super(driver);
    }

    private final By plan= AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.etisalat:id/tvPlan\").instance(0)");

    public boolean isPlanNameCorrect(String expectedName) {
        try {
            String actualName = driver.findElement(plan).getAttribute("text");

            if (actualName.equals(expectedName)) {
                Logger.pass("Plan name is correct: " + expectedName);
                return true;
            } else {
                Logger.fail("Plan name is not correct. Expected: " + expectedName + " but found: " + actualName);
                return false;
            }
        } catch (Exception e) {
            Logger.fail("Could not find plan element. Expected name: " + expectedName + ". Error: " + e.getMessage());
            return false;
        }
    }

}
