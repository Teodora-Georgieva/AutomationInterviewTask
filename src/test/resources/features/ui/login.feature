Feature: Login

  @ui @smoke
  Scenario: Login with valid credentials
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to the products page

     Examples:
        | username                 | password     |
        | standard_user            | secret_sauce |
        | problem_user             | secret_sauce |
        | error_user               | secret_sauce |
        | performance_glitch_user  | secret_sauce |
        | visual_user              | secret_sauce |

  @ui
  Scenario Outline: Login with invalid credentials
    Given I am on the SauceDemo login page
    When I login with username "<username>" and password "<password>"
    Then I should see the login error message "<errorMessage>"

    Examples:
      | username        | password       | errorMessage                                                               |
      | locked_out_user | secret_sauce   | Epic sadface: Sorry, this user has been locked out.                        |
      | standard_user   | wrong_password | Epic sadface: Username and password do not match any user in this service  |
      | wrong_user      | secret_sauce   | Epic sadface: Username and password do not match any user in this service  |
      |                 | secret_sauce   | Epic sadface: Username is required                                         |
      | standard_user   |                | Epic sadface: Password is required                                         |
      |                 |                | Epic sadface: Username is required                                         |