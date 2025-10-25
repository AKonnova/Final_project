@registration
Feature: User registration
  As a new user
  I want to login to the system
  In order to be able to use the functionality

  @positive
  Scenario: Successful registration with unique email
    Given User data is generated
    When User opens home page
    And Goes to the registration form
    And Registers with correct data
    Then Authorized user username is displayed in header

  @existingData
  Scenario: Registration failure with existing email
    Given User is already registered in the system
    When User opens home page
    And Goes to the registration form
    And Registers with already existing data
    Then Registration error is displayed