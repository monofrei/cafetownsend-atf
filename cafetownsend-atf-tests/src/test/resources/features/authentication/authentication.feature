@authentication
Feature: Authentication Feature

  @login
  Scenario: Verify user can login into the application with valid credentials
    Given user opens the Login page
    When user logs in using the credentials Luke:Skywalker
    Then user is redirected to Employees page
    And the greetings displays 'Hello Luke'

  @logout
  Scenario: Verify user can logout and is redirected to Login page
    Given user is logged into the application
    When user logs out from the application
    Then user is redirected to Login page
    When user opens the Employees page
    Then user is redirected to Login page