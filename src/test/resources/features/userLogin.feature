@login
Feature: User login
  As registered user
  I want to login to the system
  To access my account

  @success
  Scenario: successful authorization
    Given Registered user exists in system
    When User opens login page
    And User enters correct credentials and logs in
    Then User is successfully authorized