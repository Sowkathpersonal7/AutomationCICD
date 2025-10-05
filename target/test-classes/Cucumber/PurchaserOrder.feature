@tag
Feature: Purchase the order from Ecommerce Website
I want to use this template for feature file


  Background: 
    Given I landed on Ecommerce page

  Scenario Outline: Positive test case of submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product to the cart <order>
    And Checkout <oder> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples:
      | name              | password  | oder              |
      | sowkath_m@gmail.com | Test@123  | ZARA COAT 3             |
