@login
Feature: Login
  As a user I want to log in to the app

  Scenario Outline: Input email and password in correct format
    Given I have a LoginActivity
    When I input email <email>
    And I input password <password>
    And I press submit button
    Then I should <see> auth error

    Examples:
      | email    | password | see   |
      | password | password | true  |
      | user1    | password | false |

#Scenario: user opens the android app first time (when not logged in yet)
#Given  the user opens app for the first time (when not logged in yet)
#Then  login screen with user name and password entries and login button is displayed
#Scenario: user login failed
#Given  the user provided wrong user name and/or password
#When  login button is clicked
#Then  error markers are displayed by user name and/or password entries
#Scenario: user login succeed (credentials provided below)
#Given  the user provided right user name and password
#When  login button is clicked
#Then  user is taken to the news screen
#Scenario: user opens app next time (when previously logged in)
#Given  the user opens app next time (when previously logged in)
#Then  user is taken straight to the news screen
