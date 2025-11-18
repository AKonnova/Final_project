@advertisement
Feature: Advertisement delete
  As registered user
  I want to delete an advertisement
  To clear out-of-date ads in the system

  @delete
  Scenario: Successful advertisement delete
    Given User is registered in the system for ad deletion
    And User is authorized in the system for ad deletion
    And User has a created advertisement for deletion
    When User verifies advertisement is published in profile
    And User finds and opens advertisement from search
    And User clicks delete button in ad view
    Then Advertisement is not found in search