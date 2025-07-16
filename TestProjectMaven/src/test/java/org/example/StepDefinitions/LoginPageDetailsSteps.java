package org.example.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.SauceDemoLoginPage;

import java.util.List;

public class LoginPageDetailsSteps {

    SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();

    @Given("I am on the Login page")
    public void i_am_on_the_login_page() {
        sauceDemoLoginPage.validatePageUrl();
    }

    @Then("I should see the following elements")
    public void i_should_see_the_input_field(List<String> inputFieldName) {
        sauceDemoLoginPage.verifyElementPresent(inputFieldName);
    }

    @When("I enter the username {string}")
    public void i_enter_the_username(String string) {
        sauceDemoLoginPage.enterUsername(string);
    }

    @When("I enter the password {string}")
    public void i_enter_the_password(String string) {
        sauceDemoLoginPage.enterPassword(string);
    }

    @When("I click the Login button")
    public void i_click_the_button() {
        sauceDemoLoginPage.clickLoginButton();
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String string) {
        sauceDemoLoginPage.validateErrorMessage(string);
    }

    @When("I click the Login button without entering credentials")
    public void i_click_the_login_button_without_entering_credentials() {
        sauceDemoLoginPage.clickLoginButton();
    }

    @Then("local storage should contain a key {string} with value {string}")
    public void local_storage_should_contain_a_key_with_value(String expectedCookie, String expectedCookieName) {
        sauceDemoLoginPage.validateCookies(expectedCookie, expectedCookieName);
    }

    @When("I click the error close icon")
    public void i_click_the_error_close_icon() {
        sauceDemoLoginPage.clickErrorCloseIcon();
    }

    @Then("the error message should disappear")
    public void the_error_message_should_disappear() {
        sauceDemoLoginPage.errorMessageDisappear();
    }

    @Then("I Close the browser")
    public void then_i_close_the_browser() {
        sauceDemoLoginPage.closeBroswer();

    }


}
