package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AccessoriesPage extends BasePage{

    public AccessoriesPage(WebDriver driver) {
        super(driver);
    }
    public void sortByPriceLowToHigh() {
        By sortLink = By.xpath("//a[contains(@href,'300-400')]");
        click(sortLink);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[title='Sort By']")));
        WebElement sortDropdown = driver.findElement(By.cssSelector("select[title='Sort By']"));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Price");
    }
    public void openProductDetails(String name) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                By locator = By.xpath("//a[contains(@title,'" + name + "')]");
                WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
                Thread.sleep(500); // or use ExpectedConditions.invisibilityOfElementLocated(...) for loaders
                wait.until(ExpectedConditions.elementToBeClickable(product));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+name+"']")));
                return;
            } catch (Exception e) {
                attempts++;
                if (attempts == 3) {
                    throw new RuntimeException("Failed to click product: " + name, e);
                }
            }
        }
    }

    public List<Double> getAllDisplayedPrices() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".price"));
        List<Double> prices = new ArrayList<>();
        for (WebElement priceEl : priceElements) {
            String text = priceEl.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(text));
        }
        return prices;
    }
    public String isProductDetailsDisplayed(String name) {
        WebElement el = driver.findElement(By.xpath("//span[text()='"+name+"']"));
        return el.getText();
    }
}
