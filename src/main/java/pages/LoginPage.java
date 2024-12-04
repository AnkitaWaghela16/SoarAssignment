package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(xpath = "//h1[text()='Login']")
    private WebElement loginTitle;

    @FindBy(xpath = "//a[text()='Not yet a customer?']")
    private WebElement registerLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters email and password into the login fields.
     *
     * @param email    The email address to enter.
     * @param password The password to enter.
     */
    public void enterCredentials(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    /**
     * Asserts if the login page is displayed.
     *
     * @return true if the login title is displayed, false otherwise.
     */
    public boolean isLoginPageDisplayed() {
        return loginTitle.isDisplayed();
    }

    /**
     * Clicks the "Not yet a customer?" link to navigate to the registration page.
     */
    public void clickOnNotYetCustomer() {
        registerLink.click();
    }

    /**
     * Clicks the login button to submit the login form.
     */
    public void clickLoginButton() {
        loginButton.click();
    }
}
