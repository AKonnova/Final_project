@advertisement
Feature: Advertisement edit
  As registered user
  I want to edit an advertisement
  To make it up-to-date in the system

  @edit
  Scenario: Successful advertisement edit
    Given User is registered in the system for ad edit
    And User is authorized in the system for ad edit
    And User has a created advertisement
    When User finds advertisement in profile
    And User opens advertisement for editing
    And User clicks edit button
    Then Edit advertisement form is opened