
Feature: SauceDemo Checkout Functionality

  As a logged-in SauceDemo user
  I want to complete the checkout process
  So that I can purchase products successfully

  Background:
    Given user launches the SauceDemo application
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks on login button
    Then user should be redirected to products page

  @smoke @checkout @positive
  Scenario: Verify successful checkout with valid customer details
    When user adds "Sauce Labs Backpack" to cart
    And user opens shopping cart
    And user clicks checkout button
    And user enters checkout first name "John"
    And user enters checkout last name "Smith"
    And user enters checkout postal code "10001"
    And user clicks continue button
    Then checkout overview page should be displayed
    And checkout overview should contain "Sauce Labs Backpack"
    When user clicks finish button
    Then checkout success message should be displayed

  @regression @checkout @negative
  Scenario Outline: Verify checkout validation for missing customer details
    When user adds "Sauce Labs Backpack" to cart
    And user opens shopping cart
    And user clicks checkout button
    And user enters checkout first name "<firstName>"
    And user enters checkout last name "<lastName>"
    And user enters checkout postal code "<postalCode>"
    And user clicks continue button
    Then checkout error message should contain "<errorMessage>"

    Examples:
      | firstName | lastName | postalCode | errorMessage                |
      |           | Smith    | 10001      | First Name is required     |
      | John      |          | 10001      | Last Name is required      |
      | John      | Smith    |            | Postal Code is required    |

  @regression @checkout @positive
  Scenario: Verify checkout can be cancelled
    When user adds "Sauce Labs Backpack" to cart
    And user opens shopping cart
    And user clicks checkout button
    And user clicks cancel checkout button
    Then shopping cart page should be displayed
