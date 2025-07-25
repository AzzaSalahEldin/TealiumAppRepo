package listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;
import java.time.Duration;

public class PopupCloserListener implements WebDriverListener {

    private void closeConsentPopupIfPresent(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement closeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='close_btn_thick']")));

            if (closeBtn.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeBtn);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
                System.out.println("Consent popup closed.");
            }
        } catch (Exception e) {
            System.out.println("Error closing popup: " + e.getMessage());
        }
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println("[Listener] afterClick triggered.");
        WebDriver driver = ((WrapsDriver) element).getWrappedDriver();
        closeConsentPopupIfPresent(driver);
    }

    @Override
    public void afterAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args, Object result) {
        System.out.println("Navigation occurred: " + method.getName());
        closeConsentPopupIfPresent(getDriverFromNavigation(navigation));
    }

    private WebDriver getDriverFromNavigation(WebDriver.Navigation navigation) {
        return ((WebDriver) navigation);
    }
}
