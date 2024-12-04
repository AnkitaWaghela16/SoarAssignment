package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class PageHelpers extends BasePage{

    public PageHelpers(WebDriver driver) {
        super(driver);
    }

   
    public void dismissWelcomeAndCookieMessages(WebElement dismissButton, WebElement meWantItLink) {
    	
        if (dismissButton != null && dismissButton.isDisplayed()) {
            click(dismissButton);
        }
        if (meWantItLink != null && meWantItLink.isDisplayed()) {
            click(meWantItLink);
        }
    }

}
