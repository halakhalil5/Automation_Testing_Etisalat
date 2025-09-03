package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class BenefitPage extends BasePage{
    public BenefitPage(AndroidDriver driver) {
        super(driver);
    }

    private final By worldofdealsbutton=By.xpath("//android.widget.TextView[@resource-id=\"com.etisalat:id/tvTestAutomation\" and @text=\"164\"]");
    private final By dailybutton= By.xpath("(//android.widget.ImageView[@resource-id=\"com.etisalat:id/img_card\"])[1]");

    public BenefitPage isbenefitpagedisplayed(){
        waitForElementToBeClickable(dailybutton, 10,"Benefit Page Displayed");
        return this;
    }

    public WorldofDealsPage clickonworldofdeals(){
        scrollToElement(worldofdealsbutton);
        click(worldofdealsbutton, "world of deals button clicked");
        return new WorldofDealsPage(driver);
    }
}
