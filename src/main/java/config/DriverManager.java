package config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            boolean headless = ConfigManager.getBoolean("headless");
            String browser = ConfigManager.get("browser");
            String env = ConfigManager.get("env");

            if (env.equalsIgnoreCase("remote")) {
                driver.set(initRemoteDriver(browser, headless));
            } else {
                driver.set(initLocalDriver(browser, headless));
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    private static WebDriver initLocalDriver(String browser, boolean headless) {
        Map<String, Object> prefs = new HashMap<>();
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.addArguments("–-incognito");
                chromeOptions.addArguments("–-no-first-run");
                chromeOptions.addArguments("–-disable-popup-blocking");
                chromeOptions.addArguments("–-disable-save-password-bubble"); // May help in older versions
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);
                if (headless) chromeOptions.addArguments("--headless=new");
                WebDriver chromeDriver = new ChromeDriver(chromeOptions);
                ((JavascriptExecutor) chromeDriver).executeScript(
                        "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
                );
                return chromeDriver;

            case "firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("dom.webdriver.enabled", false);
                profile.setPreference("useAutomationExtension", false);
                profile.setPreference("general.useragent.override", "Mozilla/5.0");
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(profile);
                firefoxOptions.setProfile((FirefoxProfile) prefs);
                if (headless) firefoxOptions.addArguments("-headless");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless=new");
                return new EdgeDriver(edgeOptions);

            case "safari":
                if (headless) {
                    throw new UnsupportedOperationException("Safari does not support headless mode.");
                }
                return new SafariDriver();

            default:
                throw new RuntimeException("Unsupported local browser: " + browser);
        }
    }


    private static WebDriver initRemoteDriver(String browser, boolean headless) {
        try {
            String hubUrl = ConfigManager.get("hubUrl");
            ChromeOptions options = new ChromeOptions();
            if (headless) options.addArguments("--headless=new");
            return new RemoteWebDriver(new URL(hubUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid hub URL in config", e);
        }
    }
}
