Feature: Scenarios to Test in Login Page

  @smoke @login @regression
  Scenario: Find all elements in login page are exist
    Given I am on the Login page
    Then I should see the following elements
      | UserName Textbox    |
      | Password Textbox    |
      | Login Button        |
      | Error Message       |
      | Username error icon |
      | Password error icon |
    Then I Close the browser

  @regression
  Scenario: Login Attempt with Locked Out User
    Given I am on the Login page
    When I enter the username "locked_out_user"
    And I enter the password "secret_sauce"
    And I click the Login button
    Then I should see an error message "Sorry, this user has been locked out."
    Then I Close the browser

  @regression
  Scenario: Login with Valid Username and Invalid Password
    Given I am on the Login page
    When I enter the username "standard_user"
    And I enter the password "wrong_password"
    And I click the Login button
    Then I should see an error message "Username and password do not match"
    Then I Close the browser

  @regression
  Scenario: Login with Invalid Username and Valid Password
    Given I am on the Login page
    When I enter the username "invalid_user"
    And I enter the password "secret_sauce"
    And I click the Login button
    Then I should see an error message "Username and password do not match"
    Then I Close the browser

  @regression
  Scenario: Login with Empty Username and Password
    Given I am on the Login page
    When I click the Login button without entering credentials
    Then I should see an error message "Username is required"
    Then I Close the browser

  @regression
  Scenario: Attempt Login with Only Username
    Given I am on the Login page
    When I enter the username "standard_user"
    And I click the Login button
    Then I should see an error message "Password is required"
    Then I Close the browser

  @regression
  Scenario: Attempt Login with Only Password
    Given I am on the Login page
    When I enter the password "secret_sauce"
    And I click the Login button
    Then I should see an error message "Username is required"
    Then I Close the browser

  @regression @smoke
  Scenario: Validate Session Username After Successful Login
    Given I am on the Login page
    When I enter the username "standard_user"
    And I enter the password "secret_sauce"
    And I click the Login button
    Then local storage should contain a key "session-username" with value "standard_user"
    Then I Close the browser

  @regression
  Scenario: Error Message Can Be Dismissed
    Given I am on the Login page
    When I enter the username "invalid_user"
    And I enter the password "wrong_password"
    And I click the Login button
    Then I should see an error message "Username and password do not match any user in this service"
    When I click the error close icon
    Then the error message should disappear
    Then I Close the browser