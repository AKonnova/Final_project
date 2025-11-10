@advertisement
Feature: Advertisement creation
  As registered user
  I want to create an advertisement
  To post it to the system

  @creation
  Scenario: Successful advertisement creation
    Given Registered user exists in system
    And User is authorized for advertisement creation
    When User creates new advertisement
    Then Advertisement is displayed in user profile