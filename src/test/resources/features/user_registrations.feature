Feature: Users can register and unregister
  User should be able to create an account and delete their accounts

  Scenario Outline: Search results has results
    Given A user wants to register with display name "<displayName>"
    When An account registration is performed
    Then A registered user account should have "<displayName>"

    Examples:
      | displayName              |
      | thisIsADisplayName       |
      | hereIsAnotherDisplayName |
      | yetAnotherOneHere        |
