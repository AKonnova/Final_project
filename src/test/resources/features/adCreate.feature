@advertisement
Feature: Advertisement creation
  As registered user
  I want to create an advertisement
  To post it to the system

  @creation
  Scenario: Successful advertisement creation
    Given User is registered in the system
    And User is authorized in the system for ad creation
    When User creates new advertisement
    Then Ad is displayed in user profile