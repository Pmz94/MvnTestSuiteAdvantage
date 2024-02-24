Feature: HRM Application

  Scenario: Open HRM Application
    Given I'm on page "https://opensource-demo.orangehrmlive.com"
    Then User makes sure it is on HRMLogin page
    And Wait 4 seconds

  @ValidCredentials
  Scenario: Login with valid credentials
    Given I'm on page "https://opensource-demo.orangehrmlive.com"
    When User logins with username "Admin" and password "admin123"
    Then User should be on the "Dashboard" page

  @InvalidCredentials
  Scenario Outline: Login with invalid credentials
    Given I'm on page "https://opensource-demo.orangehrmlive.com"
    When User logins with username "<username>" and password "<password>"
    Then User should be able to see error message "Invalid credentials"
    Examples:
      | username | password  |
      | Admin    | admin12$$ |
      | admin$$  | admin123  |
      | abc123   | xyz$$     |

  Scenario: Verify job details information
    Given I'm on page "https://opensource-demo.orangehrmlive.com"
    When User logins with username "Admin" and password "admin123"
    Then User should be on the "Dashboard" page
    When User clicks on "My Info" button on the Sidebar
    Then User should be on the "PIM" page
    When User clicks on "Job" button on the PIM Sidebar
    Then User verifies the PIM header title is "Job Details"
    Then User makes sure the following fields have these values
      | Job Title         | HR Manager             |
      | Job Category      | Officials and Managers |
      | Sub Unit          | Human Resources        |
      | Location          | Texas R&D              |
      | Employment Status | Full-Time Permanent    |