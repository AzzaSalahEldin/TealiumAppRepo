package tests;

import org.testng.annotations.Listeners;
import utils.CSVDataProviders;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

@Epic("PetFinder App")
@Feature("Registration Feature")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "registrationData", dataProviderClass = CSVDataProviders.class)
    @Story("Valid and Invalid Registration Scenarios")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test successful and negative registration cases based on expected results from CSV")
    public void testSuccessfulRegistration(String email, String first, String last, String zip, String dogsNo, String catsNo, String password, String confirm, String expectedResult) {
        regPage.registerPersonalData(ThreadLocalRandom.current().nextInt(10000, 100000)+email,first,last,zip,dogsNo,catsNo);
        regPage.enterPassword(password);
        regPage.enterConfirmPassword(confirm);
        regPage.acceptTermsAndConditions();
        regPage.submitRegistration();
        switch (expectedResult.toLowerCase()) {
            case "success":
                regPage.assertRegistrationSuccess();
                break;
            case "invalidpassword":
                regPage.assertRegistrationPwdValidation();
                break;
            case "notmatchedconfrimpassword":
                regPage.assertRegistrationConfirmPasswordValidation();
                break;
        }
    }

    @Test
    @Story("Password Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify password and confirm password field validations")
    public void testPasswordValidation(){
        regPage.registerPersonalData("test"+ ThreadLocalRandom.current().nextInt(10000, 100000)+"@gmail.com","first","last","25100","3","2");
        regPage.enterPassword("password");
        regPage.enterConfirmPassword("confirm");
        regPage.acceptTermsAndConditions();
        regPage.submitRegistration();
        regPage.clearPassword();
        regPage.assertRegistrationPwdRequired();

        // Uncomment if needed
        // regPage.clearConfirmPassword();
        // regPage.assertRegistrationConfirmPasswordRequired();
    }

    @Test
    @Story("Email Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate email uniqueness and format during registration")
    public void testEmailValidation() {
        regPage.openRegistrationForm();
        regPage.enterEmailAddress("azzasalaheldin11@gmail.com");
        regPage.proceedFromEmail();
        regPage.assertRegistrationEmailExist();
        regPage.clickDismiss();
        regPage.enterEmailAddress("dsfvgdf@fdf.");
        regPage.proceedFromEmail();
        regPage.assertRegistrationEmailValidation();
    }

    @Test
    @Story("Personal Information Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify validations on personal info fields like ZIP, first name, and last name")
    public void testPersonalValidation() {
        regPage.openRegistrationForm();
        regPage.enterEmailAddress("test"+ ThreadLocalRandom.current().nextInt(10000, 100000)+"@gmail.com");
        regPage.proceedFromEmail();
        regPage.enterFirstName("test");
        regPage.enterLastName("last");
        regPage.enterZipCode("2");
        regPage.assertZipCodeValidation();
        regPage.enterZipCode("25100");
        regPage.clearFirstName();
        regPage.assertFirstNameValidation();
        regPage.clearLastName();
        regPage.assertLastNameValidation();
    }
}
