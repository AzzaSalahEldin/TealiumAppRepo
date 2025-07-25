package utils;

import config.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    private static final String SCREENSHOTS_DIR = "screenshots";

    public static void clearOldScreenshots() {
        try {
            FileUtils.deleteDirectory(new File(SCREENSHOTS_DIR));
            new File(SCREENSHOTS_DIR).mkdirs(); // recreate folder
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File takeScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = SCREENSHOTS_DIR + "/" + testName + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(path);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
