package tests;

import org.testng.annotations.Listeners;
import utils.CSVDataProviders;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("PetFinder App")
@Feature("Login Feature")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = CSVDataProviders.class)
    @Story("Valid and Invalid Login Scenarios")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login functionality with valid and invalid credentials from data provider")
    public void testLogin(String email, String password, String expectedResult) {
        loginPage.login(email, password);

        if (expectedResult.equalsIgnoreCase("success")) {
            loginPage.assertLoginSuccess();
        } else {
            loginPage.assertLoginFailed();
        }
    }
}
