@create_employee
Feature: Create user

  Background:
    Given user is logged into the application

  @positive
  Scenario Outline: Create new employee  <firstName> <lastName>
    And the employees are deleted from the list
      | <firstName> <lastName> |
    And user opens the Create Employee form
    And user fills in all the employee details
      | firstName   | lastName   | startDate   | email   |
      | <firstName> | <lastName> | <startDate> | <email> |
    When user approves the creation of the new user
    Then user is redirected to Employees page
    And the employee '<firstName> <lastName>' is present in the employee list
    When user opens the employee '<firstName> <lastName>' details
    Then the employee details are:
      | firstName   | lastName   | startDate   | email   |
      | <firstName> | <lastName> | <startDate> | <email> |

    Examples:
      | firstName | lastName | startDate  | email                |
      | marin     | onofrei  | 2067-05-18 | some.valid@el.com    |
      | John      | Smith    | 2018-05-18 | some.valid.23@el.com |

  @negative
  Scenario Outline: Create new employee with invalid Start Date <startDate>
    And user opens the Create Employee form
    And user fills in all the employee details
      | firstName   | lastName   | startDate   | email   |
      | <firstName> | <lastName> | <startDate> | <email> |
    When user approves the creation of the new user
    Then the application displays the creation error alert window with message 'Error trying to create a new employee: {"start_date":["can't be blank"]})'
    When user closes the creation error alert
    And user closes the Create User page
    Then the user '<firstName> <lastName>' is not present in the list
    Examples:
      | firstName | lastName | startDate   | email             |
      | invalid   | date     | 2067-00-18  | some.valid@el.com |
      | invalid   | date     | 2067-13-18  | some.valid@el.com |
      | invalid   | date     | 2067-01-00  | some.valid@el.com |
      | invalid   | date     | 2067-01-1   | some.valid@el.com |
      | invalid   | date     | 2067-01-32  | some.valid@el.com |
      | invalid   | date     | 2067-01-032 | some.valid@el.com |
      | invalid   | date     | 2067-01-001 | some.valid@el.com |
      | invalid   | date     | 206-01-01   | some.valid@el.com |
      | invalid   | date     | 19-01-01    | some.valid@el.com |