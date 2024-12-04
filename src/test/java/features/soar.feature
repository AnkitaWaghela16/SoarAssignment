Feature: OWASP Juice Shop functional test

 # Scenario: Display maximum items on homepage
  #  Given I navigate to the web application
  #  When I scroll to the end of the page
   # And I change items per page to the maximum number
   # Then I assert that the homepage displays all items

 # Scenario: Verify the product popup and review section for the first product
 #   Given I navigate to the Juice Shop home page
  #  When I click on the first product
  #  Then I assert that a popup appears with the product image
   # And I check if a review is available for the product
   # When I expand the product review if available and wait
   # Then I close the product form

  Scenario: Validate input fields and complete user registration
    Given I navigate to the Juice Shop registration page
    When I click on all input fields without entering any data
    Then I assert that validation messages are displayed for all fields
    When I fill the registration form with valid data
    And I click on Show Password Advice
    And I submit the registration form
    Then I assert that a successful registration message is displayed
    And I am redirected to the login page
    When I enter the registered email and password
    And I click the login button
    Then I assert that I am successfully logged into the application
    
     Scenario: Add multiple products to basket and complete the checkout process
    Given I log in to the Juice Shop with valid credentials
    When I add 5 different products to the basket and assert success popup after adding each product
    Then I assert that the basket number has changed to 5
    When I navigate to the basket page
    And I increase the quantity of a product in the basket
    And I delete a product from the basket
    Then I assert that the total price is updated correctly
    When I proceed to checkout and add address information
    And I select a delivery method
    Then I assert that my wallet has no money
    When I add a credit card with random information
    And I complete the purchase and assert
    
