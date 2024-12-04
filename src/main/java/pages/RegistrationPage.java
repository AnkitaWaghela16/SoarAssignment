package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.github.javafaker.Faker;

import base.BasePage;

public class RegistrationPage extends BasePage {

    private static final Faker faker = new Faker();

    public static final String email = faker.internet().emailAddress();
    public static final String password = faker.internet().password(8, 15, true, true, true);
    private final String securityAnswer = faker.internet().domainWord();

    @FindBy(id = "emailControl")
    private WebElement emailField;

    @FindBy(id = "passwordControl")
    private WebElement passwordField;

    @FindBy(id = "repeatPasswordControl")
    private WebElement repeatPasswordField;

    @FindBy(id = "mat-select-value-3")
    private WebElement securityQuestionDropdown;

    @FindBy(id = "securityAnswerControl")
    private WebElement securityAnswerField;

    @FindBy(xpath = "//mat-error[contains(@class, 'mat-error') and contains(text(), 'Please provide an email address')]")
    private WebElement errorEmail;

    @FindBy(xpath = "//mat-error[contains(@class, 'mat-error') and contains(text(), 'Please provide a password')]")
    private WebElement errorPassword;

    @FindBy(xpath = "//mat-error[contains(@class, 'mat-error') and contains(text(), 'Please repeat your password')]")
    private WebElement errorRepeatPassword;

    @FindBy(xpath = "//mat-error[contains(@class, 'mat-error') and contains(text(), ' Please select a security question')]")
    private WebElement errorSecurityQuestion;

    @FindBy(xpath = "//mat-error[contains(@class, 'mat-error') and contains(text(), 'Please provide an answer to your security question')]")
    private WebElement errorSecurityAnswer;

    @FindBy(xpath = "//h1[text()='User Registration']")
    private WebElement registrationTitle;

    @FindBy(xpath = "//*[@id=\"mat-select-2-panel\"]/*[1]")
    private WebElement firstSecurityQuestionOption;

    @FindBy(id = "mat-slide-toggle-1")
    private WebElement showPasswordToggle;

    @FindBy(id = "registerButton")
    private WebElement registerButton;

    @FindBy(xpath = "//span[text()='Registration completed successfully. You can now log in.']")
    private WebElement registrationSuccessSnackbar;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Validates the visibility of all input fields.
    public void validateInputFields() {
        clickElement(emailField);
        clickElement(passwordField);
        clickElement(repeatPasswordField);
        clickElement(securityQuestionDropdown);

        Actions actions = new Actions(driver);
		actions.moveByOffset(100, 100).click().perform(); // Clicks 10px from the top-left corner
		System.out.println("Clicked outside using absolute positioning.");
		wait.until(ExpectedConditions.elementToBeClickable(securityAnswerField)).click();
		


        clickElement(registrationTitle);
        System.out.println("Validation of input fields completed successfully.");
    }

    //Asserts that validation error messages are displayed for all fields.
    public void assertFieldErrors() {
        assertTrue(errorEmail.isDisplayed(), "Email error message is not displayed.");
        assertTrue(errorPassword.isDisplayed(), "Password error message is not displayed.");
        assertTrue(errorRepeatPassword.isDisplayed(), "Repeat password error message is not displayed.");
        assertTrue(errorSecurityQuestion.isDisplayed(), "Security question error message is not displayed.");
        assertTrue(errorSecurityAnswer.isDisplayed(), "Security answer error message is not displayed.");
    }

    //Fills out the registration form with valid data.
    public void fillRegistrationForm() throws InterruptedException {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        repeatPasswordField.sendKeys(password);
        securityQuestionDropdown.click();
        Thread.sleep(2000);
        firstSecurityQuestionOption.click();
        securityAnswerField.sendKeys(securityAnswer);
    }

   //Toggles the "Show Password" option.
    public void toggleShowPassword() {
        clickElement(showPasswordToggle);
    }

    //Submits the registration form by clicking the register button.
    public void submitRegistrationForm() {
        if (registerButton.isEnabled()) {
            clickElement(registerButton);
        } else {
            throw new RuntimeException("Register button is not enabled.");
        }
    }

    //Asserts that the registration success message is displayed.
    public void assertRegistrationSuccessMessage() {
        waitForVisibility(registrationSuccessSnackbar);
        assertTrue(registrationSuccessSnackbar.isDisplayed(), "Registration success message is not displayed.");
    }

    
    private void clickElement(WebElement element) {
        waitForVisibility(element);
        element.click();
    }
}
