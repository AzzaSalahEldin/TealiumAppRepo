package tests;
import config.ConfigManager;
import config.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.ScreenshotUtil;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected RegistrationPage regPage;
    @BeforeSuite
    public void clearAllureResults() {
        File allureResults = new File("allure-results");
        try {
            if (allureResults.exists()) {
                FileUtils.cleanDirectory(allureResults);
                System.out.println("Cleared allure-results folder.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigManager.get("url"));
        loginPage = new LoginPage(driver);
        regPage = new RegistrationPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
    @BeforeSuite
    public void beforeSuite() {
        ScreenshotUtil.clearOldScreenshots();
    }
}
