package stepDefinitions;

import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import pages.BasketPage;
import pages.AddressPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import pages.ProductPage;
import pages.RegistrationPage;
import utils.DriverManager;

public class Task4 {

    private final BasketPage basketPage;
    private final HomePage homePage;
    private final ProductPage productPage;
    private final LoginPage loginPage;
    private final AddressPage addresspage;
    private final PaymentPage paymentPage;
    
    private double initialTotal;
    private double finalTotal;

    public Task4() {
        this.basketPage = new BasketPage(DriverManager.getDriver());
        this.homePage = new HomePage(DriverManager.getDriver());
        this.productPage = new ProductPage(DriverManager.getDriver());
        this.loginPage = new LoginPage(DriverManager.getDriver());
        this.addresspage = new AddressPage(DriverManager.getDriver());
        this.paymentPage = new PaymentPage(DriverManager.getDriver());
        
    }

    @Given("I log in to the Juice Shop with valid credentials")
    public void logInToJuiceShopWithValidCredentials() {
        DriverManager.getDriver().get("https://juice-shop.herokuapp.com/#/");
        homePage.dismissWelcomeAndCookieMessages();
        homePage.clickOnAccountAndLogin();
        loginPage.isLoginPageDisplayed();
        loginPage.enterCredentials(RegistrationPage.email, RegistrationPage.password);
        loginPage.clickLoginButton();
        loginPage.isLoginPageDisplayed();
    }

    @When("I add 5 different products to the basket and assert success popup after adding each product")
    public void addProductsToBasket() throws InterruptedException {
        productPage.addProductsToBasket(5);
    }

    @Then("I assert that the basket number has changed to 5")
    public void assertBasketNumber() {
        productPage.assertBasketItemCount(5);
    }

    @When("I navigate to the basket page")
    public void navigateToBasketPage() {
        basketPage.openBasket();
        initialTotal = basketPage.getTotalPrice();
    }

    @And("I increase the quantity of a product in the basket")
    public void increaseProductQuantity() throws InterruptedException {
       basketPage.addProduct();
    }

    @And("I delete a product from the basket")
    public void deleteProductFromBasket() throws InterruptedException {
        basketPage.removeProduct();
    }

    @Then("I assert that the total price is updated correctly")
    public void assertTotalPriceIsUpdated() {
        finalTotal = basketPage.getTotalPrice();
        Assert.assertNotEquals(finalTotal, initialTotal, "The total price did not update correctly.");
    }

    @When("I proceed to checkout and add address information")
    public void proceedToCheckoutAndAddAddress() {
        basketPage.proceedToCheckout();
        addresspage.fillAddressDetails();
    }

    @And("I select a delivery method")
    public void selectDeliveryMethod() {
    	addresspage.selectDeliverySpeed();
    }

    @Then("I assert that my wallet has no money")
    public void assertWalletBalance() throws InterruptedException {
        paymentPage.checkWalletBalance();
        paymentPage.clickOnAddCreditCard();
    }

    @When("I add a credit card with random information")
    public void addCreditCardInformation() throws InterruptedException {
        paymentPage.selectcardAndEnterCardDetails();
    }

    @And("I complete the purchase and assert")
    public void completePurchase() {
        paymentPage.completePurchase();
        Assert.assertTrue(paymentPage.purchaseComplete);
		DriverManager.quitDriver();
    }
}
