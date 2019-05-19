Feature: Create user

  Background:
    Given user is logged into the application

  Scenario Outline: Create new user <firstName> <lastName>
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