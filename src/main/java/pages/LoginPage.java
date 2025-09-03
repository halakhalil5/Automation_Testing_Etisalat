package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {
    private final By usernameField = AppiumBy.id("com.etisalat:id/etLogin");
    private final By ProceedButton   = AppiumBy.id("com.etisalat:id/btnProceedLogin");

    public LoginPage(AndroidDriver driver) {
        super(driver);

    }

    public LoginPage enterUsername(String username) {
        type(usernameField, username, "Username typed "+ username);
        return this;
    }

    public PasswordPage clickOnProceedButton() {
        click(ProceedButton, "Proceed Button is Clicked");
        return new PasswordPage(driver);
    }


}
