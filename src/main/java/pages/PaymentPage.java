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

public class PaymentPage extends BasePage {

	Faker faker = new Faker();
	String name = faker.name().name();
	String creditcard = faker.number().digits(16);
	float walletbalance;
	public Boolean purchaseComplete;

	@FindBy(xpath = "//span[@class='confirmation card-title']")
	private WebElement walletBalance;

	@FindBy(xpath = "//*[contains(text(),'Add a credit or debit card')]")
	private WebElement addCreditCardButton;

	@FindBy(xpath = "(//mat-label[normalize-space(text())='Name'])/ancestor::div//input[@type='text' and contains(@id, 'mat-input') and @aria-required='true']")
	private WebElement cardNameField;

	@FindBy(xpath = "(//mat-label[(text())='Card Number'])/ancestor::div//input[@type=\"number\"]")
	private WebElement cardNumberField;

	@FindBy(xpath = "//*[@id=\"submitButton\"]")
	private WebElement submitCardButton;

	@FindBy(xpath = "//button[@aria-label=\"Proceed to review\"]")
	private WebElement continueButton;

	@FindBy(xpath = "//*[contains(text(),'Add a credit or debit card')]")
	private WebElement addCreditCard;

	public PaymentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void checkWalletBalance() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"confirmation card-title\"]")));
		Thread.sleep(2000);
		String balance = walletBalance.getText();
		walletbalance = Float.parseFloat(balance);
		System.out.println("print balance" + balance);
	}

	public void clickOnAddCreditCard() throws InterruptedException {
		if (walletbalance == 0.00) {
			System.out.println("click on add credit");
			addCreditCard.click();
			Thread.sleep(5000);
		}
	}

	public void selectcardAndEnterCardDetails() throws InterruptedException {
		cardNameField.click();
		cardNameField.sendKeys(name);
		cardNumberField.sendKeys(creditcard);

		WebElement expiry1;
		Random rand = new Random();
		int randomIndex;

		List<WebElement> expiry = driver
				.findElements(By.xpath("(//mat-label[normalize-space(text())='Expiry Year'])/ancestor::div//select"));
		for (int i = 0; i < expiry.size(); i++) {
			expiry1 = expiry.get(i);
			List<WebElement> optionsMonth = expiry1.findElements(By.xpath(".//option"));

			randomIndex = rand.nextInt(optionsMonth.size());
			WebElement randomOption = optionsMonth.get(randomIndex);
			randomOption.click();

		}

		if (submitCardButton.isEnabled()) {
			submitCardButton.click();
		}
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//span[@class='mat-simple-snack-bar-content']")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//mat-radio-button[contains(@class,'mat-radio-button')]")));
		driver.findElement(By.xpath("//mat-radio-button[contains(@class,'mat-radio-button')]")).click();
		if (continueButton.isEnabled()) {
			continueButton.click();
		}
	}

	public void completePurchase() {
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//span[@class='mat-simple-snack-bar-content']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='checkoutButton']"))).click();
		purchaseComplete = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[text()='Thank you for your purchase!']")))
				.isDisplayed();
	}
}
