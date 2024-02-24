Feature: Rahul Shetty Academy

  Scenario: Angular practice
    Given I'm on page "https://rahulshettyacademy.com/angularpractice"
    When I put in the "Name" field the value of "Benny"
    When I put in the "Email" field the value of "benny@example.com"
    When I put in the "Password" field the value of "admin"
    When I check the checkbox on the page
    When I select the "Male" option from the combobox on the Angular practice page
    When I select the "Student" option from the radiobuttons
    When I put in the "Date of Birth" field the value of "19/04/2012"
    When I click on the submit button
    Then I verify the success alert appears with the message "The Form has been submitted successfully!"

  Scenario: Login page practice
    Given I'm on page "https://rahulshettyacademy.com/loginpagePractise"
    When I enter in the following fields the next values on the Login page
      | Username | rahulshettyacademy |
      | Password | learning           |
    When I select the "User" radiobutton on the Login page
    Then I verify a confirm pane appeared on the Login page
    When I click on the Okay button from the confirm pane on the Login page
    Then I verify the confirm pane disappeared on the Login page
    When I select the "Consultant" option from the combobox on the Login page
    When I Agree to the terms and conditions on the Login page
    When I click on the sign in button on the Login page
    Then I verify I am on the shop page
    When I add all items to cart on the shop page
    Then I verify I have 4 items on cart on the shop page
    When I click on the checkout button on the shop page
    Then I verify I am on the cart view