@authentication
Feature: Authentication Feature

  @login @positive
  Scenario: Verify user can login into the application with valid credentials
    Given user opens the Login page
    When user logs in using the credentials 'Luke':'Skywalker'
    Then user is redirected to Employees page
    And the greetings displays 'Hello Luke'

  @login @negative
  Scenario Outline: Verify user can not login into the application with blank <field>
    Given user opens the Login page
    When user logs in using the credentials '<username>':'<password>'
    Then user is redirected to Login page
    And the application highlights the Login <field> as invalid
    Examples:
      | field    | username | password  |
      | username |          | Skywalker |
      | password | Luke     |           |


  @login @negative
  Scenario Outline: Verify user can not login with invalid credentials <username>_<password>
    Given user opens the Login page
    When user logs in using the credentials '<username>':'<password>'
    Then user is redirected to Login page
    And the application displays message 'Invalid username or password!'
    Examples:
      | username | password  |
      | luke     | Skywalker |
      | Luke     | skywalker |
      | Luke     | password  |
      | username | password  |
      | admin    | admin     |


  @logout @positive
  Scenario: Verify user can logout and is redirected to Login page
    Given user is logged into the application
    When user logs out from the application
    Then user is redirected to Login page
    When user opens the Employees page
    Then user is redirected to Login page