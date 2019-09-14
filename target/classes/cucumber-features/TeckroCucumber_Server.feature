Feature: Booking Hotel Server testing

  Scenario: User checks availability of room for a specific date
    Given the user that wants to check availability of room and inputs date "2019-11-24"
    Then the user asserts that the number of rooms available is 10
    Then the user asserts that the price for the room is 120

  Scenario Outline: User books a room
    Given the user that books a room on the "<CheckInDate>" for "<numOfDays>" days
    Then the user asserts that the checkout date is on "<CheckOutDate>"
    Examples:
      | CheckInDate | numOfDays | CheckOutDate |
      | 2019-04-28  | 2         | 2019-04-30    |
      | 2019-07-09  | 1         | 2019-07-10    |
      | 2019-09-12  | 4         | 2019-09-16    |

  Scenario: Negative Test: Checking that user gets the same price when they check availability and when they are booking
    Given the user that checks and books room on "2019-12-01"
    Then the user asserts that they don't receive the same price as when they checked availability