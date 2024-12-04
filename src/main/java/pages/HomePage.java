package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import base.BasePage;
import helper.PageHelpers;

public class HomePage extends BasePage {

    private final PageHelpers pageHelper;

    @FindBy(xpath = "//button[@aria-label='Close Welcome Banner']")
    private WebElement dismissButton;

    @FindBy(xpath = "//a[@aria-label='dismiss cookie message']")
    private WebElement meWantItLink;

    @FindBy(xpath = "//mat-select[@aria-label='Items per page:']")
    private WebElement itemsPerPageDropdown;

    @FindBy(xpath = "//div[@class='mat-paginator-range-label']")
    private WebElement paginatorRangeLabel;

    @FindBy(css = "mat-grid-tile")
    private List<WebElement> productItems;

    @FindBy(id = "navbarAccount")
    private WebElement accountButton;

    @FindBy(id = "navbarLoginButton")
    private WebElement loginButton;

    @FindBy(xpath = "//button[@aria-label='Show the shopping cart']")
    private WebElement shoppingCartButton;

    public HomePage(WebDriver driver) {
        super(driver);
        this.pageHelper = new PageHelpers(driver); // Initialize helper
        PageFactory.initElements(driver, this);
    }

    /**
     * Dismisses the welcome banner and cookie message.
     */
    public void dismissWelcomeAndCookieMessages() {
    	wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@aria-label='Close Welcome Banner']")));
        pageHelper.dismissWelcomeAndCookieMessages(dismissButton, meWantItLink);
        wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//span[@class='mat-simple-snack-bar-content']")));
    }

    /**
     * Scrolls to the bottom of the page.
     */
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Selects the maximum number of items per page.
     */
    public void selectMaxItemsPerPage() {
        scrollToElement(itemsPerPageDropdown);
        click(itemsPerPageDropdown);

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//mat-option//span[contains(@class, 'mat-option-text')]")));
        if (options.isEmpty()) {
            throw new RuntimeException("No options available for items per page.");
        }

        // Click on the last (maximum) option
        WebElement maxOption = options.get(options.size() - 1);
        click(maxOption);
    }

    /**
     * Gets the total number of items displayed according to the paginator.
     *
     * @return The total number of items displayed.
     */
    public int getDisplayedProductCount() {
        waitForVisibility(paginatorRangeLabel);
        String rangeText = paginatorRangeLabel.getText(); // Example: "1-37 of 37"
        return Integer.parseInt(rangeText.split("of")[1].trim());
    }

    /**
     * Gets the count of product items displayed on the page.
     *
     * @return The number of product items displayed.
     */
    public int getProductCount() {
        return productItems.size();
    }

    /**
     * Handles clicking on the account button and then the login button.
     */
    public void clickOnAccountAndLogin() {
        click(accountButton);
        if (loginButton.isDisplayed()) {
            click(loginButton);
        }
    }


   
}
