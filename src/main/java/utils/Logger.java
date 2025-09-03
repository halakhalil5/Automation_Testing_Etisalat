package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Logger {

    private static AndroidDriver driver;

    public static void setDriver(AndroidDriver drv) {
        driver = drv;
    }

    public static void log(String message) {
        if (TestListener.getTest() != null) {
            TestListener.getTest().log(Status.INFO, message);
        }
        else{
            System.out.println("null");
        }
        System.out.println("[STEP] " + message);
    }

    public static void pass(String message) {
        if (TestListener.getTest() != null) {
            TestListener.getTest().log(Status.PASS, message);
        }
        else{
            System.out.println("null");
        }
        System.out.println("[PASS] " + message);
    }

    public static void fail(String message) {
        if (TestListener.getTest() != null) {
            String screenshotPath = captureScreenshot();
            try {
                TestListener.getTest().log(Status.FAIL, message,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                TestListener.getTest().log(Status.FAIL, message + " (screenshot failed)");
            }
        }
        System.out.println("[FAIL] " + message);
    }

    private static String captureScreenshot() {
        try {
            File srcFile = driver.getScreenshotAs(OutputType.FILE);
            String path = "target/screenshots/" + System.currentTimeMillis() + ".png";
            Files.copy(srcFile.toPath(), Paths.get(path));
            return path;
        } catch (Exception e) {
            System.out.println("Could not capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
