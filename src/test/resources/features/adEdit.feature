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
    When User finds and opens advertisement from search for editing
    And User clicks edit button in ad view
    And User verifies edit page is opened
    And User updates advertisement title
    And User saves changes
    Then Updated advertisement is found in search