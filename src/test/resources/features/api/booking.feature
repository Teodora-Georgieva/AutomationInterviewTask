@api @smoke
Feature: Create booking

Scenario: Create a new booking
    Given I prepare booking details
      | firstname      | John       |
      | lastname       | Doe        |
      | totalprice     | 150        |
      | depositpaid    | true       |
      | checkin        | 2026-08-10 |
      | checkout       | 2026-08-15 |
      | additionalneeds| Breakfast  |
    When I create the booking
    Then the response status code should be 200
    And the response should contain the created booking

@api
Scenario: Create a booking with an invalid payload
    Given I prepare an invalid booking payload with missing firstname
      | lastname        | Doe        |
      | totalprice      | 150        |
      | depositpaid     | true       |
      | checkin         | 2026-08-10 |
      | checkout        | 2026-08-15 |
      | additionalneeds | Breakfast  |
    When I create the booking
    Then the response status code should be 500

@api
Scenario: Create a booking and retrieve it
    Given I prepare booking details
      | firstname       | John       |
      | lastname        | Doe        |
      | totalprice      | 150        |
      | depositpaid     | true       |
      | checkin         | 2026-08-10 |
      | checkout        | 2026-08-15 |
      | additionalneeds | Breakfast  |
    When I create the booking
    And I get the created booking
    Then the response status code should be 200
    And the response should contain the retrieved booking

@api
Scenario: Create a booking and update its first name
    Given I prepare booking details
      | firstname       | John       |
      | lastname        | Doe        |
      | totalprice      | 150        |
      | depositpaid     | true       |
      | checkin         | 2026-08-10 |
      | checkout        | 2026-08-15 |
      | additionalneeds | Breakfast  |
    When I create the booking
    And I authenticate with valid credentials
    And I update the booking "firstname" to "Jane"
    Then the response status code should be 200
    And the response should contain "firstname" "Jane"