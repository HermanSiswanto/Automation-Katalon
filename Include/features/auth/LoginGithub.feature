Feature: GitHub login Authentication
  As a user
  I want to login to GitHub
  So that I can access my account and features

  @Login
  Scenario Outline: User login with various credential combinations

    Given User is on GitHub login page
    When User enters username "<username>" and password "<password>"
    Then System should behave as "<expected_result>"

    Examples:
     | username                        | password           | expected_result     |
     | testing15pronetwork@gmail.com   | Mandiri1Automation | success             |
     | Testing15pro@gmail.com          | Mandiri            | invalid_credential  |
     |                                 | Mandiri1Automation | validation_error    |
     | testing15pronetwork@gmail.com   |                    | validation_error    |
     | TESTING15PRONETWORK@gmail.com   | Mandiri1Automation | success             |
     | testing15pronetwork@GMAIL.COM   | Mandiri1Automation | success             |  
