package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;


public class PasswordPage extends BasePage {

    private final By passwordField = By.id("com.etisalat:id/password_input");
    private final By loginButton = By.id("com.etisalat:id/proceed_login_btn");


    public PasswordPage(AndroidDriver driver) {
        super(driver);
    }

    public PasswordPage enterPassword(String password) {
        type(passwordField, password, "Password is typed");
        return this;
    }

    public HomePage clickLogin() {
        click(loginButton, "Login Button is clicked");
        return new HomePage(driver);
    }
}
