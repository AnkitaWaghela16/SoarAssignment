package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class PageHelpers extends BasePage{

    public PageHelpers(WebDriver driver) {
        super(driver);
    }

    /**
     * Dismiss the welcome banner and cookie message if they are visible.
     *
     * @param dismissButton WebElement for the welcome banner dismiss button.
     * @param meWantItLink  WebElement for the cookie message dismiss link.
     */
    public void dismissWelcomeAndCookieMessages(WebElement dismissButton, WebElement meWantItLink) {
    	
        if (dismissButton != null && dismissButton.isDisplayed()) {
            click(dismissButton);
        }
        if (meWantItLink != null && meWantItLink.isDisplayed()) {
            click(meWantItLink);
        }
    }

}
