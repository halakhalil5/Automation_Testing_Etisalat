package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class RechargePage extends BasePage{
    public RechargePage(AndroidDriver driver) {
        super(driver);
    }

    private final By akwakartButton = By.xpath("//android.widget.TextView[@resource-id=\"com.etisalat:id/tvName\" and @text=\"Akwa Kart\"]\n");

    public AkwaKartPage akwakartButton(){
        click(akwakartButton, "akwart button clicked");
        return new AkwaKartPage(driver);
    }
}
