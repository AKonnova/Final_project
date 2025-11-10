@registration
Feature: User registration
  As a new user
  I want to login to the system
  In order to be able to use the functionality

  @positive
  Scenario: Successful registration with unique email
    Given New user data is generated
    When User opens home page
    And User navigates to registration form
    And User registers with valid data
    Then Authorized user name displayed in header

  @existingData
  Scenario: Registration failure with existing email
    Given User already registered in system
    When User opens home page
    And User navigates to registration form
    And User attempts registration with existing data
    Then Registration error message displayed