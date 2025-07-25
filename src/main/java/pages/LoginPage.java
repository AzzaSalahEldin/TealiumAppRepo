package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPage extends BasePage {
    By signInBtn = By.xpath ( "//button[@aria-label='sign in']");
    By emailField = By.id("signInFormUsername");
    By passField = By.id("signInFormPassword");
    By loginBtn = By.xpath ("//button[text()='Log in']");
    By submit = By.xpath ("//input[@name='signInSubmitButton']");
    By welcomeMsg = By.xpath("//button[contains(@aria-label,'My Account')]");
    By loginError = By.xpath("//p[@id='loginErrorMessage']");
    By model = By.xpath ("//button[contains(@id,'optlyCloseModal')]");

    public LoginPage(WebDriver driver) { super(driver); }

    public void login(String email, String pwd) {
        click(signInBtn);
        click(loginBtn);
        type(emailField,email);
        type(passField,pwd);
        click(submit);
    }

    public void assertLoginSuccess()
    {
        assert isDisplayed(welcomeMsg);
    }
    public void assertLoginFailed() { assert isDisplayed(loginError); }
}
