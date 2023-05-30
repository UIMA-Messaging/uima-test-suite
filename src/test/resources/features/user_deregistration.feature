Feature: Users can deregister their account
  User should be able to delete their account from the system

  Scenario Outline: User should be able to delete their account
    Given A user wants to deregister with display name "<displayName>"
    When An account deregistration is performed
    Then No user with user "<displayName>" should exist

    Examples:
      | displayName              |
      | thisIsADisplayName       |
      | hereIsAnotherDisplayName |
      | yetAnotherOneHere        |
