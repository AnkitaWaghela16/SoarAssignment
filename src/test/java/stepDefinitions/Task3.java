package stepDefinitions;

import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.DriverManager;

public class Task3 {

	private final RegistrationPage registrationPage;
	private final LoginPage loginPage;
	private final HomePage homePage;

	public Task3() {
		this.registrationPage = new RegistrationPage(DriverManager.getDriver());
		this.loginPage = new LoginPage(DriverManager.getDriver());
		this.homePage = new HomePage(DriverManager.getDriver());
	}

	@Given("I navigate to the Juice Shop registration page")
	public void navigateToJuiceShopRegistrationPage() {
		DriverManager.getDriver().get("https://juice-shop.herokuapp.com/#/");
	}

	@When("I click on all input fields without entering any data")
	public void clickOnAllInputFields() throws InterruptedException {
		homePage.dismissWelcomeAndCookieMessages();
		homePage.clickOnAccountAndLogin();
		loginPage.clickOnNotYetCustomer();
		registrationPage.validateInputFields();
	}

	@Then("I assert that validation messages are displayed for all fields")
	public void assertValidationMessages() {
		registrationPage.assertFieldErrors();
	}

	@When("I fill the registration form with valid data")
	public void fillRegistrationForm() throws InterruptedException {
		registrationPage.fillRegistrationForm();
	}

	@And("I click on Show Password Advice")
	public void clickShowPasswordAdvice() {
		registrationPage.toggleShowPassword();
	}

	@And("I submit the registration form")
	public void submitRegistrationForm() {
		registrationPage.submitRegistrationForm();
	}

	@Then("I assert that a successful registration message is displayed")
	public void assertSuccessfulRegistrationMessage() {
		registrationPage.assertRegistrationSuccessMessage();
	}

	@And("I am redirected to the login page")
	public void assertRedirectionToLoginPage() {
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "The user is not redirected to the login page.");
	}

	@When("I enter the registered email and password")
	public void enterRegisteredEmailAndPassword() {
		loginPage.enterCredentials(registrationPage.email, registrationPage.password);
	}

	@And("I click the login button")
	public void clickLoginButton() {
		loginPage.clickLoginButton();
	}

	@Then("I assert that I am successfully logged into the application")
	public void assertSuccessfulLogin() throws InterruptedException {
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login was not successful.");

		DriverManager.quitDriver();
	}
}
