@login
Feature: User login
  As registered user
  I want to login to the system
  To access my account

  @success
  Scenario: successful authorization
    Given User is registered in the system
    When User opens login page
    And Enters correct data and logs in
    Then User is authorized