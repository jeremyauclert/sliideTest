@news
Feature: News
  Description: As a user I want to see my news

  Background: User is Logged In
    Given I have a LoginScreen
    When I submit user1 and password
    Then I should be logged in



  Scenario: news images are loaded
    Given there is internet connection
    Then images are displayed in the rows on the list

  Scenario: failed to load images
    Given there is no internet connection
    Then Failed to load news
