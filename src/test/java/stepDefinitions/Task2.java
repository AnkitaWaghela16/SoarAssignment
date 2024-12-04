package stepDefinitions;

import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ProductPage;
import utils.DriverManager;

public class Task2 {

    private final ProductPage productPage;
    private final HomePage homePage;

    public Task2() {
       
        this.productPage = new ProductPage(DriverManager.getDriver());
        this.homePage = new HomePage(DriverManager.getDriver());
    }

    @Given("I navigate to the Juice Shop home page")
    public void navigateToJuiceShopHomePage() {
        DriverManager.getDriver().get("https://juice-shop.herokuapp.com/#/");
    }

    @When("I click on the first product")
    public void clickOnTheFirstProduct() {
        homePage.dismissWelcomeAndCookieMessages();
        productPage.clickOnFirstProduct();
    }

    @Then("I assert that a popup appears with the product image")
    public void assertPopupAndImage() {
        Assert.assertTrue(productPage.isPopupDisplayed(), "The product popup is not displayed.");
        Assert.assertTrue(productPage.isPopupImageDisplayed(), "The product image in the popup is not displayed.");
    }

    @And("I check if a review is available for the product")
    public void assertProductReview() {
        Assert.assertTrue(productPage.isProductReviewSectionDisplayed(), "The product review is not displayed.");
    }

    @Then("I expand the product review if available and wait")
    public void clickOnProductReview() {
        productPage.expandProductReviewSection();
    }

    @Then("I close the product form")
    public void closePopup() {
        productPage.closePopup();
        DriverManager.quitDriver();
    }
}
