@advertisement
Feature: Advertisement delete
  As registered user
  I want to delete an advertisement
  To clear out-of-date ads in the system
  (But I cannot because there is no delete button)

  @delete
  Scenario: Successful advertisement delete
    Given User is registered in the system for ad deletion
    And User is authorized in the system for ad deletion
    And User has a created advertisement for deletion
    When User goes to profile with ads
    Then Delete button is available