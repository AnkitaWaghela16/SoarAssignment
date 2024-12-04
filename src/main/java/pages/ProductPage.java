package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class ProductPage extends BasePage {

   

    @FindBy(xpath = "(//mat-card[contains(@class, 'mat-card')])[1]")
    private WebElement firstProduct;

    @FindBy(id = "mat-dialog-1")
    private WebElement productPopup;

    @FindBy(xpath = "//*[@id='mat-dialog-1']//img[@class='img-thumbnail']")
    private WebElement productImage;

    @FindBy(xpath = "//*[@id='mat-dialog-1']//mat-expansion-panel[@aria-label='Expand for Reviews']")
    private WebElement productReviewSection;

    @FindBy(xpath = "//*[@id='mat-dialog-1']//button[contains(@class,'close-dialog')]")
    private WebElement closePopupButton;

    @FindBy(xpath = "//button[@aria-label='Add to Basket']")
    private List<WebElement> addToBasketButtons;

    @FindBy(xpath = "//span[@class='mat-simple-snack-bar-content']")
    private WebElement snackBar;

    @FindBy(xpath = "//button[@aria-label='Show the shopping cart']")
    private WebElement shoppingCartButton;

    @FindBy(xpath = "//span[contains(@class,'warn-notification')]")
    private WebElement basketCount;
 

    
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks on the first product in the product list if it is visible.
     */
    public void clickOnFirstProduct() {
        if (isElementVisible(firstProduct)) {
            click(firstProduct);
        } else {
            System.out.println("No products available to click.");
        }
    }

    /**
     * Checks if the product popup is displayed.
     *
     * @return true if the popup is displayed, false otherwise.
     */
    public boolean isPopupDisplayed() {
        return isElementVisible(productPopup);
    }

    /**
     * Checks if the product image in the popup is displayed.
     *
     * @return true if the product image is displayed, false otherwise.
     */
    public boolean isPopupImageDisplayed() {
        return isElementVisible(productImage);
    }

    /**
     * Checks if the product review section in the popup is displayed.
     *
     * @return true if the product review section is displayed, false otherwise.
     */
    public boolean isProductReviewSectionDisplayed() {
        return isElementVisible(productReviewSection);
    }

    /**
     * Expands the product review section if it is visible.
     */
    public void expandProductReviewSection() {
        if (isProductReviewSectionDisplayed()) {
            click(productReviewSection);
        }
    }

    /**
     * Closes the product popup.
     */
    public void closePopup() {
        if (isElementVisible(closePopupButton)) {
            click(closePopupButton);
        }
    }

    /**
     * Utility method to check if an element is visible on the page.
     *
     * @param element The WebElement to check.
     * @return true if the element is visible, false otherwise.
     */
    private boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
        
        
    }
    
    public void addProductsToBasket(int count) throws InterruptedException {
    	Thread.sleep(2000);
    	List<WebElement> items = driver.findElements(By.xpath("//button[@aria-label=\"Add to Basket\"]"));
        int addedCount = 0;
        for (WebElement item : items) {
			item.click();
			WebElement snackbar = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//span[@class='mat-simple-snack-bar-content']")));
			assertTrue(snackbar.isDisplayed());
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//span[@class='mat-simple-snack-bar-content']")));
			addedCount = Integer.parseInt(basketCount.getText());
			if (addedCount == 5) {
				break;
			}
		}
    }

    public void assertBasketItemCount(int expectedCount) {
        int actualCount = Integer.parseInt(basketCount.getText());
        assertTrue(actualCount == expectedCount, "Basket count does not match expected value.");
    }
   
}
