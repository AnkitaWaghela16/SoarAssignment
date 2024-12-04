package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.github.javafaker.Faker;

import base.BasePage;

public class AddressPage extends BasePage {

	Faker faker = new Faker();
	String country = faker.country().name();
	String name = faker.name().name();
	String mobile = faker.number().digits(8);
	String zipcode = faker.number().digits(8);
	String creditcard = faker.number().digits(16);
	String city = faker.address().city();
	String address = faker.address().fullAddress();
	
	@FindBy(xpath = "//button[@aria-label=\"Add a new address\"]")
	private WebElement btnAddAddress;

	@FindBy(xpath = "//input[@data-placeholder='Please provide a country.']")
	private WebElement countryField;

	@FindBy(xpath = "//input[@data-placeholder='Please provide a name.']")
	private WebElement nameField;

	@FindBy(xpath = "//input[@data-placeholder='Please provide a mobile number.']")
	private WebElement mobileField;

	@FindBy(xpath = "//input[@data-placeholder='Please provide a ZIP code.']")
	private WebElement zipCodeField;

	@FindBy(id = "address")
	private WebElement addressField;

	@FindBy(xpath = "//input[@data-placeholder='Please provide a city.']")
	private WebElement cityField;

	@FindBy(id = "submitButton")
	private WebElement submitAddressButton;

	public AddressPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void fillAddressDetails() {
		
		btnAddAddress.click();
		countryField.sendKeys(country);
		nameField.sendKeys(name);
		mobileField.sendKeys(mobile);
		zipCodeField.sendKeys(zipcode);
		addressField.sendKeys(address);
		cityField.sendKeys(city);
		click(submitAddressButton);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//mat-radio-button[contains(@Class,'mat-radio-button')]")))
				.click();

		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"card\"]/app-address/mat-card/button")))
				.click();

	}
	
	public void selectDeliverySpeed() {

		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//span[@class='mat-simple-snack-bar-content']")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//mat-radio-button[contains(@class,'mat-radio-button')]")));
		List<WebElement> radiobutton = driver
				.findElements(By.xpath("//mat-radio-button[contains(@class,'mat-radio-button')]"));

		Random random = new Random();

		radiobutton.get(random.nextInt(radiobutton.size())).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@aria-label=\"Proceed to delivery method selection\"]")))
				.click();

	}

}
