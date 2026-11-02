
Feature: SauceDemo Login Functionality

  As a SauceDemo user
  I want to log in to the application
  So that I can access the products page

  Background:
    Given user launches the SauceDemo application

  @smoke @positive
  Scenario: Verify successful login with valid credentials
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks on login button
    Then user should be redirected to products page

  @regression @negative
  Scenario Outline: Verify login with invalid credentials
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks on login button
    Then user should see login error message

    Examples:
      | username        | password       |
      | invalid_user    | invalid_pass   |
      | standard_user   | wrong_password |
      | invalid_user    | secret_sauce   |

  @regression @negative
  Scenario: Verify login with empty credentials
    When user enters username ""
    And user enters password ""
    And user clicks on login button
    Then user should see login error message

  @regression @negative
  Scenario: Verify login with locked out user
    When user enters username "locked_out_user"
    And user enters password "secret_sauce"
    And user clicks on login button
    Then user should see login error message
