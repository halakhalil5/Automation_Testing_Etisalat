package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import utils.ExtentManager;



public class BaseTest {
    public AndroidDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;



    @BeforeSuite
    public void startReport(){
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setup(ITestContext context) throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAppPackage("com.etisalat");
        options.setAppActivity("com.etisalat.view.login.MainLoginActivity");
        options.setAutomationName("UiAutomator2");
        options.setNoReset(true);

        driver = new AndroidDriver(new URL(appiumServerUrl), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 🔹 Make driver available to TestListener
        context.setAttribute("driver", driver);
    }






    @AfterMethod
    public void close() {
        driver.quit();
    }
    @AfterSuite
    public void tearDownReport() {
        // Flush the report once all tests are done
        if (extent != null) {
            extent.flush();
        }
    }

}
