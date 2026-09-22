Feature: Framework smoke test

  Scenario: Open SauceDemo
    Given I open the SauceDemo website
    Then the page title should be "Swag Labs"