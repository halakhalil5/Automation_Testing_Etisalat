package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔥 onTestStart triggered for " + result.getMethod().getMethodName());
        String testName = result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test.get() != null) {
            test.get().fail(result.getThrowable());
        }

        Object driverObj = result.getTestContext().getAttribute("driver");
        if (driverObj instanceof AndroidDriver) {
            AndroidDriver driver = (AndroidDriver) driverObj;
            try {
                Files.createDirectories(Paths.get("target/screenshots"));
                File srcFile = driver.getScreenshotAs(OutputType.FILE);
                String screenshotPath = "target/screenshots/" + System.currentTimeMillis() + ".png";
                Files.copy(srcFile.toPath(), Paths.get(screenshotPath));

                String absolutePath = new File(screenshotPath).getAbsolutePath();
                test.get().fail("Screenshot of failure",
                        MediaEntityBuilder.createScreenCaptureFromPath(absolutePath).build());

                System.out.println("[FAIL] Screenshot attached: " + absolutePath);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("[FAIL] Could not capture screenshot: " + e.getMessage());
            }
        }
    }







    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

        // Location of your generated Extent report
        File reportFile = new File("target/ExtentReport.html");

        if (reportFile.exists()) {
            try {
                // Launch Chrome with the report file
                String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
                Runtime.getRuntime().exec(new String[] {
                        chromePath,
                        reportFile.getAbsolutePath()
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static ExtentTest getTest() {
        return test.get();
    }








}
