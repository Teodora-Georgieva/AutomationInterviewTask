Feature: Product sorting

@ui
Scenario: Sort product by prices in ascending order
    Given I'm logged in
    When I sort the products by "Price (low to high)"
    Then the products should be displayed in ascending order by price