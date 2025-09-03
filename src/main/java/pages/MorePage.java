package pages;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MorePage extends BasePage {
    public MorePage(AndroidDriver driver) {
        super(driver);
    }

    private final By SettingsButton = By.xpath("//android.widget.TextView[@resource-id=\"com.etisalat:id/buttonTv\" and @text=\"Settings\"]");

    public SettingPage clicksettings(){
        scrollToElement(SettingsButton);
        click(SettingsButton, "Settings Button is clicked");
        return new SettingPage(driver);
    }
}
