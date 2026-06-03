Feature: Product price rates query

  Scenario Outline: Successful price rate query based on application date
    Given a query is made with brand <brand_id> and product <product_id>
    When the client requests the price rate for the date "<application_date>"
    Then the response should be successful with status 200
    And the returned price rate should have the list identifier <price_list> and a price of <price>

    Examples:
      | application_date    | brand_id | product_id | price_list | price |
      | 2020-06-14T10:00:00 | 1        | 35455      | 1          | 35.50 |
      | 2020-06-14T16:00:00 | 1        | 35455      | 2          | 25.45 |
      | 2020-06-14T21:00:00 | 1        | 35455      | 1          | 35.50 |
      | 2020-06-15T10:00:00 | 1        | 35455      | 3          | 30.50 |
      | 2020-06-16T21:00:00 | 1        | 35455      | 4          | 38.95 |

  Scenario: Attempted query with null required fields
    Given a query is sent with empty or null data
    When the price rate search is processed
    Then the response should fail with an error status 400

  Scenario: Attempted query with invalid or negative identifiers
    Given a query is sent with invalid or negative data
    When the price rate search is processed
    Then the response should fail with an error status 400