Feature: Advantage

  Scenario: Create Account
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I click on the user menu button in the page navbar
    Then I verify the User Menu pane is open
    When I click on the Create Account button in the User Menu pane
    Then I verify I am on the Create Account page
    When I fill up the next fields in the Create Account page
      | Username         | Team003        |
      | Email            | team3@mail.com |
      | Password         | Team3          |
      | Confirm password | Team3          |
    Then I agree to the www.AdvantageOnlineShopping.com Conditions of Use and Privacy Notice in the Create Account page
    When I click on the register button on the Create Account page
    Then I verify the username "Team003" appears in the page navbar

  Scenario: Login
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I click on the user menu button in the page navbar
    Then I verify the User Menu pane is open
    When I fill up the next fields in the User Menu pane
      | Username | Team003 |
      | Password | Team3   |
    Then I click on the Sign Up button on the User Menu pane
    Then I verify the username "Team003" appears in the page navbar

  Scenario: Search
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I search for a random product

  Scenario: Contact
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I send a message to their contact with this email "team3@mail.com"

  Scenario: Chat
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I chat with the page bot