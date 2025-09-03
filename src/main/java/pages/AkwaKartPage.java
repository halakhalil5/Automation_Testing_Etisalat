package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class AkwaKartPage extends BasePage{
    public AkwaKartPage(AndroidDriver driver) {
        super(driver);
    }

    private final By textnumber= By.id("com.etisalat:id/cardId_editText");
    private final By rechargebutton= By.id("com.etisalat:id/akwa_kart_rechargeBtn");
    public final By requestpopup= By.xpath("//android.view.ViewGroup");
    public final By closebutton= By.id("com.etisalat:id/btn_close");
    public final By popup= By.id("com.etisalat:id/loadingAnimation");






    public AkwaKartPage editTextNumber(String number){
        type(textnumber, number, "Typed the number :"+number);
        return this;
    }

    public AkwaKartPage recharge(){
        click(rechargebutton, "recharge button clicked");
        return this;
    }

    public boolean isRequestPopupVisible() {
        isElementDisplayed(popup);
        handlePopup(requestpopup, closebutton);
        return true;
    }



}
