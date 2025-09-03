package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import pages.*;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.JsonDataReader;
import utils.TestListener;



@Listeners(utils.TestListener.class)
public class LoginTest extends BaseTest {
    private ExtentReports extent;
    private ExtentTest test;


    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        JsonNode testData = JsonDataReader.readJson("src/test/java/resoures/Data.json");
        JsonNode logins = testData.get("login");

        Object[][] data = new Object[logins.size()][2]; // username + password

        for (int i = 0; i < logins.size(); i++) {
            data[i][0] = logins.get(i).get("username").asText();
            data[i][1] = logins.get(i).get("password").asText();
        }
        return data;
    }


    @Test(priority=1)
    public void verifyQuicklogin() {
        try {
            QuickLoginPage quickPage = new QuickLoginPage(driver);

            HomePage homePage = quickPage.clickOnQuickLoginButton();


            quickPage.handlePopup(By.id("com.android.packageinstaller:id/desc_container"),
                    By.id("com.android.packageinstaller:id/permission_allow_button"));


            homePage.handlePopup(By.id("com.android.packageinstaller:id/desc_container"),
                    By.id("com.etisalat:id/dailyGiftsClose"));
            Assert.assertTrue(homePage.isHomePageDisplayed(), "HomePage was not displayed!");

        } catch (Exception e) {
            System.out.println("Error during test" + e.getMessage());
            e.printStackTrace();

        }
    }

    @Test( priority=2)
    public void SignOut() {
        HomePage homePage = new HomePage(driver);
        homePage.clickMoreButton()
               .clicksettings()
               .SignOut();


    }


    @Test(priority=3)
    public void LoginSignout() throws Exception {
        JsonNode testData = JsonDataReader.readJson("Data.json");
        JsonNode logins = testData.get("login");

        for (JsonNode login : logins) {
            String username = login.get("username").asText();
            String password = login.get("password").asText();
            try {
                LoginPage loginPage = new LoginPage(driver);
                loginPage.enterUsername(username)
                        .clickOnProceedButton()
                        .enterPassword(password)
                        .clickLogin()
                        .waitUntilPageLoads()
                        .clickMoreButton()
                        .clicksettings()
                        .SignOut();

                TestListener.getTest().info("Login completed for user: " + username);

            } catch (Exception e) {
                TestListener.getTest().fail("Test failed due to exception: " + e.getMessage());
                Assert.fail("Exception during login test", e);
            }
        }
    }


    @Test( priority=4)
    public void verifyLogin() throws Exception {
        JsonNode testData = JsonDataReader.readJson("Data.json");
        JsonNode logins = testData.get("login");

        for (JsonNode login : logins) {
            String username = login.get("username").asText();
            String password = login.get("password").asText();
            LoginPage loginPage = new LoginPage(driver);

            loginPage.enterUsername(username);

            PasswordPage passwordPage = loginPage.clickOnProceedButton();
            loginPage.handlePopup(
                    By.id("com.android.packageinstaller:id/desc_container"),
                    By.id("com.android.packageinstaller:id/permission_allow_button")
            );

            passwordPage.enterPassword(password);

            HomePage homePage = passwordPage.clickLogin();
            homePage.handlePopup(By.id("com.android.packageinstaller:id/desc_container"), By.id("com.etisalat:id/dailyGiftsClose"));
            Assert.assertTrue(homePage.isHomePageDisplayed(), "HomePage was not displayed!");
        }
    }


    @Test(priority = 5)
    public void verifyPlanName() {
        HomePage homePage = new HomePage(driver);

        boolean result = homePage
                .clickUsageButton()
                .isPlanNameCorrect("(Demagh Tanya)");

        Assert.assertTrue(result, "Plan name is not correct!");
        driver.navigate().back();
    }

    @Test(priority=6)
    public void recharge(){
        HomePage homePage = new HomePage(driver);
        homePage.clickRechargeButton().akwakartButton().editTextNumber("123456789123456").recharge().isRequestPopupVisible();
        driver.navigate().back();
        driver.navigate().back();



    }

    @Test(priority=7)
    public void verifyWordofDeals(){
        HomePage homePage = new HomePage(driver);
        homePage.clickonbenefit()
                .isbenefitpagedisplayed()
                .clickonworldofdeals()
                .isWorldofdealspagedisplayed()
                .clickonfoodandbev()
                .isfoodpagedisplayed()
                .sortlowtohighprices();

    }


}


