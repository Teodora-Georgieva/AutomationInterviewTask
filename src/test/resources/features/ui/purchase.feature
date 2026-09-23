Feature: Purchase

  @ui @smoke
    Scenario: Successfully purchase a product

        Given I'm logged in
        When I add "Sauce Labs Backpack" to the cart
        Then the cart should contain 1 item

         When I open the cart
         Then I should see the cart page
         And I should see "Sauce Labs Backpack" in the cart
         And the price of "Sauce Labs Backpack" in the cart should match the price on the products page

         When I click on checkout button
         Then I should see the checkout personal details page

         When I enter my order details
            | firstName | lastName | postalCode |
            | John      | Doe      | 1000       |
         And I place the order
         Then I should see the checkout overview page
         And I should see "Sauce Labs Backpack" on the page

         When I click on finish button
         Then I should see the order confirmation page