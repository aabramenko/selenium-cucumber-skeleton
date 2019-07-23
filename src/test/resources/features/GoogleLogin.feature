Feature: Google login feature

@Login
Scenario: Verify that not possible to login with not registered email
  Given I as not logged in user open login page
When I on login page submit absent email "not-registered-email@gmaile.com"
Then I on login page should see error message "Couldn't find your Google Account"
And email input is still displayed

@Login
Scenario: Verify that password input is displayed after submitting of existing email
Given I as not logged in user navigate to login page
When I on login page submit existing email "selenium@gmail.com"
Then I on login page should see password input