Feature: Login

  Scenario: Verify user can login into the application with valid credentials
    Given user opens the Login Page
    When user logs in using the credentials Luke:Skywalker
    Then user is redirected to Employees Page