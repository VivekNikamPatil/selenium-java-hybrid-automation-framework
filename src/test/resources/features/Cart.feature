
Feature: SauceDemo Shopping Cart Functionality

  As a logged-in SauceDemo user
  I want to manage products in my shopping cart
  So that I can verify cart functionality

  Background:
    Given user launches the SauceDemo application
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks on login button
    Then user should be redirected to products page

  @smoke @cart @positive
  Scenario: Verify user can add a product to cart
    When user adds "Sauce Labs Backpack" to cart
    Then cart badge count should be 1
    When user opens shopping cart
    Then cart should contain "Sauce Labs Backpack"

  @regression @cart @positive
  Scenario: Verify user can add multiple products to cart
    When user adds "Sauce Labs Backpack" to cart
    And user adds "Sauce Labs Bike Light" to cart
    Then cart badge count should be 2
    When user opens shopping cart
    Then cart should contain "Sauce Labs Backpack"
    And cart should contain "Sauce Labs Bike Light"

  @regression @cart @positive
  Scenario: Verify user can remove a product from cart
    When user adds "Sauce Labs Backpack" to cart
    Then cart badge count should be 1
    When user removes "Sauce Labs Backpack" from cart
    Then cart badge should not be displayed

  @regression @cart @negative
  Scenario: Verify shopping cart is empty before adding products
    When user opens shopping cart
    Then shopping cart should be empty
