Feature: Login to HRM Application

  Scenario: Open HRM Application
    Given User is on HRMLogin page "https://opensource-demo.orangehrmlive.com"
    Then I make sure I am on HRMLogin page
    And I wait 4 seconds

  @ValidCredentials
  Scenario: Login with valid credentials
    Given User is on HRMLogin page "https://opensource-demo.orangehrmlive.com"
    When User enters username as "Admin" and password as "admin123"
    Then User should be able to login sucessfully and new page open

  @InvalidCredentials
  Scenario Outline: Login with invalid credentials
    Given User is on HRMLogin page "https://opensource-demo.orangehrmlive.com"
    When User enters username as "<username>" and password as "<password>"
    Then User should be able to see error message "Invalid credentials"
    Examples:
      | username | password  |
      | Admin    | admin12$$ |
      | admin$$  | admin123  |
      | abc123   | xyz$$     |