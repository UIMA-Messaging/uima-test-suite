Feature: Users can register a new account
  User should be able to create an account

  Scenario Outline: User should be able to create an account
    Given A user wants to register with display name "<displayName>"
    When An account registration is performed
    Then A registered user account should have "<displayName>"

    Examples:
      | displayName              |
      | thisIsADisplayName       |
      | hereIsAnotherDisplayName |
      | yetAnotherOneHere        |
