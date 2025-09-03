package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SettingPage extends BasePage{

    public SettingPage(AndroidDriver driver) {
        super(driver);
    }

    private final By SignOutButton = By.id("com.etisalat:id/btnLogout");

    public LoginPage SignOut(){
        click(SignOutButton, "Sign Out Button is clicked");
        handlePopup(By.id("android:id/button1"), By.id("android:id/button1"));
        return new LoginPage(driver);

    }
}
