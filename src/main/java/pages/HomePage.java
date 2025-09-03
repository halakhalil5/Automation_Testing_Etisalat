package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ExtentTestManager;

public class HomePage extends BasePage {

    private By welcomeName = By.id("com.etisalat:id/nameTv");
    private final By morebutton= By.id("com.etisalat:id/nav_more");
    private final By usagebutton= By.id("com.etisalat:id/arrowIv");
    private final By rechargebutton= By.id("com.etisalat:id/arrow");
    private final By worldofdeals= By.xpath("//android.widget.TextView[@resource-id=\"com.etisalat:id/tv_title\" and @text=\"World of Deals\"]");
    private final By benefitsbutton= By.id("com.etisalat:id/nav_benifets");
    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isHomePageDisplayed() {

        return isElementDisplayed(welcomeName);
    }

    public MorePage clickMoreButton() {
        click(morebutton, "more button clicked");

        return new MorePage(driver);
    }


    public HomePage waitUntilPageLoads() {
        waitForElementToBeClickable(morebutton, 10, "HomePage loaded successfully"); // waits max 10 seconds
//        ExtentTestManager.getTest().pass("HomePage loaded successfully");
        return this;
    }

    public UsagePage clickUsageButton() {
        click(usagebutton, "usage button clicked");
        return new UsagePage(driver);
    }

    public RechargePage clickRechargeButton(){
        click(rechargebutton, "recharge button clicked");
        return new RechargePage(driver);
    }

    public WorldofDealsPage clickonworldofdeals(){
        scrollToElement(worldofdeals);
        click(worldofdeals, "world of deals button clicked");
        return new WorldofDealsPage(driver);

    }
    public BenefitPage clickonbenefit(){
        click(benefitsbutton, "benefit button clicked");
        return new BenefitPage(driver);
    }
}
