package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static final Map<Long, ExtentTest> extentTestMap = new HashMap<>();
    private static final ExtentReports extent = ExtentManager.getInstance();

    public static synchronized void startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        extentTestMap.put(Thread.currentThread().threadId(), test);
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().threadId());
    }

    public static synchronized void endTest() {
        extent.flush();
        extentTestMap.remove(Thread.currentThread().threadId());
    }
}
