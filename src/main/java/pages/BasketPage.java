package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class BasketPage extends BasePage{
	

	@FindBy(xpath = "//button[@aria-label='Show the shopping cart']")
	    private WebElement basketButton;

	    @FindBy(xpath = "//h1[contains(text(),'Your Basket')]")
	    private WebElement basketHeader;

	    @FindBy(id = "checkoutButton")
	    private WebElement checkoutButton;

	    @FindBy(xpath = "//div[@id='price']")
	    private WebElement totalPrice;

	    public BasketPage(WebDriver driver) {
	        super(driver);
	        PageFactory.initElements(driver, this);
	    }

	    public void openBasket() {
	        click(basketButton);
	        waitForVisibility(basketHeader);
	    }

	    public double getTotalPrice() {
	        String priceText = totalPrice.getText().replaceAll("[^\\d.]", "");
	        return Double.parseDouble(priceText);
	    }

	    public void proceedToCheckout() {
	        click(checkoutButton);
	    }
	    
	    public void addProduct() throws InterruptedException {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-basket[@class='ng-star-inserted']")));
			Thread.sleep(5000);
			List<WebElement> buttons = driver.findElements(By.xpath("(//button[contains(@class, 'mat-icon-button')])"));

			for (int j = 1; j < buttons.size(); j++) {
				if ((j % 2) != 0) {
					buttons.get(j).click();
					break;
				}
			}
		}

		public void removeProduct() throws InterruptedException {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-basket[@class='ng-star-inserted']")));
			Thread.sleep(5000);
			List<WebElement> buttons = driver.findElements(By.xpath("(//button[contains(@class, 'mat-icon-button')])"));
			buttons.get(5).click();
		}
}
