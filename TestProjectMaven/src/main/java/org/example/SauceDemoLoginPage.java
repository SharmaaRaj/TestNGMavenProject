package org.example;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SauceDemoLoginPage {

    private WebDriver driver;
    BasicActions basicActions = new BasicActions();
    SoftAssert softAssert = new SoftAssert();

    public SauceDemoLoginPage() {
        this.driver = basicActions.driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessageContainer;

    @FindBy(xpath = "//input[@id='user-name']/parent::div //*[@data-icon='times-circle']")
    private WebElement userNameErrorIcon;

    @FindBy(xpath = "//input[@id='password']/parent::div //*[@data-icon='times-circle']")
    private WebElement passwordErrorIcon;

    @FindBy(className = "error-button")
    private WebElement errorMessageCloseIcon;

    // Action Methods

    public void validatePageUrl() {
        basicActions.waitForElementToBeDisplayedWithRetries(usernameInput, 10);
        String expectedUrl = driver.getCurrentUrl();
        String actualUrl = basicActions.properties.getProperty("url");
        if (expectedUrl != null) {
            softAssert.assertTrue(expectedUrl.equalsIgnoreCase(actualUrl), "Expected URL: " + expectedUrl + ", but got: " + actualUrl);
        }
        softAssert.assertAll();
    }

    public void enterUsername(String username) {
        basicActions.waitForElementToBeDisplayed(usernameInput, 5);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        basicActions.waitForElementToBeDisplayed(passwordInput, 5);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        basicActions.waitForElementToBeDisplayed(loginButton, 5);
        loginButton.click();
    }

    public void validateErrorMessage(String expectedErrorMessage) {
        basicActions.waitForElementToBeDisplayedWithRetries(errorMessageContainer, 10);
        String actualErrorMessage = basicActions.getElementText(errorMessageContainer);
        softAssert.assertTrue(actualErrorMessage.equalsIgnoreCase(expectedErrorMessage), actualErrorMessage + " error message does not match expected value " + expectedErrorMessage);
    }

    public void validateElementIsPresent(WebElement element) {
        basicActions.waitForElementToBeDisplayedWithRetries(element, 10);
        softAssert.assertTrue(element.isDisplayed(), "Element is not displayed: " + element);
        softAssert.assertAll();
    }

    public void verifyElementPresent(List<String> elementNames) {
        for (String elementName : elementNames) {
            System.out.println(elementName);
            elementsList(elementName);
        }
    }

    public void elementsList(String elementName) {
        switch (elementName) {
            case "UserName Textbox":
                validateElementIsPresent(usernameInput);
                break;
            case "Password Textbox":
                validateElementIsPresent(passwordInput);
                break;
            case "Login Button":
                validateElementIsPresent(loginButton);
                break;
            case "Error Message":
                clickLoginButton();
                validateElementIsPresent(errorMessageContainer);
                break;
            case "Username error icon":
                validateElementIsPresent(userNameErrorIcon);
                break;
            case "Password error icon":
                validateElementIsPresent(passwordErrorIcon);
                break;
            default:
                throw new IllegalArgumentException("Element not recognized: " + elementName);
        }
    }

    public void validateCookies(String expectedCookie, String expectedCookieName) {
        Cookie cookie = driver.manage().getCookieNamed(expectedCookie);
        if (cookie != null) {
            String actualCookieName = cookie.getValue();
            softAssert.assertTrue(actualCookieName.equalsIgnoreCase(expectedCookieName), "Expected cookie value: " + expectedCookieName + ", but got: " + actualCookieName);
            System.out.println(actualCookieName + " " + expectedCookieName);
        } else {
            softAssert.fail("Cookie with name " + expectedCookie + " not found.");
        }
        softAssert.assertAll();
    }

    public void clickErrorCloseIcon() {
        basicActions.waitForElementToBeDisplayedWithRetries(errorMessageCloseIcon, 10);
        errorMessageCloseIcon.click();
    }

    public void errorMessageDisappear() {
        basicActions.elementNotVisible(errorMessageCloseIcon);
    }

    public void closeBroswer() {
        Utils.quitBrowser();
    }
}
