@PROJ-5
Feature: github login

  @PROJ-6
  Scenario: login without username and password
    Given user is on github homepage
    When user clicks on Sign in button
    Then user is displayed login screen