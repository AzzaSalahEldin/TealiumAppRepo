package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DashboardPage extends BasePage {
    Actions actions = new Actions(driver);
    private By accountBtn = By.linkText("ACCOUNT");
    private By signOutLink = By.cssSelector("a[title='Log Out']");
    private By accessoriesMenu = By.xpath("//a[@class='level0 has-children' and text()='Accessories']");
    private By shoesSubMenu = By.xpath("//span[text()='(6)' and @class='count']//parent::a[contains(text(),'Shoes')]");
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSignOutVisible() {
        click(accountBtn);
        return isDisplayed(signOutLink);
    }

    public void clickSignOut() {
        click(accountBtn);
        click(signOutLink);
    }
    public boolean isAccessoriesMenuVisible() {
        WebElement accessories = driver.findElement(accessoriesMenu);
        actions.moveToElement(accessories).perform();
        return accessories.isDisplayed() && accessories.isEnabled();
    }
    public void goToShoesCategory() {
          click(accessoriesMenu);
          click(shoesSubMenu);
    }
}
