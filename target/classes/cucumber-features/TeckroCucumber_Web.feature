Feature: Booking Hotel Web testing

  Scenario: User checks availability of room for a specific date
    Given the user that checks availability for a room for date "2019-02-22"
    Then the user clicks check
    Then the user receives response

  Scenario Outline: Negative Test: Calendar test - User inputs wrong dates
    Given the user that checks availability for a room for date "<Date>"
    When the user clicks check
    Then the user asserts that they are blocked by this
    Examples:
      | Date        |
      | 0000-01-22  |
      | 30000-01-22 |
      | 2019-1      |
      | 2019-13-22  |
      | 2019-13-34  |