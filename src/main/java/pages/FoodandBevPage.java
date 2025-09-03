package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class FoodandBevPage extends BasePage{
    public FoodandBevPage(AndroidDriver driver) {
        super(driver);
    }
    private final By filterbutton= By.id("com.etisalat:id/ivFilter");
    private final By lowhighbutton= By.xpath("(//android.widget.RadioButton[@resource-id=\"com.etisalat:id/radioButton\"])[1]");
    private final By highlowbutton= By.xpath("(//android.widget.RadioButton[@resource-id=\"com.etisalat:id/radioButton\"])[2]");
    private final By applybutton=By.id("com.etisalat:id/btnApply");



    public FoodandBevPage isfoodpagedisplayed(){
        waitForElementToBeClickable(filterbutton, 10,"Food & Beverage Page Displayed");
        return this;
    }

    public FoodandBevPage sortlowtohighprices(){
        click(filterbutton,"clicked on filter button");
        click(lowhighbutton,"clicked on low to high button");
        click(applybutton,"clicked on apply button");
        return this;
    }

}
