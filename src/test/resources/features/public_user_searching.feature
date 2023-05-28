Feature: Able to get search results of users
  A publicly accessible endpoint should allow the search of users by a query in a paginated format

  Scenario Outline: Search results has results
    Given A user wishes to "<query>" on <page> with <count> users
    When A search is performed
    Then Search results should return paginated results with "<username>" as user

    Examples:
      | query         | page | count | username         |
      | Debra         | 1    | 10    | debra#0004       |
      | Christine     | 1    | 10    | christine#0005   |
      | Juan          | 1    | 10    | juan#0007        |
      | Bob           | 1    | 10    | bob#0006         |


  Scenario Outline: Search results has next page URL
    Given A user wishes to "<query>" on <page> with <count> users
    When A search is performed
    Then A next page URL is <expected>

    Examples:
      | query         | page | count | expected |
      | Debra         | 1    | 1     | true     |
      | Debra         | 2    | 1     | true     |
      | Debra         | 3    | 1     | false    |


  Scenario Outline: Search results has previous page URL
    Given A user wishes to "<query>" on <page> with <count> users
    When A search is performed
    Then A previous page URL is <expected>

    Examples:
      | query         | page | count | expected |
      | Debra         | 1    | 1     | false    |
      | Debra         | 2    | 1     | true     |
      | Debra         | 3    | 1     | true     |

