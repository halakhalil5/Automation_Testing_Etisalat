package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WorldofDealsPage extends BasePage {
    public WorldofDealsPage(AndroidDriver driver) {
        super(driver);
    }

    private final By foodbev= By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.etisalat:id/rvCategories\"]/android.view.ViewGroup[2]");



    public WorldofDealsPage isWorldofdealspagedisplayed(){
        waitForElementToBeClickable(foodbev, 10,"World of deals Page Displayed");
        return this;
    }

    public FoodandBevPage clickonfoodandbev(){
        click(foodbev,"clicked on food & beverage button");
        return new FoodandBevPage(driver);
    }
}
