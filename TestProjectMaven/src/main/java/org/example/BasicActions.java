package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class BasicActions extends Utils{

    public Properties properties;
    public WebDriver driver;

    public BasicActions() {
        Utils.browserIntialization();
        this.driver = super.driver;
        this.properties = super.properties;
        launchUrl();
    }


    public void waitForElementToBeDisplayed(WebElement webElement, int waitTime) {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(waitTime)).pollingEvery(Duration.ofMillis(100))
                    .until(ExpectedConditions.visibilityOf(webElement));
        }
        catch(TimeoutException | StaleElementReferenceException e){
            System.out.println("Element not displayed within " + waitTime + " seconds: " + e.getMessage());
        }
    }

    public void waitForElementToBeDisplayedWithRetries(WebElement webElement, int waitTime) {
        int maxRetries = 5;
        while (maxRetries>0){
            try{
                new WebDriverWait(driver, Duration.ofSeconds(waitTime)).pollingEvery(Duration.ofMillis(100))
                        .until(ExpectedConditions.visibilityOf(webElement));
                return;
            }
            catch (StaleElementReferenceException e){
                maxRetries--;
                System.out.println("StaleElementReferenceException caught, Retrying... Attempts left: " + maxRetries);
            }
            catch(TimeoutException e){
                System.out.println("Element not displyed within " + waitTime + " seconds: " + e.getMessage());
                return;
            }
        }
    }

    public void launchUrl(){
        driver.manage().deleteAllCookies();
        driver.get("Chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }

    public String getElementText(WebElement webElement) {
        waitForElementToBeDisplayedWithRetries(webElement, 10);
            return webElement.getText().trim();
    }

    public String getElementValue(WebElement webElement) {
        waitForElementToBeDisplayed(webElement, 10);
        try{
            String tagName = webElement.getTagName().toLowerCase();
            if ("input".equals(tagName) || "textarea".equals(tagName)) {
                return webElement.getAttribute("value").trim();
            } else {
                return webElement.getText().trim();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void elementNotVisible(WebElement webElement) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(webElement));
        } catch (TimeoutException e) {
            System.out.println("Element is still visible: " + webElement);
        }
    }

}
