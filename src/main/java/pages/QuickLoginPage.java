package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class QuickLoginPage extends BasePage {
    private final By QuickLoginButton   = AppiumBy.id("com.etisalat:id/btnQuickLogin");


    public QuickLoginPage(AndroidDriver driver) {
        super(driver);

    }

    public HomePage clickOnQuickLoginButton() {
        click(QuickLoginButton, "Quick Login Button is clicked");
        return new HomePage(driver);
    }

}







