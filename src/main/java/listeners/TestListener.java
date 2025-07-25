package listeners;

import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ScreenshotUtil.takeScreenshot(testName);
    }

    @Override
    public void onStart(ITestContext context) {
        ScreenshotUtil.clearOldScreenshots();
    }
//    @Override
//    public void onTestFailure(ITestResult result) {
//        String methodName = result.getMethod().getMethodName();
//        File screenshot = ScreenshotUtil.takeScreenshot(methodName);
//        attachScreenshotToAllure(screenshot);
//    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    private byte[] attachScreenshotToAllure(File screenshotFile) {
        try {
            return Files.readAllBytes(screenshotFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("[PASS] Test passed: " + testName);
        attachTextLog("Test passed successfully: " + testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("[SKIP] Test skipped: " + testName);
        attachTextLog("Test skipped: " + testName + " | Reason: " + result.getThrowable());
    }

//    @Override
//    public void onStart(ITestContext context) {
//        System.out.println("[START] Test Suite: " + context.getName());
//        attachTextLog("Test Suite Started: " + context.getName());
//    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[FINISH] Test Suite: " + context.getName());
        attachTextLog("Test Suite Finished: " + context.getName());
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String attachTextLog(String message) {
        return message;
    }
}
