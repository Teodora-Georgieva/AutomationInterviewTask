@api
@smoke
Feature: Restful Booker authentication

  Scenario: Successfully authenticate with valid credentials
    When I authenticate with valid credentials
    Then the authentication should be successful
    And an authentication token should be returned