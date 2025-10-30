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
    When User clicks edit button
    Then Ad create form is opened