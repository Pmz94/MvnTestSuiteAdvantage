Feature: Advantage

  Scenario: Create Account
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I create an account with this user: "Team003", this email: "team3@mail.com" and this password: "Team3"

  Scenario: Login
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I login with user: "Team003" and password "Team3"

  Scenario: Search
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I search for a random product

  Scenario: Contact
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I send a message to their contact with this email "team3@mail.com"

  Scenario: Chat
    Given I'm on page "https://www.advantageonlineshopping.com"
    When I chat with the page bot