Feature: Test login functionality

  @Demo
  Scenario: Verify that the user able to login with valid credentials
    When User enter valid username and password in login form
    And Clicks on Log in button
    Then Verify that the home page is displayed
