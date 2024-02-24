Feature: YouTube

  Scenario Outline: Play a video
    Given I'm on page "https://www.youtube.com"
    When I search for "<search>"
    Then I verify I'm on the search results page for "<search>"
    Examples:
      | search        |
      | rtx 4060      |
      | toro agresivo |
