package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    protected void clear(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement el : elements) {
                if (el.isDisplayed()) {
                    wait.until(ExpectedConditions.visibilityOf(el));
                    el.clear();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Clear failed on: " + locator + " - " + e.getMessage());
        }
    }
    protected void click(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement sb : elements) {
                if(sb.isDisplayed()== true){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sb);
//                    wait.until(ExpectedConditions.elementToBeClickable(sb)).click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Click failed on: " + locator + " - " + e.getMessage());
        }

    }
    protected void clearWithBackspace(By element) {
        String existingText = driver.findElement(element).getAttribute("value");
        if (existingText != null && !existingText.isEmpty()) {
            int length = existingText.length();
            driver.findElement(element).sendKeys(Keys.END);
            for (int i = 0; i < length; i++) {
                driver.findElement(element).sendKeys(Keys.BACK_SPACE);
            }
        }
    }
    protected  void type(By locator, String text){
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement el : elements) {
                if (el.isDisplayed()== true){
                    wait.until(ExpectedConditions.elementToBeClickable(el)).sendKeys(text);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Type failed on: " + locator + " - " + e.getMessage());
        }

    }
    public void selectByText(String text,By optionsLocator) {
        WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(optionsLocator));
        Select dropdown = new Select(selectElement);
        dropdown.selectByValue(text);
    }

    public void checkCustomCheckbox(By locator) {
        WebElement checkbox = driver.findElement(locator);
        String isChecked = checkbox.getAttribute("aria-checked");

        if (isChecked != null && isChecked.equals("false")) {
            checkbox.click();
        }
    }

    protected String getText(WebElement e) { return wait.until(ExpectedConditions.visibilityOf(e)).getText(); }
    protected boolean isDisplayed(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement el : elements) {
                if (el.isDisplayed()) {
                    return wait.until(ExpectedConditions.visibilityOf(el)).isDisplayed();
                }
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }


}
