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




//    @BeforeClass
//    public void startReport() {
//        extent = ExtentManager.getInstance();
//        test = extent.createTest("Login Test");
//    }

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


//    @Test(dataProvider = "loginData")
//    public void verifyLogin(String username, String password) {
//        LoginPage loginPage = new LoginPage(driver);
//
//        loginPage.enterUsername(username);
//
//        PasswordPage passwordPage = loginPage.clickOnProceedButton();
//        loginPage.handlePopup(
//                By.id("com.android.packageinstaller:id/desc_container"),
//                By.id("com.android.packageinstaller:id/permission_allow_button")
//        );
//
//        passwordPage.enterPassword(password);
//
//        HomePage homePage = passwordPage.clickLogin();
//        homePage.handlePopup(By.id("com.android.packageinstaller:id/desc_container"), By.id("com.etisalat:id/dailyGiftsClose"));
//        Assert.assertTrue(homePage.isHomePageDisplayed(), "HomePage was not displayed!");
//    }

//    @Test(dataProvider = "loginData")
//    public void verifyLoginem(String username, String password) {
//
//        try {
//            LoginPage loginPage = new LoginPage(driver);
//
//            // Step 1: Enter username
//            loginPage.enterUsername(username);
//            ExtentTestManager.getTest().pass("Entered username: " + username);
//
//            // Step 2: Click on Proceed button
//            PasswordPage passwordPage = loginPage.clickOnProceedButton();
//            ExtentTestManager.getTest().pass("Clicked Proceed button");
//
//            // Step 3: Handle permission popup if it appears
//            loginPage.handlePopup(
//                    By.id("com.android.permissioncontroller:id/grant_dialog"),
//                    By.id("com.android.permissioncontroller:id/permission_allow_button"));
//            ExtentTestManager.getTest().info("Handled permissions popup");
//
//            // Step 4: Enter password
//            passwordPage.enterPassword(password);
//            ExtentTestManager.getTest().info("Entered password");
//
//            // Step 5: Click login
//            HomePage homePage = passwordPage.clickLogin();
//            ExtentTestManager.getTest().info("Clicked Login button");
//
//            // Step 6: Handle daily gifts popup if it appears
//            homePage.handlePopup(
//                    By.id("com.etisalat:id/dailyGiftsClose"),
//                    By.id("com.etisalat:id/dailyGiftsClose"));
//            ExtentTestManager.getTest().info("Handled daily gifts popup");
//
//            // Step 7: Verify homepage
//            if (homePage.isHomePageDisplayed()) {
//                ExtentTestManager.getTest().pass("HomePage displayed successfully");
//            } else {
//                ExtentTestManager.getTest().fail("HomePage NOT displayed");
//                Assert.fail("HomePage not displayed"); // stops execution
//            }
//
//        } catch (Exception e) {
//            ExtentTestManager.getTest().fail("Test failed due to exception: " + e.getMessage());
//            Assert.fail("Exception during login test", e);
//        } finally {
//            ExtentTestManager.endTest(); // flush the report
//        }
//    }





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

    @Test(dataProvider = "loginData", priority=3)
    public void LoginSignout(String username, String password) {
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

            // if you want custom logs:
            TestListener.getTest().info("Login completed for user: " + username);

        } catch (Exception e) {
            TestListener.getTest().fail("Test failed due to exception: " + e.getMessage());
            Assert.fail("Exception during login test", e);
        }
    }


    @Test(dataProvider = "loginData", priority=4)
    public void verifyLogin(String username, String password) {
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




//    @AfterClass
//    public void endReport() {
//        if (extent != null) {
//            extent.flush();
//        }
//    }
}


