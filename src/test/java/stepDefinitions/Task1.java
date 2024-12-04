package stepDefinitions;

import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.DriverManager;

public class Task1 {

    private final HomePage homePage;
   

    public Task1() {
        this.homePage = new HomePage(DriverManager.getDriver());
    }

    @Given("I navigate to the web application")
    public void navigateToJuiceShopHomePage() {
        DriverManager.getDriver().get("https://juice-shop.herokuapp.com/#/");
    }

    @When("I scroll to the end of the page")
    public void scrollToBottom() {
        homePage.dismissWelcomeAndCookieMessages();
        homePage.scrollToBottom();
    }

    @And("I change items per page to the maximum number")
    public void changeItemsPerPageToMaximum() {
        homePage.selectMaxItemsPerPage();
    }

    @Then("I assert that the homepage displays all items")
    public void verifyAllProductsDisplayed() {
        int displayedCount = homePage.getDisplayedProductCount();
        int productCount = homePage.getProductCount();
        assertEquals(displayedCount, productCount, "Mismatch between displayed and actual product count.");
        DriverManager.quitDriver();
    }
}
