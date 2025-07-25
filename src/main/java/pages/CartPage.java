package pages;

import org.openqa.selenium.*;

public class CartPage extends BasePage {

    private By confirmationMsg = By.xpath("//span[text()='Ann Ankle Boot was added to your shopping cart.']");
    private By color = By.xpath("//dd[@class='clearfix swatch-attr']//a//span[@class='swatch-label']");
    private By addToCart = By.xpath("//button[@onclick='productAddToCartForm.submit(this)']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(String size) {
        click(color);
        click(By.xpath("//dd[@class='clearfix swatch-attr last']//a[@name='"+size+"']"));
        click(addToCart);
    }

    public boolean isConfirmationDisplayed() {
        return driver.findElement(confirmationMsg).isDisplayed();
    }
}