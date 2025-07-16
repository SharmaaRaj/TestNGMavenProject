package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageDetailsWiki {

    WebDriver driver = new ChromeDriver();

    @FindBy(xpath = "//input[@id='searchInput']")
    WebElement searchBox;

    @FindBy (xpath = "//select[@id='searchLanguage']")
    WebElement languageSelect;

    @FindBy (xpath = "//button[@type='submit']")
    WebElement searchIcon;

    @FindBy (xpath = "//h1[@id='firstHeading']")
    WebElement pageHeaderText;

    public PageDetailsWiki() {
        PageFactory.initElements(driver, this);
    }

    public void launchUrl(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void enterText(String InputText) throws InterruptedException {
        Thread.sleep(Long.parseLong("1000"));
        searchBox.click();
        searchBox.sendKeys(InputText);
    }

    public void languageSelection(String language){
        Select selectLanguage = new Select(languageSelect);
        selectLanguage.selectByVisibleText(language);
    }

    public void searchIcon(){
        searchIcon.click();
    }

    public void validateHeader(String headerInfo) throws InterruptedException {

        Thread.sleep(2000);
        if(pageHeaderText.getText().equalsIgnoreCase(headerInfo))
        {
            System.out.println("Passed");
        }
    }

}
