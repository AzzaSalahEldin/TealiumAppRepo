package pages;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage{

//    private final By color = By.xpath("//li[contains(@class, 'swatch-current')]//span[text()='Color:']/following-sibling::span//img");
//    private final By size = By.xpath("//li[contains(@class, 'swatch-current')]//span[text()='Shoe size:']/following-sibling::span//img");
    private final By wishlistMessage = By.xpath("//span[text()='Ann Ankle Boot has been added to your wishlist. Click ']");
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }
    public void selectColor(String color) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                By colorLocator = By.xpath("//a[contains(@class,'swatch-link') and contains(@class,'has-image')]//img[@title='" + color + "']");
                WebElement colorBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(colorLocator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", colorBtn);
                wait.until(ExpectedConditions.elementToBeClickable(colorBtn));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", colorBtn);
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
                attempts++;
                System.out.println("Retrying color/size selection, attempt: " + attempts);
                if (attempts == 3) {
                    throw new RuntimeException("Failed to select color: " + color , e);
                }
            }
        }
    }
    public void selectSize(String size) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                By sizeLocator = By.xpath("//a[contains(@class,'swatch-link')]//span[normalize-space(text())='" + size + "']");
                WebElement sizeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(sizeLocator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", sizeBtn);
                wait.until(ExpectedConditions.elementToBeClickable(sizeBtn));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sizeBtn);

                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
                attempts++;
                System.out.println("Retrying color/size selection, attempt: " + attempts);
                if (attempts == 3) {
                    throw new RuntimeException("Failed to select size: " + size, e);
                }

            }
        }
    }

    public void addToCart(String product) {
        By addToCart = By.xpath("//a[text()='"+product+"']//ancestor::td/following-sibling::td[contains(@class,'item-cart')]//button");
        click(addToCart);
    }

    public void addToWishList(String product)
    {
      click(By.xpath("//a[text()='"+product+"']/../..//a[@class='link-wishlist']"));
    }

    public boolean assertOnSuccessMessage(String Product){
        WebElement message = driver.findElement(wishlistMessage);
        return message.isDisplayed() && message.getText().contains(Product) ;
    }
    public String isColorSelected() {
        String selectedColor = driver.findElement(By.xpath("//span[@class='label' and text()='Color:']/following-sibling::span//img")).getAttribute("alt");
        return selectedColor;
    }

    public String isSizeSelected() {
        try {
            WebElement selectedSize = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='label' and text()='Shoe size:']/following-sibling::span//img")
            ));
            return selectedSize.getAttribute("alt");
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Selected size not found.");
            return "Not selected";
        }
    }


}
