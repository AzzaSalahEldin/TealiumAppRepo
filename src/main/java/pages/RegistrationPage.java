package pages;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {
    By email = By.id("emailAddress");
    By pwd = By.id("password");
    By confirmPwd = By.id("confirmPassword");
    By closeBtn = By.xpath("//button[@aria-label='Close']");
    By continueBtn = By.xpath("//button[text()='Continue']");
    By firstName = By.xpath("//input[@name='firstName']");
    By lastName = By.xpath("//input[@name='lastName']");
    By postalCode = By.xpath("//input[@name='postalCode']");
    By dogsCount = By.xpath("//select[@name='dogCount']");
    By catsCount = By.xpath("//select[@name='catCount']");
    By dismissBtn = By.xpath("//button[text()='Dismiss']");
    By termsCheckBox = By.xpath("//button[@role='checkbox']");
    By successMsg = By.xpath("//h1[text()='Verify your email']");
    By existEmail = By.xpath("//h2[text()='We recognize you!']");
    By inValidEmail = By.xpath("//span[text()='Please provide an email in this format: name@example.com']");

    By pwdRequired = By.xpath("//div[text()='Password is required']");
    By confirmPasswordRequired = By.xpath("Confirm Password is required");
    By pwdValidation = By.xpath("//div[text()='Your password needs to be at least 6 characters in length']");
    By confirmPasswordValidation = By.xpath("//div[text()='Passwords do not match']");
    By zipCodeMessge = By.xpath("//span[text()='Must enter valid US ZIP or CA Postal Code.']");
    By firstNameMessge = By.xpath("//span[text()='First Name is required']");
    By lastNameMessge = By.xpath("//span[text()='Last Name is required']");

    By signInBtn = By.xpath ( "//button[@aria-label='sign in']");
    By createBtn = By.xpath ("//button[text()='Create Account']");
    public RegistrationPage(WebDriver driver) { super(driver); }

    public void openRegistrationForm() {
        click(signInBtn);
        click(createBtn);
    }
    public void registerPersonalData(String emailAddr,String first,String last,String zip,String dogsNo,String catsNo) {
        openRegistrationForm();
        type(email, emailAddr);
        click(continueBtn);
        type(firstName, first);
        type(lastName, last);
        type(postalCode, zip);
        selectByText(dogsNo,dogsCount);
        selectByText(catsNo,catsCount);
        click(closeBtn);
        click(continueBtn);
    }

    public void enterEmailAddress(String emailAddr) {
        clear(email);
        type(email, emailAddr);
    }

    public void proceedFromEmail() {
        click(continueBtn);
    }

    public void enterFirstName(String first) {
        type(firstName, first);
    }
    public void clearFirstName() {
        clearWithBackspace(firstName);
    }

    public void enterLastName(String last) {
        type(lastName, last);
    }
    public void clearLastName() {
        clearWithBackspace(lastName);
    }
    public  void clickDismiss(){
        click(dismissBtn);
    }

    public void enterZipCode(String zip) {
        clear(postalCode);
        type(postalCode, zip);
    }

    public void selectNumberOfDogs(String dogsNo) {
        selectByText(dogsNo,dogsCount);
    }

    public void selectNumberOfCats(String catsNo) {
        selectByText(catsNo,catsCount);
    }

    public void proceedFromPersonalInfo() {
        click(continueBtn);
    }

    // Password Step
    public void enterPassword(String password) {
        clearConfirmPassword();
        type(pwd, password);
    }
    public void clearPassword() {
        clearWithBackspace(pwd);
    }
    public void clearConfirmPassword() {
        clearWithBackspace(confirmPwd);
    }

    public void enterConfirmPassword(String confirm) {
        clearConfirmPassword();
        type(confirmPwd, confirm);
    }

    // Terms & Submit
    public void acceptTermsAndConditions() {
        checkCustomCheckbox(termsCheckBox);
    }

    public void submitRegistration() {
        click(createBtn);
    }
    public void assertRegistrationEmailExist() { assert isDisplayed(existEmail); }
    public void assertRegistrationEmailValidation() { assert isDisplayed(inValidEmail); }
    public void assertZipCodeValidation() { assert isDisplayed(zipCodeMessge); }
    public void assertFirstNameValidation() { assert isDisplayed(firstNameMessge); }
    public void assertLastNameValidation() { assert isDisplayed(lastNameMessge); }


    public void assertRegistrationSuccess() { assert isDisplayed(successMsg); }
    public void assertRegistrationPwdRequired() { assert isDisplayed(pwdRequired); }
    public void assertRegistrationConfirmPasswordRequired() { assert isDisplayed(confirmPasswordRequired); }

    public void assertRegistrationPwdValidation() { assert isDisplayed(pwdValidation); }
    public void assertRegistrationConfirmPasswordValidation() { assert isDisplayed(confirmPasswordValidation); }
}
